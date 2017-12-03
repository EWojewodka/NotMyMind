package com.wojewodka.bit.misc;

import java.util.Locale;

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

}
