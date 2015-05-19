package views;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_NAME = "Attribution des sujets";
	
	private JMenuItem jmiImport;
	private JMenuItem jmiLoad;
	private JMenuItem jmiSave;
	private JMenuItem jmiExport;
	
	public MainFrame() {
		super(APPLICATION_NAME);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initializeView();
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public JMenuItem getJmiImport() {
		if(jmiImport == null) {
			jmiImport = new JMenuItem("Importer");
		}
		
		return jmiImport;
	}
	public JMenuItem getJmiLoad() {
		if(jmiLoad == null) {
			jmiLoad = new JMenuItem("Charger");
		}
		
		return jmiLoad;
	}

	public JMenuItem getJmiSave() {
		if(jmiSave == null){
			jmiSave = new JMenuItem("Sauvegarder");
		}
		
		return jmiSave;
	}
	
	public JMenuItem getJmiExport() {
		if(jmiExport == null) {
			jmiExport = new JMenuItem("Exporter en CVS");
		}
		
		return jmiExport;
	}

	private void initializeView() {
		setPreferredSize(new Dimension(1200, 800));
		
		setJMenuBar(getMenus());
		
		add(new JLabel("Coucou ! :)"));
	}
	
	private JMenuBar getMenus() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("Fichier");
		menu.add(getJmiImport());
		menu.add(getJmiLoad());
		menu.add(getJmiSave());
		menu.add(getJmiExport());
		menuBar.add(menu);
		
		menu = new JMenu("Paramètres");
		menuBar.add(menu);
		
		return menuBar;
	}
}
