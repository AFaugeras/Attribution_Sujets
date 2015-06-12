package models.solver.reader;

import models.solver.SolverException;

/**
 * Exception permettant de signifier une erreur lors de la lecture de solutions des solveurs Choco ou GLPK
 */
public class ReaderException extends SolverException{	
	
	/**
	 * Version
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur
	 * @param message erreur
	 */
	public ReaderException(String message)
	{
		super(message);
	}
}
