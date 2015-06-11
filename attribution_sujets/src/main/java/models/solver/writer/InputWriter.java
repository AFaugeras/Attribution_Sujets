package models.solver.writer;

/**
 * Ecrivain de fichiers d'entree des solveurs
 */
public interface InputWriter {

	/**
	 * Methode d'ecriture du fichier d'entree du solveur.
	 * @param pathFile chemin du fichier a ecrire.
	 * @throws WriterException Erreur d'ecriture
	 */
	public void write(String pathFile) throws WriterException;
}
