package solver.writer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;
import models.solver.adaptor.AdaptorChocoImpl;
import models.solver.writer.InputWriterChoco;
import models.solver.writer.InputWriterChocoImpl;
import models.solver.writer.WriterException;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputWriterChocoImplTest extends TestCase {

	private InputWriterChoco iwc;
			
	private AdaptorChocoImpl adaptorChocoMock;
	
	private String path;
	
	@Before
	public void setUp() throws Exception{	
		
		this.adaptorChocoMock = EasyMock.createMock(AdaptorChocoImpl.class);
		assertNotNull("précondition", this.adaptorChocoMock);
		
		this.iwc = new InputWriterChocoImpl(this.adaptorChocoMock);
		assertNotNull("Constructeur", this.iwc);
		
		this.path = "inputChoco.txt";
	}
	
	@After
	public void tearDown() throws Exception {
		File output = new File(this.path);
		output.delete();
	}
	
	@Test
	public void testwrite() 
	{
		
		EasyMock.expect(this.adaptorChocoMock.getNbPersons()).andReturn(new StringBuilder("4"));
		EasyMock.expect(this.adaptorChocoMock.getNbSubjects()).andReturn(new StringBuilder("2"));
		EasyMock.expect(this.adaptorChocoMock.getSizeRange()).andReturn(new StringBuilder("1\t3\n0\t4"));
		EasyMock.expect(this.adaptorChocoMock.getCardRange()).andReturn(new StringBuilder("0\t2\n0\t2"));
		EasyMock.expect(this.adaptorChocoMock.getMinimumAssignedSubject()).andReturn(new StringBuilder("0"));
		EasyMock.expect(this.adaptorChocoMock.getRepartitionCost()).andReturn(new StringBuilder("4\t1\t5\t25\t1000"));
		EasyMock.expect(this.adaptorChocoMock.getChoices()).andReturn(new StringBuilder("clinqu14\t1\t1\t2\nafauge14\t1\t2\t1\nnhebra14\t2\t1\t1\nlsinqu14\t1\t2\t1"));
		EasyMock.expect(this.adaptorChocoMock.getRejects()).andReturn(new StringBuilder("0\n0\n0\n0"));
		EasyMock.expect(this.adaptorChocoMock.getMultiplicity()).andReturn(new StringBuilder("0"));
		EasyMock.replay(this.adaptorChocoMock);
		
		try{
			this.iwc.write(this.path);
		}
		catch(WriterException e){
			fail("Echec ecriture");
		}
		
		this.checkFile(this.path);		
		
		EasyMock.verify(this.adaptorChocoMock);
	}
	
	public void checkFile(String filename){
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			assertEquals("/* nb eleves */", br.readLine());
			assertEquals("4", br.readLine());
			assertEquals("/* nb sujets */", br.readLine());
			assertEquals("2", br.readLine());
			assertEquals("/* pour chaque sujet de 1 a n, nb min de groupe puis nb max de groupes */", br.readLine());
			assertEquals("0\t2", br.readLine());
			assertEquals("0\t2", br.readLine());
			assertEquals("/* effectif min et effectif max des groupes pour chaque sujet */", br.readLine());
			assertEquals("1\t3", br.readLine());
			assertEquals("0\t4", br.readLine());
			assertEquals("/* nb min de sujets effectivement affectés */", br.readLine());
			assertEquals("0", br.readLine());
			assertEquals("/* nombre puis valeurs des pénalités (coûts des affectations) */", br.readLine());
			assertEquals("4\t1\t5\t25\t1000", br.readLine());
			assertEquals("/* nom prenom, nb choix, rang sujet 1.. Rang sujet n */", br.readLine());
			assertEquals("clinqu14\t1\t1\t2", br.readLine());
			assertEquals("afauge14\t1\t2\t1", br.readLine());
			assertEquals("nhebra14\t2\t1\t1", br.readLine());
			assertEquals("lsinqu14\t1\t2\t1", br.readLine());
			assertEquals("/* pour chaque eleve, nb puis liste des sujets refusés */", br.readLine());
			assertEquals("0", br.readLine());
			assertEquals("0", br.readLine());
			assertEquals("0", br.readLine());
			assertEquals("0", br.readLine());
			assertEquals("/* multiplicite des groupes */", br.readLine());
			assertEquals("0", br.readLine());
			
			br.close();
			fr.close();
		} catch (IOException e) {
			fail("Fichier inexistant");
		}
	}
}
