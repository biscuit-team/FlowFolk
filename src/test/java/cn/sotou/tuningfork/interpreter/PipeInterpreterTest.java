package cn.sotou.tuningfork.interpreter;

import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;

public class PipeInterpreterTest extends TestCase {


	private static final String TEST_FILE_PATH = "/test.properties";

	public void testEvaluateString() throws Exception {

		String script = IOUtils.toString(PipeInterpreterTest.class.getResourceAsStream(TEST_FILE_PATH));
		PipeInterpreter interpreter = new PipeInterpreterFactory().getPipeInterpreter();
		interpreter.evaluate(script);

		//System.out.println(interpreter.getVariable("pages")[0]);

		//System.out
		//		.println(Arrays.toString(interpreter.getVariable("listUrls")));
		//System.out
		//		.println(Arrays.toString(interpreter.getVariable("jsonBodys")));


		//interpreter.evaluate("value=123|Repeat 5");
		System.out.println(interpreter.getVariable("value")[0]);

	}
}