package com.bme.syx.es;


import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JestTestEsQuery {


    @Autowired
    private JestClient jestClient;






    @Test
    public void queryDuster() throws  Exception{

        String deviceNo = "\"RZ_Y4_Y41_04\",\"RZ_Y4_Y41_01\",\"RZ_Y3_LG_12\",\"RZ_WF_30_81\"";
        String queryJson = getMonitorRealTimeTemplate(deviceNo);
        log.info("-----> monitor json :{}", queryJson);
        Search search = new Search.Builder(queryJson).addIndex("product").addType("_doc").build();
        SearchResult result = jestClient.execute(search);
        log.info("-----> monitor result :{}", result.getJsonString());

        List<EsMonitorRealTime> recordList = new ArrayList<>();
        recordList = result.getSourceAsObjectList(EsMonitorRealTime.class,true);
        System.out.println("11111"+recordList.size());

    }


    /**
     * 获取检测设备实时值
     */
    public static String getMonitorRealTimeTemplate(String deviceNo) {

        return "{\n" +
                "   \"_source\": {\n" +
                "        \"includes\": [ \"id\", \"deviceNo\", \"deviceName\", \"productionLine\", \"latitude\",\"longitude\",\"realTimeUpdateTime\",\"vdmRealtime\", \"pm2_5RealTime\",\"pm10RealTime\", \"tspRealTime\", \"coRealTime\", \"cemsRealTime\", \"so2RealTime\", \"noxRealTime\", \"vdmRealtime\", \"runStatus\", \"lastTypeName\", \"typeId\"]\n" +
                "    }, " +
                "   \"query\": {\n" +
                "        \"bool\" : {  \n" +
                "            \"filter\": \n" +
                "                { \n" +
                "                    \"terms\": {\n" +
                "                        \"deviceNo.keyword\": [" +
                deviceNo +
                "]\n" +
                "                    }\n" +
                "                }\n" +
                "        }\n" +
                "    },\n" +
                "    \"from\":" + 0 + ",\n" +
                "    \"size\":" + 10000 + "\n" +
                "}\n";
    }
}
