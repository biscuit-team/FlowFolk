package cn.sotou.flowfolk.interpreter;

import java.util.regex.Pattern;

public class ScriptComment {

	private static final Pattern COMMENT_PATTERN = Pattern.compile("(^\\s*#)|(^\\s*$)");

	public static boolean isComment(String sentence) {
	boolean result = COMMENT_PATTERN.matcher(sentence).find();
		return result;
	}
}
