package views.configuration.weights;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class WeightPanel extends JPanel {

		private static final long serialVersionUID = 1L;

		private JLabel jlLabel;
		private JSpinner jsValue;

		public WeightPanel() {
			super();

			this.jlLabel = new JLabel();
			this.jsValue = new JSpinner(new SpinnerNumberModel(0, 0,
					Long.MAX_VALUE, 1));

			this.initializeView();
		}

		public JLabel getJlLabel() {
			return jlLabel;
		}

		public JSpinner getJsValue() {
			return jsValue;
		}

		public WeightPanel(int index, long value) {
			super();

			this.jlLabel = new JLabel("Poid " + index + " : ");
			this.jsValue = new JSpinner(new SpinnerNumberModel(value, 0,
					Long.MAX_VALUE, 1));

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