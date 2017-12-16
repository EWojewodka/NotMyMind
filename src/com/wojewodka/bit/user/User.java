package com.wojewodka.bit.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wojewodka.bit.misc.Nodeable;

public class User implements Nodeable{

	private Map<String, Object> fields = new HashMap<>();

	public User() {
		fields.put("created", new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
	}

	public void setField(String name, String value) {
		fields.put(name, value);
	}

	public Object getField(String name) {
		return fields.get(name);
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	@Override
	public Element toNode(Document doc) {
		Element userElement = doc.createElement("User");

		if (fields.size() == 0) {
			return userElement;
		}

		Set<String> keys = fields.keySet();
		Iterator<String> it = keys.iterator();
		while (it.hasNext()) {
			String key = it.next();
			Element fieldElement = doc.createElement(key);
			fieldElement.setTextContent((String) fields.get(key));
			userElement.appendChild(fieldElement);
		}

		return userElement;
	}
}
