package solver.reader;

import junit.framework.Test;
import junit.framework.TestSuite;
import parse.ParserTestSuite;
import stats.StatsTest;

public class SolutionReaderTestSuite {
	public static Test suite() 
	{
		final Class<?>[] classesTest = {
			SolutionReaderChocoTest.class,
			SolutionReaderGlpkTest.class
		};

		final TestSuite suite = new TestSuite(classesTest);

		return suite;
	}
}
