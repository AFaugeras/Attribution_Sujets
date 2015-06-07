package models.solver.reader;

/**
 * Exception pour signifier l'impossibilite pour le solver de d�terminer une solution
 *
 */
public class NotFoundSolutionException extends Exception{

	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Message precisant l'erreur
	 */
	private String message;
	
	/**
	 * Constructeur
	 * @param message erreur
	 */
	public NotFoundSolutionException(String message){
		this.message = message;
	}

}
