package cn.sotou.tuningfork.util.builtin;

import cn.sotou.tuningfork.exception.PipeUtilException;
import cn.sotou.tuningfork.util.BasePipeUtil;
import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.io.StringWriter;

public class XPath extends BasePipeUtil {

	public InputStream[] process(InputStream input, String... args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db;
		try {
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(input);
			String xpathStr = args[0];
			javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xPath.compile(xpathStr);

			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			InputStream[] inputStreams = new InputStream[nodes.getLength()];
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				inputStreams[i] = IOUtils.toInputStream(nodeToString(node));
			}

			return inputStreams;
		} catch (Exception e) {
			throw new PipeUtilException(e);
		}
	}

	private String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		try {
			Transformer t = TransformerFactory.newInstance().newTransformer();
			t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
			throw new PipeUtilException(te);
		}
		return sw.toString();
	}

}
