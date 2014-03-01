package cn.sotou.grabber.pipe.interpreter;

import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;

import java.util.Arrays;

public class PipeInterpreterTest extends TestCase {


	private static final String TEST_FILE_PATH = "/grab.properties";

	public void testEvaluateString() throws Exception {

		System.out.println(this.getClass().getResource("/"));

		String script = IOUtils.toString(PipeInterpreterTest.class.getResourceAsStream(TEST_FILE_PATH));
		PipeInterpreter interpreter = new PipeInterpreter();
		interpreter.evaluate(script);

		System.out.println(interpreter.getVariable("pages")[0]);

		System.out
				.println(Arrays.toString(interpreter.getVariable("listUrls")));
		System.out
				.println(Arrays.toString(interpreter.getVariable("jsonBodys")));
	}
}
