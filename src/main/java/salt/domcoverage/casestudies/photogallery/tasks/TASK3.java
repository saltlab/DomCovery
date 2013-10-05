package salt.domcoverage.casestudies.photogallery.tasks;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import salt.domcoverage.core.RoundTripDOMCoverage;
import salt.domcoverage.core.TestConstantVars;
import salt.domcoverage.core.utils.ConstantVars;

public class TASK3 {

	String outputFolder = "/Users/mehdimir/Desktop/Dropbox/UBC-research/DOM-coverage-documents/TASKS/TASK3/";
	String instrumentedTestFolder = System.getProperty("user.dir") + "/src/main/java/salt/domcoverage/casestudies/photogallery/instrumentedtask3";

	@Test
	public void execute() {
		// run this
		RoundTripDOMCoverage rtdc = new RoundTripDOMCoverage();
		rtdc.execute(instrumentedTestFolder, outputFolder);

	}
}