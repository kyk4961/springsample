package com.sample.maven.util;

import java.security.MessageDigest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SHAUtil {
	
	public static byte[] HMACSHA256Encode(String key, String data) throws Exception {
		
		Mac sha256HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretkey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		sha256HMAC.init(secretkey);
		
		return sha256HMAC.doFinal(data.getBytes("UTF-8"));
	}
	
	public static String SHAencode(String data, String encType) throws Exception {
		
		MessageDigest digest = MessageDigest.getInstance(encType);
		byte[] hash = digest.digest(data.getBytes("UTF-8"));
		StringBuffer hexString = new StringBuffer();
		
		for (int i = 0; i < hash.length; i++) {
		    String hex = Integer.toHexString(0xff & hash[i]);
		    if(hex.length() == 1) hexString.append('0');
		    hexString.append(hex);
		}
		
		return hexString.toString();
		
	}

}
