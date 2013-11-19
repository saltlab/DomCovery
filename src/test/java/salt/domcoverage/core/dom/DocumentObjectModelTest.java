package salt.domcoverage.core.dom;

import org.junit.Test;

public class DocumentObjectModelTest {

	@Test
	public void test() {
		// getdom
		String str = "<title> title </title> ";
		str = " <html>  <head>  </head> <body><div>tittt</div><p></p><br><input type=radio>intkkkkkk</input><a href=url></a></body> </html>";
		DocumentObjectModel dom = new DocumentObjectModel(str);
		int all = dom.getAllElementsSize();

		System.out.println("all elements : " + all);
		// count numbers
	}
}
