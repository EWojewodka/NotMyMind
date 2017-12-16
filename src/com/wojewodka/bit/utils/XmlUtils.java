package com.wojewodka.bit.utils;

import org.w3c.dom.Element;

public class XmlUtils {

	public static void addAttribute(Element element, String name, Object value) {
		element.setAttribute(name, value.toString());
	}

}
