package cn.sotou.flowfolk.interpreter;

import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;

import java.util.Arrays;

public class PipeInterpreterTest extends TestCase {


	private static final String TEST_FILE_PATH = "/grab.properties";

	public void testEvaluateString() throws Exception {

		String script = IOUtils.toString(PipeInterpreterTest.class.getResourceAsStream(TEST_FILE_PATH));
		PipeInterpreter interpreter = new PipeInterpreterFactory().getPipeInterpreter();
		interpreter.evaluate(script);

		System.out.println(interpreter.getVariable("pages")[0]);

		System.out
				.println(Arrays.toString(interpreter.getVariable("listUrls")));
		System.out
				.println(Arrays.toString(interpreter.getVariable("jsonBodys")));
	}
}
