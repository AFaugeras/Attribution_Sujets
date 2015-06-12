package models.solver;

/**
 * Exception lors de la resolution.
 * Cela comprend notamment l'absence de solution, une erreur d'ecriture des fichiers d'entree ou de lecture des fichiers solutions.
 */
public class SolverException extends Exception {

	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;
		
	public SolverException(String message)
	{
		super(message);
	}

}
