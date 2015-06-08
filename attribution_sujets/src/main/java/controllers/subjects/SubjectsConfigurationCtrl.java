package controllers.subjects;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import models.bean.Model;
import models.bean.Subject;
import models.exception.fileformatexception.FileException;
import models.parser.subject.ParserCsvSubject;
import models.utils.CSVXLSFileFilter;
import views.configuration.subjects.SubjectPanel;
import views.configuration.subjects.SubjectsConfigurationPanel;
import controllers.Utils;

public class SubjectsConfigurationCtrl extends DropTargetAdapter implements ActionListener {

	private static final CSVXLSFileFilter CSV_XLS_FILE_FILTER = new CSVXLSFileFilter();

	private List<Subject> model;	
	private SubjectsConfigurationPanel view;
	private List<SubjectPanel> subjectsPanels;
	
	public SubjectsConfigurationCtrl(List<Subject> model, SubjectsConfigurationPanel view) {
		this.model = model;
		this.view = view;

		this.subjectsPanels = new ArrayList<SubjectPanel>();

		initializeReactions();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (actionCommand.equals(SubjectsConfigurationPanel.JB_ADD_SUBJECT_ACTION)) {
			addNewSubject();
		}
		else if (actionCommand.equals(SubjectPanel.JB_DELETE_ACTION)) {
			deleteSubject((JButton) e.getSource());
		}
		else if (actionCommand.equals(SubjectsConfigurationPanel.JB_IMPORT_ACTION)) {
			importSubjectsFromCVS();
		}
		else if (actionCommand.equals(SubjectsConfigurationPanel.JB_EXPORT_ACTION)) {
			exportSubjectToCVS();
		}
	}

	public void saveToModel() {
		this.model.clear();

		for (SubjectPanel sp : this.subjectsPanels) {
			int id = Integer.parseInt(sp.getJtfID().getText());
			String label = sp.getJtfSubjectLabel().getText();
			int minSize = (int) sp.getJsMinSize().getValue();
			int maxSize = (int) sp.getJsMaxSize().getValue();
			int minCard = (int) sp.getJsMinCard().getValue();
			int maxCard = (int) sp.getJsMaxCard().getValue();
			
			Subject tmp = new Subject(id, label, minSize, maxSize, minCard, maxCard);
			
			this.model.add(tmp);
		}
	}

	public boolean isIdsUnique() {
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
		this.view.getJbAddSubject().addActionListener(this);
		this.view.getJbImport().addActionListener(this);
		this.view.getJbExport().addActionListener(this);
		
		new DropTarget(this.view, DnDConstants.ACTION_MOVE, this);
	}

	private void addNewSubject() {
		this.subjectsPanels.add(createSubjectPanel(generateId()));
		this.repaintSubjects();
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
			importSubjectFromFile(fc.getSelectedFile());

		} else if (returnVal != JFileChooser.CANCEL_OPTION) {
			Utils.displayErrorMessage("Fichier incorrect.", SwingUtilities.getWindowAncestor(this.view));
		}
	}

	private void importSubjectFromFile(final File file) {
		ParserCsvSubject parser = new ParserCsvSubject();
		try {
			parser.ParseSubjectList(file);

			this.subjectsPanels.clear();
			for (Subject s : parser.getSubjectList()) {
				this.subjectsPanels.add(this.createSubjectPanelFromModel(s));
			}

			this.repaintSubjects();
			
		} catch (FileException e) {
			Utils.displayErrorMessage(e.getMessage(), SwingUtilities.getWindowAncestor(this.view));
		}	
	}

	private void exportSubjectToCVS() {
		final JFileChooser fc = new JFileChooser();
		fc.setFileFilter(CSV_XLS_FILE_FILTER);
		
		int returnVal = fc.showSaveDialog(SwingUtilities.getWindowAncestor(this.view));
		System.out.println(returnVal);
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			String path = fc.getSelectedFile().getName();
			
			if(path.contains(".")){
				path = path.substring(0, path.indexOf("."));
			}
			
			// TODO : Appeller la méthode de sauvegarde (voir avec Cédric).
			if(!path.equals("")) {
				System.out.println("OK");				
				path = path + ".csv";
				
				try {
					Subject.save(this.model, new File(path));
				} catch (FileException e) {
					Utils.displayErrorMessage(e.getMessage(), SwingUtilities.getWindowAncestor(this.view));
				}
			}
		}
	}

	private SubjectPanel createSubjectPanelFromModel(Subject model) {
		SubjectPanel ret = this.createSubjectPanel(model.getId());

		ret.getJtfSubjectLabel().setText(model.getLabel());
		ret.getJsMaxSize().setValue(model.getMaxSize());
		ret.getJsMinSize().setValue(model.getMinSize());
		ret.getJsMaxCard().setValue(model.getMaxCard());
		ret.getJsMinCard().setValue(model.getMinCard());

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
		subjectPanel.getJbDelete().setActionCommand(SubjectPanel.JB_DELETE_ACTION);
		
		new SubjectPanelCtrl(subjectPanel);

		return subjectPanel;
	}

	private void repaintView() {
		this.view.invalidate();
		this.view.validate();
		this.view.repaint();
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

	@Override
	public void drop(DropTargetDropEvent dtde) {
		dtde.acceptDrop(DnDConstants.ACTION_COPY);
		
		File input = Utils.getFileFromTransferable(dtde.getTransferable());
		
		if(input != null) {
			importSubjectFromFile(input);
		}
		
		dtde.dropComplete(true);
	}
}
