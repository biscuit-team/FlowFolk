package cn.sotou.flowfolk.interpreter;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import cn.sotou.flowfolk.exception.PipeUtilException;

public abstract class PipeSupport {


	protected static String readerFromStream(InputStream inputStream) {
		try {
			return IOUtils.toString(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new PipeUtilException("fail to read from stream", e);
		}
	}

	protected static InputStream toInputStream(String input) {
		return IOUtils.toInputStream(input);
	}

	protected static void closeStream(Closeable closeable) {
		IOUtils.closeQuietly(closeable);
	}

	protected static InputStream[] wrapStreams(InputStream... streams) {
		return streams;
	}
}
