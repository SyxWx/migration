package com.bme.syx.cloud.third;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CountryScheduled {

    public static void main(String[] args) {

       // syncCountrySite();

        syncProvinceSite();
    }

    public static void syncCountrySite() {

        Map<String, List<GovernmentMonitor>> provinceStationDataMap = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + "3109706e739f4ca68df12cb73e8229e7");
        querys.put("city", "唐山市");
        List<GovernmentMonitor> governmentMonitorList = provinceStationDataMap.get("唐山市");
        governmentMonitorList = getStationRealtimeList("https://nairc.market.alicloudapi.com", "/api/v1/nation_air_by_city/station_realtime_list", "GET",
                headers, querys);
        if (!Objects.isNull(governmentMonitorList)){
            provinceStationDataMap.put("唐山市", governmentMonitorList);
        }
    }


    public static void syncProvinceSite() {

        Map<String, List<GovernmentMonitor>> provinceStationDataMap = new HashMap<>();
        Map<String, String> querys = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + "3109706e739f4ca68df12cb73e8229e7");
        querys.put("province", "河北省");
        List<GovernmentMonitor> governmentMonitorList = provinceStationDataMap.get("唐山市");
        governmentMonitorList = getStationRealtimeList("https://pair.market.alicloudapi.com", "/api/v1/pair/station_realtime_list", "GET",
                headers, querys);
        if (!Objects.isNull(governmentMonitorList)){
            provinceStationDataMap.put("河北省", governmentMonitorList);
        }
    }

















    private  static List<GovernmentMonitor> getStationRealtimeList(String host, String path, String method, Map<String, String> headers, Map<String, String> querys) {
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            if (response.getStatusLine().getStatusCode() == 200) {
                String str = EntityUtils.toString(response.getEntity());
                AliyunSiteResponse aliyunSiteResponse = JacksonJsonUtil.json2Obj(str, AliyunSiteResponse.class);
                if (Objects.isNull(aliyunSiteResponse) || Objects.isNull(aliyunSiteResponse.getData())) {
                    throw new Exception("查询阿里云省控点返回数据为空");
                }
                return aliyunSiteResponse.getData();
            } else {
                //if (retryTimes.incrementAndGet() < 3) {
                 //   getStationRealtimeList(host, path, method, headers, querys);
                // }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}