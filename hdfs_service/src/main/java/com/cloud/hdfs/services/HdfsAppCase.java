package com.cloud.hdfs.services;

/**
 * @Classname HdfsAppCase
 * @Description TODO
 * @Author Stlife
 * @Date 2018/7/23 12:21
 * @Version 1.0
 **/
public class HdfsAppCase {
    public static void main(String[] args){
        upload();
    }

    public static void upload(){//将指定文件夹下的压缩包拷贝到指定的路径下
        HdfsUtil util = new HdfsUtil();
        String SRC_DIR = "G:\\10_works\\gy\\tar";
        String HDFS_URL = "hdfs://master:9000";
        String TAR_DIR = "/data/prj/yjprj_tar";
        util.uploadToHdfs(SRC_DIR,TAR_DIR,false);
    }
}
