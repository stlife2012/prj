package com.cloud.hcms.services.liver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LiverServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiverServicesApplication.class, args);
    }
}
