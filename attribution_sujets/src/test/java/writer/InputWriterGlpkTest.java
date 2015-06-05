package writer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import models.bean.Constraints;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import models.exception.fileformatexception.FileFormatException;
import models.interfaces.I_Answer;
import models.parser.BeanMatcher;
import models.parser.answer.ParserCsvAnswer;
import models.parser.helper.CsvHelper;
import models.parser.subject.ParserCsvSubject;
import models.parser.user.ParserCsvUserList;
import models.solver.adaptor.AdaptorGlpk;
import models.solver.adaptor.AdaptorGlpkImpl;
import models.solver.writer.InputWriterGlpk;
import models.solver.writer.WriterException;

import org.junit.Before;
import org.junit.Test;

public class InputWriterGlpkTest extends TestCase{

	private AdaptorGlpk adaptorGlpk;
	
	private String path;
	
	@Before
	public void setUp() throws Exception{	
		Model data = this.generateModel();
		
		List<Long> costs = new ArrayList<Long>();
		
		costs.add(1L);
		costs.add(5L);
		costs.add(25L);
		costs.add(1000L);
		costs.add(5000L);
		costs.add(60000L);
		costs.add(1200000L);
		
		data.getConstraint().setWeights(costs);
		
		this.adaptorGlpk = new AdaptorGlpkImpl(data);
		
		assertNotNull("Erreur lors de la preparation", this.adaptorGlpk);
		
		this.path = "src" + File.separator + "test"
				+ File.separator + "resources" +  File.separator + "inputGlpk.txt";
	}
	
	@Test
	public void testwrite() throws FileFormatException 
	{
		try{
			InputWriterGlpk.write(this.path, this.adaptorGlpk);
			
		}
		catch(WriterException e){
			fail("Echec ecriture");
		}		
	}
	
	private Model generateModel() throws IOException,FileFormatException{
		Model ret = null;
		
		File f = new File("");
		f = CsvHelper.getRessource(f.getAbsolutePath()  + File.separator + "src"
				+ File.separator + "test"+ File.separator+ "resources" + File.separator + "data"
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
		Constraints c = new Constraints(6,6,0,0);
		List<Person> persons = parser.getUserList();
		List<Subject> subjects = subject.getSubjectList();
		
		try {
			new BeanMatcher(persons, listanswer, subjects, c).match();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ret = new Model(c, parser.getUserList(), subject.getSubjectList());
		
		return ret;
	}
}
