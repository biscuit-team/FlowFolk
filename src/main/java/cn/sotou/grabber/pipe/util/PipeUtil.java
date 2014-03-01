package cn.sotou.grabber.pipe.util;

import java.io.InputStream;

import cn.sotou.grabber.pipe.exception.PipeUtilException;

public interface PipeUtil {
	InputStream[] process(InputStream inputStream, String... args)
			throws PipeUtilException, Exception;
}
