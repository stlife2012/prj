package com.cloud.web.services.web.liver.handler;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("LIVER-SERVICE")
public interface LiverServiceHandler {

    @RequestMapping("/check/health")
    public String checkHealth();
}
