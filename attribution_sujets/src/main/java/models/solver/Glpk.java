package models.solver;

import java.io.IOException;
import java.net.URL;

import models.bean.Model;
import models.exception.ModelException;
import models.solver.adaptor.AdaptorGlpk;
import models.solver.adaptor.AdaptorGlpkImpl;
import models.solver.reader.SolutionReaderGlpk;
import models.solver.reader.SolutionReaderGlpkImpl;
import models.solver.writer.InputWriterGlpk;
import models.solver.writer.InputWriterGlpkImpl;


public class Glpk implements Solver{
	
	/**
	 * Chemin du fichier d'entree de Glpk.
	 */
	private static final String INPUT_FILENAME_GLPK = "inputGlpk.txt";
	
	/**
	 * Fichier de sortie ou fichier solution de Glpk.
	 */
	private static final String OUTPUT_FILENAME_GLPK = "outputGlpk.txt";
	
	/**
	 * Modele de donnees.
	 */
	private Model data;
	
	/**
	 * Writer du fichier d'entree
	 */
	private InputWriterGlpk iwg;
	
	/**
	 * Reader du fichier de sortie (solution).
	 */
	private SolutionReaderGlpk srg;
	
	/**
	 * Constructeur du solveur Glpk. C'est ici que le choix des classes d'implementations utilisees a lieu.
	 * @param data modele de donnes
	 */
	public Glpk(Model data)
	{
		this.data = data;
		
		//Possiblite de modifier les classes d'implementations utilisees.
		AdaptorGlpk ag = new AdaptorGlpkImpl(this.data);
		
		this.iwg = new InputWriterGlpkImpl(ag);
		this.srg = new SolutionReaderGlpkImpl(data);	
	}
	
	@Override
	public void solve() throws SolverException, ModelException
	{	
		this.generateInputFile();
		
		URL modelFile = this.getClass().getClassLoader().getResource("glpk/affectation-modele.mod");
	
		String[] cmd = {"cmd", "/c", "glpsol", "-m", modelFile.getPath().substring(1), "-d", Glpk.INPUT_FILENAME_GLPK, "-w", Glpk.OUTPUT_FILENAME_GLPK};
		
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (IOException e) {
			throw new SolverException("commande glpsol introuvable, vérifiez votre PATH");
		} catch (InterruptedException e) {
			throw new SolverException("processus interrompue");
		}
		
		this.readSolutionFile();
	}
	
	/**
	 * Verification que le nombre d'eleve est un multiple de la multiplicite.
	 * @param data model de donnees
	 * @return true si la multiplicite est satisfaisante. False sinon.
	 */
	private boolean checkMultiplicity(Model data)
	{		
		int multiplicity = data.getConstraint().getMultiplicity();
		
		if(multiplicity != 0 && data.getPersons().size() % multiplicity != 0){
			return false;
		}
		
		return true;
	}

	@Override
	public void generateInputFile() throws SolverException, ModelException
	{
		boolean correct = 		this.checkMultiplicity(this.data);
		
		if(!correct){
			throw new ModelException("Multiplicité incompatible avec le nombre d'élèves.");
		}
		
		this.iwg.write(Glpk.INPUT_FILENAME_GLPK);
		
	}

	@Override
	public void readSolutionFile() throws SolverException
	{
		this.srg.read(Glpk.OUTPUT_FILENAME_GLPK);		
	}

}
