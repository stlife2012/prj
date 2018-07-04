package com.cloud.hcms.services.liver.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/check")
public class CheckLiverService {
    @RequestMapping("/health")
    public Map healthStatus(){
        Map status = new HashMap<String,String>();
        status.put("status","1");
        status.put("check_time",new Date(System.currentTimeMillis()).toString());
        status.put("name","zhangs");
        return status;
    }
}
