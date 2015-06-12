package models.solver.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import models.solver.adaptor.AdaptorGlpk;

/**
 * Classe d'implementation de InputWriterGlpk
 */
public class InputWriterGlpkImpl implements InputWriterGlpk{
		
	/**
	 * Adapteur des donnees pour Glpk.
	 */
	private AdaptorGlpk ag;
	
	public InputWriterGlpkImpl(AdaptorGlpk ag)
	{
		this.ag = ag;
	}
	
	@Override
	public void write(String pathFile) throws WriterException
	{	
		try {
			FileWriter fw = new FileWriter(pathFile);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write("data;");
			
			bw.newLine();
			bw.newLine();
			
			bw.write(this.ag.getSubjects().toString());
			bw.newLine();
			
			bw.write(this.ag.getPersons().toString());
			bw.newLine();
			bw.newLine();
						
			bw.write(this.ag.getMinCardSubjects().toString());
			bw.newLine();
			
			bw.write(this.ag.getMaxCardSubjects().toString());
			bw.newLine();
			
			bw.write(this.ag.getMultiplicity().toString());
			bw.newLine();
			
			bw.write(this.ag.getMinSizeSubjects().toString());
			bw.newLine();
			
			bw.write(this.ag.getMaxSizeSubjects().toString());
			bw.newLine();
						
			bw.write(this.ag.getMinimumAssignedSubject().toString());
			bw.newLine();
			
			bw.write(this.ag.getChoices().toString());
			
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
