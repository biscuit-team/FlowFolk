package cn.sotou.flowfolk.util.provider;

import cn.sotou.flowfolk.util.PipeUtil;
import groovy.lang.GroovyClassLoader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by gongshw on 14-3-2.
 */
public class GroovyUtilProvider implements IUtilProvider {

	private String[] groovyUtilsDirectories = new String[]{"/util"};

	@Override
	public PipeUtil get(String name) {
		GroovyClassLoader loader = new GroovyClassLoader();
		InputStream assemblerSourceStream;
		assemblerSourceStream = getClass().getResourceAsStream(String.format("%s/%s.groovy",
				this.getGroovyUtilsDirectories()[0], name));

		if (assemblerSourceStream == null) {
			return null;
		}

		Class utilClass;

		try {
			utilClass = loader.parseClass(IOUtils.toString(assemblerSourceStream));
			return (PipeUtil) utilClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String[] getGroovyUtilsDirectories() {
		return groovyUtilsDirectories;
	}

	public void setGroovyUtilsDirectories(String[] groovyUtilsDirectories) {
		this.groovyUtilsDirectories = groovyUtilsDirectories;
	}
}
