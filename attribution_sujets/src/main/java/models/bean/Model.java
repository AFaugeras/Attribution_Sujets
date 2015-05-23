package models.bean;

import java.util.List;
import java.util.Observable;

public class Model extends Observable {

	public static final String SUBJECTS_UPDATE_MESSAGE = "SUBJECTS_UPDATED";

	private GeneralConstraints constraint;
	private List<Person> persons;
	private List<Subject> subjects;

	public Model(GeneralConstraints constraint, List<Person> persons,
			List<Subject> subjects) {
		super();
		this.constraint = constraint;
		this.persons = persons;
		this.subjects = subjects;
	}

	public GeneralConstraints getConstraint() {
		return constraint;
	}

	public void setConstraint(GeneralConstraints constraint) {
		this.constraint = constraint;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
		this.notifyObservers(SUBJECTS_UPDATE_MESSAGE);
	}

	public boolean add(Subject e) {
		boolean ret = false;

		if (subjects.add(e)) {
			ret = true;
			this.notifyObservers(SUBJECTS_UPDATE_MESSAGE);
		}

		return ret;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

}
