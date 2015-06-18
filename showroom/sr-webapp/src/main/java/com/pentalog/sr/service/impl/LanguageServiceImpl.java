package com.pentalog.sr.service.impl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import org.springframework.stereotype.Service;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.pentalog.sr.service.LanguageService;
import com.pentalog.sr.utils.BundleMapSerializer;

/**
 * The language service implementation
 * @authors acozma and bpopovici
 *
 */
@Service("languageService")
public class LanguageServiceImpl implements LanguageService {

	/**
	 * @see {@link LanguageService.getJson}
	 */
	@Override
	public String getJson(String langKey) {
		Map<String,String> translations = new HashMap<>();
		String jsonBundle;
		if(translations.containsKey(langKey))
			jsonBundle = translations.get(langKey);
		else
		{
			Locale locale = new Locale(langKey);
			final ResourceBundle bundle = ResourceBundle.getBundle("bundle.messages",locale);
			final Map<String, String> bundleMap = resourceBundleToMap(bundle);
	
			final Type mapType = new TypeToken<Map<String, String>>() {
			}.getType();
	
			 jsonBundle = new GsonBuilder()
					.registerTypeAdapter(mapType, new BundleMapSerializer())
					.create().toJson(bundleMap, mapType);
			translations.put(langKey, jsonBundle);
		}
		return jsonBundle;
	}
	
	/**
	 * Method that returns a map from a resource bundle
	 * @param bundle - the resource bundle
	 * @return
	 */
	private Map<String, String> resourceBundleToMap(ResourceBundle bundle) {
	    Map<String, String> bundleMap = new HashMap<>();

	    for (String key: bundle.keySet()) {
	        String value = bundle.getString(key);

	        bundleMap.put(key, value);
	    }
	    return bundleMap;
	}
}