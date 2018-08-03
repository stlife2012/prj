package com.cloud.hdfs.services;

import org.apache.hadoop.fs.Path;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

/**
 * @Classname UploadImgToHBase
 * @Description TODO
 * @Author Stlife
 * @Date 2018/7/23 15:40
 * @Version 1.0
 **/
public class UploadImgToHBase {
    public static String SRC_DIR = "G:\\10_works\\gy\\prjyj";

    public static void main(String[] args){

//        util.uploadToHdfs(SRC_DIR);
        uploadToHdfs(SRC_DIR);
    }
    public static void uploadToHdfs(String srcDir){
        HbaseUtil util = new HbaseUtil();
        List<String> paths = FileHandler.getAllFilePaths(srcDir);
        int i = 0;
        try{
            for (String path : paths) {
                i ++;
                Path srcPath = new Path(path);
                String srcfp = path.replace(srcDir,"");
                srcfp = srcfp.replaceAll("\\\\","/");
                System.out.println("文件路径：" + path + " => " + srcfp);

                BufferedImage image = ImageIO.read(new File(path));         //读取图片文件流
                ByteArrayOutputStream out = new ByteArrayOutputStream();    //新建流
                ImageIO.write(image,"jpg",out);
                byte[] data = out.toByteArray();
                util.putDataH("t_doc",srcfp,"content","data",data);

                if(i % 100 == 0){
                    System.out.println("已上传文件数量：" + i + " 文件总数：" + paths.size());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
