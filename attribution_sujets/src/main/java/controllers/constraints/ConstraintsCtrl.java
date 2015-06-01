package controllers.constraints;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import models.bean.Constraints;
import views.configuration.constraints.BoundsConstraintsPanel;
import views.configuration.constraints.CampusConstraintsPanel;
import views.configuration.weights.WeightsConfigurationPanel;
import views.configuration.weights.WeightsConfigurationPanel.WeightLine;

public class ConstraintsCtrl implements ChangeListener {

	private Constraints model;

	private BoundsConstraintsPanel boundsView;
	private CampusConstraintsPanel campusView;
	private WeightsConfigurationPanel weightsView;

	private List<WeightLine> weightPanels;

	private int maxChoiceValue;

	public ConstraintsCtrl(Constraints model,
			BoundsConstraintsPanel boundsView,
			CampusConstraintsPanel campusView,
			WeightsConfigurationPanel weightsView) {
		this.model = model;
		this.boundsView = boundsView;
		this.campusView = campusView;
		this.weightsView = weightsView;

		this.weightPanels = new ArrayList<WeightLine>();

		this.maxChoiceValue = (int) this.boundsView.getJsMaxChoice().getValue();
		
		this.initializeReactions();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if (e.getSource() == this.boundsView.getJsMaxChoice()) {
			int tmp = (int) this.boundsView.getJsMaxChoice()
					.getValue() - this.maxChoiceValue;
			
			this.maxChoiceValue = (int) this.boundsView.getJsMaxChoice().getValue();

			if (tmp > 0) {
				for(int i = 0; i < tmp; i++) {
					this.addNewWeightPanel();
				}
			} else if (tmp < 0) {
				int breakValue = this.weightPanels.size() + tmp;
				for(int i = this.weightPanels.size() - 1; i >= breakValue; i--){
					this.removeLastWeightPanel();
				}
			}
		}
	}

	public void saveToModel() {
		this.model.setNbMaxChoice((int) this.boundsView.getJsMaxChoice()
				.getValue());
		this.model.setNbMaxReject((int) this.boundsView.getJsMaxReject()
				.getValue());

		this.model
				.setNbChoice((int) this.campusView.getJsNbChoice().getValue());
		this.model
				.setNbReject((int) this.campusView.getJsNbReject().getValue());

		this.model.getWeights().clear();
		for (WeightLine wp : weightPanels) {
			this.model.getWeights().add((Integer) wp.getJsValue().getValue());
		}
	}

	private void initializeReactions() {
		this.boundsView.getJsMaxChoice().addChangeListener(this);
	}

	private void clearWeights() {
		this.weightPanels.clear();
		this.repaintWeights();
	}

	private void addNewWeightPanel() {
		this.weightPanels.add(new WeightLine(this.weightPanels.size() + 1));
		this.repaintWeights();
	}

	private void removeLastWeightPanel() {
		this.weightPanels.remove(this.weightPanels.size() - 1);
		this.repaintWeights();
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
