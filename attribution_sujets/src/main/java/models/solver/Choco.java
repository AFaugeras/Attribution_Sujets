package models.solver;

import models.bean.Model;
import models.exception.ModelException;
import models.solver.adaptor.AdaptorChocoImpl;
import models.solver.reader.SolutionReaderChoco;
import models.solver.writer.InputWriterChoco;
import resolution.Ipipip;


/**
 * Classe d'implementation de resolution via le solveur Choco.
 */
public class Choco implements Solver {
	
	/**
	 * Chemin du fichier d'entree du solveur
	 */
	private static final String INPUT_FILENAME_CHOCO = "inputChoco.txt";
	
	/**
	 * Fichier de sortie ou fichier solution du solveur
	 */
	private static final String OUTPUT_FILENAME_CHOCO = "outputChoco.txt";
	
	private InputWriterChoco iwc;
	
	private SolutionReaderChoco src;
	
	public Choco(Model data){		
		this.iwc = new InputWriterChoco(data);
		this.src = new SolutionReaderChoco(data);	
	}
	
	@Override
	public Model solve(Model data) throws SolverException, ModelException{
		this.generateInputFile(data);
		
		String[] args = new String[2];
		args[0] = Choco.INPUT_FILENAME_CHOCO;
		args[1] = Choco.OUTPUT_FILENAME_CHOCO;
		Ipipip.main(args);
		
		this.readSolutionFile(data);
		
		return data;		
	}

	@Override
	public void generateInputFile(Model data)
			throws SolverException, ModelException {
				
		InputWriterChoco iwc = new InputWriterChoco(data);
		
		iwc.write(Choco.INPUT_FILENAME_CHOCO);	
	}

	@Override
	public Model readSolutionFile(Model data)
			throws SolverException {
		
		SolutionReaderChoco src = new SolutionReaderChoco(data);
		
		src.read(Choco.OUTPUT_FILENAME_CHOCO);
		
		return data;
	}
}
