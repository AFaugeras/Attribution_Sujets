package models.parser;

import java.util.List;

import models.bean.Constraints;
import models.bean.Person;
import models.bean.Subject;
import models.exception.NoDefineSubjectException;
import models.exception.NoUserFoundedException;
import models.interfaces.I_Answer;

/**
 * Classe chargée de faire la corélation entre les personnes, les responses et les sujets.
 */
public class BeanMatcher {

	private List<Person> personList;	// liste de personne 
	private List<I_Answer> answerList;	// liste de reponse 
	private List<Subject> subjectList;	// liste de sujet
	private Constraints constraint; 	// contrainte definies par l'utilisateur
	
	public BeanMatcher(List<Person> personList, List<I_Answer> listAnswer,
			List<Subject> subjectList, Constraints constraint) {
		super();
		this.personList = personList;
		this.answerList = listAnswer;
		this.subjectList = subjectList;
		this.constraint = constraint;
	}
	
	/**
	 * methode mettant a jour la liste des choix et des rejets 
	 * @throws NoUserFoundedException 
	 */
	public void match() throws NoDefineSubjectException, NoUserFoundedException {
		// on met les reponses dans les case choice ou rejet selon le parametrage
		for (I_Answer answer : answerList) {
			List<String> subject = answer.getChoix();// liste des sujets choisis par un utilisateur.
			Person person = this.getPersonById(answer.getUserName());
			if(constraint.isMatchSubjectOnId()){
				{	// on enregistre les choix dans le choix de l'objet person
					int i=0;
					for(i=i ;i<constraint.getNbChoice();i++){// on enregistre les choix dans le choix de l'objet person
						person.getChoices().add(getSubjectbyID(subject.get(i)));
					}// puis les rejets
					for ( i = constraint.getNbChoice(); i < constraint.getNbReject()+constraint.getNbChoice(); i++) {
						person.getRejects().add(getSubjectbyID(subject.get(i)));
					}
					if(i<subject.size()){
						person.setComment(subject.get(i));
					}
				}
			}else{
				int i=0;
				for(i=i ;i<constraint.getNbChoice() && i < subject.size();i++){// on enregistre les choix dans le choix de l'objet person
				if (!subject.get(i).isEmpty())	person.getChoices().add(getSubjectbyLabel(subject.get(i)));

				}// puis les rejets
				for ( i = constraint.getNbChoice(); i < constraint.getNbReject()+constraint.getNbChoice(); i++) {
					if (!subject.get(i).isEmpty())person.getRejects().add(getSubjectbyLabel(subject.get(i)));
				}
				if(i<subject.size()){
					person.setComment(subject.get(i));
				}
			}
		}
	}
	
	private Subject getSubjectbyID(String id) throws NoDefineSubjectException{
		for(Subject subject :this.subjectList){
			if(subject.getId()== Integer.valueOf(id))
				return subject;
		}
		throw new NoDefineSubjectException();
	}
	private Subject getSubjectbyLabel(String label) throws NoDefineSubjectException{
		for(Subject subject :this.subjectList){
			if(subject.getLabel().equals(label))
				return subject;
		}
		System.out.println(label);
		throw new NoDefineSubjectException();
	}
	
	private Person getPersonById(String id) throws NoUserFoundedException{
		for(Person person :personList){
			if(person.getIDcampus().equals(id))
				return person;
		}
		
		throw new NoUserFoundedException(id);
	}
	
	
}
