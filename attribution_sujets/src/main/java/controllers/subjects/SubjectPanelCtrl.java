package controllers.subjects;

import java.awt.Color;

import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import views.configuration.subjects.SubjectPanel;

/**
 * Une instance de cette classe permet de contrôler un SubjectPanel.
 */
public class SubjectPanelCtrl implements ChangeListener {

	// Constantes :
	private static final Color ALERT_BACKGROUND_COLOR = new Color(238, 185, 168);
	private static final Color DEFAULT_BACKGROUND_COLOR = UIManager.getLookAndFeel().getDefaults().getColor("EditorPane.background");

	/**
	 * La vue.
	 */
	private SubjectPanel view;

	/**
	 * Constructeur.
	 * 
	 * @param view La vue.
	 */
	public SubjectPanelCtrl(SubjectPanel view) {
		super();

		this.view = view;

		this.initializeReactions();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner src = (JSpinner) e.getSource();

		if (src == this.view.getJsMinSize()) {
			this.checkMinSize(src);
		} else if (src == this.view.getJsMaxSize()) {
			this.checkMaxSize(src);
		} else if (src == this.view.getJsMinCard()) {
			this.checkMinCard(src);
		} else if (src == this.view.getJsMaxCard()) {
			this.checkMaxCard(src);
		}
	}

	/**
	 * Vérifie que la valeur du Spinner de l'effectif min soit bien inférieure ou égale à la valeur du spinner de l'effectif max.
	 * Si elle est supérieure on active une alerte visuelle.
	 * 
	 * @param jsMinSize Je Spinner de l'effectif min.
	 */
	private void checkMinSize(JSpinner jsMinSize) {
		int min = (int) jsMinSize.getValue();
		int max = (int) this.view.getJsMaxSize().getValue();

		if (min > max) {
			jsMinSize.getEditor().getComponent(0).setBackground(ALERT_BACKGROUND_COLOR);
			this.view.getJsMaxSize().getEditor().getComponent(0).setBackground(ALERT_BACKGROUND_COLOR);
		} else {
			jsMinSize.getEditor().getComponent(0).setBackground(DEFAULT_BACKGROUND_COLOR);
			this.view.getJsMaxSize().getEditor().getComponent(0).setBackground(DEFAULT_BACKGROUND_COLOR);
		}
	}

	/**
	 * Vérifie que la valeur du Spinner de l'effectif max soit bien supérieure ou égale à la valeur du spinner de l'effectif min.
	 * Si elle est inférieure on active une alerte visuelle.
	 * @param jsMaxSize Le Spinner de l'effectif max.
	 */
	private void checkMaxSize(JSpinner jsMaxSize) {
		int max = (int) jsMaxSize.getValue();
		int min = (int) this.view.getJsMinSize().getValue();

		if (min > max) {
			jsMaxSize.getEditor().getComponent(0).setBackground(ALERT_BACKGROUND_COLOR);
			this.view.getJsMinSize().getEditor().getComponent(0).setBackground(ALERT_BACKGROUND_COLOR);
		} else {
			jsMaxSize.getEditor().getComponent(0).setBackground(DEFAULT_BACKGROUND_COLOR);
			this.view.getJsMinSize().getEditor().getComponent(0).setBackground(DEFAULT_BACKGROUND_COLOR);
		}
	}

	/**
	 * Vérifie que la valeur du spinner de la cardinalité max soit bien inférieure ou égale à la valeur du spinner de la cardinalité min.
	 * Si elle est supérieure on active une alerte visuelle.
	 * @param jsMinCard Le Spinner de la cardinalité min.
	 */
	private void checkMinCard(JSpinner jsMinCard) {
		int min = (int) jsMinCard.getValue();
		int max = (int) this.view.getJsMaxCard().getValue();

		if (min > max) {
			jsMinCard.getEditor().getComponent(0).setBackground(ALERT_BACKGROUND_COLOR);
			this.view.getJsMaxCard().getEditor().getComponent(0).setBackground(ALERT_BACKGROUND_COLOR);
		} else {
			jsMinCard.getEditor().getComponent(0).setBackground(DEFAULT_BACKGROUND_COLOR);
			this.view.getJsMaxCard().getEditor().getComponent(0).setBackground(DEFAULT_BACKGROUND_COLOR);
		}
	}

	/**
	 * Vérifie que la valeur du Spinner de la cardinalité max soit bien supérieure ou égale à la valeur du spinner de l'effectif min.
	 * Si elle est inférieure on active une alerte visuelle.
	 * @param jsMaxCard
	 */
	private void checkMaxCard(JSpinner jsMaxCard){
		int max = (int) jsMaxCard.getValue();
		int min = (int) this.view.getJsMinCard().getValue();

		if (min > max) {
			jsMaxCard.getEditor().getComponent(0).setBackground(ALERT_BACKGROUND_COLOR);
			this.view.getJsMinCard().getEditor().getComponent(0).setBackground(ALERT_BACKGROUND_COLOR);
		} else {
			jsMaxCard.getEditor().getComponent(0).setBackground(DEFAULT_BACKGROUND_COLOR);
			this.view.getJsMinCard().getEditor().getComponent(0).setBackground(DEFAULT_BACKGROUND_COLOR);
		}
	}

	/**
	 * Méthode privée appellée par le constructeur pour initialiser les réactions.
	 */
	private void initializeReactions() {
		this.view.getJsMinSize().addChangeListener(this);
		this.view.getJsMaxSize().addChangeListener(this);
		this.view.getJsMinCard().addChangeListener(this);
		this.view.getJsMaxCard().addChangeListener(this);
	}

//	public boolean isError() {
//		boolean ret = true;
//
//		int minSize = (int) this.view.getJsMinSize().getValue();
//		int maxSize = (int) this.view.getJsMaxSize().getValue();
//		int minCard = (int) this.view.getJsMinCard().getValue();
//		int maxCard = (int) this.view.getJsMaxCard().getValue();
//
//		if (minSize <= maxSize && minCard <= maxCard) {
//			ret = false;
//		}
//
//		return ret;
//	}
}
