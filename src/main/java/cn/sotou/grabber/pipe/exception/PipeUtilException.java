package cn.sotou.grabber.pipe.exception;

@SuppressWarnings("serial")
public class PipeUtilException extends RuntimeException {

	private Exception nestedException;

	private String message;

	public PipeUtilException() {

	}

	public PipeUtilException(String message, Exception nestedException) {
		super();
		this.message = message;
		this.nestedException = nestedException;
	}

	public Exception getNestedException() {
		return nestedException;
	}

	public void setNestedException(Exception nestedException) {
		this.nestedException = nestedException;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
