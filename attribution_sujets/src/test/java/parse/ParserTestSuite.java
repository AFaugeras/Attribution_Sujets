package parse;

import junit.framework.Test;
import junit.framework.TestSuite;
import parse.answer.ParseCsvAnswerTest;
import parse.helper.CsvHelperTest;
import parse.subject.ParserCsvSubjectTest;
import parse.user.ParserUserTest;

public class ParserTestSuite extends TestSuite {

	public static Test suite() 
	{
		final Class<?>[] classesTest = {
			CsvHelperTest.class,
			ParseCsvAnswerTest.class,
			ParserCsvSubjectTest.class,
			ParserUserTest.class,
			ParserFormatTest.class,
			BeanMatcherTest.class
		};

		final TestSuite suite = new TestSuite(classesTest);

		return suite;
	}
	
}
