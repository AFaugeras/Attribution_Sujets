package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import models.bean.Model;
import models.exception.NoDefineSubjectException;
import models.exception.NoUserFoundedException;
import models.exception.fileformatexception.FileFormatException;
import models.parser.BeanMatcher;
import models.parser.answer.ParserCsvAnswer;
import models.parser.user.ParserCsvUserList;
import models.solver.reader.NotFoundSolutionException;
import models.solver.reader.ReaderException;
import models.solver.writer.WriterException;
import views.MainFrame;
import views.configuration.ConfigurationPanel;
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

		if (actionCommand.equals(ConfigurationPanel.JB_NEXT_ACTION)) {
			solvingAsked();
		} else if (actionCommand.equals(ResultPanel.JB_BACK_ACTION)) {
			returnToConfiguration();
		}
	}

	private void solvingAsked() {
		if (!isErrors()) {
			this.constraintsCtrl.saveToModel();
			this.subjectsCtrl.saveToModel();

			ParserCsvAnswer parserAwnser = new ParserCsvAnswer();
			ParserCsvUserList parserPerson = new ParserCsvUserList();
			BeanMatcher matcher;

			
				try {
					parserAwnser.parseAnswer(this.dataSelectionCtrl.getCampusFile());
					parserPerson.ParseUserList(this.dataSelectionCtrl.getPersonsFile());

					matcher = new BeanMatcher(parserPerson.getUserList(),
							parserAwnser.getCleanedData(),
							this.model.getSubjects(),
							this.model.getConstraint());
					
					this.model.setPersons(parserPerson.getUserList());

					matcher.match();
					
					this.solverCtrl.getSelectedSolver().solve("input.txt", "output.txt", this.model);
					
					this.view.getResultPanel().setModel(this.model);
					this.view.showResultPanel();
					
				} catch (IOException e) {
					displayErrorMessage("Erreur à la lecture du fichier.");
				} catch (FileFormatException e) {
					displayErrorMessage("Erreur CSV :\n" + e.getMessage());
				} catch (NoDefineSubjectException e) {
					displayErrorMessage("Un ou plusieurs sujets ne sont pas définis :\n" + e.getMessage());
				} catch (NoUserFoundedException e) {
					displayErrorMessage(/*"L'utilisateur " + */e.getMessage()/* + " n'est pas présent dans le fichier d'élèves."*/);
				} catch (WriterException e) {
					displayErrorMessage("Erreur lors de la génération du fichier d'entrée.");
				} catch (ReaderException e) {
					displayErrorMessage("Erreur lors de la lecture du fichier solution.");
				} catch (NotFoundSolutionException e) {
					displayErrorMessage("Aucune solution trouvée.");
				}
		}
	}

	private void returnToConfiguration() {
		this.view.showConfigurationPanel();
	}

	private void initializeReactions() {
		this.subjectsCtrl = new SubjectsConfigurationCtrl(this.model, 
				this.view.getConfigurationPanel().getSubjectsPanel());
		
		this.solverCtrl = new SolverSelectionPanelCtrl(this.view.getConfigurationPanel().getSolverSelectionPanel());
		
		this.constraintsCtrl = new ConstraintsCtrl(this.model.getConstraint(),
				this.view.getConfigurationPanel().getBoundConstraintsPanel(),
				this.view.getConfigurationPanel().getCampusConstraintsPanel(),
				this.view.getConfigurationPanel().getWeightsConfigurationPanel());
		
		this.dataSelectionCtrl = new DataSelectionPanelCtrl(
				this.view.getConfigurationPanel().getDataSelectionPanel());

		this.view.getConfigurationPanel().getJbNext().addActionListener(this);
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
	
	private void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this.view, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Launcher(new Model(), new MainFrame());
	}
}