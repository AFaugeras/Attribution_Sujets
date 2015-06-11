package models.solver.reader;

/**
 * Lecteur de solutions provenant d'un solveur afin de l'integrer a un modele de donnees.
 */
public interface SolutionReader {

	/**
	 * Lecture d'un fichier correspondant a la solution venant d'un Solveur.
	 * @param pathFile Chemin du fichier a lire.
	 * @throws ReaderException Probleme de lecture du fichier.
	 * @throws NotFoundSolutionException Absence de solution.
	 */
	public void read(String pathFile) throws ReaderException, NotFoundSolutionException;
}
