package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controleur.ControleurConnexion;

public class VueConnexion extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		
	private JPanel vueConnexion;
	private ControleurConnexion unControleurConnexion ;
	
	
	private JPanel connexionDescriptionPanel;
	private JPanel connexionTextFieldPanel;
	private JLabel connexionLoginLabel;
	private JTextField connexionLoginField;
	private JLabel connexionMdpLabel;
	private JPasswordField connexionMdpField;
	private JPanel connexionButtonPanel;
	private JButton connexionBtnConnexion;
	private JButton connexionBtnQuitter;
	private int connexionCompteurMdp;

	private boolean connexionJOptionPaneVisible;
		
	public VueConnexion() {
				
        this.connexionCompteurMdp = 3 ;
        this.connexionJOptionPaneVisible = false;
              
        this.vueConnexion = new JPanel();     
        this.connexionTextFieldPanel = new JPanel();
        this.connexionDescriptionPanel = new JPanel();
        this.connexionButtonPanel = new JPanel();
        this.connexionDescriptionPanel = new JPanel();
        this.connexionLoginLabel = new JLabel("Login :");
        this.connexionMdpLabel = new JLabel("Mot de passe :");
		this.connexionMdpField = new JPasswordField(20);
        this.connexionLoginField = new JTextField(20);
        this.connexionBtnConnexion = new JButton("Connexion");
        this.connexionBtnQuitter = new JButton("Quitter");
        																						
		this.vueConnexion.setLayout(new BorderLayout());
        this.connexionDescriptionPanel.setLayout(new BorderLayout());
        this.connexionButtonPanel.setLayout(new FlowLayout());
        this.connexionTextFieldPanel.setLayout(new FlowLayout());
        this.connexionButtonPanel.setPreferredSize(new Dimension(4, 120));
        
        this.connexionLoginField.setText("mcalvo");
        this.connexionMdpField.setText("root");
        
	    this.connexionBtnConnexion.setBorder(BorderFactory.createLineBorder(new Color(226 , 241 , 255), 5));
	    this.connexionBtnQuitter.setBorder(BorderFactory.createLineBorder(new Color(226 , 241 , 255), 5));
	    this.connexionBtnConnexion.setBackground(new Color(226 , 241 , 255));
	    this.connexionBtnQuitter.setBackground(new Color(226 , 241 , 255));
	    this.connexionDescriptionPanel.setBackground(new Color(119 , 170 , 221));
	    this.connexionTextFieldPanel.setBackground(new Color(119 , 170 , 221));
	    this.connexionButtonPanel.setBackground(new Color(119 , 170 , 221));
	    
	    this.connexionLoginField.setBackground(Color.WHITE);
		this.connexionMdpField.setForeground(Color.BLACK);
	    this.connexionLoginLabel.setForeground(Color.WHITE);
	    this.connexionMdpLabel.setForeground(Color.WHITE);
	    
		this.connexionButtonPanel.add(this.connexionBtnConnexion);
        this.connexionButtonPanel.add(this.connexionBtnQuitter);				        
        this.connexionTextFieldPanel.add(this.connexionLoginLabel);
        this.connexionTextFieldPanel.add(this.connexionLoginField);
        this.connexionTextFieldPanel.add(this.connexionMdpLabel);
        this.connexionTextFieldPanel.add(this.connexionMdpField);
        this.connexionButtonPanel.add(this.connexionBtnQuitter);
        
        this.connexionDescriptionPanel.add(this.connexionTextFieldPanel, BorderLayout.SOUTH);
        this.vueConnexion.add(this.connexionDescriptionPanel, BorderLayout.CENTER);
        this.vueConnexion.add(this.connexionButtonPanel, BorderLayout.SOUTH);
             
	}
	
		
	public boolean isConnexionJOptionPaneVisible() {
		return connexionJOptionPaneVisible;
	}


	public void setConnexionJOptionPaneVisible(boolean connexionJOptionPaneVisible) {
		this.connexionJOptionPaneVisible = connexionJOptionPaneVisible;
	}


	public JPanel getVueConnexion() {
		return vueConnexion;
	}

	public ControleurConnexion getUnControleurConnexion() {
		return unControleurConnexion;
	}

	public JPanel getConnexionDescriptionPanel() {
		return connexionDescriptionPanel;
	}

	public JPanel getConnexionTextFieldPanel() {
		return connexionTextFieldPanel;
	}

	public JLabel getConnexionLoginLabel() {
		return connexionLoginLabel;
	}


	public JTextField getConnexionLoginField() {
		return connexionLoginField;
	}


	public void setConnexionLoginLabel(JLabel connexionLoginLabel) {
		this.connexionLoginLabel = connexionLoginLabel;
	}


	public void setConnexionLoginField(JTextField connexionLoginField) {
		this.connexionLoginField = connexionLoginField;
	}


	public JLabel getConnexionMdpLabel() {
		return connexionMdpLabel;
	}

	public JPasswordField getConnexionMdpField() {
		return connexionMdpField;
	}

	public JPanel getConnexionButtonPanel() {
		return connexionButtonPanel;
	}

	public JButton getConnexionBtnConnexion() {
		return connexionBtnConnexion;
	}

	public JButton getConnexionBtnQuitter() {
		return connexionBtnQuitter;
	}

	public int getConnexionCompteurMdp() {
		return connexionCompteurMdp;
	}

	public void setConnexionCompteurMdp(int connexionCompteurMdp) {
		this.connexionCompteurMdp = connexionCompteurMdp;
	}


	public void setConnexionDescriptionPanel(JPanel connexionDescriptionPanel) {
		this.connexionDescriptionPanel = connexionDescriptionPanel;
	}
	
	
	
}
