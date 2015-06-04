package views.configuration.subjects;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

/**
 * Ce widget permet de représenter un sujet.
 */
public class SubjectPanel extends JPanel {

	// Constantes :
	public static final String JB_DELETE_ACTION = "DELETE";

	private static final long serialVersionUID = 1L;
	private static final Icon JB_DELETE_ICON = new ImageIcon(SubjectPanel.class
			.getClassLoader().getResource("ihm/img/delete_subject.png"));
	private static final Border BORDER = BorderFactory.createEmptyBorder(5, 5,
			5, 5);
	private static final Border LINE_BORDER = BorderFactory.createEmptyBorder(
			3, 3, 3, 3);
	private static final Dimension PREFERED_SIZE = new Dimension(375, 100);
	private static final Dimension JB_DELETE_SIZE = new Dimension(30, 30);

	/**
	 * Champ de saisie du libellé du sujet.
	 */
	private JTextField jtfSubjectLabel;

	/**
	 * Champ de saisie de l'id.
	 */
	private JTextField jtfID;

	/**
	 * Spinner nombre d'élèves maximum.
	 */
	private JSpinner jsMaxSize;
	
	/**
	 * Spinner nombre d'élèves minimum.
	 */
	private JSpinner jsMinSize;
	
	/**
	 * Spinner cardinalité minimale.
	 */
	private JSpinner jsMaxCard;
	
	/**
	 * Spinner cardinalité maximale.
	 */
	private JSpinner jsMinCard;
	
//	/**
//	 * Spinner multiplicité.
//	 */
//	private JSpinner jsMultiplicity;

	/**
	 * Button supprimer.
	 */
	private JButton jbDelete;

	/**
	 * Constructeur.
	 */
	public SubjectPanel() {
		this.initializeView();
	}

	/**
	 * Accesseur de l'attribut jtfSubjectLabel.
	 * 
	 * @return Le JTextField jtfSubjectLabel.
	 */
	public JTextField getJtfSubjectLabel() {
		if (this.jtfSubjectLabel == null) {
			this.jtfSubjectLabel = new JTextField();
		}

		return this.jtfSubjectLabel;
	}

	/**
	 * Accesseur de l'attribut jtfID.
	 * 
	 * @return Le JTextField jtfID.
	 */
	public JTextField getJtfID() {
		if (this.jtfID == null) {
			this.jtfID = new IntegerTextField();
		}

		return this.jtfID;
	}

