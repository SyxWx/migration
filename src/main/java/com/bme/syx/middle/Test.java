package com.bme.syx.middle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class Test
{



    public static void main(String[] args) {

        String day = "2023-01-13";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate date  = LocalDate.parse(day,fmt);
        LocalDate yesedate = date.plusDays(-1);

        System.out.println("前一天"+yesedate);
        //获取当月第一天
        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("当月第一天"+firstDay);

        //获取当月最后一天
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("当月最后一天"+lastDay);

        //获取上月第一天
        LocalDate yesterMonfirstDay = date.minusMonths(1L).with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("上月第一天"+yesterMonfirstDay);

        //获取上月最后一天
        LocalDate yesterMonLastDay = date.minusMonths(1L).with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("上月最后一天"+yesterMonLastDay);

    }
}
