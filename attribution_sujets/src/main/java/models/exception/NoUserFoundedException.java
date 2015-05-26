package models.exception;
/**
 * Message d'erreur lorsque un utilisateur apprarait dans le fichier de r�ponse alors qu'il n'est pas pr�sent dans la liste de r�ponse fournir par campus
 * @author C�dric
 *
 */
public class NoUserFoundedException extends Exception {

	String IdUser;
	
	public NoUserFoundedException(String idUser) {
		super();
		IdUser = idUser;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Person non trouv�e dans le fichier de liste des utilisateurs, v�rifier la pr�ence de l'id: "+IdUser;
	}
	
}