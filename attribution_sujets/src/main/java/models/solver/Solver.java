package models.solver;

import models.exception.ModelException;
import models.solver.reader.NotFoundSolutionException;

/**
 * Interface d'un solver. Celui-ci est capable de resoudre le probleme d'attribution de sujet.
 */
public interface Solver {

	/**
	 * Methode de resolution des attributions de sujets
	 * @throws SolverException Erreur d'ecriture du fichier d'entree ou de lecture du fichier solution.
	 * @throws NotFoundSolutionException Aucune solution au probleme
	 */
	public void solve() throws SolverException, ModelException;
	
	/**
	 * Genere le fichier d'entree du solveur pour des ultilisations ulterieures. Le solver n'est cependant pas execute.
	 * @throws SolverException Erreur d'ecriture du fichier.
	 * @throws ModelException Incoherence du modele de donnes.
	 */
	public void generateInputFile() throws SolverException, ModelException;
	
	/**
	 * Lecture d'un fichier solution afin de completer un modele de donnes.
	 * @throws SolverException Erreur de lecture ou absence de resultat.
	 */
	public void readSolutionFile() throws SolverException;
}
