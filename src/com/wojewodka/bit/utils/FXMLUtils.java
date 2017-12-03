package com.wojewodka.bit.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import com.wojewodka.bit.misc.CustomToolbar;
import com.wojewodka.bit.misc.Internalization;
import com.wojewodka.bit.runnable.Runnable;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLUtils {

	private static final ToolBar TOOLBAR = new CustomToolbar();

	private static double clickedX;

	private static double clickedY;

	private static double nodeX;

	private static double nodeY;

	public static void goTo(String sceneName) {
		goTo(sceneName, true);
	}

	public static void goTo(String sceneName, boolean withToolbar) {
		try {
			FXMLLoader loader = new FXMLLoader(StandardCharsets.UTF_8);
			Parent root = loader.load(ResourcesUtils.getFxml(sceneName), ResourceBundle.getBundle("bundles.language", Internalization.getLangugage().getLocale()));
			Scene scene = new Scene(root);
			addToolbar((Pane) root);
			scene.getStylesheets().add(ResourcesUtils.getCss("application.css"));
			Stage stage = Runnable.getStage();
			stage.setScene(scene);

			if (!stage.isShowing()) {
				stage.show();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addToolbar(Pane parent) {
		parent.getChildren().add(TOOLBAR);
	}

	public static void initDtaggable(Region pane) {
		initDraggable(pane, false);
	}

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

	public static void handleReflect(Region source) {
		Timeline t = new Timeline();
		t.setAutoReverse(true);
		Effect effect = source.getEffect();
		if (!(effect instanceof Glow)) {
			effect = new Glow(0);
			source.setEffect(effect);
		}

		Glow glowEff = (Glow) effect;
		double currentLevel = glowEff.getLevel();
		t.getKeyFrames()
				.add(new KeyFrame(Duration.millis(300),
						new KeyValue(glowEff.levelProperty(), (currentLevel > 0) ? 1 : 0),
						new KeyValue(glowEff.levelProperty(), (currentLevel > 0) ? 0 : 1)));
		t.play();
	}

}
