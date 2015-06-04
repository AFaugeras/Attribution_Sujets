package views.configuration.weights;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
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
		this.getVerticalScrollBar().setUnitIncrement(15);
	}
}
