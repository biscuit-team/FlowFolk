package cn.sotou.tuningfork.interpreter;

import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;

import java.util.Arrays;
import java.util.Date;

public class PipeInterpreterTest extends TestCase {


	private static final String TEST_FILE_PATH = "/grab.properties";

	public void testEvaluateString() throws Exception {

		String script = IOUtils.toString(PipeInterpreterTest.class.getResourceAsStream(TEST_FILE_PATH));
		PipeInterpreter interpreter;
		InterpreterConfig config;

		interpreter = new PipeInterpreterFactory().getPipeInterpreter();
		config = interpreter.getConfig();
		config.setChainThreads(false);
		config.setMultiThreads(false);
		getTimeSpend(interpreter, script, "single thread");

		interpreter = new PipeInterpreterFactory().getPipeInterpreter();
		config = interpreter.getConfig();
		config.setChainThreads(false);
		config.setMultiThreads(true);
		config.setMaxThreadNum(10);
		getTimeSpend(interpreter, script, "multi thread 10");

		interpreter = new PipeInterpreterFactory().getPipeInterpreter();
		config = interpreter.getConfig();
		config.setChainThreads(false);
		config.setMultiThreads(true);
		config.setMaxThreadNum(20);
		getTimeSpend(interpreter, script, "multi thread 20");

		interpreter = new PipeInterpreterFactory().getPipeInterpreter();
		config = interpreter.getConfig();
		config.setChainThreads(true);
		config.setMultiThreads(false);
		config.setMaxThreadNum(10);
		getTimeSpend(interpreter, script, "chain thread 10");

		interpreter = new PipeInterpreterFactory().getPipeInterpreter();
		config = interpreter.getConfig();
		config.setChainThreads(true);
		config.setMultiThreads(false);
		config.setMaxThreadNum(20);
		getTimeSpend(interpreter, script, "chain thread 20");

	}

	private void getTimeSpend(PipeInterpreter interpreter, String script, String name) {
		long beginTime = System.nanoTime();
		interpreter.evaluate(script);
		long endTime = System.nanoTime();
		long costTime = (endTime - beginTime) / 1000000;
		System.out.println(String.format("execute: %s reults in %s ms", name,
				costTime));

	}

}
