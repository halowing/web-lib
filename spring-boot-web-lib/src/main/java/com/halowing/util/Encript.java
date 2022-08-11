package com.halowing.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Assert;

public class Encript {

	private static final String ENC_TYPE = "SHA-256";

	public static String encriptSHA256(String password, String salt) throws NoSuchAlgorithmException {
		if(password == null ) return null;
		Assert.notNull(salt, "salt can't be null1.");
		
		MessageDigest md = MessageDigest.getInstance(ENC_TYPE);
		md.reset();
		md.update(salt.getBytes());
		byte[] hash = md.digest(password.getBytes());
		
		return new String(Base64.encodeBase64(hash));
	}
}
