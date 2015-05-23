package models.interfaces;

import java.util.Date;
import java.util.List;

/**
 * Interface représentant une ligne de réponse. L'interface prévoit le format de
 * réponse suivant: Un nombre d'attribut fixes (9): IdReponse DateSoumission:
 * Institution Departement Cours Groupe IDRepondant NomPenom UserName; Et un
 * nombre de choix dynamique Renvoyé sur forme de liste de choix
 * 
 * @author Cédric
 *
 */
public interface I_Answer {
	/**
	 * Donne la liste des choix sous forme de tableau.
	 * 
	 * @return List<String> choix
	 */
	public List<String> getChoix();

	/**
	 * revois l'identifiant de la réponse présente dans le fichier CSV
	 * 
	 * @return String
	 */
	public String getIdReponse();

	/**
	 * retourne la date a laquelle la réponse a été enregistrée
	 * 
	 * @return Date
	 */
	public Date getDateSoumission();

	/**
	 * renvoi l'institution à laquelle est rattaché l'utilisateur. Exemple : A1
	 * A2 FilA1...
	 * 
	 * @return String
	 */
	public String getInstitution();

	/**
	 * renvoi le departement auquel est rattaché l'utilisateur Exemple : éléve,
	 * professeur...
	 * 
	 * @return
	 */
	public String getDepartement();

	/**
	 * renvoi le cours associée a la réponse Exemple : multiphysique, IPIPIP,
	 * Stage ouvrier...
	 * 
	 * @return String
	 */
	public String getCours();

	/**
	 * renvoi le groupe ( vide dans le fichier passé en paramétre
	 * 
	 * @return String
	 */
	public String getGroupe();

	/**
	 * Retourne l'identifiant de l'utilisateur a l'origine de la réponse.
	 * 
	 * @return Integer
	 */
	public String getIdRepondant();

	/**
	 * retourne Le nom complet de l'utilisateur a l'origine de la réponse
	 * 
	 * @return String
	 */
	public String getNomPrenom();

	/**
	 * retourne le login de l'utilisateur a l'origine de la réponse
	 * 
	 * @return String
	 */
	public String getUserName();
	
	// setters
	public void setChoix(List<String> choix);
	public void setIdReponse(String id);
	public void setDateSoumission(Date dateSoumission);
	public void setInstitution(String institution);
	public void setDepartement(String departement);
	public void setCours(String cours);
	public void setGroupe(String groupe);
	public void setIdRepondant(String idRepondant);
	public void setNomPrenom(String nomPrenom);
	public void setUserName(String userName);

	/**
	 * renvoi true si l'identifiant de la personne est le meme que la réponse passée en paramétre
	 * @return
	 */
	public boolean isSameSender(I_Answer other);
	/**
	 * retourne la réponse la plus récente entre deux réponses.
	 * @param other
	 * @return
	 */
	public I_Answer wichIsTheEarlier(I_Answer other);

}