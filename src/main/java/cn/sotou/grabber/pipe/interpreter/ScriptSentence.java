package cn.sotou.grabber.pipe.interpreter;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sotou.grabber.pipe.exception.ScriptGrammarException;
import cn.sotou.grabber.pipe.util.PipeConstant;

public class ScriptSentence {
	private String leftVar;
	private String startVar;
	private List<String> pipeUtilCmdChain = new LinkedList<String>();

	private static final Pattern LEFT_VAR_PATTERN = Pattern
			.compile("^\\s*([a-zA-Z][a-zA-Z0-9]+)\\s*=\\s*(.+)\\s*$");

	public ScriptSentence(String script) throws ScriptGrammarException {
		super();

		Matcher matcher = LEFT_VAR_PATTERN.matcher(script);
		if (matcher.matches()) {
			setLeftVar(matcher.group(1));
			String pipeExpresion = matcher.group(2);
			String pipeUtils[] = pipeExpresion
					.split(PipeConstant.PIPE_OPERATOR);
			if (pipeUtils.length > 0) {
				setStartVar(pipeUtils[0]);
				for (int i = 1; i < pipeUtils.length; i++) {
					pipeUtilCmdChain.add(pipeUtils[i]);
				}
			} else {
				throw new ScriptGrammarException(script);
			}
		} else {
			throw new ScriptGrammarException(script);
		}
	}

	public String getLeftVar() {
		return leftVar;
	}

	public void setLeftVar(String leftVar) {
		this.leftVar = leftVar;
	}

	public String getStartVar() {
		return startVar;
	}

	public void setStartVar(String startVar) {
		this.startVar = startVar;
	}

	public List<String> getPipeUtilCmdChain() {
		return pipeUtilCmdChain;
	}

	public void setPipeUtilCmdChain(List<String> pipeUtilNameChain) {
		this.pipeUtilCmdChain = pipeUtilNameChain;
	}
}
