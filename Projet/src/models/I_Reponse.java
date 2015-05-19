package models;

import java.util.Date;
import java.util.List;

/**
 * Interface représentant une ligne de réponse.
 * L'interface prévoit le format de réponse suivant:
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
 * Renvoyé sur forme de liste de choix
 * 
 * @author Cédric
 *
 */
public interface I_Reponse {
/**
 *Donne la liste des choix sous forme de tableau.
 * @return List<String> choix
 */
public List<String> getChoix();

/**
 * revois l'identifiant de la réponse présente dans le fichier CSV
 * @return Integer
 */
public Integer getIdReponse();
/**
 * retourne la date a laquelle la réponse a été enregistrée
 * @return Date
 */
public Date getDateSoumission();
/**
 * renvoi l'institution à laquelle est rattaché l'utilisateur.
 * Exemple : A1 A2 FilA1...
 * @return String
 */
public String getInstitution();
/**
 * renvoi le departement auquel est rattaché l'utilisateur
 * Exemple : éléve, professeur...
 * @return
 */
public String getDepartement();	
/**
 * renvoi le cours associée a la réponse
 * Exemple : multiphysique, IPIPIP, Stage ouvrier...
 * @return String
 */
public String getCours	();
/**
 * renvoi le groupe ( vide dans le fichier passé en paramétre
 * @return String
 */
public String getGroupe	();
/**
 * Retourne l'identifiant de l'utilisateur a l'origine de la réponse.
 * @return Integer
 */
public String getIDRepondant();
/**
 * retourne Le nom complet de l'utilisateur a l'origine de la réponse
 * @return String
 */
public String getNomPenon();
/**
 * retourne le login de l'utilisateur a l'origine de la réponse
 * @return String
 */
public String getUserName();
}
