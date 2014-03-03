package cn.sotou.flowfolk.provider;

import cn.sotou.flowfolk.util.PipeUtil;

/**
 * Created by gongshw on 14-3-2.
 */
public class GroovyUtilProvider implements IUtilProvider {

	private String[] groovyUtilsDirectories;

	@Override
	public PipeUtil get(String name) {
		return null;
	}

	public String[] getGroovyUtilsDirectories() {
		return groovyUtilsDirectories;
	}

	public void setGroovyUtilsDirectories(String[] groovyUtilsDirectories) {
		this.groovyUtilsDirectories = groovyUtilsDirectories;
	}
}
