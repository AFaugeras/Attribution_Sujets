package models.writer;

/**
 * Exception permettant de signifier une erreur lors de l'ecriture de fichiers d'entree pour Choco ou GLPK.
 */
public class WriterException extends Exception {

	/**
	 * Message precisant l'erreur
	 */
	private String message;
	
	
	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * @param message erreur
	 */
	public WriterException(String message){
		this.message = message;
	}
}
