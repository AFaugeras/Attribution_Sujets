package parse;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import models.bean.Constraints;
import models.bean.Person;
import models.bean.Subject;
import models.exception.fileformatexception.FileException;
import models.exception.fileformatexception.FileFormatException;
import models.factory.AnswerFactory;
import models.interfaces.I_Answer;
import models.parser.BeanMatcher;
import models.parser.answer.ParserCsvAnswer;
import models.parser.helper.CsvHelper;

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
								{"1","Gestion des notes et élèves","2","2","2","2","2"},
								{"2","Plan de déchargement","","","2","",""},
								{"3","Souris de bibliothèque","","","","",""},
								{"4","Design","","","","",""},
								{"5","Baby sitting","","","","",""},
								{"6","Expressions algébriques","","","",""},
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
		BeanMatcher matcher = new BeanMatcher(listPerson, listAnswer, listsubject, constraint);
		
		
		try {
			matcher.match();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	@Test
	public void testAnswerNotPlenty(){

		List<Person> listPerson = new ArrayList<Person>();
		List<Subject> listsubject = new ArrayList<Subject>();
		List<I_Answer> listAnswer = new ArrayList<I_Answer>();
		String[] dataUser= {"ABOUTARIK","Sara",	"Sara.ABOUTARIK@etudiant.mines-nantes.fr","fadam14"};
		Person person = models.factory.UserFactory.createUser(dataUser);
		listPerson.add(person);
		String[][] subjectList={
								{"1","Labyrinthes","2","2","2","2"},
								{"2","Space landing","","2","",""},
								{"3","Poker","","","",""},
								{"4","Bataille","","","",""},
								{"5","Ruzzle","","","",""},
								{"6","Expressions algébriques","","","",""},
								};
		int i=0;
		for (String[] strings : subjectList) {
			listsubject.add(models.factory.SubjectFactory.createSubject(strings, i++));
		}
		
		File f = new File("");
		f = CsvHelper.getRessource(f.getAbsolutePath()  + f.separator + "src"
				+ f.separator + "test"+ f.separator+ "resources"
				+ f.separator+ "Choix_avec_doublon.csv");
		ParserCsvAnswer parser = new ParserCsvAnswer();
		try {
			parser.parseAnswer(f);
		} catch (FileException e1) {
			fail();
		}
		listAnswer=parser.getCleanedData();
		
		
		Constraints constraint = new Constraints(6, 6, 0, 0);
		constraint.setMatchSubjectOnId(false);
		EasyMock.createMock(Constraints.class);

		BeanMatcher matcher = new BeanMatcher(listPerson, listAnswer, listsubject, constraint);
		
		boolean erreur = false;
		try {
			matcher.match();
		} catch (Exception e) {
			erreur = true;
		}
		 if(erreur == true) fail("Le match ne devrait pas avoir lieu");
	}
}
