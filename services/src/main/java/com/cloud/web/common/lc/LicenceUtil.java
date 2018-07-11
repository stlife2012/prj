package com.cloud.web.common.lc;

import com.alibaba.fastjson.JSON;
import com.cloud.web.common.data.pojo.LicenceObj;
import com.cloud.web.common.utils.DateUtil;
import com.cloud.web.common.utils.FileUtil;
import com.cloud.web.common.utils.RSAUtil;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.UUID;

public class LicenceUtil {
    private static String pubFile = "pub.key";
    private static String privateFile = "pri.key";

    private static String TIME_FORMART = "yyyy-MM-dd HH:mm:ss";

    public static void main(String args[]) {
//        String code = encryptData("你好");
//        System.out.println(decodeData(code));
        pubLc("lis",10);
        System.out.println(DateUtil.getCurrentDate().getTime());
    }

    /**
     * 生成LC信息
     * @param name
     * @param day
     * @return
     */
    public static LicenceObj pubLc(String name, Integer day){
        Date preDate = DateUtil.addDatetimeByDay(DateUtil.getCurrentDate(),day);
        String valDate = DateUtil.getDate(preDate, TIME_FORMART);

        String uuid = UUID.randomUUID().toString();
        LicenceObj cert = new LicenceObj(uuid,name,preDate.getTime(),null,null);
        String jsonStr = JSON.toJSONString(cert);

        String secretKey = encryptData(jsonStr);
        cert.setSecKey(secretKey);
        System.out.println(">>" + JSON.toJSONString(cert));
        return cert;
    }

    public static String getLc(){
        StringBuffer buffer = new StringBuffer();

        return buffer.toString();
    }

    public static void init() {
        // 生成RSA公钥和私钥，并Base64编码
        try {
            KeyPair keyPair = RSAUtil.getKeyPair();
            String publicKeyStr = RSAUtil.getPublicKey(keyPair);
            String privateKeyStr = RSAUtil.getPrivateKey(keyPair);

            FileUtil.createFile(pubFile);
            FileUtil.writeFile(pubFile, publicKeyStr);
            FileUtil.createFile(privateFile);
            FileUtil.writeFile(privateFile, privateKeyStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encryptData(String message) {
        StringBuffer buffer = new StringBuffer();
        // 将Base64编码后的公钥转换成PublicKey对象
        try {
            PublicKey publicKey = RSAUtil.string2PublicKey(FileUtil.readFile(pubFile));
            // 用公钥加密
            byte[] publicEncrypt = RSAUtil.publicEncrypt(message.getBytes(), publicKey);
            // 加密后的内容Base64编码
            String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
            buffer.append(byte2Base64);
//            System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public static String decodeData(String message) {
        StringBuffer content = new StringBuffer();
        // 将Base64编码后的私钥转换成PrivateKey对象
        try {
            PrivateKey privateKey = RSAUtil.string2PrivateKey(FileUtil.readFile(privateFile));
            // 加密后的内容Base64解码
            byte[] base642Byte = RSAUtil.base642Byte(message);
            // 用私钥解密
            byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
            content.append(new String(privateDecrypt));
            // 解密后的明文
//            System.out.println("解密后的明文: " + new String(privateDecrypt));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
