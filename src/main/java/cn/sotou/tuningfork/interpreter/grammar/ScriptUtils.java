package cn.sotou.tuningfork.interpreter.grammar;

import java.util.regex.Pattern;

public class ScriptUtils {

	private static final Pattern COMMENT_PATTERN = Pattern.compile("(^\\s*#)|(^\\s*$)");

	public static boolean isExecutableScript(String sentence) {
		return !COMMENT_PATTERN.matcher(sentence).find();
	}
}
