package controllers.tableModel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.text.JTextComponent;

import models.bean.Subject;

public class ResultTable extends JTable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Integer> disabledCols;
	private Integer subjectIdColNumber;
	private Integer subjectLabelColNumber;
	private List<Subject> list;

	public ResultTable(Object[][] donnees, String[] entete,  ArrayList<Integer> disabledCols, Integer subjectIdColNumber, Integer subjectLabelColNumber, List<Subject> list) {
		super(donnees, entete);
		
		this.disabledCols = disabledCols;
		this.subjectIdColNumber = subjectIdColNumber;
		this.subjectLabelColNumber = subjectLabelColNumber;
		this.list = list;
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
				if (Integer.valueOf(aValue.toString()) <= list
						.size() && Integer.valueOf(aValue.toString()) > 0) {
					setValueAt(
							list
									.get(Integer.valueOf(aValue.toString()) - 1)
									.getLabel(), rowIndex,
							subjectLabelColNumber);
					super.setValueAt(aValue, rowIndex, columnIndex);
				}
			} catch (NumberFormatException e) {

			}
		} else {
			super.setValueAt(aValue, rowIndex, columnIndex);
		}
		// resizeColumnWidth(this);
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
