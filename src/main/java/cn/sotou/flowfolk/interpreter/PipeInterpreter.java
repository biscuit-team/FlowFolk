package cn.sotou.flowfolk.interpreter;

import cn.sotou.flowfolk.exception.PipeException;
import cn.sotou.flowfolk.interpreter.grammar.*;
import cn.sotou.flowfolk.interpreter.task.IStreamsProcessor;
import cn.sotou.flowfolk.interpreter.task.SimpleStreamsProcessor;
import cn.sotou.flowfolk.interpreter.task.ThreadStreamsProcessor;
import cn.sotou.flowfolk.interpreter.grammar.PipeConstant;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class PipeInterpreter extends PipeSupport {

	private InterpreterConfig config;

	private VariableDereferencer dereferencer = new VariableDereferencer();

	private PipeCommandBuilder pipeCommandBuilder;

	private VariableStorage variableStorage = new VariableStorage();

	private IStreamsProcessor streamsProcessor;

	public void init() {
		if (config.getMultiThreads()) {
			this.streamsProcessor = new ThreadStreamsProcessor(config.getMaxThreadNum());
		} else {
			this.streamsProcessor = new SimpleStreamsProcessor();
		}
		if (config.getUtilProvider() != null) {
			pipeCommandBuilder = new PipeCommandBuilder(config.getUtilProvider());
		} else {
			pipeCommandBuilder = new PipeCommandBuilder();
		}
	}

	public void evaluate(String script) throws PipeException {

		String[] scriptLines = script.split("\n");

		evaluate(scriptLines);

	}

	public void evaluate(String[] scriptLines) throws
			PipeException {

		for (String string : scriptLines) {
			if (!ScriptComment.isComment(string)) {
				evaluateLine(string, variableStorage);
			}
		}
	}


	private void evaluateLine(String scriptLine, VariableStorage variableStorage) throws PipeException {
		String dereferencedScriptLine = dereferencer.dereference(scriptLine,
				variableStorage);
		ScriptSentence sentence = new ScriptSentence(dereferencedScriptLine);

		InputStream inputs[] = getStartStreams(sentence.getStartVar(), variableStorage);

		for (String pipeCmd : sentence.getPipeUtilCmdChain()) {

			PipeCommand command = pipeCommandBuilder.build(pipeCmd);

			inputs = streamsProcessor.process(inputs, command);

		}
		try {
			variableStorage.addStreamsAsVariable(sentence.getLeftVar(), inputs);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
