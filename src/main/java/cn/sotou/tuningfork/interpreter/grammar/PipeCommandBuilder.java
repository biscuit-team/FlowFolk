package cn.sotou.tuningfork.interpreter.grammar;

import org.apache.commons.lang.ArrayUtils;

import cn.sotou.tuningfork.exception.NoPipeUtilFoundException;
import cn.sotou.tuningfork.exception.ScriptGrammarException;
import cn.sotou.tuningfork.util.provider.PipeUtilProvider;
import cn.sotou.tuningfork.util.PipeUtil;

public class PipeCommandBuilder {

	private static final String ARGS_SPLIT = PipeConstant.ARGS_SPLIT;

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
		return EscapeUtils.decode(cmdString.split(ARGS_SPLIT));
	}

	public void setPipeUtilProvider(PipeUtilProvider pipeUtilProvider) {
		this.pipeUtilProvider = pipeUtilProvider;
	}
}
