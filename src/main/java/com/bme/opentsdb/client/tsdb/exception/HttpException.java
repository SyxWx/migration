package com.bme.opentsdb.client.tsdb.exception;

import com.bme.opentsdb.client.tsdb.bean.response.ErrorResponse;
import lombok.Data;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

@Data
public class HttpException {
    private ErrorResponse errorResponse;

    public HttpException(ErrorResponse errorResponse) {
        super(errorResponse.toString());
        this.errorResponse = errorResponse;
    }

}
