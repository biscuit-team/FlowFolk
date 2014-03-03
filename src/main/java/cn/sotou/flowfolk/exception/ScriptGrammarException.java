package cn.sotou.flowfolk.exception;

@SuppressWarnings("serial")
public class ScriptGrammarException extends Exception {

	public ScriptGrammarException(String script) {
		super(script);
	}

}
