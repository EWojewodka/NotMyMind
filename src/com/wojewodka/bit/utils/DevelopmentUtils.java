package com.wojewodka.bit.utils;

import com.wojewodka.bit.developer.DeveloperStage;
import com.wojewodka.bit.property.PropertyFacade;

public class DevelopmentUtils {

	/**
	 * Return true if application is in development mode.
	 * 
	 * @return
	 */
	public static boolean isDevelopment() {
		return PropertyFacade.getBoolProperty("development", false);
	}
	
	public static void runDevelopmentTool() {
		@SuppressWarnings("unused")
		DeveloperStage developerStage = new DeveloperStage();
	}

}
