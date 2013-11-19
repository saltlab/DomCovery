package salt.domcoverage.core.metrics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import salt.domcoverage.core.TestConstantVars;
import salt.domcoverage.core.utils.ConstantVars;

public class ClickableElementsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		String testLocationFolder = TestConstantVars.add_story_assert;

		ClickableElements.findClickableElements(testLocationFolder);
		System.out.println("done");

	}

}
