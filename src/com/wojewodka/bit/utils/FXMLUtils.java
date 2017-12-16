package com.wojewodka.bit.utils;

import java.io.IOException;
import java.util.ResourceBundle;

import com.wojewodka.bit.misc.Internalization;
import com.wojewodka.bit.misc.toolbar.CustomToolbar;
import com.wojewodka.bit.runnable.Runnable;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXMLUtils {

	/**
	 * Redirect to specified {@link Scene} with toolbar. </br>
	 * 
	 * @param sceneName
	 */
	public static void goTo(String sceneName) {
		goTo(sceneName, true);
	}

	/**
	 * Redirect to other {@link Scene} </br>
	 * Actually you if doesn't needs a toolbar on top of Stage you could
	 * parameterized it. </br>
	 * In future this method should be expanded for more customization </br>
	 * 
	 * @param sceneName
	 * @param withToolbar
	 */
	public static void goTo(String sceneName, boolean withToolbar) {
		try {
			FXMLLoader loader = new FXMLLoader(ResourcesUtils.getFxml(sceneName),
					ResourceBundle.getBundle("bundles.language", Internalization.getLangugage().getLocale()));
			Parent root = loader.load();
			ControllerUtils.setController(loader.getController());
			Scene scene = new Scene(root);
			StageUtils.setParent(root);
			if (withToolbar) {
				addToolbar((Pane) root);
			}
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
		parent.getChildren().add(new CustomToolbar());
	}

}
