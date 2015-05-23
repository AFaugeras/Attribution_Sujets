package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import models.bean.Model;

public class SubjectPanelCtrl implements ActionListener, Observer {

	private Model model;

	public SubjectPanelCtrl(Model model) {
		this.model = model;
	}

	@Override
	public void update(Observable o, Object arg) {
		String message = (String) arg;

		if (message.equals(Model.SUBJECTS_UPDATE_MESSAGE)) {
			updateSubjectsPanel();
		}
	}

	private void updateSubjectsPanel() {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
}
