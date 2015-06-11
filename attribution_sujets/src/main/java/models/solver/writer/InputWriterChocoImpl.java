package models.solver.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import models.bean.Model;
import models.solver.adaptor.AdaptorChoco;
import models.solver.adaptor.AdaptorChocoImpl;

/**
 * Classe d'implementation de InputWriterChoco
 */
public class InputWriterChocoImpl implements InputWriterChoco{
		
	/**
	 * Adapteur des donnees pour Choco.
	 */
	private AdaptorChoco ac;
	
	public InputWriterChocoImpl(AdaptorChoco ac){
		this.ac = ac;
	}
	
	@Override
	public void write(String pathFile) throws WriterException {
		
		try {
			FileWriter fw = new FileWriter(pathFile);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("/* nb eleves */\n");
			bw.write(this.ac.getNbPersons().toString());
			
			bw.write("\n/* nb sujets */\n");
			bw.write(this.ac.getNbSubjects().toString());
			
			bw.write("\n/* pour chaque sujet de 1 a n, nb min de groupe puis nb max de groupes */\n");
			bw.write(this.ac.getCardRange().toString());
			
			bw.write("\n/* effectif min et effectif max des groupes pour chaque sujet */\n");
			bw.write(this.ac.getSizeRange().toString());
			
			bw.write("\n/* nb min de sujets effectivement affectés */\n");
			bw.write(this.ac.getMinimumAssignedSubject().toString());
			
			bw.write("\n/* nombre puis valeurs des pénalités (coûts des affectations) */\n");
			bw.write(this.ac.getRepartitionCost().toString());
			
			bw.write("\n/* nom prenom, nb choix, rang sujet 1.. Rang sujet n */\n");
			bw.write(this.ac.getChoices().toString());
			
			bw.write("\n/* pour chaque eleve, nb puis liste des sujets refusés */\n");
			bw.write(this.ac.getRejects().toString());
			
			bw.write("\n/* multiplicite des groupes */\n");
			bw.write(this.ac.getMultiplicity().toString());
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			throw new WriterException("Erreur : Ecriture du fichier entree de choco");
		}				
	}

}
