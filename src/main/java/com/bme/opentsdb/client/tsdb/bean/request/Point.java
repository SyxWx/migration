package com.bme.opentsdb.client.tsdb.bean.request;

import lombok.Data;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Data
public class Point {

    private String metric;

    private Map<String, String> tags = new HashMap<>();

    private Number value;

    private long timestamp;

    public static MetricBuilder metric(String metric) {
        return new MetricBuilder(metric);
    }

    /**
     * Builder模式
     */
    public static class MetricBuilder {

        private String metric;

        private Map<String, String> tags = new HashMap<>();

        private Number value;

        private long timestamp;

        public MetricBuilder(String metric) {
            if (StringUtils.isBlank(metric)) {
                throw new IllegalArgumentException("The metric can't be empty");
            }
            this.metric = metric;
        }

        public MetricBuilder value(long timestamp, Number value) {
            if (timestamp == 0) {
                throw new IllegalArgumentException("timestamp must gt 0");
            }
            Objects.requireNonNull(value);
            this.timestamp = timestamp;
            this.value = value;
            return this;
        }

        public MetricBuilder tag(final String tagName, final String value) {
            if (StringUtils.isNoneBlank(tagName) && StringUtils.isNoneBlank(value)) {
                tags.put(tagName, value);
            }
            return this;
        }

        public MetricBuilder tag(final Map<String, String> tags) {
            if (!MapUtils.isEmpty(tags)) {
                this.tags.putAll(tags);
            }
            return this;
        }

        public Point build() {
            Point point = new Point();
            point.metric = this.metric;

            if (MapUtils.isEmpty(tags)) {
                throw new IllegalArgumentException("tags can't be empty");
            }
            point.tags = this.tags;

            point.timestamp = this.timestamp;
            point.value = this.value;
            return point;
        }

    }
}
