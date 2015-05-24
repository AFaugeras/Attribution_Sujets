package controllers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import models.bean.Model;
import models.bean.Subject;
import views.subjects.SubjectPanel;
import views.subjects.SubjectsConfigurationPanel;

public class SubjectsConfigurationCtrl implements ActionListener {

	private Model model;
	private SubjectsConfigurationPanel view;
	private List<SubjectPanel> subjectsPanels;

	public SubjectsConfigurationCtrl(Model model,
			SubjectsConfigurationPanel view) {
		this.model = model;
		this.view = view;

		this.subjectsPanels = new ArrayList<>();

		initializeReactions();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (actionCommand
				.equals(SubjectsConfigurationPanel.JB_ADD_SUBJECT_ACTION)) {
			addNewSubject();
		} else if (actionCommand.equals(SubjectPanel.JB_DELETE_ACTION)) {
			deleteSubject((JButton) e.getSource());
		}
	}

	public void saveToModel() {
		if (checkIds()) {
			this.model.getSubjects().clear();

			for (SubjectPanel sp : this.subjectsPanels) {
				Subject tmp = new Subject(Integer.parseInt(sp.getJtfID()
						.getText()), sp.getJtfSubjectLabel().getText(),
						(int) sp.getJsMaxSize().getValue(), (int) sp
								.getJsMinSize().getValue(), (int) sp
								.getJsMultiplicity().getValue(), (int) sp
								.getJsMinCard().getValue(), (int) sp
								.getJsMaxCard().getValue());
				this.model.add(tmp);
			}
		} else {
			JOptionPane.showMessageDialog(null,
					"Les identifiants ne sont pas uniques", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean checkIds() {
		boolean ret = true;
		List<Integer> ids = new ArrayList<Integer>();

		Integer tmp;
		for (SubjectPanel sp : this.subjectsPanels) {
			tmp = Integer.parseInt(sp.getJtfID().getText());

			if (ids.contains(tmp)) {
				ret = false;
			}

			ids.add(tmp);
		}

		return ret;
	}

	private void initializeReactions() {
		view.getJbAddSubject().addActionListener(this);
		view.getJbImport().addActionListener(this);
	}

	private void addNewSubject() {
		this.subjectsPanels.add(createSubjectPanel(generateId()));
		repaintSubjects();
	}

	private void deleteSubject(JButton src) {
		SubjectPanel subjectPanel = (SubjectPanel) src.getParent().getParent();
		this.subjectsPanels.remove(subjectPanel);

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
		gbc.fill = GridBagConstraints.HORIZONTAL;

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

		int tmp;
		for (SubjectPanel sp : this.subjectsPanels) {
			tmp = Integer.parseInt(sp.getJtfID().getText());
			if (tmp > max) {
				max = tmp;
			}
		}

		return max + 1;
	}
}
