package models.parser.subject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.bean.Subject;
import models.exception.fileformatexception.FileException;
import models.exception.fileformatexception.FileFormatException;
import models.exception.fileformatexception.NotFoundFileException;
import models.factory.SubjectFactory;
import models.parser.AbstractParser;



/**
 * Classe dédiée a la recupération d'une liste de sujet et de son paramétrage sous format CSV.
 */
public class ParserCsvSubject extends AbstractParser {

	List<Subject> subjectList;

	public ParserCsvSubject() {
		this.subjectList= new ArrayList<Subject>();
	}
	
	/**
	 * Demande de Parse d'une liste CSV de Sujet avec leur paramétrage
	 * @param sourceFile
	 * @throws IOException
	 * @throws FileFormatException 
	 */
	public void ParseSubjectList(File sourceFile) throws FileException{
		List<String> datas;
		try {
			datas = AbstractParser.readfile(sourceFile);
		
		int size = datas.size(); 						// compte le nombre total de ligne dans le fichier
		int index; 										// pour se deplacer dans le tableau de données source nombre de champ maximum d'une réponse
		String[] line = new String[datas.get(0).split(SUBJECTSPLIT).length]; // donne la taille max du tableau
		checkFormat(SUBJECT, datas.get(0).split(SUBJECTSPLIT));
		// on va parcourir chaque lignes et creer un objet Subject contenant les informations adéquates
		for (index = 1; index < size; index++) {
			String data = datas.get(index);
			line = data.split(SUBJECTSPLIT);
			Subject subject = SubjectFactory.createSubject(line, Integer.valueOf(line[0]));// on creer un objet Subject
			subjectList.add(subject); 
		}
		} catch (IOException e) {
			throw new NotFoundFileException();
		}
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}
	
	/**
	 * Vérifie que le format du fichier est correct
	 * @param file
	 * @return boolean
	 * @throws IOException
	 */
	public static boolean checkFormat(File file) throws FileException{
		
		List<String> datas;
		try {
			datas = readfile(file);
		
			String[] line = new String[datas.get(0).split(SUBJECTSPLIT).length]; 
			try {
				checkFormat(SUBJECT, line);
			} catch (FileFormatException e) {
				return false;
			}
			return true;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			throw new NotFoundFileException();
		}
		
	}
	
}
