package models.exception.fileformatexception;

public class NotFoundFileException extends FileException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Probléme à l'ouverture ou à la fermeture du fichier, verifier le chemin du fichier";
	}
}
