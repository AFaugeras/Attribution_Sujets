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
		assertNotNull("Erreur d'initialisation", this.modelMock);
		
		this.ac = new AdaptorChocoImpl(this.modelMock);
		assertNotNull("Erreur constructeur", this.ac);

	}
	
	@Test
	public void testGetNbPersons() 
	{
		Person persMock = EasyMock.createMock(Person.class);
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(persMock);
		persons.add(persMock);
		persons.add(persMock);
		
		EasyMock.expect(this.modelMock.getPersons()).andReturn(persons);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(persMock);
		
		assertEquals(this.ac.getNbPersons().toString(), new StringBuilder("3").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(persMock);
	}
	
	@Test
	public void testGetNbSubjects() 
	{
		Subject subjMock = EasyMock.createMock(Subject.class);
		List<Subject> subjects = new ArrayList<Subject>();
		
		subjects.add(subjMock);
		subjects.add(subjMock);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(subjects);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(subjMock);

		assertEquals(this.ac.getNbSubjects().toString(), new StringBuilder("2").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(subjMock);
	}
	
	@Test
	public void testGetSizeRange() 
	{
		Subject subj1Mock = EasyMock.createMock(Subject.class);
		Subject subj2Mock = EasyMock.createMock(Subject.class);
		List<Subject> subjects = new ArrayList<Subject>();
		
		subjects.add(subj1Mock);
		subjects.add(subj2Mock);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(subjects);
		
		EasyMock.expect(subj1Mock.getMinSize()).andReturn(5);
		EasyMock.expect(subj1Mock.getMaxSize()).andReturn(10);
		
		EasyMock.expect(subj2Mock.getMinSize()).andReturn(1);
		EasyMock.expect(subj2Mock.getMaxSize()).andReturn(15);

		EasyMock.replay(this.modelMock);
		EasyMock.replay(subj1Mock);
		EasyMock.replay(subj2Mock);
		
		assertEquals(this.ac.getSizeRange().toString(), new StringBuilder("5\t10\n1\t15").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(subj1Mock);
		EasyMock.verify(subj2Mock);
	}
	
	@Test
	public void testGetCardRange() 
	{
		Subject subj1Mock = EasyMock.createMock(Subject.class);
		Subject subj2Mock = EasyMock.createMock(Subject.class);
		List<Subject> subjects = new ArrayList<Subject>();
		
		subjects.add(subj1Mock);
		subjects.add(subj2Mock);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(subjects);
		
		EasyMock.expect(subj1Mock.getMinCard()).andReturn(0);
		EasyMock.expect(subj1Mock.getMaxCard()).andReturn(2);
		
		EasyMock.expect(subj2Mock.getMinCard()).andReturn(1);
		EasyMock.expect(subj2Mock.getMaxCard()).andReturn(2);
				
		EasyMock.replay(this.modelMock);
		EasyMock.replay(subj1Mock);
		EasyMock.replay(subj2Mock);
		
		assertEquals(this.ac.getCardRange().toString(), new StringBuilder("0\t2\n1\t2").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(subj1Mock);
		EasyMock.verify(subj2Mock);
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
		Person pers1Mock = EasyMock.createMock(Person.class);
		Person pers2Mock = EasyMock.createMock(Person.class);
		Person pers3Mock = EasyMock.createMock(Person.class);
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(pers1Mock);
		persons.add(pers2Mock);
		persons.add(pers3Mock);
		
		Subject subj1Mock = EasyMock.createMock(Subject.class);
		Subject subj2Mock = EasyMock.createMock(Subject.class);
		List<Subject> subjects = new ArrayList<Subject>();
		subjects.add(subj1Mock);
		subjects.add(subj2Mock);
		
		List<Subject> choices1 = new ArrayList<Subject>();
		choices1.add(subj1Mock);
		
		List<Subject> choices2 = new ArrayList<Subject>();
		choices2.add(subj2Mock);
		choices2.add(subj1Mock);
		
		List<Subject> choices3 = new ArrayList<Subject>();
		choices3.add(subj2Mock);
				
		EasyMock.expect(this.modelMock.getPersons()).andReturn(persons);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(subjects);

		EasyMock.expect(pers1Mock.getIDcampus()).andReturn("clinqu14");
		EasyMock.expect(pers1Mock.getChoices()).andReturn(choices1);
		
		EasyMock.expect(pers2Mock.getIDcampus()).andReturn("afauge14");
		EasyMock.expect(pers2Mock.getChoices()).andReturn(choices2);
		
		EasyMock.expect(pers3Mock.getIDcampus()).andReturn("nhebra14");
		EasyMock.expect(pers3Mock.getChoices()).andReturn(choices3);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(subj1Mock);
		EasyMock.replay(subj2Mock);
		EasyMock.replay(pers1Mock);
		EasyMock.replay(pers2Mock);
		EasyMock.replay(pers3Mock);
		
		assertEquals(this.ac.getChoices().toString(), new StringBuilder("clinqu14\t1\t1\t2\nafauge14\t2\t2\t1\nnhebra14\t1\t2\t1").toString());
		
		EasyMock.verify(subj1Mock);
		EasyMock.verify(subj2Mock);
		EasyMock.verify(pers1Mock);
		EasyMock.verify(pers2Mock);
		EasyMock.verify(pers3Mock);
	}
	
	@Test
	public void testGetRejects()
	{
		Person pers1Mock = EasyMock.createMock(Person.class);
		Person pers2Mock = EasyMock.createMock(Person.class);
		Person pers3Mock = EasyMock.createMock(Person.class);
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(pers1Mock);
		persons.add(pers2Mock);
		persons.add(pers3Mock);
		
		Subject subj1Mock = EasyMock.createMock(Subject.class);
		Subject subj2Mock = EasyMock.createMock(Subject.class);
		List<Subject> subjects = new ArrayList<Subject>();
		subjects.add(subj1Mock);
		subjects.add(subj2Mock);
		
		List<Subject> rejects1 = new ArrayList<Subject>();
		rejects1.add(subj2Mock);
		
		List<Subject> rejects2 = new ArrayList<Subject>();
		
		List<Subject> rejects3 = new ArrayList<Subject>();
		rejects3.add(subj1Mock);
		
		EasyMock.expect(this.modelMock.getPersons()).andReturn(persons);		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(subjects);

		EasyMock.expect(pers1Mock.getRejects()).andReturn(rejects1);
		EasyMock.expect(pers2Mock.getRejects()).andReturn(rejects2);
		EasyMock.expect(pers3Mock.getRejects()).andReturn(rejects3);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(subj1Mock);
		EasyMock.replay(subj2Mock);
		EasyMock.replay(pers1Mock);
		EasyMock.replay(pers2Mock);
		EasyMock.replay(pers3Mock);

		
		assertEquals(this.ac.getRejects().toString(), new StringBuilder("1\t2\n0\n1\t1").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(subj1Mock);
		EasyMock.verify(subj2Mock);
		EasyMock.verify(pers1Mock);
		EasyMock.verify(pers2Mock);
		EasyMock.verify(pers3Mock);
	}
	
	@Test
	public void testGetMultiplicity()
	{
		assertEquals(this.ac.getMultiplicity().toString(), new StringBuilder("0").toString());
	}
}