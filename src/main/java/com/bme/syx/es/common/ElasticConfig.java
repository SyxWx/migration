package com.bme.syx.es.common;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("elasticsearch")
public class ElasticConfig {

    /**
     * 基础服务ES地址
     */
    private List<String> basicUrls;

    /**
     * 运营中心ES地址
     */
    private List<String> centerUrls;

    @Bean("basicJestClient")
    public JestClient basicJestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(basicUrls)
                //.gson(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create())
                .multiThreaded(true)
                .build());
        return factory.getObject();
    }

}
