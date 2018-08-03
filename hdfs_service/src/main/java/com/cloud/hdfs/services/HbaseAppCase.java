package com.cloud.hdfs.services;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname HbaseAppCase
 * @Description TODO
 * @Author Stlife
 * @Date 2018/7/23 12:17
 * @Version 1.0
 **/
public class HbaseAppCase {
    public static void main(String[] args){
//        createTable();
//        putDataToTable();
//        getDataFromTable();
//        readImage();
//        putImgDataToTable();
//        getImgDataFromTable();
    }

    public static void readImage(){
        try {
            BufferedImage image = ImageIO.read(new File("G:\\10_works\\img002828.jpg")); //读取图片文件流
            ByteArrayOutputStream out = new ByteArrayOutputStream();//新建流。
            ImageIO.write(image,"jpg",out);
            byte[] data = out.toByteArray();
            System.out.println(data);

            ByteArrayInputStream in = new ByteArrayInputStream(data);    //将b作为输入流；
            BufferedImage nimage = ImageIO.read(in);
            ImageIO.write(nimage,"jpg",new File("G:\\10_works\\nimg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void putImgDataToTable(){
        HbaseUtil util = new HbaseUtil();
        try{
            BufferedImage image = ImageIO.read(new File("G:\\10_works\\img002828.jpg")); //读取图片文件流
            ByteArrayOutputStream out = new ByteArrayOutputStream();//新建流。
            ImageIO.write(image,"jpg",out);
            byte[] data = out.toByteArray();
            util.putDataH("t_doc","img002828.jpg","content","data",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void getImgDataFromTable(){
        HbaseUtil util = new HbaseUtil();
        try {
            byte[] data = util.getImageValueBySeriesH("t_yj_doc","t_data_2","content","data");
            System.out.println("-----------------------------data:" + data.length);
            ByteArrayInputStream in = new ByteArrayInputStream(data);    //将b作为输入流；
            BufferedImage nimage = ImageIO.read(in);
            ImageIO.write(nimage,"jpg",new File("G:\\10_works\\nimg1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void createTable(){
        HbaseUtil util = new HbaseUtil();
        util.createImageTable("t_doc","info,content");
    }

    public static void putDataToTable(){
        HbaseUtil util = new HbaseUtil();
        try{
            List data = new ArrayList();
            data.add("one");
            data.add("two");
            util.putDataH("t_yj_doc","t_data_1","content","data",data);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getDataFromTable(){
        HbaseUtil util = new HbaseUtil();
        try {
            String data = util.getValueBySeriesH("t_yj_doc","t_data_1","content","data");
            System.out.println("-----------------------------data:" + data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
