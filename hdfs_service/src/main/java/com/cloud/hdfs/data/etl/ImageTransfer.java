package com.cloud.hdfs.data.etl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname ImageTransfer
 * @Description TODO
 * @Author Stlife
 * @Date 2018/7/27 9:38
 * @Version 1.0
 **/
public class ImageTransfer {
    public static void main(String args[]){
        String listPath = "G:\\10_works\\gy\\test\\test.txt"; //GYJG_fileList.txt GYWC_fileList
        String outFile = "G:\\10_works\\gy\\test\\test_data.txt";
        String dataDir = "G:\\10_works\\gy\\prjyj\\";

        long start = System.currentTimeMillis();
        List<String> lists = readLists(listPath);
        System.out.println("Num:" + lists.size());
//        System.out.println(lists.get(0));
        transfer(lists,dataDir,outFile);
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start));
    }

    public static void transfer(List<String> lists,String dataDir,String outFile){
        PrintWriter out = null;
        try {
            Integer WIDTH = 400;
            Integer HEIGHT = Double.valueOf(WIDTH * 1.4).intValue();
            out = new PrintWriter(outFile);
            int curNum = 0;
            for(String path:lists){
                curNum ++;
                String fpath = dataDir + path;
                BufferedImage image = ImageIO.read(new File(fpath));
                
                /* 转化图片 */
                BufferedImage bwImage = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_BYTE_GRAY);
                int[] pixels = new int[WIDTH * HEIGHT];
                Graphics g = bwImage.getGraphics();
                g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
                g.dispose();
                bwImage.getData().getPixels(0, 0, WIDTH, HEIGHT, pixels);
                
//                System.out.println("bounds:" + bwImage.getData().getNumBands());
//                System.out.println(pixels.length);
                String outStr = path + "," + intToStr(pixels,"|");
                out.write(outStr + "\n");

                if(curNum % 100 == 0){
                    System.out.println("总数： " + lists.size() + " 当前处理：" + curNum);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }

    /**
     *@Author Stlife
     *@Description 将数组转化为指定的分割的字符串
     *@Date 10:05 2018/7/27
     *@Param [data, splitTip]
     *@return java.lang.String
     **/
    public static String intToStr(int data[],String splitTip){
        StringBuffer buffer = new StringBuffer();
        for(int v:data){
            buffer.append(v + splitTip);
        }
        return buffer.toString();
    }

    /**
     *@Author Stlife
     *@Description 获取图像路径列表
     *@Date 9:41 2018/7/27
     *@Param [path]
     *@return java.util.List<java.lang.String>
     **/
    public static List<String> readLists(String path){
        List<String> lists = new ArrayList<String>();
        try {
            File file = new File(path);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.length() < 1){
                    continue;
                }
                lists.add(line);
            }
            fileReader.close();
            System.out.println("Contents of file:");
            System.out.println(stringBuffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }
}
