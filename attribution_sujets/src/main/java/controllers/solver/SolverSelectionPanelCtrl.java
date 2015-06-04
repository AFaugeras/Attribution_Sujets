package controllers.solver;

import models.solver.Choco;
import models.solver.Glpk;
import models.solver.Solver;
import views.configuration.solver.SolverSelectionPanel;

public class SolverSelectionPanelCtrl {

	private SolverSelectionPanel view;

	public SolverSelectionPanelCtrl(SolverSelectionPanel view) {
		this.view = view;
	}

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
