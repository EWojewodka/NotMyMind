package com.wojewodka.bit.developer;

import java.io.IOException;

import com.wojewodka.bit.utils.ResourcesUtils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Run stage with tools which helps with development application.
 * 
 * @author wojew
 *
 */
public class DeveloperStage extends Stage {

	public DeveloperStage() {
		setWidth(300);
		setHeight(600);
		try {
			setScene(new Scene(FXMLLoader.load(ResourcesUtils.getFxml("developmentTool/Basic"))));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setTitle("Developer tool");

		show();
	}

}
