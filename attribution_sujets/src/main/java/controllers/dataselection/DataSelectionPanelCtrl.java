package controllers.dataselection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import models.bean.Model;
import models.utils.CSVXLSFileFilter;
import views.configuration.dataselection.DataSelectionPanel;

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
			this.campusFileSelection();
		} else if (actionCommand.equals(DataSelectionPanel.JB_PERSON_SELECTION)) {
			this.personFileSelection();
		}
	}
	
	public boolean isCampusFileExists() {
		return this.getCampusFile().exists();
	}
	
	public boolean isPersonFileExists() {
		return this.getPersonsFile().exists();
	}

	private void initializeReactions() {
		this.view.getJbCampusFile().addActionListener(this);
		this.view.getJbPersonsFile().addActionListener(this);
	}

	private void campusFileSelection() {
		final JFileChooser fc = new JFileChooser(new File(this.view
				.getJtfCampusFile().getText()));
		fc.setCurrentDirectory(new File(Model.getFileChoserPath()));
		fc.setFileFilter(CSV_XLS_FILE_FILTER);

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Model.setFileChoserPath(fc.getSelectedFile().getParent());
			this.view.getJtfCampusFile().setText(
					fc.getSelectedFile().getAbsolutePath());
		}
	}

	private void personFileSelection() {
		final JFileChooser fc = new JFileChooser(new File(this.view
				.getJtfPersonsFile().getText()));
		fc.setCurrentDirectory(new File(Model.getFileChoserPath()));
		fc.setFileFilter(CSV_XLS_FILE_FILTER);

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Model.setFileChoserPath(fc.getSelectedFile().getParent());
			this.view.getJtfPersonsFile().setText(
					fc.getSelectedFile().getAbsolutePath());
		}
	}
}