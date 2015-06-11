package controllers.tableModel;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.JTextComponent;

import models.bean.Subject;

/**
 * Classe représentant le tableau d'affichage des résultats de l'attribution
 *
 */
public class ResultTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Integer> disabledCols;
	private Integer subjectIdColNumber;
	private Integer subjectLabelColNumber;
	private List<Subject> list;

	/**
	 * @param donnees le contenu du tableau
	 * @param entete l'entete du tableau
	 * @param disabledCols la liste des index des colonnes à désactiver
	 * @param subjectIdColNumber l'index de la colonne des identfiants de sujet
	 * @param subjectLabelColNumber l'index de la colonne des libellés de sujet
	 * @param list la liste des sujets
	 */
	public ResultTable(Object[][] donnees, ResultTableHeader[] entete,
			ArrayList<Integer> disabledCols, Integer subjectIdColNumber,
			Integer subjectLabelColNumber, List<Subject> list) {
		super(donnees, entete);

		refreshHeader(entete);
		this.disabledCols = disabledCols;
		this.subjectIdColNumber = subjectIdColNumber;
		this.subjectLabelColNumber = subjectLabelColNumber;
		this.list = list;
		resizeColumnWidth(this);

		this.setAutoCreateRowSorter(true);
		
		JTable table = this;
		this.getTableHeader().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
//				String name = table.getColumnName(col);
//				System.out.println("Column index selected " + col + " " + name);

				

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				int col = table.columnAtPoint(e.getPoint());
				TableColumnModel colModel = table.getColumnModel();
				switch (e.getButton()) {
				case MouseEvent.BUTTON1: 
					for (int i = 0 ; i < colModel.getColumnCount() ; i++) {
						((ResultTableHeader)colModel.getColumn(i).getHeaderValue()).clearSort();
					}
					
					((ResultTableHeader)colModel.getColumn(col).getHeaderValue()).rightClick();
					break;
				case MouseEvent.BUTTON3:
					((ResultTableHeader)colModel.getColumn(col).getHeaderValue()).setIsSelected();
					System.out.println("left click on : " + ((ResultTableHeader)colModel.getColumn(col).getHeaderValue()).getLabel());
					//refreshHeader(entete);
					table.getTableHeader().repaint();
					break;
				}
			}
		});
	}

	
	private void refreshHeader(ResultTableHeader[] entete) {
		TableColumnModel colModel = this.getColumnModel();
		for (ResultTableHeader head : entete) {
			colModel.getColumn(head.getPosition()).setHeaderRenderer(
					new JComponentTableCellRenderer());
			colModel.getColumn(head.getPosition()).setHeaderValue(head);
		}
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return getPreferredSize().width < getParent().getWidth();
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (disabledCols.contains(col)) {
			return false;
		}
		return true;
	}

	// Override pour permettre le contrôle de la saisie
	// Ainsi que l'actualisation dynamique du libellé des sujets
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (subjectIdColNumber.equals(columnIndex)) {
			try {
				if (Integer.valueOf(aValue.toString()) <= list.size()
						&& Integer.valueOf(aValue.toString()) > 0) {
					setValueAt(list.get(Integer.valueOf(aValue.toString()) - 1)
							.getLabel(), rowIndex, subjectLabelColNumber);
					super.setValueAt(aValue, rowIndex, columnIndex);
				}
			} catch (NumberFormatException e) {

			}
		} else {
			super.setValueAt(aValue, rowIndex, columnIndex);
		}
		resizeColumnWidth(this);
	}

	public void resizeColumnWidth(ResultTable resultTable) {
		final TableColumnModel columnModel = resultTable.getColumnModel();
		for (int column = 0; column < resultTable.getColumnCount(); column++) {
			int width = 150; // Min width
			for (int row = 0; row < resultTable.getRowCount(); row++) {
				TableCellRenderer renderer = resultTable.getCellRenderer(row,
						column);
				Component comp = resultTable.prepareRenderer(renderer, row,
						column);
				width = Math.max(comp.getPreferredSize().width + 5, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	@Override
	public Component prepareEditor(TableCellEditor editor, int row, int column) {
		Component c = super.prepareEditor(editor, row, column);
		if (c instanceof JTextComponent) {
			((JTextComponent) c).selectAll();
		}
		return c;
	}
}

class JComponentTableCellRenderer implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		return (JComponent) value;
	}
}
