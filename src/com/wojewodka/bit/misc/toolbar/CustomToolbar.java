package com.wojewodka.bit.misc.toolbar;

import com.wojewodka.bit.runnable.Runnable;
import com.wojewodka.bit.utils.ClassUtils;
import com.wojewodka.bit.utils.ControllerUtils;
import com.wojewodka.bit.utils.StageUtils;
import com.wojewodka.bit.utils.StringUtils;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class CustomToolbar extends ToolBar {

	public enum ToolbarAction {

		BACK("back"),
		/**
		 * 
		 */
		SAVE("save"),
		/**
		 * 
		 */
		EXIT("exit");

		private String code;

		private ToolbarAction(String code) {
			this.code = code;
		}

		public static ToolbarAction findByCode(String code) {
			ToolbarAction[] values = values();
			for (ToolbarAction ta : values) {
				if (ta.getCode().equalsIgnoreCase(code)) {
					return ta;
				}
			}
			return null;
		}

		public String getCode() {
			return code;
		}
	}

	private ToolbarListener eventImplementation;

	public CustomToolbar() {
		Object con = ControllerUtils.getController();
		if (!ClassUtils.hasImplementationOf(con, ToolbarListener.class))
			return;

		eventImplementation = (ToolbarListener) con;
		setPrefHeight(15);
		setMinWidth(Runnable.getStage().getWidth());
		setLayoutX(0);
		idProperty().set("toolbar");
		getStyleClass().add("toolbar");
		addItems();
		StageUtils.initDraggable(this, true);
	}

	private void addItems() {
		addBackButton();
		addSaveButton();
		addSpace();
		addExitButton();
	}

	private Button toolbarItemGenerator(String content, String cssClass) {
		Button b = new Button();
		b.getStyleClass().add("icon");
		b.getStyleClass().add("toolbar-item");
		if (!StringUtils.isEmpty(cssClass)) {
			b.getStyleClass().add(cssClass);
		}
		b.setText(content);
		b.toFront();
		b.setVisible(true);

		return b;
	}

	private void addSpace() {
		HBox e = new HBox();
		HBox.setHgrow(e, Priority.ALWAYS);
		getItems().add(e);
	}

	private void addBackButton() {
		if (!eventImplementation.getToolbarActions().contains(ToolbarAction.BACK))
			return;

		Button b = toolbarItemGenerator("\uf060", null);
		b.setOnMouseClicked(e -> {
			eventImplementation.onBack();
		});

		getItems().add(b);
	}

	private void addSaveButton() {
		if (!eventImplementation.getToolbarActions().contains(ToolbarAction.SAVE))
			return;

		Button b = toolbarItemGenerator("\uf0c7", null);
		b.setOnMouseClicked(e -> {
			eventImplementation.onSave();
		});
		getItems().add(b);
	}

	private void addExitButton() {
		if (!eventImplementation.getToolbarActions().contains(ToolbarAction.EXIT))
			return;

		Button b = toolbarItemGenerator("\uf00d", "toolbar-exit");
		b.setAlignment(Pos.CENTER_RIGHT);
		b.setOnMouseClicked(e -> {
			eventImplementation.onExit();
		});
		getItems().add(b);
	}

}
