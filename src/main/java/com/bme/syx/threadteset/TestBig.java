package com.bme.syx.threadteset;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestBig {

    public static void main(String[] args) {


        //double realValue = 1.2345667;
        double realValue = 3.14153;

        //String value = String.valueOf(new BigDecimal(realValue).setScale(2, RoundingMode.UP).doubleValue());
        //System.out.println( value+ "ug");

        System.out.println( String.valueOf(new BigDecimal(realValue).setScale(2, RoundingMode.UP).doubleValue())+ "ug");

    }
}
