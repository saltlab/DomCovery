package salt.domcoverage.core.dom;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import salt.domcoverage.core.utils.ConstantVars;

public class DOMScreenShotTest {

	@Test
	public void test() {
		List<String> domStateCoverage = new ArrayList<String>();
		domStateCoverage.add("25.html");
		DOMScreenShot domScreenShot = new DOMScreenShot();
		List<String> list = new ArrayList<String>();
		for (String domStateCoveredFileName : domStateCoverage) {
			domScreenShot.takeScreenshot(ConstantVars.MERGEDLOCATION + domStateCoveredFileName, "");
		}

	}
}
