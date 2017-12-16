package com.wojewodka.bit.menu;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.wojewodka.bit.misc.Internalization;
import com.wojewodka.bit.runnable.Runnable;
import com.wojewodka.bit.utils.FXMLUtils;
import com.wojewodka.bit.utils.RegionUtils;
import com.wojewodka.bit.utils.ResourcesUtils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MenuController implements Initializable {

	@FXML
	private Label menuLogo;

	@FXML
	private Button startButton;

	@FXML
	private Button exitButton;

	@FXML
	private Button loadButton;

	@FXML
	private ImageView languageFlags;

	@FXML
	private VBox otherLanguage;

	public void handleReflect(MouseEvent e) {
		Region source = (Region) e.getSource();
		RegionUtils.handleReflect(source);
	}

	public void startNewGame() {
		FXMLUtils.goTo("UserGenerator");
	}
	
	public void loadGame() {
		FXMLUtils.goTo("Load");
	}

	public void exit(MouseEvent e) {
		Runnable._stop();
	}

	public void prepareLogo() {
		RegionUtils.prepareLogo(menuLogo);
	}

	public void toggleOtherLanguage() {
		if(!otherLanguage.isVisible()) {
			showOtherLanguage();
		} else {
			hideOtherLanguage();
		}
	}
	
	private void hideOtherLanguage() {
		Timeline time = new Timeline();
		DoubleProperty xProperty = otherLanguage.opacityProperty();
		KeyFrame onStart = new KeyFrame(Duration.millis(100), new KeyValue(xProperty, 0));
		KeyFrame onFinish = new KeyFrame(Duration.millis(100), evt -> otherLanguage.setVisible(false));
		time.getKeyFrames().addAll(onStart, onFinish);
		time.play();
	}
	
	private void showOtherLanguage() {
		Timeline time = new Timeline();
		otherLanguage.setOpacity(1);
		otherLanguage.setLayoutY(-30);
		DoubleProperty yProperty = otherLanguage.layoutYProperty();
		time.getKeyFrames().add(new KeyFrame(Duration.millis(200), 
										new KeyValue(yProperty, 325)));
		time.play();
		otherLanguage.setVisible(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		addOtherLanguage();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		prepareLogo();
	}

	private void addOtherLanguage() {
		String shortcut = Internalization.getLangugage().getShortcut();
		URL url = ResourcesUtils.getImage("flags/" + shortcut + "_flag", ".png");
		languageFlags.setImage(new Image(url.toString()));

		otherLanguage.setMaxWidth(60);
		List<ImageView> languageImage = Internalization.getLanguageImage();
		double languageHeight = languageImage.size() * languageImage.get(0).getFitHeight();
		otherLanguage.setPrefHeight(languageHeight);
		otherLanguage.setMaxHeight(languageHeight);
		otherLanguage.heightProperty().add(languageHeight);
		otherLanguage.getChildren().addAll(languageImage);
	}

}
