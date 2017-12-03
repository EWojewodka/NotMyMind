package com.wojewodka.bit.user.generator;

import java.net.URL;
import java.util.ResourceBundle;

import com.wojewodka.bit.runnable.Runnable;
import com.wojewodka.bit.user.User;
import com.wojewodka.bit.utils.StringUtils;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class UserGeneratorController implements Initializable {

	@FXML
	private TextField name;

	@FXML
	private Button ageMinus;

	@FXML
	private Button agePlus;

	@FXML
	private Label age;

	@FXML
	private Label stamina;

	@FXML
	private Label charisma;

	@FXML
	private Label energy;
	
	private User user;

	public void addOrRemoveAge(MouseEvent e) {
		Button b = (Button) e.getSource();

		int currentAge = Integer.valueOf(age.getText());
		boolean needAdd = b.getId().equals("agePlus");

		// Cannot create hero younger than 13 yo.
		if (!needAdd && currentAge <= 13)
			return;

		int newAge = currentAge + (needAdd ? 1 : -1);
		age.setText(String.valueOf(newAge));
		user.setAge(newAge);
		Runnable.getStage().setUserData(user);
	}

	public void addOrRemoveValue(MouseEvent e) {
		Button b = (Button) e.getSource();
		String buttonId = b.getId();
		if (StringUtils.isEmpty(buttonId)) {
			return;
		}

		boolean add = buttonId.endsWith("Plus");
		String parentId = null;
		if (add) {
			parentId = buttonId.substring(0, buttonId.length() - 4); // xxx[Plus]
		} else {
			parentId = buttonId.substring(0, buttonId.length() - 5);
		}

		Label[] changeableLabels = new Label[] {stamina, charisma, energy };

		for (Label l : changeableLabels) {
			String labelId = l.getId();

			if (StringUtils.isEmpty(labelId)) {
				continue;
			}

			if (labelId.equalsIgnoreCase(parentId)) {
				int value = Integer.valueOf(l.getText());
				value = (add) ? ++value : --value;
				l.setText(String.valueOf(value));
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		User userData = (User) Runnable.getStage().getUserData();
		this.user = (userData == null) ? new User() : (User) userData;
		
	}

}
