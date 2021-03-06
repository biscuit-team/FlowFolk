package cn.sotou.tuningfork.interpreter;

import cn.sotou.tuningfork.exception.PipeException;
import cn.sotou.tuningfork.interpreter.grammar.*;
import cn.sotou.tuningfork.interpreter.parallel.ChainScriptLineEvaluator;
import cn.sotou.tuningfork.interpreter.task.IStreamsProcessor;
import cn.sotou.tuningfork.interpreter.task.SimpleStreamsProcessor;
import cn.sotou.tuningfork.interpreter.task.ThreadStreamsProcessor;

public class PipeInterpreter extends PipeSupport {

	private InterpreterConfig config;

	private final VariableDereferencer dereferencer = new VariableDereferencer();

	private final VariableStorage variableStorage = new VariableStorage();

	private ScriptLineEvaluator lineEvaluator;

	private final SentenceBuilder sentenceBuilder = new SentenceBuilder();

	public void init() {


		if (config.isChainThreads()) {
			lineEvaluator = new ChainScriptLineEvaluator(config.getMaxThreadNum());
		} else {
			IStreamsProcessor streamsProcessor;
			streamsProcessor = config.getMultiThreads() ? new ThreadStreamsProcessor(config.getMaxThreadNum())
					: new SimpleStreamsProcessor();

			lineEvaluator = new SimpleScriptLineEvaluator(streamsProcessor);
		}

		PipeCommandBuilder pipeCommandBuilder;
		if (config.getUtilProvider() != null) {
			pipeCommandBuilder = new PipeCommandBuilder(config.getUtilProvider());
		} else {
			pipeCommandBuilder = new PipeCommandBuilder();
		}
		sentenceBuilder.setPipeCommandBuilder(pipeCommandBuilder);
		sentenceBuilder.setVariableStorage(variableStorage);
	}

	public void evaluate(String script) throws PipeException {

		String[] scriptLines = script.split("\n");

		evaluate(scriptLines);

	}

	public void evaluate(String[] scriptLines) throws
			PipeException {

		for (String string : scriptLines) {
			if (ScriptUtils.isExecutableScript(string)) {
				evaluateLine(string, variableStorage);
			}
		}
	}


	private void evaluateLine(String scriptLine, VariableStorage variableStorage) throws PipeException {
		String dereferencedScriptLine = dereferencer.dereference(scriptLine,
				variableStorage);
		ScriptSentence sentence = sentenceBuilder.build(dereferencedScriptLine);
		lineEvaluator.evaluate(sentence, variableStorage);
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
