package com.bme.opentsdb.client.tsdb;


import com.bme.opentsdb.client.tsdb.bean.request.Point;
import com.bme.opentsdb.client.tsdb.bean.request.Query;
import com.bme.opentsdb.client.tsdb.bean.request.SubQuery;
import com.bme.opentsdb.client.tsdb.bean.response.DetailResult;
import com.bme.opentsdb.client.tsdb.bean.response.QueryResult;
import com.bme.opentsdb.client.tsdb.http.callback.BatchPutHttpResponseCallback;
import com.bme.opentsdb.client.tsdb.opentsdb.OpenTSDBClient;
import com.bme.opentsdb.client.tsdb.opentsdb.OpenTSDBClientFactory;
import com.bme.opentsdb.client.tsdb.opentsdb.OpenTSDBConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.nio.reactor.IOReactorException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;


@Slf4j
public class CrudTest {

    OpenTSDBClient client;

    /**
     * 初始化连接
     *
     * @throws IOReactorException
     */
    @Before
    public void config() throws IOReactorException {

        //自定义连接配置
        OpenTSDBConfig config = OpenTSDBConfig.address("http://192.168.1.131", 8599)
                // http连接池大小，默认100
                .httpConnectionPool(100)
                // http请求超时时间，默认100s
                .httpConnectTimeout(100)
                // 异步写入数据时，每次http提交的数据条数，默认50
                .batchPutSize(50)
                // 异步写入数据中，内部有一个队列，默认队列大小20000
                .batchPutBufferSize(20000)
                // 异步写入等待时间，如果距离上一次请求超多300ms，且有数据，则直接提交
                .batchPutTimeLimit(300)
                // 当确认这个client只用于查询时设置，可不创建内部队列从而提高效率
//                .readonly()
                // 每批数据提交完成后回调
                .batchPutCallBack(new BatchPutHttpResponseCallback.BatchPutCallBack() {
                    @Override
                    public void response(List<Point> points, DetailResult result) {
                        // 在请求完成并且response code成功时回调
                    }

                    @Override
                    public void responseError(List<Point> points, DetailResult result) {
                        // 在response code失败时回调
                    }

                    @Override
                    public void failed(List<Point> points, Exception e) {
                        // 在发生错误是回调
                    }
                })
                .config();
        client = OpenTSDBClientFactory.build(config);
    }

    @Test
    public void queeryOldInsertNew() throws Exception{

        System.out.println("1111");
        String signalNo = "BMENEWPM1909R0354_61";
        String deviceNo = "BMENEWPM1909R0354";
        String startTime = "1679535974";
        String endTime = "1679536188";
        String customerId = "1";
        List<QueryResult> resultList = queryBymetric(signalNo,deviceNo,startTime,endTime,customerId);
        if(resultList!=null && resultList.size()>0){
            LinkedHashMap<Long, Number> dps = resultList.get(0).getDps();
            dps.forEach((aLong,Number)->{
                System.out.println("time:"+aLong+"---------value:"+Number);
            });
        }
    }


    public List<QueryResult> queryBymetric(String signalNo,String deviceNo,String startTime,String endTime,String customerId){
        Query query = Query.begin(startTime)
                .end(endTime)
                .msResolution()
                .sub(SubQuery.metric(signalNo)
                        .aggregator(SubQuery.Aggregator.NONE)
                        .tag("device_no",deviceNo)
                        .tag("customer_id",customerId)
                        .build())
                .build();
        //同步查询返回结果
        List<QueryResult> resultList = new ArrayList<>();
        try {
            resultList =  client.query(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }



}
