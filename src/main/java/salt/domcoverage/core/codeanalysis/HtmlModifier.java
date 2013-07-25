package salt.domcoverage.core.codeanalysis;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HtmlModifier {

	public static void main(String[] args) {
		Document doc = null;
		try {
			doc = Jsoup.connect("http://watersmc.ece.ubc.ca:8888/phormer-photoGallery331/").get();
			// System.out.println("Document Before->\n"+doc);
			System.out.println();
			Elements links = doc.select("a");
			// can be done in this manner also
			// Elements changed_attributes = doc.select("class");
			// for(Element new_attribute: changed_attributes)
			// {
			//
			// new_attribute.attr("coverage", "true");
			// }

			links.attr("coverage", "true");
			System.out.println("Document After->\n" + doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
