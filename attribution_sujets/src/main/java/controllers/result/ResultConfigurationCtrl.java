package controllers.result;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;

import models.bean.Model;
import models.bean.Person;
import views.result.ResultCellRenderer;
import views.result.ResultPanel;
import views.result.ResultPdfGenerator;

import com.itextpdf.text.DocumentException;

import controllers.tableModel.ResultTable;
import controllers.tableModel.ResultTableHeader;

public class ResultConfigurationCtrl implements ActionListener {

	private Model model;
	private ResultPanel view;

	/**
	 * La liste des entrées du tableau
	 */
	private ResultTableHeader[] entete = {
			new ResultTableHeader(0, "Prénom", true),
			new ResultTableHeader(1, "Nom", true),
			new ResultTableHeader(2, "Identifiant Campus", true),
			new ResultTableHeader(3, "Identifiant sujet", true),
			new ResultTableHeader(4, "Sujet", true),
			new ResultTableHeader(5, "Encadrant", true),
			new ResultTableHeader(6, "Commentaires", false) };
	/**
	 * Les colonnes qui ne seront pas éditables
	 */
	private ArrayList<Integer> disabledCols = new ArrayList<Integer>();
	/**
	 * L'index de la colonne contenant les identifiants des sujets
	 */
	private Integer subjectIdColNumber = 3;
	/**
	 * L'index de la colonne contenant les libellés des sujets
	 */
	private Integer subjectLabelColNumber = 4;

	/**
	 * Le tableau des résultats
	 */
	private Object[][] donnees;

	public ResultConfigurationCtrl(Model model, ResultPanel view) {
		this.model = model;
		this.view = view;

		disabledCols.add(0);
		disabledCols.add(1);
		disabledCols.add(2);
		disabledCols.add(4);
	}

	public void initializeReactions() {
		view.getJbExportCsv().addActionListener(this);
		view.getJbExportPdf().addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (actionCommand.equals(ResultPanel.JB_EXPORT_CSV_ACTION)) {
			exportCSV();
		} else if (actionCommand.equals(ResultPanel.JB_EXPORT_PDF_ACTION)) {
			exportPDF();
		} else if (actionCommand.equals(ResultPanel.JB_BACK_ACTION)) {
			//
		}
	}

