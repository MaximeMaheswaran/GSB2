package Vue;


import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class VueSecretaire extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Font mainFont ;

    // Panel Secretaire
    private JPanel secretaireMainPanel;
    private JPanel secretairePanel1;
	private JPanel secretairePanel2;
	private JPanel secretairePanel3;
	private JPanel secretairePanel4;

	private JMenuBar menuBar ;
	private JMenu menuSecretaire ;
	private JMenuItem ajouterConference ;
	private JMenuItem ajouterPresentation ;
	private JMenuItem voirPresentations ;
	private JMenuItem supprimerPresentation ;
	private JMenuItem supprimerConference ;
	private JMenuItem quitter ;
	
	public VueSecretaire() {
		
		this.secretaireMainPanel = new JPanel();
		this.secretairePanel1 = new JPanel();
		this.secretairePanel2 = new JPanel();
		this.secretairePanel3 = new JPanel();
		this.secretairePanel4 = new JPanel();
		
		this.menuBar = new JMenuBar();
		this.menuBar.setBackground(Color.WHITE);	
		this.menuSecretaire = new JMenu("Menu");
		this.menuSecretaire.setRolloverEnabled(true);
		this.ajouterConference = new JMenuItem("Ajouter une nouvelle conférence");
		this.ajouterPresentation = new JMenuItem("Ajouter une nouvelle présentation");
		this.voirPresentations = new JMenuItem("Voir les présentations");
		this.supprimerPresentation = new JMenuItem("Supprimer une présentation");
		this.supprimerConference = new JMenuItem("Supprimer une conférence");
		this.quitter = new JMenuItem("Se déconnecter");
		
        this.secretaireMainPanel.setLayout(new GridLayout(0,1));
	    this.secretaireMainPanel.setBackground(new Color(119 , 170 , 221));
	    this.secretairePanel1.setBackground(new Color(119 , 170 , 221));
		this.secretairePanel2.setBackground(new Color(119 , 170 , 221));
		this.secretairePanel3.setBackground(new Color(119 , 170 , 221));
		this.secretairePanel4.setBackground(new Color(119 , 170 , 221));
	    		
		this.menuSecretaire.add(this.ajouterConference);
		this.menuSecretaire.add(this.ajouterPresentation);
		this.menuSecretaire.add(this.voirPresentations);
		this.menuSecretaire.add(this.supprimerPresentation);
		this.menuSecretaire.add(this.supprimerConference);
		this.menuSecretaire.add(this.quitter);
		this.menuBar.add(this.menuSecretaire);
		
		this.secretaireMainPanel.add(this.secretairePanel1);
		this.secretaireMainPanel.add(this.secretairePanel2);
		this.secretaireMainPanel.add(this.secretairePanel3);
		this.secretaireMainPanel.add(this.secretairePanel4);
		
		this.secretaireMainPanel.add(this.menuBar);
		
		this.voirPresentations.setVisible(false);
		this.supprimerPresentation.setVisible(false);
		this.supprimerConference.setVisible(false);
			
	}
	
	public JPanel getVueSecretaire() {
		return secretaireMainPanel;
	}
	
	public JPanel setVueSecretaire(JPanel unPanel) {
		return this.secretaireMainPanel = unPanel ;
	}

	public Font getMainFont() {
		return mainFont;
	}

	public JPanel getSecretaireMainPanel() {
		return secretaireMainPanel;
	}

	public JPanel getSecretairePanel1() {
		return secretairePanel1;
	}

	public JPanel getSecretairePanel2() {
		return secretairePanel2;
	}

	public JPanel getSecretairePanel3() {
		return secretairePanel3;
	}

	public JPanel getSecretairePanel4() {
		return secretairePanel4;
	}

	public void setMainFont(Font mainFont) {
		this.mainFont = mainFont;
	}

	public void setSecretaireMainPanel(JPanel SecretaireMainPanel) {
		this.secretaireMainPanel = SecretaireMainPanel;
	}

	public void setSecretairePanel1(JPanel SecretairePanel1) {
		this.secretairePanel1 = SecretairePanel1;
	}

	public void setSecretairePanel2(JPanel SecretairePanel2) {
		this.secretairePanel2 = SecretairePanel2;
	}

	public void setSecretairePanel3(JPanel SecretairePanel3) {
		this.secretairePanel3 = SecretairePanel3;
	}

	public void setSecretairePanel4(JPanel SecretairePanel4) {
		this.secretairePanel4 = SecretairePanel4;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenu getMenuSecretaire() {
		return menuSecretaire;
	}

	public void setMenuSecretaire(JMenu menuSecretaire) {
		this.menuSecretaire = menuSecretaire;
	}

	public JMenuItem getAjouterConference() {
		return ajouterConference;
	}

	public void setAjouterConference(JMenuItem ajouterConference) {
		this.ajouterConference = ajouterConference;
	}

	public JMenuItem getVoirPresentations() {
		return voirPresentations;
	}

	public void setVoirPresentations(JMenuItem voirPresentations) {
		this.voirPresentations = voirPresentations;
	}

	public JMenuItem getQuitter() {
		return quitter;
	}

	public void setQuitter(JMenuItem quitter) {
		this.quitter = quitter;
	}

	public JMenuItem getSupprimerConference() {
		return supprimerConference;
	}

	public void setSupprimerConference(JMenuItem supprimerConference) {
		this.supprimerConference = supprimerConference;
	}

	public JMenuItem getSupprimerPresentation() {
		return supprimerPresentation;
	}

	public void setSupprimerPresentation(JMenuItem supprimerPresentation) {
		this.supprimerPresentation = supprimerPresentation;
	}

	public JMenuItem getAjouterPresentation() {
		return ajouterPresentation;
	}

	public void setAjouterPresentation(JMenuItem ajouterPresentation) {
		this.ajouterPresentation = ajouterPresentation;
	}
		
}

