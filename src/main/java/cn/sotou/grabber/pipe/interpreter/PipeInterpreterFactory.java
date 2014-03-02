package cn.sotou.grabber.pipe.interpreter;

/**
 * Created by gongshw on 14-3-2.
 */
public class PipeInterpreterFactory {
	private String springConfigurationFilePath;

	public PipeInterpreterFactory() {
		springConfigurationFilePath = "applicationContext.xml";
	}

	public PipeInterpreterFactory(String springConfigurationFilePath) {
		this.springConfigurationFilePath = springConfigurationFilePath;
	}

	public static PipeInterpreter getPipeInterpreter() {
		return new PipeInterpreter();
	}
}