	/**
	 * Exporte le tableau de résultats en fichier CSV
	 * 
	 * @throws IOException
	 */
	private void exportCSV() {
		try {
			JFileChooser c = new JFileChooser() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void approveSelection() {
					File f = getSelectedFile();
					if (!f.getName().endsWith(".csv"))
						f = new File(f.getAbsolutePath() + ".csv");
					if (f.exists() && getDialogType() == SAVE_DIALOG) {
						int result = JOptionPane.showConfirmDialog(this,
								"Ce fichier existe déjà, le remplacer ?",
								"Fichier existant",
								JOptionPane.YES_NO_CANCEL_OPTION);
						switch (result) {
						case JOptionPane.YES_OPTION:
							super.approveSelection();
							return;
						case JOptionPane.NO_OPTION:
							return;
						case JOptionPane.CLOSED_OPTION:
							return;
						case JOptionPane.CANCEL_OPTION:
							cancelSelection();
							return;
						}
					}
					super.approveSelection();
				}
			};
			// c.setCurrentDirectory(new File(Model.getFileChoserPath()));
			c.setFileFilter(new FileFilter() {

				@Override
				public String getDescription() {
					return ".csv";
				}

				@Override
				public boolean accept(File f) {
					String[] extensions = { "csv" };
					if (f.isDirectory()) {
						return true;
					} else {
						String path = f.getAbsolutePath().toLowerCase();
						for (int i = 0, n = extensions.length; i < n; i++) {
							String extension = extensions[i];
							if ((path.endsWith(extension) && (path.charAt(path
									.length() - extension.length() - 1)) == '.')) {
								return true;
							}
						}
					}
					return false;
				}
			});

			c.setDialogTitle("Export CSV");

			int rVal = c.showSaveDialog(this.view);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				// Model.setFileChoserPath(c.getSelectedFile().getParent());
				String filename = c.getSelectedFile().getAbsolutePath();

				if (!filename.endsWith(".csv"))
					filename += ".csv";

				System.out.println("FILENAME CHOSEN : " + filename);

				FileWriter fileWriter = new FileWriter(filename);

				String line = "";

				ArrayList<Integer> selectedCols = new ArrayList<Integer>();
				for (ResultTableHeader head : entete) {
					if (head.isSelected()) {
						selectedCols.add(head.getPosition());
						line += head.getLabel() + ";";
					}
				}
				line += '\n';
				fileWriter.append(line);

				for (Object[] objects : donnees) {
					line = "";
					for (Integer pos : selectedCols) {
						line += (objects[pos] == null ? "" : objects[pos]) + ";";
					}
					line += '\n';
					fileWriter.append(line);
				}

				fileWriter.close();
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				System.out.println("CANCEL CHOSING FILENAME FOR CSV EXPORT");
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Exporte les résultats dans un fichier PDF
	 * 
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	private void exportPDF() {
		try {
			ResultPdfGenerator generator = new ResultPdfGenerator(this.entete,
					this.donnees);

			JFileChooser c = new JFileChooser() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void approveSelection() {
					File f = getSelectedFile();
					if (!f.getName().endsWith(".pdf"))
						f = new File(f.getAbsolutePath() + ".pdf");
					if (f.exists() && getDialogType() == SAVE_DIALOG) {
						int result = JOptionPane.showConfirmDialog(this,
								"Ce fichier existe déjà, le remplacer ?",
								"File existant",
								JOptionPane.YES_NO_CANCEL_OPTION);
						switch (result) {
						case JOptionPane.YES_OPTION:
							super.approveSelection();
							return;
						case JOptionPane.NO_OPTION:
							return;
						case JOptionPane.CLOSED_OPTION:
							return;
						case JOptionPane.CANCEL_OPTION:
							cancelSelection();
							return;
						}
					}
					super.approveSelection();
				}
			};
			// c.setCurrentDirectory(new File(Model.getFileChoserPath()));
			c.setFileFilter(new FileFilter() {

				@Override
				public String getDescription() {
					return ".pdf";
				}

				@Override
				public boolean accept(File f) {
					String[] extensions = { "pdf" };
					if (f.isDirectory()) {
						return true;
					} else {
						String path = f.getAbsolutePath().toLowerCase();
						for (int i = 0, n = extensions.length; i < n; i++) {
							String extension = extensions[i];
							if ((path.endsWith(extension) && (path.charAt(path
									.length() - extension.length() - 1)) == '.')) {
								return true;
							}
						}
					}
					return false;
				}
			});

			c.setDialogTitle("Export PDF");

			int rVal = c.showSaveDialog(this.view);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				// Model.setFileChoserPath(c.getSelectedFile().getParent());
				String filename = c.getSelectedFile().getAbsolutePath();

				if (!filename.endsWith(".pdf"))
					filename += ".pdf";

				System.out.println("FILENAME CHOSEN : " + filename);

				generator.buildPDF(filename);
			}
			if (rVal == JFileChooser.CANCEL_OPTION) {
				System.out.println("CANCEL CHOSING FILENAME FOR PDF EXPORT");
			}
		} catch (FileNotFoundException | DocumentException e1) {
			e1.printStackTrace();
		}
	}

	public Model getModel() {
		return this.model;
	}

	public ResultTable getTable() {
		List<Person> people = this.model.getPersons();

		donnees = new Object[people.size()][];

		for (int i = 0; i < people.size(); i++) {
			Person someone = people.get(i);

			String subjectId = "-1";
			String subjectLabel = "N/A";
			if (someone.getAssigned() != null) {
				subjectId = String.valueOf(someone.getAssigned().getId());
				subjectLabel = someone.getAssigned().getLabel();
			}

			String[] data = { someone.getFirstName(), someone.getFamilyName(),
					someone.getIDcampus(), subjectId, subjectLabel, "",
					someone.getComment() };
			donnees[i] = data;
		}

		ResultTable tableau = new ResultTable(donnees, entete, disabledCols,
				subjectIdColNumber, subjectLabelColNumber, model.getSubjects());

		tableau.setDefaultRenderer(Object.class, new ResultCellRenderer(
				disabledCols));

		tableau.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

		tableau.getTableHeader().setReorderingAllowed(false);
		tableau.setPreferredScrollableViewportSize(tableau.getPreferredSize());
		tableau.setFillsViewportHeight(true);
		// resizeColumnWidth(tableau);
		return tableau;
	}
}
