package com.cloud.hdfs;

import com.cloud.hdfs.services.HdfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Classname HdfsMsgController
 * @Description TODO
 * @Author Stlife
 * @Date 2018/7/19 13:42
 * @Version 1.0
 **/
@RequestMapping
public class HdfsMsgController {
    @Autowired
    private HdfsUtil hdfsUtil;

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public String upload(){
        hdfsUtil.testData();
        return "ok";
    }
}
