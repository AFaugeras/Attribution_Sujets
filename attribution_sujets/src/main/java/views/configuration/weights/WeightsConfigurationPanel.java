package views.configuration.weights;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class WeightsConfigurationPanel extends JScrollPane {

	private static final long serialVersionUID = 1L;

	public JPanel jpContainer;

	public WeightsConfigurationPanel() {
		super();

		this.initializeView();
	}

	public JPanel getJpContainer() {
		if (this.jpContainer == null) {
			this.jpContainer = new JPanel(new GridBagLayout());
		}

		return this.jpContainer;
	}

	private void initializeView() {
		this.setViewportView(this.getJpContainer());

		TitledBorder border = BorderFactory.createTitledBorder("Poids");
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		border.setBorder(new LineBorder(Color.BLACK));
		this.setBorder(border);
		
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		this.setBorder(null);
		this.getVerticalScrollBar().setUnitIncrement(15);
	}

	public static class WeightLine extends JPanel {

		private static final long serialVersionUID = 1L;

		private JLabel jlLabel;
		private JSpinner jsValue;

		public WeightLine() {
			super();

			this.jlLabel = new JLabel();
			this.jsValue = new JSpinner(new SpinnerNumberModel(0, 0,
					Integer.MAX_VALUE, 1));

			this.initializeView();
		}

		public JLabel getJlLabel() {
			return jlLabel;
		}

		public JSpinner getJsValue() {
			return jsValue;
		}

		public WeightLine(int index) {
			super();

			this.jlLabel = new JLabel("Choix " + index + " : ");
			this.jsValue = new JSpinner(new SpinnerNumberModel(0, 0,
					Integer.MAX_VALUE, 1));

			this.initializeView();
		}

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
}
