package models.solver;

import java.io.File;

import models.adaptor.AdaptorChoco;
import models.adaptor.AdaptorChocoImpl;
import models.bean.Model;
import models.reader.NotFoundSolutionException;
import models.reader.ReaderException;
import models.reader.SolutionReaderChoco;
import models.writer.InputWriterChoco;
import models.writer.WriterException;

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
//		Ipipip.main(args);
		
		SolutionReaderChoco.read(outputFilename, data);
		
		return data;
		
		
		
		
		
	}
}
