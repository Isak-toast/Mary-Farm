package com.ssafy.maryfarmdiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MaryfarmDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaryfarmDiscoveryServiceApplication.class, args);
    }

}
