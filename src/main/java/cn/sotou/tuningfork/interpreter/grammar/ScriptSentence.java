package cn.sotou.tuningfork.interpreter.grammar;

import java.io.InputStream;

import cn.sotou.tuningfork.util.chain.CommandChain;

public class ScriptSentence {

	private String leftVar;
	private InputStream[]  startStreams;
	private CommandChain pipeUtilCmdChain;

	public ScriptSentence() {
	}

	public String getLeftVar() {
		return leftVar;
	}

	public void setLeftVar(String leftVar) {
		this.leftVar = leftVar;
	}

	public CommandChain getPipeUtilCmdChain() {
		return pipeUtilCmdChain;
	}

	public void setPipeUtilCmdChain(CommandChain pipeUtilNameChain) {
		this.pipeUtilCmdChain = pipeUtilNameChain;
	}

	public InputStream[] getStartStreams() {
		return startStreams;
	}

	public void setStartStreams(InputStream[] startStreams) {
		this.startStreams = startStreams;
	}
}
