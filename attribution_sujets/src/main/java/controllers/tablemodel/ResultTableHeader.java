package controllers.tablemodel;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * Classe gérant l'affichage du header de la JTable dans l'affichage des résultats de l'attribution
 *
 */
public class ResultTableHeader extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int position;
	private String label;
	private JCheckBox jcbSelected;
	private boolean sorted = false;

	/**
	 * @param position l'index de la colonne
	 * @param label le libellé de la colonne
	 * @param selectedDefault si la checkbox est par défaut sélectionnée ou non
	 */
	public ResultTableHeader(int position, String label, boolean selectedDefault) {
		super();
		this.position = position;
		this.label = label;
		jcbSelected = new JCheckBox(label, selectedDefault);
		jcbSelected.setEnabled(true);
		this.setBorder(UIManager.getBorder("TableHeader.cellBorder"));

		this.add(jcbSelected);
		clearSort();
	}

	public int getPosition() {
		return position;
	}

	public String getLabel() {
		return this.label;
	}

	public boolean isSelected() {
		return jcbSelected.isSelected();
	}
	
	public void setIsSelected() {
		this.jcbSelected.setSelected(!jcbSelected.isSelected());
	}

	/**
	 * gère l'affichage de l'icone de tri suivant le click
	 */
	public void rightClick() {
		String newLabel = "";
		String filename = sorted ? "down.png" : "up.png";
		sorted = !sorted;
		String p = getClass().getClassLoader().getResource("ihm/img/" + filename).toString();
		newLabel = "<html>";
		newLabel += getLabel() + " <img src='" + p + "' /></html>";
		
		this.jcbSelected.setText(newLabel);
	}
	
	/**
	 * réinitialise l'icone de tri
	 */
	public void clearSort() {
		String p = getClass().getClassLoader().getResource("ihm/img/sort.png").toString();
		String newLabel = "<html>";
		newLabel += getLabel() + " <img src='" + p + "'></html>";
		
		this.jcbSelected.setText(newLabel);
	}
}
