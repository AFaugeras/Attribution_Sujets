package writer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.TestCase;
import models.exception.fileformatexception.FileFormatException;
import models.solver.adaptor.AdaptorGlpk;
import models.solver.adaptor.AdaptorGlpkImpl;
import models.solver.writer.InputWriterGlpk;
import models.solver.writer.WriterException;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InputWriterGlpkTest extends TestCase{

	private AdaptorGlpk adaptorGlpkMock;
	
	private String path;
	
	@Before
	public void setUp() throws Exception{	
		
		this.adaptorGlpkMock = EasyMock.createMock(AdaptorGlpkImpl.class);
		assertNotNull("Erreur lors de la preparation", this.adaptorGlpkMock);
		
		this.path = "src" + File.separator + "test"
				+ File.separator + "resources" +  File.separator + "inputGlpk.txt";
	}
	
	@After
	public void tearDown() throws Exception{
		File output = new File(this.path);
		output.delete();
	}
	
	@Test
	public void testwrite() throws FileFormatException 
	{
		EasyMock.expect(this.adaptorGlpkMock.getSubjects()).andReturn(new StringBuilder("set projets := \tp0 \tp1\t;"));
		EasyMock.expect(this.adaptorGlpkMock.getPersons()).andReturn(new StringBuilder("set eleves := \te0 \te1 \te2 \te3\t;"));
		EasyMock.expect(this.adaptorGlpkMock.getMinCardSubjects()).andReturn(new StringBuilder("param nbMinGpes :=\tp0 \t0\tp1 \t0\t;"));
		EasyMock.expect(this.adaptorGlpkMock.getMaxCardSubjects()).andReturn(new StringBuilder("param nbMaxGpes :=\tp0 \t2\tp1 \t2\t;"));
		EasyMock.expect(this.adaptorGlpkMock.getMultiplicity()).andReturn(new StringBuilder("param tailleEquipe:=\t0\t;"));
		EasyMock.expect(this.adaptorGlpkMock.getMinSizeSubjects()).andReturn(new StringBuilder("param nMin :=\tp0 \t0\tp1 \t1\t;"));
		EasyMock.expect(this.adaptorGlpkMock.getMaxSizeSubjects()).andReturn(new StringBuilder("param nMax :=\tp0 \t2\tp1 \t3\t;"));
		EasyMock.expect(this.adaptorGlpkMock.getMinimumAssignedSubject()).andReturn(new StringBuilder("param nbMiniSujets :=\t0\t;"));
		EasyMock.expect(this.adaptorGlpkMock.getChoices()).andReturn(new StringBuilder("param c :\te0\te1\te2\te3\t\t:=\n\tp0\t1\t5\n\tp1\t5\t1\n\tp2\t1\t5\n\tp3\t1\t1\n\t;"));
		
		EasyMock.replay(this.adaptorGlpkMock);
		
		try{
			InputWriterGlpk.write(this.path, this.adaptorGlpkMock);
		}
		catch(WriterException e){
			fail("Echec ecriture");
		}
		
		this.checkFile(this.path);		
		
		EasyMock.verify(this.adaptorGlpkMock);
	}
	
public void checkFile(String filename){
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			assertEquals("data;", br.readLine());
			assertEquals("", br.readLine());
			assertEquals("set projets := \tp0 \tp1\t;", br.readLine());
			assertEquals("set eleves := \te0 \te1 \te2 \te3\t;", br.readLine());
			assertEquals("", br.readLine());
			assertEquals("param nbMinGpes :=\tp0 \t0\tp1 \t0\t;", br.readLine());
			assertEquals("param nbMaxGpes :=\tp0 \t2\tp1 \t2\t;", br.readLine());
			assertEquals("param tailleEquipe:=\t0\t;", br.readLine());
			assertEquals("param nMin :=\tp0 \t0\tp1 \t1\t;", br.readLine());
			assertEquals("param nMax :=\tp0 \t2\tp1 \t3\t;", br.readLine());
			assertEquals("param nbMiniSujets :=\t0\t;", br.readLine());
			assertEquals("param c :\te0\te1\te2\te3\t\t:=", br.readLine());
			assertEquals("\tp0\t1\t5", br.readLine());
			assertEquals("\tp1\t5\t1", br.readLine());
			assertEquals("\tp2\t1\t5", br.readLine());
			assertEquals("\tp3\t1\t1", br.readLine());
			assertEquals("\t;", br.readLine());
			assertEquals("", br.readLine());
			assertEquals("", br.readLine());
			assertEquals("end;", br.readLine());
			
			br.close();
			fr.close();
		} catch (IOException e) {
			fail("Fichier inexistant");
		}
	}
}
