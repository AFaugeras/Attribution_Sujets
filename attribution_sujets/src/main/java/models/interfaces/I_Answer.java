package models.interfaces;

import java.util.Date;
import java.util.List;

/**
 * Interface repr�sentant une ligne de r�ponse. L'interface pr�voit le format de
 * r�ponse suivant: Un nombre d'attribut fixes (9): IdReponse DateSoumission:
 * Institution Departement Cours Groupe IDRepondant NomPenom UserName; Et un
 * nombre de choix dynamique Renvoy� sur forme de liste de choix
 * 
 * @author C�dric
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
	 * revois l'identifiant de la r�ponse pr�sente dans le fichier CSV
	 * 
	 * @return String
	 */
	public String getIdReponse();

	/**
	 * retourne la date a laquelle la r�ponse a �t� enregistr�e
	 * 
	 * @return Date
	 */
	public Date getDateSoumission();

	/**
	 * renvoi l'institution � laquelle est rattach� l'utilisateur. Exemple : A1
	 * A2 FilA1...
	 * 
	 * @return String
	 */
	public String getInstitution();

	/**
	 * renvoi le departement auquel est rattach� l'utilisateur Exemple : �l�ve,
	 * professeur...
	 * 
	 * @return
	 */
	public String getDepartement();

	/**
	 * renvoi le cours associ�e a la r�ponse Exemple : multiphysique, IPIPIP,
	 * Stage ouvrier...
	 * 
	 * @return String
	 */
	public String getCours();

	/**
	 * renvoi le groupe ( vide dans le fichier pass� en param�tre
	 * 
	 * @return String
	 */
	public String getGroupe();

	/**
	 * Retourne l'identifiant de l'utilisateur a l'origine de la r�ponse.
	 * 
	 * @return Integer
	 */
	public String getIdRepondant();

	/**
	 * retourne Le nom complet de l'utilisateur a l'origine de la r�ponse
	 * 
	 * @return String
	 */
	public String getNomPrenom();

	/**
	 * retourne le login de l'utilisateur a l'origine de la r�ponse
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
	 * renvoi true si l'identifiant de la personne est le meme que la r�ponse pass�e en param�tre
	 * @return
	 */
	public boolean isSameSender(I_Answer other);
	/**
	 * retourne la r�ponse la plus r�cente entre deux r�ponses.
	 * @param other
	 * @return
	 */
	public I_Answer wichIsTheEarlier(I_Answer other);

}