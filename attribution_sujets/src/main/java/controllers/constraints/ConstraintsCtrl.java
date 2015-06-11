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
import views.configuration.constraints.CampusConstraintsPanel;
import views.configuration.constraints.SolverConfigurationPanel;
import views.configuration.constraints.weights.WeightPanel;
import views.configuration.constraints.weights.WeightsConfigurationPanel;

/**
 * Contrôleur des panels de contraintes (package view.constraints.*).
 *
 */
public class ConstraintsCtrl implements ChangeListener {

	/**
	 * Le modèle.
	 */
	private Constraints model;

	/**
	 * Le panel de configuration du solver.
	 */
	private SolverConfigurationPanel solverParametersView;

	/**
	 * Le panel de configuration campus.
	 */
	private CampusConstraintsPanel campusView;

	/**
	 * Le panel de management des poids.
	 */
	private WeightsConfigurationPanel weightsView;

	/**
	 * Liste de WeightPanel.
	 */
	private List<WeightPanel> weightPanels;

	/**
	 * Variable d'état indiquant la dernière valeur enregistré du nombre de
	 * choix.
	 */
	private int maxChoiceValue;

	/**
	 * Constructeur.
	 * 
	 * @param model Le modèle.
	 * @param solverParametersView Le panel de de configuration du solveur.
	 * @param campusView Le panel de configuration campus.
	 * @param weightsView Le panel de management des poids.
	 */
	public ConstraintsCtrl(Constraints model,
			SolverConfigurationPanel solverParametersView,
			CampusConstraintsPanel campusView,
			WeightsConfigurationPanel weightsView) {
		this.model = model;
		this.solverParametersView = solverParametersView;
		this.campusView = campusView;
		this.weightsView = weightsView;

		this.weightPanels = new ArrayList<WeightPanel>();

		this.maxChoiceValue = (int) this.solverParametersView.getJsMaxChoice().getValue();

		this.addNewWeightPanel();

		this.initializeReactions();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSpinner src = (JSpinner) e.getSource();

		if (src == this.campusView.getJsNbChoice()) {
			int nbChoice = (int) src.getValue();
			int nbMaxChoice = (int) this.solverParametersView.getJsMaxChoice().getValue();

			((SpinnerNumberModel) this.solverParametersView.getJsMaxChoice().getModel()).setMaximum(nbChoice);

			if (nbMaxChoice > nbChoice) {
				this.solverParametersView.getJsMaxChoice().setValue(nbChoice);
			}

		} else if (src == this.campusView.getJsNbReject()) {
			int nbReject = (int) src.getValue();
			int nbMaxReject = (int) this.solverParametersView.getJsMaxReject().getValue();

			((SpinnerNumberModel) this.solverParametersView.getJsMaxReject().getModel()).setMaximum(nbReject);

			if (nbMaxReject > nbReject) {
				this.solverParametersView.getJsMaxReject().setValue(nbReject);
			}
		} else if (src == this.solverParametersView.getJsMaxChoice()) {
			manageWeights();
		}
	}

	/**
	 * Méthode de réaction. Elle est appellé quand l'utilisateur modifie le
	 * nombre de choix. Ajoute ou supprime des WeightPanel au besoins.
	 */
	private void manageWeights() {
		int tmp = (int) this.solverParametersView.getJsMaxChoice().getValue()
				- this.maxChoiceValue;

		this.maxChoiceValue = (int) this.solverParametersView.getJsMaxChoice()
				.getValue();

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

	/**
	 * Cette méthode sauvegarde les valeurs de la vue dans le modèle.
	 */
	public void saveToModel() {
		this.model.setNbMaxChoice((int) this.solverParametersView.getJsMaxChoice().getValue());
		this.model.setNbMaxReject((int) this.solverParametersView.getJsMaxReject().getValue());
		this.model.setNbMinSubjectsAssigned((int) this.solverParametersView.getJsMinAssigned().getValue());
		this.model.setMultiplicity((int) this.solverParametersView.getJsMultiplicity().getValue());

		this.model.setNbChoice((int) this.campusView.getJsNbChoice().getValue());
		this.model.setNbReject((int) this.campusView.getJsNbReject().getValue());

		this.model.getWeights().clear();

		for (WeightPanel wp : weightPanels) {
			this.model.getWeights().add(((Double) wp.getJsValue().getValue()).longValue());
		}
	}

	/**
	 * Méthode privée appellée par le constructeur pour initialiser les
	 * réactions.
	 */
	private void initializeReactions() {
		this.campusView.getJsNbChoice().addChangeListener(this);
		this.campusView.getJsNbReject().addChangeListener(this);
		this.solverParametersView.getJsMaxChoice().addChangeListener(this);
	}

	/**
	 * Ajoute un nouveau WeightPanel.
	 */
	private void addNewWeightPanel() {
		this.weightPanels.add(new WeightPanel(this.weightPanels.size() + 1,
				this.generateWeight()));
		this.repaintWeights();
	}

	/**
	 * Supprime le dernier WeightPanel.
	 */
	private void removeLastWeightPanel() {
		this.weightPanels.remove(this.weightPanels.size() - 1);
		this.repaintWeights();
	}

	/**
	 * Génére un poids.
	 * 
	 * @return Le poids.
	 */
	private long generateWeight() {
		long ret;

		if (this.weightPanels.size() == 0) {
			ret = 1;
		} else {
			ret = (long) Math.exp(Math.pow(2, this.weightPanels.size()));
		}

		return ret;
	}

	/**
	 * Rafraichie la liste des poids.
	 */
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

	/**
	 * Rafraichie la vue.
	 */
	private void repaintView() {
		this.weightsView.invalidate();
		this.weightsView.validate();
		this.weightsView.repaint();
	}
}
