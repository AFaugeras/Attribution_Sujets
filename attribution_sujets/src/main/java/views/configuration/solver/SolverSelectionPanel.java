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

/**
 * Panel de sélection du solveur. *
 */
public class SolverSelectionPanel extends JPanel {

	// Constantes :
	public static final String CHOCO_SOLVER = "Choco";
	public static final String GLPK_SOLVER = "GLPK";
	
	private static final long serialVersionUID = 1L;

	private static final String[] SOLVER_NAMES = { GLPK_SOLVER, CHOCO_SOLVER };

	/**
	 * Combobox de choix du solveur.
	 */
	private JComboBox<String> jcbSolvers;

	/**
	 * Constructeur.
	 */
	public SolverSelectionPanel() {
		super();

		initializeView();
	}

	/**
	 * Accesseur de l'attribut jcbSolver.
	 * 
	 * @return La combobox jcbSolver.
	 */
	public JComboBox<String> getJcbSolvers() {
		if (this.jcbSolvers == null) {
			this.jcbSolvers = new JComboBox<String>(SOLVER_NAMES);
		}
		
		return this.jcbSolvers;
	}

	/**
	 * Cette méthode privée est appelée par le constructeur pour initialiser la vue.
	 */
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
