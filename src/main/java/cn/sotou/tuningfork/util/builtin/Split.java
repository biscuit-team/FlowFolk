package cn.sotou.tuningfork.util.builtin;

import cn.sotou.tuningfork.exception.PipeUtilException;
import cn.sotou.tuningfork.util.BasePipeUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Split extends BasePipeUtil {

	public InputStream[] process(InputStream inputStream, String... args)
			throws PipeUtilException {
		List<InputStream> streams = new LinkedList<InputStream>();

		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

		return null;
	}

}
