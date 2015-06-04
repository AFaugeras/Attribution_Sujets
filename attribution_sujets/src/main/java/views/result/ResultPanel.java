package views.result;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import controllers.result.ResultConfigurationCtrl;

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
	private ResultConfigurationCtrl controller;
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


	public ResultPanel() {
		super();
	}
	
	public void setModel(Model model) {
		controller = new ResultConfigurationCtrl(model, this);
		this.initializeView();
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
	 * Cette méthode privée est appelée par le constructeur pour initialiser la
	 * vue.
	 */
	private void initializeView() {
		this.removeAll();
		this.setLayout(new GridBagLayout());

		JScrollPane jsp = new JScrollPane(getJpPeople(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jsp.setBorder(null);
		jsp.setPreferredSize(new Dimension(1000, 480));
		jsp.getVerticalScrollBar().setUnitIncrement(15);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(jsp, gbc);

		gbc.gridy = 1;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(getButtonsBar(), gbc);
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
	 * @return le bouton d'export CSV
	 */
	public JButton getJbExportCsv() {
		if (jbExportCsv == null) {
			jbExportCsv = new JButton("Export CSV", new ImageIcon(this
					.getClass().getClassLoader()
					.getResource("ihm/img/export_csv.png")));
			jbExportCsv.setActionCommand(JB_EXPORT_CSV_ACTION);
		}

		return jbExportCsv;
	}

	/**
	 * @return le bouton d'export PDF
	 */
	public JButton getJbExportPdf() {
		if (jbExportPdf == null) {
			jbExportPdf = new JButton("Export PDF", new ImageIcon(this
					.getClass().getClassLoader()
					.getResource("ihm/img/export_pdf.png")));
			jbExportPdf.setActionCommand(JB_EXPORT_PDF_ACTION);
		}

		return jbExportPdf;
	}

	/**
	 * @return retourne le tableau construit des résultats
	 */
	private JTable getJpPeople() {
		// if (jpPeople == null) {
		// this.jpPeople = new JPanel();
		this.jpPeople = this.controller.getTable();
		
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

	// TODO Development method to delete.
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		JFrame frameTest = new JFrame();
		frameTest.setLayout(new GridBagLayout());

		ArrayList<Subject> subjects = new ArrayList<Subject>();

		Subject subject1 = new Subject(1, "Stark", 0, 0,  0, 0);
		subjects.add(subject1);
		Subject subject2 = new Subject(2, "Lannister", 0, 0,  0, 0);
		subjects.add(subject2);
		Subject subject3 = new Subject(3, "Baratheon", 0, 0,  0, 0);
		subjects.add(subject3);
		Subject subject4 = new Subject(4, "Targaryen", 0, 0,  0, 0);
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

		ResultPanel tmp = new ResultPanel();
		tmp.setModel(model);
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
