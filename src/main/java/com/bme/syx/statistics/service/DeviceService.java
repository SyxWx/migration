package com.bme.syx.statistics.service;

import cn.hutool.core.util.NumberUtil;
import com.bme.syx.es.EsMonitorRealTime;
import com.bme.syx.statistics.dao.CountMapper;
import com.bme.syx.statistics.dao.DeviceMapper;
import com.bme.syx.statistics.entity.Customer;
import com.bme.syx.statistics.entity.Device;
import com.bme.syx.statistics.entity.EsGovernmentRealTime;
import com.bme.syx.statistics.entity.EsProductRealTime;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ArrayStack;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class DeviceService {


    @Autowired
    private CountMapper countMapper;


    @Autowired
    private DeviceMapper deviceMapper;


    @Autowired
    private JestClient jestClient;

    @Test
    public void queryCustomerdDeviceOnline11() {
        List<Map<String, String>> list = new ArrayList<>();
        List<Customer> customerList = countMapper.selectAllCustomer();
        for (Customer customer : customerList) {
            long customerId = customer.getCustomerId();
            Map<String, String> map = getDeviceOnlineByCustomerID(customerId);
            if (map != null) {
                list.add(map);
            }
        }
    }

    public List<Map<String, String>> queryCustomerdDeviceOnline() {
        List<Map<String, String>> list = new ArrayList<>();
        List<Customer> customerList = countMapper.selectAllCustomer();
        for (Customer customer : customerList) {
            long customerId = customer.getCustomerId();
            if(1==customerId || 2==customerId){
                Map<String, String> map = getDeviceOnlineByCustomerID(customerId);
                if (map != null) {
                    map.put("customerName",customer.getCustomerName());
                    list.add(map);
                }
            }
        }
        return list;
    }

    public Map<String, String> getDeviceOnlineByCustomerID(long customerId) {

        Map<String, String> map = new HashMap<>();
        // 统计设备在线数与信号数
        long totalDevice = 0L;
        long totalOnlineDevice = 0L;
        List<String> deviceNoList = new ArrayList<>();
        List<Long> longList = Arrays.asList(1L, 2L, 3L);
        for (Long primaryId : longList) {
            List<Device> deviceList = deviceMapper.getDeviceListAll(customerId, primaryId);
            for (Device device : deviceList) {
                deviceNoList.add(device.getDeviceNo());
            }
            if (deviceList != null && deviceList.size() > 0) {
                totalDevice = totalDevice + deviceList.size();
            }
            // 查询设备在线数
            Long deviceOnlineCount = 0L;
            deviceOnlineCount = getDeviceOnlineCount(deviceNoList, primaryId,customerId+"");
            totalOnlineDevice = totalOnlineDevice + deviceOnlineCount;
        }
        map.put("customerId", customerId + "");
        map.put("totalDevice", totalDevice + "");
        map.put("totalOnlineDevice", totalOnlineDevice + "");
        map.put("totalValue",  totalDevice == 0L || 0L == totalOnlineDevice ? "0%" : NumberUtil.round(NumberUtil.div(totalOnlineDevice, totalDevice) * 100, 2) + "%");
        return map;
    }


    public long getDeviceOnlineCount(List<String> deviceNoList, Long primaryId,String customerId) {
        long count = 0;
        String deviceNoStr = deviceNoList.stream().map(s -> "\"" + s + "\"").collect(Collectors.joining(","));
        if (1 == primaryId) {
            count = productDeviceOnLineCount(deviceNoStr,customerId);
        } else if (2 == primaryId) {
            count = dusterDeviceOnLineCount(deviceNoStr,customerId);
        } else if (3 == primaryId) {
            count = monitorDeviceOnLineCount(deviceNoStr,customerId);
        } else {
            count = 0;
        }
        return count;
    }


    /**
     *      * 获取生产设备实时状态   runStatus: 0停止   1启动  2无数据
     */
    public long productDeviceOnLineCount(String deviceNoStr,String customerId) {
        AtomicLong atomicLong = new AtomicLong(0);
        try {
            String queryJson = getProductDeviceRealTimeTemplate(deviceNoStr, customerId);
            log.info("-----> product json :{}", queryJson);
            Search search = new Search.Builder(queryJson).addIndex("product").addType("_doc").build();
            SearchResult result = jestClient.execute(search);
            log.info("-----> product result :{}", result.getJsonString());
            List<EsProductRealTime> recordList = new ArrayList<>();
            recordList = result.getSourceAsObjectList(EsProductRealTime.class, true);
            for (EsProductRealTime realTime : recordList) {
                if (realTime != null && (realTime.getRunStatus() == 1 || realTime.getRunStatus() == 0)) {
                    atomicLong.incrementAndGet();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return atomicLong.get();
    }

    /**
     *      *      * 获取治理设备实时状态   runStatus: 0停止   1启动  2无数据
     * @param deviceNo
     * @param customerId
     * @return
     */
    public long dusterDeviceOnLineCount(String deviceNo,String customerId) {
        AtomicLong atomicLong = new AtomicLong(0);
        try {
            String queryJson = getGovernmentDeviceRealTimeTemplate(deviceNo,customerId);
            log.info("-----> duster json :{}", queryJson);
            Search search = new Search.Builder(queryJson).addIndex("duster").addType("_doc").build();
            SearchResult result = jestClient.execute(search);
            log.info("-----> duster result :{}", result.getJsonString());
            List<EsGovernmentRealTime> recordList = new ArrayList<>();
            recordList = result.getSourceAsObjectList(EsGovernmentRealTime.class, true);
            for (EsGovernmentRealTime realTime : recordList) {
                if (realTime != null &&  (realTime.getRunStatus() == 1 || realTime.getRunStatus() == 0)) {
                    atomicLong.incrementAndGet();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return atomicLong.get();
    }

    /**
     *      * 获取监测设备实时值 runStatus: 0离线   1在线
     * @param deviceNo
     * @param customerId
     * @return
     */
    public long monitorDeviceOnLineCount(String deviceNo,String customerId) {
        AtomicLong atomicLong = new AtomicLong(0);
        try {
            String queryJson = getMonitorRealTimeTemplate(deviceNo,customerId);
            log.info("-----> monitor json :{}", queryJson);
            Search search = new Search.Builder(queryJson).addIndex("monitor").addType("_doc").build();
            SearchResult result = jestClient.execute(search);
            log.info("-----> monitor result :{}", result.getJsonString());
            List<EsMonitorRealTime> recordList = new ArrayList<>();
            recordList = result.getSourceAsObjectList(EsMonitorRealTime.class, true);
            for (EsMonitorRealTime realTime : recordList) {
                if (realTime != null && realTime.getRunStatus() == 1) {
                    atomicLong.incrementAndGet();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return atomicLong.get();
    }

    /**
     * 获取监测设备实时值 runStatus: 0离线   1在线
     */
    public static String getMonitorRealTimeTemplate(String deviceNo,String customerId) {

        return "{\n" +
                "   \"_source\": {\n" +
                "        \"includes\": [ \"id\", \"deviceNo\", \"deviceName\", \"productionLine\", \"latitude\",\"longitude\",\"realTimeUpdateTime\",\"vdmRealtime\", \"pm2_5RealTime\",\"pm10RealTime\", \"tspRealTime\", \"coRealTime\", \"cemsRealTime\", \"so2RealTime\", \"noxRealTime\", \"vdmRealtime\", \"runStatus\", \"lastTypeName\", \"typeId\"]\n" +
                "    }, " +
                "   \"query\": {\n" +
                "        \"bool\" : {  \n" +
                "            \"filter\": \n [" +
                "                { \n" +
                "                    \"terms\": {\n" +
                "                        \"deviceNo.keyword\": [" +
                deviceNo +
                "]\n" +
                "                    }\n" +
                "                },\n { \"terms\":{\"customerId\":[\"" +
                ""+customerId+"" +
                "\"]}}" +
                "           ] " +
                "        }\n" +
                "    },\n" +
                "    \"from\":" + 0 + ",\n" +
                "    \"size\":" + 10000 + "\n" +
                "}\n";

    }



    /**
     * 获取治理设备实时状态   runStatus: 0停止   1启动  2无数据
     * 获取监测设备实时值 runStatus: 0离线   1在线
     */
    public static String getGovernmentDeviceRealTimeTemplate(String deviceNo,String customerId){
        return "{\n" +
                "  \"_source\": {\n" +
                "    \"includes\": [ \"id\", \"branchFactoryId\", \"branchFactory\",\"deviceNo\", \"runStatus\", \"lastTypeName\", \"deviceName\"]\n" +
                "  },    \n" +
                "  \"query\": {\n" +
                "  \"bool\" : {\n" +
                "    \"filter\":[\n" +
                "    {\n" +
                "      \"terms\": {\n" +
                "        \"deviceNo.keyword\": [" +
                deviceNo +
                "]\n" +
                "      }\n" +
                "    },{ \n" +
                "                    \"terms\":{\n" +
                "                        \"customerId\":[\""+customerId+"\"]\n" +
                "                    }\n" +
                "                }]\n" +
                "  }\n" +
                "},\n" +
                "  \"from\":0,\n" +
                "  \"size\":1000\n" +
                "}\n";
    }

    /**
     * 获取生产设备实时状态   runStatus: 0停止   1启动  2无数据
     * 获取治理设备实时状态   runStatus: 0停止   1启动  2无数据
     * 获取监测设备实时值 runStatus: 0离线   1在线
     */
    public static String getProductDeviceRealTimeTemplate(String deviceNo,String customerId){
        return "{\n" +
                "  \"_source\": {\n" +
                "    \"includes\": [ \"id\", \"branchFactoryId\", \"branchFactory\",\"deviceNo\", \"runStatus\", \"lastTypeName\", \"deviceName\"]\n" +
                "  },    \n" +
                "  \"query\": {\n" +
                "  \"bool\" : {\n" +
                "    \"filter\":[\n" +
                "    {\n" +
                "      \"terms\": {\n" +
                "        \"deviceNo.keyword\": [" +
                deviceNo +
                "]\n" +
                "      }\n" +
                "    },\n" +
                "                { \n" +
                "                    \"terms\":{\n" +
                "                        \"customerId\":[\""+customerId+"\"]\n" +
                "                    }\n" +
                "                }\n" +
                "]  }\n" +
                "},\n" +
                "  \"from\":0,\n" +
                "  \"size\":1000\n" +
                "}\n";
    }


}