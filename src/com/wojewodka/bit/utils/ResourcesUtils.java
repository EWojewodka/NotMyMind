package com.wojewodka.bit.utils;

import java.net.URL;

public class ResourcesUtils {

	public static URL getResource(String path, String extension) {
		if (StringUtils.isEmpty(path))
			return null;

		path = (path.startsWith("/")) ? path.substring(1, path.length()) : path;
		path = (path.endsWith(extension)) ? path : path + extension;

		URL resource = ResourcesUtils.class.getClassLoader().getResource(path);
		if (resource == null) {
			resource = ResourcesUtils.class.getClass().getClassLoader().getResource(path);
		}
		return resource;
	}

	public static URL getImage(String path, String extension) {
		return getResource("images/" + path, extension);
	}

	public static URL getFxml(String path) {
		return getResource("fxml/" + path, ".fxml");
	}

	public static String getCss(String path) {
		return getResource("css/" + path, ".css").toExternalForm();
	}

}
