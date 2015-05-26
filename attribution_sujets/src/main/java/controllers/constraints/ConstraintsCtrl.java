package controllers.constraints;

import models.bean.Constraints;
import views.constraints.BoundsConstraintsPanel;
import views.constraints.CampusConstraintsPanel;

public class ConstraintsCtrl {

	private Constraints model;
	private BoundsConstraintsPanel boundsView;
	private CampusConstraintsPanel campusView;

	public ConstraintsCtrl(Constraints model,
			BoundsConstraintsPanel boundsView, CampusConstraintsPanel campusView) {
		super();
		this.model = model;
		this.boundsView = boundsView;
		this.campusView = campusView;
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
	}
}
