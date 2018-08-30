package com.cloud.hdfs.data.etl;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname TextFileUtil
 * @Description TODO
 * @Author Stlife
 * @Date 2018/8/2 10:25
 * @Version 1.0
 **/
public class TextFileUtil {
    public static void main(String[] args) {
//        String dir = "G:\\10_works\\gy\\out\\";
//        String output = "G:\\10_works\\gy\\out\\file_list.txt";
//        String fp = "GYZH_fileList.txt,GYWC_fileList.txt,GYJG_fileList.txt,GY_fileList.txt";
//        String paths[] = fp.split(",");
//        for(String path:paths){
//            appendText(dir + path,output);
//        }
        //比较两个文件内容，并输出新的文件内容
        String s = "M:\\prj\\data\\out\\fileList.txt";
        String t = "M:\\prj\\data\\out\\list.csv";
        String o = "M:\\prj\\data\\out\\compare.txt";
        compare(s,t,o);
    }

    public static void compare(String s,String t,String o){
        try {
            // read file content from file
            FileReader treader = new FileReader(t);
            Map tmap = new HashMap();
            BufferedReader tbr = new BufferedReader(treader);
            String tstr = null;
            while ((tstr = tbr.readLine()) != null) {
                String[] split = tstr.split(",");
                tmap.put(split[1].toLowerCase(),split[0]);
            }
            tbr.close();
            treader.close();

            StringBuffer sb = new StringBuffer("");
            FileReader reader = new FileReader(s);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            while ((str = br.readLine()) != null) {
//                String[] split = str.split(",");
                str = str.replaceAll("/","\\\\");
                System.out.println(str);
                if(!tmap.containsKey(str.toLowerCase())){
                    sb.append(str + "\n");
                    System.out.println(str);
                }
            }
            br.close();
            reader.close();

            FileWriter writer = new FileWriter(o,true);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sb.toString());
            bw.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void appendText(String input,String output) {
        try {
            // read file content from file
            StringBuffer sb = new StringBuffer("");
            FileReader reader = new FileReader(input);
            BufferedReader br = new BufferedReader(reader);
            String str = null;
            while ((str = br.readLine()) != null) {
                sb.append(str + "\n");
            }
            br.close();
            reader.close();

            FileWriter writer = new FileWriter(output,true);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(sb.toString());
            bw.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
