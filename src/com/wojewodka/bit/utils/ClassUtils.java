package com.wojewodka.bit.utils;

public class ClassUtils {

	public static boolean hasImplementationOf(Object object, Class<?> interfaceClass) {
		if (object == null || interfaceClass == null)
			return false;
		Class<?> clazz = object.getClass();
		return hasImplementationOf(clazz, interfaceClass);
	}

	public static boolean hasImplementationOf(Class<?> clazz, Class<?> interfaceClass) {
		if (clazz == null || interfaceClass == null)
			return false;
		Class<?>[] interfaces = clazz.getInterfaces();
		if (interfaces == null || interfaces.length == 0) {
			return false;
		}

		boolean result = false;
		for (Class<?> c : interfaces) {
			if (c.getName().equals(interfaceClass.getName())) {
				result =  true;
				break;
			}
		}
		return result;
	}

}
