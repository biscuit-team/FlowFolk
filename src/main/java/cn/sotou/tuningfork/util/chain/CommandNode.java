package cn.sotou.tuningfork.util.chain;

import cn.sotou.tuningfork.interpreter.grammar.PipeCommand;

/**
 * Created by shigong on 14-3-11.
 */
public class CommandNode {
	private final PipeCommand cmd;
	private final CommandNode next;

	public CommandNode(PipeCommand cmd, CommandNode next) {
		this.cmd = cmd;
		this.next = next;
	}

	public PipeCommand getCommand() {
		return cmd;
	}

	public CommandNode getNext() {
		return next;
	}
}
