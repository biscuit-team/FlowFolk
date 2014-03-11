package util

import cn.sotou.tuningfork.util.BasePipeUtil

/**
 * Created by shigong on 14-3-4.
 */

/**
 * a test util to test whether interpreter splist cmd and args correctly
 */
class DebugTool extends BasePipeUtil {

	private int maxOutputLeng = 50;

	@Override
	InputStream[] process(InputStream inputStream, String... args) throws Exception {
		String debugInfo = String.format("===========================\n" +
				"input:%s,\nargs:%s\n" +
				"===========================\n", formatInput(inputStream), Arrays.toString(args));
		return wrapStreams(toInputStream(debugInfo));
	}

	private String formatInput(InputStream inputStream) {
		String inputStr = readerFromStream(inputStream);
		int length = inputStr.length();
		if (length > this.maxOutputLeng) {
			String.format("%s...%s", inputStr.substring(0, maxOutputLeng / 2), inputStr.substring(length - maxOutputLeng / 2));
		} else {
			return inputStr;
		}
	}
}
