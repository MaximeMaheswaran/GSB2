package Vue;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VueConference extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel conferenceMainPanel ;
	private JPanel unPanel ;
	private JButton boutonSupprimerConference ;
	
	public VueConference() {
		
		this.conferenceMainPanel = new JPanel();
		this.unPanel = new JPanel();
		this.boutonSupprimerConference = new JButton(" Supprimer toutes les conférences ");
	    this.boutonSupprimerConference.setBorder(BorderFactory.createLineBorder(new Color(226 , 241 , 255), 5));
	    this.boutonSupprimerConference.setBackground(new Color(226 , 241 , 255));
		this.conferenceMainPanel.add(this.boutonSupprimerConference);	
		
		this.conferenceMainPanel.setBackground(new Color(119 , 170 , 221));
	}

	public JPanel getVueConference() {
		return conferenceMainPanel;
	}

	public void setVueConference(JPanel conferenceMainPanel) {
		this.conferenceMainPanel = conferenceMainPanel;
	}

	public JPanel getUnPanel() {
		return unPanel;
	}

	public void setUnPanel(JPanel unPanel) {
		this.unPanel = unPanel;
	}

	public JButton getBoutonSupprimerConference() {
		return boutonSupprimerConference;
	}

	public void setBoutonSupprimerConference(JButton supprimerConference) {
		this.boutonSupprimerConference = supprimerConference;
	}
		
}
