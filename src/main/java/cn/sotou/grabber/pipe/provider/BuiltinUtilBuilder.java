package cn.sotou.grabber.pipe.provider;

import cn.sotou.grabber.pipe.exception.NoPipeUtilFoundException;
import cn.sotou.grabber.pipe.util.PipeUtil;

public class BuiltinUtilBuilder {

	private static final String BUILTIN_UTIL_PACKAGE = "cn.sotou.grabber.pipe.util.builtin";

	public PipeUtil build(Class<? extends PipeUtil> clazz) {
		try {
			PipeUtil util = clazz.newInstance();
			// TODO use groovy to initial this PipeUtil
			return util;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public PipeUtil build(String utilName) throws NoPipeUtilFoundException {
		Class<? extends PipeUtil> clazz = null;
		try {
			clazz = (Class<? extends PipeUtil>) Class
					.forName(BUILTIN_UTIL_PACKAGE + '.' + utilName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoPipeUtilFoundException(utilName);
		}
		return build(clazz);
	}
}
