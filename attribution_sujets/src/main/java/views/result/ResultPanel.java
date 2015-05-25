package views.result;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;

import com.itextpdf.text.DocumentException;

import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;

public class ResultPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private JTable jpPeople;

	public static final String JB_EXPORT_CSV_ACTION = "EXPORT CSV";
	public static final String JB_EXPORT_PDF_ACTION = "EXPORT PDF";

	private JButton jbExportCsv;
	private JButton jbExportPdf;

	private String[] entete = { "Prénom", "Nom", "Identifiant Campus",
			"Identifiant sujet", "Sujet", "Commentaires" };;
	private Object[][] donnees;

	public ResultPanel(Model model) {
		this.model = model;

		initializeView();
	}

	/**
	 * Cette méthode privée est appellée par le constructeur pour initialiser la
	 * vue.
	 */
	private void initializeView() {
		this.setLayout(new BorderLayout());

		JScrollPane jsp = new JScrollPane(getJpPeople());
		jsp.setBorder(null);
		System.out.println(jsp.getVerticalScrollBar().getUnitIncrement());
		jsp.getVerticalScrollBar().setUnitIncrement(15);
		this.add(jsp, BorderLayout.CENTER);

		this.add(getButtonsBar(), BorderLayout.SOUTH);
	}

	private JPanel getButtonsBar() {
		JPanel ret = new JPanel();
		ret.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		ret.add(getJbExportCsv());
		ret.add(getJbExportPdf());

		return ret;
	}

	private JButton getJbExportPdf() {
		if (jbExportCsv == null) {
			jbExportCsv = new JButton("Export PDF", new ImageIcon(this
					.getClass().getClassLoader()
					.getResource("ihm/img/export_pdf.png")));
			jbExportCsv.setActionCommand(JB_EXPORT_CSV_ACTION);
			jbExportCsv.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						exportPDF();
					} catch (FileNotFoundException | DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

		return jbExportCsv;
	}

	private JButton getJbExportCsv() {
		if (jbExportPdf == null) {
			jbExportPdf = new JButton("Export CSV", new ImageIcon(this
					.getClass().getClassLoader()
					.getResource("ihm/img/export_csv.png")));
			jbExportPdf.setActionCommand(JB_EXPORT_PDF_ACTION);
			jbExportPdf.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						exportCSV();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

		return jbExportPdf;
	}

	private JTable getJpPeople() {
		if (jpPeople == null) {
			// this.jpPeople = new JPanel();

			List<Person> people = this.model.getPersons();

			donnees = new Object[people.size()][];

			for (int i = 0; i < people.size(); i++) {
				Person someone = people.get(i);
				String[] data = { someone.getFirstName(), someone.getFamilyName(), someone.getIDcampus(),
						String.valueOf(someone.getAssigned().getId()),
						someone.getAssigned().getLabel(), someone.getComment() };
				donnees[i] = data;
			}

			JTable tableau = new JTable(donnees, entete);

			this.jpPeople = tableau;
		}

		return jpPeople;
	}

	public void exportCSV() throws IOException {
		JFileChooser c = new JFileChooser() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void approveSelection() {
				File f = getSelectedFile();
				if (!f.getName().endsWith(".csv"))
					f = new File(f.getAbsolutePath() + ".csv");
		        if(f.exists() && getDialogType() == SAVE_DIALOG){
		            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
		            switch(result){
		                case JOptionPane.YES_OPTION:
		                    super.approveSelection();
		                    return;
		                case JOptionPane.NO_OPTION:
		                    return;
		                case JOptionPane.CLOSED_OPTION:
		                    return;
		                case JOptionPane.CANCEL_OPTION:
		                    cancelSelection();
		                    return;
		            }
		        }
		        super.approveSelection();
		    }  
		};
		
		c.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return ".csv";
			}
			
			@Override
			public boolean accept(File f) {
				String[] extensions = {"csv"};
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
		
		c.setDialogTitle("Export CSV");
		
		// Demonstrate "Save" dialog:
		int rVal = c.showSaveDialog(this.jpPeople);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			String filename = c.getSelectedFile().getAbsolutePath();

			if (!filename.endsWith(".csv"))
				filename += ".csv";
			
			System.out.println("FILENAME CHOSEN : " + filename);

			FileWriter fileWriter = new FileWriter(filename);

			String line = "";

			for (Object object : entete) {
				line += object + ";";
			}
			line += '\n';
			fileWriter.append(line);

			for (Object[] objects : donnees) {
				line = "";
				for (Object object : objects) {
					line += (object == null ? "" : object) + ";";
				}
				line += '\n';
				fileWriter.append(line);
			}

			fileWriter.close();
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("CANCEL CHOSING FILENAME FOR CSV EXPORT");
		}
	}

	public void exportPDF() throws FileNotFoundException, DocumentException {
		ResultPdfGenerator generator = new ResultPdfGenerator(this.entete, this.donnees);
		
		JFileChooser c = new JFileChooser() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void approveSelection() {
				File f = getSelectedFile();
				if (!f.getName().endsWith(".pdf"))
					f = new File(f.getAbsolutePath() + ".pdf");
		        if(f.exists() && getDialogType() == SAVE_DIALOG){
		            int result = JOptionPane.showConfirmDialog(this,"The file exists, overwrite?","Existing file",JOptionPane.YES_NO_CANCEL_OPTION);
		            switch(result){
		                case JOptionPane.YES_OPTION:
		                    super.approveSelection();
		                    return;
		                case JOptionPane.NO_OPTION:
		                    return;
		                case JOptionPane.CLOSED_OPTION:
		                    return;
		                case JOptionPane.CANCEL_OPTION:
		                    cancelSelection();
		                    return;
		            }
		        }
		        super.approveSelection();
		    }  
		};
		
		c.setFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return ".pdf";
			}
			
			@Override
			public boolean accept(File f) {
				String[] extensions = {"pdf"};
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
		
		c.setDialogTitle("Export PDF");
		
		// Demonstrate "Save" dialog:
		int rVal = c.showSaveDialog(this.jpPeople);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			String filename = c.getSelectedFile().getAbsolutePath();

			if (!filename.endsWith(".pdf"))
				filename += ".pdf";
			
			System.out.println("FILENAME CHOSEN : " + filename);

			generator.buildPDF(filename);
		}
		if (rVal == JFileChooser.CANCEL_OPTION) {
			System.out.println("CANCEL CHOSING FILENAME FOR PDF EXPORT");
		}
	}

	// TODO Development method to delete.
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		JFrame frameTest = new JFrame();
		frameTest.setLayout(new GridBagLayout());

		ArrayList<Subject> subjects = new ArrayList<Subject>();

		Subject subject1 = new Subject(1, "Stark", 0, 0, 0, 0, 0);
		subjects.add(subject1);
		Subject subject2 = new Subject(2, "Lannister", 0, 0, 0, 0, 0);
		subjects.add(subject2);
		Subject subject3 = new Subject(3, "Baratheon", 0, 0, 0, 0, 0);
		subjects.add(subject3);
		Subject subject4 = new Subject(4, "Targaryen", 0, 0, 0, 0, 0);
		subjects.add(subject4);

		ArrayList<Person> people = new ArrayList<Person>();

		for (int i = 0; i < 40; i++) {
			int random = (int) (Math.random() * 4);
			Person someone = new Person();
			someone.setFirstName("Hodor");
			someone.setFamilyName(String.valueOf(i));
			someone.setIDcampus(i + "hodor" + 15);
			someone.setAssigned(subjects.get(random));
			people.add(someone);
		}

		Model model = new Model(null, people, subjects);

		ResultPanel tmp = new ResultPanel(model);
		// new ResultPanel(new Model(null, people, subjects));
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

		System.out.println(tmp.getSize());

	}
}
