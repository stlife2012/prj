package com.cloud.hdfs.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname FileHandler
 * @Description TODO
 * @Author Stlife
 * @Date 2018/7/19 16:29
 * @Version 1.0
 **/
public class FileHandler {
    public static String SRC_DIR = "G:\\10_works\\yc";

    public static List<String> getAllFilePaths(File filePath, List<String> filePaths) {
        File[] files = filePath.listFiles();
        if (files == null) {
            return filePaths;
        }
        for (File f : files) {
            if (f.isDirectory()) {
//                filePaths.add(f.getPath());
                getAllFilePaths(f, filePaths);
            } else {
                filePaths.add(f.getPath());
            }
        }
        return filePaths;
    }

    public static List<String> getAllFilePaths(String dir){
        List<String> paths = new ArrayList<String>();
        paths = getAllFilePaths(new File(dir), paths);
        return paths;
    }

    public static void main(String[] args) {
        List<String> paths = getAllFilePaths(SRC_DIR);
        for (String path : paths) {
            System.out.println(path);
            String fp = path.replace(SRC_DIR,"");
            System.out.println(fp);
            String fd = fp.substring(0,fp.lastIndexOf("\\"));
            String ff = fp.substring(fp.lastIndexOf("\\") + 1);
//            fp = fp.replaceAll("\\","/");
            System.out.println(path + " " + fp + " " + fd + " " + ff);
        }
        System.out.println(paths.size());
    }
}
