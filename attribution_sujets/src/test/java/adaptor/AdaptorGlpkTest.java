package adaptor;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import models.bean.Constraints;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import models.solver.adaptor.AdaptorGlpkImpl;

import org.chocosolver.solver.constraints.Constraint;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AdaptorGlpkTest extends TestCase{

	private Model modelMock;
	
	private AdaptorGlpkImpl ag;

	@Before
	public void setUp() throws Exception{
		this.modelMock = EasyMock.createMock(Model.class);
		assertNotNull("Erreur d'initialisation", this.modelMock);
		
		this.ag = new AdaptorGlpkImpl(this.modelMock);
		assertNotNull("Erreur constructeur", this.ag);

	}
	
	@Test
	public void testGetPersons() 
	{
		Person persMock = EasyMock.createMock(Person.class);
		List<Person> persons = new ArrayList<Person>();
		
		persons.add(persMock);
		persons.add(persMock);
		persons.add(persMock);
		
		EasyMock.expect(this.modelMock.getPersons()).andReturn(persons);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(persMock);
		
		assertEquals(this.ag.getPersons().toString(), new StringBuilder("set eleves := \te0 \te1 \te2\t;").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(persMock);
	}
	
	@Test
	public void testGetSubjects() 
	{
		Subject subjMock = EasyMock.createMock(Subject.class);
		List<Subject> subjects = new ArrayList<Subject>();
		
		subjects.add(subjMock);
		subjects.add(subjMock);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(subjects);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(subjMock);
		
		assertEquals(this.ag.getSubjects().toString(), new StringBuilder("set projets := \tp0 \tp1\t;").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(subjMock);
	}
	
	@Test
	public void testGetMinSizeSubjects() 
	{
		Subject subj1Mock = EasyMock.createMock(Subject.class);
		Subject subj2Mock = EasyMock.createMock(Subject.class);
		Subject subj3Mock = EasyMock.createMock(Subject.class);
		
		List<Subject> subjects = new ArrayList<Subject>();
		
		subjects.add(subj1Mock);
		subjects.add(subj2Mock);
		subjects.add(subj3Mock);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(subjects);
		
		EasyMock.expect(subj1Mock.getMinSize()).andReturn(5);
		
		EasyMock.expect(subj2Mock.getMinSize()).andReturn(3);

		EasyMock.expect(subj3Mock.getMinSize()).andReturn(1);

		EasyMock.replay(this.modelMock);
		EasyMock.replay(subj1Mock);
		EasyMock.replay(subj2Mock);
		EasyMock.replay(subj3Mock);
		
		assertEquals(this.ag.getMinSizeSubjects().toString(), new StringBuilder("param nMin :=\tp0 \t5	p1 \t3	p2 \t1\t;").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(subj1Mock);
		EasyMock.verify(subj2Mock);
		EasyMock.verify(subj3Mock);
	}
	
	@Test
	public void testGetMaxSizeSubjects() 
	{
		Subject subj1Mock = EasyMock.createMock(Subject.class);
		Subject subj2Mock = EasyMock.createMock(Subject.class);
		Subject subj3Mock = EasyMock.createMock(Subject.class);
		
		List<Subject> subjects = new ArrayList<Subject>();
		
		subjects.add(subj1Mock);
		subjects.add(subj2Mock);
		subjects.add(subj3Mock);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(subjects);
		
		EasyMock.expect(subj1Mock.getMaxSize()).andReturn(15);
		
		EasyMock.expect(subj2Mock.getMaxSize()).andReturn(8);

		EasyMock.expect(subj3Mock.getMaxSize()).andReturn(10);

		EasyMock.replay(this.modelMock);
		EasyMock.replay(subj1Mock);
		EasyMock.replay(subj2Mock);
		EasyMock.replay(subj3Mock);
		
		assertEquals(this.ag.getMaxSizeSubjects().toString(), new StringBuilder("param nMax :=\tp0 \t15	p1 \t8	p2 \t10\t;").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(subj1Mock);
		EasyMock.verify(subj2Mock);
		EasyMock.verify(subj3Mock);
	}
	
	@Test
	public void testGetMinCardSubjects() 
	{
		Subject subj1Mock = EasyMock.createMock(Subject.class);
		Subject subj2Mock = EasyMock.createMock(Subject.class);
		Subject subj3Mock = EasyMock.createMock(Subject.class);
		
		List<Subject> subjects = new ArrayList<Subject>();
		
		subjects.add(subj1Mock);
		subjects.add(subj2Mock);
		subjects.add(subj3Mock);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(subjects);
		
		EasyMock.expect(subj1Mock.getMinCard()).andReturn(0);
		
		EasyMock.expect(subj2Mock.getMinCard()).andReturn(0);

		EasyMock.expect(subj3Mock.getMinCard()).andReturn(1);

		EasyMock.replay(this.modelMock);
		EasyMock.replay(subj1Mock);
		EasyMock.replay(subj2Mock);
		EasyMock.replay(subj3Mock);
		
		assertEquals(this.ag.getMinCardSubjects().toString(), new StringBuilder("param nbMinGpes :=\tp0 \t0	p1 \t0	p2 \t1\t;").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(subj1Mock);
		EasyMock.verify(subj2Mock);
		EasyMock.verify(subj3Mock);
	}
	
	@Test
	public void testGetMaxCardSubjects() 
	{
		Subject subj1Mock = EasyMock.createMock(Subject.class);
		Subject subj2Mock = EasyMock.createMock(Subject.class);
		Subject subj3Mock = EasyMock.createMock(Subject.class);
		
		List<Subject> subjects = new ArrayList<Subject>();
		
		subjects.add(subj1Mock);
		subjects.add(subj2Mock);
		subjects.add(subj3Mock);
		
		EasyMock.expect(this.modelMock.getSubjects()).andReturn(subjects);
		
		EasyMock.expect(subj1Mock.getMaxCard()).andReturn(2);
		
		EasyMock.expect(subj2Mock.getMaxCard()).andReturn(2);

		EasyMock.expect(subj3Mock.getMaxCard()).andReturn(1);

		EasyMock.replay(this.modelMock);
		EasyMock.replay(subj1Mock);
		EasyMock.replay(subj2Mock);
		EasyMock.replay(subj3Mock);
		
		assertEquals(this.ag.getMaxCardSubjects().toString(), new StringBuilder("param nbMaxGpes :=\tp0 \t2	p1 \t2	p2 \t1\t;").toString());
		
		EasyMock.verify(this.modelMock);
		EasyMock.verify(subj1Mock);
		EasyMock.verify(subj2Mock);
		EasyMock.verify(subj3Mock);
	}
	
	@Test
	public void testGetMinimumAssignedSubject()
	{
		assertEquals(this.ag.getMinimumAssignedSubject().toString(), new StringBuilder("param nbMiniSujets :=\t5\t;").toString());
	}
	
	@Test
	public void testGetChoices()
	{
		Constraints constraintsMock = EasyMock.createMock(Constraints.class);
		
		List<Long> costs = new ArrayList<Long>();
		costs.add(1L);
		costs.add(5L);
		costs.add(25L);
		
		EasyMock.expect(this.modelMock.getConstraint()).andReturn(constraintsMock);
		EasyMock.expect(constraintsMock.getWeights()).andReturn(costs);
		
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

		EasyMock.expect(pers1Mock.getChoices()).andReturn(choices1);	
		EasyMock.expect(pers2Mock.getChoices()).andReturn(choices2);		
		EasyMock.expect(pers3Mock.getChoices()).andReturn(choices3);
		
		EasyMock.expect(pers1Mock.getChoices()).andReturn(choices1);	
		EasyMock.expect(pers2Mock.getChoices()).andReturn(choices2);		
		EasyMock.expect(pers3Mock.getChoices()).andReturn(choices3);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(subj1Mock);
		EasyMock.replay(subj2Mock);
		EasyMock.replay(pers1Mock);
		EasyMock.replay(pers2Mock);
		EasyMock.replay(pers3Mock);
		EasyMock.replay(constraintsMock);
		
		assertEquals(this.ag.getChoices().toString(), new StringBuilder("param c :\te0\te1\te2\t\t:=\n\tp0\t1\t5\t5\t\t\n\tp1\t5\t1\t1\t\t\n\t;").toString());
		
		EasyMock.verify(subj1Mock);
		EasyMock.verify(subj2Mock);
		EasyMock.verify(pers1Mock);
		EasyMock.verify(pers2Mock);
		EasyMock.verify(pers3Mock);
		EasyMock.verify(constraintsMock);
	}
	
	@Test
	public void testGetMultiplicity()
	{
		Constraints constraintsMock = EasyMock.createMock(Constraints.class);
		EasyMock.expect(this.modelMock.getConstraint()).andReturn(constraintsMock);
		
		EasyMock.expect(constraintsMock.getMultiplicity()).andReturn(2);
		
		EasyMock.replay(this.modelMock);
		EasyMock.replay(constraintsMock);
		
		assertEquals(this.ag.getMultiplicity().toString(), new StringBuilder("param tailleEquipe:=\t2\t;").toString());

		EasyMock.verify(this.modelMock);
		EasyMock.verify(constraintsMock);
	}
}
