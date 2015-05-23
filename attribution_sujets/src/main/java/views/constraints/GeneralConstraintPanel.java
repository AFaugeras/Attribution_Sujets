package views.constraints;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GeneralConstraintPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JSpinner jsMaxChoices;
	private JSpinner jsMinChoices;
	private JSpinner jsNbChoices;
	private JSpinner jsNbReject;

	public GeneralConstraintPanel() {
		super();

		add(new JLabel("Configuration générale"));

		this.initializeView();
	}

	public JSpinner getJsMaxChoices() {
		if (jsMaxChoices == null) {
			this.jsMaxChoices = new JSpinner(new SpinnerNumberModel(0, 0, 200,
					1));
		}

		return jsMaxChoices;
	}

	public JSpinner getJsMinChoices() {
		if (jsMinChoices == null) {
			this.jsMinChoices = new JSpinner(new SpinnerNumberModel(0, 0, 200,
					1));
		}

		return jsMinChoices;
	}

	public JSpinner getJsNbChoices() {
		if (jsNbChoices == null) {
			this.jsNbChoices = new JSpinner(
					new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsNbChoices;
	}

	public JSpinner getJsNbReject() {
		if (jsNbReject == null) {
			this.jsNbReject = new JSpinner(new SpinnerNumberModel(0, 0, 200, 1));
		}

		return jsNbReject;
	}

	private void initializeView() {
	}
}
