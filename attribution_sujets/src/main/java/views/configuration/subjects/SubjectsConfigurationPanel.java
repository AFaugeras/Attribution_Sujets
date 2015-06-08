package views.configuration.subjects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Widget de paramétre des sujets.
 */
public class SubjectsConfigurationPanel extends JPanel {

	// Constantes :
	public static final String JB_ADD_SUBJECT_ACTION = "ADD_SUBJECT";
	public static final String JB_IMPORT_ACTION = "IMPORT";
	public static final String JB_EXPORT_ACTION = "SAVE";

	private static final long serialVersionUID = 1L;
	private static final Dimension PREFERED_SIZE = new Dimension(409, 450);

	/**
	 * Panel des sujets.
	 */
	private JPanel jpSubjects;

	/**
	 * Bouton ajouter.
	 */
	private JButton jbAddSubject;
	
	/**
	 * Bouton importer.
	 */
	private JButton jbImport;
	
	/**
	 * Bouton exporter.
	 */
	private JButton jbExport;

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

	/**
	 * Accesseur de l'attribut jbExport.
	 * 
	 * @return Le JButton jbExport.
	 */
	public JButton getJbExport() {
		if (jbExport == null) {
			jbExport = new JButton("Exporter", new ImageIcon(this.getClass()
					.getClassLoader()
					.getResource("ihm/img/export_subjects2.png")));
			jbExport.setActionCommand(JB_EXPORT_ACTION);
		}

		return jbExport;
	}

	@Override
	public Dimension getPreferredSize() {
		return PREFERED_SIZE;
	}

	/**
	 * Cette méthode privée est appelée par le constructeur pour initialiser la vue.
	 */
	private void initializeView() {
		this.setLayout(new BorderLayout());

		TitledBorder border = BorderFactory.createTitledBorder("Sujets");
		Font font = border.getTitleFont();
		border.setTitleFont(new Font(font.getName(), Font.BOLD, font.getSize()));
		border.setBorder(new LineBorder(Color.BLACK));
		this.setBorder(border);

		JScrollPane jsp = new JScrollPane(getJpSubjects());
		jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setBorder(null);
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
		ret.add(getJbExport());

		return ret;
	}
}