	/**
	 * Acceseur de l'attribut jsMaxSize.
	 * 
	 * @return Le JSpinner jsMaxSize.
	 */
	public JSpinner getJsMaxSize() {
		if (this.jsMaxSize == null) {
			this.jsMaxSize = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return this.jsMaxSize;
	}

	/**
	 * Acceseur de l'attribut jsMinSize.
	 * 
	 * @return Le JSpinner jsMinSize.
	 */
	public JSpinner getJsMinSize() {
		if (this.jsMinSize == null) {
			this.jsMinSize = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return this.jsMinSize;
	}

	/**
	 * Acceseur de l'attribut jsMaxCard.
	 * 
	 * @return Le JSpinner jsMaxCard.
	 */
	public JSpinner getJsMaxCard() {
		if (this.jsMaxCard == null) {
			this.jsMaxCard = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return this.jsMaxCard;
	}

	/**
	 * Acceseur de l'attribut jsMinCard.
	 * 
	 * @return Le JSpinner jsMinCard.
	 */
	public JSpinner getJsMinCard() {
		if (this.jsMinCard == null) {
			this.jsMinCard = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return this.jsMinCard;
	}

//	/**
//	 * Acceseur de l'attribut jsMultiplicity.
//	 * 
//	 * @return Le JSpinner jsMultiplicity.
//	 */
//	public JSpinner getJsMultiplicity() {
//		if (this.jsMultiplicity == null) {
//			this.jsMultiplicity = new JSpinner(new SpinnerNumberModel(0, 0,
//					200, 1));
//		}
//
//		return this.jsMultiplicity;
//	}

	/**
	 * Accesseur de l'attribut jbDelete.
	 * 
	 * @return Le JButton jbDelete.
	 */
	public JButton getJbDelete() {
		if (this.jbDelete == null) {
			this.jbDelete = new JButton(JB_DELETE_ICON);
			this.jbDelete.setPreferredSize(JB_DELETE_SIZE);
		}

		return this.jbDelete;
	}

	@Override
	public Dimension getPreferredSize() {
		return PREFERED_SIZE;
	}

	/**
	 * Cette méthode privée est appelée par le constructeur pour initialiser la vue.
	 */
	private void initializeView() {
		this.setLayout(new BorderLayout());

		this.setBorder(BORDER);

		this.add(getInformationPanel(), BorderLayout.CENTER);

		JPanel tmp = new JPanel(new GridBagLayout());
		tmp.add(getJbDelete(), new GridBagConstraints());
		this.add(tmp, BorderLayout.EAST);

		this.add(new JSeparator(), BorderLayout.SOUTH);
	}

	/**
	 * Cette méthode privée construit le panel d'information (qui contient tous
	 * les champs de saisies).
	 * 
	 * @return Le JPanel.
	 */
	private JPanel getInformationPanel() {
		JPanel ret = new JPanel(new BorderLayout());

		ret.add(getIdLabelPanel(), BorderLayout.NORTH);
		ret.add(getSizePanel(), BorderLayout.CENTER);

		return ret;
	}

	/**
	 * Cette méthode privée construit la ligne (panel) de saisie de l'id et du
	 * libellé.
	 * 
	 * @return Le JPanel.
	 */
	private JPanel getIdLabelPanel() {
		JPanel ret = new JPanel(new GridBagLayout());
		ret.setBorder(LINE_BORDER);

		JLabel jlId = new JLabel("ID :");
		JLabel jlSubjectLabel = new JLabel("Libellé :");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		ret.add(jlId, gbc);

		gbc.gridx = 2;
		gbc.insets.left = 5;
		ret.add(jlSubjectLabel, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0.35;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		ret.add(getJtfID(), gbc);

		gbc.gridx = 3;
		gbc.weightx = 1;
		gbc.insets.right = 5;
		ret.add(getJtfSubjectLabel(), gbc);

		return ret;
	}

	/**
	 * Cette méthode privée la zone de saisie (panel) des bornes et de la
	 * multiplicité.
	 * 
	 * @return Le JPanel.
	 */
	private JPanel getSizePanel() {
		JPanel ret = new JPanel(new GridBagLayout());
		ret.setBorder(LINE_BORDER);

		initializeSizeLine(ret);
		initializeCardLine(ret);
//		initializeMultiplicityLine(ret);

		return ret;
	}

//	/**
//	 * Cette méthode privée initialise et ajoute au conteneur donné en paramétre
//	 * le champ de saisie de la multiplicité.
//	 * 
//	 * @param container Le conteneur.
//	 */
//	private void initializeMultiplicityLine(JPanel container) {
//		JLabel jlMultiplicity = new JLabel("Multiplicité :");
//
//		GridBagConstraints gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 2;
//		gbc.weightx = 0;
//		gbc.weighty = 1;
//		gbc.anchor = GridBagConstraints.LINE_START;
//		gbc.fill = GridBagConstraints.NONE;
//		gbc.insets.left = 0;
//		container.add(jlMultiplicity, gbc);
//
//		gbc.gridx = 1;
//		gbc.insets.left = 5;
//		container.add(getJsMultiplicity(), gbc);
//	}

	/**
	 * Cette méthode privée initialise et ajoute au conteneur donnée en
	 * paramétre les champs de saisies des cardinalités.
	 * 
	 * @param container Le conteneur.
	 */
	private void initializeCardLine(JPanel container) {
		JLabel jlMaxCard = new JLabel("Card max :");
		JLabel jlMinCard = new JLabel("Card min :");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets.left = 0;
		container.add(jlMinCard, gbc);

		gbc.gridx = 2;
		gbc.insets.left = 5;
		container.add(jlMaxCard, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		container.add(getJsMinCard(), gbc);

		gbc.gridx = 3;
		container.add(getJsMaxCard(), gbc);
	}

	/**
	 * Cette méthode privée initialise et ajoute au conteneur donnée en
	 * paramétre les champs de saisies de la taille.
	 * 
	 * @param container Le conteneur.
	 */
	private void initializeSizeLine(JPanel container) {
		JLabel jlMaxSize = new JLabel("Taille max :");
		JLabel jlMinSize = new JLabel("Taille min :");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		container.add(jlMinSize, gbc);

		gbc.gridx = 2;
		gbc.insets.left = 5;
		container.add(jlMaxSize, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0;
		container.add(getJsMinSize(), gbc);

		gbc.gridx = 3;
		container.add(getJsMaxSize(), gbc);

		gbc.gridx = 4;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		container.add(new JPanel(), gbc);
	}
}
