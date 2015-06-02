package views.configuration.solver;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SolverSelectionPanel extends JPanel {

	// Constantes :
	private static final long serialVersionUID = 1L;

	private static final String[] SOLVER_NAMES = { "Choco", "GLPK" };

	private JComboBox<String> jcbSolvers;

	/**
	 * Constructeur.
	 */
	public SolverSelectionPanel() {
		super();

		initializeView();
	}

	public JComboBox<String> getJcbSolvers() {
		if (this.jcbSolvers == null) {
			this.jcbSolvers = new JComboBox<String>(SOLVER_NAMES);
		}
		
		return this.jcbSolvers;
	}

	private void initializeView() {
		this.setLayout(new GridBagLayout());

		TitledBorder border = BorderFactory.createTitledBorder("Solveur");
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		border.setBorder(new LineBorder(Color.BLACK));
		this.setBorder(border);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		this.add(new JLabel("Solveur : "), gbc);
		
		gbc.gridx = 1;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(getJcbSolvers(), gbc);
	}
}
