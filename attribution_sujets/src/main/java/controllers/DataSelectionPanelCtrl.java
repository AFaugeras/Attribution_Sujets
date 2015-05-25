package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import views.dataselection.CSVXLSFileFilter;
import views.dataselection.DataSelectionPanel;

public class DataSelectionPanelCtrl implements ActionListener {

	private static final CSVXLSFileFilter CSV_XLS_FILE_FILTER = new CSVXLSFileFilter();

	private DataSelectionPanel view;

	public DataSelectionPanelCtrl(DataSelectionPanel view) {
		this.view = view;

		initializeReactions();
	}

	public File getCampusFile() {
		return new File(this.view.getJtfCampusFile().getText());
	}

	public File getPersonsFile() {
		return new File(this.view.getJtfPersonsFile().getText());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (actionCommand.equals(DataSelectionPanel.JB_CAMPUS_SELECTION)) {
			campusFileSelection();
		} else if (actionCommand.equals(DataSelectionPanel.JB_PERSON_SELECTION)) {
			personFileSelection();
		}
	}

	private void initializeReactions() {
		this.view.getJbCampusFile().addActionListener(this);
		this.view.getJbPersonsFile().addActionListener(this);
	}

	private void campusFileSelection() {
		final JFileChooser fc = new JFileChooser(new File(this.view
				.getJtfCampusFile().getText()));

		fc.setFileFilter(CSV_XLS_FILE_FILTER);

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.view.getJtfCampusFile().setText(
					fc.getSelectedFile().getAbsolutePath());
		}
	}

	private void personFileSelection() {
		final JFileChooser fc = new JFileChooser(new File(this.view
				.getJtfPersonsFile().getText()));
		fc.setFileFilter(CSV_XLS_FILE_FILTER);

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.view.getJtfPersonsFile().setText(
					fc.getSelectedFile().getAbsolutePath());
		}
	}
}
