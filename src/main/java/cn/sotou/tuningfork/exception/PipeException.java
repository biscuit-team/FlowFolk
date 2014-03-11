package cn.sotou.tuningfork.exception;

/**
 * Created by shigong on 14-3-4.
 */
public abstract class PipeException extends RuntimeException {
	public PipeException() {
		super();
	}

	public PipeException(String msg) {
		super(msg);
	}
}
