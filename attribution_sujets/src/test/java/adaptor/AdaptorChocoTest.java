package adaptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import models.adaptor.AdaptorChoco;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AdaptorChocoTest {

	private Model modelMock;
	
	private AdaptorChoco ac;

	@Before
	public void setUp() throws Exception{
		this.modelMock = EasyMock.createMock(Model.class);
		assertNotNull("précondition", this.modelMock);
		
		this.ac = new AdaptorChoco(this.modelMock);
		assertNotNull("constructeur erreur", this.ac);

	}
	
	@Test
	public void testGetNbPersons() 
	{
		List<Person> listMock = EasyMock.createMock(List.class);
		
		EasyMock.expect(this.modelMock.getPersons()).andReturn(listMock);
		EasyMock.expect(listMock.size()).andReturn(100);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(listMock);
		
		assertEquals(this.ac.getNbPersons().toString(), new StringBuilder("100").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(listMock);
	}
	
	@Test
	public void testGetNbSubjects() 
	{
		List<Subject> listMock = EasyMock.createMock(List.class);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(listMock);
		EasyMock.expect(listMock.size()).andReturn(5);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(listMock);

		assertEquals(this.ac.getNbSubjects().toString(), new StringBuilder("5").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(listMock);
	}
	
	@Test
	public void testGetSizeRange() 
	{
		List<Subject> listMock = EasyMock.createMock(List.class);
		Subject subj = EasyMock.createMock(Subject.class);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(listMock);
		
		EasyMock.expect(listMock.size()).andReturn(2);	
		EasyMock.expect(listMock.get(0)).andReturn(subj);
		EasyMock.expect(subj.getMinSize()).andReturn(5);
		EasyMock.expect(subj.getMaxSize()).andReturn(10);
		
		EasyMock.expect(listMock.size()).andReturn(2);
		EasyMock.expect(listMock.get(1)).andReturn(subj);
		EasyMock.expect(subj.getMinSize()).andReturn(2);
		EasyMock.expect(subj.getMaxSize()).andReturn(10);
		
		EasyMock.expect(listMock.size()).andReturn(2);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(listMock);
		EasyMock.replay(subj);
		
		assertEquals(this.ac.getSizeRange().toString(), new StringBuilder("5\t10\n2\t10").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(listMock);
		EasyMock.verify(subj);
	}
	
	@Test
	public void testGetCardRange() 
	{
		List<Subject> listMock = EasyMock.createMock(List.class);
		Subject subj = EasyMock.createMock(Subject.class);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(listMock);
		
		EasyMock.expect(listMock.size()).andReturn(2);	
		EasyMock.expect(listMock.get(0)).andReturn(subj);
		EasyMock.expect(subj.getCardMin()).andReturn(0);
		EasyMock.expect(subj.getCardMax()).andReturn(2);
		
		EasyMock.expect(listMock.size()).andReturn(2);
		EasyMock.expect(listMock.get(1)).andReturn(subj);
		EasyMock.expect(subj.getCardMin()).andReturn(1);
		EasyMock.expect(subj.getCardMax()).andReturn(2);
		
		EasyMock.expect(listMock.size()).andReturn(2);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(listMock);
		EasyMock.replay(subj);
		
		assertEquals(this.ac.getCardRange().toString(), new StringBuilder("0\t2\n1\t2").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(listMock);
		EasyMock.verify(subj);
	}
	
	@Test
	public void testGetMinimumAssignedSubject()
	{
		assertEquals(this.ac.getMinimumAssignedSubject().toString(), new StringBuilder("0").toString());
	}
	
	@Test
	public void testGetRepartitionCost()
	{
		assertEquals(this.ac.getRepartitionCost().toString(), new StringBuilder("7\t1\t5\t25\t1000\t20000\t150000\t1200000").toString());
	}
	
	@Test
	public void testGetChoices()
	{
		assertEquals(this.ac.getMultiplicity().toString(), new StringBuilder("0").toString());
	}
	
	@Test
	public void testGetMultiplicity()
	{
		assertEquals(this.ac.getMultiplicity().toString(), new StringBuilder("0").toString());
	}
}
