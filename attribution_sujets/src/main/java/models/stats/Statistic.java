package models.stats;

import java.util.List;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
/**
 * classe responsable de calculer les données statisques
 *
 */
public class Statistic {
private Model model;

public Statistic(Model model) {
	super();
	this.model = model;
}

/**
 * retorne le pourcentage de personne ayant obtenu leur Nieme choix
 * Pour le premier choix : N =1 etc
 * @param N
 * @return long
 */
public double PortionWhichGetChoiceN(int N){
	List<Person> listPerson = model.getPersons();
	double result =0;
	for (Person person : listPerson) {
		List<Subject> listSubject = person.getChoices();
		Subject subject = listSubject.get(N-1);
		
		if(person.getAssigned().equals(subject))
			result +=1;
	}
	return (result/listPerson.size())*100;
	
}


}
