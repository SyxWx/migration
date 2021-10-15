package com.bme.syx.module.productdata.web;


import com.bme.syx.module.productdata.service.ProductHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@RequestMapping(value = "product_history")
public class ProductHistoryController {

    @Autowired
    //private ProductHistoryService productHistoryService;

    // http://localhost:8080/migration/product_history/go?startTime=2021-02-19%2019:00:00&endTime=2021-02-21%2023:20:00
    // http://localhost:8080/migration/product_history/go?startTime=2021-02-19%2019:00:00&endTime=2021-02-19%2019:00:10
    @GetMapping("go")
    public String getProductData(String startTime, String endTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date startDate = format.parse(startTime);
            Date endDate = format.parse(endTime);
            int customerId = 6;
            //productHistoryService.getProductData(startDate.getTime(), endDate.getTime(),customerId);
        } catch (ParseException e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }


}
