package com.wojewodka.bit.property;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import com.wojewodka.bit.utils.ResourcesUtils;
import com.wojewodka.bit.utils.StringUtils;

public class PropertyFacade {

	private static InputStream is;

	private static Properties prop;

	static {
		URL resource = ResourcesUtils.getResource("properties", ".properties");
		try {
			is = new FileInputStream(resource.getFile());
			prop = new Properties();
			prop.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getStringProperty(String name) {
		return getStringProperty(name, null);
	}

	public static String getStringProperty(String name, String defaultValue) {
		return prop.getProperty(name, defaultValue);
	}

	public static boolean getBoolProperty(String name) {
		return getBoolProperty(name, false);
	}

	public static boolean getBoolProperty(String name, boolean defaultValue) {
		return StringUtils.boolValue(getStringProperty(name), defaultValue);
	}

}
