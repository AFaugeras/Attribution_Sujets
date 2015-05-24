package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import views.constraints.GeneralConstraintsPanel;
import views.subjects.SubjectsConfigurationPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_NAME = "Attribution des sujets";
	public static final String JB_NEXT_ACTION = "NEXT";
	private static final Dimension PREFERED_SIZE = new Dimension(1200, 650);

	private JMenuItem jmiImport;
	private JMenuItem jmiLoad;
	private JMenuItem jmiSave;
	private JMenuItem jmiExport;

	private SubjectsConfigurationPanel subjectsPanel;
	private GeneralConstraintsPanel constraintsPanel;

	private JButton jbNext;

	public MainFrame() {
		super(APPLICATION_NAME);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.initializeView();
		this.setResizable(false);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public JMenuItem getJmiImport() {
		if (this.jmiImport == null) {
			this.jmiImport = new JMenuItem("Importer");
		}

		return this.jmiImport;
	}

	public JMenuItem getJmiLoad() {
		if (this.jmiLoad == null) {
			this.jmiLoad = new JMenuItem("Charger");
		}

		return this.jmiLoad;
	}

	public JMenuItem getJmiSave() {
		if (this.jmiSave == null) {
			this.jmiSave = new JMenuItem("Sauvegarder");
		}

		return this.jmiSave;
	}

	public JMenuItem getJmiExport() {
		if (this.jmiExport == null) {
			this.jmiExport = new JMenuItem("Exporter en CVS");
		}

		return this.jmiExport;
	}

	public SubjectsConfigurationPanel getSubjectsPanel() {
		if (this.subjectsPanel == null) {
			this.subjectsPanel = new SubjectsConfigurationPanel();
		}

		return this.subjectsPanel;
	}

	public GeneralConstraintsPanel getConstraintsPanel() {
		if (this.constraintsPanel == null) {
			this.constraintsPanel = new GeneralConstraintsPanel();
		}

		return this.constraintsPanel;
	}

	/**
	 * Accesseur de l'attribut jbNext.
	 *
	 * @return Le JButton jbNext.
	 */
	public JButton getJbNext() {
		if (jbNext == null) {
			jbNext = new JButton("Suivant", new ImageIcon(this.getClass()
					.getClassLoader().getResource("ihm/img/next.png")));
			jbNext.setActionCommand(JB_NEXT_ACTION);
		}

		return jbNext;
	}

	@Override
	public Dimension getPreferredSize() {
		return PREFERED_SIZE;
	}

	private void initializeView() {
		this.setJMenuBar(getMenus());

		JPanel container = new JPanel(new GridBagLayout());
		container.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		container.add(getSubjectsPanel(), gbc);

		// gbc.gridx = 1;
		// gbc.weightx = 0;
		// container.add(new JSeparator(JSeparator.VERTICAL), gbc);

		gbc.gridx = 1;
		gbc.weightx = 1;
		container.add(getConstraintsPanel(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weighty = 0;
		gbc.gridwidth = 2;
		gbc.insets.top = 9;
		container.add(new JSeparator(), gbc);
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		container.add(getJbNext(), gbc);

		this.getContentPane().add(container);
	}

	private JMenuBar getMenus() {
		JMenuBar menuBar = new JMenuBar();

		JMenu menu = new JMenu("Fichier");
		menu.add(getJmiImport());
		menu.add(getJmiLoad());
		menu.add(getJmiSave());
		menu.add(getJmiExport());
		menuBar.add(menu);

		menu = new JMenu("Paramètres");
		menuBar.add(menu);

		return menuBar;
	}
}