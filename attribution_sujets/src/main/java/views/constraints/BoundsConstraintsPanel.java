package views.constraints;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

public class BoundsConstraintsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// private static final Dimension PREFERED_SIZE = new Dimension(375, 140);

	private JSpinner jsMaxChoice;
	private JSpinner jsMaxReject;

	public BoundsConstraintsPanel() {
		super();

		this.initializeView();
	}

	public JSpinner getJsMaxChoice() {
		if (jsMaxChoice == null) {
			this.jsMaxChoice = new JSpinner(
					new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsMaxChoice;
	}

	public JSpinner getJsMaxReject() {
		if (jsMaxReject == null) {
			this.jsMaxReject = new JSpinner(
					new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsMaxReject;
	}

	private void initializeView() {
		this.setLayout(new GridBagLayout());

		TitledBorder border = BorderFactory
				.createTitledBorder("Choix pris en compte");
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		this.setBorder(border);

		JLabel jlMaxChoices = new JLabel("Nombre de choix maximum : ");
		JLabel jlMaxReject = new JLabel("Nombre de rejets maximum : ");

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

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		this.add(getJsMaxChoice(), gbc);

		gbc.gridy = 1;
		this.add(getJsMaxReject(), gbc);
	}
}
