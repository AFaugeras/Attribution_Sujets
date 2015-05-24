package controllers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import models.bean.Model;
import models.bean.Subject;
import views.subjects.SubjectPanel;
import views.subjects.SubjectsConfigurationPanel;

public class SubjectsConfigurationCtrl implements ActionListener, Observer {

	private Model model;
	private SubjectsConfigurationPanel view;
	private List<SubjectPanel> subjectsPanels;

	public SubjectsConfigurationCtrl(Model model, SubjectsConfigurationPanel view) {
		this.model = model;
		this.view = view;

		this.subjectsPanels = new ArrayList<>();

		initializeReactions();
	}

	@Override
	public void update(Observable o, Object arg) {
		String message = (String) arg;

		if (message.equals(Model.SUBJECTS_ADDED_MESSAGE)) {
			repaintSubjects();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		System.out.println(actionCommand);

		if (actionCommand.equals(SubjectsConfigurationPanel.JB_ADD_SUBJECT_ACTION)) {
			addNewSubject();
		} else if (actionCommand.equals(SubjectPanel.JB_DELETE_ACTION)) {
			deleteSubject((JButton) e.getSource());
		}
	}

	private void initializeReactions() {
		model.addObserver(this);

		view.getJbAddSubject().addActionListener(this);
		view.getJbImport().addActionListener(this);
		view.getJbNext().addActionListener(this);
	}

	private void addNewSubject() {
		int id = generateId();

		Subject newSubject = new Subject();
		newSubject.setId(id);

		this.subjectsPanels.add(createSubjectPanel(id));
		this.model.add(newSubject);
	}

	private void deleteSubject(JButton src) {
		SubjectPanel subjectPanel = (SubjectPanel) src.getParent().getParent();

		this.subjectsPanels.remove(subjectPanel);

		for (Iterator<Subject> iterator = model.getSubjects().iterator(); iterator
				.hasNext();) {
			Subject subject = iterator.next();

			if (subject.getId() == Integer.parseInt(subjectPanel.getJtfID()
					.getText())) {
				iterator.remove();
			}
		}

		this.repaintSubjects();
	}

	private void repaintSubjects() {
		JPanel container = view.getJpSubjects();

		container.removeAll();
		container.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 0;

		for (int i = 0; i < this.subjectsPanels.size(); i++) {
			container.add(this.subjectsPanels.get(i), gbc);
			gbc.gridy++;
		}

		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		container.add(new JPanel(), gbc);

		repaintView();
	}

	private SubjectPanel createSubjectPanel(int id) {
		SubjectPanel subjectPanel = new SubjectPanel();

		subjectPanel.getJtfID().setText(String.valueOf(id));

		subjectPanel.getJbDelete().addActionListener(this);
		subjectPanel.getJbDelete().setActionCommand(
				SubjectPanel.JB_DELETE_ACTION);

		return subjectPanel;
	}

	private void repaintView() {
		view.invalidate();
		view.validate();
		view.repaint();
	}

	private int generateId() {
		int max = 0;

		for (Subject subject : this.model.getSubjects()) {
			if (subject.getId() > max) {
				max = subject.getId();
			}
		}

		return max + 1;
	}
}
