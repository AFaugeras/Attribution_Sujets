package parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import factory.AnswerFactory;
import models.interfaces.I_Answer;
/**
 * classe qui s'occupe de parser un fichier csv pour generer des objets réponse (Answer)
 * @author Cédric
 *
 */
public class ParserCsvAnswer {

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
	public void parseAnswer(File sourceFile) throws IOException {
		List<String> datas = this.readfile(sourceFile); // recuperation des
														// données sous forme
														// tableau de string
		int size = datas.size(); // compte le nombre total de ligne dans le
									// fichier
		int index; // pour se deplacer dans le tableau de données source nombre de champ maximum d'une réponse
		String[] line = new String[datas.get(1).split("	").length]; // donne la taille max du tableau
		// on va parcourir chaque lignes et creer un objet reponse contenant les
		// informations adéquates
		for (index = 2; index < size; index++) {
			String data = datas.get(index);
			line = data.split("	");
			I_Answer response = AnswerFactory.createResponse(line);// on creer un objet reponse
			answerlist.add(response); 
		}
		
	}

	/**
	 * permet de renvoyer les données d'un fichier ligne a ligne dans un tableau
	 * de chaine de caractéres
	 * 
	 * @param sourceFile
	 * @return
	 * @throws IOException
	 */
	private List<String> readfile(File sourceFile) throws IOException {

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
	 * renvoie les données nettoyées, suppression des doublons
	 * @return
	 */
	public List<I_Answer>getCleanedData(){
		return DataCleaner.cleanResponse(this.answerlist);
	}
}
