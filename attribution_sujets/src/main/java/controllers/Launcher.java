package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import models.bean.Model;
import views.MainFrame;
import views.configuration.ConfigurationPanel;
import views.processing.ProcessingPanel;
import views.result.ResultPanel;
import controllers.constraints.ConstraintsCtrl;
import controllers.dataselection.DataSelectionPanelCtrl;
import controllers.solver.SolverSelectionPanelCtrl;
import controllers.subjects.SubjectsConfigurationCtrl;

public class Launcher implements ActionListener {

	private Model model;
	private MainFrame view;

	private SolverSelectionPanelCtrl solverCtrl;
	private ConstraintsCtrl constraintsCtrl;
	private SubjectsConfigurationCtrl subjectsCtrl;
	private DataSelectionPanelCtrl dataSelectionCtrl;

	public Launcher(Model model, MainFrame view) {
		this.model = model;
		this.view = view;

		initializeReactions();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (actionCommand.equals(ConfigurationPanel.JB_DIVIDE_ACTION)) {
			solvingAsked();
		} else if (actionCommand.equals(ResultPanel.JB_BACK_ACTION)) {
			returnToConfiguration();
		}
	}

	private void solvingAsked() {
		if (!isErrors()) {
			this.constraintsCtrl.saveToModel();
			this.subjectsCtrl.saveToModel();

			JDialog jd = new JDialog(this.view);
			jd.getContentPane().add(new ProcessingPanel());
			jd.pack();
			jd.setLocationRelativeTo(this.view);
			jd.setVisible(true);

			new SolvingWorker(this.solverCtrl.getSelectedSolver(), this.model, this.dataSelectionCtrl.getCampusFile(), this.dataSelectionCtrl.getPersonsFile(), this.view, jd).execute();
		}
	}

	private void returnToConfiguration() {
		this.view.showConfigurationPanel();
	}

	private void initializeReactions() {
		this.subjectsCtrl = new SubjectsConfigurationCtrl(this.model.getSubjects(), this.view.getConfigurationPanel().getSubjectsPanel());

		this.solverCtrl = new SolverSelectionPanelCtrl(this.view.getConfigurationPanel().getSolverSelectionPanel());

		this.constraintsCtrl = new ConstraintsCtrl(this.model.getConstraint(),
				this.view.getConfigurationPanel().getSolverParametersPanel(),
				this.view.getConfigurationPanel().getCampusConstraintsPanel(),
				this.view.getConfigurationPanel().getWeightsConfigurationPanel());

		this.dataSelectionCtrl = new DataSelectionPanelCtrl(this.view.getConfigurationPanel().getDataSelectionPanel());

		this.view.getConfigurationPanel().getJbDivide().addActionListener(this);
		this.view.getResultPanel().getJbBack().addActionListener(this);
	}

	private boolean isErrors() {
		boolean ret = false;

		if (!this.subjectsCtrl.isIdsUnique()) {
			JOptionPane.showMessageDialog(null,
					"Les identifiants ne sont pas uniques", "Error",
					JOptionPane.ERROR_MESSAGE);
			ret = true;
		}

		if (!this.dataSelectionCtrl.isCampusFileExists()) {
			JOptionPane.showMessageDialog(null,
					"Fichier campus non renseigné ou non existant.", "Error",
					JOptionPane.ERROR_MESSAGE);
			ret = true;
		}

		if (!this.dataSelectionCtrl.isPersonFileExists()) {
			JOptionPane.showMessageDialog(null,
					"Liste des personnes non renseignés ou non existante.",
					"Error", JOptionPane.ERROR_MESSAGE);
			ret = true;
		}

		return ret;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new Launcher(new Model(), new MainFrame());
			}
		});
	}
}