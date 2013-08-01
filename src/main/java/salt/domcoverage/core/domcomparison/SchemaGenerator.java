package salt.domcoverage.core.domcomparison;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.inst2xsd.Inst2Xsd;
import org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

public class SchemaGenerator {

	public static void main(String[] args) {
		try {
			SchemaGenerator generator = new SchemaGenerator();

			File xml1 = new File("src/test/resources/test-data.xml");
			File xml2 = new File("src/test/resources/test-data2.xml");

			SchemaDocument schemaDocument = generator.generateSchema(xml1, xml2);
			String schema = generator.getSchema(schemaDocument);

			System.out.println(schema);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSchema(SchemaDocument schemaDocument) throws IOException {
		StringWriter writer = new StringWriter();
		schemaDocument.save(writer, new XmlOptions().setSavePrettyPrint());
		writer.close();

		return writer.toString();
	}

	public SchemaDocument generateSchema(File... files) throws XmlException, IOException {
		XmlObject[] xmlInstances = new XmlObject[files.length];

		for (int i = 0; i < files.length; i++) {
			xmlInstances[i] = XmlObject.Factory.parse(files[i]);
		}

		return inst2xsd(xmlInstances);
	}

	public SchemaDocument generateSchema(InputStream... inputStreams) throws XmlException,
	        IOException {

		XmlObject[] xmlInstances = new XmlObject[inputStreams.length];

		for (int i = 0; i < inputStreams.length; i++) {
			xmlInstances[i] = XmlObject.Factory.parse(inputStreams[i]);
		}

		return inst2xsd(xmlInstances);
	}

	public SchemaDocument generateSchema(String... strs) throws XmlException, IOException {

		XmlObject[] xmlInstances = new XmlObject[strs.length];

		for (int i = 0; i < strs.length; i++) {

			xmlInstances[i] = XmlObject.Factory.parse(strs[i]);
		}

		return inst2xsd(xmlInstances);
	}

	private SchemaDocument inst2xsd(XmlObject[] xmlInstances) throws IOException {
		Inst2XsdOptions inst2XsdOptions = new Inst2XsdOptions();
		// if (design == null || design == XMLSchemaDesign.VENETIAN_BLIND) {
		// inst2XsdOptions.setDesign(Inst2XsdOptions.DESIGN_VENETIAN_BLIND);
		// } else if (design == XMLSchemaDesign.RUSSIAN_DOLL) {
		inst2XsdOptions.setDesign(Inst2XsdOptions.DESIGN_RUSSIAN_DOLL);
		/*
		 * } else if (design == XMLSchemaDesign.SALAMI_SLICE) {
		 * inst2XsdOptions.setDesign(Inst2XsdOptions.DESIGN_SALAMI_SLICE); }
		 */

		SchemaDocument[] schemaDocuments = Inst2Xsd.inst2xsd(xmlInstances, inst2XsdOptions);

		if (schemaDocuments != null && schemaDocuments.length > 0) {
			return schemaDocuments[0];
		}

		return null;
	}
}