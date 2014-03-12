package cn.sotou.tuningfork.interpreter.task;

import cn.sotou.tuningfork.interpreter.grammar.PipeCommand;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: shigong
 * Date: 14-3-3
 * Time: 上午11:25
 * To change this template use File | Settings | File Templates.
 */
public class StreamProcessTaskSet implements Iterable<StreamProcessTask> {
	private final List<StreamProcessTask> tasks;

	public StreamProcessTaskSet(InputStream[] inputs, PipeCommand command) {
		tasks = new LinkedList<StreamProcessTask>();

		for (InputStream input : inputs) {
			tasks.add(new StreamProcessTask(input, command));
		}
	}

	public InputStream[] getOutputsFromTasks() {
		List<InputStream> results = new LinkedList<InputStream>();

		for (StreamProcessTask task : tasks) {
			InputStream[] aResultInputs = task.getOutputs();
			results.addAll(Arrays.asList(aResultInputs));
		}

		return results.toArray(new InputStream[results.size()]);
	}

	@Override
	public Iterator<StreamProcessTask> iterator() {
		return tasks.iterator();
	}
}
