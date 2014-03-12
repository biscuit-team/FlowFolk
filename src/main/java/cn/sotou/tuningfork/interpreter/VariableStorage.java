package cn.sotou.tuningfork.interpreter;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gongshw on 14-3-2.
 */
public class VariableStorage {

	private final Map<String, String[]> varMap = new HashMap<String, String[]>();

	public void addStreamsAsVariable(String variableName, InputStream[] inputStreams) throws IOException {
		String[] inputStrings = new String[inputStreams.length];
		for (int i = 0; i < inputStrings.length; i++) {
			assert inputStreams[i] != null;
			inputStrings[i] = IOUtils.toString(inputStreams[i]);
			IOUtils.closeQuietly(inputStreams[i]);
		}
		varMap.put(variableName, inputStrings);
	}

	public boolean hasVariable(String variableName) {
		return varMap.get(variableName) != null;
	}

	public InputStream[] getVariableAsSteams(String variableName) {
		String[] inputStrings = varMap.get(variableName.substring(1));
		InputStream[] inputStreams = new InputStream[inputStrings.length];

		for (int i = 0; i < inputStreams.length; i++) {
			inputStreams[i] = IOUtils.toInputStream(inputStrings[i]);

		}

		return inputStreams;
	}

	public String[] getVariable(String name) {
		return varMap.get(name);
	}

	public Map<String, String[]> getVariablesAsMap() {
		return varMap;
	}

}
