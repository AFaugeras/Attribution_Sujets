package views.configuration.weights;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * Cette classe permet de représenter un poids.
 *          _________
 * Label : |___Valeur|
 */
public class WeightPanel extends JPanel {

	// Constante :
	private static final long serialVersionUID = 1L;
	
	private static final long DEFAULT_WEIGHT = 0;
	private static final long DEFAULT_MIN_WEIGHT = 0;
	private static final long DEFAULT_MAX_WEIGHT = Long.MAX_VALUE;
	private static final long STEP_SIZE = 1;
	
	/**
	 * Libellé.
	 */
	private JLabel jlLabel;
	
	/**
	 * Spinner de saisie du poids.
	 */
	private JSpinner jsValue;

	/**
	 * Constructeur par défaut.
	 */
	public WeightPanel() {
		super();

		this.jlLabel = new JLabel();
		this.jsValue = new JSpinner(new SpinnerNumberModel(DEFAULT_WEIGHT, DEFAULT_MIN_WEIGHT, DEFAULT_MAX_WEIGHT, STEP_SIZE));

		this.initializeView();
	}

	/**
	 * Constructeur avec paramétres.
	 * 
	 * @param index Le numéro de la ligne.
	 * @param value La valeur du poids.
	 */
	public WeightPanel(int index, long value) {
		super();

		this.jlLabel = new JLabel("Poids " + index + " : ");
		this.jsValue = new JSpinner(new SpinnerNumberModel(value, DEFAULT_MIN_WEIGHT, DEFAULT_MAX_WEIGHT, STEP_SIZE));

		this.initializeView();
	}

	/**
	 * Accesseur de l'attribut jlLabel.
	 * 
	 * @return Le JLabel du libellé.
	 */
	public JLabel getJlLabel() {
		return jlLabel;
	}

	/**
	 * Accesseur de l'attribut jsValue.
	 * 
	 * @return Le spinner du saisie du poids.
	 */
	public JSpinner getJsValue() {
		return jsValue;
	}

	/**
	 * Cette méthode privée appelée par le constructeur initialiser la vue.
	 */
	private void initializeView() {
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		this.add(getJlLabel(), gbc);

		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(getJsValue(), gbc);
	}
}