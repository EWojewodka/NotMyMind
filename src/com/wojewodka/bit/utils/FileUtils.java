package com.wojewodka.bit.utils;

import java.io.File;

public class FileUtils {

	public static final String ROOT_PATH = new File("").getAbsolutePath();

	public static final String RESOURCES_PATH = ROOT_PATH + File.separator + "resources";
	
	public static void main(String[] args) {
		System.out.println(ROOT_PATH);
		System.out.println(RESOURCES_PATH);
	}

}
