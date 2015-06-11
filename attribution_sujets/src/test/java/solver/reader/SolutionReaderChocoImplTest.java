package solver.reader;

import java.io.File;
import java.util.List;

import junit.framework.TestCase;
import models.bean.Constraints;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import models.exception.fileformatexception.FileException;
import models.exception.fileformatexception.FileFormatException;
import models.interfaces.I_Answer;
import models.parser.BeanMatcher;
import models.parser.answer.ParserCsvAnswer;
import models.parser.helper.CsvHelper;
import models.parser.subject.ParserCsvSubject;
import models.parser.user.ParserCsvUserList;
import models.solver.reader.NotFoundSolutionException;
import models.solver.reader.ReaderException;
import models.solver.reader.SolutionReaderChoco;
import models.solver.reader.SolutionReaderChocoImpl;

import org.junit.Before;
import org.junit.Test;

/**
 * Ceci n'est pas un test unitaire. Il correspond d'avantage à un test d'intégration.
 */
public class SolutionReaderChocoImplTest extends TestCase {

	private Model data;
	
	private SolutionReaderChoco src; 
	
	@SuppressWarnings("unused")
	private String pathNoSolution;
	
	private String pathSolution;
	
	@Before
	public void setUp() throws Exception{	
		
		this.data = this.generateModel();
		assertNotNull(this.data);
		
		this.src = new SolutionReaderChocoImpl(this.data);
		assertNotNull("Constructeur", this.src);
		
		this.pathNoSolution = "src" + File.separator + "test"+ File.separator 
				+ "resources" + File.separator + "NoSolutionChoco";
		
		this.pathSolution = "src" + File.separator + "test"+ File.separator 
				+ "resources" + File.separator + "SolutionChoco";
	}
	
	@Test
	public void testReadSolutionFound() throws FileFormatException 
	{
		try{
			
			List<Person> persons = this.data.getPersons();
		
			this.src.read(this.pathSolution);
			
			for(Person pers : persons){
				if(pers.getAssigned() == null){
					fail("Au moins une personne ne possède pas de sujet");
				}
			}

		}
		catch(ReaderException e){
			fail(e.getMessage());	
		}
		catch(NotFoundSolutionException e){
			fail(e.getMessage());	
		}			
	}
	
//	@Test
//	public void testReadNoSolution() throws FileFormatException 
//	{
//		try{
//		
//			this.src.read(this.pathNoSolution);
//			
//			fail("Exception attendue");
//
//		}
//		catch(ReaderException e){
//			fail(e.getMessage());	
//		}
//		catch(NotFoundSolutionException e){
//		}	
//	}
	
	
	private Model generateModel() throws FileException{
		Model ret = null;
		
		File f = new File("");
		f = CsvHelper.getRessource(f.getAbsolutePath()  + File.separator + "src"
				+ File.separator + "test"+ File.separator+ "resources" + File.separator+ "data"
				+ File.separator+ "Choix_des_sujets_2015.csv");
		ParserCsvAnswer answer = new ParserCsvAnswer();
		answer.parseAnswer(f);
		
		File g = new File("");
		g = CsvHelper.getRessource(g.getAbsolutePath() + File.separator + "src"
				+ File.separator + "test"+ File.separator+ "resources" + File.separator+ "data"
				+ File.separator+ "liste_sujet.csv");
		ParserCsvSubject subject = new ParserCsvSubject();
		subject.ParseSubjectList(g);

		
		File h = new File("");
		h = CsvHelper.getRessource(h.getAbsolutePath() + File.separator + "src"
				+ File.separator + "test"+ File.separator+ "resources" + File.separator+ "data"
				+ File.separator+ "liste_eleves.csv");
		ParserCsvUserList parser = new ParserCsvUserList();
		parser.ParseUserList(h);
		
		
		List<I_Answer> listanswer = answer.getCleanedData();
		Constraints c = new Constraints(2, 2, 2, 2);
		List<Person> persons = parser.getUserList();
		List<Subject> subjects = subject.getSubjectList();
		
		new BeanMatcher(persons, listanswer, subjects, c);
		
		ret = new Model(c, parser.getUserList(), subject.getSubjectList());
		
		return ret;
	}
}
