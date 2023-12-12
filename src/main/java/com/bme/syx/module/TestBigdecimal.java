package com.bme.syx.module;

import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestBigdecimal {

    public static void main(String[] args) {

        Double realValue = 0.12903;

        Double reealvalueNUll = null;

        String unit = "mg";

        String realValuestr = ObjectUtils.isEmpty(realValue) ? "0.00" : BigDecimal.valueOf(realValue).setScale(3, RoundingMode.CEILING).toString()+unit;


        String reealvalueNUllstr = ObjectUtils.isEmpty(reealvalueNUll) ? "0.00" : BigDecimal.valueOf(reealvalueNUll).setScale(3, RoundingMode.CEILING).toString()+unit;

        System.out.println(realValuestr);

        System.out.println(reealvalueNUllstr);

    }
}
