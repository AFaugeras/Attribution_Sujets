package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
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

/**
 * Lanceur et contrôleur principal de l'application.
 */
public class Launcher implements ActionListener {

	/**
	 * Modèle de l'application.
	 */
	private Model model;
	
	/**
	 * Fenêtre de l'application.
	 */
	private MainFrame view;
	
	/**
	 * Contrôleur du panel de configuration des sujets.
	 */
	private SubjectsConfigurationCtrl subjectsCtrl;

	/**
	 * Contrôleur du panel de sélection du solver.
	 */
	private SolverSelectionPanelCtrl solverCtrl;
	
	/**
	 * Contrôleur des panels contraintes (Panel configuration campus et paramètre solver).
	 */
	private ConstraintsCtrl constraintsCtrl;
	
	/**
	 * Contrôleur du panel de sélection du fichier campus et de la liste de personnes.
	 */
	private DataSelectionPanelCtrl dataSelectionCtrl;

	/**
	 * Constructeur.
	 * 
	 * @param model Le modèle.
	 * @param view La vue.
	 */
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

	/**
	 * Méthode privée appellée par le constructeur pour initialiser les contrôleurs.
	 */
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

	/**
	 * Cette méthode privée est appellée au clic sur le bouton "Répartir".
	 */
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

	/**
	 * Méthode appellée au clic sur le bouton "Retour".
	 */
	private void returnToConfiguration() {
		this.view.showConfigurationPanel();
	}

	/**
	 * Méthode privée appellée pour vérifier la cohérence des données saisies. <br/>
	 * Affiche également un popup d'erreur si besoin.
	 * 
	 * @return True si aucune erreur. False sinon.
	 */
	private boolean isErrors() {
		boolean ret = false;

		if (!this.subjectsCtrl.isIdsUnique()) {
			Utils.displayErrorMessage("Les identifiants des sujets ne sont pas uniques.", this.view);
			ret = true;
		}

		if (!this.dataSelectionCtrl.isCampusFileExists()) {
			Utils.displayErrorMessage("Fichier campus non renseigné ou non existant.", this.view);
			ret = true;
		}

		if (!this.dataSelectionCtrl.isPersonFileExists()) {
			Utils.displayErrorMessage("Liste des personnes non renseignées ou non existante.", this.view);
			ret = true;
		}

		return ret;
	}

	/**
	 * Point d'entrée de l'application.
	 */
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