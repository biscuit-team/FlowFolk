package cn.sotou.tuningfork.util.builtin;

import cn.sotou.tuningfork.exception.PipeUtilException;
import cn.sotou.tuningfork.util.BasePipeUtil;

import java.io.InputStream;

/**
 * Created by shigong on 14-3-4.
 */
public class StringFormat extends BasePipeUtil {
	@Override
	public InputStream[] process(InputStream inputStream, String... args) {
		if (args.length > 0) {
			return wrapStreams(toInputStream(String.format(args[0], readerFromStream(inputStream))));
		} else {
			throw new PipeUtilException("parameter err");
		}
	}
}
