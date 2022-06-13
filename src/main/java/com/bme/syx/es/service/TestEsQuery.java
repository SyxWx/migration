package com.bme.syx.es.service;

import com.bme.syx.es.entity.EsMonitorRealTime;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class TestEsQuery {

    @Autowired
    private JestClient jestClient;


    public List<EsMonitorRealTime> queryEs(String deviceNo, long customerId) {

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
}
