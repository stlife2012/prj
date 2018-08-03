package com.cloud.hdfs.services;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

/**
 * @Classname HdfsUtil
 * @Description TODO
 * @Author Stlife
 * @Date 2018/7/19 13:40
 * @Version 1.0
 **/
//@Component
public class HdfsUtil {
    public static String SRC_DIR = "G:\\10_works\\gy\\tar";
    public static String HDFS_URL = "hdfs://master:9000";
    public static String TAR_DIR = "/data/prj/yjprj_tar";

    public void testData(){
        System.out.println("test Data");
    }

    public FileSystem init(){
        System.setProperty("HADOOP_USER_NAME", "cloud");
        URI uri = null;
        FileSystem fileSystem = null;
        Configuration conf = new Configuration();
        try {
            conf.set("fs.defaultFS",HDFS_URL);
            fileSystem = FileSystem.get(conf);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileSystem;
    }

    public void check(){
        FileSystem fs = init();
        Path path = new Path(SRC_DIR + "/T02-GYZH-K0001/img002828.jpg");
        try {
            FileStatus fileStatus = fs.getFileStatus(path);
            System.out.println("*************************************");
            System.out.println("文件根目录: "+fileStatus.getPath());
            System.out.println("这文件目录为：");
            for(FileStatus f : fs.listStatus(path)){
                System.out.println(f.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void upload(Boolean overWrite){
        Path srcPath = new Path(SRC_DIR + "/T02-GYZH-K0001/img002828.jpg");
        Path dstPath = new Path(HDFS_URL + TAR_DIR + "/1/img002828.jpg");
        FileSystem fs = init();
        try {
            if(overWrite){
                fs.copyFromLocalFile(false,overWrite, srcPath, dstPath);
            }else if(!fs.exists(dstPath)){//不重写
                fs.copyFromLocalFile(false,overWrite, srcPath, dstPath);
            }
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("*************************************");
        System.out.println("上传成功！");
    }

    /**
     *@Author Stlife
     *@Description 将指定目录下的所有文件上传到HDFS
     *@Date 17:30 2018/7/19
     *@Param [srcDir, dstDir, overWrite]
     *@return void
     **/
    public void uploadToHdfs(String srcDir,String dstDir,Boolean overWrite){
        List<String> paths = FileHandler.getAllFilePaths(srcDir);
        FileSystem fs = init();
        try {
            int i = 0;
            for (String path : paths) {
                i ++;
                Path srcPath = new Path(path);
                String srcfp = path.replace(SRC_DIR,"");
                srcfp = srcfp.replaceAll("\\\\","/");
                String srcfd = srcfp.substring(0,srcfp.lastIndexOf("/"));
                String strfn = srcfp.substring(srcfp.lastIndexOf("/"));

                Path dstfDir = new Path(HDFS_URL + TAR_DIR + srcfd);

                if(!fs.exists(dstfDir)){//创建目录
                    fs.mkdirs(dstfDir);
                    System.out.println("创建目录" + dstfDir);
                }
                Path dstPath = new Path(HDFS_URL + TAR_DIR + srcfp);
                if(overWrite){
                    fs.copyFromLocalFile(false,overWrite, srcPath, dstPath);
                }else if(!fs.exists(dstPath)){//不重写
                    fs.copyFromLocalFile(false,overWrite, srcPath, dstPath);
                }
                if(i % 100 == 0){
                    System.out.println("已上传文件数量：" + i + " 文件总数：" + paths.size());
                }
            }
            fs.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * filename是希望解压的文件
     */
    public static void decompres(String filename){
        System.out.println("[" + new Date() + "] : enter compress");
        Configuration conf = new Configuration();
        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(new Path(filename));
        if (null == codec) {
            System.out.println("Cannot find codec for file " + filename);
            return;
        }
        File fout = new File(filename+ ".decoded");
        try {
            InputStream cin = codec.createInputStream(new FileInputStream(filename));
            OutputStream out = new FileOutputStream(fout);
            System.out.println("[" + new Date() + "]: start decompressing ");
            IOUtils.copyBytes(cin, out, 1024 * 1024 * 5, false);
            System.out.println("[" + new Date() + "]: decompressing finished ");
            cin.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        HdfsUtil util = new HdfsUtil();
//        util.uploadToHdfs(SRC_DIR,TAR_DIR,false);

        util.decompres(HDFS_URL);
    }
}
