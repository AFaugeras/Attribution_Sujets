package models.solver;

import models.bean.Model;
import models.reader.NotFoundSolutionException;
import models.reader.ReaderException;
import models.writer.WriterException;

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
	public Model solve(String inputFilename, String outputFilename, Model data) throws WriterException, ReaderException, NotFoundSolutionException;
}
