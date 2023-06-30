package com.bme.syx.es.service;


import com.bme.syx.es.entity.CarwashInfoData;
import io.searchbox.client.JestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class EsQueryCarService {


    @Resource(name = "basicJestClient")
    private JestClient basicJestClient;

    @Autowired
    private EsService esService;

    private String WASH_INDEX = "carwash";

    /**
     * 获取洗车机设备名称
     */
    public String getCarWashName(Long customerId, String deviceNo) {
        String deviceName = null;
        Map<String, Object> params = new HashMap<>();
        params.put("customerId", customerId);
        params.put("deviceNo.keyword", deviceNo);
        CarwashInfoData carwashInfo = esService.get(basicJestClient, params, WASH_INDEX, CarwashInfoData.class);
        if (Objects.nonNull(carwashInfo)) {
            deviceName = carwashInfo.getCarName();
        }
        return deviceName;
    }


}
