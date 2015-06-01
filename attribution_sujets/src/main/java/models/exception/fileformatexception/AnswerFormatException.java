package models.exception.fileformatexception;

public class AnswerFormatException extends FileFormatException{

	public String getMessage() {
		String message ="Format de fichier incompatible avec une liste de reponse campus.\n"
				+ "Format en vigeur:";
		for(String col : models.parser.AbstractParser.AnswerFormat)
			message+="\n-"+col;
		return message;
		
	};
}
