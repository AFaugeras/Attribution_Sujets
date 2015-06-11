package views.result;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import models.bean.Subject;

/**
 * Panel du détail d'un sujet (après répartition)
 *
 */
public class ResultSubjectDetail extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Subject subject;
	private Integer sum;
	private GridBagConstraints gbc2;
	
	private JLabel jlContent;
	
	/**
	 * Constructeur du panel
	 * 
	 * @param subject le sujet pour lequel on veut les détails
	 * @param sum la somme des élèves à qui on a attribué ce sujet
	 */
	public ResultSubjectDetail(Subject subject, Integer sum) {
		super(new GridBagLayout());
		this.subject = subject;
		this.sum = sum;
		
		this.gbc2 = new GridBagConstraints();
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
		gbc2.fill = GridBagConstraints.CENTER;
		
		intializePanel();
	}

	private void intializePanel() {
		String content = "<html>";
		
		content += "<style type='text/css'>";
		content += "table {margin-left : 20px;}";
		content += "html {background-color:#FFFFFF;width:100%;border: 1px solid #C0C0C0; border-radius: 10px;width: 300px;padding: 10px;margin: 5px;}";
		content += "h1 {color: #005CB1}";
		content += "</style>";
		
		content += "<h1>" + this.subject.getId() + " - " + this.subject.getLabel() + "</h1>";
		
		content += "<table>";
		content += "<tr><td>Cardinalités : </td><td>[ " + this.subject.getMinCard() + " ; " + this.subject.getMaxCard() + " ]</td></tr>";
		
		content += "<tr><td>Taille : </td><td>[ " + this.subject.getMinSize() + " ; " + this.subject.getMaxSize() + " ]</td></tr>";
		
		content += "<tr><td>Nombre total d'étudiants : </td><td>" + sum + "</td></tr>";
		
		content += "</table>";
		
		content += "</html>";
		jlContent = new JLabel(content);
		this.add(jlContent, gbc2);
	}

}
