package models.bean;

import java.util.Date;
import java.util.List;

import models.interfaces.I_Answer;

/**
 * classe représentant une ligne de réponse extraite du fichier csv resultant du
 * formulaire de campus
 * 
 * @implements I_Answer
 */
public class Answer implements I_Answer {

	private String idReponse;
	private Date dateSoumission;
	private String institution;
	private String departement;
	private String cours;
	private String groupe;
	private String idRepondant;
	private String nomPrenom;
	private String userName;
	/**
	 * liste des réponse a toutes les colonnes dynamiques. y compris les
	 * commentaires
	 */
	private List<String> choix;

	@Override
	public List<String> getChoix() {
		return choix;
	}

	@Override
	public String getIdReponse() {
		return idReponse;
	}

	@Override
	public Date getDateSoumission() {
		return dateSoumission;
	}

	@Override
	public String getInstitution() {
		return institution;
	}

	@Override
	public String getDepartement() {
		return departement;
	}

	@Override
	public String getCours() {
		return cours;
	}

	@Override
	public String getGroupe() {
		return groupe;
	}

	@Override
	public String getIdRepondant() {
		return idRepondant;
	}

	@Override
	public String getNomPrenom() {
		return nomPrenom;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setIdReponse(String idReponse) {
		this.idReponse = idReponse;
	}

	@Override
	public void setChoix(List<String> choix) {
		this.choix = choix;
	}

	@Override
	public void setDateSoumission(Date dateSoumission) {
		this.dateSoumission = dateSoumission;
	}

	@Override
	public void setInstitution(String institution) {
		this.institution = institution;
	}

	@Override
	public void setDepartement(String departement) {
		this.departement = departement;
	}

	@Override
	public void setCours(String cours) {
		this.cours = cours;
	}

	@Override
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}

	@Override
	public void setIdRepondant(String idRepondant) {
		this.idRepondant = idRepondant;
	}

	@Override
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		String chaine = "--------------------\n";
		chaine += "Réponse \n id =" + idReponse + "\n";
		chaine += "userName = " + userName + "\n";
		chaine += "Nom Prenom = " + nomPrenom + "\n";
		chaine += "date de Soumission = " + dateSoumission + "\n";
		chaine += "institution = " + institution + " \n";
		chaine += "departement = " + departement + "\n";
		chaine += "cours =" + cours + "\n";
		chaine += "groupe = " + groupe + "\n";
		chaine += "idRepondant = " + idRepondant + "\n";
		chaine += "Liste de choix:";
		for (String choice : this.choix) {
			chaine += "\n	-" + choice;
		}
		chaine += "\n--------------------";

		return chaine;
	}

	@Override
	public boolean isSameSender(I_Answer other) {
		return (this.idRepondant.equals(other.getIdRepondant()));

	}

	@Override
	public I_Answer wichIsTheLater(I_Answer other) {
		return this.dateSoumission.before(other.getDateSoumission()) ? other
				: this;

	}

}
