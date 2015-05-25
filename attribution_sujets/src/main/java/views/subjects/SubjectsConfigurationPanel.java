package views.subjects;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 * Widget de paramétre des sujets.
 */
public class SubjectsConfigurationPanel extends JPanel {

	public static final String JB_ADD_SUBJECT_ACTION = "ADD_SUBJECT";
	public static final String JB_IMPORT_ACTION = "IMPORT";

	private static final long serialVersionUID = 1L;

	private static final Dimension PREFERED_SIZE = new Dimension(409, 450);

	private JPanel jpSubjects;

	private JButton jbAddSubject;
	private JButton jbImport;

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

		TitledBorder border = BorderFactory.createTitledBorder("Sujets");
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		this.setBorder(border);

		JScrollPane jsp = new JScrollPane(getJpSubjects());
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setBorder(UIManager.getBorder("TitledBorder.border"));
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

		return ret;
	}
}
