package cn.sotou.flowfolk.interpreter.task;

import cn.sotou.flowfolk.interpreter.PipeCommand;

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
	private List<StreamProcessTask> tasks;

	public StreamProcessTaskSet(InputStream[] inputs, PipeCommand command) {
		tasks = new LinkedList<StreamProcessTask>();

		for (int i = 0; i < inputs.length; i++) {
			tasks.add(new StreamProcessTask(inputs[i], command));
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
