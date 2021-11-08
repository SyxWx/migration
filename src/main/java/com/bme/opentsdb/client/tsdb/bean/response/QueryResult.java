package com.bme.opentsdb.client.tsdb.bean.response;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Data
public class QueryResult {

    private String metric;

    private Map<String, String> tags;

    private List<String> aggregateTags;

    private LinkedHashMap<Long, Number> dps;

}
