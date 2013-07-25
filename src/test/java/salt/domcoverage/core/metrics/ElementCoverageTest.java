package salt.domcoverage.core.metrics;

import static org.junit.Assert.*;

import org.junit.Test;

public class ElementCoverageTest {

	@Test
	public void testElements() {
		ElementCoverage ec = new ElementCoverage();
		ec.getCoverage("Coverage");
		
		double cov =  (double) 1/271;//(double)(element.getElements().size()) /(double) allelementsinDom;
		//System.out.println(cov);
	}
	

}
