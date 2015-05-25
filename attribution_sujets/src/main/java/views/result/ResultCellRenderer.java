package views.result;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Classe permettant d'appliquer des règles particulères de rendu pour le tableau d'affichage des résultats
 * 
 * @author Arthur FAUGERAS
 *
 */
public class ResultCellRenderer extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {

		// Cells are by default rendered as a JLabel.
		JLabel l = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, col);

		// Get the status for the current row.
		//TableModel tableModel = table.getModel();
		l.setBackground(Color.LIGHT_GRAY);

		// Return the JLabel which renders the cell.
		return l;
	}
}
