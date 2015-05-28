package views.result;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;

import com.itextpdf.text.DocumentException;

/**
 * 
 *         Classe prennant en charge l'affchage des résultats des solveurs
 *
 */
public class ResultPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Notre modèle de données
	 */
	private Model model;
	/**
	 * La Jtable contenant l'affchage des résultats
	 */
	private JTable jpPeople;

	public static final String JB_EXPORT_CSV_ACTION = "EXPORT CSV";
	public static final String JB_EXPORT_PDF_ACTION = "EXPORT PDF";
	public static final String JB_BACK_ACTION = "BACK";

	private JButton jbExportCsv;
	private JButton jbExportPdf;
	private JButton jbBack;

	/**
	 * La liste des entrées du tableau
	 */
	private String[] entete = { "Prénom", "Nom", "Identifiant Campus",
			"Identifiant sujet", "Sujet", "Commentaires" };
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

	/**
	 * @param model
	 *            notre modèle de données contenant le résultat du solveur
	 */
	public ResultPanel(Model model) {
		setModel(model);
	}

	public void setModel(Model model) {
		this.model = model;
		if (model != null) {
			disabledCols.add(0);
			disabledCols.add(1);
			disabledCols.add(2);
			disabledCols.add(4);
			initializeView();
		}
	}

	public JButton getJbBack() {
		if (jbBack == null) {
			jbBack = new JButton("Retour", new ImageIcon(this.getClass()
					.getClassLoader().getResource("ihm/img/back.png")));
			jbBack.setActionCommand(JB_BACK_ACTION);
		}

		return jbBack;
	}

	/**
	 * Cette méthode privée est appellée par le constructeur pour initialiser la
	 * vue.
	 */
	private void initializeView() {
		this.removeAll();
		this.setLayout(new BorderLayout());

		JScrollPane jsp = new JScrollPane(getJpPeople(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBorder(null);
		jsp.setPreferredSize(new Dimension(1000, 480));

		jsp.getVerticalScrollBar().setUnitIncrement(15);
		this.add(jsp, BorderLayout.CENTER);

		this.add(getButtonsBar(), BorderLayout.SOUTH);
	}

	/**
	 * @return Le panel contenant les boutons d'export du tableau
	 */
	private JPanel getButtonsBar() {
		JPanel ret = new JPanel();
		ret.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		ret.add(getJbExportCsv());
		ret.add(getJbExportPdf());
		ret.add(getJbBack());

		return ret;
	}

	/**
	 * @return le bouton d'export PDF
	 */
	private JButton getJbExportPdf() {
		if (jbExportCsv == null) {
			jbExportCsv = new JButton("Export PDF", new ImageIcon(this
					.getClass().getClassLoader()
					.getResource("ihm/img/export_pdf.png")));
			jbExportCsv.setActionCommand(JB_EXPORT_CSV_ACTION);
			jbExportCsv.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						exportPDF();
					} catch (FileNotFoundException | DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

		return jbExportCsv;
	}

	/**
	 * @return le bouton d'export CSV
	 */
	private JButton getJbExportCsv() {
		if (jbExportPdf == null) {
			jbExportPdf = new JButton("Export CSV", new ImageIcon(this
					.getClass().getClassLoader()
					.getResource("ihm/img/export_csv.png")));
			jbExportPdf.setActionCommand(JB_EXPORT_PDF_ACTION);
			jbExportPdf.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						exportCSV();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

		return jbExportPdf;
	}

	/**
	 * @return retourne le tableau construit des résultats
	 */
	private JTable getJpPeople() {
		// if (jpPeople == null) {
		// this.jpPeople = new JPanel();

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
					someone.getIDcampus(), subjectId, subjectLabel,
					someone.getComment() };
			donnees[i] = data;
		}

		JTable tableau = new JTable(donnees, entete) {
			/**
				 * 
				 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				if (disabledCols.contains(col)) {
					return false;
				}
				return true;
			}

			// Override pour permettre le contrôle de la saisie
			// Ainsi que l'actualisation dynamique du libellé des sujets
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
				if (subjectIdColNumber.equals(columnIndex)) {
					try {
						if (Integer.valueOf(aValue.toString()) <= model
								.getSubjects().size()
								&& Integer.valueOf(aValue.toString()) > 0) {
							setValueAt(
									model.getSubjects()
											.get(Integer.valueOf(aValue
													.toString()) - 1)
											.getLabel(), rowIndex,
									subjectLabelColNumber);
							super.setValueAt(aValue, rowIndex, columnIndex);
						}
					} catch (NumberFormatException e) {

					}
				} else {
					super.setValueAt(aValue, rowIndex, columnIndex);
				}
				resizeColumnWidth(this);
			}
			@Override
			public Component prepareEditor(TableCellEditor editor, int row, int column) {
			    Component c = super.prepareEditor(editor, row, column);
			    if (c instanceof JTextComponent) {
			        ((JTextComponent) c).selectAll();
			    } 
			    return c;
			}

		};
		tableau.setDefaultRenderer(Object.class, new ResultCellRenderer(disabledCols));

		tableau.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		tableau.getTableHeader().setReorderingAllowed(false);
		tableau.setPreferredScrollableViewportSize(tableau.getPreferredSize());
		tableau.setFillsViewportHeight(true);
		resizeColumnWidth(tableau);
		this.jpPeople = tableau;
		// }

		return jpPeople;
	}

	/**
	 * Méthode permettant de redimensionner les colonnes d'une JTable en
	 * fonction de la taille du contenu des colonnes
	 * 
	 * @param table
	 *            la table sur laquelle on veut appliquer un redimensionnement
	 *            des colonnes
	 */
	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 50; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width + 5, width);
			}
			
			width = width < 166 ? 166 : width;
			
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	/**
	 * Exporte le tableau de résultats en fichier CSV
	 * 
	 * @throws IOException
	 */
	public void exportCSV() throws IOException {
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
		c.setCurrentDirectory(new File(Model.getFileChoserPath()));
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

		int rVal = c.showSaveDialog(this.jpPeople);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			Model.setFileChoserPath(c.getSelectedFile().getParent());
			String filename = c.getSelectedFile().getAbsolutePath();

			if (!filename.endsWith(".csv"))
				filename += ".csv";

			System.out.println("FILENAME CHOSEN : " + filename);

			FileWriter fileWriter = new FileWriter(filename);

			String line = "";

			for (Object object : entete) {
				line += object + ";";
			}
			line += '\n';
			fileWriter.append(line);

			for (Object[] objects : donnees) {
				line = "";
				for (Object object : objects) {
					line += (object == null ? "" : object) + ";";
				}
				line += '\n';
				fileWriter.append(line);
			}

			fileWriter.close();
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("CANCEL CHOSING FILENAME FOR CSV EXPORT");
		}
	}

	/**
	 * Exporte les résultats dans un fichier PDF
	 * 
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public void exportPDF() throws FileNotFoundException, DocumentException {
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
							"File existant", JOptionPane.YES_NO_CANCEL_OPTION);
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
		c.setCurrentDirectory(new File(Model.getFileChoserPath()));
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

		int rVal = c.showSaveDialog(this.jpPeople);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			Model.setFileChoserPath(c.getSelectedFile().getParent());
			String filename = c.getSelectedFile().getAbsolutePath();

			if (!filename.endsWith(".pdf"))
				filename += ".pdf";

			System.out.println("FILENAME CHOSEN : " + filename);

			generator.buildPDF(filename);
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("CANCEL CHOSING FILENAME FOR PDF EXPORT");
		}
	}

	// TODO Development method to delete.
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		JFrame frameTest = new JFrame();
		frameTest.setLayout(new GridBagLayout());

		ArrayList<Subject> subjects = new ArrayList<Subject>();

		Subject subject1 = new Subject(1, "Stark", 0, 0, 0, 0, 0);
		subjects.add(subject1);
		Subject subject2 = new Subject(2, "Lannister", 0, 0, 0, 0, 0);
		subjects.add(subject2);
		Subject subject3 = new Subject(3, "Baratheon", 0, 0, 0, 0, 0);
		subjects.add(subject3);
		Subject subject4 = new Subject(4, "Targaryen", 0, 0, 0, 0, 0);
		subjects.add(subject4);

		ArrayList<Person> people = new ArrayList<Person>();

		for (int i = 0; i < 40; i++) {
			int random = (int) (Math.random() * 4);
			Person someone = new Person();
			someone.setFirstName("Hodor");
			someone.setFamilyName(String.valueOf(i));
			someone.setIDcampus(i + "hodor" + 15);
			someone.setAssigned(subjects.get(random));
			people.add(someone);
		}

		Model model = new Model(null, people, subjects);

		ResultPanel tmp = new ResultPanel(model);
		JPanel aux = new JPanel(new GridBagLayout());
		aux.add(tmp);
		frameTest.add(aux, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		frameTest.setPreferredSize(new Dimension(1200, 600));
		frameTest.pack();
		frameTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTest.setLocationRelativeTo(null);
		frameTest.setVisible(true);

		System.out.println(tmp.getSize());

	}
}