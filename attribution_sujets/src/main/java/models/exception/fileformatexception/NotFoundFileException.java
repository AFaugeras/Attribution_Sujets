package models.exception.fileformatexception;

public class NotFoundFileException extends FileException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Probl�me � l'ouverture ou � la fermeture du fichier, verifier le chemin du fichier";
	}
}
