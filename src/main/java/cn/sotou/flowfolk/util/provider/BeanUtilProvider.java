package cn.sotou.flowfolk.util.provider;

import cn.sotou.flowfolk.util.PipeUtil;

import java.util.Map;

/**
 * Created by gongshw on 14-3-2.
 */
public class BeanUtilProvider implements IUtilProvider {

	private Map<String, PipeUtil> utilMap;

	@Override
	public PipeUtil get(String name) {
		if (utilMap == null) {
			return null;
		} else {
			return utilMap.get(name);
		}
	}

	public Map<String, PipeUtil> getUtilMap() {
		return utilMap;
	}

	public void setUtilMap(Map<String, PipeUtil> utilMap) {
		this.utilMap = utilMap;
	}
}
