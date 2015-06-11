package models.exception.fileformatexception;

public class PersonFormatException extends FileFormatException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		String message ="Format de fichier incompatible(List de personne).\n"
				+ "Format en vigeur:";
		for(String col : models.parser.AbstractParser.PERSONFORMAT)
			message+="\n-"+col;
		return message;
		
		
	};
}
