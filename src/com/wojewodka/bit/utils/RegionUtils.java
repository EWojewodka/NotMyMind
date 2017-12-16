package com.wojewodka.bit.utils;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class RegionUtils {

	public static void prepareLogo(Label logo) {
		prepareLogo(logo, 1000);
	}
	
	public static void prepareLogo(Label logo, long time) {
		Timeline t = new Timeline();
		t.getKeyFrames().add(
				new KeyFrame(Duration.millis(time), new KeyValue(((BoxBlur) logo.getEffect()).widthProperty(), 0)));
		t.play();
	}

	public static void handleReflect(Region source) {
		handleReflect(source, 300);
	}

	public static void handleReflect(Region source, long time) {
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
				.add(new KeyFrame(Duration.millis(time),
						new KeyValue(glowEff.levelProperty(), (currentLevel > 0) ? 1 : 0),
						new KeyValue(glowEff.levelProperty(), (currentLevel > 0) ? 0 : 1)));
		t.play();
	}

}
