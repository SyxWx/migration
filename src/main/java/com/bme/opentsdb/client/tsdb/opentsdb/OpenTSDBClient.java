package com.bme.opentsdb.client.tsdb.opentsdb;


import com.bme.opentsdb.client.tsdb.bean.request.Query;
import com.bme.opentsdb.client.tsdb.common.Json;
import com.bme.opentsdb.client.tsdb.http.HttpClient;
import com.bme.opentsdb.client.tsdb.http.callback.QueryHttpResponseCallback;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class OpenTSDBClient {

    private static final long GANG_LU_TIME = 1625068800L;

    private final OpenTSDBConfig config;

    private final HttpClient httpClient;

    /***
     * 异步查询
     * @param query 查询对象
     * @param callback 回调
     */
    public void query(Query query, QueryHttpResponseCallback.QueryCallback callback) throws JsonProcessingException {
        QueryHttpResponseCallback queryHttpResponseCallback = new QueryHttpResponseCallback(callback, query);
        httpClient.post(Api.QUERY.getPath(), Json.writeValueAsString(query), queryHttpResponseCallback);
    }
}
