package salt.domcoverage.core.dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.custommonkey.xmlunit.Difference;
import org.w3c.dom.Document;

import salt.domcoverage.core.code2instrument.ElementData;
import salt.domcoverage.core.utils.DOMUtility;

import com.crawljax.util.DomUtils;

public class DomComparatorUsingSchema extends DomComparator {

	public double differences(ElementData ed1, ElementData ed2) {
		String dom1 = ed1.getDomData();
		String dom2 = ed2.getDomData();
		List<Difference> differences = new ArrayList<Difference>();

		// dom1 = DOMUtility.normalizeDOM(dom1, false);
		// dom2 = DOMUtility.normalizeDOM(dom2, false);

		SchemaGenerator generator = new SchemaGenerator();

		SchemaDocument schemaDocument;
		int max = 0, doc1size = 1, doc2size = 1;
		try {
			dom1 = DOMUtility.normalizeDOM(dom1, false);
			dom2 = DOMUtility.normalizeDOM(dom2, false);

			// remove style and script tags
			dom1 = DOMUtility.removeTagName(dom1, "script");
			dom1 = DOMUtility.removeTagName(dom1, "style");
			dom2 = DOMUtility.removeTagName(dom2, "script");
			dom2 = DOMUtility.removeTagName(dom2, "style");
			// create a valid xhtml
			// FileUtils.writeStringToFile(new File("1-afterSrtip"), dom1);
			dom1 = XHTMLTransformer.getDocumentToString(DomUtils.asDocument(dom1));
			dom2 = XHTMLTransformer.getDocumentToString(DomUtils.asDocument(dom2));
			// FileUtils.writeStringToFile(new File("2-xhtml"), dom2);
			SchemaDocument schemaDom1 = generator.generateSchema(dom1);
			SchemaDocument schemaDom2 = generator.generateSchema(dom2);

			String schemaDom1String = generator.getSchema(schemaDom1);
			String schemaDom2String = generator.getSchema(schemaDom2);

			// schemaDom1String = DOMUtility.normalizeDOM(schemaDom1String,
			// false);
			// schemaDom2String = DOMUtility.normalizeDOM(schemaDom2String,
			// false);

			// FileUtils.writeStringToFile(new File("1"), schemaDom1String);
			// FileUtils.writeStringToFile(new File("2"), schemaDom2String);
			// FileUtils.writeStringToFile(new File("dom1"), dom1);
			// FileUtils.writeStringToFile(new File("dom2"), dom2);

			Document dom1doc = DomUtils.asDocument(schemaDom1String);
			Document dom2doc = DomUtils.asDocument(schemaDom2String);

			DOMDiffer dc = new DOMDiffer(dom1doc, dom2doc);

			differences = dc.compare();
			if (differences == null)
				return Integer.MAX_VALUE;
			doc1size = dom1doc.getElementsByTagName("*").getLength();
			doc2size = dom2doc.getElementsByTagName("*").getLength();

			max = Math.max(doc1size, doc2size);
		} catch (XmlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Utils.printList(differences);

		// calculate score
		boolean contains = false;
		if (ed1.getElements() != null && ed1.getElements().size() != 0)
			contains = ed2.contains(ed1.getElements().get(0));
		double diffscore = getDiffScore(differences.size(), doc1size, doc2size, contains);

		System.out.println("size: " + diffscore);

		return (double) diffscore; // (doc1size + doc2size);// / max;
	}

	private double getDiffScore(int diffSize, int doc1size, int doc2size, boolean contains) {
		double power = 0;
		if (contains)
			power = 1;
		return diffSize / Math.pow(2, power);
		// double a = (double) (differences.size()) / (doc1size * doc2size);
		// double log = Math.log(a + 1);
		// double diffscore = Math.pow(2, power) * log;
		// return diffscore;
	}
	// private double getDiffScore(List<Difference> differences, int doc1size,
	// int doc2size, boolean contains) {
	// int power = 0;
	// if (contains)
	// power = 1;
	// double a = (double) (differences.size()) / (doc1size * doc2size);
	// double log = Math.log(a + 1);
	// double diffscore = Math.pow(2, power) * log;
	// return diffscore;
	// }
}
