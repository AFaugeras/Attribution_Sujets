package models.bean;

import java.util.List;

public class Person {

	private String name;
	private String IDcampus;
	private List<Subject> choices;
	private List<Subject> rejects;
	private Subject assigned;
	private String comment;

	public Person(String name, String iDcampus, List<Subject> choices,
			List<Subject> rejects, Subject assigned, String comment) {
		super();
		this.name = name;
		IDcampus = iDcampus;
		this.choices = choices;
		this.rejects = rejects;
		this.assigned = assigned;
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public String toString() {
		return "Person [name=" + name + ", IDcampus=" + IDcampus + ", choices="
				+ choices + ", rejects=" + rejects + ", assigned=" + assigned
				+ ", comment=" + comment + "]";
	}
}
