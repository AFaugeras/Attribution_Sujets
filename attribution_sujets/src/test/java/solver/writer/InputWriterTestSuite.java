package solver.writer;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * classe regroupant les test des writer
 *
 */
public class InputWriterTestSuite extends TestSuite{
	public static Test suite() 
	{
		final Class<?>[] classesTest = {
			InputWriterChocoImplTest.class,
			InputWriterGlpkImplTest.class
			
		};

		final TestSuite suite = new TestSuite(classesTest);

		return suite;
	}
}
