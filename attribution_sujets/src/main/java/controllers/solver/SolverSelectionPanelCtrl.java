package controllers.solver;

import models.bean.Model;
import models.solver.Choco;
import models.solver.Glpk;
import models.solver.Solver;
import views.configuration.solver.SolverSelectionPanel;

/**
 * Contr�leur du panel de s�lection du solveur.
 */
public class SolverSelectionPanelCtrl {

	/**
	 * Le mod�le.
	 */
	private Model model;
	
	/**
	 * La vue.
	 */
	private SolverSelectionPanel view;

	/**
	 * Constructeur.
	 * 
	 * @param view La vue.
	 */
	public SolverSelectionPanelCtrl(Model model, SolverSelectionPanel view) {
		this.model = model;
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
				ret = new Choco(this.model);
				break;
			case SolverSelectionPanel.GLPK_SOLVER:
				ret = new Glpk(this.model);
				break;
		}
		
		return ret;
	}
}
