package cn.sotou.grabber.pipe.interpreter;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;

import cn.sotou.grabber.pipe.exception.PipeUtilException;
import cn.sotou.grabber.pipe.util.PipeUtil;

public class PipeCommand {

	private PipeUtil util;

	private String args[];

	public PipeCommand(PipeUtil util, String[] args) {
		super();
		this.util = util;
		this.args = args;
	}

	public InputStream[] execute(InputStream input) throws PipeUtilException, Exception {
		if (this.isRegularCmd()) {
			return util.process(input, getArgs());
		} else {
			try {
				InputStream inputForUtil = IOUtils.toInputStream(getArgs()[0]);
				String[] argsForUtil = (String[]) ArrayUtils.clone(getArgs());
				int indexForInput = ArrayUtils.indexOf(argsForUtil, "%s");
				argsForUtil[indexForInput] = IOUtils.toString(input);
				argsForUtil = (String[]) ArrayUtils.remove(getArgs(), 0);
				return util.process(inputForUtil, argsForUtil);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new PipeUtilException("IOException", e);
			}
		}
	}

	public boolean isRegularCmd() {
		return !ArrayUtils.contains(getArgs(), "%s");
	}

	public PipeUtil getUtil() {
		return util;
	}

	public void setUtil(PipeUtil util) {
		this.util = util;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

}
