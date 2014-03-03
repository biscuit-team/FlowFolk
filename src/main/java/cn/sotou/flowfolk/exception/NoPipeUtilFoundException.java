package cn.sotou.flowfolk.exception;

@SuppressWarnings("serial")
public class NoPipeUtilFoundException extends Exception {
	public NoPipeUtilFoundException(String utilName) {
		super(utilName);
	}
}
