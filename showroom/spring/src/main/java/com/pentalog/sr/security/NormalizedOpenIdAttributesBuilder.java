package com.pentalog.sr.security;

import java.util.HashSet;
import java.util.Set;
import org.springframework.security.openid.OpenIDAttribute;
import org.springframework.security.openid.OpenIDAuthenticationToken;

/**
 * Class that normalizes exchanged attributes with the open id service provider
 * @author acozma
 *
 */
public class NormalizedOpenIdAttributesBuilder {
	
	/**
	 *	The email address attributes 
	 */
	private Set<String> emailAddressAttributeNames = new HashSet<String>();

	/**
	 * Method that retrieves the user email from the open id provider
	 * @param openIdAuthenticationToken - the token given by the open id provider
	 * @return
	 */
	public String retrieveEmail(
			OpenIDAuthenticationToken openIdAuthenticationToken) {
		String emailAddress = setUpEmailAddress(openIdAuthenticationToken);
		return emailAddress;
	}
	private String setUpEmailAddress(
			OpenIDAuthenticationToken openIdAuthenticationToken) {
		for (OpenIDAttribute openIDAttribute : openIdAuthenticationToken
				.getAttributes()) {
			if (setContainsAndAttributeHasValue(emailAddressAttributeNames,
					openIDAttribute)) {
				return openIDAttribute.getValues().get(0);
			}
		}
		return null;
	}
	private boolean setContainsAndAttributeHasValue(
			Set<String> emailAddressAttributeNames,
			OpenIDAttribute openIDAttribute) {
		return emailAddressAttributeNames.contains(openIDAttribute.getName())
				&& attributeHasValue(openIDAttribute);
	}
	private boolean attributeHasValue(OpenIDAttribute openIDAttribute) {
		return openIDAttribute.getValues() != null
				&& openIDAttribute.getValues().size() > 0;
	}
	public void setEmailAddressAttributeNames(
			Set<String> emailAddressAttributeNames) {
		this.emailAddressAttributeNames = emailAddressAttributeNames;
	}
}