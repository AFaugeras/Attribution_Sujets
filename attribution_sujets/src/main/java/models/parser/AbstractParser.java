package models.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.exception.fileformatexception.AnswerFormatException;
import models.exception.fileformatexception.FileFormatException;
import models.exception.fileformatexception.PersonFormatException;
import models.exception.fileformatexception.SubjectFormatException;
/**
 * Methodes communes a tout les parsers
 * 
 *
 */
public abstract class AbstractParser {
	public static final String ANSWER =  "Answer";
	public static final String SUBJECT = "Subject";
	public static final String PERSON =  "Person";
	public static final String[] AnswerFormat ={"Réponse",	
												"Soumis le :",	
												"Institution",
												"Département",
												"Cours",
												"Groupe",	
												"ID",	
												"Nom complet",
												"Nom d'utilisateur"};
	public static final String[] SUBJECTFORMAT={	"NomSujet",
													"maxSize",
													"minSize",
													"multiple",
													"cardMin",
													"cardMax"};
	
	public static final String[] PERSONFORMAT={		"Nom",
													"Prénom 1",
													"Mèl (EMN)",
													"Compte d'accès"};
	/**
	 * permet de renvoyer les données d'un fichier ligne a ligne dans un tableau
	 * de chaine de caractéres
	 * 
	 * @param sourceFile
	 * @return
	 * @throws IOException
	 */
	protected List<String> readfile(File sourceFile) throws IOException {

		List<String> result = new ArrayList<String>();
		FileReader fr = new FileReader(sourceFile);
		BufferedReader br = new BufferedReader(fr);
		// on met chaque ligne du fichier dans un tableau
		for (String line = br.readLine(); line != null; line = br.readLine()) {
			result.add(line);
		}

		br.close();
		fr.close();
		return result;
	}
	
	/**
	 * Fonction permettant de vérifier le format des fichiers d'entrée
	 * @param bean
	 * @param format
	 * @throws FileFormatException
	 */
	public static void checkFormat(String bean, String[] format)throws FileFormatException{
		switch(bean){
		case ANSWER:{
			for (int i = 0; i < AnswerFormat.length; i++) {
				if(!AnswerFormat[i].equals(format[i])) throw new AnswerFormatException();
			}
			break;
		}
		case SUBJECT:{
			for (int i = 0; i < SUBJECTFORMAT.length; i++) {
					if(!SUBJECTFORMAT[i].equals(format[i])) throw new SubjectFormatException();
				
			}
			break;
		}
		case PERSON:{
			
			for (int i = 0; i < PERSONFORMAT.length; i++) {
				if(!PERSONFORMAT[i].equals(format[i])) throw new PersonFormatException();
			}
			break;
		}
		}
		
	}
}
