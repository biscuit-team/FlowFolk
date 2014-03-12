package cn.sotou.tuningfork.interpreter.task;

import cn.sotou.tuningfork.interpreter.grammar.PipeCommand;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 * User: shigong
 * Date: 14-3-3
 * Time: 上午11:05
 * To change this template use File | Settings | File Templates.
 */
public class StreamProcessTask implements Runnable {

	private static final Logger logger = Logger.getLogger(StreamProcessTask.class);

	private final InputStream input;

	private final PipeCommand command;

	private InputStream[] outputs;

	public StreamProcessTask(InputStream input, PipeCommand command) {
		this.input = input;
		this.command = command;
	}

	@Override
	public void run() {
		try {

			logger.info(String.format("task start run : %s", command.toString()));
			outputs = command.execute(input);
			IOUtils.closeQuietly(input);
			logger.info(String.format("task done : %s", command));
		} catch (Exception e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
	}

	public InputStream[] getOutputs() {
		return outputs;
	}

	public void setOutputs(InputStream[] outputs) {
		this.outputs = outputs;
	}
}
