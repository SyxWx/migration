package com.bme.syx.module.productdata.service;


import com.bme.syx.module.productdata.ProductHistoryRunnable;
import com.bme.syx.module.productdata.dao.tdb.ProductHistoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
public class ProductHistoryService {


    @Autowired
    private ProductHistoryMapper productHistoryMapper;

    public List<Map<String, Object>> getProductData(Long startTime, Long endTime,int customerId) {
        Map<String,Object> map = new HashMap<>();

        ProductHistoryRunnable productHistoryRunnable = new ProductHistoryRunnable();
        productHistoryRunnable.setStartTime(startTime);
        productHistoryRunnable.setEndTime(endTime);
        productHistoryRunnable.setCustomerId(customerId);

        Thread thread = new Thread(productHistoryRunnable, "productHistoryRunnable" + customerId);
        thread.start();
        log.info("==========>start Supplementary data thread: {}", customerId);

        //List<Map<String,Object>> list = productHistoryMapper.findProductList(map);

        return null;
    }

    
    public List<Map<String, Object>> findProductData(Long startTime, Long endTime,int customerId) {
        Map<String, Object> map = new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<Map<String,Object>> list = productHistoryMapper.findProductList(map);

        return null;
    }
}
