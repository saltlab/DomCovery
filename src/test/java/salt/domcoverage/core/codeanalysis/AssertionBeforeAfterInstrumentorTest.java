package salt.domcoverage.core.codeanalysis;

import japa.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class AssertionBeforeAfterInstrumentorTest {
	AssertionBeforeAfterInstrumentor ait;
	private boolean writeBack = true;

	@Before
	public void setUp() {
		ait = new AssertionBeforeAfterInstrumentor();

	}

	@Test
	public void testInstrumentMethodCall() throws FileNotFoundException, ParseException, IOException {
		// testthis(TestSourceLocations.TestUrl);
	}

	@Test
	public void testInstrumentMethodCallforStopPlay() throws FileNotFoundException, ParseException, IOException {
		ait.instrumentTestSuite(TestSourceLocations.ClasrolineTestFolder);
	}

}
