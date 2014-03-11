package cn.sotou.tuningfork.interpreter;

import cn.sotou.tuningfork.exception.PipeException;
import cn.sotou.tuningfork.interpreter.grammar.PipeCommand;
import cn.sotou.tuningfork.interpreter.grammar.ScriptSentence;
import cn.sotou.tuningfork.interpreter.task.IStreamsProcessor;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shigong on 14-3-11.
 */
public class SimpleScriptLineEvaluator implements ScriptLineEvaluator {
	private IStreamsProcessor streamsProcessor;

	public SimpleScriptLineEvaluator(IStreamsProcessor streamsProcessor) {
		this.streamsProcessor = streamsProcessor;
	}

	@Override
	public void evaluate(ScriptSentence sentence, VariableStorage variableStorage) throws PipeException {

		InputStream inputs[] = sentence.getStartStreams();

		for (PipeCommand command : sentence.getPipeUtilCmdChain()) {

			inputs = streamsProcessor.process(inputs, command);

		}
		try {
			variableStorage.addStreamsAsVariable(sentence.getLeftVar(), inputs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
