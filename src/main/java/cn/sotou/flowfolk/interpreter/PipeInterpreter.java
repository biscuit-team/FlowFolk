package cn.sotou.flowfolk.interpreter;

import cn.sotou.flowfolk.interpreter.task.IStreamsProcessor;
import cn.sotou.flowfolk.interpreter.task.SimpleStreamsProcessor;
import cn.sotou.flowfolk.interpreter.task.ThreadStreamsProcessor;
import cn.sotou.flowfolk.util.PipeConstant;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PipeInterpreter extends PipeSupport {

	private InterpreterConfig config;

	private VariableDereferencer dereferencer = new VariableDereferencer();

	private PipeCommandBuilder pipeCommandBuilder = new PipeCommandBuilder();

	private VariableStorage variableStorage = new VariableStorage();

	private IStreamsProcessor streamsProcessor;

	public void evaluate(String script) throws Exception {

		String[] scriptLines = script.split("\n");

		if (config.getMultiThreads()) {
			this.streamsProcessor = new ThreadStreamsProcessor(config.getMaxThreadNum());
		} else {
			this.streamsProcessor = new SimpleStreamsProcessor();
		}

		evaluate(scriptLines);

	}

	public void evaluate(String[] scriptLines) throws
			Exception {

		for (String string : scriptLines) {
			if (!ScriptComment.isComment(string)) {
				evaluateLine(string, variableStorage);
			}
		}
	}


	private void evaluateLine(String scriptLine, VariableStorage variableStorage) throws Exception {
		String dereferencedScriptLine = dereferencer.dereference(scriptLine,
				variableStorage);
		ScriptSentence sentence = new ScriptSentence(dereferencedScriptLine);

		InputStream inputs[] = getStartStreams(sentence.getStartVar(), variableStorage);

		for (String pipeCmd : sentence.getPipeUtilCmdChain()) {

			PipeCommand command = pipeCommandBuilder.build(pipeCmd);

			inputs = streamsProcessor.process(inputs, command);

		}
		variableStorage.addStreamsAsVariable(sentence.getLeftVar(), inputs);
	}

	private InputStream[] getStartStreams(String startName,
	                                      VariableStorage variableStorage) {
		if (startName.startsWith(PipeConstant.REF_CHAR)) {
			return variableStorage.getVariableAsSteams(startName);
		} else {
			return new InputStream[]{IOUtils.toInputStream(startName)};
		}
	}


	public String[] getVariable(String name) {
		return variableStorage.getVariablesAsMap().get(name);
	}

	public InterpreterConfig getConfig() {
		return config;
	}

	public void setConfig(InterpreterConfig config) {
		this.config = config;
	}
}
