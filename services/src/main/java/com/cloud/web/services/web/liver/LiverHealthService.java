package com.cloud.web.services.web.liver;

import com.cloud.web.common.data.LicenceAccess;
import com.cloud.web.common.data.LicenceHandlerService;
import com.cloud.web.common.data.pojo.LicenceObj;
import com.cloud.web.services.web.liver.handler.LiverServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/liver")
public class LiverHealthService {
    @Autowired
    private LiverServiceHandler liverServiceHandler;

    @Resource
    private LicenceHandlerService licenceService;

    @RequestMapping(value = "/status",method = RequestMethod.GET)
    @LicenceAccess
    public String check() throws Exception {
        System.out.println("测试LIVER-SERVICE模块服务调用");
        return "Health:" + liverServiceHandler.checkHealth();
    }

    @RequestMapping(value = "/lc",method = RequestMethod.GET)
    public LicenceObj loadLc(){
        return licenceService.loadLicence("be58cc01-a363-470f-9e03-2713e150699f",false);
    }
}
