package cn.sotou.tuningfork.interpreter.parallel;

import cn.sotou.tuningfork.exception.PipeException;
import cn.sotou.tuningfork.exception.PipeUtilException;
import cn.sotou.tuningfork.interpreter.ScriptLineEvaluator;
import cn.sotou.tuningfork.interpreter.VariableStorage;
import cn.sotou.tuningfork.interpreter.grammar.ScriptSentence;

import java.io.IOException;
import java.util.concurrent.Executors;

/**
 * Created by shigong on 14-3-11.
 */
//balance performance and memory usage
//use parallel computing
public class ChainScriptLineEvaluator implements ScriptLineEvaluator {

	private final int maxThread;

	public ChainScriptLineEvaluator(int maxThread) {

		this.maxThread = maxThread;
	}

	@Override
	public void evaluate(ScriptSentence sentence, VariableStorage variableStorage) throws PipeException {
		ComputingNodeTree tree = new ComputingNodeTree(Executors.newFixedThreadPool(maxThread));
		IComputingNode[] rootNodes = new IComputingNode[sentence.getStartStreams().length];
		for (int i = 0; i < rootNodes.length; i++) {
			rootNodes[i] = new CommandComputingNode(sentence.getStartStreams()[i],
					sentence.getPipeUtilCmdChain().getFirstNode(), tree);
		}
		tree.setRootNodes(rootNodes);
		tree.execute();
		try {
			tree.waitAllFinish();
			variableStorage.addStreamsAsVariable(sentence.getLeftVar(), tree.getOutputs());
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new PipeUtilException("interrupted when evaluating", e);
		} catch (IOException e) {
			throw new PipeUtilException(e.getMessage(), e);
		}
	}
}
