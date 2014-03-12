package cn.sotou.tuningfork.util.provider;

import java.util.HashMap;
import java.util.Map;

import cn.sotou.tuningfork.exception.NoPipeUtilFoundException;
import cn.sotou.tuningfork.util.PipeUtil;

public class BuiltinUtilProvider implements IUtilProvider {

	private final BuiltinUtilBuilder builtinUtilBuilder = new BuiltinUtilBuilder();

	private final Map<String, PipeUtil> utilMap = new HashMap<String, PipeUtil>();

	public PipeUtil get(String name) {
		PipeUtil util = utilMap.get(name);
		if (util != null) {
			return util;
		} else {
			try {
				util = builtinUtilBuilder.build(name);
				if (util != null) {
					utilMap.put(name, util);
				}
				return util;
			} catch (NoPipeUtilFoundException e) {
				return null;
			}
		}
	}
}
