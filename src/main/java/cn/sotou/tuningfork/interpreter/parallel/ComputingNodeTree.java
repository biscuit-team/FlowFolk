package cn.sotou.tuningfork.interpreter.parallel;

import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by shigong on 14-3-11.
 */
public class ComputingNodeTree {

	private IComputingNode[] rootNodes;

	private final Executor executor;

	private CountLock lock = new CountLock();

	private List<InputStream> outputs = new LinkedList<InputStream>();

	public ComputingNodeTree(IComputingNode[] rootNodes, Executor executor) {
		this.rootNodes = rootNodes;
		this.executor = executor;
	}

	public ComputingNodeTree(Executor executor) {
		this(null, executor);
	}

	public void execute(IComputingNode node) {
		this.bindOne();
		executor.execute(node);
	}

	public void execute() {

		for (IComputingNode node : rootNodes) {
			this.execute(node);
		}

	}


	public void waitAllFinish() throws InterruptedException {
		lock.waitAllFinish();
	}

	public void bindOne() {
		lock.bindOne();
	}

	public void releaseOne() {
		lock.releaseOne();
	}

	public InputStream[] getOutputs() {
		return outputs.toArray(new InputStream[outputs.size()]);
	}

	public void addOutputs(InputStream[] someOutputs) {
		synchronized (outputs) {
			this.outputs.addAll(Arrays.asList(someOutputs));
		}

	}

	public IComputingNode[] getRootNodes() {
		return rootNodes;
	}

	public void setRootNodes(IComputingNode rootNodes[]) {
		this.rootNodes = rootNodes;
	}

	private class CountLock {
		private int count = 0;


		public void waitAllFinish() throws InterruptedException {
			synchronized (this) {
				this.wait();
			}
		}

		public void bindOne() {
			synchronized (this) {
				count++;
			}
		}

		public void releaseOne() {
			synchronized (this) {
				count--;
				if (count == 0) {
					this.notify();
				}
			}
		}

	}

}
