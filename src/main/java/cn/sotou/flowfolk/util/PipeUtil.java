package cn.sotou.flowfolk.util;

import java.io.InputStream;

import cn.sotou.flowfolk.exception.PipeUtilException;

public interface PipeUtil {
	InputStream[] process(InputStream inputStream, String... args)
			throws Exception;
}
