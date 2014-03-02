package cn.sotou.grabber.pipe.interpreter;

import cn.sotou.grabber.pipe.util.PipeConstant;
import cn.sotou.grabber.pipe.util.PipeSupport;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PipeInterpreter extends PipeSupport {

	private boolean multiThreads = false;

	private int maxThreadNum = 5;

	private VariableDereferencer dereferencer = new VariableDereferencer();

	private PipeCommandBuilder pipeCommandBuilder = new PipeCommandBuilder();

	//private Map<String, String[]> varMap = new HashMap<String, String[]>();

	private VariableStorage variableStorage = new VariableStorage();

	public void evaluate(String script) throws Exception {

		String[] scriptLines = script.split("\n");

		evaluate(scriptLines);

	}

	public void evaluate(String[] scriptLines) throws
			Exception {
		for (String string : scriptLines) {
			if (!ScriptComment.isComment(string)) {
				evaluateLine(string, variableStorage);
			}
		}
	}

	private void evaluateLine(String scriptLine, VariableStorage variableStorage)
			throws Exception {
		String dereferencedScriptLine = dereferencer.dereference(scriptLine,
				variableStorage);
		ScriptSentence sentence = new ScriptSentence(dereferencedScriptLine);

		InputStream inputs[] = getStartStreams(sentence.getStartVar(), variableStorage);

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
		variableStorage.addStreamsAsVariable(sentence.getLeftVar(), inputs);
	}

	private InputStream[] getStartStreams(String startName,
	                                      VariableStorage variableStorage) {
		if (startName.startsWith(PipeConstant.REF_CHAR)) {
			return variableStorage.getVariableAsSteams(startName);
		} else {
			return new InputStream[]{IOUtils.toInputStream(startName)};
		}
	}


	public String[] getVariable(String name) {
		return variableStorage.getVariablesAsMap().get(name);
	}

}
