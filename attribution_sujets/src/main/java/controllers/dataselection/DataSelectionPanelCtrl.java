
package controllers.dataselection;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import controllers.Utils;
import models.utils.CSVXLSFileFilter;
import views.configuration.dataselection.DataSelectionPanel;

public class DataSelectionPanelCtrl extends DropTargetAdapter implements ActionListener {

	private static final CSVXLSFileFilter CSV_XLS_FILE_FILTER = new CSVXLSFileFilter();

	private DataSelectionPanel view;
	
	private DropTarget dtJtfCampusFile;
	private DropTarget dtJtfPersonsFile;

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
		
		this.dtJtfCampusFile = new DropTarget(this.view.getJtfCampusFile(), DnDConstants.ACTION_MOVE, this);
		this.dtJtfPersonsFile = new DropTarget(this.view.getJtfPersonsFile(), DnDConstants.ACTION_MOVE, this);
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
		final JFileChooser fc = new JFileChooser(new File(this.view.getJtfPersonsFile().getText()));
		fc.setFileFilter(CSV_XLS_FILE_FILTER);

		int returnVal = fc.showOpenDialog(null);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			this.view.getJtfPersonsFile().setText(fc.getSelectedFile().getAbsolutePath());
		}
	}
	
	@Override
	public void drop(DropTargetDropEvent dtde) {
		Object source = dtde.getSource();
		
		if(source == this.dtJtfCampusFile) {
			System.out.println("campus drop");
			this.dropOnJtfCampusFile(dtde);
		}
		else if(source == this.dtJtfPersonsFile) {
			System.out.println("person drop");
			this.dropOnJtfPersonsFile(dtde);
		}
	}

	private void dropOnJtfCampusFile(DropTargetDropEvent dtde) {
		dtde.acceptDrop(DnDConstants.ACTION_MOVE);
		
		File input = Utils.getFileFromTransferable(dtde.getTransferable());
		
		if(input != null) {
			this.view.getJtfCampusFile().setText(input.getAbsolutePath());
			dtde.dropComplete(true);
		}
		else {
			dtde.dropComplete(false);
		}
	}

	private void dropOnJtfPersonsFile(DropTargetDropEvent dtde) {
		dtde.acceptDrop(DnDConstants.ACTION_MOVE);
	
		File input = Utils.getFileFromTransferable(dtde.getTransferable());
		
		if(input != null) {
			this.view.getJtfPersonsFile().setText(input.getAbsolutePath());
			dtde.dropComplete(true);
		}
		else {
			dtde.dropComplete(false);
		}
	}
}
