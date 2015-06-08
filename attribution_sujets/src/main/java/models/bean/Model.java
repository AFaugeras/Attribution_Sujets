package models.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Model de donnees permettant de representer le probleme d'attribution de sujets 
 * a un ensemble de personnes s'appuyant sur les preferences
 */
public class Model {

	/**
	 * Contraintes generales du problemes
	 */
	private Constraints constraints;
	
	/**
	 * Liste des participants
	 */
	private List<Person> persons;
	
	/**
	 * Liste des sujets a assignes
	 */
	private List<Subject> subjects;
	
	public Model() {
		this.constraints = new Constraints(0, 0, 0, 0, 0);
		this.persons = new ArrayList<Person>();
		this.subjects = new ArrayList<Subject>();
	}

	public Model(Constraints constraint, List<Person> persons,
			List<Subject> subjects) {
		super();
		this.constraints = constraint;
		this.persons = persons;
		this.subjects = subjects;
	}

	public Constraints getConstraint() {
		return constraints;
	}

	public void setConstraint(Constraints constraint) {
		this.constraints = constraint;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Model [constraint=" + constraints + ", persons=" + persons
				+ ", subjects=" + subjects + "]";
	}
}
