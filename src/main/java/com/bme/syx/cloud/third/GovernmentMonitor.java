package com.bme.syx.cloud.third;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GovernmentMonitor {
    /**
     * 监测ID
     */
    private Long id;

    /**
     * 站点ID
     */
    private Integer siteId;

    /**
     * 站点名称
     */
    private String siteName;

    /**
     * 站点类型：1 国控  2 省控
     */
    private Integer siteType;

    /**
     * PM2_5
     */
    @JsonProperty("pm2_5")
    private Integer pm25;

    /**
     * PM10
     */
    private Integer pm10;

    /**
     * 空气质量指数
     */
    private Integer aqi;

    /**
     * 一氧化碳
     */
    private Double co;

    /**
     * 二氧化氮
     */
    private Integer no2;

    /**
     * 臭氧
     */
    private Integer o3;

    /**
     * 二氧化硫
     */
    private Integer so2;

    /**
     * 二氧化硫24小时平均浓度
     */
    @JsonProperty("so2_24h")
    private Integer so224h;

    /**
     * 二氧化氮24小时平均浓度
     */
    @JsonProperty("no2_24h")
    private Integer no224h;

    /**
     * PM10 24小时平均浓度
     */
    @JsonProperty("pm10_24h")
    private Integer pm1024h;

    /**
     * 一氧化碳24小时平均浓度
     */
    @JsonProperty("co_24h")
    private Double co24h;

    /**
     * 臭氧日最大1小时平均浓度
     */
    @JsonProperty("o3_24h")
    private Integer o324h;

    /**
     * 臭氧8小时平均浓度
     */
    @JsonProperty("o3_8h")
    private Integer o38h;

    /**
     * 臭氧日最大8小时平均浓度
     */
    @JsonProperty("o3_8h_24h")
    private Integer o38h24h;

    /**
     * PM2_5 24小时平均浓度
     */
    @JsonProperty("pm2_5_24h")
    private Integer pm2524h;

    /**
     * 二氧化硫分指数IAQI
     */
    private Integer iso2;

    /**
     * 一氧化碳分指数IAQI
     */
    private Integer ico;

    /**
     * 臭氧分指数IAQI
     */
    private Integer io3;

    /**
     * 二氧化碳分指数IAQI
     */
    private Integer ino2;

    /**
     * PM2_5分指数IAQI
     */
    private Integer ipm25;

    /**
     * PM10分指数IAQI
     */
    private Integer ipm10;

    private String pollutions;

    private String province;

    private String city;

    private String district;

    @JsonProperty("station_code")
    private String stationCode;

    private String station;

    private Double latitude;

    private Double longitude;


    @JsonProperty("city_code")
    private String cityCode;

    private String level;

    /**
     * 发布时间
     */
    private String pubtime;

    private Date createTime;

    private Date updateTime;

    private Integer dataType;

    public void init(GovernmentMonitor returnGovernmentMonitor, GovernmentMonitor governmentMonitor) {
        returnGovernmentMonitor.setPm25(governmentMonitor.getPm25());
        returnGovernmentMonitor.setPm10(governmentMonitor.getPm10());
        returnGovernmentMonitor.setAqi(governmentMonitor.getAqi());
        returnGovernmentMonitor.setCo(governmentMonitor.getCo());
        returnGovernmentMonitor.setNo2(governmentMonitor.getNo2());
        returnGovernmentMonitor.setO3(governmentMonitor.getO3());
        returnGovernmentMonitor.setSo2(governmentMonitor.getSo2());
        returnGovernmentMonitor.setPm2524h(governmentMonitor.getPm2524h());
        returnGovernmentMonitor.setPm1024h(governmentMonitor.getPm1024h());
        returnGovernmentMonitor.setSo224h(governmentMonitor.getSo224h());
        returnGovernmentMonitor.setNo224h(governmentMonitor.getNo224h());
        returnGovernmentMonitor.setCo24h(governmentMonitor.getCo24h());
        returnGovernmentMonitor.setO324h(governmentMonitor.getO324h());
        returnGovernmentMonitor.setO38h(governmentMonitor.getO38h());
        returnGovernmentMonitor.setO38h24h(governmentMonitor.getO38h24h());
        returnGovernmentMonitor.setPubtime(governmentMonitor.getPubtime());
        returnGovernmentMonitor.setUpdateTime(new Date());
    }

    public void initNew(GovernmentMonitor tGovernmentMonitor, Map<String, Object> map) {
        if (map.get("pm2_5") != null && !"-".equals(map.get("pm2_5"))) {
            tGovernmentMonitor.setPm25(Integer.parseInt(map.get("pm2_5").toString()));
        }
        if (map.get("pm10") != null && !"-".equals(map.get("pm10"))) {
            tGovernmentMonitor.setPm10(Integer.parseInt(map.get("pm10").toString()));
        }
        if (map.get("aqi") != null && !"-".equals(map.get("aqi"))) {
            tGovernmentMonitor.setAqi(Integer.parseInt(map.get("aqi").toString()));
        }
        if (map.get("co") != null && !"-".equals(map.get("co"))) {
            tGovernmentMonitor.setCo(new BigDecimal(map.get("co").toString()).doubleValue());
        }
        if (map.get("no2") != null && !"-".equals(map.get("no2"))) {
            tGovernmentMonitor.setNo2(Integer.parseInt(map.get("no2").toString()));
        }
        if (map.get("o3") != null && !"-".equals(map.get("o3"))) {
            tGovernmentMonitor.setO3(Integer.parseInt(map.get("o3").toString()));
        }
        if (map.get("so2") != null && !"-".equals(map.get("so2"))) {
            tGovernmentMonitor.setSo2(Integer.parseInt(map.get("so2").toString()));
        }
        if (map.get("so2") != null && !"-".equals(map.get("so2"))) {
            tGovernmentMonitor.setSo2(Integer.parseInt(map.get("so2").toString()));
        }
        if (map.get("primary_pollutant") != null) {
            tGovernmentMonitor.setPollutions(map.get("primary_pollutant").toString());
        }
        if (map.get("ct") != null && (!"".equals(map.get("ct")))) {
            String ct = map.get("ct").toString().split("\\.")[0];
            tGovernmentMonitor.setPubtime(ct);
        }
    }

    public void initByCityHour(GovernmentMonitor tGovernmentMonitor, CityMonitorByHours cityMonitorByHours){
        if (cityMonitorByHours.getAqi() != null){
            tGovernmentMonitor.setAqi(cityMonitorByHours.getAqi().intValue());
        }
        if (cityMonitorByHours.getCo() != null){
            tGovernmentMonitor.setCo(cityMonitorByHours.getCo());
        }
        if (cityMonitorByHours.getPm25() != null){
            tGovernmentMonitor.setPm25(cityMonitorByHours.getPm25().intValue());
        }
        if (cityMonitorByHours.getPm10() != null){
            tGovernmentMonitor.setPm10(cityMonitorByHours.getPm10().intValue());
        }
        if (cityMonitorByHours.getSo2() != null){
            tGovernmentMonitor.setSo2(cityMonitorByHours.getSo2().intValue());
        }
        if (cityMonitorByHours.getNo2() != null){
            tGovernmentMonitor.setNo2(cityMonitorByHours.getNo2().intValue());
        }
        if (cityMonitorByHours.getO3() != null){
            tGovernmentMonitor.setO3(cityMonitorByHours.getO3().intValue());
        }
        if (!StringUtils.isEmpty(cityMonitorByHours.getPollutions())){
            tGovernmentMonitor.setPollutions(cityMonitorByHours.getPollutions());
        }
        if (!StringUtils.isEmpty(cityMonitorByHours.getPubtime())){
            tGovernmentMonitor.setPubtime(cityMonitorByHours.getPubtime());
        }
        tGovernmentMonitor.setUpdateTime(new Date());
    }
}