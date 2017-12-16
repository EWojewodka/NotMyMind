package com.wojewodka.bit.user;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.wojewodka.bit.controller.core.Changeable;
import com.wojewodka.bit.utils.ClassUtils;
import com.wojewodka.bit.utils.ControllerUtils;
import com.wojewodka.bit.utils.StageUtils;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserUtils {

	/**
	 * Copy values from controller's control to user map </br>
	 * For correct copying you should implements {@link Changeable}.
	 * 
	 * @param user
	 */
	public static void copyValueToUser(User user) {
		if (user == null) {
			return;
		}
		Object _controller = ControllerUtils.getController();
		if (!ClassUtils.hasImplementationOf(_controller, Changeable.class)) {
			return;
		}

		Changeable controller = (Changeable) _controller;
		Label[] labels = controller.getLabels();
		if (labels.length > 0) {
			for (int i = 0; i < labels.length; i++) {
				Label l = labels[i];
				if (l == null)
					continue;

				user.setField(l.getId(), l.getText());
			}
		}

		TextField[] textFields = controller.getTextFields();
		if (textFields.length > 0) {
			for (int i = 0; i < textFields.length; i++) {
				TextField l = textFields[i];
				if (l == null)
					continue;
				user.setField(l.getId(), l.getText());
			}
		}
	}

	/**
	 * Copy value from user map to controller's control. </br>
	 * Please implements {@link Changeable} for correct copying.
	 * 
	 * @param user
	 */
	public static void copyValueToController(User user) {
		if (user == null) {
			return;
		}

		Object _controller = ControllerUtils.getController();
		if (!ClassUtils.hasImplementationOf(_controller, Changeable.class)) {
			return;
		}

		Changeable controller = (Changeable) _controller;
		Label[] labels = controller.getLabels();
		if (labels.length > 0) {
			for (int i = 0; i < labels.length; i++) {
				Label l = labels[i];
				if (l == null)
					continue;

				l.setText((String) user.getField(l.getId()));
			}
		}

		TextField[] textFields = controller.getTextFields();
		if (textFields.length > 0) {
			for (int i = 0; i < textFields.length; i++) {
				TextField l = textFields[i];
				if (l == null)
					continue;
				l.setText((String) user.getField(l.getId()));
			}
		}
	}

	/**
	 * Return {@link Element} of <code>XML</code>. </br>
	 * 
	 * @param user
	 * @return
	 * @throws ParserConfigurationException
	 */
	public static Element toNode(Document doc, User user) throws ParserConfigurationException {
		if (user == null) {
			return null;
		}

		Element userElement = doc.createElement("User");

		Map<String, Object> fields = user.getFields();
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

	public static User getUser() {
		Map<String, Object> gameplayData = StageUtils.getGameplayData();
		if (gameplayData.containsKey("user")) {
			return (User) gameplayData.get("user");
		}
		return null;
	}
}
