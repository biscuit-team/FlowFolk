package cn.sotou.flowfolk.interpreter;

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
}
