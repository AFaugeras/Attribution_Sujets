package controllers.solver;

import models.solver.Choco;
import models.solver.Glpk;
import models.solver.Solver;
import views.configuration.solver.SolverSelectionPanel;

/**
 * Contr�leur du panel de s�lection du solveur.
 */
public class SolverSelectionPanelCtrl {

	/**
	 * La vue.
	 */
	private SolverSelectionPanel view;

	/**
	 * Constructeur.
	 * 
	 * @param view La vue.
	 */
	public SolverSelectionPanelCtrl(SolverSelectionPanel view) {
		this.view = view;
	}

	/**
	 * Permet d'obtenir une instance du solveur s�lectionn�.
	 * @return Un solveur.
	 */
	public Solver getSelectedSolver() {
		Solver ret = null;
		String solverName = (String) this.view.getJcbSolvers()
				.getSelectedItem();

		switch (solverName) {
			case SolverSelectionPanel.CHOCO_SOLVER:
				ret = new Choco();
				break;
			case SolverSelectionPanel.GLPK_SOLVER:
				ret = new Glpk();
				break;
		}
		
		return ret;
	}
}
