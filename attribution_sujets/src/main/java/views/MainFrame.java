package views;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import views.configuration.ConfigurationPanel;
import views.result.ResultPanel;

/**
 * Fenêtre principale de l'application.
 *
 */
public class MainFrame extends JFrame {

	// Constantes :
	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_NAME = "Attribution des sujets";
	private static final String CONFIGURATION_PANEL = "CONFIGURATION_PANEL";
	private static final String RESULT_PANEL = "RESULT_PANEL";
	private static final Dimension PREFERED_SIZE = new Dimension(1200, 650);

	/**
	 * Conteneur principal.
	 */
	private JPanel cardPanel;
	
	/**
	 * Panel de configuration.
	 */
	private ConfigurationPanel configurationPanel;
	
	/**
	 * Panel de résultat.
	 */
	private ResultPanel resultPanel;

	/**
	 * Constructeur.
	 */
	public MainFrame() {
		super(APPLICATION_NAME);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.initializeView();

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Accesseur de l'attribut configurationPanel.
	 * 
	 * @return Le panel configurationPanel.
	 */
	public ConfigurationPanel getConfigurationPanel() {
		if (this.configurationPanel == null) {
			this.configurationPanel = new ConfigurationPanel();
		}

		return this.configurationPanel;
	}


	/**
	 * Accesseur de l'attribut resultPanel.
	 * 
	 * @return Le panel resultPanel.
	 */
	public ResultPanel getResultPanel() {
		if (this.resultPanel == null) {
			this.resultPanel = new ResultPanel();
		}

		return this.resultPanel;
	}

	@Override
	public Dimension getPreferredSize() {
		return PREFERED_SIZE;
	}

	/**
	 * Affiche le panel de configuration.
	 */
	public void showConfigurationPanel() {
		((CardLayout) this.cardPanel.getLayout()).show(this.cardPanel,
				CONFIGURATION_PANEL);
	}

	/**
	 * Affiche le panel de résultat.
	 */
	public void showResultPanel() {
		((CardLayout) this.cardPanel.getLayout()).show(this.cardPanel,
				RESULT_PANEL);
	}

	/**
	 * Méthode privée appelée par le constructeur pour initialiser la vue.
	 */
	private void initializeView() {
		this.cardPanel = new JPanel(new CardLayout());

		this.cardPanel.add(this.getConfigurationPanel(), CONFIGURATION_PANEL);
		this.cardPanel.add(this.getResultPanel(), RESULT_PANEL);

		this.getContentPane().add(cardPanel);
	}
}