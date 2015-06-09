package models.solver;

import models.bean.Model;
import models.exception.ModelException;
import models.solver.reader.NotFoundSolutionException;
import models.solver.reader.ReaderException;
import models.solver.writer.WriterException;

/**
 * Interface d'un solver. Celui-ci est capable de resoudre le probleme d'attribution de sujet.
 */
public interface Solver {

	/**
	 * Methode de resolution des attributions de sujets
	 * @param inputFilename fichier d'entree du solver
	 * @param outputFilename fichier de sortie du solver
	 * @param data model de donnees du probleme 
	 * @return model modifie a partir de la solution
	 * @throws WriterException Erreur lors de l'ecriture du fichier d'entree du solver
	 * @throws ReaderException Erreur lors de la lecture de la solution du solver
	 * @throws NotFoundSolutionException Aucune solution au probleme
	 */
	public Model solve(String inputFilename, String outputFilename, Model data) throws SolverException, ModelException;
	
	/**
	 * Genere le fichier d'entree du solveur pour des ultilisations ulterieures. Le solver n'est cependant pas execute.
	 * @param inputFilename Path du fichier d'entree
	 * @param data Model de donnees
	 * @throws SolverException Erreur d'ecriture du fichier.
	 * @throws ModelException Incoherence du modele de donnes.
	 */
	public void generateInputFile(String inputFilename, Model data) throws SolverException, ModelException;
	
	/**
	 * Lecture d'un fichier solution afin de completer un modele de donnes.
	 * @param solutionFilename Path du fichier de solution
	 * @param data Model de donnees
	 * @throws SolverException Erreur de lecture ou absence de resultat.
	 */
	public Model readSolutionFile(String solutionFilename, Model data) throws SolverException;
}
