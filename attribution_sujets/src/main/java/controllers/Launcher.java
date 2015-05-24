package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;

import models.bean.GeneralConstraints;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import views.MainFrame;

public class Launcher implements ActionListener {

	private Model model;
	private MainFrame view;

	private GeneralConstraintPanelCtrl constraintsCtrl;
	private SubjectsConfigurationCtrl subjectsCtrl;

	public Launcher(Model model, MainFrame view) {
		this.model = model;
		this.view = view;

		initializeReactions();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (actionCommand.equals(MainFrame.JB_NEXT_ACTION)) {
			this.constraintsCtrl.saveToModel();
			this.subjectsCtrl.saveToModel();

			System.out.println(this.model);
		}
	}

	private void initializeReactions() {
		this.constraintsCtrl = new GeneralConstraintPanelCtrl(
				this.model.getConstraint(), this.view.getConstraintsPanel());
		this.subjectsCtrl = new SubjectsConfigurationCtrl(this.model,
				this.view.getSubjectsPanel());

		this.view.getJbNext().addActionListener(this);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		GeneralConstraints gc = new GeneralConstraints(0, 0, 0, 0);
		List<Subject> subjects = new ArrayList<Subject>();
		List<Person> persons = new ArrayList<Person>();
		Model mockModel = new Model(gc, persons, subjects);

		new Launcher(mockModel, new MainFrame());
	}
}