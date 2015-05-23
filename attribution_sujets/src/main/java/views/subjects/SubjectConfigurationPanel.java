package views.subjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class SubjectConfigurationPanel extends JPanel {

	public static final String JB_ADD_SUBJECT_ACTION = "ADD_SUBJECT";
	public static final String JB_IMPORT_ACTION = "IMPORT";
	public static final String JB_NEXT_ACTION = "NEXT";

	private static final long serialVersionUID = 1L;

	private static final Border BORDER = BorderFactory.createEmptyBorder(5, 5,
			5, 5);

	private JPanel jpSubjects;
	
	private JButton jbAddSubject;
	private JButton jbImport;
	private JButton jbNext;

	public SubjectConfigurationPanel() {
		super();

		this.initializeView();
	}

	public JPanel getJpSubjects() {
		if(jpSubjects == null){
			this.jpSubjects = new JPanel();
			this.jpSubjects.setBorder(BorderFactory.createTitledBorder("Sujets"));
		}
		
		return jpSubjects;
	}



	public JButton getJbAddSubject() {
		if (jbAddSubject == null) {
			jbAddSubject = new JButton("Ajouter", new ImageIcon(this.getClass()
					.getClassLoader().getResource("ihm/img/add_subject.png")));
			jbAddSubject.setActionCommand(JB_ADD_SUBJECT_ACTION);
		}

		return jbAddSubject;
	}

	public JButton getJbImport() {
		if (jbImport == null) {
			jbImport = new JButton("Importer", new ImageIcon(this.getClass()
					.getClassLoader().getResource("ihm/img/import_subjects.png")));
			jbImport.setActionCommand(JB_IMPORT_ACTION);
		}
		
		return jbImport;
	}

	public JButton getJbNext() {
		if (jbNext == null) {
			jbNext = new JButton("Suivant", new ImageIcon(this.getClass()
					.getClassLoader().getResource("ihm/img/next.png")));
			jbNext.setActionCommand(JB_NEXT_ACTION);
		}
		
		return jbNext;
	}

	private void initializeView() {
		this.setLayout(new BorderLayout());

		this.setBorder(BORDER);
		this.setBackground(Color.WHITE);

		this.add(getJpSubjects(), BorderLayout.CENTER);
		this.add(initializeButtonsBar(), BorderLayout.SOUTH);
	}

	private JPanel initializeButtonsBar() {
		JPanel ret = new JPanel();

		ret.add(getJbAddSubject());
		ret.add(getJbImport());
		ret.add(getJbNext());

		return ret;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		JFrame frameTest = new JFrame();
		frameTest.setLayout(new GridBagLayout());
		SubjectConfigurationPanel tmp = new SubjectConfigurationPanel();
		frameTest.add(tmp, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						0, 0, 0, 0), 0, 0));
		frameTest.setPreferredSize(new Dimension(1200, 600));
		frameTest.pack();
		frameTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTest.setLocationRelativeTo(null);
		frameTest.setVisible(true);
		// frameTest.setPreferredSize(new Dimension(1200, 600));

		System.out.println(tmp.getSize());
	}
}
