package views.configuration.dataselection;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel de sélection des fichier campus et de la liste de personnes.
 */
public class DataSelectionPanel extends JPanel {

	// Constantes :
	public static final String JB_CAMPUS_SELECTION = "CAMPUS_SELECTION";
	public static final String JB_PERSONS_SELECTION = "PERSON_SELECTION";
	
	private static final String LABEL_TITLED_BORDER = "Données";
	private static final String CAMPUS_LABEL = "Fichier campus :";
	private static final String JB_CAMPUS_ICON_PATH = "ihm/img/import_mini.png";
	private static final String PERSONS_LABEL = "Personnes :";
	private static final String JB_PERSONS_ICON_PATH = "ihm/img/import_mini.png";

	private static final long serialVersionUID = 1L;

	/**
	 * Champ de texte non éditable où est affiché le fichier campus sélectionné.
	 */
	private JTextField jtfCampusFile;
	
	/**
	 * Champ de texte non éditable où est affiché le fichier de personnes sélectionné.
	 */
	private JTextField jtfPersonsFile;

	/**
	 * Bouton qui ouvre l'explorateur de fichier pour sélectionner le fichier campus.
	 */
	private JButton jbCampusFile;
	
	/**
	 * Bouton qui ouvre l'explorateur de fichier pour sélectionner le fichier de personnes. 
	 */
	private JButton jbPersonsFile;

	/**
	 * Constructeur.
	 */
	public DataSelectionPanel() {
		super();

		initializeView();
	}

	/**
	 * Accesseur de l'attribut jtfCampusFile.
	 * 
	 * @return Le JTextField jtfCampusFile.
	 */
	public JTextField getJtfCampusFile() {
		if (this.jtfCampusFile == null) {
			this.jtfCampusFile = new JTextField();
			this.jtfCampusFile.setEditable(false);
			this.jtfCampusFile.setBackground(Color.WHITE);
		}

		return this.jtfCampusFile;
	}

	/**
	 * Accesseur de l'attribut jtfPersonsFile.
	 * 
	 * @return Le JTextField jtfPersonsFile.
	 */
	public JTextField getJtfPersonsFile() {
		if (this.jtfPersonsFile == null) {
			this.jtfPersonsFile = new JTextField();
			this.jtfPersonsFile.setEditable(false);
			this.jtfPersonsFile.setBackground(Color.WHITE);
		}

		return this.jtfPersonsFile;
	}

	/**
	 * Accesseur de l'attribut jbCampusFile.
	 * 
	 * @return Le bouton jbCampusFile.
	 */
	public JButton getJbCampusFile() {
		if (this.jbCampusFile == null) {
			this.jbCampusFile = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource(JB_CAMPUS_ICON_PATH)));
			this.jbCampusFile.setActionCommand(JB_CAMPUS_SELECTION);
		}

		return this.jbCampusFile;
	}

	/**
	 * Accesseur de l'attribut jbPersonsFile.
	 * 
	 * @return Le bouton jbPersonsFile.
	 */
	public JButton getJbPersonsFile() {
		if (this.jbPersonsFile == null) {
			this.jbPersonsFile = new JButton(new ImageIcon(this.getClass().getClassLoader().getResource(JB_PERSONS_ICON_PATH)));
			this.jbPersonsFile.setActionCommand(JB_PERSONS_SELECTION);
		}

		return this.jbPersonsFile;
	}

	/**
	 * Cette méthode privée est appelée par le constructeur pour initialiser la vue.
	 */
	private void initializeView() {
		this.setLayout(new GridBagLayout());

		TitledBorder border = BorderFactory.createTitledBorder(LABEL_TITLED_BORDER);
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		border.setBorder(new LineBorder(Color.BLACK));
		this.setBorder(border);

		JLabel jlCampusFile = new JLabel(CAMPUS_LABEL);
		JLabel jlPersonFile = new JLabel(PERSONS_LABEL);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(jlCampusFile, gbc);

		gbc.gridy = 2;
		this.add(jlPersonFile, gbc);

		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets.right = 3;
		this.add(getJtfCampusFile(), gbc);

		gbc.gridx = 1;
		gbc.weightx = 0;
		gbc.insets.right = 0;
		this.add(getJbCampusFile(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets.right = 3;
		this.add(getJtfPersonsFile(), gbc);

		gbc.gridx = 1;
		gbc.insets.right = 0;
		this.add(getJbPersonsFile(), gbc);

		gbc.gridy = 4;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JPanel(), gbc);
	}
}
