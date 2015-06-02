package models.solver;

import models.bean.Model;
import models.solver.reader.NotFoundSolutionException;
import models.solver.reader.ReaderException;
import models.solver.writer.WriterException;

public class Glpk implements Solver{

	@Override
	public Model solve(String inputFilename, String outputFilename, Model data)
			throws WriterException, ReaderException, NotFoundSolutionException {
		// TODO Auto-generated method stub
		return null;
	}

}
