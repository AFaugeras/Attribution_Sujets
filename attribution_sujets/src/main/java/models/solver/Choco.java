package models.solver;

import models.bean.Model;
import models.exception.ModelException;
import models.solver.adaptor.AdaptorChoco;
import models.solver.adaptor.AdaptorChocoImpl;
import models.solver.reader.SolutionReaderChoco;
import models.solver.reader.SolutionReaderChocoImpl;
import models.solver.writer.InputWriterChoco;
import models.solver.writer.InputWriterChocoImpl;
import resolution.Ipipip;


/**
 * Classe d'implementation de resolution via le solveur Choco.
 */
public class Choco implements Solver {
	
	/**
	 * Chemin du fichier d'entree de Choco.
	 */
	private static final String INPUT_FILENAME_CHOCO = "inputChoco.txt";
	
	/**
	 * Fichier de sortie ou fichier solution de Choco.
	 */
	private static final String OUTPUT_FILENAME_CHOCO = "outputChoco.txt";
	
	/**
	 * Modele de donnees.
	 */
	private Model data;
	
	/**
	 * Writer du fichier d'entree
	 */
	private InputWriterChoco iwc;
	
	/**
	 * Reader du fichier de sortie (solution).
	 */
	private SolutionReaderChoco src;
	
	/**
	 * Constructeur du solveur Choco. C'est ici que le choix des classes d'implementations utilisees a lieu.
	 * @param data modele de donnes
	 */
	public Choco(Model data)
	{		
		//Possibilite de modifier les classes d'implementations.
		this.data = data;
		AdaptorChoco ac = new AdaptorChocoImpl(data);
		
		this.iwc = new InputWriterChocoImpl(ac);
		this.src = new SolutionReaderChocoImpl(data);	
	}
	
	@Override
	public void solve() throws SolverException, ModelException
	{
		this.generateInputFile();
		
		String[] args = new String[2];
		args[0] = Choco.INPUT_FILENAME_CHOCO;
		args[1] = Choco.OUTPUT_FILENAME_CHOCO;
		Ipipip.main(args);
		
		this.readSolutionFile();
			
	}

	@Override
	public void generateInputFile() throws SolverException, ModelException 
	{				
		this.iwc.write(Choco.INPUT_FILENAME_CHOCO);	
	}

	@Override
	public void readSolutionFile() throws SolverException 
	{		
		this.src.read(Choco.OUTPUT_FILENAME_CHOCO);
	}
}
