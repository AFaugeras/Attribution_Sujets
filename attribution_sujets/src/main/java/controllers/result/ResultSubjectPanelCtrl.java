package controllers.result;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import views.result.ResultSubjectDetail;

/**
 * Controleur du panel du détail des sujets des sujets
 *
 */
public class ResultSubjectPanelCtrl {

	private Model model;
	private ArrayList<Integer> nbPerSubject;
	private JPanel jpSubjects;
	private GridBagConstraints gbc;
	
	public ResultSubjectPanelCtrl() {
		this.nbPerSubject = new ArrayList<Integer>();
		this.jpSubjects = new JPanel(new GridBagLayout());
	}
	
	public void setModel(Model model) {
		this.model = model;
		
		this.gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0.5;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.BOTH;
		
		initializeData();
	}

	private void initializeData() {
		// Initialize the subjects panel
		for (int i = 0 ; i < model.getSubjects().size() ; i++) {
			nbPerSubject.add(0);
		}
		
		for (Person person : this.model.getPersons()) {
			
			int subjectId = person.getAssigned() == null ? 0 : person.getAssigned().getId() - 1;
			nbPerSubject.set(subjectId, nbPerSubject.get(subjectId) + 1);
		}
		
		for (Subject subject : this.model.getSubjects()) {
			this.jpSubjects.add(new ResultSubjectDetail(subject, this.nbPerSubject.get(subject.getId() - 1)), gbc);
			
			if (gbc.gridx == 0) {
				gbc.gridx ++;
			} else {
				gbc.gridx = 0;
				gbc.gridy ++;
			}
		}
	}
	
	public JPanel getJpSubjects() {
		return this.jpSubjects;
	}
}
