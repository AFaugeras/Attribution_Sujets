package controllers;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import models.bean.Model;
import models.exception.ModelException;
import models.exception.NoDefineSubjectException;
import models.exception.NoUserFoundedException;
import models.exception.fileformatexception.FileException;
import models.parser.BeanMatcher;
import models.parser.answer.ParserCsvAnswer;
import models.parser.user.ParserCsvUserList;
import models.solver.Solver;
import models.solver.SolverException;
import views.MainFrame;

/**
 * Thread de résolution.
 */
public class SolvingWorker extends SwingWorker<Boolean, Void> {

	/**
	 * Le solveur.
	 */
	private Solver solver;
	
	/**
	 * Le modele.
	 */
	private Model model;
	
	/**
	 * Fichier campus.
	 */
	private File campusFile;
	
	/**
	 * Fichier personnes.
	 */
	private File personFile;
	
	/**
	 * Fenêtre principale de l'application.
	 */
	private MainFrame mainFrame;
	
	/**
	 * Popup d'attente.
	 */
	private JDialog dialog;
	
	/**
	 * Constructeur.
	 * 
	 * @param solver Le solveur.
	 * @param model Le modèle.
	 * @param campusFile Le fichier campus.
	 * @param personFile La liste de personnes.
	 * @param mainFrame La fenêtre principale de l'application.
	 * @param dialog Dialog d'attente.
	 */
	public SolvingWorker(Solver solver, Model model, File campusFile, File personFile, MainFrame mainFrame, JDialog dialog) {
		super();
		
		this.solver = solver;
		this.model = model;
		this.campusFile = campusFile;
		this.personFile = personFile;
		this.mainFrame = mainFrame;
		this.dialog = dialog;
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		boolean ret = true;
		
		try {
			// Initialisation des parseur et du matcheur.
			ParserCsvAnswer parserAwnser = new ParserCsvAnswer();
			ParserCsvUserList parserPerson = new ParserCsvUserList();
			BeanMatcher matcher;
			
			// Validation du modèle.
			this.model.checkModel();

			// Parsing du fichier campus et de la liste de personnes.
			parserAwnser.parseAnswer(this.campusFile);
			parserPerson.ParseUserList(this.personFile);

			// Initialisation du parseur.
			matcher = new BeanMatcher(parserPerson.getUserList(),
					parserAwnser.getCleanedData(),
					this.model.getSubjects(),
					this.model.getConstraint());

			// Mise à jour du modèle.
			this.model.setPersons(parserPerson.getUserList());

			// Matching. 
			matcher.match();

			// Résolution.
			this.solver.solve();

		} catch (FileException | ModelException  | SolverException | NoDefineSubjectException | NoUserFoundedException e) {
			displayErrorMessage(e.getMessage());
		}
		
		return ret;
	}
	
	@Override
	protected void done() {
		super.done();

		try {
			// Affichage du résultat.
			if(get()) {
				this.mainFrame.getResultPanel().setModel(this.model);
				this.mainFrame.showResultPanel();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		this.dialog.setVisible(false);
		this.dialog.dispose();
	}

	/**
	 * Affiche un popup d'erreur.
	 * @param message Le message d'erreur.
	 */
	private void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this.mainFrame, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
