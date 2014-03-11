package cn.sotou.tuningfork.util.provider;

import java.util.LinkedList;
import java.util.List;

import cn.sotou.tuningfork.util.PipeUtil;

public class PipeUtilProvider {

	private List<IUtilProvider> providers = new LinkedList<IUtilProvider>();

	public PipeUtilProvider() {
		providers.add(new BuiltinUtilProvider());
	}

	public PipeUtil get(String name) {
		for (IUtilProvider provider : providers) {
			PipeUtil util = provider.get(name);
			if (util != null) {
				return util;
			}
		}
		return null;
	}

	public void setUtilProviders(List<IUtilProvider> providers) {
		this.providers = providers;
	}
}
