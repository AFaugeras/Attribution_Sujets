package views.configuration.constraints.weights;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Panel de configuration des poids.
 */
public class WeightsConfigurationPanel extends JScrollPane {

	// Constantes :
	private static final long serialVersionUID = 1L;

	private static final String LABEL_TITLE_BORDER = "Poids";
	
	/**
	 * Conteneur.
	 */
	public JPanel jpContainer;

	/**
	 * Constructeur.
	 */
	public WeightsConfigurationPanel() {
		super();

		this.initializeView();
	}

	/**
	 * Accesseur de l'attribut jpContainer.
	 * 
	 * @return Le panel jpContainer.
	 */
	public JPanel getJpContainer() {
		if (this.jpContainer == null) {
			this.jpContainer = new JPanel(new GridBagLayout());
		}

		return this.jpContainer;
	}

	/**
	 * Cette méthode privée appelée par le constructeur initialiser la vue.
	 */
	private void initializeView() {
		this.setViewportView(this.getJpContainer());

		TitledBorder border = BorderFactory.createTitledBorder(LABEL_TITLE_BORDER);
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		border.setBorder(new LineBorder(Color.BLACK));
		this.setBorder(border);
		
		this.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.getVerticalScrollBar().setUnitIncrement(15);
	}
}
