package views.processing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// TODO : Faire la javadoc de cette classe.
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
	        @Override
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
}
