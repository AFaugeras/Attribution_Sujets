package models.exception;
/**
 * Exception levée quand un sujet n'est pas défini dans le fichier de paramétrage
 *
 */
public class NoDefineSubjectException extends Exception {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
public String getMessage() {
	
	return "Sujet non trouvé, vérifier votre configuration et votre liste de sujet (Match sur Id ou sur libellé";
}
}
