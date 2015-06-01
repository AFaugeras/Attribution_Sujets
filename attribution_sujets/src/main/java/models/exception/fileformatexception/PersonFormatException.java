package models.exception.fileformatexception;

public class PersonFormatException extends FileFormatException {

	public String getMessage() {
		String message ="Format de fichier incompatible avec une liste de Personne.\n"
				+ "Format en vigeur:";
		for(String col : models.parser.AbstractParser.PERSONFORMAT)
			message+="\n-"+col;
		return message;
		
		
	};
}
