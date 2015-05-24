package models.bean;

import java.util.List;

public class Person {

	private String firstName;
	private String FamilyName;
	private String IDcampus;
	private List<Subject> choices;
	private List<Subject> rejects;
	private Subject assigned;
	private String comment;

	public Person() {

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
}
