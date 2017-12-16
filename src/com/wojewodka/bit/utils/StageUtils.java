package com.wojewodka.bit.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wojewodka.bit.misc.Nodeable;
import com.wojewodka.bit.runnable.Runnable;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageUtils {

	// VARIABLES FOR DRAGGABLE NODES.
	private static double clickedX;

	private static double clickedY;

	private static double nodeX;

	private static double nodeY;

	private static Parent parent;

	public static Stage getStage() {
		return Runnable.getStage();
	}

	public static void setParent(Parent p) {
		parent = p;
	}

	public static Parent getParent() {
		return parent;
	}

	public static void prepareStage(Stage stage) {
		stage.setWidth(800);
		stage.setHeight(400);
		stage.setResizable(false);
		stage.initStyle(StageStyle.UNDECORATED);
		stage.setTitle("NotMyMind Alpha");
		stage.setUserData(new HashMap<String, Object>());
	}

	/**
	 * Make a draggable region. catch&move&drop.
	 * 
	 * @param pane
	 */
	public static void initDraggable(Region pane) {
		initDraggable(pane, false);
	}

	/**
	 * If window param is <code>true</code> your stage'll move if pane is dragged.
	 * 
	 * @param pane
	 * @param window
	 */
	public static void initDraggable(Region pane, boolean window) {
		pane.setOnMousePressed(event -> {
			pane.setCursor(Cursor.OPEN_HAND);
			/*
			 * Get location of click.
			 */
			clickedX = event.getX();
			clickedY = event.getY();
		});

		pane.onMouseDraggedProperty().set(e -> {
			/*
			 * Drag&Drop node logic.
			 */
			double mouseX = (window) ? e.getScreenX() : e.getSceneX();
			double mouseY = (window) ? e.getScreenY() : e.getSceneY();

			double value = mouseX - clickedX;
			double value2 = mouseY - clickedY;

			if (window) {
				Stage stage = Runnable.getStage();
				stage.setX(value + nodeX);
				stage.setY(value2 + nodeY);
			} else {
				pane.setLayoutX(value + nodeX);
				pane.setLayoutY(value2 + nodeY);
			}

		});

		pane.onMouseReleasedProperty().set(event -> {
			pane.setCursor(Cursor.DEFAULT);
		});
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> getGameplayData() {
		return (Map<String, Object>) getStage().getUserData();
	}

	/**
	 * 
	 * @return only objects which implements {@link Nodeable} interface.
	 */
	public static List<Nodeable> getNodeGamplayData() {
		Map<String, Object> gd = getGameplayData();
		List<Nodeable> list = new ArrayList<>();
		gd.values().forEach(v -> {
			if (v instanceof Nodeable) {
				list.add((Nodeable) v);
			}
		});
		return list;
	}

	public static boolean addToGampeplayData(String name, Object value) {
		Map<String, Object> gameplayData = getGameplayData();
		if (gameplayData.containsKey(name)) {
			return gameplayData.replace(name, gameplayData.get(name), value);
		} else {
			gameplayData.put(name, value);
		}
		return true;
	}

}
