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

import views.configuration.IntegerTextField;

/**
 * Panel de saisie des nombres maximums de choix et de rejets pris en compte.
 */
public class BoundsConstraintsPanel extends JPanel {

	// Constantes :
	private static final long serialVersionUID = 1L;

	/**
	 * Spinner de saisie du nombre maximal de choix pris en compte.
	 */
	private JSpinner jsMaxChoice;
	
	/**
	 * Spinner de saisie du nombre maximal de rejets pris en compte.
	 */
	private JSpinner jsMaxReject;
	
	/**
	 * Champ de saisie pour la multiplicité.
	 */
	private IntegerTextField jtfMultiplicity;

	/**
	 * Constructeur.
	 */
	public BoundsConstraintsPanel() {
		super();

		this.initializeView();
	}

	/**
	 * Accesseur de l'attribut jsMaxChoice.
	 * 
	 * @return Le spinner jsMaxChoice.
	 */
	public JSpinner getJsMaxChoice() {
		if (this.jsMaxChoice == null) {
			this.jsMaxChoice = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsMaxChoice;
	}

	/**
	 * Accesseur de l'attribut jsMaxChoice.
	 * 
	 * @return Le spinner jsMaxChoice.
	 */
	public JSpinner getJsMaxReject() {
		if (this.jsMaxReject == null) {
			this.jsMaxReject = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsMaxReject;
	}

	/**
	 * Accesseur de l'attribut jtfMultiplicity.
	 * 
	 * @return Le textfield jtfMultiplicity.
	 */
	public IntegerTextField getJtfMultiplicity() {
		if (this.jtfMultiplicity == null) {
			this.jtfMultiplicity = new IntegerTextField();
		}

		return this.jtfMultiplicity;
	}

	/**
	 * Cette méthode privée est appelée par le constructeur pour initialiser la vue.
	 */
	private void initializeView() {
		this.setLayout(new GridBagLayout());

		TitledBorder border = BorderFactory
				.createTitledBorder("Choix pris en compte");
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		border.setBorder(new LineBorder(Color.BLACK));
		this.setBorder(border);

		JLabel jlMaxChoices = new JLabel("Nombre de choix maximum : ");
		JLabel jlMaxReject = new JLabel("Nombre de rejets maximum : ");
		JLabel jlMultiplicity = new JLabel("Multiplicité : ");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		this.add(jlMaxChoices, gbc);

		gbc.gridy = 1;
		this.add(jlMaxReject, gbc);
		
		gbc.gridy = 2;
		this.add(jlMultiplicity, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets.bottom = 3;
		this.add(getJsMaxChoice(), gbc);

		gbc.gridy = 1;
		this.add(getJsMaxReject(), gbc);
		
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(getJtfMultiplicity(), gbc);
		
		gbc.gridx = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JPanel(), gbc);
	}
}
