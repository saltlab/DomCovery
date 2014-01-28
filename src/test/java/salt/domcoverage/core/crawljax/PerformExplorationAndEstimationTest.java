package salt.domcoverage.core.crawljax;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import salt.domcoverage.core.utils.ConstantVars;

public class PerformExplorationAndEstimationTest {

	@Test
	public void testEstimation() throws IOException {

		PerformExplorationAndEstimation pee = new PerformExplorationAndEstimation();

		String estimate = pee.estimate(ConstantVars.EXPLORATIONFILE, ConstantVars.ESTIMATIONFILE);

		FileUtils.writeStringToFile(new File("temestimate-photgalerythreshold3-1min-30min.csv"), estimate);

	}
}
