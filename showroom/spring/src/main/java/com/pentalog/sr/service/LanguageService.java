package com.pentalog.sr.service;

/**
 * The language service
 * @authors acozma and bpopovici
 *
 */
public interface LanguageService {
	
	/**
	 * Method that returns a string in json format, that contains the keys and values from a resource bundle
	 * @param langKey - the local language used for translation
	 * @return
	 */
	String getJson(String langKey);
}