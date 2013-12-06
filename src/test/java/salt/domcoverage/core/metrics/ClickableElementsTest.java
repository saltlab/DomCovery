package salt.domcoverage.core.metrics;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import salt.domcoverage.core.TestConstantVars;
import salt.domcoverage.core.utils.ConstantVars;
import salt.domcoverage.core.utils.TestUtil;

public class ClickableElementsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String testLocationFolder = TestConstantVars.add_story_assert;
		List<String> allTests = TestUtil.getAllTests(testLocationFolder, ".java");

		ClickableElements.findClickableElements(allTests);
		System.out.println("done");

	}

}
