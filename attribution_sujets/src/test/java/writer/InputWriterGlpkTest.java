package writer;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import models.solver.reader.NotFoundSolutionException;
import models.solver.reader.ReaderException;
import models.solver.reader.SolutionReaderChoco;
import models.solver.writer.InputWriterChoco;
import models.solver.writer.InputWriterGlpk;
import models.solver.writer.WriterException;

import org.junit.Before;
import org.junit.Test;

public class InputWriterGlpkTest {

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
		
		System.out.println(data.getPersons().get(19).getChoices().size());
		this.adaptorGlpk = new AdaptorGlpkImpl(data);
		
		assertNotNull("Erreur lors de la preparation", this.adaptorGlpk);
		
		this.path = "src" + File.separator + "test"
				+ File.separator + "resources" +  File.separator + "inputGlpk.txt";
	}
	
	@Test
	public void testGetNbPersons() throws FileFormatException 
	{
		try{
			InputWriterGlpk.write(this.path, this.adaptorGlpk);
			
			String[] cmd = { "C:\\Users\\lulu2_000\\Desktop\\glpk-4.55\\w64\\glpsol", "-m", "C:\\Users\\lulu2_000\\Desktop\\intersemestre-modele.mod", "-d", "C:\\Users\\lulu2_000\\Desktop\\inputGlpk.txt", "-o", "C:\\Users\\lulu2_000\\Desktop\\test5.txt", "-w", "C:\\Users\\lulu2_000\\Desktop\\test6.txt" };
			
			//glpk-4.55/w64/glpsol.exe -m ipipip-modele-binomes.mod -d inputGlpk.txt -o test.txt -w test2.txt
			Process p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
			
			System.out.println(p.exitValue());
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		catch(IOException e){
			fail(e.getMessage());
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
