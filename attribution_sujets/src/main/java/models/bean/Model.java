package models.bean;

import java.util.List;

public class Model {
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
public List<Subject> getSubjects() {
	return subjects;
}
public void setSubjects(List<Subject> subjects) {
	this.subjects = subjects;
}


}
