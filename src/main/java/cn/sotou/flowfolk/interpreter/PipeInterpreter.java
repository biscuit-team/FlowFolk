package cn.sotou.flowfolk.interpreter;

import cn.sotou.flowfolk.interpreter.task.StreamsProcessor;
import cn.sotou.flowfolk.util.PipeConstant;
import cn.sotou.flowfolk.util.PipeSupport;
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

	private StreamsProcessor streamsProcessor;

	public void evaluate(String script) throws Exception {

		String[] scriptLines = script.split("\n");

		evaluate(scriptLines);

	}

	public void evaluate(String[] scriptLines) throws
			Exception {
		streamsProcessor = new StreamsProcessor(config.getMaxThreadNum());
		for (String string : scriptLines) {
			if (!ScriptComment.isComment(string)) {
				if (config.getMultiThreads()) {
					evaluateLineWithMultiThread(string, variableStorage);
				} else {
					evaluateLine(string, variableStorage);
				}

			}
		}
	}

	private void evaluateLine(String scriptLine, VariableStorage variableStorage)
			throws Exception {
		String dereferencedScriptLine = dereferencer.dereference(scriptLine,
				variableStorage);
		ScriptSentence sentence = new ScriptSentence(dereferencedScriptLine);

		InputStream inputs[] = getStartStreams(sentence.getStartVar(), variableStorage);

		for (String pipeCmd : sentence.getPipeUtilCmdChain()) {

			List<InputStream> results = new LinkedList<InputStream>();
			PipeCommand command = pipeCommandBuilder.build(pipeCmd);

			for (InputStream inputStream : inputs) {
				InputStream[] aResultInputs = command.execute(inputStream);
				results.addAll(Arrays.asList(aResultInputs));
				IOUtils.closeQuietly(inputStream);
			}

			inputs = results.toArray(new InputStream[results.size()]);

		}
		variableStorage.addStreamsAsVariable(sentence.getLeftVar(), inputs);
	}

	private void evaluateLineWithMultiThread(String scriptLine, VariableStorage variableStorage) throws Exception {
		String dereferencedScriptLine = dereferencer.dereference(scriptLine,
				variableStorage);
		ScriptSentence sentence = new ScriptSentence(dereferencedScriptLine);

		InputStream inputs[] = getStartStreams(sentence.getStartVar(), variableStorage);

		for (String pipeCmd : sentence.getPipeUtilCmdChain()) {

			List<InputStream> results = new LinkedList<InputStream>();
			PipeCommand command = pipeCommandBuilder.build(pipeCmd);


			inputs = streamsProcessor.process(inputs,command);

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
