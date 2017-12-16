package com.wojewodka.bit.runnable;

import com.wojewodka.bit.property.PropertyFacade;
import com.wojewodka.bit.utils.DevelopmentUtils;
import com.wojewodka.bit.utils.FXMLUtils;
import com.wojewodka.bit.utils.StageUtils;

import javafx.application.Application;
import javafx.stage.Stage;

public class Runnable extends Application {

	private static Stage stage;

	private static final String START_SCENE = PropertyFacade.getStringProperty("stage.start", "Menu");

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		StageUtils.prepareStage(primaryStage);
		setStage(primaryStage);
		
		if(PropertyFacade.getBoolProperty("development", false)) {
			DevelopmentUtils.runDevelopmentTool();
		}
		
		try {
			FXMLUtils.goTo(START_SCENE, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void _stop() {
		System.exit(0);
	}

	public static Stage getStage() {
		return stage;
	}

	private void setStage(Stage stage) {
		Runnable.stage = stage;
	}

}
