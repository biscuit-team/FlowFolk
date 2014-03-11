package cn.sotou.tuningfork.interpreter.task;

import cn.sotou.tuningfork.interpreter.grammar.PipeCommand;

import java.io.InputStream;

/**
 * Created by gongshw on 14-3-3.
 */
public interface IStreamsProcessor {
	public InputStream[] process(InputStream[] inputs, PipeCommand command);
}
