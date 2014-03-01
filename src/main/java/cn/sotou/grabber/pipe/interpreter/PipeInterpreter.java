package cn.sotou.grabber.pipe.interpreter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import cn.sotou.grabber.pipe.exception.PipeUtilException;
import cn.sotou.grabber.pipe.util.PipeConstant;
import cn.sotou.grabber.pipe.util.PipeSupport;

public class PipeInterpreter extends PipeSupport {

	private ReferenceReplacer referenceReplacer = new ReferenceReplacer();

	private PipeCommandBuilder pipeCommandBuilder = new PipeCommandBuilder();

	private Map<String, String[]> varMap = new HashMap<String, String[]>();

	public void evaluate(String script) throws PipeUtilException, Exception {

		String[] scriptLines = script.split("\n");

		evaluate(scriptLines);

	}

	public void evaluate(String[] scriptLines) throws PipeUtilException,
			Exception {
		for (String string : scriptLines) {
			if (ScriptComment.isComment(string)) {
				continue;
			} else {
				evaluateLine(string, varMap);
			}
		}
	}

	private void evaluateLine(String scriptLine, Map<String, String[]> varMap)
			throws PipeUtilException, Exception {
		String dereferencedScriptLine = referenceReplacer.replace(scriptLine,
				varMap);
		ScriptSentence sentence = new ScriptSentence(dereferencedScriptLine);

		InputStream inputs[] = getStartStreams(sentence.getStartVar(), varMap);
		
		for (String pipeCmd : sentence.getPipeUtilCmdChain()) {

			List<InputStream> results = new LinkedList<InputStream>();
			PipeCommand command = pipeCommandBuilder.build(pipeCmd);

			for (InputStream inputStream : inputs) {
				InputStream[] aResultInputs = command.execute(inputStream);
				results.addAll(Arrays.asList(aResultInputs));
				IOUtils.closeQuietly(inputStream);
			}

			inputs = results.toArray(new InputStream[results.size()]);

		}
		addVarToMap(sentence.getLeftVar(), inputs, varMap);
	}

	private InputStream[] getStartStreams(String startName,
			Map<String, String[]> varMap) {
		if (startName.startsWith(PipeConstant.REF_CHAR)) {
			String[] inputStrings = varMap.get(startName.substring(1));
			InputStream[] inputStreams = new InputStream[inputStrings.length];

			for (int i = 0; i < inputStreams.length; i++) {
				inputStreams[i] = IOUtils.toInputStream(inputStrings[i]);

			}

			return inputStreams;
		} else {
			return new InputStream[] { IOUtils.toInputStream(startName) };
		}
	}

	private void addVarToMap(String leftName, InputStream[] inputStreams,
			Map<String, String[]> varMap) throws IOException {

		String[] inputStrings = new String[inputStreams.length];
		for (int i = 0; i < inputStrings.length; i++) {
			inputStrings[i] = IOUtils.toString(inputStreams[i]);
			IOUtils.closeQuietly(inputStreams[i]);
		}
		varMap.put(leftName, inputStrings);
	}

	public String[] getVariable(String name) {
		return varMap.get(name);
	}

	public Map<String, String[]> getResult() {
		return varMap;
	}
}
