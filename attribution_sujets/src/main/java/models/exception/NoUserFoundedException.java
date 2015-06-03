package models.exception;
/**
 * Message d'erreur lorsque un utilisateur apprarait dans le fichier de r�ponse alors qu'il n'est pas pr�sent dans la liste de r�ponse fournir par campus
 */
public class NoUserFoundedException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private String IdUser;
	
	public NoUserFoundedException(String idUser) {
		super();
		IdUser = idUser;
	}

	@Override
	public String getMessage() {
		return "Person non trouv�e dans le fichier de liste des utilisateurs, v�rifier la pr�ence de l'id: "+IdUser;
	}
	
}
