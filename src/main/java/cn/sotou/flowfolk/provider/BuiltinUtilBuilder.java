package cn.sotou.flowfolk.provider;

import cn.sotou.flowfolk.exception.NoPipeUtilFoundException;
import cn.sotou.flowfolk.util.PipeUtil;

public class BuiltinUtilBuilder {

	private static final String BUILTIN_UTIL_PACKAGE = "cn.sotou.flowfolk.util.builtin";

	public PipeUtil build(Class<? extends PipeUtil> clazz) {
		try {
			// TODO use groovy to initial this PipeUtil
			return clazz.newInstance();
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
		Class<? extends PipeUtil> clazz;
		try {
			clazz = (Class<? extends PipeUtil>) Class
					.forName(String.format("%s.%s", BUILTIN_UTIL_PACKAGE, utilName));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NoPipeUtilFoundException(utilName);
		}
		return build(clazz);
	}
}
