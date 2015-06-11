package models.bean;

import java.util.ArrayList;
import java.util.List;

import models.exception.ModelException;

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
	/**
	 * verifie si la somme (de chaque minimum) est superieur au nb de personne
	 * @return
	 */
	public boolean isMinUnderNbPeople(){
		int minimum = 0;
		for(Subject s: this.subjects){
			minimum+= s.getMinSize()*s.getMaxCard();
		}
		return minimum>this.persons.size();
	}
	
	/**
	 * verifie que les valeur de paramétrage sont correctes
	 * @throws ModelException
	 */
	public void checkModel()throws ModelException{
		// test sur les minimums
		if(isMinUnderNbPeople()){
		throw new ModelException("La somme des minimums pas sujet est supérieure au nombre de personnes");
		}
		
		// comparaison des min et maxSize de chaque sujet
		for (Subject s : this.subjects) {
			if(!s.isMaxSizeOverMinSize()) throw new ModelException("Le paramétrage du sujet N° "+s.getId()+"'"+s.getLabel()+"' est incorrecte");
		}
		// verification si le max paramétré est bien superieur ou egale au nombre de personnes total
		if(calcMaxSizeOfAllSubject()<this.persons.size()) 
			throw new ModelException("La somme des (Cardinalité Max * Taille Maximum ) est moins grande que le nombre de personne.\nCalcul = "
										+calcMaxSizeOfAllSubject()+"\nNb Personnes = "+this.persons.size());
		// verifie que le nombre de sujet campus et superieur ou egale au nombre de sujet que le solveur doit prendre en compte
		if(this.constraints.getNbChoice()<this.constraints.getNbMaxChoice()){
			throw new ModelException("Le nombre de sujet dans le fichier campus est supérieur a nombre de choix que le solveur prendra en compte");
		}
		if(this.constraints.getNbReject()<this.constraints.getNbMaxReject()){
			throw new ModelException("Le nombre de rejets dans le fichier campus est supérieur a nombre de rejet que le solveur prendra en compte");
		}
				
	}
	/**
	 * retourne la somme des produits entre le maxCard et le MaxSize de tous les sujets
	 * @return
	 */
	public int calcMaxSizeOfAllSubject(){
		int size=0;
		for (Subject s : this.subjects) {
		size+= s.getMaxCard()*s.getMaxSize();
		}
		
		return size;
	}
}
