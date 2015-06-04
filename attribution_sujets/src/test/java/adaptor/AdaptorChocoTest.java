package adaptor;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import models.bean.Constraints;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import models.solver.adaptor.AdaptorChocoImpl;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AdaptorChocoTest extends TestCase{

	private Model modelMock;
	
	private AdaptorChocoImpl ac;

	@Override
	@Before
	public void setUp() throws Exception{
		this.modelMock = EasyMock.createMock(Model.class);
		assertNotNull("précondition", this.modelMock);
		
		this.ac = new AdaptorChocoImpl(this.modelMock);
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
		EasyMock.expect(subj.getMinCard()).andReturn(0);
		EasyMock.expect(subj.getMaxCard()).andReturn(2);
		
		EasyMock.expect(listMock.size()).andReturn(2);
		EasyMock.expect(listMock.get(1)).andReturn(subj);
		EasyMock.expect(subj.getMinCard()).andReturn(1);
		EasyMock.expect(subj.getMaxCard()).andReturn(2);
		
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
		Constraints consMock = EasyMock.createMock(Constraints.class);
		List<Long> costs = new ArrayList<Long>();
		costs.add(1L);
		costs.add(5L);
		costs.add(25L);
		
		EasyMock.expect(this.modelMock.getConstraint()).andReturn(consMock);
		EasyMock.expect(consMock.getWeights()).andReturn(costs);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(consMock);
		
		assertEquals(this.ac.getRepartitionCost().toString(), new StringBuilder("3\t1\t5\t25").toString());
	}
	
	@Test
	public void testGetChoices()
	{
		List<Subject> listSubjMock = EasyMock.createMock(List.class);
		List<Person> listPersMock = EasyMock.createMock(List.class);
		Subject subj = EasyMock.createMock(Subject.class);
		Person pers = EasyMock.createMock(Person.class);
		
		EasyMock.expect(this.modelMock.getPersons()).andReturn(listPersMock);
		EasyMock.expect(listPersMock.size()).andReturn(2);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(listSubjMock);
		EasyMock.expect(listSubjMock.size()).andReturn(3);	

		EasyMock.expect(listPersMock.get(0)).andReturn(pers);
		EasyMock.expect(pers.getIDcampus()).andReturn("clinqu14");
		EasyMock.expect(pers.getChoices()).andReturn(listSubjMock);
		EasyMock.expect(listSubjMock.size()).andReturn(1);
		EasyMock.expect(listSubjMock.get(0)).andReturn(subj);	
		EasyMock.expect(listSubjMock.indexOf(subj)).andReturn(-1);
		EasyMock.expect(listSubjMock.get(1)).andReturn(subj);	
		EasyMock.expect(listSubjMock.indexOf(subj)).andReturn(-1);
		EasyMock.expect(listSubjMock.get(2)).andReturn(subj);	
		EasyMock.expect(listSubjMock.indexOf(subj)).andReturn(0);
		
		EasyMock.expect(listPersMock.get(1)).andReturn(pers);
		EasyMock.expect(pers.getIDcampus()).andReturn("afauge14");
		EasyMock.expect(pers.getChoices()).andReturn(listSubjMock);
		EasyMock.expect(listSubjMock.size()).andReturn(1);
		EasyMock.expect(listSubjMock.get(0)).andReturn(subj);	
		EasyMock.expect(listSubjMock.indexOf(subj)).andReturn(0);
		EasyMock.expect(listSubjMock.get(1)).andReturn(subj);	
		EasyMock.expect(listSubjMock.indexOf(subj)).andReturn(-1);
		EasyMock.expect(listSubjMock.get(2)).andReturn(subj);	
		EasyMock.expect(listSubjMock.indexOf(subj)).andReturn(-1);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(listSubjMock);
		EasyMock.replay(listPersMock);
		EasyMock.replay(pers);
		EasyMock.replay(subj);
		
		assertEquals(this.ac.getChoices().toString(), new StringBuilder("clinqu14\t1\t2\t2\t1\nafauge14\t1\t1\t2\t2").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(listSubjMock);
		EasyMock.verify(listPersMock);
		EasyMock.verify(pers);
		EasyMock.verify(subj);
	}
	
	@Test
	public void testGetRejects()
	{
		List<Subject> listSubjMock = EasyMock.createMock(List.class);
		List<Person> listPersMock = EasyMock.createMock(List.class);
		List<Subject> listRejectsMock = EasyMock.createMock(List.class);
		
		Subject subj = EasyMock.createMock(Subject.class);
		Person pers = EasyMock.createMock(Person.class);
		
		EasyMock.expect(this.modelMock.getPersons()).andReturn(listPersMock);
		EasyMock.expect(listPersMock.size()).andReturn(2);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(listSubjMock);

		EasyMock.expect(listPersMock.get(0)).andReturn(pers);
		EasyMock.expect(pers.getRejects()).andReturn(listRejectsMock);
		EasyMock.expect(listRejectsMock.size()).andReturn(0);
		
		EasyMock.expect(listPersMock.get(1)).andReturn(pers);
		EasyMock.expect(pers.getRejects()).andReturn(listRejectsMock);
		EasyMock.expect(listRejectsMock.size()).andReturn(1);
		EasyMock.expect(listRejectsMock.get(0)).andReturn(subj);	
		EasyMock.expect(listSubjMock.indexOf(subj)).andReturn(2);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(listSubjMock);
		EasyMock.replay(listPersMock);
		EasyMock.replay(listRejectsMock);
		EasyMock.replay(pers);
		EasyMock.replay(subj);

		
		assertEquals(this.ac.getRejects().toString(), new StringBuilder("0\n1\t3").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(listSubjMock);
		EasyMock.verify(listPersMock);
		EasyMock.verify(listRejectsMock);
		EasyMock.verify(pers);
		EasyMock.verify(subj);
	}
	
	@Test
	public void testGetMultiplicity()
	{
		assertEquals(this.ac.getMultiplicity().toString(), new StringBuilder("0").toString());
	}
}