package models.solver;

import resolution.Ipipip;
import models.bean.Model;
import models.solver.adaptor.AdaptorChoco;
import models.solver.adaptor.AdaptorChocoImpl;
import models.solver.reader.NotFoundSolutionException;
import models.solver.reader.ReaderException;
import models.solver.reader.SolutionReaderChoco;
import models.solver.writer.InputWriterChoco;
import models.solver.writer.WriterException;


/**
 * Classe d'implementation de resolution via le solveur Choco.
 */
public class Choco implements Solver {
	
	@Override
	public Model solve(String inputFilename, String outputFilename, Model data) throws WriterException, ReaderException, NotFoundSolutionException{
		AdaptorChoco ac = new AdaptorChocoImpl(data);
		
		InputWriterChoco.write(inputFilename, ac);
		
		String[] args = new String[2];
		args[0] = inputFilename;
		args[1] = outputFilename;
		Ipipip.main(args);
		
		SolutionReaderChoco.read(outputFilename, data);
		
		return data;
		
		
		
		
		
	}
}
