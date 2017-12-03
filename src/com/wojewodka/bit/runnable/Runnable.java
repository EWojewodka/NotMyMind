package com.wojewodka.bit.runnable;

import com.wojewodka.bit.utils.FXMLUtils;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Runnable extends Application {

	private static Stage stage;
	
	private static final String START_SCENE = "UserGenerator";

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setWidth(800);
		primaryStage.setHeight(400);
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.setTitle("NotMyMind Alpha");
		setStage(primaryStage);

		try {
			FXMLUtils.goTo(START_SCENE);
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

	public static void main(String[] args) {
		launch(args);
	}
}
