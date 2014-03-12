package cn.sotou.tuningfork.util;

import java.io.InputStream;

import cn.sotou.tuningfork.exception.PipeUtilException;

public interface PipeUtil {

	//TODO package InputStream to PipeStream for more feature
	InputStream[] process(InputStream inputStream, String... args)
			throws PipeUtilException;
}
