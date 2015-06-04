package models.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Contraintes globales du modeles. Ces contraintes s'appliquent donc a tous les sujets et personnes.
 */
public class Constraints {
	/**
	 * Nombre de choix possibles par participants via le questionnaire campus.
	 */
	private int nbChoice;
	
	/**
	 * Nombre de rejets possibles par participants via le questionnaire campus.
	 */
	private int nbReject;

	/**
	 * Nombre de choix  pris en compte par l'utilisateur. 
	 * En effet l'utilisateur a la possibilite de choisir de prendre en compte uniquement les 2 premiers choix 
	 * alors que les participants ont precises les 3 choix preferes.
	 */
	private int nbMaxChoice; 

	/**
	 * Nombre de rejets  pris en compte par l'utilisateur. 
	 * En effet l'utilisateur a la possibilite de choisir de prendre en compte uniquement les 2 premiers rejets
	 * alors que les participants ont precises 3 rejets.
	 */
	private int nbMaxReject;

	/**
	 * Multiplicité.
	 */
	private int multiplicity;
	
	/**
	 * Cout de repartition ordonne par choix
	 */
	private List<Long> weights;
	
	/**
	 * True si la correlation s'appuye sur l'id. False si celle-ci s'appuye sur le libelle
	 */
	private boolean matchSubjectOnId;

	public Constraints(int nbChoice, int nbReject, int nbMaxChoice, int nbMaxReject, int multiplicity) {
		this.nbChoice = nbChoice;
		this.nbReject = nbReject;
		this.nbMaxChoice = nbMaxChoice;
		this.nbMaxReject = nbMaxReject;
		this.multiplicity = multiplicity;
		
		this.matchSubjectOnId = false;
		
		this.weights = new ArrayList<Long>();	
	}
	
	// TODO : Vieux constructeur à supprimer.
	public Constraints(int nbMaxChoice, int nbChoice, int nbMaxReject,
			int nbReject) {
		this.nbMaxChoice = nbMaxChoice;
		this.nbChoice = nbChoice;
		this.nbMaxReject = nbMaxReject;
		this.nbReject = nbReject;
		
		this.weights = new ArrayList<Long>();		
	}

	/**
	 * @return the nbChoice
	 */
	public int getNbChoice() {
		return nbChoice;
	}

	/**
	 * @param nbChoice the nbChoice to set
	 */
	public void setNbChoice(int nbChoice) {
		this.nbChoice = nbChoice;
	}

	/**
	 * @return the nbReject
	 */
	public int getNbReject() {
		return nbReject;
	}

	/**
	 * @param nbReject the nbReject to set
	 */
	public void setNbReject(int nbReject) {
		this.nbReject = nbReject;
	}

	/**
	 * @return the nbMaxChoice
	 */
	public int getNbMaxChoice() {
		return nbMaxChoice;
	}

	/**
	 * @param nbMaxChoice the nbMaxChoice to set
	 */
	public void setNbMaxChoice(int nbMaxChoice) {
		this.nbMaxChoice = nbMaxChoice;
	}

	/**
	 * @return the nbMaxReject
	 */
	public int getNbMaxReject() {
		return nbMaxReject;
	}

	/**
	 * @param nbMaxReject the nbMaxReject to set
	 */
	public void setNbMaxReject(int nbMaxReject) {
		this.nbMaxReject = nbMaxReject;
	}

	/**
	 * @return the multiplicity
	 */
	public int getMultiplicity() {
		return multiplicity;
	}

	/**
	 * @param multiplicity the multiplicity to set
	 */
	public void setMultiplicity(int multiplicity) {
		this.multiplicity = multiplicity;
	}

	/**
	 * @return the weights
	 */
	public List<Long> getWeights() {
		return weights;
	}

	/**
	 * @param weights the weights to set
	 */
	public void setWeights(List<Long> weights) {
		this.weights = weights;
	}

	/**
	 * @return the matchSubjectOnId
	 */
	public boolean isMatchSubjectOnId() {
		return matchSubjectOnId;
	}

	/**
	 * @param matchSubjectOnId the matchSubjectOnId to set
	 */
	public void setMatchSubjectOnId(boolean matchSubjectOnId) {
		this.matchSubjectOnId = matchSubjectOnId;
	}
	
	@Override
	public String toString() {
		return "Constraints [nbChoice=" + nbChoice + ", nbReject=" + nbReject
				+ ", nbMaxChoice=" + nbMaxChoice + ", nbMaxReject="
				+ nbMaxReject + ", multiplicity=" + multiplicity + ", weights="
				+ weights + ", matchSubjectOnId=" + matchSubjectOnId + "]";
	}
}
