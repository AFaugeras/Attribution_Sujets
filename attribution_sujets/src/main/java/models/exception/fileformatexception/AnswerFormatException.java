package models.exception.fileformatexception;

public class AnswerFormatException extends FileFormatException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		String message ="Format de fichier CSV incompatible (Liste de choix Campus).\n"
				+ "Format en vigeur:";
		for(String col : models.parser.AbstractParser.AnswerFormat)
			message+="\n-"+col;
		return message;
		
	};
}
