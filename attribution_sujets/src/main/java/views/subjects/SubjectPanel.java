package views.subjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.border.Border;



public class SubjectPanel extends JPanel {

	
	private static final long serialVersionUID = 1L;

	private static final Border BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
	private static final Border LINE_BORDER = BorderFactory.createEmptyBorder(3, 3, 3, 3);

	
	private JTextField jtfSubjectLabel;
	private JTextField jtfID;
	
	private JSpinner jsMaxSize;
	private JSpinner jsMinSize;
	private JSpinner jsMaxCard;
	private JSpinner jsMinCard;
	private JSpinner jsMultiplicity;

	
	
	public SubjectPanel() {
		this.initializeView();
	}

	
	
	public JTextField getJtfSubjectLabel() {
		if (jtfSubjectLabel == null) {
			jtfSubjectLabel = new JTextField();
		}

		return jtfSubjectLabel;
	}

	
	
	public JTextField getJtfID() {
		if (jtfID == null) {
			jtfID = new JTextField();
		}

		return jtfID;
	}

	
	
	public JSpinner getJsMaxSize() {
		if (jsMaxSize == null) {
			jsMaxSize = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsMaxSize;
	}

	
	
	public JSpinner getJsMinSize() {
		if (jsMinSize == null) {
			jsMinSize = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsMinSize;
	}

	
	
	public JSpinner getJsMaxCard() {
		if (jsMaxCard == null) {
			jsMaxCard = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsMaxCard;
	}

	
	
	public JSpinner getJsMinCard() {
		if (jsMinCard == null) {
			jsMinCard = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsMinCard;
	}

	
	
	public JSpinner getJsMultiplicity() {
		if (jsMultiplicity == null) {
			jsMultiplicity = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsMultiplicity;
	}

	
	
	private void initializeView() {
		this.setLayout(new BorderLayout());
		
		this.setPreferredSize(new Dimension(375, 120));
		this.setBorder(BORDER);
		this.setBackground(Color.WHITE);

		this.add(getIdLabelPanel(), BorderLayout.NORTH);
		this.add(getSizePanel(), BorderLayout.CENTER);
//		this.add(getCardinalityPanel());
//		this.add(getMultiplicityPanel());
	}

	
	
	private JPanel getIdLabelPanel() {
		JPanel ret = new JPanel(new GridBagLayout());
		ret.setBorder(LINE_BORDER);

		JLabel jlId = new JLabel("ID :");
		JLabel jlSubjectLabel = new JLabel("Libellé :");

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		ret.add(jlId, gbc);

		gbc.gridx = 2;
		gbc.insets.left = 5;
		ret.add(jlSubjectLabel, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0.35;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		ret.add(getJtfID(), gbc);

		gbc.gridx = 3;
		gbc.weightx = 1;
		ret.add(getJtfSubjectLabel(), gbc);

		return ret;
	}

	
	
	private JPanel getSizePanel() {
		JPanel ret = new JPanel(new GridBagLayout());
		ret.setBorder(LINE_BORDER);

		JLabel jlMaxSize = new JLabel("Taille max :");
		JLabel jlMinSize = new JLabel("Taille min :");
		JLabel jlMaxCard = new JLabel("Card max :");
		JLabel jlMinCard = new JLabel("Card min :");
		JLabel jlMultiplicity = new JLabel("Multiplicité :");

		GridBagConstraints gbc = new GridBagConstraints();
		
		initializeSizeLine(ret, jlMaxSize, jlMinSize, gbc);
		initializeCardLine(ret, jlMaxCard, jlMinCard, gbc);
		initializeMultiplicityLine(ret, jlMultiplicity, gbc);

		return ret;
	}



	private void initializeMultiplicityLine(JPanel ret, JLabel jlMultiplicity,
			GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets.left = 0;
		ret.add(jlMultiplicity, gbc);

		gbc.gridx = 1;
		gbc.insets.left = 5;
		ret.add(getJsMultiplicity(), gbc);
	}



	private void initializeCardLine(JPanel ret, JLabel jlMaxCard,
			JLabel jlMinCard, GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets.left = 0;
		ret.add(jlMaxCard, gbc);

		gbc.gridx = 2;
		gbc.insets.left = 5;
		ret.add(jlMinCard, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0;
		gbc.fill = GridBagConstraints.NONE;
		ret.add(getJsMaxCard(), gbc);

		gbc.gridx = 3;
		ret.add(getJsMinCard(), gbc);
	}



	private void initializeSizeLine(JPanel ret, JLabel jlMaxSize,
			JLabel jlMinSize, GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.weighty = 1;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.NONE;
		ret.add(jlMaxSize, gbc);

		gbc.gridx = 2;
		gbc.insets.left = 5;
		ret.add(jlMinSize, gbc);

		gbc.gridx = 1;
		gbc.weightx = 0;
		ret.add(getJsMaxSize(), gbc);

		gbc.gridx = 3;
		ret.add(getJsMinSize(), gbc);

		gbc.gridx = 4;
		gbc.weightx = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		ret.add(new JPanel(), gbc);
	}

	// TODO : Development method to delete.
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager
					.getSystemLookAndFeelClassName());
		} catch (Exception e){
			
		}
		
		JFrame frameTest = new JFrame();
		frameTest.setLayout(new GridBagLayout());
		SubjectPanel tmp = new SubjectPanel();
		frameTest.add(tmp, new GridBagConstraints(0, 0, 1, 1, 1,
				1, GridBagConstraints.CENTER, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0));
		frameTest.setPreferredSize(new Dimension(1200, 600));
		frameTest.pack();
		frameTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTest.setLocationRelativeTo(null);
		frameTest.setVisible(true);
//		frameTest.setPreferredSize(new Dimension(1200, 600));
		
		System.out.println(tmp.getSize());
	}
}
