package cn.sotou.tuningfork.util.builtin;

import cn.sotou.tuningfork.exception.PipeUtilException;
import cn.sotou.tuningfork.util.BasePipeUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;


public class JsoupSelect extends BasePipeUtil {

	public InputStream[] process(InputStream input, String... args) {
		String selectString;
		if (args.length > 0) {
			selectString = args[0];
		} else {
			throw new PipeUtilException("no select string!");
		}
		String charsetName = args.length > 1 ? args[1] : "UTF-8";
		String baseUrl = args.length > 2 ? args[2] : "";
		try {
			Document doc = Jsoup.parse(input, charsetName, baseUrl);
			Elements eles = doc.select(selectString);
			List<InputStream> inputStreams = new LinkedList<InputStream>();
			for (Element element : eles) {
				inputStreams.add(toInputStream(element.outerHtml()));
			}
			return inputStreams.toArray(new InputStream[eles.size()]);
		} catch (IOException e) {
			e.printStackTrace();
			throw new PipeUtilException("parse html error", e);
		}
	}
}
