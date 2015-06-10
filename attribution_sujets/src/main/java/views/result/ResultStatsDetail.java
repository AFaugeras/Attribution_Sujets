package views.result;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import models.bean.Model;
import models.stats.Statistic;

public class ResultStatsDetail extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Integer> nbPerChoiceOrder;
	private GridBagConstraints gbc2;
	
	private JLabel jlContent;
	
	private Model model;
	
	public ResultStatsDetail(ArrayList<Integer> nbPerChoiceOrder, Model model) {
		super(new GridBagLayout());
		this.nbPerChoiceOrder = nbPerChoiceOrder;
		
		this.gbc2 = new GridBagConstraints();
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gbc2.weightx = 0.5;
		gbc2.weighty = 0;
		gbc2.fill = GridBagConstraints.NORTHWEST;
		this.model = model;
		intializePanel();
	}

	private void intializePanel() {
		String content = "<html>";
		
		content += "<h1>Statistiques des attributions par rapport aux choix des élèves</h1>";
		content += "<p>Nombre d'élèves ayant eu leur : </p><ul>";
		Statistic stats = new Statistic(model);
		for (int i = 0 ; i < model.getConstraint().getNbMaxChoice() ; i++) {
			content += "<li>" + (i+1) + "° choix : " + stats.NumberWhichGetChoiceN(i+1) + "</li>";
		}
		
		content += "<li>Par défaut avec choix : " + stats.NumberWhichGetChoiceN(-2) + "</li>";
		content += "<li>Par défaut sans choix : " + stats.NumberWhichGetChoiceN(-1) + "</li>";

		
		content += "</ul></html>";
		
		jlContent = new JLabel(content);
		this.add(jlContent, gbc2);
		gbc2.gridy = 1;
		this.add(ResultStatsPieChart.createDemoPanel(model), gbc2);
	}
}
