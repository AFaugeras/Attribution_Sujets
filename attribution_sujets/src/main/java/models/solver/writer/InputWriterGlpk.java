package models.solver.writer;

/**
 * Ecrivain du fichier d'entree pour Glpk
 */
public interface InputWriterGlpk extends InputWriter{

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
	 * @param pathFile chemin du fichier a ecrire
	 * @throws WriterException Erreur d'ecriture
	 */
	public void write(String pathFile) throws WriterException;
}
