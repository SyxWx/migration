package com.bme.syx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {

        String date = "2023-08-15 18:18:18";
        System.out.println(stringToTimestamp(date));

    }
    public static long stringToTimestamp(String dateString) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(dateString);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
