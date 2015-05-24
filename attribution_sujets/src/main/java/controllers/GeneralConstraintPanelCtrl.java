package controllers;

import models.bean.GeneralConstraints;
import views.constraints.GeneralConstraintsPanel;

public class GeneralConstraintPanelCtrl {

	private GeneralConstraints model;
	private GeneralConstraintsPanel view;

	public GeneralConstraintPanelCtrl(GeneralConstraints model,
			GeneralConstraintsPanel view) {
		this.model = model;
		this.view = view;
	}

	public void saveToModel() {
		this.model.setNbMaxChoice((int) view.getJsMaxChoice().getValue());
		this.model.setNbMaxReject((int) view.getJsMaxReject().getValue());

		this.model.setNbChoice((int) view.getJsNbChoice().getValue());
		this.model.setNbReject((int) view.getJsNbReject().getValue());
	}
}
