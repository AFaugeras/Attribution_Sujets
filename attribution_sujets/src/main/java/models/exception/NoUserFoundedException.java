package models.exception;
/**
 * Message d'erreur lorsque un utilisateur apprarait dans le fichier de réponse alors qu'il n'est pas présent dans la liste de réponse fournir par campus
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
		return "Person non trouvée dans le fichier de liste des utilisateurs, vérifier la préence de l'id: "+IdUser;
	}
	
}
