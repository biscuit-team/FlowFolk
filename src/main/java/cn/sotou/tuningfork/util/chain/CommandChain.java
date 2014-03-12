package cn.sotou.tuningfork.util.chain;

import cn.sotou.tuningfork.interpreter.grammar.PipeCommand;

import java.util.Iterator;

/**
 * Created by shigong on 14-3-11.
 */
public class CommandChain implements Iterable<PipeCommand> {

	private CommandNode firstNode;

	public CommandChain(PipeCommand[] cmds) {
		CommandNode lastNode = null;
		for (int i = cmds.length - 1; i >= 0; i--) {
			CommandNode nowNode = new CommandNode(cmds[i], lastNode);
			lastNode = nowNode;
		}
		firstNode = lastNode;
	}

	public CommandNode getFirstNode() {
		return firstNode;
	}

	public void setFirstNode(CommandNode firstNode) {
		this.firstNode = firstNode;
	}

	@Override
	public Iterator<PipeCommand> iterator() {
		CommandNode head = new CommandNode(null, firstNode);
		return new NodeIterator(head);
	}

	public class NodeIterator implements Iterator<PipeCommand> {

		private CommandNode node;

		public NodeIterator(CommandNode node) {
			this.node = node;
		}

		@Override
		public boolean hasNext() {
			return node.getNext() != null;
		}

		@Override
		public PipeCommand next() {
			node = node.getNext();
			return node.getCommand();
		}

		@Override
		public void remove() {
			throw new RuntimeException("cannot remove node when iterating");
		}
	}
}
