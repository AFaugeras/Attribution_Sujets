package views.result;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Classe permettant d'appliquer des règles particulères de rendu pour le tableau d'affichage des résultats
 * 
 *
 */
public class ResultCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;
	private static final Color LOCKED_CELL_BACKGROUND = new Color(240, 240, 240);//new Color(232, 233, 235);
	private static final Color HOVER_CELL_BACKGROUND = new Color(177, 215, 230);//new Color(175, 175, 175);
	private static final Color SELECTED_CELL_BACKGROUND = new Color(0, 174, 240);
	
	private ArrayList<Integer> disabledCols;
	
	public ResultCellRenderer(ArrayList<Integer> disabledCols) {
		this.disabledCols = disabledCols;System.out.println("nouh");
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int col) {
		// Cells are by default rendered as a JLabel.
		JLabel l = (JLabel) super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, col);

		// Get the status for the current row.
		//TableModel tableModel = table.getModel();
		if (!disabledCols.contains(col)) {
			if(isSelected) {
				l.setForeground(Color.BLACK);
				l.setBackground(HOVER_CELL_BACKGROUND);
				if (hasFocus) {
					l.setForeground(Color.WHITE);
					l.setBackground(SELECTED_CELL_BACKGROUND);
				}
			} else {
				l.setForeground(Color.BLACK);
				l.setBackground(Color.WHITE);
			}
		} else {
			if(isSelected) {
				l.setForeground(Color.BLACK);
				l.setBackground(HOVER_CELL_BACKGROUND);
				if (hasFocus) {
					l.setForeground(Color.WHITE);
					l.setBackground(SELECTED_CELL_BACKGROUND);
				}
				//l.setForeground(Color.BLACK);
			} else {
				l.setForeground(Color.BLACK);
				l.setBackground(LOCKED_CELL_BACKGROUND);
			}
		}

		// Return the JLabel which renders the cell.
		return l;
	}
}
