package models.exception.fileformatexception;

public class NotFoundFileException extends FileException {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Probl�me � l'ouverture ou � la fermeture du fichier, verifier le chemin du fichier";
	}
}
