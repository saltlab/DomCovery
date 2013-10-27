package salt.domcoverage.core.crawljax;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import salt.domcoverage.core.utils.ConstantVars;

public class InFileStateFlowGraphTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		InFileStateFlowGraph sfg = new InFileStateFlowGraph();
		sfg.print(ConstantVars.EXPLORATIONFILE, ConstantVars.ESTIMATIONFILE);
	}

}
