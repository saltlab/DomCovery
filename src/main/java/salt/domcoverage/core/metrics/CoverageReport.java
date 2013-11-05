package salt.domcoverage.core.metrics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import salt.domcoverage.core.utils.ConstantVars;

public class CoverageReport {
	private final VelocityEngine ve;
	private File coveragestatistics;

	public static void main(String[] args) {
		CoverageReport coverageReport = new CoverageReport();
		coverageReport.addStateCoverage(10, 35);

	}

	public CoverageReport() {

		String outputDir = ConstantVars.CRAWLOVERVIEW;
		coveragestatistics = new File(outputDir, "coveragestatistics.html");

		ve = new VelocityEngine();
		configureVelocity();

	}

	public void addStateCoverage(int statesCovered, int statesAll) {

		VelocityContext context = new VelocityContext();
		double domStateCoverage = (double) statesCovered / statesAll;
		context.put("stats", new Statistics(String.valueOf(domStateCoverage)));

		writeFile(context, coveragestatistics, "coveragestatistics.html");
	}

	private void writeFile(VelocityContext context, File outFile, String template) {
		try {
			Template templatee = ve.getTemplate(template);

			FileWriter writer = new FileWriter(outFile);
			templatee.merge(context, writer);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void configureVelocity() {
		ve.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.NullLogChute");
		ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
	}

}
