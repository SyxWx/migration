package com.bme.syx;

import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TestDateUtils {




    public static Long timeToLong(String time) {
        Assert.notNull(time, "time is null");
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime parse = LocalDateTime.parse(time, ftf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static void main(String[] args) {
        System.out.println(timeToLong("2023-12-01 14:00"));

        Double initValue = 0.0;
        Double pvalue = 0.0;
        Double pvalue1 = 0.1;
        Double pvalue2 = 1.0;



        if(initValue.compareTo(pvalue1) < 0){
            System.out.println("pvalue < pvalue1 ");
        }




    }





}
