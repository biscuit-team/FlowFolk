package cn.sotou.tuningfork.interpreter.parallel;

import cn.sotou.tuningfork.exception.PipeException;
import cn.sotou.tuningfork.interpreter.ScriptLineEvaluator;
import cn.sotou.tuningfork.interpreter.VariableStorage;
import cn.sotou.tuningfork.interpreter.grammar.ScriptSentence;

/**
 * Created by shigong on 14-3-11.
 */
//balance performance and memory usage
//use parallel computing
public class ChianScriptLineEvaluator implements ScriptLineEvaluator {
	@Override
	public void evaluate(ScriptSentence sentence, VariableStorage variableStorage) throws PipeException {

	}
}
