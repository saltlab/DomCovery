package salt.domcoverage.core.domcomparison;

import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;
import org.junit.Test;

public class SchemaGeneratorTest {

	@Test
	public void test() throws XmlException, IOException {
		SchemaGenerator generator = new SchemaGenerator();

		File xml1 = new File("src/test/resources/test-data.xml");
		File xml2 = new File("src/test/resources/test-data2.xml");

		SchemaDocument schemaDocument = generator.generateSchema(xml1, xml2);
		String schema = generator.getSchema(schemaDocument);

		System.out.println(schema);

	}

}
