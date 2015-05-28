package views.processing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class ProcessingPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Dimension PREFFERED_SIZE = new Dimension(350, 100);
	
	private int secondes = 0;
	private int minutes = 0;
	private int heures = 0;

	private JLabel jlSince;
	private JLabel jlProcessing;
	private JLabel jlSpinner;
	
	private Timer timer;

	public ProcessingPanel() {
		initializeView();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return PREFFERED_SIZE;
	}

	private void initializeView() {
		this.setLayout(new BorderLayout());

		jlSince = new JLabel("Analyse depuis : 0 secondes", JLabel.CENTER);
		jlProcessing = new JLabel("en cours de traitement...", JLabel.CENTER);
		jlProcessing.setHorizontalAlignment(SwingConstants.CENTER);
		jlSpinner = new JLabel(new ImageIcon(this.getClass().getClassLoader()
				.getResource("ihm/img/processing.gif")));

		this.add(jlSince, BorderLayout.NORTH);
		this.add(jlSpinner, BorderLayout.CENTER);
		this.add(jlProcessing, BorderLayout.SOUTH);
		
		this.timer = new Timer();
		
		refreshTimer();
	}

	private void refreshTimer() {
		this.timer.cancel(); //this will cancel the current task. if there is no active task, nothing happens
	    this.timer = new Timer();

	    TimerTask action = new TimerTask() {
	        public void run() {
	            addOneSecond();
	            refreshTimer();
	        }
	    };

	    this.timer.schedule(action, 1000);
	}

	private void addOneSecond() {
		secondes ++;
		
		if (secondes == 60) {
			minutes++;
			secondes = 0;
			
			if (minutes == 60) {
				heures ++;
				minutes = 0;
			}
		}
		
		refreshDisplay();
	}
	
	private void refreshDisplay() {
		String content = "";
		
		content += heures > 0 ? " " + heures + " heure(s)" : "";
		content += minutes > 0 ? " " + minutes + " minute(s)" : "";
		content += " " + secondes + " seconde(s)";
		
		this.jlSince.setText("Analyse depuis : " + content);
	}

	// TODO Development method to delete.
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {

		}

		JFrame frameTest = new JFrame();
		frameTest.setLayout(new GridBagLayout());

		ProcessingPanel tmp = new ProcessingPanel();
		JPanel aux = new JPanel(new GridBagLayout());
		aux.add(tmp);
		frameTest.add(aux, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
						0, 0, 0, 0), 0, 0));
		frameTest.setPreferredSize(new Dimension(400, 200));
		frameTest.pack();
		frameTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTest.setLocationRelativeTo(null);
		frameTest.setVisible(true);

		System.out.println(tmp.getSize());

	}
}
