package cn.sotou.tuningfork.exception;

@SuppressWarnings("serial")
public class NoPipeUtilFoundException extends PipeException {
	public NoPipeUtilFoundException(String utilName) {
		super(utilName);
	}
}
