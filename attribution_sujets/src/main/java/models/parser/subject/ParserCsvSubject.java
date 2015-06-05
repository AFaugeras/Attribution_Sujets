package models.parser.subject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.bean.Subject;
import models.exception.fileformatexception.FileFormatException;
import models.factory.SubjectFactory;
import models.parser.AbstractParser;



/**
 * Classe d�di�e a la recup�ration d'une liste de sujet et de son param�trage sous format CSV.
 */
public class ParserCsvSubject extends AbstractParser {

	List<Subject> subjectList;

	public ParserCsvSubject() {
		this.subjectList= new ArrayList<Subject>();
	}
	
	/**
	 * Demande de Parse d'une liste CSV de Sujet avec leur param�trage
	 * @param sourceFile
	 * @throws IOException
	 * @throws FileFormatException 
	 */
	public void ParseSubjectList(File sourceFile) throws IOException, FileFormatException{
		List<String> datas = this.readfile(sourceFile);
		int size = datas.size(); 						// compte le nombre total de ligne dans le fichier
		int index; 										// pour se deplacer dans le tableau de donn�es source nombre de champ maximum d'une r�ponse
		String[] line = new String[datas.get(0).split(SUBJECTSPLIT).length]; // donne la taille max du tableau
		checkFormat(SUBJECT, datas.get(0).split(SUBJECTSPLIT));
		// on va parcourir chaque lignes et creer un objet Subject contenant les informations ad�quates
		for (index = 1; index < size; index++) {
			String data = datas.get(index);
			line = data.split(SUBJECTSPLIT);
			Subject subject = SubjectFactory.createSubject(line, Integer.valueOf(line[0]));// on creer un objet Subject
			subjectList.add(subject); 
		}
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}
	
	public static boolean checkFormat(File file) throws IOException{
		boolean ok =true;
		List<String> datas = readfile(file);
		int size = datas.size(); 						// compte le nombre total de ligne dans le fichier
		int index; 										// pour se deplacer dans le tableau de donn�es source nombre de champ maximum d'une r�ponse
		String[] line = new String[datas.get(0).split(SUBJECTSPLIT).length]; 
		try {
			checkFormat(SUBJECT, line);
		} catch (FileFormatException e) {
			return false;
		}
		return true;
	}
	
}
