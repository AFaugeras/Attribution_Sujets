package controllers;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import models.bean.Model;
import models.exception.ModelException;
import models.parser.BeanMatcher;
import models.parser.answer.ParserCsvAnswer;
import models.parser.user.ParserCsvUserList;
import models.solver.Solver;
import models.solver.SolverException;
import views.MainFrame;


public class SolvingWorker extends SwingWorker<Boolean, Void> {

	private Solver solver;
	private Model model;
	private File campusFile;
	private File personFile;
	private MainFrame mainFrame;
	private JDialog dialog;
	
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
			ParserCsvAnswer parserAwnser = new ParserCsvAnswer();
			ParserCsvUserList parserPerson = new ParserCsvUserList();
			BeanMatcher matcher;

			parserAwnser.parseAnswer(this.campusFile);
			parserPerson.ParseUserList(this.personFile);

			matcher = new BeanMatcher(parserPerson.getUserList(),
					parserAwnser.getCleanedData(),
					this.model.getSubjects(),
					this.model.getConstraint());

			this.model.setPersons(parserPerson.getUserList());

			matcher.match();

			this.solver.solve("input.txt",	"output.txt", this.model);

		} catch (ModelException  | SolverException e) {
			displayErrorMessage(e.getMessage());
			e.printStackTrace();
		}
		
		return ret;
	}
	
	@Override
	protected void done() {
		super.done();

		try {
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

	private void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(this.mainFrame, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
}
