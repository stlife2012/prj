package com.cloud.hdfs.data.etl;

import java.io.*;

/**
 * @Classname TextFileUtil
 * @Description TODO
 * @Author Stlife
 * @Date 2018/8/2 10:25
 * @Version 1.0
 **/
public class TextFileUtil {
    public static void main(String[] args) {
        String dir = "G:\\10_works\\gy\\out\\";
        String output = "G:\\10_works\\gy\\out\\file_list.txt";
        String fp = "GYZH_fileList.txt,GYWC_fileList.txt,GYJG_fileList.txt,GY_fileList.txt";
        String paths[] = fp.split(",");
        for(String path:paths){
            appendText(dir + path,output);
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
