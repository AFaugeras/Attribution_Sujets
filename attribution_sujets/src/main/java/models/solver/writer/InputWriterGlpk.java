package models.solver.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import models.solver.adaptor.AdaptorGlpk;

/**
 * Classe d'ecriture du fichier d'entree du Solver Glpk.
 */
public class InputWriterGlpk{
	
	/**
	 * Methode d'ecriture d'un fichier d'entree de Glpk.
	 * @param filename nom du fichier de sortie
	 * @param data model de donnees sur lequel sera base ce fichier
	 * @throws WriterException Erreur d'ecriture
	 */
	public static void write(String filename, AdaptorGlpk data) throws WriterException {
		
		try {
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("data;");
			bw.newLine();
			
			bw.write("\n" + data.getSubjects().toString());
			bw.write("\n" + data.getPersons().toString());
			
			bw.newLine();
			
			bw.write("\n" + data.getMinCardSubjects().toString());
			bw.write("\n" + data.getMaxCardSubjects().toString());
						
			bw.write("\n" + data.getMinSizeSubjects().toString());
			bw.write("\n" + data.getMaxSizeSubjects().toString());
			
			bw.newLine();
			
			bw.write("\n" + data.getMinimumAssignedSubject().toString());
			bw.write("\n" + data.getChoices().toString());
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			throw new WriterException("Erreur : Ecriture du fichier entree de choco");
		}				
	}

}
