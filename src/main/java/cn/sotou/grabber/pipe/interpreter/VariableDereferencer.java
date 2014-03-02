package cn.sotou.grabber.pipe.interpreter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VariableDereferencer {

	private static final Pattern REF_VAR_PATTERN = Pattern
			.compile("@\\(([a-zA-Z-0-9]+)\\)");

	public String dereference(String input, VariableStorage variableStorage) {
		Matcher matcher = REF_VAR_PATTERN.matcher(input);
		String result = new String(input);
		while (matcher.find()) {
			String value = variableStorage.getVariablesAsMap().get(matcher.group(1))[0];
			result = result.replace(matcher.group(0), value);
		}
		return result;
	}
}
