package com.cloud.web.services.web.liver;

import com.cloud.web.services.web.liver.handler.LiverServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/liver")
public class LiverHealthService {
    @Autowired
    private LiverServiceHandler liverServiceHandler;

    @RequestMapping(value = "/status",method = RequestMethod.GET)
    public String check(){
        System.out.println("测试LIVER-SERVICE模块服务调用");
        return "Health:" + liverServiceHandler.checkHealth();
    }
}
