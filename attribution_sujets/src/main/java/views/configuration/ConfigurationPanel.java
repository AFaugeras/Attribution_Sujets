package views.configuration;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import views.configuration.constraints.SolverConfigurationPanel;
import views.configuration.constraints.CampusConstraintsPanel;
import views.configuration.dataselection.DataSelectionPanel;
import views.configuration.solver.SolverSelectionPanel;
import views.configuration.subjects.SubjectsConfigurationPanel;
import views.configuration.weights.WeightsConfigurationPanel;

/**
 * Panel de configuration.
 */
public class ConfigurationPanel extends JPanel {

	// Constantes :
	public static final String JB_DIVIDE_ACTION = "NEXT";
	
	private static final long serialVersionUID = 1L;

	private static final String LABEL_JB_DIVIDE = "Répartir";
	private static final String ICON_PATH_JB_DIVIDE = "ihm/img/next2.png";
	
	/**
	 * Panel de configuration des sujets.
	 */
	private SubjectsConfigurationPanel subjectsPanel;
	
	/**
	 * Panel de sélection du solveur.
	 */
	private SolverSelectionPanel solverSelectionPanel;

	/**
	 * Panel de configuration du solver.
	 */
	private SolverConfigurationPanel SolverConfigurationPanel;

	/**
	 * Panel de configuration campus.
	 */
	private CampusConstraintsPanel campusConstraintsPanel;

	/**
	 * Panel de selection du fichier campus et de la liste de personnes.
	 */
	private DataSelectionPanel dataSelectionPanel;
	
	/**
	 * Panel de configuration des poids.
	 */
	private WeightsConfigurationPanel weightsConfigurationPanel;

	/**
	 * Bouton suivant.
	 */
	private JButton jbDivide;

	/**
	 * Constructeur.
	 */
	public ConfigurationPanel() {
		super();

		this.initializeView();
	}

	/**
	 * Accesseur de l'attribut subjectsPanel.
	 *
	 * @return Le panel subjectsPanel.
	 */
	public SubjectsConfigurationPanel getSubjectsPanel() {
		if (this.subjectsPanel == null) {
			this.subjectsPanel = new SubjectsConfigurationPanel();
		}

		return this.subjectsPanel;
	}

	/**
	 * Accesseur de l'attribut subjectsPanel.
	 *
	 * @return Le panel subjectsPanel.
	 */
	public SolverSelectionPanel getSolverSelectionPanel() {
		if (this.solverSelectionPanel == null) {
			this.solverSelectionPanel = new SolverSelectionPanel();
		}

		return this.solverSelectionPanel;
	}

	/**
	 * Accesseur de l'attribut boundConstraintsPanel.
	 *
	 * @return Le panel boundConstraintsPanel.
	 */
	public SolverConfigurationPanel getSolverParametersPanel() {
		if (this.SolverConfigurationPanel == null) {
			this.SolverConfigurationPanel = new SolverConfigurationPanel();
		}

		return this.SolverConfigurationPanel;
	}

	/**
	 * Accesseur de l'attribut campusConstraintsPanel.
	 *
	 * @return Le panel campusConstraintsPanel.
	 */
	public CampusConstraintsPanel getCampusConstraintsPanel() {
		if (this.campusConstraintsPanel == null) {
			this.campusConstraintsPanel = new CampusConstraintsPanel();
		}

		return this.campusConstraintsPanel;
	}

	/**
	 * Accesseur de l'attribut dataSelectionPanel.
	 *
	 * @return Le panel dataSelectionPanel.
	 */
	public DataSelectionPanel getDataSelectionPanel() {
		if (this.dataSelectionPanel == null) {
			this.dataSelectionPanel = new DataSelectionPanel();
		}

		return this.dataSelectionPanel;
	}

	/**
	 * Accesseur de l'attribut weightsConfigurationPanel.
	 *
	 * @return Le panel weightsConfigurationPanel.
	 */
	public WeightsConfigurationPanel getWeightsConfigurationPanel() {
		if (this.weightsConfigurationPanel == null) {
			this.weightsConfigurationPanel = new WeightsConfigurationPanel();
		}

		return this.weightsConfigurationPanel;
	}

	/**
	 * Accesseur de l'attribut jbNext.
	 *
	 * @return Le JButton jbNext.
	 */
	public JButton getJbDivide() {
		if (jbDivide == null) {
			jbDivide = new JButton(LABEL_JB_DIVIDE, new ImageIcon(this.getClass().getClassLoader().getResource(ICON_PATH_JB_DIVIDE)));
			jbDivide.setActionCommand(JB_DIVIDE_ACTION);
		}

		return jbDivide;
	}

	/**
	 * Cette méthode privée appelée par le constructeur initialise la vue.
	 */
	private void initializeView() {
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));

		// Ajout du panel de configuration des sujets.
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = 5;
		gbc.insets.right = 9;
		this.add(getSubjectsPanel(), gbc);

		// Ajout du panel de sélection du solver.
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets.right = 0;
		this.add(getSolverSelectionPanel(), gbc);
		
		// Ajout du panel de configuration campus.
		gbc.gridy = 1;
		this.add(getCampusConstraintsPanel(), gbc);

		// Ajout du panel de sélection des données (fichier réponses campus et liste de personnes).
		gbc.gridy = 2;
		this.add(getDataSelectionPanel(), gbc);

		// Ajout du panel de configuration du solver.
		gbc.gridy = 3;
		this.add(getSolverParametersPanel(), gbc);
		
		// Ajout du panel de configuration des poids.
		gbc.gridy = 4;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(getWeightsConfigurationPanel(), gbc);

		// Ajout d'un séparateur horizontal.
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.weighty = 0;
		gbc.gridwidth = 2;
		gbc.insets.top = 9;
		this.add(new JSeparator(), gbc);

		// Ajout du bouton "Répartir".
		gbc.gridy = 6;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(getJbDivide(), gbc);
	}
}
