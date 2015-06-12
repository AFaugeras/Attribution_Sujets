package models.solver.writer;

import models.solver.SolverException;

/**
 * Exception permettant de signifier une erreur lors de l'ecriture de fichiers d'entree pour Choco ou GLPK.
 */
public class WriterException extends SolverException {
	
	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * @param message erreur
	 */
	public WriterException(String message)
	{
		super(message);
	}
}
