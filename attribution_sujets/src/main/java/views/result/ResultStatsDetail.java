package views.result;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ResultStatsDetail extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Integer> nbPerChoiceOrder;
	private GridBagConstraints gbc2;
	
	private JLabel jlContent;
	
	public ResultStatsDetail(ArrayList<Integer> nbPerChoiceOrder) {
		super(new GridBagLayout());
		this.nbPerChoiceOrder = nbPerChoiceOrder;
		
		this.gbc2 = new GridBagConstraints();
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gbc2.weightx = 0;
		gbc2.weighty = 0;
		gbc2.fill = GridBagConstraints.NORTHWEST;
		
		intializePanel();
	}

	private void intializePanel() {
		String content = "<html>";
		
		content += "<h1>Statistiques des attributions par rapport aux choix des élèves</h1>";
		content += "<p>Nombre d'élèves ayant eu leur : </p><ul>";
		
		for (int i = 0 ; i < nbPerChoiceOrder.size() ; i++) {
			content += "<li>" + (i + 1) + "° choix : " + nbPerChoiceOrder.get(i) + "</li>";
		}
		
		content += "</ul></html>";
		
		jlContent = new JLabel(content);
		this.add(jlContent, gbc2);
	}
}
