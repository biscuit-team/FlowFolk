package cn.sotou.grabber.pipe.interpreter;

import org.apache.commons.lang.ArrayUtils;

import cn.sotou.grabber.pipe.exception.NoPipeUtilFoundException;
import cn.sotou.grabber.pipe.exception.ScriptGrammarException;
import cn.sotou.grabber.pipe.provider.PipeUtilProvider;
import cn.sotou.grabber.pipe.util.PipeUtil;

public class PipeCommandBuilder {

	private final String ARGS_SPLIT = "\\s+";

	private final PipeUtilProvider pipeUtilProvider = new PipeUtilProvider();

	public PipeCommand build(String cmdString) throws ScriptGrammarException,
			NoPipeUtilFoundException {
		String args[] = cmdString.split(ARGS_SPLIT);

		if (args.length > 0) {
			PipeUtil util = pipeUtilProvider.get(args[0]);
			if (util != null) {
				return new PipeCommand(util, (String[]) ArrayUtils.remove(args,
						0));
			} else {
				throw new NoPipeUtilFoundException(args[0]);
			}
		} else {
			throw new ScriptGrammarException(cmdString);
		}

	}

}
