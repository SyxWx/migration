package com.bme.syx.cloud.third;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "alicloud.country")
public class AlicloudCountryConfig {

    private String host;

    private String path;

    private String method;

    private String appcode;

    private String city;

    private String stations;
}
