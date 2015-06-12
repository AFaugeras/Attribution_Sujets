package models.solver.reader;

import models.solver.SolverException;

/**
 * Exception pour signifier l'impossibilite pour le solver de d�terminer une solution
 *
 */
public class NotFoundSolutionException extends SolverException{

	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur
	 * @param message erreur
	 */
	public NotFoundSolutionException(String message)
	{
		super(message);
	}

}
