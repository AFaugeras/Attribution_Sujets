package controllers;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import models.bean.Model;
import models.exception.NoDefineSubjectException;
import models.exception.NoUserFoundedException;
import models.exception.fileformatexception.FileFormatException;
import models.parser.BeanMatcher;
import models.parser.answer.ParserCsvAnswer;
import models.parser.user.ParserCsvUserList;
import models.solver.Solver;
import models.solver.reader.NotFoundSolutionException;
import models.solver.reader.ReaderException;
import models.solver.writer.WriterException;
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

		} catch (IOException e) {
			displayErrorMessage("Erreur à la lecture du fichier.");
			ret = false;
		} catch (FileFormatException e) {
			displayErrorMessage("Erreur CSV :\n" + e.getMessage());
			ret = false;
		} catch (NoDefineSubjectException e) {
			displayErrorMessage("Un ou plusieurs sujets ne sont pas définis :\n" + e.getMessage());
			ret = false;
		} catch (NoUserFoundedException e) {
			displayErrorMessage(/* "L'utilisateur " + */e.getMessage()/* + " n'est pas présent dans le fichier d'élèves."*/);
			ret = false;
		} catch (WriterException e) {
			displayErrorMessage("Erreur lors de la génération du fichier d'entrée.");
			ret = false;
		} catch (ReaderException e) {
			displayErrorMessage("Erreur lors de la lecture du fichier solution.");
			ret = false;
		} catch (NotFoundSolutionException e) {
			displayErrorMessage("Aucune solution trouvée.");
			ret = false;
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
