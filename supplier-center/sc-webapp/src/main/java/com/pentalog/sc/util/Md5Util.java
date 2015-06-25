package com.pentalog.sc.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

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

	public String generateMd5(String input) throws Exception {
		String str = hashString(input, "MD5");
		return str;
	}

	public String readAppSalt() throws IOException {
		// Get the inputStream-->This time we have specified the folder name
		// too.
		Properties properties = new Properties();
		InputStream inputStream = this.getClass().getClassLoader()
				.getResourceAsStream("properties/application.properties");
		// load the inputStream using the Properties
		properties.load(inputStream);
		// get the value of the property
		String propValue = properties.getProperty("applicationSalt");
		return propValue;
	}

}
