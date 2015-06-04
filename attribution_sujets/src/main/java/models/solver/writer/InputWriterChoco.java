package models.solver.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import models.solver.adaptor.AdaptorChoco;

/**
 * Classe d'ecriture du fichier d'entree du Solver Choco.
 */
public class InputWriterChoco{
	
	/**
	 * Methode d'ecriture d'un fichier d'entree de Choco
	 * @param filename nom du fichier de sortie
	 * @param data model de donnees sur lequel sera base ce fichier
	 * @throws WriterException Erreur d'ecriture
	 */
	public static void write(String filename, AdaptorChoco data) throws WriterException {
		
		try {
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("/* nb eleves */\n");
			bw.write(data.getNbPersons().toString());
			
			bw.write("\n/* nb sujets */\n");
			bw.write(data.getNbSubjects().toString());
			
			bw.write("\n/* pour chaque sujet de 1 a n, nb min de groupe puis nb max de groupes */\n");
			bw.write(data.getSizeRange().toString());
			
			bw.write("\n/* effectif min et effectif max des groupes pour chaque sujet */\n");
			bw.write(data.getCardRange().toString());
			
			bw.write("\n/* nb min de sujets effectivement affectés */\n");
			bw.write(data.getMinimumAssignedSubject().toString());
			
			bw.write("\n/* nombre puis valeurs des pénalités (coûts des affectations) */\n");
			bw.write(data.getRepartitionCost().toString());
			
			bw.write("\n/* nom prenom, nb choix, rang sujet 1.. Rang sujet n */\n");
			bw.write(data.getChoices().toString());
			
			bw.write("\n/* pour chaque eleve, nb puis liste des sujets refusés */\n");
			bw.write(data.getRejects().toString());
			
			bw.write("\n/* multiplicite des groupes */\n");
			bw.write(data.getMultiplicity().toString());
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			throw new WriterException("Erreur : Ecriture du fichier entree de choco");
		}				
	}

}
