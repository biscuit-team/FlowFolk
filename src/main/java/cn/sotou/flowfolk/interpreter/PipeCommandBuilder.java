package cn.sotou.flowfolk.interpreter;

import org.apache.commons.lang.ArrayUtils;

import cn.sotou.flowfolk.exception.NoPipeUtilFoundException;
import cn.sotou.flowfolk.exception.ScriptGrammarException;
import cn.sotou.flowfolk.util.provider.PipeUtilProvider;
import cn.sotou.flowfolk.util.PipeUtil;

public class PipeCommandBuilder {

	private final String ARGS_SPLIT = "\\s+";

	private PipeUtilProvider pipeUtilProvider;

	public PipeCommandBuilder() {
		pipeUtilProvider = new PipeUtilProvider();
	}

	public PipeCommandBuilder(PipeUtilProvider pipeUtilProvider) {
		setPipeUtilProvider(pipeUtilProvider);
	}

	public PipeCommand build(String cmdString) throws ScriptGrammarException,
			NoPipeUtilFoundException {
		String args[] = splitArguments(cmdString);

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

	private String[] splitArguments(String cmdString) {
		//TODO support escape blank characters and quotation
		return cmdString.split(ARGS_SPLIT);
	}

	public void setPipeUtilProvider(PipeUtilProvider pipeUtilProvider) {
		this.pipeUtilProvider = pipeUtilProvider;
	}
}
