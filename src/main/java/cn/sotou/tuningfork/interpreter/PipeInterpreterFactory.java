package cn.sotou.tuningfork.interpreter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by gongshw on 14-3-2.
 */
public class PipeInterpreterFactory {

	private final String springConfigurationFilePath;

	private final ApplicationContext applicationContext;

	public PipeInterpreterFactory() {
		springConfigurationFilePath = "applicationContext.xml";
		applicationContext = new ClassPathXmlApplicationContext(springConfigurationFilePath);
	}

	public PipeInterpreterFactory(String springConfigurationFilePath) {
		this.springConfigurationFilePath = springConfigurationFilePath;
		applicationContext = new ClassPathXmlApplicationContext(springConfigurationFilePath);
	}

	public PipeInterpreter getPipeInterpreter() {

		return applicationContext.getBean("pipeInterpreter", PipeInterpreter.class);
	}
}
