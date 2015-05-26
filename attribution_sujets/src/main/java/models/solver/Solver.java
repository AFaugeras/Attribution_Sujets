package models.solver;

import models.bean.Model;

public interface Solver {

	public Model solve(String inputFilename, String outputFilename, Model data);
}
