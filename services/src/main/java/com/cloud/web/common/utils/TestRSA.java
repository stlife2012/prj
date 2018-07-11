package com.cloud.web.common.utils;
/** 
* 类说明 
* @author  stlife
* @version V1.0  创建时间：2018年7月9日 下午5:11:41  
*/

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class TestRSA {

	public static void main(String args[]) {
		try {
			// ===============生成公钥和私钥，公钥传给客户端，私钥服务端保留==================
			// 生成RSA公钥和私钥，并Base64编码
			KeyPair keyPair = RSAUtil.getKeyPair();
			String publicKeyStr = RSAUtil.getPublicKey(keyPair);
			String privateKeyStr = RSAUtil.getPrivateKey(keyPair);
			String pubFile = "pub.key";
			String privateFile = "pri.key";
			FileUtil.createFile(pubFile);
			FileUtil.writeFile(pubFile,publicKeyStr);
			FileUtil.createFile(privateFile);
			FileUtil.writeFile(privateFile,privateKeyStr);

			publicKeyStr = FileUtil.readFile(pubFile);
			privateKeyStr = FileUtil.readFile(privateFile);
//			System.out.println(pubString.equals(publicKeyStr));

			System.out.println("RSA公钥Base64编码:" + publicKeyStr);
			System.out.println("RSA私钥Base64编码:" + privateKeyStr);

			// =================客户端=================
			// hello, i am infi, good night!加密
			String message = "hello, i am infi, good night!";
			// 将Base64编码后的公钥转换成PublicKey对象
			PublicKey publicKey = RSAUtil.string2PublicKey(publicKeyStr);
			// 用公钥加密
			byte[] publicEncrypt = RSAUtil.publicEncrypt(message.getBytes(), publicKey);
			// 加密后的内容Base64编码
			String byte2Base64 = RSAUtil.byte2Base64(publicEncrypt);
			System.out.println("公钥加密并Base64编码的结果：" + byte2Base64);

			// ############## 网络上传输的内容有Base64编码后的公钥 和 Base64编码后的公钥加密的内容 #################

			// ===================服务端================
			// 将Base64编码后的私钥转换成PrivateKey对象
			PrivateKey privateKey = RSAUtil.string2PrivateKey(privateKeyStr);
			// 加密后的内容Base64解码
			byte[] base642Byte = RSAUtil.base642Byte(byte2Base64);
			// 用私钥解密
			byte[] privateDecrypt = RSAUtil.privateDecrypt(base642Byte, privateKey);
			// 解密后的明文
			System.out.println("解密后的明文: " + new String(privateDecrypt));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
