package com.wojewodka.bit.utils;

public class StringUtils {

	public static boolean isEmpty(String value) {
		if (value == null || value.trim().length() <= 0) {
			return true;
		}
		return false;
	}

	/**
	 * Return zero if value contains chars.
	 * 
	 * @param value
	 * @return
	 */
	public static int intValue(String value) {
		if (!onlyDigits(value)) {
			return 0;
		}
		return Integer.valueOf(value);
	}

	public static int intValue(String value, int defaultValue) {
		int result = intValue(value);

		if (defaultValue == 0 && result == 0) {
			return 0;
		}

		if (result == 0) {
			return defaultValue;
		}

		return result;
	}

	public static String stringValue(Object value, String defaultValue) {
		String v = value.toString();
		if (v instanceof String)
			return v;

		if (isEmpty(v))
			return defaultValue;

		return null;
	}

	public static boolean boolValue(Object value, boolean defaultValue) {
		if (value == null)
			return defaultValue;

		if (value instanceof Boolean)
			return (boolean) value;

		if (value instanceof String) {
			String v = (String) value;
			if (v.equalsIgnoreCase("true") || v.equals("1"))
				return true;

			if (v.equalsIgnoreCase("false") || v.equals("0"))
				return false;
		}

		return defaultValue;
	}

	/**
	 * Return false if is empty or null.
	 * 
	 * @param v
	 * @return
	 */
	public static boolean onlyDigits(String v) {
		if (StringUtils.isEmpty(v)) {
			return false;
		}
		char[] charArray = v.toCharArray();
		for (Character c : charArray) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}
}