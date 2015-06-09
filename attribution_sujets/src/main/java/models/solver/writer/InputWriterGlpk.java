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
			bw.newLine();
			
			bw.write(data.getSubjects().toString());
			bw.newLine();
			
			bw.write(data.getPersons().toString());
			bw.newLine();
			bw.newLine();
						
			bw.write(data.getMinCardSubjects().toString());
			bw.newLine();
			
			bw.write(data.getMaxCardSubjects().toString());
			bw.newLine();
			
			bw.write(data.getMultiplicity().toString());
			bw.newLine();
			
			bw.write(data.getMinSizeSubjects().toString());
			bw.newLine();
			
			bw.write(data.getMaxSizeSubjects().toString());
			bw.newLine();
						
			bw.write(data.getMinimumAssignedSubject().toString());
			bw.newLine();
			
			bw.write(data.getChoices().toString());
			
			bw.newLine();
			bw.newLine();
			bw.newLine();
			
			bw.write("end;");
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			throw new WriterException("Erreur : Ecriture du fichier entree de choco");
		}				
	}
}
