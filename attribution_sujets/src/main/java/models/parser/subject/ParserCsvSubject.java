package models.parser.subject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.bean.Subject;
import models.factory.SubjectFactory;
import models.parser.AbstractParser;



/**
 * Classe d�dier a la recup�ration d'une liste de sujet et de son param�trage sous format CSV.
 */
public class ParserCsvSubject extends AbstractParser {

	List<Subject> subjectList;

	public ParserCsvSubject() {
		this.subjectList= new ArrayList<Subject>();
	}
	
	
	public void ParseSubjectList(File sourceFile) throws IOException{
		List<String> datas = this.readfile(sourceFile);
		
		int size = datas.size(); 						// compte le nombre total de ligne dans le fichier
		int index; 										// pour se deplacer dans le tableau de donn�es source nombre de champ maximum d'une r�ponse
		String[] line = new String[datas.get(0).split(";").length]; // donne la taille max du tableau
		// on va parcourir chaque lignes et creer un objet Subject contenant les informations ad�quates
		for (index = 1; index < size; index++) {
			String data = datas.get(index);
			line = data.split(";");
			Subject subject = SubjectFactory.createSubject(line, index);// on creer un objet Subject
			subjectList.add(subject); 
		}
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}
	
}
