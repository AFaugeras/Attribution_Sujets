package models.bean;

public class GeneralConstraints {

	private int nbMaxChoice; // nombre de choix que le parseur devra prendre en compte
	private int nbChoice; 	// nombre de choix (on sait ou s'arrete les choix et ou commence les rejets)
	
	private int nbMaxReject; // nombre de reject que le parseur devra prendre en compte
	private int nbReject; // nombre reject que contient une réponse.
	public GeneralConstraints(int nbMaxChoice, int nbChoice, int nbMaxReject,
			int nbReject) {
		super();
		this.nbMaxChoice = nbMaxChoice;
		this.nbChoice = nbChoice;
		this.nbMaxReject = nbMaxReject;
		this.nbReject = nbReject;
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
	
	
}
