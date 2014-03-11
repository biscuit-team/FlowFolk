package cn.sotou.tuningfork.util.builtin;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.sotou.tuningfork.exception.PipeUtilException;
import cn.sotou.tuningfork.util.BasePipeUtil;

//generate a series of strings with a production express
public class StringGenerator extends BasePipeUtil {

	private static final Pattern VALIDATE_PATTERN = Pattern
			.compile("\\{(\\d+)-(\\d+)\\}");

	public InputStream[] process(InputStream inputStream, String... args)
			throws PipeUtilException {
		String input = readerFromStream(inputStream);
		Matcher matcher = VALIDATE_PATTERN.matcher(input);

		if (matcher.find()) {
			String entery = matcher.group(0);
			int start = Integer.parseInt(matcher.group(1));
			int end = Integer.parseInt(matcher.group(2));
			InputStream[] inputStreams = new InputStream[end - start + 1];

			for (int i = start; i <= end; i++) {
				inputStreams[i - start] = toInputStream(input.replaceAll(entery
						.replace("{", "\\{").replaceAll("}", "\\}"), i + ""));
			}
			return inputStreams;
		} else {
			return wrapStreams(toInputStream(input));

		}

	}

}
