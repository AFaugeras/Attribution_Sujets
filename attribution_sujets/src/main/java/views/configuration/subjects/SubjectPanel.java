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

import views.configuration.IntegerTextField;

/**
 * Cette classe permet de représenter un sujet.
 */
public class SubjectPanel extends JPanel {

	// Constantes :
	public static final String JB_DELETE_ACTION = "DELETE";

	private static final long serialVersionUID = 1L;
	
	private static final String LABEL_ID = "ID : ";
	private static final String LABEL_NAME = "Libellé :";
	private static final String LABEL_SIZE_MIN = "Effectif min : ";
	private static final String LABEL_SIZE_MAX = "Effectif max : ";
	private static final String LABEL_CARDINALITY_MIN = "Cardinalité min : ";
	private static final String LABEL_CARDINALITY_MAX = "Cardinalité max : ";
	
	private static final int SPINNER_SPINNER_VALUE = 0;
	private static final int SPINNER_MIN_VALUE= 0;
	private static final int SPINNER_MAX_VALUE = 200;
	private static final int SPINNER_STEP_SIZE = 1;
	
	private static final Icon JB_DELETE_ICON = new ImageIcon(SubjectPanel.class.getClassLoader().getResource("ihm/img/delete_subject3.png"));
	private static final Border BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	private static final Border LINE_BORDER = BorderFactory.createEmptyBorder(3, 3, 3, 3);
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
			this.jsMaxSize = new JSpinner(createSpinnerNumberModel());
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
			this.jsMinSize = new JSpinner(createSpinnerNumberModel());
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
			this.jsMaxCard = new JSpinner(createSpinnerNumberModel());
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
			this.jsMinCard = new JSpinner(createSpinnerNumberModel());
		}

		return this.jsMinCard;
	}

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
	 * Cette méthode peut-être appellée pour créer un SpinnerNumberModel avec les constantes : <br/>
	 * SPINNER_SPINNER_VALUE
	 * SPINNER_MIN_VALUE
	 * SPINNER_MAX_VALUE
	 * SPINNER_STEP_SIZE
	 * 
	 * @return Le SpinnerNumberModel.
	 */
	private SpinnerNumberModel createSpinnerNumberModel() {
		return new SpinnerNumberModel(SPINNER_SPINNER_VALUE, SPINNER_MIN_VALUE, SPINNER_MAX_VALUE, SPINNER_STEP_SIZE);
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

		JLabel jlId = new JLabel(LABEL_ID);
		JLabel jlSubjectLabel = new JLabel(LABEL_NAME);

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

		return ret;
	}

	/**
	 * Cette méthode privée initialise et ajoute au conteneur donnée en
	 * paramétre les champs de saisies des cardinalités.
	 * 
	 * @param container Le conteneur.
	 */
	private void initializeCardLine(JPanel container) {
		JLabel jlMaxCard = new JLabel(LABEL_CARDINALITY_MAX);
		JLabel jlMinCard = new JLabel(LABEL_CARDINALITY_MIN);

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
		JLabel jlMaxSize = new JLabel(LABEL_SIZE_MAX);
		JLabel jlMinSize = new JLabel(LABEL_SIZE_MIN);

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
