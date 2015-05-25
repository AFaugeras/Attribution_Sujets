package controllers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import models.bean.Model;
import models.bean.Subject;
import models.parser.subject.ParserCsvSubject;
import models.utils.CSVXLSFileFilter;
import views.subjects.SubjectPanel;
import views.subjects.SubjectsConfigurationPanel;

public class SubjectsConfigurationCtrl implements ActionListener {

	private static final CSVXLSFileFilter CSV_XLS_FILE_FILTER = new CSVXLSFileFilter();

	private Model model;
	private SubjectsConfigurationPanel view;
	private List<SubjectPanel> subjectsPanels;

	public SubjectsConfigurationCtrl(Model model,
			SubjectsConfigurationPanel view) {
		this.model = model;
		this.view = view;

		this.subjectsPanels = new ArrayList<SubjectPanel>();

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
		} else if (actionCommand
				.equals(SubjectsConfigurationPanel.JB_IMPORT_ACTION)) {
			importSubjectsFromCVS();
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

	private void importSubjectsFromCVS() {
		final JFileChooser fc = new JFileChooser();

		fc.setFileFilter(CSV_XLS_FILE_FILTER);

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			ParserCsvSubject parser = new ParserCsvSubject();
			try {
				parser.ParseSubjectList(fc.getSelectedFile());

				for (Subject s : parser.getSubjectList()) {
					this.subjectsPanels
							.add(this.createSubjectPanelFromModel(s));
				}

				this.repaintSubjects();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Erreur à l'ouverture du fichier.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} else if (returnVal != JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(null, "Fichier incorrect.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private SubjectPanel createSubjectPanelFromModel(Subject model) {
		SubjectPanel ret = this.createSubjectPanel(model.getId());

		ret.getJtfSubjectLabel().setText(model.getLabel());
		ret.getJsMaxSize().setValue(model.getMaxSize());
		ret.getJsMinSize().setValue(model.getMinSize());
		ret.getJsMaxCard().setValue(model.getCardMax());
		ret.getJsMinCard().setValue(model.getCardMin());
		ret.getJsMultiplicity().setValue(model.getMultiple());

		return ret;
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
