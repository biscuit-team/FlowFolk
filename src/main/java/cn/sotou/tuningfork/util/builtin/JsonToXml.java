package cn.sotou.tuningfork.util.builtin;

import java.io.InputStream;

import org.json.JSONObject;
import org.json.XML;

import cn.sotou.tuningfork.util.BasePipeUtil;

/**
 * Converting JSON to XML
 *
 * @author shigong
 */
public class JsonToXml extends BasePipeUtil {

	public InputStream[] process(InputStream input, String... args) {
		// TODO Auto-generated method stub
		String text = readerFromStream(input);
		JSONObject json = new JSONObject(text);
		String xmlStr = XML.toString(json);
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version=\"1.0\"?>").append("<root>")
				.append(xmlStr).append("</root>");
		return wrapStreams(toInputStream(builder.toString()));
	}
}
