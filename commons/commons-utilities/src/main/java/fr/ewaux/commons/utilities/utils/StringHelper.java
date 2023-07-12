package fr.ewaux.commons.utilities.utils;

public final class StringHelper {

	public static String generate(final int i) {
		return i < 0 ? "" : generate((i / 26) - 1) + (char) (65 + i % 26);
	}
}
