package models.reader;

/**
 * Exception permettant de signifier une erreur lors de la lecture de solutions des solveurs Choco ou GLPK
 */
public class ReaderException extends Exception{

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
	public ReaderException(String message){
		this.message = message;
	}
}
