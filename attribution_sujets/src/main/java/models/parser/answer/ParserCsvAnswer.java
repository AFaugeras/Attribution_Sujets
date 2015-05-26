package models.parser.answer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.factory.AnswerFactory;
import models.interfaces.I_Answer;
import models.parser.AbstractParser;
import models.parser.DataCleaner;

/**
 * classe qui s'occupe de parser un fichier csv pour generer des objets r�ponse
 * (Answer)
 * 
 * @author C�dric
 *
 */
public class ParserCsvAnswer extends AbstractParser {

	private List<I_Answer> answerlist; // resultat du parsing sans netoyage

	public ParserCsvAnswer() {
		answerlist = new ArrayList<I_Answer>();
	}

	/**
	 * Parse un fichier de donn�e csv pour creer des object response separator =
	 * tabulation
	 * 
	 * @param sourceFile
	 * @throws IOException
	 */
	public void parseAnswer(File sourceFile) throws IOException {
		List<String> datas = this.readfile(sourceFile); // recuperation des
														// donn�es sous forme
														// tableau de string
		int size = datas.size(); // compte le nombre total de ligne dans le
									// fichier
		int index; // pour se deplacer dans le tableau de donn�es source nombre
					// de champ maximum d'une r�ponse
		String[] line = new String[datas.get(0).split("	").length]; // donne la
																	// taille
																	// max du
																	// tableau
		// on va parcourir chaque lignes et creer un objet reponse contenant les
		// informations ad�quates
		for (index = 1; index < size; index++) {
			String data = datas.get(index);
			line = data.split("	");
			I_Answer response = AnswerFactory.createAnswer(line);// on creer un
																	// objet
																	// reponse
			answerlist.add(response);
		}

	}

	/**
	 * renvoie les donn�es nettoy�es, suppression des doublons
	 * 
	 * @return
	 */
	public List<I_Answer> getCleanedData() {
		return DataCleaner.cleanAnswers(this.answerlist);
	}
	
	
}
