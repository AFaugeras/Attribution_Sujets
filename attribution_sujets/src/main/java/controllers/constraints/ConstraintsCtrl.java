package controllers.constraints;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.bean.Constraints;
import views.configuration.constraints.BoundsConstraintsPanel;
import views.configuration.constraints.CampusConstraintsPanel;
import views.configuration.weights.WeightPanel;
import views.configuration.weights.WeightsConfigurationPanel;

public class ConstraintsCtrl implements ChangeListener {

	private Constraints model;

	private BoundsConstraintsPanel boundsView;
	private CampusConstraintsPanel campusView;
	private WeightsConfigurationPanel weightsView;

	private List<WeightPanel> weightPanels;

	private int maxChoiceValue;

	public ConstraintsCtrl(Constraints model,
			BoundsConstraintsPanel boundsView,
			CampusConstraintsPanel campusView,
			WeightsConfigurationPanel weightsView) {
		this.model = model;
		this.boundsView = boundsView;
		this.campusView = campusView;
		this.weightsView = weightsView;

		this.weightPanels = new ArrayList<WeightPanel>();

		this.maxChoiceValue = (int) this.boundsView.getJsMaxChoice().getValue();

		this.addNewWeightPanel();
		
		this.initializeReactions();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner src = (JSpinner) e.getSource();
		
		if(src == this.campusView.getJsNbChoice()){
			((SpinnerNumberModel) this.boundsView.getJsMaxChoice().getModel()).setMaximum((int) src.getValue());
		}
		else if(src == this.campusView.getJsNbReject()){
			((SpinnerNumberModel) this.boundsView.getJsMaxReject().getModel()).setMaximum((int) src.getValue());
		}
		else if (src == this.boundsView.getJsMaxChoice()) {
			manageWeights();
		}
	}

	private void manageWeights() {
		int tmp = (int) this.boundsView.getJsMaxChoice().getValue() - this.maxChoiceValue;

		this.maxChoiceValue = (int) this.boundsView.getJsMaxChoice().getValue();

		if (tmp > 0) {
			for (int i = 0; i < tmp; i++) {
				this.addNewWeightPanel();
			}
		} else if (tmp < 0) {
			int breakValue = this.weightPanels.size() + tmp;
			for (int i = this.weightPanels.size() - 1; i >= breakValue; i--) {
				this.removeLastWeightPanel();
			}
		}
	}

	public void saveToModel() {
		this.model.setNbMaxChoice((int) this.boundsView.getJsMaxChoice().getValue());
		this.model.setNbMaxReject((int) this.boundsView.getJsMaxReject().getValue());

		this.model.setNbChoice((int) this.campusView.getJsNbChoice().getValue());
		this.model.setNbReject((int) this.campusView.getJsNbReject().getValue());
		
		this.model.setMultiplicity(Integer.parseInt(this.boundsView.getJtfMultiplicity().getText()));

		this.model.getWeights().clear();
		
		for (WeightPanel wp : weightPanels) {
			this.model.getWeights().add(((Double) wp.getJsValue().getValue()).longValue());
		}
	}

	private void initializeReactions() {
		this.campusView.getJsNbChoice().addChangeListener(this);
		this.campusView.getJsNbReject().addChangeListener(this);
		this.boundsView.getJsMaxChoice().addChangeListener(this);
	}

	private void addNewWeightPanel() {
		this.weightPanels.add(new WeightPanel(this.weightPanels.size() + 1, this
				.generateWeight()));
		this.repaintWeights();
	}

	private void removeLastWeightPanel() {
		this.weightPanels.remove(this.weightPanels.size() - 1);
		this.repaintWeights();
	}

	private long generateWeight() {
		long ret;
		
		if(this.weightPanels.size() == 0) {
			ret = 1;
		} else {
			ret = (long) Math.exp(Math.pow(2, this.weightPanels.size()));
		}
		
		return ret;
	}

	private void repaintWeights() {
		JPanel container = this.weightsView.getJpContainer();

		container.removeAll();
		container.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;
		gbc.insets.bottom = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		for (int i = 0; i < this.weightPanels.size(); i++) {
			container.add(this.weightPanels.get(i), gbc);
			gbc.gridy++;
		}

		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets.bottom = 0;
		container.add(new JPanel(), gbc);

		this.repaintView();
	}

	private void repaintView() {
		this.weightsView.invalidate();
		this.weightsView.validate();
		this.weightsView.repaint();
	}
}
