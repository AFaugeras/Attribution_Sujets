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

public class CampusConstraintsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JSpinner jsNbChoice;
	private JSpinner jsNbReject;

	public CampusConstraintsPanel() {
		super();

		this.initializeView();
	}

	private void initializeView() {
		this.setLayout(new GridBagLayout());

		JLabel jlNbChoice = new JLabel("Nombre de choix : ");
		JLabel jlNbReject = new JLabel("Nombre de rejets : ");

		TitledBorder border = BorderFactory
				.createTitledBorder("Configuration campus");
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
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
		this.add(getJsNbChoice(), gbc);

		gbc.gridy = 1;
		this.add(getJsNbReject(), gbc);
	}

	public JSpinner getJsNbChoice() {
		if (jsNbChoice == null) {
			this.jsNbChoice = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsNbChoice;
	}

	public JSpinner getJsNbReject() {
		if (jsNbReject == null) {
			this.jsNbReject = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsNbReject;
	}
}
