package controllers.result;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.bean.Model;
import models.bean.Person;
import views.result.ResultStatsDetail;

public class ResultStatPanelCtrl {

	private Model model;
	private ArrayList<Integer> nbPerChoiceOrder;
	private JPanel jpStats;
	private GridBagConstraints gbc;
	
	public ResultStatPanelCtrl() {
		this.nbPerChoiceOrder = new ArrayList<Integer>();
		this.jpStats = new JPanel(new GridBagLayout());
	}
	
	public void setModel(Model model) {
		this.model = model;
		
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NORTHWEST;
		
		initializeData();
	}

	private void initializeData() {
		// Initialize the stats panel
		for (int i = 0 ; i < model.getConstraint().getNbMaxChoice() ; i++) {
			nbPerChoiceOrder.add(0);
		}
		for (Person person : this.model.getPersons()) {
			
			int choiceNb = nbPerChoiceOrder.size() - 1;
			for (int j = 0 ; j < person.getChoices().size() && j < nbPerChoiceOrder.size() ; j++) {
				if (person.getChoices().get(j).equals(person.getAssigned())) {
					choiceNb = j;
					break;
				}
			}
			
			nbPerChoiceOrder.set(choiceNb, nbPerChoiceOrder.get(choiceNb) + 1);
		}
		this.jpStats.add(new ResultStatsDetail(this.nbPerChoiceOrder, this.model), gbc);
	}

	public Component getJpStats() {
		return this.jpStats;
	}
}