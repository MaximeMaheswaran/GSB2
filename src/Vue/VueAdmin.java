package Vue;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueAdmin extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Font mainFont ;

    // Panel Admin
    private JPanel adminMainPanel;
    private JPanel adminPanel1;
	private JPanel adminPanel2;
	private JPanel adminPanel3;
	private JPanel adminPanel4;

	private JLabel adminLblMessage;
	private JComboBox<String> adminListe;
	private JButton adminBtnConfirmer;
	private JButton adminBtnQuitter;
	private JButton adminInscriptionBtnAjouter ;
	private JButton adminInscriptionBtnQuitter ;
	
	public VueAdmin() {
		
        // Initialisation de la partie Admin
		this.adminMainPanel = new JPanel();
		this.adminPanel1 = new JPanel();
		this.adminPanel2 = new JPanel();
		this.adminPanel3 = new JPanel();
		this.adminPanel4 = new JPanel();
		this.adminLblMessage= new JLabel("Base de donn�es SIO+"); 
		this.adminListe = new JComboBox<String>();
		this.adminBtnConfirmer = new JButton("Confirmer");
		this.adminBtnQuitter = new JButton("D�connexion");
		this.adminInscriptionBtnQuitter = new JButton("Quitter");
		this.adminInscriptionBtnAjouter = new JButton("Ajouter");
	
	    // Styler pour Admin
        this.adminMainPanel.setLayout(new GridLayout(0,1));
	    this.adminMainPanel.setBackground(Color.DARK_GRAY);
	    this.adminPanel1.setBackground(Color.DARK_GRAY);
		this.adminPanel2.setBackground(Color.DARK_GRAY);
		this.adminPanel3.setBackground(Color.DARK_GRAY);
		this.adminPanel4.setBackground(Color.DARK_GRAY);
		this.adminInscriptionBtnAjouter.setBackground(Color.GRAY);
		this.adminBtnConfirmer.setBackground(Color.GRAY);
		this.adminBtnQuitter.setBackground(Color.GRAY);
		this.adminInscriptionBtnQuitter.setBackground(Color.GRAY);
		this.adminLblMessage.setForeground(Color.RED);
	    this.adminBtnConfirmer.setForeground(Color.WHITE);
	    this.adminBtnQuitter.setForeground(Color.WHITE);
	    this.adminInscriptionBtnAjouter.setForeground(Color.WHITE);
	    this.adminInscriptionBtnQuitter.setForeground(Color.WHITE);
	    this.adminInscriptionBtnQuitter.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
	    this.adminBtnConfirmer.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
	    this.adminInscriptionBtnAjouter.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
	    this.adminBtnQuitter.setBorder(BorderFactory.createLineBorder(Color.GRAY, 5));
	    this.adminLblMessage.setFont(mainFont);
	    
        // Ajout des composants pour Admin
		this.adminListe.addItem("Ajouter une nouvelle conférence");
		this.adminPanel1.add(this.adminLblMessage);
		this.adminPanel2.add(this.adminListe);
		this.adminPanel3.add(this.adminBtnConfirmer);
		this.adminPanel4.add(this.adminBtnQuitter);
		this.adminMainPanel.add(this.adminPanel1);
		this.adminMainPanel.add(this.adminPanel2);
		this.adminMainPanel.add(this.adminPanel3);
		this.adminMainPanel.add(this.adminPanel4);
			
	}
	
	public JPanel getVueAdmin() {
		return adminMainPanel;
	}

	public Font getMainFont() {
		return mainFont;
	}

	public JPanel getAdminMainPanel() {
		return adminMainPanel;
	}

	public JPanel getAdminPanel1() {
		return adminPanel1;
	}

	public JPanel getAdminPanel2() {
		return adminPanel2;
	}

	public JPanel getAdminPanel3() {
		return adminPanel3;
	}

	public JPanel getAdminPanel4() {
		return adminPanel4;
	}

	public JLabel getAdminLblMessage() {
		return adminLblMessage;
	}

	public JComboBox<String> getAdminListe() {
		return adminListe;
	}

	public JButton getAdminBtnConfirmer() {
		return adminBtnConfirmer;
	}

	public JButton getAdminBtnQuitter() {
		return adminBtnQuitter;
	}

	public JButton getAdminInscriptionBtnAjouter() {
		return adminInscriptionBtnAjouter;
	}

	public JButton getAdminInscriptionBtnQuitter() {
		return adminInscriptionBtnQuitter;
	}

	public void setMainFont(Font mainFont) {
		this.mainFont = mainFont;
	}

	public void setAdminMainPanel(JPanel adminMainPanel) {
		this.adminMainPanel = adminMainPanel;
	}

	public void setAdminPanel1(JPanel adminPanel1) {
		this.adminPanel1 = adminPanel1;
	}

	public void setAdminPanel2(JPanel adminPanel2) {
		this.adminPanel2 = adminPanel2;
	}

	public void setAdminPanel3(JPanel adminPanel3) {
		this.adminPanel3 = adminPanel3;
	}

	public void setAdminPanel4(JPanel adminPanel4) {
		this.adminPanel4 = adminPanel4;
	}

	public void setAdminLblMessage(JLabel adminLblMessage) {
		this.adminLblMessage = adminLblMessage;
	}

	public void setAdminListe(JComboBox<String> adminListe) {
		this.adminListe = adminListe;
	}

	public void setAdminBtnConfirmer(JButton adminBtnConfirmer) {
		this.adminBtnConfirmer = adminBtnConfirmer;
	}

	public void setAdminBtnQuitter(JButton adminBtnQuitter) {
		this.adminBtnQuitter = adminBtnQuitter;
	}

	public void setAdminInscriptionBtnAjouter(JButton adminInscriptionBtnAjouter) {
		this.adminInscriptionBtnAjouter = adminInscriptionBtnAjouter;
	}

	public void setAdminInscriptionBtnQuitter(JButton adminInscriptionBtnQuitter) {
		this.adminInscriptionBtnQuitter = adminInscriptionBtnQuitter;
	}
	
}

