package cn.sotou.tuningfork.interpreter.parallel;

import java.io.InputStream;

/**
 * Created by shigong on 14-3-11.
 */
public interface IComputingNode extends Runnable {
	void nextNode(InputStream[] result);
}
