package models.stats;

import java.util.ArrayList;
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
 * retourne le pourcentage de personne ayant obtenu leur Nieme choix
 * Pour le premier choix : N =1 etc
 * Paramétre a passer pour un sujet assigné sans que la personne n'ai fait de choix : N = -1
 * Paramétre a passer pour un sujet assigné que la personne n'avais pas mis dans sa liste de choix : N = -2
 * 
 * @param N
 * @return long
 */
public double PortionWhichGetChoiceN(int N){
	List<Person> listPerson = model.getPersons();
	double result =0;
	double nbPerson = listPerson.size();
	if (N==-1){// personne ayant un sujet quelconque sachant qu'il n'a fait aucun choix
		for (Person person : listPerson) {
			List<Subject> listSubject = person.getChoices();
			if(listSubject.isEmpty())
				result +=1;
		}
		return (result/nbPerson)*100;
	}else if(N ==-2){ //cas d'un sujet attribué alors qu'il n'est pas dans la liste de choix de la personne
		for (Person person : listPerson) {
			List<Subject> listSubject = person.getChoices();
			if(listSubject.contains(person.getAssigned()))
				result +=1;
		}
		
		return (result/nbPerson)*100;
	}
	else{
		for (Person person : listPerson) {
			List<Subject> listSubject = person.getChoices();
			Subject subject = listSubject.get(N-1);
			if(person.isThisSubjectAssigned(subject))
				result +=1;
		}
	return (result/listPerson.size())*100;
	}
}
/**
 * Renvoie le pourcentage de personne ayant un sujet attribué alors qu'il sagit de son niéme rejet
 * @param N
 * @return
 */
public double PortionWhichGetRejectN(int N){
	List<Person> listPerson = model.getPersons();
	double result =0;
	for (Person person : listPerson) {
		List<Subject> listSubject = person.getRejects();
		Subject subject = listSubject.get(N-1);
		
		if(person.isThisSubjectAssigned(subject))
			result +=1;
	}
	return (result/listPerson.size())*100;
	
}

/**
 * donne la proportion de personne ayant son sujet assigné comme Niéme choix
 * @param subject
 * @param N rang du choix
 * @return double
 */
public double PortionNchoiceSubject(Subject subject, int N){
	double result =0;
	List<Person> person = getUserWhoThisSubjectIsAssigned(subject);
	for (Person person2 : person) {
		if(subject.equals(person2.getChoices().get(N)))
			result+=1;
	}
	
	
	return result/this.model.getPersons().size();
}

/**
 * retourne la liste des user ayant le sujet passer paramétre assigné.
 * @param subject
 * @return List<Person>
 */
private List<Person> getUserWhoThisSubjectIsAssigned(Subject subject){
	List<Person> liste = new ArrayList<Person>();
	for(Person person : this.model.getPersons()){
		if(person.isThisSubjectAssigned(subject))
			liste.add(person);
	}
	
	return liste;
}
}
