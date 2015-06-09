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
 * Panel de saisie des nombres maximums de choix et de rejets pris en compte.
 */
public class SolverConfigurationPanel extends JPanel {

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
	 * Spinner de saisie du nombre minimum du sujet effecté.
	 */
	private JSpinner jsMinAssigned;
	
	/**
	 * Spinner de saisie pour la multiplicité.
	 */
	private JSpinner jsMultiplicity;

	/**
	 * Constructeur.
	 */
	public SolverConfigurationPanel() {
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
			this.jsMaxChoice = new JSpinner(new SpinnerNumberModel(0, 0, 0, 1));
			((JSpinner.NumberEditor) this.jsMaxChoice.getEditor()).getTextField().setColumns(3);
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
			this.jsMaxReject = new JSpinner(new SpinnerNumberModel(0, 0, 0, 1));
			((JSpinner.NumberEditor) this.jsMaxReject.getEditor()).getTextField().setColumns(3);
		}

		return this.jsMaxReject;
	}
	
	/**
	 * Accesseur de l'attribut jsMinAssigned.
	 * 
	 * @return Le spinner jsMinAssigned.
	 */
	public JSpinner getJsMinAssigned() {
		if (this.jsMinAssigned == null) {
			this.jsMinAssigned = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}
		
		return this.jsMinAssigned;
	}

	/**
	 * Accesseur de l'attribut jtfMultiplicity.
	 * 
	 * @return Le textfield jtfMultiplicity.
	 */
	public JSpinner getJsMultiplicity() {
		if (this.jsMultiplicity == null) {
			this.jsMultiplicity = new JSpinner(new SpinnerNumberModel(1, 1, 200, 1));
		}

		return this.jsMultiplicity;
	}

	/**
	 * Cette méthode privée est appelée par le constructeur pour initialiser la vue.
	 */
	private void initializeView() {
		this.setLayout(new GridBagLayout());

		TitledBorder border = BorderFactory.createTitledBorder("Paramètre solveur");
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		border.setBorder(new LineBorder(Color.BLACK));
		this.setBorder(border);

		JLabel jlMaxChoices = new JLabel("Nombre de choix maximum : ");
		JLabel jlMaxReject = new JLabel("Nombre de rejets maximum : ");
		JLabel jlMinAssigned = new JLabel("Minimum assigné : ");
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
		this.add(jlMinAssigned, gbc);
		
		gbc.gridy = 3;
		this.add(jlMultiplicity, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets.bottom = 3;
		this.add(getJsMaxChoice(), gbc);

		gbc.gridy = 1;
		this.add(getJsMaxReject(), gbc);
		
		gbc.gridy = 2;
		this.add(getJsMinAssigned(), gbc);
		
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(getJsMultiplicity(), gbc);
		
		gbc.gridx = 2;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JPanel(), gbc);
	}
}
