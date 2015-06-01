package models.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Contraintes globales du modeles. Ces contraintes s'appliquent donc a tous les sujets et personnes.
 */
public class Constraints {

	/**
	 * Nombre de choix  pris en compte par l'utilisateur. 
	 * En effet l'utilisateur a la possibilite de choisir de prendre en compte uniquement les 2 premiers choix 
	 * alors que les participants ont precises les 3 choix preferes.
	 */
	private int nbMaxChoice; 
	
	/**
	 * Nombre de choix possibles par participants via le questionnaire campus.
	 */
	private int nbChoice;

	/**
	 * Nombre de rejets  pris en compte par l'utilisateur. 
	 * En effet l'utilisateur a la possibilite de choisir de prendre en compte uniquement les 2 premiers rejets
	 * alors que les participants ont precises 3 rejets.
	 */
	private int nbMaxReject;
	
	/**
	 * Nombre de rejets possibles par participants via le questionnaire campus.
	 */
	private int nbReject;
	
	private List<Integer> weights;
	
	/**
	 * True si la correlation s'appuye sur l'id. False si celle-ci s'appuye sur le libelle
	 */
	private boolean matchSubjectOnId = false;
	
	public Constraints(int nbMaxChoice, int nbChoice, int nbMaxReject,
			int nbReject) {
		this.nbMaxChoice = nbMaxChoice;
		this.nbChoice = nbChoice;
		this.nbMaxReject = nbMaxReject;
		this.nbReject = nbReject;
		this.weights = new ArrayList<Integer>();		
	}

	public int getNbMaxChoice() {
		return nbMaxChoice;
	}

	public void setNbMaxChoice(int nbMaxChoice) {
		this.nbMaxChoice = nbMaxChoice;
	}

	public int getNbChoice() {
		return nbChoice;
	}

	public void setNbChoice(int nbChoice) {
		this.nbChoice = nbChoice;
	}

	public int getNbMaxReject() {
		return nbMaxReject;
	}

	public void setNbMaxReject(int nbMaxReject) {
		this.nbMaxReject = nbMaxReject;
	}

	public int getNbReject() {
		return nbReject;
	}

	public void setNbReject(int nbReject) {
		this.nbReject = nbReject;
	}
	public boolean isMatchSubjectOnId() {
		return matchSubjectOnId;
	}

	public void setMatchSubjectOnId(boolean matchSubjectOnId) {
		this.matchSubjectOnId = matchSubjectOnId;
	}

	public List<Integer> getWeights() {
		return weights;
	}

	public void setWeights(List<Integer> weights) {
		this.weights = weights;
	}

	@Override
	public String toString() {
		return "Constraints [nbMaxChoice=" + nbMaxChoice + ", nbChoice="
				+ nbChoice + ", nbMaxReject=" + nbMaxReject + ", nbReject="
				+ nbReject + ", weights=" + weights + ", matchSubjectOnId="
				+ matchSubjectOnId + "]";
	}
}
