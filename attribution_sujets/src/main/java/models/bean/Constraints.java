package models.bean;

public class Constraints {

	private int nbMaxChoice; // nombre de choix que le parseur devra prendre en
								// compte
	private int nbChoice; // nombre de choix (on sait ou s'arrete les choix et
							// ou commence les rejets)

	private int nbMaxReject; // nombre de reject que le parseur devra prendre en
								// compte
	private int nbReject; // nombre reject que contient une r�ponse.
	
	private boolean matchSubjectOnId = false;// permet de savoir si la corelation doit se faire sur l'id ou sur le lib�ll� du fichier 
	
	public Constraints(int nbMaxChoice, int nbChoice, int nbMaxReject,
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
	public boolean isMatchSubjectOnId() {
		return matchSubjectOnId;
	}

	public void setMatchSubjectOnId(boolean matchSubjectOnId) {
		this.matchSubjectOnId = matchSubjectOnId;
	}

	@Override
	public String toString() {
		return "GeneralConstraints [nbMaxChoice=" + nbMaxChoice + ", nbChoice="
				+ nbChoice + ", nbMaxReject=" + nbMaxReject + ", nbReject="
				+ nbReject + "]";
	}
}
