package cn.sotou.tuningfork.interpreter.grammar;

public abstract class PipeConstant {

	public static final String NO_ESCAPE_CHAR = "(?<!\\\\)";
	public static final String PIPE_OPERATOR = "\\s*(" + NO_ESCAPE_CHAR + "\\|)\\s*";
	public static final String REF_CHAR = NO_ESCAPE_CHAR + "@";
	public static final String ARGS_SPLIT = "(" + NO_ESCAPE_CHAR + "\\s)+";
}
