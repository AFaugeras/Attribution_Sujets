package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;

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
			jmiImport = new JMenu("Importer");
			JMenuItem fromSolverChocco = new JMenuItem("à partir du résultat Chocco");
			JMenuItem fromSolverGLPK = new JMenuItem("à partir du résultat GLPK");
			JMenuItem fromTreatment = new JMenuItem("à partir du résultat de notre traitement");
			
			jmiImport.add(getFromCSV());
			jmiImport.add(fromSolverChocco);
			jmiImport.add(fromSolverGLPK);
			jmiImport.add(fromTreatment);
		}
		
		return jmiImport;
	}
	
	public JMenuItem getFromCSV() {
		JMenuItem fromCSV = new JMenuItem("à partir d'un CSV");
		
		fromCSV.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				
				fc.setDialogTitle("Importer à partir d'un CSV");
				fc.setFileFilter(new FileFilter() {
					
					@Override
					public String getDescription() {
						return ".xls, .csv";
					}
					
					@Override
					public boolean accept(File f) {
						String[] extensions = {"csv", "xls"};
						if (f.isDirectory()) {
						      return true;
						    } else {
						      String path = f.getAbsolutePath().toLowerCase();
						      for (int i = 0, n = extensions.length; i < n; i++) {
						        String extension = extensions[i];
						        if ((path.endsWith(extension) && (path.charAt(path.length() 
						                  - extension.length() - 1)) == '.')) {
						          return true;
						        }
						      }
						    }
						    return false;
					}
				});
				
				int returnVal = fc.showOpenDialog(null);
				
				if (returnVal == JFileChooser.CANCEL_OPTION) {
					System.out.println("Choix du fichier csv annulé");
				}
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("Choix du fichier csv validé");
					System.out.println(fc.getSelectedFile().getAbsolutePath());
				}
				
				if (returnVal == JFileChooser.ERROR) {
					System.out.println("Choix du fichier csv interrompu");
				}
			}
		});
		
		return fromCSV;
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