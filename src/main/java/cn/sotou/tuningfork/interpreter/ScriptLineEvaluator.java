package cn.sotou.tuningfork.interpreter;

import cn.sotou.tuningfork.exception.PipeException;
import cn.sotou.tuningfork.interpreter.grammar.ScriptSentence;

/**
 * Created by shigong on 14-3-11.
 */
public interface ScriptLineEvaluator {
	void evaluate(ScriptSentence sentence, VariableStorage variableStorage) throws PipeException;
}
