package models.exception.fileformatexception;


public class SubjectFormatException extends FileFormatException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		String message ="Format de fichier incompatible (Liste de sujet).\n"
				+ "Format en vigeur:";
		for(String col : models.parser.AbstractParser.SUBJECTFORMAT)
			message+="\n-"+col;
		return message;
		
		
	};
}
