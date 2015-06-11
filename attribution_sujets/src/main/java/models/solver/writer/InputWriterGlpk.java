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
	 * Methode d'ecriture d'un fichier d'entree de Glpk. Celui-ci suit le format suivant :
	 * data;
	 * 
	 * set projets := 	p0 	p1
	 * set eleves := 	e1 	e2 	e3 	e4
	 * 
	 * param nbMinGpes :=	p0 	0	p1 	0	;
	 * param nbMaxGpes :=	p0 	2	p1 	2	;
	 * param tailleEquipe:=	1	;
	 * param nMin :=	p0 	5	p1 	5	;
	 * param nMax :=	p0 	15	p1 	15	;
	 * param nbMiniSujets :=	1	;
	 * param c :	e0	e1	e2	e3 :=
	 * 	p0	1	5	5	1
	 * 	p1	5	1	1	1
	 * 	;
	 * 
	 * 
	 * end;
	 * 
	 * 
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
