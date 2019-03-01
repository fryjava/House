package com.xmcc.House.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({HttpClientProperties.class})
@ConditionalOnClass({HttpClient.class})
public class HttpClientConfig {
    private HttpClientProperties httpClientProperties;

    public HttpClientConfig(HttpClientProperties httpClientProperties) {
        this.httpClientProperties = httpClientProperties;
    }

    @Bean
    @ConditionalOnMissingBean({HttpClient.class})
    public HttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(httpClientProperties.getConnectTimeOut())
                .setSocketTimeout(httpClientProperties.getScoketTimeOut()).build();
        CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig)
                .setMaxConnTotal(httpClientProperties.getMaxConnTotal()).setMaxConnPerRoute(httpClientProperties.getMaxConnPerRoute())
                .setUserAgent(httpClientProperties.getAgent())
                .setConnectionReuseStrategy(new NoConnectionReuseStrategy()).build();
        return httpClient;
    }
}
