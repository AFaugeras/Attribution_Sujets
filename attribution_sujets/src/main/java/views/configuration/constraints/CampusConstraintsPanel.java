package views.configuration.constraints;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel de saisie de la configuration campus (nombre de choix et de rejets).
 */
public class CampusConstraintsPanel extends JPanel {

	// Constantes :
	private static final long serialVersionUID = 1L;
	
	private static final int SPINNER_SPINNER_VALUE = 0;
	private static final int SPINNER_MIN_VALUE= 0;
	private static final int SPINNER_MAX_VALUE = 200;
	private static final int SPINNER_STEP_SIZE = 1;

	/**
	 * Spinner de saisie du nombre de choix.
	 */
	private JSpinner jsNbChoice;
	
	/**
	 * Spinner de saisie du nombre de rejet.
	 */
	private JSpinner jsNbReject;

	/**
	 * Constructeur.
	 */
	public CampusConstraintsPanel() {
		super();

		this.initializeView();
	}

	/**
	 * Accesseur de l'attribut jsNbChoice.
	 * 
	 * @return Le spinner jsNbChoice.
	 */
	public JSpinner getJsNbChoice() {
		if (this.jsNbChoice == null) {
			this.jsNbChoice = new JSpinner(createSpinnerNumberModel());
		}

		return this.jsNbChoice;
	}

	/**
	 * Accesseur de l'attribut jsNbReject.
	 * 
	 * @return Le spinner jsNbReject.
	 */
	public JSpinner getJsNbReject() {
		if (this.jsNbReject == null) {
			this.jsNbReject = new JSpinner(createSpinnerNumberModel());
		}

		return this.jsNbReject;
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
	 * Cette méthode privée est appelée par le constructeur pour initialiser la vue.
	 */
	private void initializeView() {
		this.setLayout(new GridBagLayout());

		JLabel jlNbChoice = new JLabel("Nombre de choix : ");
		JLabel jlNbReject = new JLabel("Nombre de rejets : ");

		TitledBorder border = BorderFactory
				.createTitledBorder("Configuration campus");
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		border.setBorder(new LineBorder(Color.BLACK));
		this.setBorder(border);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		this.add(jlNbChoice, gbc);

		gbc.gridy = 1;
		this.add(jlNbReject, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.insets.bottom = 3;
		this.add(getJsNbChoice(), gbc);

		gbc.gridy = 1;
		this.add(getJsNbReject(), gbc);
	}
}
