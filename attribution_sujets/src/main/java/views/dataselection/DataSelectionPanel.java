package views.dataselection;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class DataSelectionPanel extends JPanel {

	public static final String JB_CAMPUS_SELECTION = "CAMPUS_SELECTION";
	public static final String JB_PERSON_SELECTION = "PERSON_SELECTION";

	private static final long serialVersionUID = 1L;

	private JTextField jtfCampusFile;
	private JTextField jtfPersonsFile;

	private JButton jbCampusFile;
	private JButton jbPersonsFile;

	public DataSelectionPanel() {
		super();

		initializeView();
	}

	public JTextField getJtfCampusFile() {
		if (this.jtfCampusFile == null) {
			this.jtfCampusFile = new JTextField();
			this.jtfCampusFile.setEditable(false);
			this.jtfCampusFile.setBackground(Color.WHITE);
		}

		return this.jtfCampusFile;
	}

	public JTextField getJtfPersonsFile() {
		if (this.jtfPersonsFile == null) {
			this.jtfPersonsFile = new JTextField();
			this.jtfPersonsFile.setEditable(false);
			this.jtfPersonsFile.setBackground(Color.WHITE);
		}

		return this.jtfPersonsFile;
	}

	public JButton getJbCampusFile() {
		if (this.jbCampusFile == null) {
			this.jbCampusFile = new JButton("...");
			this.jbCampusFile.setActionCommand(JB_CAMPUS_SELECTION);
		}

		return this.jbCampusFile;
	}

	public JButton getJbPersonsFile() {
		if (this.jbPersonsFile == null) {
			this.jbPersonsFile = new JButton("...");
			this.jbPersonsFile.setActionCommand(JB_PERSON_SELECTION);
		}

		return this.jbPersonsFile;
	}

	private void initializeView() {
		this.setLayout(new GridBagLayout());

		TitledBorder border = BorderFactory.createTitledBorder("Données");
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		this.setBorder(border);

		JLabel jlCampusFile = new JLabel("Fichier campus :");
		JLabel jlPersonFile = new JLabel("Elèves :");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(jlCampusFile, gbc);

		gbc.gridy = 2;
		this.add(jlPersonFile, gbc);

		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(getJtfCampusFile(), gbc);

		gbc.gridx = 1;
		gbc.weightx = 0;
		this.add(getJbCampusFile(), gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(getJtfPersonsFile(), gbc);

		gbc.gridx = 1;
		this.add(getJbPersonsFile(), gbc);

		gbc.gridy = 4;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(new JPanel(), gbc);
	}
}
