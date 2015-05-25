package models.parser;

import java.util.ArrayList;
import java.util.List;

import models.bean.Person;
import models.interfaces.I_Answer;

/**
 * classe charg�e de supprimer les doublons d'un jeu de donn�es
 * 
 * @author C�dric
 *
 */
public class DataCleaner {

	/**
	 * m�thode charg�e de supprimer les doublons. Regle de gestion : La reponse
	 * la plus r�cente est gard�e, les autres sont supprim�es
	 * 
	 * @param listSource
	 * @return
	 */
	public static List<I_Answer> cleanAnswers(List<I_Answer> listSource) {
		List<I_Answer> datacleaned = new ArrayList<I_Answer>();
		List<String> userCleaned = new ArrayList<String>(); // liste des
															// utilisateurs qui
															// ont �t� trait�s.
		for (I_Answer answerOne : listSource) {
			I_Answer lastAnswer = answerOne;
			if (!userCleaned.contains(lastAnswer.getIdRepondant())) {
				for (I_Answer answertwo : getAllResponsesFromSameUser(
						answerOne.getIdRepondant(), listSource)) {
					lastAnswer = lastAnswer.wichIsTheLater(answertwo); // on
																		// garde
																		// en
					 													// m�moire
																		// celle
																		// qui
																		// est
																		// la
																		// plus
																		// tard
				}
				userCleaned.add(lastAnswer.getIdRepondant());// on enregistre
																// l'id de
																// l'utilisateur
																// trait�
				datacleaned.add(lastAnswer);// on enregistre sa derni�re r�ponse
											// dans un tableau
			}
		}

		return datacleaned;
	}

	/**
	 * methode ramenant toutes les r�ponses d'un utilisateur
	 * 
	 * @param id
	 * @param listeResponse
	 * @return liste de reponse
	 */
	private static List<I_Answer> getAllResponsesFromSameUser(String id,
			List<I_Answer> listeResponse) {
		List<I_Answer> sameUserAnswer = new ArrayList<I_Answer>();
		// on recup�re toutes les r�ponses d'un meme user
		for (I_Answer Reponse : listeResponse) {
			if (Reponse.getIdRepondant().equals(id)) {
				sameUserAnswer.add(Reponse);
			}
		}
		return sameUserAnswer;
	}

	public static List<Person> cleanPerson(List<Person> data) {
		List<Person> dataCleaned = new ArrayList<Person>();

		for (Person person : data) {
			if (!dataCleaned.contains(person)) {
				dataCleaned.add(person);
			}
			;

		}

		return dataCleaned;
	}
}
