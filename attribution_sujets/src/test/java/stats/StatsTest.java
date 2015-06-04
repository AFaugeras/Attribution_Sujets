package stats;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import models.bean.Constraints;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import models.factory.SubjectFactory;
import models.factory.UserFactory;
import models.interfaces.I_Answer;
import models.parser.BeanMatcher;
import models.stats.Statistic;

public class StatsTest extends TestCase {

	
	private Model initModel() throws Exception{
		Constraints constraint =new Constraints(6, 6, 0, 0);
		List<Person>   person= initPerson();
		List<I_Answer> answer = initAnswer();
		List<Subject>  subject = initSubject();
		
		BeanMatcher matcher = new BeanMatcher(person, answer, subject, constraint);
		matcher.match();
		person.get(0).setAssigned(subject.get(0));
		person.get(1).setAssigned(subject.get(1));
		person.get(2).setAssigned(subject.get(0));
		person.get(3).setAssigned(subject.get(0));
		Model model = new Model(constraint, person, subject);
		
		return model;
	}
	private List<Person> initPerson(){
		String[][] datas = {{"ADAM", "François","osef@etudiant.mines-nantes.fr", "fadam14" },
							{"ADAM", "François1","osef@etudiant.mines-nantes.fr", "doudo14" },
							{"ADAM", "François2","osef@etudiant.mines-nantes.fr", "foudo14" },
							{"ADAM", "François3","osef@etudiant.mines-nantes.fr", "goudo14" }};	
	
		List<Person> person = new ArrayList<Person>();
		for (String[] data : datas) {
			person.add(UserFactory.createUser(data));
		} 
		
		return person;
	}
	
	private List<Subject> initSubject(){
		String[][] datas ={{"Labyrinthes","0","0","3","4","0"},
							{"Space landing","0","0","3","4","0"},
							{"Poker","0","0","3","4","0"},
							{"Bataille","0","0","3","4","0"},
							{"Ruzzle","0","0","3","4","0"}
		};
		
		List<Subject> subject = new ArrayList<Subject>();
		int id=0;
		for (String[] data : datas) {
			subject.add(SubjectFactory.createSubject(data, id++))	;	
				}
		return subject;
	}
	private List<I_Answer> initAnswer(){
		 String[][]datas = {{ "28773", "03/02/2015 21:38:21", "A1", "eleve","IPIPIP", "	", "4161", "ADAM François", "fadam14","Labyrinthes", "Space landing", "Poker", "Bataille","", "Ruzzle","Si possible, j'aimerais bien me mettre avec Maxime Ansquer." },
				 			{"28774", "03/02/2015 21:38:22", "A1", "eleve","IPIPIP", "	", "4162", "ADAM François1", "doudo14", "Space landing","Labyrinthes", "Poker", "Bataille","", "Ruzzle","Si possible, j'aimerais bien me mettre avec Maxime Ansquer." },
				 			{"28774", "03/02/2015 21:38:22", "A1", "eleve","IPIPIP", "	", "4163", "ADAM François3", "foudo14",  "Space landing","Labyrinthes","Poker", "Bataille","", "Ruzzle" },
				 			{"28774", "03/02/2015 21:38:22", "A1", "eleve","IPIPIP", "	", "4163", "ADAM François3", "goudo14", "Poker", "Space landing","Labyrinthes", "Bataille","", "Ruzzle" },
				 			};
		 List<I_Answer>answer = new ArrayList<I_Answer>();
			for (String[] data : datas) {
				answer.add(models.factory.AnswerFactory.createAnswer(data));
			}
			return answer;
	}
	
	
	@Test
	public void testStat(){
		try {
			Model model = initModel();
			Statistic stat = new Statistic(model);
			double portionfirstChoice = 50;
			double portionSecondChoice = 25;
			double portionFirdChoice = 25;
			assertEquals(portionfirstChoice, stat.PortionWhichGetChoiceN(1));
			assertEquals(portionSecondChoice,stat.PortionWhichGetChoiceN(2));
			assertEquals(portionFirdChoice,stat.PortionWhichGetChoiceN(3));
			
		} catch (Exception e) {
			e.printStackTrace();fail("Problem de création de model");
			
		}
		
		
		
	}
	
}
