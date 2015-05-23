package parse;

import junit.framework.Test;
import junit.framework.TestSuite;

public class ParserTestSuite extends TestSuite {

	public static Test suite() 
	{
		final Class<?>[] classesTest = {
			CsvHelperTest.class,
			ParseCsvAnswerTest.class
		};

		final TestSuite suite = new TestSuite(classesTest);

		return suite;
	}
	
}