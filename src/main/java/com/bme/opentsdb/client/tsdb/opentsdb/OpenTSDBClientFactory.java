package com.bme.opentsdb.client.tsdb.opentsdb;

import lombok.Data;
import org.apache.http.nio.reactor.IOReactorException;

import java.util.Objects;

@Data
public class OpenTSDBClientFactory {
    public static OpenTSDBClient build(OpenTSDBConfig config) throws IOReactorException {
        Objects.requireNonNull(config, "config");
        return new OpenTSDBClient(config);
    }


}
