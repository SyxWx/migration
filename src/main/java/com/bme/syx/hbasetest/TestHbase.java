package com.bme.syx.hbasetest;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import org.apache.commons.collections4.MapUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class TestHbase {


    @Resource(name = "hbaseTable")
    private Table historyDataNew;


    public List<Map<String, Object>> getLastRowDataFromHbase(){
        List<Map<String, Object>> list = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now().plusMinutes(10);
        Long startTime = now.minusMinutes(4500).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long endTime = now.plusMinutes(10).toInstant(ZoneOffset.of("+8")).toEpochMilli();

        String signalNo = "PYGT-SC-4GL-044-2_1";
        String rowKeyStart = signalNo + startTime;
        String rowKeyStop = signalNo + endTime;
        Map<String, Object> data = this.scanLastRow(rowKeyStart, rowKeyStop);
        Map<String, Object> map = new HashMap<>();
        map.put("signalNo", signalNo);
        if (MapUtils.isEmpty(data)) {
            map.put("event_time", "");
            map.put("val", "");
        } else {
            map.put("event_time", DateUtil.format(new Date(Long.parseLong(data.get("event_time").toString())),
                    DatePattern.NORM_DATETIME_PATTERN));
            map.put("val", Double.parseDouble(data.get("val").toString()));
        }
        list.add(map);
        return list;
    }

    public List<Map<String, Object>> getRowDataFromHbase(){
        List<Map<String, Object>> list = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now().plusMinutes(10);
        Long startTime = now.minusMinutes(1).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long endTime = now.plusMinutes(10).toInstant(ZoneOffset.of("+8")).toEpochMilli();

        String signalNo = "BMEVDM_ELC_220002_C3_3";
        String rowKeyStart = signalNo + startTime;
        String rowKeyStop = signalNo + endTime;
        List<Map<String, Object>> dataList = this.scanRowKey(rowKeyStart, rowKeyStop);

        for (Map<String,Object> data:dataList) {
            Map<String, Object> map = new HashMap<>();
            map.put("signalNo", signalNo);
            if (MapUtils.isEmpty(data)) {
                map.put("event_time", "");
                map.put("val", "");
            } else {
                map.put("event_time", DateUtil.format(new Date(Long.parseLong(data.get("event_time").toString())),
                        DatePattern.NORM_DATETIME_PATTERN));
                map.put("val", Double.parseDouble(data.get("val").toString()));
            }
            list.add(map);
        }
        return list;
    }


    public List<Map<String, Object>> getRowDataFromHbaseBySignal(String signalNo){
        List<Map<String, Object>> list = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now().plusMinutes(10);
        Long startTime = now.minusMinutes(45).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long endTime = now.plusMinutes(10).toInstant(ZoneOffset.of("+8")).toEpochMilli();

        String rowKeyStart = signalNo + startTime;
        String rowKeyStop = signalNo + endTime;
        List<Map<String, Object>> dataList = this.scanRowKey(rowKeyStart, rowKeyStop);

        for (Map<String,Object> data:dataList) {
            Map<String, Object> map = new HashMap<>();
            map.put("signalNo", signalNo);
            if (MapUtils.isEmpty(data)) {
                map.put("event_time", "");
                map.put("val", "");
            } else {
                map.put("event_time", DateUtil.format(new Date(Long.parseLong(data.get("event_time").toString())),
                        DatePattern.NORM_DATETIME_PATTERN));
                map.put("val", Double.parseDouble(data.get("val").toString()));
            }
            list.add(map);
        }
        return list;
    }



    public List<Map<String, Object>> scanRowKey(String rowKeyStart, String rowKeyStop) {
        List<Map<String, Object>> rtn = new ArrayList<>();
        try {
            Scan scan = new Scan();
            scan.withStartRow(rowKeyStart.getBytes());
            scan.withStopRow(rowKeyStop.getBytes(), true);
            ResultScanner resultScanner = historyDataNew.getScanner(scan);
            for (Result result : resultScanner) {
                Map<String, Object> map = new HashMap<>();
                for (Cell cell : result.rawCells()) {
                    String collumName = Bytes.toString(CellUtil.cloneQualifier(cell));
                    if ("event_time".equals(collumName) || "val".equals(collumName)) {
                        map.put(collumName, Bytes.toString(CellUtil.cloneValue(cell)));
                    }
                }
                rtn.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }


    public List<Map<String,Object>> getRowKey(String rowKeyStart,String rowKeyStop){
        List<Map<String,Object>> rtn = new ArrayList<>();
        try {
            Scan scan = new Scan();
            scan.withStartRow(rowKeyStart.getBytes());
            scan.withStopRow(rowKeyStop.getBytes(),true);
            ResultScanner resultScanner = historyDataNew.getScanner(scan);
            for (Result result: resultScanner) {
                Map<String,Object> map = new HashMap<>();
                for(Cell cell:result.rawCells()){
                    String collumName = Bytes.toString(CellUtil.cloneQualifier(cell));
                    if("event_time".equals(collumName) || "val".equals(collumName)){
                        map.put(collumName,Bytes.toString(CellUtil.cloneValue(cell)));
                    }
                }
                rtn.add(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public  Map<String, Object> scanLastRow(String rowKeyStart, String rowKeyStop) {
        Map<String, Object> map = new HashMap<>();
        try {
            Scan scan = new Scan();
            // 倒查 返回第一条
            scan.withStartRow(rowKeyStop.getBytes());
            scan.withStopRow(rowKeyStart.getBytes(), true);
            scan.setReversed(true);
            scan.setOneRowLimit();
            ResultScanner resultScanner = historyDataNew.getScanner(scan);
            Result result = resultScanner.next();
            if (null == result) {
                return map;
            }
            for (Cell cell : result.rawCells()) {
                String collumName = Bytes.toString(CellUtil.cloneQualifier(cell));
                if ("event_time".equals(collumName) || "val".equals(collumName)) {
                    map.put(collumName, Bytes.toString(CellUtil.cloneValue(cell)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now().plusMinutes(10);
        Long startTime = now.minusMinutes(45).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        Long endTime = now.plusMinutes(10).toInstant(ZoneOffset.of("+8")).toEpochMilli();

        System.out.println(now +"-----"+startTime+"-----"+endTime);
    }

}
