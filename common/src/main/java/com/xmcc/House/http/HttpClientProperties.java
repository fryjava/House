package com.xmcc.House.http;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "httpclient")
public class HttpClientProperties {
    private  String agent="agent";
    private  Integer maxConnPerRoute =10 ;
    private  Integer maxConnTotal =50 ;
    private  Integer connectTimeOut =1000 ;
    private  Integer scoketTimeOut =100000;

}
