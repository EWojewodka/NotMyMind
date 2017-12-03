package com.wojewodka.bit.user.generator;

import java.net.URL;
import java.util.ResourceBundle;

import com.wojewodka.bit.user.User;

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

	public void addOrRemoveAge(MouseEvent e) {
		Button b = (Button) e.getSource();

		int currentAge = Integer.valueOf(age.getText());
		boolean needAdd = b.getId().equals("agePlus");

		// Cannot create hero youger than 13 yo.
		if (!needAdd && currentAge <= 13)
			return;

		age.setText(String.valueOf(currentAge + (needAdd ? 1 : -1)));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		User user = new User();
	}

}
