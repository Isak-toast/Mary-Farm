package com.ssafy.maryfarmchatservice;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Optional;
import java.util.UUID;

@OpenAPIDefinition
@SpringBootApplication
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableFeignClients
@EnableCaching
@EnableScheduling
public class MaryfarmChatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaryfarmChatServiceApplication.class, args);
    }
    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of(UUID.randomUUID().toString());
    }
    @Bean
    Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }
}
