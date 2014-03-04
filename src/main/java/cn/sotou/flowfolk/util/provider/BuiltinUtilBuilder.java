package cn.sotou.flowfolk.util.provider;

import cn.sotou.flowfolk.exception.NoPipeUtilFoundException;
import cn.sotou.flowfolk.util.PipeUtil;
import groovy.lang.GroovyClassLoader;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class BuiltinUtilBuilder {

	private String builtinUtilAssemblerDirectory = "/assembler";

	private static final String BUILTIN_UTIL_PACKAGE = "cn.sotou.flowfolk.util.builtin";

	public PipeUtil build(Class<? extends PipeUtil> clazz) {
		try {
			PipeUtil util = clazz.newInstance();
			assembleUtil(util);
			return util;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
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
			return null;
		}
		return build(clazz);
	}

	private PipeUtil assembleUtil(PipeUtil util) {
		IBuiltinUtilAssembler assembler = searchAssembler(util.getClass());
		if (assembler != null) {
			assembler.assemble(util);
		}
		return util;
	}

	private IBuiltinUtilAssembler searchAssembler(Class<? extends PipeUtil> clazz) {
		GroovyClassLoader loader = new GroovyClassLoader();
		InputStream assemblerSourceStream;
		assemblerSourceStream = getClass().getResourceAsStream(String.format("%s/%s.groovy",
				this.getBuiltinUtilAssemblerDirectory(), clazz.getSimpleName()));

		if (assemblerSourceStream == null) {
			return null;
		}

		Class assemblerClass;

		try {
			assemblerClass = loader.parseClass(IOUtils.toString(assemblerSourceStream));
			return (IBuiltinUtilAssembler) assemblerClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public String getBuiltinUtilAssemblerDirectory() {
		return builtinUtilAssemblerDirectory;
	}

	public void setBuiltinUtilAssemblerDirectory(String builtinUtilAssemblerDirectory) {
		this.builtinUtilAssemblerDirectory = builtinUtilAssemblerDirectory;
	}
}
