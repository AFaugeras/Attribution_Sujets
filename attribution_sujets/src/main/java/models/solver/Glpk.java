package models.solver;

import java.io.IOException;
import java.net.URL;

import models.bean.Model;
import models.exception.ModelException;
import models.solver.adaptor.AdaptorGlpk;
import models.solver.adaptor.AdaptorGlpkImpl;
import models.solver.reader.NotFoundSolutionException;
import models.solver.reader.SolutionReaderGlpk;
import models.solver.writer.InputWriterGlpk;


public class Glpk implements Solver{
	
	
	private Model data;
	
	public Glpk(Model date){
		this.data = data;
	}
	
	@Override
	public Model solve(Model data)
			throws SolverException, ModelException {
		
		boolean correct = this.checkMultiplicity(data);
		
		if(!correct){
			throw new ModelException("Multiplicité incompatible avec le nombre d'élèves.");
		}
		
		this.generateInputFile(data);
		
		URL modelFile = this.getClass().getClassLoader().getResource("glpk/affectation-modele.mod");
	
		String[] cmd = {"cmd", "/c", "glpsol", "-m", modelFile.getPath().substring(1), "-d", inputFilename, "-w", outputFilename };
		
		Process p;
		try {
			p = Runtime.getRuntime().exec(cmd);
			p.waitFor();
		} catch (IOException e) {
			throw new SolverException("commande glpsol introuvable, vérifiez votre PATH");
		} catch (InterruptedException e) {
			throw new SolverException("processus interrompue");
		}
		
		this.readSolutionFile(data);
		
		return data;
	}
	
	/**
	 * Verification que le nombre d'eleve est un multiple de la multiplicite.
	 * @param data model de donnees
	 * @return true si la multiplicite est satisfaisante. False sinon.
	 */
	private boolean checkMultiplicity(Model data){		
		int multiplicity = data.getConstraint().getMultiplicity();
		
		if(multiplicity != 0 && data.getPersons().size() % multiplicity != 0){
			return false;
		}
		
		return true;
	}

	@Override
	public void generateInputFile(Model data)
			throws SolverException, ModelException {
		
		boolean correct = this.checkMultiplicity(data);
		
		if(!correct){
			throw new ModelException("Multiplicité incompatible avec le nombre d'élèves.");
		}
		
		InputWriterGlpk iwg = new InputWriterGlpk(data);
		
		iwg.write(Glpk.);
		
	}

	@Override
	public Model readSolutionFile(Model data)
			throws SolverException {
		
		SolutionReaderGlpk.read(solutionFilename, data);
		
		return data;
		
	}

}
