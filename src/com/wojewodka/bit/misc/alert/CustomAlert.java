package com.wojewodka.bit.misc.alert;

import java.util.Arrays;

import com.wojewodka.bit.utils.ResourcesUtils;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.StageStyle;

/**
 * Style class: </br>
 * {@link AlertType#name()} to lowerCase
 * 
 * @author wojew
 *
 */
public class CustomAlert extends Alert {

	public CustomAlert(AlertType alertType) {
		this(alertType, null);
	}

	public CustomAlert(AlertType alertType, String[] styleClasses) {
		super(alertType);
		DialogPane dp = getDialogPane();
		dp.getStylesheets().add(ResourcesUtils.getCss("application"));
		dp.getStyleClass().addAll("alert", getAlertType().name().toLowerCase());
		if (styleClasses != null && styleClasses.length > 0) {
			dp.getStyleClass().addAll(Arrays.asList(styleClasses));
		}
		initStyle(StageStyle.UNDECORATED);
	}

}
