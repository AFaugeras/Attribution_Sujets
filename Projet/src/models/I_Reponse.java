package models;

import java.util.Date;
import java.util.List;

/**
 * Interface repr�sentant une ligne de r�ponse.
 * L'interface pr�voit le format de r�ponse suivant:
 * Un nombre d'attribut fixes (9):
 * -IdReponse 
 * -DateSoumission:	
 * -Institution	
 * -Departement	
 * -Cours	
 * -Groupe	
 * -IDRepondant	
 * -NomPenom	
 * -UserName;
 * Et un nombre de choix dynamique
 * Renvoy� sur forme de liste de choix
 * 
 * @author C�dric
 *
 */
public interface I_Reponse {
/**
 *Donne la liste des choix sous forme de tableau.
 * @return List<String> choix
 */
public List<String> getChoix();

/**
 * revois l'identifiant de la r�ponse pr�sente dans le fichier CSV
 * @return Integer
 */
public Integer getIdReponse();
/**
 * retourne la date a laquelle la r�ponse a �t� enregistr�e
 * @return Date
 */
public Date getDateSoumission();
/**
 * renvoi l'institution � laquelle est rattach� l'utilisateur.
 * Exemple : A1 A2 FilA1...
 * @return String
 */
public String getInstitution();
/**
 * renvoi le departement auquel est rattach� l'utilisateur
 * Exemple : �l�ve, professeur...
 * @return
 */
public String getDepartement();	
/**
 * renvoi le cours associ�e a la r�ponse
 * Exemple : multiphysique, IPIPIP, Stage ouvrier...
 * @return String
 */
public String getCours	();
/**
 * renvoi le groupe ( vide dans le fichier pass� en param�tre
 * @return String
 */
public String getGroupe	();
/**
 * Retourne l'identifiant de l'utilisateur a l'origine de la r�ponse.
 * @return Integer
 */
public String getIDRepondant();
/**
 * retourne Le nom complet de l'utilisateur a l'origine de la r�ponse
 * @return String
 */
public String getNomPenon();
/**
 * retourne le login de l'utilisateur a l'origine de la r�ponse
 * @return String
 */
public String getUserName();
}
