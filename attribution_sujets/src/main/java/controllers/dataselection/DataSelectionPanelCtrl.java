
package controllers.dataselection;

import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import models.utils.CSVXLSFileFilter;
import views.configuration.dataselection.DataSelectionPanel;
import controllers.Utils;

/**
 * Contrôleur du panel de sélection des données.
 */
public class DataSelectionPanelCtrl extends DropTargetAdapter implements ActionListener {

	// Constantes :
	private static final CSVXLSFileFilter CSV_XLS_FILE_FILTER = new CSVXLSFileFilter();

	/**
	 * La vue.
	 */
	private DataSelectionPanel view;
	
	/**
	 * DropTarget du champ de texte campus.
	 */
	private DropTarget dtJtfCampusFile;
	
	/**
	 * DropTarget du champ de texte personnes.
	 */
	private DropTarget dtJtfPersonsFile;

	/**
	 * Constructeur.
	 * 
	 * @param view La vue.
	 */
	public DataSelectionPanelCtrl(DataSelectionPanel view) {
		this.view = view;

		initializeReactions();
	}

	/**
	 * Permet d'obtenir une instance de la classe File correspondant à la sélection du fichier campus.
	 * @return La File.
	 */
	public File getCampusFile() {
		return new File(this.view.getJtfCampusFile().getText());
	}

	/**
	 * Permet d'obtenir une instance de la classe File correspondant à la sélection de la liste de personnes.
	 * @return
	 */
	public File getPersonsFile() {
		return new File(this.view.getJtfPersonsFile().getText());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();

		if (actionCommand.equals(DataSelectionPanel.JB_CAMPUS_SELECTION)) {
			this.campusFileSelection();
		} else if (actionCommand.equals(DataSelectionPanel.JB_PERSONS_SELECTION)) {
			this.personFileSelection();
		}
	}
	
	/**
	 * Indique si le fichier campus sélectionner existe réellement.
	 * @return True si il existe, false sinon.
	 */
	public boolean isCampusFileExists() {
		return this.getCampusFile().exists();
	}
	
	/**
	 * Indique si la liste de personnes renseignée existe réellement.
	 * @return True si elle existe, false sinon.
	 */
	public boolean isPersonFileExists() {
		return this.getPersonsFile().exists();
	}

	/**
	 * Méthode privée appellée par le constructeur pour initialiser les réactions.
	 */
	private void initializeReactions() {
		this.view.getJbCampusFile().addActionListener(this);
		this.view.getJbPersonsFile().addActionListener(this);
		
		this.dtJtfCampusFile = new DropTarget(this.view.getJtfCampusFile(), DnDConstants.ACTION_MOVE, this);
		this.dtJtfPersonsFile = new DropTarget(this.view.getJtfPersonsFile(), DnDConstants.ACTION_MOVE, this);
	}

	/**
	 * Méthode de réaction au clic sur le bouton import du champ campus.
	 */
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

	/**
	 * Méthode de réaction au clic sur le bouton import du champ personnes.
	 */
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

	/**
	 * Méthode de réaction au drop sur le champs de texte campus.
	 * Accepte les fichier csv au format campus.
	 * 
	 * @param dtde Le DropTargetDropEvent associé.
	 */
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

	/**
	 * Méthode de réaction au drip sur le champs de texte personnes.
	 * Accepte les fichier csv au format oasis.
	 * 
	 * @param dtde Le DropTargetDropEvent associé.
	 */
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
