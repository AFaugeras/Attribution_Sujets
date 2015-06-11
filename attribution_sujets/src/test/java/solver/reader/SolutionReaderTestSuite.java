package solver.reader;

import junit.framework.Test;
import junit.framework.TestSuite;

public class SolutionReaderTestSuite {
	public static Test suite() 
	{
		final Class<?>[] classesTest = {
			SolutionReaderChocoImplTest.class,
			SolutionReaderGlpkImplTest.class
		};

		final TestSuite suite = new TestSuite(classesTest);

		return suite;
	}
}
