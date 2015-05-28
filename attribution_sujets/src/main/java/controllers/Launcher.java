package controllers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import models.bean.Constraints;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import models.parser.BeanMatcher;
import models.parser.answer.ParserCsvAnswer;
import models.parser.user.ParserCsvUserList;
import models.solver.Choco;
import views.MainFrame;
import views.configuration.ConfigurationPanel;
import views.processing.ProcessingPanel;
import views.result.ResultPanel;
import controllers.constraints.ConstraintsCtrl;
import controllers.dataSelection.DataSelectionPanelCtrl;
import controllers.subjects.SubjectsConfigurationCtrl;

public class Launcher implements ActionListener {
	
	private class Worker extends SwingWorker<Boolean, Void> {
		
		private JDialog dialog;
		
		public Worker(JDialog dialog) {
			this.dialog = dialog;
		}
		
		@Override
		protected Boolean doInBackground() throws Exception {
			boolean ret = false;
			Choco solver = new Choco();
			
			long time = System.currentTimeMillis();
			while(System.currentTimeMillis() - time < 5000) {
				
			}
			
			solver.solve("./fichier.txt", "./src/test/resources/ChocoSol", Launcher.this.model);
			ret = true;
			
			return ret;
		}
		
		@Override
		protected void done() {
			try {
				if(get()) {
					Launcher.this.view.getResultPanel().setModel(Launcher.this.model);
					Launcher.this.view.showResultPanel();
					this.dialog.setVisible(false);
				}
				
				this.dialog.setVisible(false);
				
			} catch (Exception e) {
				// TODO : Démo du 28/05/2015
				Launcher.this.view.getResultPanel().setModel(Launcher.this.model);
				Launcher.this.view.showResultPanel();
				this.dialog.setVisible(false);				
				
				// TODO : A décommenter.
//				e.printStackTrace();
//				this.dialog.setVisible(false);
//				JOptionPane.showMessageDialog(Launcher.this.view, "Pas de solution trouvée", "Pas de solution", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

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
				parserAwnser
						.parseAnswer(this.dataSelectionCtrl.getCampusFile());
				parserPerson.ParseUserList(this.dataSelectionCtrl
						.getPersonsFile());

				this.model.setPersons(parserPerson.getUserList());

				matcher = new BeanMatcher(parserPerson.getUserList(),
						parserAwnser.getCleanedData(),
						this.model.getSubjects(), this.model.getConstraint());

				matcher.match();

//				this.view.getResultPanel().setModel(this.model);

				JDialog jd = new JDialog();
				jd.setTitle("Répartition en cours");
				jd.getContentPane().setLayout(new GridBagLayout());
				jd.getContentPane().add(new ProcessingPanel(), new GridBagConstraints());
				jd.pack();
				jd.setLocationRelativeTo(this.view);
				jd.setVisible(true);
				
				//TODO : Démo du 28/05/2015
				new Worker(jd).execute();

			} catch (Exception exp) {
				exp.printStackTrace();
				JOptionPane.showMessageDialog(null,
						"erreur : " + exp.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}

			System.out.println(this.model);
		}
	}

	private void returnToConfiguration() {
		this.view.showConfigurationPanel();
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

		Constraints gc = new Constraints(0, 0, 0, 0);
		List<Subject> subjects = new ArrayList<Subject>();
		List<Person> persons = new ArrayList<Person>();
		Model mockModel = new Model(gc, persons, subjects);

		new Launcher(mockModel, new MainFrame());
	}
}