package cn.sotou.tuningfork.util;

import java.io.InputStream;

import cn.sotou.tuningfork.exception.PipeUtilException;

public interface PipeUtil {
	InputStream[] process(InputStream inputStream, String... args)
			throws PipeUtilException;
}
