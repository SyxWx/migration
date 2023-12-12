package com.bme.opentsdb.client.tsdb.opentsdb;


import com.bme.opentsdb.client.tsdb.bean.request.*;
import com.bme.opentsdb.client.tsdb.bean.response.QueryResult;
import com.bme.opentsdb.client.tsdb.common.Json;
import com.bme.opentsdb.client.tsdb.http.HttpClient;
import com.bme.opentsdb.client.tsdb.http.HttpClientFactory;
import com.bme.opentsdb.client.tsdb.http.callback.QueryHttpResponseCallback;
import com.bme.opentsdb.client.tsdb.sender.consumer.Consumer;
import com.bme.opentsdb.client.tsdb.sender.consumer.ConsumerImpl;
import com.bme.opentsdb.client.tsdb.sender.producer.Producer;
import com.bme.opentsdb.client.tsdb.sender.producer.ProducerImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.nio.reactor.IOReactorException;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Slf4j
@Data
public class OpenTSDBClient {

    private static final long GANG_LU_TIME = 1625068800L;

    private final OpenTSDBConfig config;

    private final HttpClient httpClient;

    private Producer producer;

    private Consumer consumer;

    private BlockingDeque<Point> queue;
    /***
     * 通过反射来允许删除
     */
    private static Field queryDeleteField;

    public OpenTSDBClient(OpenTSDBConfig config) throws IOReactorException {
        this.config = config;
        //从连接池获取客户端
        this.httpClient = HttpClientFactory.createHttpClient(config);
//        this.httpClient.start();

        if (!config.isReadonly()) {
            this.queue = new ArrayBlockingQueue<>(config.getBatchPutBufferSize());
            this.producer = new ProducerImpl(queue);
            this.consumer = new ConsumerImpl(queue, httpClient, config);
            this.consumer.start();

            try {
                queryDeleteField = Query.class.getDeclaredField("delete");
                queryDeleteField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        log.debug("the httpclient has started");
    }

    /***
     * 同步查询
     * @param query 查询对象
     * @return
     */
    public List<QueryResult> query(Query query) throws IOException, ExecutionException, InterruptedException {
        boolean checkGangLu = false;
        if(!CollectionUtils.isEmpty(query.getQueries())){
            SubQuery subQuery = query.getQueries().get(0);
            if(!CollectionUtils.isEmpty(subQuery.getTags())){
                Map<String, String> tags = subQuery.getTags();
                if(tags.containsKey("customer_id") && tags.get("customer_id").equals("14")){
                    checkGangLu = true;
                }
            }
        }
        Future<HttpResponse> future = httpClient.post(com.bme.cloud.opentsdb.bean.request.Api.QUERY.getPath(), Json.writeValueAsString(query));
        HttpResponse response = future.get();
        List<QueryResult> results = Json.readValue(ResponseUtil.getContent(response), List.class, QueryResult.class);
        if(CollectionUtils.isEmpty(results)){
            return results;
        }
        if(checkGangLu){
            LinkedHashMap<Long, Number> map =  results.get(0).getDps();
            for(Map.Entry<Long,Number> entry:map.entrySet()){
                if(entry.getKey()<GANG_LU_TIME){
                    entry.setValue(null);
                }else {
                    break;
                }
            }
        }
        return results;
    }




    /***
     * 异步查询
     * @param query 查询对象
     * @param callback 回调
     */
    public void query(Query query, QueryHttpResponseCallback.QueryCallback callback) throws JsonProcessingException {
        QueryHttpResponseCallback queryHttpResponseCallback = new QueryHttpResponseCallback(callback, query);
        httpClient.post(com.bme.cloud.opentsdb.bean.request.Api.QUERY.getPath(), Json.writeValueAsString(query), queryHttpResponseCallback);
    }




}
