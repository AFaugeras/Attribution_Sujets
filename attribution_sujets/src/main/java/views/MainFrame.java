package views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileFilter;

import views.constraints.BoundsConstraintsPanel;
import views.constraints.CampusConstraintsPanel;
import views.dataselection.DataSelectionPanel;
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
	private BoundsConstraintsPanel boundConstraintsPanel;
	private CampusConstraintsPanel campusConstraintsPanel;
	private DataSelectionPanel dataSelectionPanel;

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
		if (jmiImport == null) {
			jmiImport = new JMenu("Importer");
			JMenuItem fromSolverChocco = new JMenuItem(
					"à partir du résultat Chocco");
			JMenuItem fromSolverGLPK = new JMenuItem(
					"à partir du résultat GLPK");
			JMenuItem fromTreatment = new JMenuItem(
					"à partir du résultat de notre traitement");

			jmiImport.add(getFromCSV());
			jmiImport.add(fromSolverChocco);
			jmiImport.add(fromSolverGLPK);
			jmiImport.add(fromTreatment);
		}

		return jmiImport;
	}

	public JMenuItem getFromCSV() {
		JMenuItem fromCSV = new JMenuItem("à partir d'un CSV");

		fromCSV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();

				fc.setDialogTitle("Importer à partir d'un CSV");
				fc.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {
						return ".xls, .csv";
					}

					@Override
					public boolean accept(File f) {
						String[] extensions = { "csv", "xls" };
						if (f.isDirectory()) {
							return true;
						} else {
							String path = f.getAbsolutePath().toLowerCase();
							for (int i = 0, n = extensions.length; i < n; i++) {
								String extension = extensions[i];
								if ((path.endsWith(extension) && (path
										.charAt(path.length()
												- extension.length() - 1)) == '.')) {
									return true;
								}
							}
						}
						return false;
					}
				});

				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.CANCEL_OPTION) {
					System.out.println("Choix du fichier csv annulé");
				}

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("Choix du fichier csv validé");
					System.out.println(fc.getSelectedFile().getAbsolutePath());
				}

				if (returnVal == JFileChooser.ERROR) {
					System.out.println("Choix du fichier csv interrompu");
				}
			}
		});

		return fromCSV;
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

	public BoundsConstraintsPanel getBoundConstraintsPanel() {
		if (this.boundConstraintsPanel == null) {
			this.boundConstraintsPanel = new BoundsConstraintsPanel();
		}

		return this.boundConstraintsPanel;
	}

	public CampusConstraintsPanel getCampusConstraintsPanel() {
		if (this.campusConstraintsPanel == null) {
			this.campusConstraintsPanel = new CampusConstraintsPanel();
		}

		return this.campusConstraintsPanel;
	}

	public DataSelectionPanel getDataSelectionPanel() {
		if (this.dataSelectionPanel == null) {
			this.dataSelectionPanel = new DataSelectionPanel();
		}

		return this.dataSelectionPanel;
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
		// this.setJMenuBar(getMenus());

		JPanel container = new JPanel(new GridBagLayout());
		container.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = 4;
		container.add(getSubjectsPanel(), gbc);

		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		container.add(getBoundConstraintsPanel(), gbc);

		gbc.gridy = 1;
		container.add(getCampusConstraintsPanel(), gbc);

		gbc.gridy = 2;
		container.add(getDataSelectionPanel(), gbc);

		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		container.add(new JPanel(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weighty = 0;
		gbc.gridwidth = 2;
		gbc.insets.top = 9;
		container.add(new JSeparator(), gbc);

		gbc.gridy = 5;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		container.add(getJbNext(), gbc);

		this.getContentPane().add(container);
	}

	// private JMenuBar getMenus() {
	// JMenuBar menuBar = new JMenuBar();
	//
	// JMenu menu = new JMenu("Fichier");
	// menu.add(getJmiImport());
	// menu.add(getJmiLoad());
	// menu.add(getJmiSave());
	// menu.add(getJmiExport());
	// menuBar.add(menu);
	//
	// menu = new JMenu("Paramètres");
	// menuBar.add(menu);
	//
	// return menuBar;
	// }
}