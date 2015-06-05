package writer;

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
			InputWriterChocoTest.class,
			InputWriterGlpkTest.class
			
		};

		final TestSuite suite = new TestSuite(classesTest);

		return suite;
	}
}
