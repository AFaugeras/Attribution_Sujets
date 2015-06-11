package models.exception;
/**
 * Exception lev�e quand un sujet n'est pas d�fini dans le fichier de param�trage
 *
 */
public class NoDefineSubjectException extends Exception {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
public String getMessage() {
	
	return "Sujet non trouv�, v�rifier votre configuration et votre liste de sujet (Match sur Id ou sur libell�";
}
}
