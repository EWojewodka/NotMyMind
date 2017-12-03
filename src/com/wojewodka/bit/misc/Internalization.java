package com.wojewodka.bit.misc;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.wojewodka.bit.utils.FXMLUtils;
import com.wojewodka.bit.utils.ResourcesUtils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Internalization {

	public enum Language {
		ENGLISH("en", Locale.ENGLISH), POLISH("pl", new Locale("pl", ""));

		private String shortcut;

		private Locale locale;

		private Language(String shortcut, Locale locale) {
			this.shortcut = shortcut;
			this.locale = locale;
		}

		public static Language findByShortcut(String shortcut) {
			Language[] array = Language.values();
			for (Language l : array) {
				if (l.getShortcut().equalsIgnoreCase(shortcut)) {
					return l;
				}
			}
			return null;
		}

		public String getShortcut() {
			return shortcut;
		}

		public Locale getLocale() {
			return locale;
		}

	}

	private static Language currentLangugage = Language.ENGLISH;

	public static Language getLangugage() {
		return currentLangugage;
	}

	public static void setLangugage(Language currentLangugage) {
		Internalization.currentLangugage = currentLangugage;
	}

	/**
	 * @return all of languages image view. Current locale is excluded. Views has
	 *         set id like a shortcut of lang.
	 */
	public static List<ImageView> getLanguageImage() {
		Language[] languages = Language.values();
		List<ImageView> list = new ArrayList<>(languages.length);

		for (Language lang : languages) {
			String shortcut = lang.getShortcut();
			if (shortcut.equals(currentLangugage.getShortcut())) {
				continue;
			}

			URL url = ResourcesUtils.getImage("flags/" + shortcut + "_flag", ".png");

			ImageView iv = new ImageView(new Image(url.toString()));
			iv.setId(shortcut);
			iv.setFitWidth(60);
			iv.setFitHeight(32);
			iv.maxHeight(32);
			iv.maxWidth(60);
			iv.setSmooth(true);
			iv.setPreserveRatio(true);
			iv.setOnMouseClicked(e -> {
				setLanguage(((MouseEvent) e));
			});
			iv.getStyleClass().add("language-button");

			list.add(iv);
		}

		return list;
	}

	private static void setLanguage(MouseEvent e) {
		ImageView s = (ImageView) e.getSource();
		Language newLanguage = Language.findByShortcut(s.getId());
		if (newLanguage == null) {
			return;
		}
		Internalization.setLangugage(newLanguage);
		FXMLUtils.goTo("Menu");
	}

}
