package views.constraints;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class GeneralConstraintsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final Border BORDER = new TitledBorder("Contraintes");
	private static final Dimension PREFERED_SIZE = new Dimension(375, 140);

	private JSpinner jsMaxChoice;
	private JSpinner jsMaxReject;
	private JSpinner jsNbChoice;
	private JSpinner jsNbReject;

	public GeneralConstraintsPanel() {
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

	@Override
	public Dimension getPreferredSize() {
		return PREFERED_SIZE;
	}

	private void initializeView() {
		this.setLayout(new GridBagLayout());
		this.setBorder(BORDER);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(getBoundsPanel(), gbc);

		gbc.gridy = 1;
		this.add(getCampusBoundsPanel(), gbc);

		gbc.gridy = 2;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JPanel(), gbc);
	}

	private JPanel getBoundsPanel() {
		JPanel ret = new JPanel(new GridBagLayout());
		ret.setBorder(BorderFactory.createTitledBorder("Choix pris en compte"));

		JLabel jlMaxChoices = new JLabel("Nombre de choix maximum : ");
		JLabel jlMaxReject = new JLabel("Nombre de rejets maximum : ");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		ret.add(jlMaxChoices, gbc);

		gbc.gridy = 1;
		ret.add(jlMaxReject, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		ret.add(getJsMaxChoice(), gbc);

		gbc.gridy = 1;
		ret.add(getJsMaxReject(), gbc);

		return ret;
	}

	private JPanel getCampusBoundsPanel() {
		JPanel ret = new JPanel(new GridBagLayout());
		ret.setBorder(BorderFactory.createTitledBorder("Configuration campus"));

		JLabel jlNbChoice = new JLabel("Nombre de choix : ");
		JLabel jlNbReject = new JLabel("Nombre de rejets : ");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		ret.add(jlNbChoice, gbc);

		gbc.gridy = 1;
		ret.add(jlNbReject, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1;
		ret.add(getJsNbChoice(), gbc);

		gbc.gridy = 1;
		ret.add(getJsNbReject(), gbc);

		return ret;
	}
}
