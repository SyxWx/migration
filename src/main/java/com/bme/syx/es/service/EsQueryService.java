package com.bme.syx.es.service;

import com.bme.syx.es.entity.EsDusterRealTime;
import com.bme.syx.es.entity.EsMonitorRealTime;
import com.bme.syx.es.entity.EsProductRealTime;
import com.bme.syx.es.entity.EsSignalRealTime;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class EsQueryService {

    @Autowired
    private JestClient jestClient;

    public List<EsProductRealTime> queryProductEs(String deviceNo,long customerId){
        String queryJson = "{\n" +
                "\"query\":{\n" +
                "   \"bool\":{" +
                "       \"must\":[{\n" +
                "           \"match\":{\n" +
                "               \"customerId\":"+customerId+"\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "           \"match\":{\n" +
                "               \"deviceNo.keyword\":\""+deviceNo+"\"\n" +
                "            }\n" +
                "        }]\n" +
                "      }\n" +
                "   }\n" +
                "}";
        Search search = new Search.Builder(queryJson).addIndex("product").addType("_doc").build();
        SearchResult result  = null;
        try {
            result = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<EsProductRealTime> esProductList = new ArrayList<>();
        if(result!=null && null != result.getHits(Object.class)){
            esProductList = result.getSourceAsObjectList(EsProductRealTime.class,true);
        }
        return esProductList;
    }


    public List<EsDusterRealTime> queryDusterEs(String deviceNo,long customerId){
        String queryJson="{\n" +
                "\"query\":{\n" +
                "   \"bool\":{\n" +
                "       \"must\":[{\n" +
                "           \"match\":{\n" +
                "               \"customerId\":"+customerId+"\n"+
                "             }\n"+
                "         },\n" +
                "         {\n" +
                "           \"match\":{\n" +
                "                \"deviceNo.keyword\":\""+deviceNo +"\"\n" +
                "              }\n" +
                "          }]\n " +
                "       }\n" +
                "   }\n" +
                "}";
        Search search =  new Search.Builder(queryJson).addIndex("duster").addType("_doc").build();
        SearchResult result = null;
        try {
            result = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<EsDusterRealTime> dusterList = new ArrayList<>();
        if(result!=null && null != result.getHits(Object.class)){
            List<SearchResult.Hit<EsMonitorRealTime, Void>> hits = result.getHits(EsMonitorRealTime.class);
            dusterList = result.getSourceAsObjectList(EsDusterRealTime.class,true);
        }
        return dusterList;
    }


    public List<EsMonitorRealTime> queryMonitorEs(String deviceNo, long customerId) {
        String queryJson = "{\n" +
                "  \"query\":{\n" +
                "    \"bool\":{\n" +
                "      \"must\":[{\n" +
                "        \"match\":{\n" +
                "      \"customerId\": "+customerId+" \n" +
                "        }\n" +
                "    },\n" +
                "    {\n" +
                "    \"match\":{\n" +
                "       \"deviceNo.keyword\": \""+ deviceNo +"\"\n" +
                "            }  \n" +
                "    \n" +
                "    }\n" +
                "  ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Search search = new Search.Builder(queryJson).addIndex("monitor").addType("_doc").build();
        SearchResult result = null;
        try {
            result = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<EsMonitorRealTime> recordList = new ArrayList<>();
        if (result!=null && null != result.getHits(Object.class)) {
            List<SearchResult.Hit<EsMonitorRealTime, Void>> hits = result.getHits(EsMonitorRealTime.class);
            recordList = result.getSourceAsObjectList(EsMonitorRealTime.class, true);
        }
        //获取结果集合
        return recordList;
    }



    public List<EsSignalRealTime> querySignalEs(String signal,long customerId){
        String queryJson="{\n" +
                "  \"query\":{\n" +
                "    \"bool\":{\n" +
                "      \"must\":[{\n" +
                "        \"match\":{\n" +
                "           \"customerId\":42\n" +
                "           }\n" +
                "       },\n" +
                "       {\n" +
                "       \"match\":{\n" +
                "           \"signalNo.keyword\": \"BME_042_001_F_004_258\"\n" +
                "            }\n" +
                "       }\n" +
                "       ]\n" +
                "    }\n" +
                "  }\n" +
                "}";
        Search search = new Search.Builder(queryJson).addIndex("signal").addType("_doc").build();
        SearchResult result = null;
        try {
            result = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<EsSignalRealTime> list = new ArrayList<>();

        if(result!=null && null!=result.getHits(Object.class)){
            list = result.getSourceAsObjectList(EsSignalRealTime.class,true);
        }
        return list;
    }

}
