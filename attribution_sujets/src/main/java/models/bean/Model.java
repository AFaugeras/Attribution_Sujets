package models.bean;

import java.util.List;

public class Model {

	private Constraints constraint;
	private List<Person> persons;
	private List<Subject> subjects;

	public Model(Constraints constraint, List<Person> persons,
			List<Subject> subjects) {
		super();
		this.constraint = constraint;
		this.persons = persons;
		this.subjects = subjects;
	}

	public Constraints getConstraint() {
		return constraint;
	}

	public void setConstraint(Constraints constraint) {
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
		}

		return ret;
	}

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
