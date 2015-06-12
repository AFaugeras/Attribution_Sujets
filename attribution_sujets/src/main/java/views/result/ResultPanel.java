package views.result;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import models.bean.Constraints;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import controllers.result.ResultConfigurationCtrl;
import controllers.result.ResultStatPanelCtrl;
import controllers.result.ResultSubjectPanelCtrl;
import controllers.tablemodel.ResultTable;

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
	private ResultTable jpPeople;
	
	private JPanel jpResultTable;
	private JPanel jpSubjects;
	private JPanel jpStats;
	
	private JTabbedPane jtContent;

	public static final String JB_EXPORT_CSV_ACTION = "EXPORT CSV";
	public static final String JB_EXPORT_PDF_ACTION = "EXPORT PDF";
	public static final String JB_BACK_ACTION = "BACK";

	private JButton jbExportCsv;
	private JButton jbExportPdf;
	private JButton jbBack;

	private ResultSubjectPanelCtrl resultSubjectPanelCtrl;
	private ResultStatPanelCtrl resultStatPanelCtrl;

	public ResultPanel() {
		super();
		this.resultSubjectPanelCtrl = new ResultSubjectPanelCtrl();
		this.resultStatPanelCtrl = new ResultStatPanelCtrl();
	}
	
	public void setModel(Model model) {
		controller = new ResultConfigurationCtrl(model, this);
		this.resultSubjectPanelCtrl = new ResultSubjectPanelCtrl();
		this.resultStatPanelCtrl = new ResultStatPanelCtrl();
		resultSubjectPanelCtrl.setModel(model);
		resultStatPanelCtrl.setModel(model);
		this.jbExportCsv = null;
		this.jbExportPdf = null;
		this.initializeView();
		this.controller.initializeReactions();
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

		this.jtContent = new JTabbedPane();
		
		this.jpResultTable = new JPanel(new GridBagLayout());
		getJpPeople().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
		this.jpResultTable.add(jsp, gbc);

		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		gbc2.weightx = 1;
		gbc2.weighty = 0;
		gbc2.fill = GridBagConstraints.NONE;
		this.jpResultTable.add(getButtonsBar(), gbc2);
		
		this.jtContent.addTab("Tableau de résultat", new ImageIcon(this.getClass()
				.getClassLoader().getResource("ihm/img/list.png")), this.jpResultTable);
		
		this.jpSubjects = new JPanel(new GridBagLayout());
		this.jpSubjects.add(new JScrollPane(resultSubjectPanelCtrl.getJpSubjects()), gbc);
		this.jtContent.addTab("Détail des sujets", new ImageIcon(this.getClass()
				.getClassLoader().getResource("ihm/img/table.png")), this.jpSubjects);
		
		this.jpStats = new JPanel(new GridBagLayout());
		this.jpStats.add(new JScrollPane(resultStatPanelCtrl.getJpStats()), gbc);
		this.jtContent.addTab("Statistiques", new ImageIcon(this.getClass()
				.getClassLoader().getResource("ihm/img/stats.png")), this.jpStats);
		
		this.add(jtContent, gbc);
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
			jbExportCsv = new JButton("Exporter la sélection en CSV", new ImageIcon(this
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
			jbExportPdf = new JButton("Export la sélection en PDF", new ImageIcon(this
					.getClass().getClassLoader()
					.getResource("ihm/img/export_pdf.png")));
			jbExportPdf.setActionCommand(JB_EXPORT_PDF_ACTION);
		}

		return jbExportPdf;
	}

	/**
	 * @return retourne le tableau construit des résultats
	 */
	private ResultTable getJpPeople() {
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

		Subject subject1 = new Subject(1, "Stark", 0, 10, 0, 2);
		subjects.add(subject1);
		Subject subject2 = new Subject(2, "Lannister", 0, 10, 0, 1);
		subjects.add(subject2);
		Subject subject3 = new Subject(3, "Baratheon", 0, 10, 0, 2);
		subjects.add(subject3);
		Subject subject4 = new Subject(4, "Targaryen", 0, 10, 0, 1);
		subjects.add(subject4);

		ArrayList<Person> people = new ArrayList<Person>();

		for (int i = 0; i < 40; i++) {
			int random = (int) (Math.random() * 4);
			Person someone = new Person();
			someone.setFirstName("Hodor");
			someone.setFamilyName(String.valueOf(i));
			someone.setIDcampus(i + "hodor" + 15);
			someone.setAssigned(subjects.get(random));
			ArrayList<Subject> choices = new ArrayList<Subject>();
			choices.add(subject1);
			choices.add(subject2);
			choices.add(subject4);
			someone.setChoices(choices);
			
			people.add(someone);
		}

		people.get(0).setComment("u vhbfe ivlbfaei bfeiuag beidvb dckjv c hvfeib vidpbe vibdeiv bfidl bfda vpibcdklh zbvclkd bvjkcbdv kdfzb viefda bchkvv falykbvkdbchjvc b vhbeai kvbdcalkv bc dqj bqc hvlaf bqvhbdqvl hqkcb dvhjqd bvldqb");
		
		Model model = new Model(null, people, subjects);
		
		Constraints constraint = new Constraints(3, 0, 0, 0);
		
		model.setConstraint(constraint);

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

	}
}
