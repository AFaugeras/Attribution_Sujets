package views.configuration;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import views.configuration.constraints.BoundsConstraintsPanel;
import views.configuration.constraints.CampusConstraintsPanel;
import views.configuration.dataselection.DataSelectionPanel;
import views.configuration.subjects.SubjectsConfigurationPanel;
import views.configuration.weights.WeightsConfigurationPanel;

/**
 * Panel de configuration.
 */
public class ConfigurationPanel extends JPanel {

	// Constantes :
	public static final String JB_NEXT_ACTION = "NEXT";

	private static final long serialVersionUID = 1L;

	/**
	 * Panel de configuration des sujets.
	 */
	private SubjectsConfigurationPanel subjectsPanel;

	/**
	 * Panel bornes min et max.
	 */
	private BoundsConstraintsPanel boundConstraintsPanel;

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
	private JButton jbNext;

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
	 * Accesseur de l'attribut boundConstraintsPanel.
	 *
	 * @return Le panel boundConstraintsPanel.
	 */
	public BoundsConstraintsPanel getBoundConstraintsPanel() {
		if (this.boundConstraintsPanel == null) {
			this.boundConstraintsPanel = new BoundsConstraintsPanel();
		}

		return this.boundConstraintsPanel;
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
	public JButton getJbNext() {
		if (jbNext == null) {
			jbNext = new JButton("R�partir", new ImageIcon(this.getClass()
					.getClassLoader().getResource("ihm/img/next2.png")));
			jbNext.setActionCommand(JB_NEXT_ACTION);
		}

		return jbNext;
	}

	/**
	 * Cette m�thode priv�e appel�e par le constructeur initialise la vue.
	 */
	private void initializeView() {
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createEmptyBorder(9, 9, 9, 9));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridheight = 4;
		this.add(getSubjectsPanel(), gbc);

		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.gridheight = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(getBoundConstraintsPanel(), gbc);

		gbc.gridy = 1;
		this.add(getCampusConstraintsPanel(), gbc);

		gbc.gridy = 2;
		this.add(getDataSelectionPanel(), gbc);
		
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(getWeightsConfigurationPanel(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.weighty = 0;
		gbc.gridwidth = 2;
		gbc.insets.top = 9;
		this.add(new JSeparator(), gbc);

		gbc.gridy = 5;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		this.add(getJbNext(), gbc);
	}
}
