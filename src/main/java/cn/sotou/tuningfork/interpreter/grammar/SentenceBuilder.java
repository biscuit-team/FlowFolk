package cn.sotou.tuningfork.interpreter.grammar;

import cn.sotou.tuningfork.exception.ScriptGrammarException;
import cn.sotou.tuningfork.interpreter.VariableStorage;
import cn.sotou.tuningfork.util.chain.CommandChain;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shigong on 14-3-11.
 */
public class SentenceBuilder {

	private static final Pattern LEFT_VAR_PATTERN = Pattern
			.compile("^\\s*([a-zA-Z][a-zA-Z0-9]+)\\s*=\\s*(.+)\\s*$");

	private PipeCommandBuilder pipeCommandBuilder;

	private VariableStorage variableStorage;

	public SentenceBuilder() {
	}


	public ScriptSentence build(String script) {

		ScriptSentence sentence = new ScriptSentence();

		Matcher matcher = LEFT_VAR_PATTERN.matcher(script);
		if (matcher.matches()) {
			sentence.setLeftVar(matcher.group(1));
			String pipeExpression = matcher.group(2);
			String pipeUtils[] = pipeExpression
					.split(PipeConstant.PIPE_OPERATOR);
			if (pipeUtils.length > 0) {
				sentence.setStartStreams(getStartStreams(pipeUtils[0], variableStorage));
				PipeCommand[] cmds = new PipeCommand[pipeUtils.length - 1];
				for (int i = 1; i < pipeUtils.length; i++) {
					cmds[i - 1] = pipeCommandBuilder.build(pipeUtils[i]);
				}

				sentence.setPipeUtilCmdChain(new CommandChain(cmds));
			} else {
				throw new ScriptGrammarException(script);
			}
		} else {
			throw new ScriptGrammarException(script);
		}
		return sentence;
	}


	private InputStream[] getStartStreams(String startName,
	                                      VariableStorage variableStorage) {
		if (startName.startsWith(PipeConstant.REF_CHAR)) {
			return variableStorage.getVariableAsSteams(startName);
		} else {
			return new InputStream[]{IOUtils.toInputStream(startName)};
		}
	}

	public void setPipeCommandBuilder(PipeCommandBuilder pipeCommandBuilder) {
		this.pipeCommandBuilder = pipeCommandBuilder;
	}

	public void setVariableStorage(VariableStorage variableStorage) {
		this.variableStorage = variableStorage;
	}
}
