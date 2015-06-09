package models.solver;

import models.bean.Model;
import models.exception.ModelException;
import models.solver.adaptor.AdaptorChoco;
import models.solver.adaptor.AdaptorChocoImpl;
import models.solver.reader.SolutionReaderChoco;
import models.solver.writer.InputWriterChoco;
import resolution.Ipipip;


/**
 * Classe d'implementation de resolution via le solveur Choco.
 */
public class Choco implements Solver {
	
	@Override
	public Model solve(String inputFilename, String outputFilename, Model data) throws SolverException, ModelException{
		AdaptorChoco ac = new AdaptorChocoImpl(data);
		
		InputWriterChoco.write(inputFilename, ac);
		
		String[] args = new String[2];
		args[0] = inputFilename;
		args[1] = outputFilename;
		Ipipip.main(args);
		
		SolutionReaderChoco.read(outputFilename, data);
		
		return data;
		
		
		
		
		
	}

	@Override
	public void generateInputFile(String inputFilename, Model data)
			throws SolverException, ModelException {
		
		AdaptorChoco ac = new AdaptorChocoImpl(data);
		
		InputWriterChoco.write(inputFilename, ac);
		
	}

	@Override
	public Model readSolutionFile(String solutionFilename, Model data)
			throws SolverException {
		
		SolutionReaderChoco.read(solutionFilename, data);
		
		return data;
	}
}
