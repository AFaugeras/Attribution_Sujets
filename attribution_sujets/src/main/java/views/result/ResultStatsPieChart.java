package views.result;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import models.bean.Constraints;
import models.bean.Model;
import models.bean.Person;
import models.bean.Subject;
import models.stats.Statistic;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 * Classe permettant la création d'un PieChart à partir du model 
 * 
 * Le piechart crée représentera la proportion des élèves ayant eu leur nième choix
 *
 */
public class ResultStatsPieChart extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static PiePlot pieplot;

	private static PieDataset createDataset(Model model)
    {
		Statistic stats = new Statistic(model);
            DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
           
            for (int i = 0 ; i < model.getConstraint().getNbMaxChoice() ; i++) {
            	defaultpiedataset.setValue((i+1) + "° choix", Math.round(stats.PortionWhichGetChoiceN(i+1)*100)/100);
            }
            
            if (stats.PortionWhichGetChoiceN(-2) != 0) {
            	Double value = new Double(stats.PortionWhichGetChoiceN(-2) * 100);
            	defaultpiedataset.setValue("Par défaut avec choix", Math.round(value) / 100);
            }
            if (stats.PortionWhichGetChoiceN(-1) != 0)
            	defaultpiedataset.setValue("Par défaut sans choix", Math.round(stats.PortionWhichGetChoiceN(-1)*100)/100);
            return defaultpiedataset;
    }

    private static JFreeChart createChart(PieDataset piedataset)
    {
            JFreeChart jfreechart = ChartFactory.createPieChart("Statistiques de la répartition", piedataset, true, true, false);
            pieplot = (PiePlot)jfreechart.getPlot();
            pieplot.setNoDataMessage("Aucune donnée disponible");
            pieplot.setExplodePercent("Two", 0.20000000000000001D);
            pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})"));
            pieplot.setLabelBackgroundPaint(new Color(220, 220, 220));
            pieplot.setLabelGenerator(null);
            pieplot.setInteriorGap(0.00D);
            return jfreechart;
    }

    /**
     * @param model le model de l'application ayant été résolu
     * @return le JPanel contenant le piechart généré
     */
    public static JPanel createDemoPanel(Model model)
    {   	
            JFreeChart jfreechart = createChart(createDataset(model));	
            JPanel panel = new ChartPanel(jfreechart);
            panel.addMouseWheelListener(new MouseWheelListener() {
				
				@Override
				public void mouseWheelMoved(MouseWheelEvent e) {
					pieplot.handleMouseWheelRotation(e.getWheelRotation());
				}
			});
            
            JPanel myPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
    		gbc.gridx = 0;
    		gbc.gridy = 0;
    		gbc.weightx = 1;
    		gbc.weighty = 1;
    		gbc.fill = GridBagConstraints.BOTH;
            myPanel.add(panel, gbc);
            return myPanel;
    }
    
    /**
     * Main de démonstration et de développement du PieChart
     *
     * @param args  ignorés.
     */
    public static void main(final String[] args) {

    	JFrame frameTest = new JFrame();
		frameTest.setLayout(new GridBagLayout());
		
		ArrayList<Subject> subjects = new ArrayList<Subject>();

		Subject subject1 = new Subject(1, "Stark", 0, 10, 0, 2);
		subjects.add(subject1);
		Subject subject2 = new Subject(2, "Lannister", 0, 10, 0, 1);
		subjects.add(subject2);
		Subject subject3 = new Subject(3, "Baratheon", 0, 10, 0, 2);
		subjects.add(subject3);
		Subject subject4 = new Subject(4, "Targaryen", 0, 10, 0, 1);
		subjects.add(subject4);

		ArrayList<Person> people = new ArrayList<Person>();

		for (int i = 0; i < 40; i++) {
			int random = (int) (Math.random() * 4);
			Person someone = new Person();
			someone.setFirstName("Hodor");
			someone.setFamilyName(String.valueOf(i));
			someone.setIDcampus(i + "hodor" + 15);
			someone.setAssigned(subjects.get(random));
			ArrayList<Subject> choices = new ArrayList<Subject>();
			choices.add(subject1);
			choices.add(subject2);
			choices.add(subject4);
			someone.setChoices(choices);
			
			people.add(someone);
		}

		people.get(0).setComment("u vhbfe ivlbfaei bfeiuag beidvb dckjv c hvfeib vidpbe vibdeiv bfidl bfda vpibcdklh zbvclkd bvjkcbdv kdfzb viefda bchkvv falykbvkdbchjvc b vhbeai kvbdcalkv bc dqj bqc hvlaf bqvhbdqvl hqkcb dvhjqd bvldqb");
		
		Model model = new Model(null, people, subjects);
		
		Constraints constraint = new Constraints(3, 0, 0, 0);
		
		model.setConstraint(constraint);
		
		
        frameTest.add(createDemoPanel(model), new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		frameTest.setPreferredSize(new Dimension(1200, 600));
		frameTest.pack();
		frameTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTest.setLocationRelativeTo(null);
		frameTest.setVisible(true);

    }
}
