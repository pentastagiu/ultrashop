package com.pentalog.sc.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class Md5Util {

	public Md5Util() {
	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString(
					(arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}

	private static String hashString(String message, String algorithm)
			throws Exception {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

			return convertByteArrayToHexString(hashedBytes);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
			throw new Exception("Could not generate hash from String", ex);
		}
	}

	/**
	 * Encrypt a string with md5
	 * 
	 * @param input
	 * @return
	 */
	public String generateMd5(String input) throws Exception {
		String str = hashString(input, "MD5");
		return str;
	}

}
