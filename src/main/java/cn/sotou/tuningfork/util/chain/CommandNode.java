package cn.sotou.tuningfork.util.chain;

import cn.sotou.tuningfork.interpreter.grammar.PipeCommand;

/**
 * Created by shigong on 14-3-11.
 */
public class CommandNode {
	private PipeCommand cmd;
	private CommandNode next;

	public CommandNode(PipeCommand cmd, CommandNode next) {
		this.cmd = cmd;
		this.next = next;
	}

	public PipeCommand getUtil() {
		return cmd;
	}

	public CommandNode getNext() {
		return next;
	}
}
