package cn.sotou.flowfolk.exception;

@SuppressWarnings("serial")
public class NoPipeUtilFoundException extends PipeException {
	public NoPipeUtilFoundException(String utilName) {
		super(utilName);
	}
}
