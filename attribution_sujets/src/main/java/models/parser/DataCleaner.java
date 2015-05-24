package models.parser;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import models.interfaces.I_Answer;

/**
 * classe charg�e de supprimer les doublons d'un jeu de donn�e
 * @author C�dric
 *
 */
public class DataCleaner {

	
	public static List<I_Answer> cleanResponse(List<I_Answer>listSource){
		List<I_Answer> datacleaned = new ArrayList<I_Answer>();
		List<String> userCleaned = new ArrayList<String>(); // liste des utilisateur qui ont �t� trait�s.
		for (I_Answer answer : listSource) {
			I_Answer lastAnswer = answer;
			if(!userCleaned.contains(lastAnswer.getIdRepondant())){
				for (I_Answer answertwo : getAllResponsesFromSameUser(answer.getIdRepondant(), listSource)) {
					lastAnswer =lastAnswer.wichIsTheEarlier(answertwo);
				}
				userCleaned.add(lastAnswer.getIdRepondant());
				datacleaned.add(lastAnswer);
			}
		}
		
		
		return datacleaned;
	}
	
	private static List<I_Answer> getAllResponsesFromSameUser(String id, List<I_Answer> listeResponse){
		List<I_Answer> sameUserAnswer = new ArrayList<I_Answer>();
		// on recup�re toute les r�ponse d'un meme user
		for (I_Answer Reponse : listeResponse) {
			if(Reponse.getIdRepondant().equals(id)){
				sameUserAnswer.add(Reponse);
			}	
		}
		return sameUserAnswer;
	}
}
