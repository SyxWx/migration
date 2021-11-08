package com.bme.opentsdb.client.tsdb.bean.response;

import com.bme.opentsdb.client.tsdb.bean.request.Point;
import lombok.Data;

import java.util.List;

@Data
public class DetailResult {

    private List<ErrorPoint> errors;

    private int failed;

    private int success;

    /**
     * put错误的Point
     */
    @Data
    public static class ErrorPoint{

        private Point datapoint;

        private String error;

    }
}
