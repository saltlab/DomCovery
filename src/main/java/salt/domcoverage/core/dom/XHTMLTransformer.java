package salt.domcoverage.core.dom;

import java.io.ByteArrayOutputStream;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class XHTMLTransformer {
	public static String getDocumentToString(Document dom) {
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();

			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			// TODO should be fixed to read doctype declaration
			// transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
			// "-//W3C//DTD XHTML 1.0 Strict//EN\" " +
			// "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd");

			DOMSource source = new DOMSource(dom);

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Result result = new StreamResult(out);
			transformer.transform(source, result);

			return out.toString();
		} catch (TransformerException e) {
			System.err.println("Error while converting the document to a byte array" + e);
		}
		return null;

	}

}
