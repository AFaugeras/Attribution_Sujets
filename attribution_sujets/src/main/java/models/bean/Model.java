package models.bean;

import java.util.List;

public class Model {

	// public static final String SUBJECTS_ADDED_MESSAGE = "SUBJECTS_ADDED";

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
	}

	public boolean add(Subject e) {
		boolean ret = false;

		if (subjects.add(e)) {
			ret = true;
			// notifySubjectsChanged(SUBJECTS_ADDED_MESSAGE);
		}

		return ret;
	}

	// private void notifySubjectsChanged(String message) {
	// this.setChanged();
	// this.notifyObservers(message);
	// this.clearChanged();
	// }

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}

	@Override
	public String toString() {
		return "Model [constraint=" + constraint + ", persons=" + persons
				+ ", subjects=" + subjects + "]";
	}
}
