package com.ssafy.maryfarmapigatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MaryfarmApigatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaryfarmApigatewayServiceApplication.class, args);
    }

}
