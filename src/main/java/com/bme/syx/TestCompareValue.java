package com.bme.syx;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

public class TestCompareValue {


    public static void main(String[] args) {


        String  value  = "0.0";
        String compareValue = "0.0";
        if (Objects.equals(compareValue, value)) {
            System.out.println("0.0 等于 0.0");
        }else{
            System.out.println("0.0 不等于 0.0");
        }


        long now = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long start = now - 60 * 20 * 1000;

        System.out.println(now   + "-----" + start);


    }
}
