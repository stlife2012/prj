package com.cloud.web.common.utils;

import java.io.*;

public class FileUtil {
    public static void main(String args[]) {
        String fp = "pub.key";
        FileUtil.createFile(fp);
        FileUtil.writeFile(fp,"good");
    }

    public static void writeFile(String filePath, String conent) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, false)));
            out.write(conent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readFile(String filePath) {
        StringBuffer content = new StringBuffer();
        File file = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                content.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return content.toString();
    }

    public static Boolean createFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("文件已存在");
            return false;
        } else {
            try {
                File fileParent = file.getParentFile();
                if (fileParent != null) {
                    if (!fileParent.exists()) {
                        fileParent.mkdirs();
                    }
                }
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
