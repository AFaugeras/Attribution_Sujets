package models.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Participant a l'attribution automatique des sujets
 */
public class Person {

	/**
	 * Prenom
	 */
	private String firstName;
	
	/**
	 * Nom
	 */
	private String FamilyName;
	
	/**
	 * Identifiant Campus.
	 * Generalement la premiere lettre correspond a la premiere lettre du prenom. Les 5 suivants correspondent les 5 premieres de son nom. Les chiffre correspond a son annee d'integration
	 * Dupont Alexandra promo 2014 => adupont14
	 */
	private String IDcampus;
	
	/**
	 * Choix preferes du participant. 
	 * Le premier element de la liste correspond a son choix prefere, ...
	 */
	private List<Subject> choices;
	
	/**
	 * Rejets du partipant, c'est a dire les sujets auxquels il refuse de participer.
	 */
	private List<Subject> rejects;
	
	/**
	 * Sujet attribue suite a l'attribution automatique.
	 */
	private Subject assigned;
	
	/**
	 * Commentaire.
	 * Celui-ci peut par exemple permettre a un participant de preciser les personnes dont il ne souhaite pas travailler avec.
	 */
	private String comment;

	public Person() {
		choices = new ArrayList<Subject>();
		rejects = new ArrayList<Subject>();
	}

	public Person(String name, String iDcampus, List<Subject> choices,
			List<Subject> rejects, Subject assigned, String comment) {
		super();
		this.firstName = name;
		IDcampus = iDcampus;
		this.choices = choices;
		this.rejects = rejects;
		this.assigned = assigned;
		this.comment = comment;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getFamilyName() {
		return FamilyName;
	}

	public void setFamilyName(String familyName) {
		FamilyName = familyName;
	}

	public String getIDcampus() {
		return IDcampus;
	}

	public void setIDcampus(String iDcampus) {
		IDcampus = iDcampus;
	}

	public List<Subject> getChoices() {
		return choices;
	}

	public void setChoices(List<Subject> choices) {
		this.choices = choices;
	}

	public List<Subject> getRejects() {
		return rejects;
	}

	public void setRejects(List<Subject> rejects) {
		this.rejects = rejects;
	}

	public Subject getAssigned() {
		return assigned;
	}

	public void setAssigned(Subject assigned) {
		this.assigned = assigned;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person other = (Person) obj;
			return other.getIDcampus().equals(this.getIDcampus());

		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", FamilyName=" + FamilyName
				+ ", IDcampus=" + IDcampus + ", choices=" + choices
				+ ", rejects=" + rejects + ", assigned=" + assigned
				+ ", comment=" + comment + "]";
	}
	
	public boolean isThisSubjectAssigned(Subject Subject){
		if (Subject == null) return false;
		else if(Subject.equals(this.assigned)) return true;
		else return false;
		
	}
}
