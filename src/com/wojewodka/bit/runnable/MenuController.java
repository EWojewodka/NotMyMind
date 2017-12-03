package com.wojewodka.bit.runnable;

import java.net.URL;
import java.util.ResourceBundle;

import com.wojewodka.bit.misc.Internalization;
import com.wojewodka.bit.misc.Internalization.Language;
import com.wojewodka.bit.utils.FXMLUtils;
import com.wojewodka.bit.utils.ResourcesUtils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
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

	public void handleReflect(MouseEvent e) {
		Region source = (Region) e.getSource();
		FXMLUtils.handleReflect(source);
	}

	public void startNewGame() {
		FXMLUtils.goTo("UserGenerator");
	}

	public void exit(MouseEvent e) {
		Runnable._stop();
	}

	public void prepareLogo() {
		Timeline t = new Timeline();
		t.getKeyFrames().add(
				new KeyFrame(Duration.millis(1000), new KeyValue(((BoxBlur) menuLogo.getEffect()).widthProperty(), 0)));
		t.play();
	}

	private void setLanguage(MouseEvent e) {
		ImageView s = (ImageView) e.getSource();
		Language newLanguage = Language.findByShortcut(s.getId());
		Internalization.setLangugage(newLanguage);
		
		FXMLUtils.goTo("Menu");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String shortcut = Internalization.getLangugage().getShortcut();
		URL url = ResourcesUtils.getImage("flags/" + shortcut + "_flag", ".png");
		languageFlags.setImage(new Image(url.toString()));
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		prepareLogo();
	}

}
