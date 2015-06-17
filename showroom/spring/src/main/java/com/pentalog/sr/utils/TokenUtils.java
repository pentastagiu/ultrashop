package com.pentalog.sr.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * A TokenUtils is an utility class that creates and validates authentication
 * tokens from UserDetails.
 * 
 * @author <a href="mailto:scherrer@openwms.org">Heiko Scherrer</a>
 * @version $Revision: $
 * @since 0.1
 */
public class TokenUtils {

	/**
	 * Create a new authentication token from the given username with an
	 * expiration period of one hour.
	 * 
	 * @param userDetails
	 *            From where the username is taken
	 * @return Token as colon concatenated String
	 */
	public static String createToken(UserDetails userDetails) {
		long expires = System.currentTimeMillis() + 1000L * 60 * 60;
		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(userDetails.getUsername()).append(":");
		tokenBuilder.append(expires).append(":");
		tokenBuilder.append(TokenUtils.computeSignature(userDetails, expires));
		return tokenBuilder.toString();
	}

	/**
	 * Concatenate credentials with <code>expires</code>, add a salt and hash
	 * this String.
	 * 
	 * @param userDetails
	 *            Where to take the credentials from
	 * @param expires
	 *            Expiration lease
	 * @return The hashed String
	 */
	public static String computeSignature(UserDetails userDetails, long expires) {
		StringBuilder signatureBuilder = new StringBuilder();
		Properties saltProp = new Properties();
		try {
			saltProp.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config/saltConfig.properties"));
		} catch (IOException e1) {
			throw new RuntimeException(e1);
		}
		signatureBuilder.append(userDetails.getUsername());
		signatureBuilder.append(":");
		signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(userDetails.getPassword());
		signatureBuilder.append(":");
		signatureBuilder.append(saltProp.getProperty("showroom.solr.salt"));
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(
					"No MD5 algorithm found on platform!");
		}
		return new String(Sha512DigestUtils.shaHex(digest
				.digest(signatureBuilder.toString().getBytes())));
	}

	/**
	 * Split token at colons and return the first part as username.
	 * 
	 * @param authenticationToken
	 *            The token
	 * @return The username or <code>null</code>
	 */
	public static String getUserNameFromToken(String authenticationToken) {
		if (null == authenticationToken) {
			return null;
		}
		String[] parts = authenticationToken.split(":");
		return parts[0];
	}

	/**
	 * Validate <code>authenticationToken</code> if expired and its content.
	 * 
	 * @param authenticationToken
	 *            The token
	 * @param userDetails
	 *            To be used for content comparison
	 * @return <code>true</code> is valid, otherwise <code>false</code>
	 */
	public static boolean validateToken(String authenticationToken,
			UserDetails userDetails) {
		String[] parts = authenticationToken.split(":");
		long expires = Long.parseLong(parts[1]);
		String signature = parts[2];
		if (expires < System.currentTimeMillis()) {
			return false;
		}
		String newSignature = TokenUtils.computeSignature(userDetails,
				expires);
		return signature.equals(newSignature);
	}
}