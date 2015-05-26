package views;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import views.configuration.ConfigurationPanel;
import views.result.ResultPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_NAME = "Attribution des sujets";
	private static final String CONFIGURATION_PANEL = "CONFIGURATION_PANEL";
	private static final String RESULT_PANEL = "RESULT_PANEL";

	private static final Dimension PREFERED_SIZE = new Dimension(1200, 650);

	private JPanel cardPanel;
	private ConfigurationPanel configurationPanel;
	private ResultPanel resultPanel;

	public MainFrame() {
		super(APPLICATION_NAME);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.initializeView();
		// this.setResizable(false);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public ConfigurationPanel getConfigurationPanel() {
		if (this.configurationPanel == null) {
			this.configurationPanel = new ConfigurationPanel();
		}

		return this.configurationPanel;
	}

	public ResultPanel getPanelResult() {
		if (this.resultPanel == null) {
			this.resultPanel = new ResultPanel(null);
		}

		return this.resultPanel;
	}

	public void switchCard() {
		((CardLayout) this.cardPanel.getLayout()).next(this.cardPanel);
	}

	@Override
	public Dimension getPreferredSize() {
		return PREFERED_SIZE;
	}

	private void initializeView() {
		this.cardPanel = new JPanel(new CardLayout());

		this.cardPanel.add(this.getConfigurationPanel(), CONFIGURATION_PANEL);
		this.cardPanel.add(this.getPanelResult(), RESULT_PANEL);

		this.getContentPane().add(cardPanel);
	}

	// public static final String JB_NEXT_ACTION = "NEXT";

	// private JMenuItem jmiImport;
	// private JMenuItem jmiLoad;
	// private JMenuItem jmiSave;
	// private JMenuItem jmiExport;

	// public JMenuItem getFromCSV() {
	// JMenuItem fromCSV = new JMenuItem("à partir d'un CSV");
	//
	// fromCSV.addActionListener(new ActionListener() {
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// final JFileChooser fc = new JFileChooser();
	//
	// fc.setDialogTitle("Importer à partir d'un CSV");
	// fc.setFileFilter(new FileFilter() {
	//
	// @Override
	// public String getDescription() {
	// return ".xls, .csv";
	// }
	//
	// @Override
	// public boolean accept(File f) {
	// String[] extensions = { "csv", "xls" };
	// if (f.isDirectory()) {
	// return true;
	// } else {
	// String path = f.getAbsolutePath().toLowerCase();
	// for (int i = 0, n = extensions.length; i < n; i++) {
	// String extension = extensions[i];
	// if ((path.endsWith(extension) && (path
	// .charAt(path.length()
	// - extension.length() - 1)) == '.')) {
	// return true;
	// }
	// }
	// }
	// return false;
	// }
	// });
	//
	// int returnVal = fc.showOpenDialog(null);
	//
	// if (returnVal == JFileChooser.CANCEL_OPTION) {
	// System.out.println("Choix du fichier csv annulé");
	// }
	//
	// if (returnVal == JFileChooser.APPROVE_OPTION) {
	// System.out.println("Choix du fichier csv validé");
	// System.out.println(fc.getSelectedFile().getAbsolutePath());
	// }
	//
	// if (returnVal == JFileChooser.ERROR) {
	// System.out.println("Choix du fichier csv interrompu");
	// }
	// }
	// });
	//
	// return fromCSV;
	// }

	// public JMenuItem getJmiImport() {
	// if (jmiImport == null) {
	// jmiImport = new JMenu("Importer");
	// JMenuItem fromSolverChocco = new JMenuItem(
	// "à partir du résultat Chocco");
	// JMenuItem fromSolverGLPK = new JMenuItem(
	// "à partir du résultat GLPK");
	// JMenuItem fromTreatment = new JMenuItem(
	// "à partir du résultat de notre traitement");
	//
	// jmiImport.add(getFromCSV());
	// jmiImport.add(fromSolverChocco);
	// jmiImport.add(fromSolverGLPK);
	// jmiImport.add(fromTreatment);
	// }
	//
	// return jmiImport;
	// }

	// public JMenuItem getJmiLoad() {
	// if (this.jmiLoad == null) {
	// this.jmiLoad = new JMenuItem("Charger");
	// }
	//
	// return this.jmiLoad;
	// }

	// public JMenuItem getJmiSave() {
	// if (this.jmiSave == null) {
	// this.jmiSave = new JMenuItem("Sauvegarder");
	// }
	//
	// return this.jmiSave;
	// }

	// public JMenuItem getJmiExport() {
	// if (this.jmiExport == null) {
	// this.jmiExport = new JMenuItem("Exporter en CVS");
	// }
	//
	// return this.jmiExport;
	// }
}