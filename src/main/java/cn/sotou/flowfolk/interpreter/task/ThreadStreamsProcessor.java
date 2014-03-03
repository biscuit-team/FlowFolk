package cn.sotou.flowfolk.interpreter.task;

import cn.sotou.flowfolk.interpreter.PipeCommand;
import cn.sotou.flowfolk.interpreter.task.StreamProcessTask;

import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: shigong
 * Date: 14-3-3
 * Time: 上午10:39
 * To change this template use File | Settings | File Templates.
 */
public class ThreadStreamsProcessor implements IStreamsProcessor {

	private int maxThreadNum;

	public ThreadStreamsProcessor(int maxThreadNum) {
		this.maxThreadNum = maxThreadNum;
	}

	public InputStream[] process(InputStream[] inputs, PipeCommand command) {
		StreamProcessTaskSet taskSet = new StreamProcessTaskSet(inputs, command);
		ExecutorService executor = Executors.newFixedThreadPool(maxThreadNum);
		for (StreamProcessTask task : taskSet) {
			executor.execute(task);
		}

		executor.shutdown();
		try {
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
			return taskSet.getOutputsFromTasks();
		} catch (InterruptedException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		return inputs;
	}

}
