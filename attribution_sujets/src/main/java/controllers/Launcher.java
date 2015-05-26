package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import models.bean.Constraints;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import models.parser.BeanMatcher;
import models.parser.answer.ParserCsvAnswer;
import models.parser.user.ParserCsvUserList;
import views.MainFrame;
import views.configuration.ConfigurationPanel;
import controllers.constraints.ConstraintsCtrl;
import controllers.dataSelection.DataSelectionPanelCtrl;
import controllers.subjects.SubjectsConfigurationCtrl;

public class Launcher implements ActionListener {

	private Model model;
	private MainFrame view;

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
			if (!isErrors()) {
				this.constraintsCtrl.saveToModel();
				this.subjectsCtrl.saveToModel();

				ParserCsvAnswer parserAwnser = new ParserCsvAnswer();
				ParserCsvUserList parserPerson = new ParserCsvUserList();
				BeanMatcher matcher;

				try {
					parserAwnser.parseAnswer(this.dataSelectionCtrl
							.getCampusFile());
					parserPerson.ParseUserList(this.dataSelectionCtrl
							.getPersonsFile());

					this.model.setPersons(parserPerson.getUserList());

					matcher = new BeanMatcher(parserPerson.getUserList(),
							parserAwnser.getCleanedData(),
							this.model.getSubjects(),
							this.model.getConstraint());

					matcher.match();

					this.view.getPanelResult().setModel(this.model);
					this.view.switchCard();

				} catch (Exception exp) {
					exp.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"erreur : " + exp.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				System.out.println(this.model);
			}
		}
	}

	private void initializeReactions() {
		this.constraintsCtrl = new ConstraintsCtrl(this.model.getConstraint(),
				this.view.getConfigurationPanel().getBoundConstraintsPanel(),
				this.view.getConfigurationPanel().getCampusConstraintsPanel());
		this.subjectsCtrl = new SubjectsConfigurationCtrl(this.model, this.view
				.getConfigurationPanel().getSubjectsPanel());
		this.dataSelectionCtrl = new DataSelectionPanelCtrl(this.view
				.getConfigurationPanel().getDataSelectionPanel());

		this.view.getConfigurationPanel().getJbNext().addActionListener(this);
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

		Constraints gc = new Constraints(0, 0, 0, 0);
		List<Subject> subjects = new ArrayList<Subject>();
		List<Person> persons = new ArrayList<Person>();
		Model mockModel = new Model(gc, persons, subjects);

		new Launcher(mockModel, new MainFrame());
	}
}