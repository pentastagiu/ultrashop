package com.pentalog.sr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.pentalog.sr.service.LanguageService;

/**
 * Controller for web  services regarding i18n operations
 * For more information access http://stackoverflow.com/questions/23871694/java-properties-to-json - Yuriy Yunikov solution
 * @authors acozma and bpopovici
 *
 */
@Controller
@RequestMapping("/bundle")
public class LanguageController {
	
	/**
	 *  The language service - contains methods used to fetch data regarding i18n operations.
	 */
	@Autowired
	private LanguageService languageService;
	
	/**
	 * Method that returns a string in json format, that contains the keys and values from a resource bundle
	 * @param langKey - the local language used for translation
	 * @return
	 */
	@RequestMapping(value="/{langKey}",method = RequestMethod.GET)
	public @ResponseBody String getJson(@PathVariable String langKey){
		return languageService.getJson(langKey);
	}
}