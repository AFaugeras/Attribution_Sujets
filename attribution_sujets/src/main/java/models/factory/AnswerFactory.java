package models.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import models.bean.Answer;
import models.interfaces.I_Answer;

public class AnswerFactory {

	/**
	 * renvoi un objet r�ponse en utilisant un tableau de chaine de caract�re en
	 * entr�e mod�le choisi: 9 entetes fixes :R�ponse, Soumis le ,Institution,
	 * D�partement, Cours, Groupe, ID, Nom complet, Nom d'utilisateur
	 * 
	 * @param data
	 * @return I_Response
	 */

	public static I_Answer createAnswer(String[] data) {
		I_Answer response = new Answer();
		response.setIdReponse(data[0]);
		// formatage de la date
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
			String dateInString = data[1];
			Date date = sdf.parse(dateInString);
			response.setDateSoumission(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		response.setInstitution(data[2]);
		response.setDepartement(data[3]);
		response.setCours(data[4]);
		response.setGroupe(data[5]);
		response.setIdRepondant(data[6]);
		response.setNomPrenom(data[7]);
		response.setUserName(data[8]);

		// enregistrement de la liste de choix.

		ArrayList<String> listChoice = new ArrayList<String>();
		int length = data.length;
		for (int i = 9; i < length; i++) {
			listChoice.add(data[i]);
		}
		response.setChoix(listChoice);

		return response;

	}
}
