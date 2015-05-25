package writer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import models.adaptor.AdaptorChoco;
import models.writer.OutputWriterChoco;
import models.writer.WriterException;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OutputWriterChocoTest {

	private AdaptorChoco adaptorChocoMock;
	
	@Before
	public void setUp() throws Exception{
		this.adaptorChocoMock = EasyMock.createMock(AdaptorChoco.class);
		assertNotNull("précondition", this.adaptorChocoMock);
	}
	
	@After
	public void tearDown() throws Exception {
		File output = new File("output.txt");
		output.delete();
	}
	
	@Test
	public void testGetNbPersons() 
	{
		
		EasyMock.expect(this.adaptorChocoMock.getNbPersons()).andReturn(new StringBuilder("4"));
		EasyMock.expect(this.adaptorChocoMock.getNbSubjects()).andReturn(new StringBuilder("2"));
		EasyMock.expect(this.adaptorChocoMock.getSizeRange()).andReturn(new StringBuilder("1\t3\n0\t4"));
		EasyMock.expect(this.adaptorChocoMock.getCardRange()).andReturn(new StringBuilder("0\t2\n0\t2"));
		EasyMock.expect(this.adaptorChocoMock.getMinimumAssignedSubject()).andReturn(new StringBuilder("0"));
		EasyMock.expect(this.adaptorChocoMock.getRepartitionCost()).andReturn(new StringBuilder("4\t1\t5\t25\t1000"));
		EasyMock.expect(this.adaptorChocoMock.getChoices()).andReturn(new StringBuilder("clinqu14\t1\t2\nafauge14\t2\t1\nnhebra14\t1\t1\nlsinqu14\t2\t1"));
		EasyMock.expect(this.adaptorChocoMock.getRejects()).andReturn(new StringBuilder("0\n0\n0\n0"));
		EasyMock.expect(this.adaptorChocoMock.getMultiplicity()).andReturn(new StringBuilder("0"));
		
		EasyMock.replay(this.adaptorChocoMock);
		
		try{
			OutputWriterChoco.write("output.txt", this.adaptorChocoMock);
		}
		catch(WriterException e){
			fail("Echec ecriture");
		}
		
		EasyMock.verify(this.adaptorChocoMock);
	}
}
