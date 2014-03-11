package cn.sotou.tuningfork.interpreter;

import cn.sotou.tuningfork.util.provider.PipeUtilProvider;

/**
 * Created with IntelliJ IDEA.
 * User: shigong
 * Date: 14-3-3
 * Time: 上午10:19
 * To change this template use File | Settings | File Templates.
 */
public class InterpreterConfig {

	private boolean multiThreads = false;

	private int maxThreadNum = 5;

	private PipeUtilProvider utilProvider;

	public boolean getMultiThreads() {
		return multiThreads;
	}

	public void setMultiThreads(boolean multiThreads) {
		this.multiThreads = multiThreads;
	}

	public int getMaxThreadNum() {
		return maxThreadNum;
	}

	public void setMaxThreadNum(int maxThreadNum) {
		this.maxThreadNum = maxThreadNum;
	}

	public PipeUtilProvider getUtilProvider() {
		return utilProvider;
	}

	public void setUtilProvider(PipeUtilProvider utilProvider) {
		this.utilProvider = utilProvider;
	}
}
