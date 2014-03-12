package cn.sotou.tuningfork.interpreter.parallel;

import cn.sotou.tuningfork.util.chain.CommandNode;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.InputStream;

/**
 * Created by shigong on 14-3-12.
 */
public class CommandComputingNode implements IComputingNode {

	//private InputStream[] outputs;

	private static final Logger logger = Logger.getLogger(CommandComputingNode.class);
	private final InputStream input;
	private final CommandNode commandNode;
	private final ComputingNodeTree tree;

	public CommandComputingNode(InputStream input, CommandNode commandNode, ComputingNodeTree tree) {
		this.input = input;
		this.commandNode = commandNode;
		this.tree = tree;
	}

	@Override
	public void nextNode(InputStream[] result) {

		CommandNode next = commandNode.getNext();

		for (InputStream out : result) {
			CommandComputingNode node = new CommandComputingNode(out, next, tree);
			tree.execute(node);
		}
	}

	@Override
	public void run() {

		if (commandNode != null) {
			try {
				logger.info(String.format("task start run : %s", commandNode.getCommand().toString()));
				InputStream[] outputs = commandNode.getCommand().execute(input);
				IOUtils.closeQuietly(input);
				logger.info(String.format("task done : %s", commandNode.getCommand()));
				this.nextNode(outputs);
			} catch (Exception e) {
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
		} else {
			tree.addOutputs(new InputStream[]{input});
		}
		tree.releaseOne();

	}
}
