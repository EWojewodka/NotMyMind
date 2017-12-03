package com.wojewodka.bit.misc;

import com.wojewodka.bit.runnable.Runnable;
import com.wojewodka.bit.utils.FXMLUtils;

import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

public class CustomToolbar extends ToolBar {

	public CustomToolbar() {
		setPrefHeight(15);
		setMinWidth(Runnable.getStage().getWidth());
		setLayoutX(0);
		idProperty().set("toolbar");
		addExitButton();
		FXMLUtils.initDraggable(this, true);
	}

	private void addExitButton() {
		Button b = new Button();
		b.setText("x");
		b.setLayoutY(0);
		b.toFront();
		b.setId("toolbarCross");
		b.setVisible(true);
		b.setOnMouseClicked(e -> {
			Runnable._stop();
		});
		getItems().add(b);
	}

}
