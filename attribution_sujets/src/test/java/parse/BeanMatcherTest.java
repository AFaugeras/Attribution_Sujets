package parse;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import models.bean.Answer;
import models.bean.Constraints;
import models.bean.Person;
import models.bean.Subject;
import models.factory.AnswerFactory;
import models.interfaces.I_Answer;
import models.parser.BeanMatcher;

import org.easymock.EasyMock;
import org.junit.Test;

public class BeanMatcherTest extends TestCase{
	
	@Test
	public void testMatch(){
		List<Person> listPerson = new ArrayList<Person>();
		List<Subject> listsubject = new ArrayList<Subject>();
		List<I_Answer> listAnswer = new ArrayList<I_Answer>();
		String[] dataUser= {"ABOUTARIK","Sara",	"Sara.ABOUTARIK@etudiant.mines-nantes.fr","sabout14"};
		Person person = models.factory.UserFactory.createUser(dataUser);
		listPerson.add(person);
		String[][] subjectList={
								{"Gestion des notes et élèves","2","2","2","2","2"},
								{"Plan de déchargement","","","2","",""},
								{"Souris de bibliothèque","","","","",""},
								{"Design","","","","",""},
								{"Baby sitting","","","","",""},
								{"Expressions algébriques","","","",""},
								};
		int i=0;
		for (String[] strings : subjectList) {
			listsubject.add(models.factory.SubjectFactory.createSubject(strings, i++));
		}
		
		String[] dataAnswer = {
				"28949"
				,"10/02/2015 20:21:00"
				,"A1"
				,"eleve"
				,"IPIPIP"
				,""
				,"4145"
				,"ABOUTARIK Sara"
				,"sabout14"
				,"Gestion des notes et élèves"
				,"Plan de déchargement"
				,"Souris de bibliothèque"
				,"Design"
				,"Baby sitting"
				,"Expressions algébriques"	
};
		I_Answer answer = AnswerFactory.createAnswer(dataAnswer);
		listAnswer.add(answer);
		
		Constraints constraint = new Constraints(3, 3, 3, 3);
		constraint.setMatchSubjectOnId(false);
		EasyMock.createMock(Constraints.class);
//		EasyMock.expect(constraint.isMatchSubjectOnId()).andReturn(false);// match sur les libelle
		//EasyMock.expect(constraint.getNbChoice()).andReturn(3);
		//EasyMock.expect(constraint.getNbReject()).andReturn(3);
		
		
		BeanMatcher matcher = new BeanMatcher(listPerson, listAnswer, listsubject, constraint);
		
		
		try {
			matcher.match();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
