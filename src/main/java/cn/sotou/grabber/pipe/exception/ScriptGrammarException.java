package cn.sotou.grabber.pipe.exception;

@SuppressWarnings("serial")
public class ScriptGrammarException extends Exception {

	public ScriptGrammarException(String script) {
		super(script);
	}

}
