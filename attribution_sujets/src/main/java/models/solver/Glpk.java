package models.solver;

import java.io.IOException;

import models.bean.Model;
import models.solver.adaptor.AdaptorGlpk;
import models.solver.adaptor.AdaptorGlpkImpl;
import models.solver.reader.NotFoundSolutionException;
import models.solver.reader.ReaderException;
import models.solver.reader.SolutionReaderGlpk;
import models.solver.writer.InputWriterGlpk;
import models.solver.writer.WriterException;

public class Glpk implements Solver{

	@Override
	public Model solve(String inputFilename, String outputFilename, Model data)
			throws WriterException, ReaderException, NotFoundSolutionException {

		//TODO Supprimer cette contrainte forcee
		data.getConstraint().getWeights().add(1000000L);
		AdaptorGlpk ag = new AdaptorGlpkImpl(data);
		
		InputWriterGlpk.write(inputFilename, ag);
			
		String[] cmd = { "C:\\Users\\lulu2_000\\Desktop\\glpk-4.55\\w64\\glpsol", "-m", "C:\\Users\\lulu2_000\\Desktop\\intersemestre-modele.mod", "-d", inputFilename, "-o", "./solution.txt", "-w", outputFilename };
		
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		
		SolutionReaderGlpk.read(outputFilename, data);
		
		return data;
	}

}
