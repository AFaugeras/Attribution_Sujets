package controllers.subjects;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import views.configuration.subjects.SubjectPanel;

public class SubjectPanelCtrl implements ChangeListener {

	private SubjectPanel view;
	
	
	public SubjectPanelCtrl(SubjectPanel view) {
		super();
		this.view = view;
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		
	}
}
