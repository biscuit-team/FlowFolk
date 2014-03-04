package cn.sotou.flowfolk.interpreter.task;

import cn.sotou.flowfolk.interpreter.grammar.PipeCommand;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by gongshw on 14-3-3.
 */
public class SimpleStreamsProcessor implements IStreamsProcessor{

	private static Logger logger = Logger.getLogger(SimpleStreamsProcessor.class);

	@Override
	public InputStream[] process(InputStream[] inputs, PipeCommand command) {

		List<InputStream> results = new LinkedList<InputStream>();
		for (InputStream inputStream : inputs) {
			InputStream[] aResultInputs = new InputStream[0];
			try {
				logger.info(String.format("cmd run: %s", command.toString()));
				aResultInputs = command.execute(inputStream);
				logger.info(String.format("cmd: %s donw", command));
			} catch (Exception e) {
				e.printStackTrace();
			}
			results.addAll(Arrays.asList(aResultInputs));
			IOUtils.closeQuietly(inputStream);
		}

		return results.toArray(new InputStream[results.size()]);
	}
}
