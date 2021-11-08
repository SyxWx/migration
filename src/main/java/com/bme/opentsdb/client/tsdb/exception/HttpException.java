package com.bme.opentsdb.client.tsdb.exception;

import lombok.Data;

@Data
public class HttpException {

    private String  errorResponse;

    public HttpException(String errorResponse) {
        //super(errorResponse);
        this.errorResponse = errorResponse;
    }

}
