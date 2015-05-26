package parse.subject;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import models.bean.Subject;
import models.factory.SubjectFactory;
import models.interfaces.I_Answer;
import models.parser.answer.ParserCsvAnswer;
import models.parser.helper.CsvHelper;
import models.parser.subject.ParserCsvSubject;

import org.junit.Test;

public class ParserCsvSubjectTest extends TestCase {

	@Test
	public void testParseCsvSubjectWithParametrage() throws IOException{
		File f = new File("");
		f = CsvHelper.getRessource(f.getAbsolutePath() + f.separator + "src"
				+ f.separator + "test"+ f.separator+ "resources"
				+ f.separator+ "liste_sujet.csv");
		ParserCsvSubject parser = new ParserCsvSubject();
		parser.ParseSubjectList(f);
		List<Subject> subject = parser.getSubjectList();
		Subject heavySubject = createSubject();
		assertEquals(subject.get(0).getId(),heavySubject.getId());
		assertEquals(subject.get(0).getLabel(),heavySubject.getLabel());
		assertEquals(subject.get(0).getMaxSize(),heavySubject.getMaxSize());
		assertEquals(subject.get(0).getMinSize(),heavySubject.getMinSize());
		assertEquals(subject.get(0).getCardMax(),heavySubject.getCardMax());
		assertEquals(subject.get(0).getCardMin(),heavySubject.getCardMin());
		assertEquals(subject.get(0).getMultiple(),heavySubject.getMultiple());
		
		
	}
	
	
	private Subject createSubject(){
		String[] data = {"Poker","2","2","2","2","2"};
		return SubjectFactory.createSubject(data, 1);
		
	}
}
