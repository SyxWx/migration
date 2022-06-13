package com.bme.syx.cloud.third;

import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * description:
 * <p></p>
 *
 * @author huanghao
 * @since 2021/3/18 13:27
 * Copyright: 2021, BME (Shanghai) Co., Ltd. All Rights Reserved.
 */
@Data
public class CityMonitorByHours {

    private String province;

    private String city;

    private Integer cityCode;

    private Double pm25;

    private Double pm10;

    private Double aqi;

    private Double co;

    private Double no2;

    private Double o3;

    private Double so2;

    private String pollutions;

    private String quality;

    private Integer level;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private String pubtime;

    private LocalDateTime createTime;

    public CityMonitorByHours(){}

    public CityMonitorByHours(Map<String, Object> cityMonitorByHoursMap){
        if (!CollectionUtils.isEmpty(cityMonitorByHoursMap)){
            this.province = String.valueOf(cityMonitorByHoursMap.get("province"));
            this.city = String.valueOf(cityMonitorByHoursMap.get("city"));
            this.cityCode = Integer.parseInt(String.valueOf(cityMonitorByHoursMap.get("city_code")));
            this.pm25 = new BigDecimal(String.valueOf(cityMonitorByHoursMap.get("pm2_5"))).doubleValue();
            this.pm10 = new BigDecimal(String.valueOf(cityMonitorByHoursMap.get("pm10"))).doubleValue();
            this.aqi = new BigDecimal(String.valueOf(cityMonitorByHoursMap.get("aqi"))).doubleValue();
            this.co = new BigDecimal(String.valueOf(cityMonitorByHoursMap.get("co"))).doubleValue();
            this.no2 = new BigDecimal(String.valueOf(cityMonitorByHoursMap.get("no2"))).doubleValue();
            this.o3 = new BigDecimal(String.valueOf(cityMonitorByHoursMap.get("o3"))).doubleValue();
            this.so2 = new BigDecimal(String.valueOf(cityMonitorByHoursMap.get("so2"))).doubleValue();
            this.pollutions = String.valueOf(cityMonitorByHoursMap.get("pollutions"));
            this.quality = String.valueOf(cityMonitorByHoursMap.get("quality"));
            this.level = Integer.parseInt(String.valueOf(cityMonitorByHoursMap.get("level")));
            this.latitude = new BigDecimal(String.valueOf(cityMonitorByHoursMap.get("latitude")));
            this.longitude = new BigDecimal(String.valueOf(cityMonitorByHoursMap.get("longitude")));
            this.pubtime = String.valueOf(cityMonitorByHoursMap.get("pubtime"));
            this.createTime = LocalDateTime.now();
        }
    }


    public CityMonitorByHours convertTo(Map<String, Object> map, String province, String city) {
        CityMonitorByHours res = new CityMonitorByHours();
        res.setProvince(province);
        res.setCity(city);
        if (CollectionUtils.isEmpty(map)){
            return res;
        }
        if (map.get("num") != null){
            int cityCode = 0;
            try {
                cityCode = Integer.parseInt(String.valueOf(map.get("num")));
            } catch (NumberFormatException e) {
                cityCode = 999;
            }
            res.setCityCode(cityCode);
        }
        if (map.get("pm2_5") != null && !"-".equals(map.get("pm2_5"))) {
            res.setPm25(new BigDecimal(String.valueOf(map.get("pm2_5"))).doubleValue());
        }
        if (map.get("pm10") != null && !"-".equals(map.get("pm10"))) {
            res.setPm10(new BigDecimal(String.valueOf(map.get("pm10"))).doubleValue());
        }
        if (map.get("aqi") != null && !"-".equals(map.get("aqi"))) {
            res.setAqi(new BigDecimal(String.valueOf(map.get("aqi"))).doubleValue());
        }
        if (map.get("co") != null && !"-".equals(map.get("co"))) {
            res.setCo(new BigDecimal(String.valueOf(map.get("co"))).doubleValue());
        }
        if (map.get("no2") != null && !"-".equals(map.get("no2"))) {
            res.setNo2(new BigDecimal(String.valueOf(map.get("no2"))).doubleValue());
        }
        if (map.get("o3") != null && !"-".equals(map.get("o3"))) {
            res.setO3(new BigDecimal(String.valueOf(map.get("o3"))).doubleValue());
        }
        if (map.get("so2") != null && !"-".equals(map.get("so2"))) {
            res.setSo2(new BigDecimal(String.valueOf(map.get("so2"))).doubleValue());
        }
        if (map.get("primary_pollutant") != null && !"-".equals(map.get("primary_pollutant"))) {
            res.setPollutions(map.get("primary_pollutant").toString());
        }

        if (map.get("quality") != null && !"-".equals(map.get("quality"))) {
            res.setQuality(map.get("quality").toString());
        }

        if (map.get("quality") != null && !"-".equals(map.get("quality"))) {
            res.setQuality(map.get("quality").toString());
        }

        if (map.get("ct") != null && !"-".equals(map.get("ct"))) {
            //"ct": "2021-04-14 16:10:10.926" format to  ""2021-04-14 16:10:10"
            String ct = map.get("ct").toString();
            res.setPubtime(ct.split("\\.")[0]);
        }
        res.setCreateTime(LocalDateTime.now());
        return res;
    }

}
