package views.subjects;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import models.bean.Model;
import models.bean.Subject;
import controllers.SubjectsConfigurationCtrl;

/**
 * Widget de paramétre des sujets.
 */
public class SubjectsConfigurationPanel extends JPanel {

	public static final String JB_ADD_SUBJECT_ACTION = "ADD_SUBJECT";
	public static final String JB_IMPORT_ACTION = "IMPORT";
	public static final String JB_NEXT_ACTION = "NEXT";

	private static final long serialVersionUID = 1L;

	private static final Dimension PREFERED_SIZE = new Dimension(409, 450);

	private JPanel jpSubjects;

	private JButton jbAddSubject;
	private JButton jbImport;
	private JButton jbNext;

	/**
	 * Constructeur.
	 */
	public SubjectsConfigurationPanel() {
		super();

		this.initializeView();
	}

	/**
	 * Accesseur de l'attribut getJpSubjects.
	 * 
	 * @return Le JPanel getJpSubjects.
	 */
	public JPanel getJpSubjects() {
		if (jpSubjects == null) {
			this.jpSubjects = new JPanel();
			this.jpSubjects.setBorder(BorderFactory
					.createTitledBorder("Sujets"));
		}

		return jpSubjects;
	}

	/**
	 * Accesseur de l'attribut jbAddSubject.
	 * 
	 * @return Le JButton getJpSubjects.
	 */
	public JButton getJbAddSubject() {
		if (jbAddSubject == null) {
			jbAddSubject = new JButton("Ajouter", new ImageIcon(this.getClass()
					.getClassLoader().getResource("ihm/img/add_subject2.png")));
			jbAddSubject.setActionCommand(JB_ADD_SUBJECT_ACTION);
		}

		return jbAddSubject;
	}

	/**
	 * Accesseur de l'attribut jbImport.
	 * 
	 * @return Le JButton jbImport.
	 */
	public JButton getJbImport() {
		if (jbImport == null) {
			jbImport = new JButton("Importer", new ImageIcon(this.getClass()
					.getClassLoader()
					.getResource("ihm/img/import_subjects.png")));
			jbImport.setActionCommand(JB_IMPORT_ACTION);
		}

		return jbImport;
	}

	/**
	 * Accesseur de l'attribut jbNext.
	 * 
	 * @return Le JButton jbNext.
	 */
	public JButton getJbNext() {
		if (jbNext == null) {
			jbNext = new JButton("Suivant", new ImageIcon(this.getClass()
					.getClassLoader().getResource("ihm/img/next2.png")));
			jbNext.setActionCommand(JB_NEXT_ACTION);
		}

		return jbNext;
	}

	@Override
	public Dimension getPreferredSize() {
		return PREFERED_SIZE;
	}

	/**
	 * Cette méthode privée est appellée par le constructeur pour initialiser la
	 * vue.
	 */
	private void initializeView() {
		this.setLayout(new BorderLayout());

		JScrollPane jsp = new JScrollPane(getJpSubjects());
		jsp.setBorder(null);
		System.out.println(jsp.getVerticalScrollBar().getUnitIncrement());
		jsp.getVerticalScrollBar().setUnitIncrement(15);
		this.add(jsp, BorderLayout.CENTER);

		this.add(getButtonsBar(), BorderLayout.SOUTH);
	}

	/**
	 * Cette méthode privée construit la barre des boutons.
	 * 
	 * @return La barre des boutons (JPanel).
	 */
	private JPanel getButtonsBar() {
		JPanel ret = new JPanel();
		ret.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		ret.add(getJbAddSubject());
		ret.add(getJbImport());
		ret.add(getJbNext());

		return ret;
	}

	// TODO Development method to delete.
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		JFrame frameTest = new JFrame();
		frameTest.setLayout(new GridBagLayout());
		SubjectsConfigurationPanel tmp = new SubjectsConfigurationPanel();
		new SubjectsConfigurationCtrl(new Model(null, null, new ArrayList<Subject>()),
				tmp);
		JPanel aux = new JPanel(new GridBagLayout());
		aux.add(tmp);
		frameTest.add(aux, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
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
