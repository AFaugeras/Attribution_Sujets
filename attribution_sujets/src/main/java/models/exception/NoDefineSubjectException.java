package models.exception;

public class NoDefineSubjectException extends Exception {

@Override
public String getMessage() {
	
	return "Sujet non trouv�, v�rifier votre configuration et votre liste de sujet (Match sur Id ou sur libell�";
}
}
