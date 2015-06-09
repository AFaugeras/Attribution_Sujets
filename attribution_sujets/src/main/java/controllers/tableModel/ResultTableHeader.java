package controllers.tableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class ResultTableHeader extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int position;
	private String label;
	private JCheckBox jcbSelected;
	private boolean sorted = false;

	public ResultTableHeader(int position, String label, boolean selectedDefault) {
		super();
		this.position = position;
		this.label = label;
		jcbSelected = new JCheckBox(label, selectedDefault);
		jcbSelected.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("click on : " + label);
			}
		});
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

	public void rightClick() {
		String newLabel = "";
		String filename = sorted ? "down.png" : "up.png";
		sorted = !sorted;
		String p = getClass().getClassLoader().getResource("ihm/img/" + filename).toString();
		newLabel = "<html>";
		newLabel += getLabel() + " <img src='" + p + "' /></html>";
		
		this.jcbSelected.setText(newLabel);
	}
	
	public void clearSort() {
		String p = getClass().getClassLoader().getResource("ihm/img/sort.png").toString();
		String newLabel = "<html>";
		newLabel += getLabel() + " <img src='" + p + "'></html>";
		
		this.jcbSelected.setText(newLabel);
	}
}
