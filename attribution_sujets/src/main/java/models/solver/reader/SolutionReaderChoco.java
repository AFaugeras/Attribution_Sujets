package models.solver.reader;

/**
 * Lecteur de solutions provenant du solveur Choco
 */
public interface SolutionReaderChoco extends SolutionReader{

	/**
	 * Lecture d'un fichier correspondant a la solution venant du Solveur Choco.
	 * Celui-ci doit suivre le format suivant :
	 * Pas encore implémenté : 0 (Si pas de solution) ou 1 (Sinon)
	 * 2 (Sujet attribue au premier eleve)
	 * 4 (Sujet attribue au second eleve)
	 * ...
	 * 
	 * @param pathFile Chemin du fichier a lire
	 * @throws ReaderException Probleme de lecture du fichier.
	 * @throws NotFoundSolutionException Absence de solution.
	 */
	public void read(String pathFile) throws ReaderException, NotFoundSolutionException;
}
