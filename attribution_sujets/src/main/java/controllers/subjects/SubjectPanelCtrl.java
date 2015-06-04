package controllers.subjects;

import java.awt.Color;

import javax.swing.JSpinner;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import views.configuration.subjects.SubjectPanel;

public class SubjectPanelCtrl implements ChangeListener {

	private static final Color ALERT_BACKGROUND_COLOR = new Color(238, 185, 168);
	private static final Color DEFAULT_BACKGROUND_COLOR = UIManager.getLookAndFeel().getDefaults().getColor("EditorPane.background");

	private SubjectPanel view;

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

	public boolean isError() {
		boolean ret = true;

		int minSize = (int) this.view.getJsMinSize().getValue();
		int maxSize = (int) this.view.getJsMaxSize().getValue();
		int minCard = (int) this.view.getJsMinCard().getValue();
		int maxCard = (int) this.view.getJsMaxCard().getValue();

		if (minSize <= maxSize && minCard <= maxCard) {
			ret = false;
		}

		return ret;
	}

	private void initializeReactions() {
		this.view.getJsMinSize().addChangeListener(this);
		this.view.getJsMaxSize().addChangeListener(this);
		this.view.getJsMinCard().addChangeListener(this);
		this.view.getJsMaxCard().addChangeListener(this);
	}
}
