package com.cloud.hdfs;

import com.cloud.hdfs.services.HdfsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HdfsServiceApplication {
    public static void main(String[] args) {
//        FsSystem fs = null;
        SpringApplication.run(HdfsServiceApplication.class, args);
    }
}
