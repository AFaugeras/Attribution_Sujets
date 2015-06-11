package parse.subject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import models.bean.Subject;
import models.exception.fileformatexception.FileException;
import models.factory.SubjectFactory;
import models.parser.helper.CsvHelper;
import models.parser.subject.ParserCsvSubject;

import org.junit.Test;

public class ParserCsvSubjectTest extends TestCase {

	@Test
	public void testParseCsvSubjectWithParametrage() throws IOException{
		File f = new File("");
		f = CsvHelper.getRessource(f.getAbsolutePath() + File.separator + "src"
				+ File.separator + "test"+ File.separator+ "resources"
				+ File.separator+ "liste_sujet.csv");
		ParserCsvSubject parser = new ParserCsvSubject();
		try {
			parser.ParseSubjectList(f);
		} catch (FileException e) {
			fail("Probléme de format");
		}
		List<Subject> subject = parser.getSubjectList();
		Subject heavySubject = createSubject();
		assertEquals(subject.get(0).getId(),heavySubject.getId());
		assertEquals(subject.get(0).getLabel(),heavySubject.getLabel());
		assertEquals(subject.get(0).getMaxSize(),heavySubject.getMaxSize());
		assertEquals(subject.get(0).getMinSize(),heavySubject.getMinSize());
		assertEquals(subject.get(0).getMaxCard(),heavySubject.getMaxCard());
		assertEquals(subject.get(0).getMinCard(),heavySubject.getMinCard());
		
		
		
	}
	
	
	private Subject createSubject(){
		String[] data = {"1","Poker","2","2","2","2","2"};
		return SubjectFactory.createSubject(data, Integer.valueOf(data[0]));
		
	}
	
	@Test
	public void testParseCsvSubjectWithParametrageSave() throws FileException{
		File f = new File("");
		f = CsvHelper.getRessource(f.getAbsolutePath() + File.separator + "src" 
				+ File.separator + "test"+ File.separator+ "resources"
				+ File.separator+ "liste_sujet.csv");
		ParserCsvSubject parser = new ParserCsvSubject();
		try {
			parser.ParseSubjectList(f);
		} catch (FileException e) {
			fail("Probléme de format");
		}
		List<Subject> subject = parser.getSubjectList();
		
		@SuppressWarnings("unused")
		Subject heavySubject = createSubject();
		
		Subject.save(subject,new File("testcedric.csv"));
		
		
		
	}
}
