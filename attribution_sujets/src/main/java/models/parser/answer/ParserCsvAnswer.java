package models.parser.answer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.exception.fileformatexception.FileException;
import models.exception.fileformatexception.FileFormatException;
import models.exception.fileformatexception.NotFoundFileException;
import models.factory.AnswerFactory;
import models.interfaces.I_Answer;
import models.parser.AbstractParser;
import models.parser.DataCleaner;

/**
 * Classe qui s'occupe de parser un fichier csv pour generer des objets réponse
 * (Answer).
*/
public class ParserCsvAnswer extends AbstractParser {

	private List<I_Answer> answerlist; // resultat du parsing sans netoyage

	public ParserCsvAnswer() {
		answerlist = new ArrayList<I_Answer>();
	}

	/**
	 * Parse un fichier de donnée csv pour creer des object response separator =
	 * tabulation
	 * 
	 * @param sourceFile
	 * @throws IOException
	 */
	public void parseAnswer(File sourceFile) throws FileException {
		try {
			List<String> datas = this.readfile(sourceFile); // recuperation des
														// données sous forme
														// tableau de string
		
		int size = datas.size(); // compte le nombre total de ligne dans le
									// fichier
		int index; // pour se deplacer dans le tableau de données source nombre
					// de champ maximum d'une réponse
		String[] line = new String[datas.get(0).split(ANSWERSPLIT).length]; // donne la
			checkFormat(ANSWER, datas.get(0).split(ANSWERSPLIT));	// on verifie que le format du fichier est correct												// taille
																	// max du
																	// tableau
		// on va parcourir chaque lignes et creer un objet reponse contenant les
		// informations adéquates
		for (index = 1; index < size; index++) {
			String data = datas.get(index);
			line = data.split(ANSWERSPLIT);
			I_Answer response = AnswerFactory.createAnswer(line);// on creer un
																	// objet
																	// reponse
			answerlist.add(response);
		}
		} catch (IOException e) {
			throw new NotFoundFileException();
		}
	}

	/**
	 * renvoie les données nettoyées, suppression des doublons
	 * 
	 * @return
	 */
	public List<I_Answer> getCleanedData() {
		return DataCleaner.cleanAnswers(this.answerlist);
	}
	/**
	 * verifie 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static boolean checkFormat(File file) {
	
		try {List<String> datas = readfile(file);
		String[] line = new String[datas.get(0).split(ANSWERSPLIT).length]; 
		
			checkFormat(ANSWER, line);
		} catch (FileFormatException e) {
			return false;
		}
		catch (IOException e1) {
		return false;
	}
		return true;
	}
	
}
