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
 * classe qui s'occupe de parser un fichier csv pour generer des objets r�ponse (Answer)
 * @author C�dric
 *
 */
public class ParserCsvAnswer {

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
		int index; // pour se deplacer dans le tableau de donn�es source nombre de champ maximum d'une r�ponse
		String[] line = new String[datas.get(1).split("	").length]; // donne la taille max du tableau
		// on va parcourir chaque lignes et creer un objet reponse contenant les
		// informations ad�quates
		for (index = 2; index < size; index++) {
			String data = datas.get(index);
			line = data.split("	");
			I_Answer response = AnswerFactory.createResponse(line);// on creer un objet reponse
			answerlist.add(response); 
		}
		
	}

	/**
	 * permet de renvoyer les donn�es d'un fichier ligne a ligne dans un tableau
	 * de chaine de caract�res
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
	 * renvoie les donn�es nettoy�es, suppression des doublons
	 * @return
	 */
	public List<I_Answer>getCleanedData(){
		return DataCleaner.cleanResponse(this.answerlist);
	}
}
