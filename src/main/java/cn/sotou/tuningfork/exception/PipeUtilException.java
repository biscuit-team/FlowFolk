package cn.sotou.tuningfork.exception;

@SuppressWarnings("serial")
public class PipeUtilException extends PipeException {

	private Exception nestedException;

	private String message;

	public PipeUtilException() {
		super();
	}

	public PipeUtilException(Exception nestedException) {
		super();
		this.nestedException = nestedException;

	}

	public PipeUtilException(String message) {
		super();
		this.message = message;
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

	@Override
	public String toString() {
		return String.format("%s, nested exception is %", getMessage(), getNestedException().getMessage());
	}
}
