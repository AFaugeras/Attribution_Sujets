package solver.adaptor;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AdaptorTestSuite extends TestSuite {

	public static Test suite() 
	{
		final Class<?>[] classesTest = {
			AdaptorChocoTest.class,
			AdaptorGlpkTest.class
		};

		final TestSuite suite = new TestSuite(classesTest);

		return suite;
	}
}