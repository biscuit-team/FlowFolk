package cn.sotou.flowfolk.interpreter.grammar;

import java.util.regex.Pattern;

/**
 * Created by shigong on 14-3-4.
 */
public abstract class EscapeUtils {
	//private static char[] CHAR_TO_ESCAPE = new char[]{' ', '\\', '\'', '|', '"'};
	//private static char[] CHAR_TO_ESCAPE = new char[]{' ', '\\', '|'};

	private static final String ESCAPE_PATTERN;

	static {
		ESCAPE_PATTERN = ("\\\\(\\||\\\\|\\ )");
	}

	public static String decode(String encodedString) {
		return encodedString.replaceAll(ESCAPE_PATTERN, "$1");
	}

	public static String[] decode(String[] encodedStrings) {
		String[] decodedStrings = new String[encodedStrings.length];
		for (int i = 0; i < encodedStrings.length; i++) {
			decodedStrings[i] = decode(encodedStrings[i]);
		}
		return decodedStrings;
	}
}
