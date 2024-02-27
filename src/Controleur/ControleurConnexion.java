package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Modele.Modele;
import Vue.Fenetre;
import Vue.VueConnexion;

public class ControleurConnexion implements ActionListener {
	
	private JFrame uneFenetre ;
	private VueConnexion connexion ;
	
	
	public ControleurConnexion() {
		
		this.uneFenetre = new Fenetre() ;	
		this.connexion = new VueConnexion() ;
        ImageIcon uneImage = new ImageIcon(Modele.getUnePhoto(1));
        JLabel imageLabel = new JLabel(uneImage);
        this.connexion.getConnexionDescriptionPanel().add(imageLabel);
		this.connexion.getConnexionBtnQuitter().addActionListener(this);
		this.connexion.getConnexionBtnConnexion().addActionListener(this);
		this.uneFenetre.getContentPane().add(this.connexion.getVueConnexion());
		this.uneFenetre.validate();
		this.uneFenetre.repaint();
		this.uneFenetre.setVisible(true);
        // Champ email et mot de passe qui écoutent la touche Entrée
        this.connexion.getConnexionLoginField().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!connexion.isConnexionJOptionPaneVisible()) {
                        connexion.getConnexionMdpField().requestFocusInWindow();
                    } else {
                    	connexion.setConnexionJOptionPaneVisible(false);
                    }
                }
            }
        });
        this.connexion.getConnexionMdpField().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!connexion.isConnexionJOptionPaneVisible()) {
                        connexion.getConnexionBtnConnexion().doClick();
                    } else {
                    	connexion.setConnexionJOptionPaneVisible(false);
                    }
                }
            }
        });
	}
			
	public ControleurConnexion(JFrame uneFenetre) {
		
		this.uneFenetre = uneFenetre ;
		this.uneFenetre.setJMenuBar(null);
		this.connexion = new VueConnexion() ;    
        ImageIcon uneImage = new ImageIcon(Modele.getUnePhoto(1));
        JLabel imageLabel = new JLabel(uneImage);
        this.connexion.getConnexionDescriptionPanel().add(imageLabel);
		this.connexion.getConnexionBtnQuitter().addActionListener(this);
		this.connexion.getConnexionBtnConnexion().addActionListener(this);
		this.uneFenetre.getContentPane().add(this.connexion.getVueConnexion());	
		this.uneFenetre.validate();
		this.uneFenetre.repaint();			
        // Champ email qui écoute la touche Entrée
        this.connexion.getConnexionLoginField().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!connexion.isConnexionJOptionPaneVisible()) {
                        connexion.getConnexionMdpField().requestFocusInWindow();
                    } else {
                    	connexion.setConnexionJOptionPaneVisible(false);
                    }
                }
            }
        });
     // Champ mot de passe qui écoute la touche Entrée
        this.connexion.getConnexionMdpField().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!connexion.isConnexionJOptionPaneVisible()) {
                        connexion.getConnexionBtnConnexion().doClick();
                    } else {
                    	connexion.setConnexionJOptionPaneVisible(false);
                    }
                }
            }
        });
	}
        	
	public void actionPerformed(ActionEvent e) {
		// Si le bouton Quitter est appuyé , demande la fermeture
		if(e.getSource() == this.connexion.getConnexionBtnQuitter()) {
			int confirm = JOptionPane.showConfirmDialog(null, "\nFermer cette fenêtre ?", "Fermeture", JOptionPane.YES_NO_OPTION);     
			// Si l'utilisateur confirme la fermeture , fermeture de la fenêtre
			if (confirm == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}	
		// Si le bouton Connexion du panel Connexion , on récupère les valeurs
		if(e.getSource() == this.connexion.getConnexionBtnConnexion()) {
			String login = connexion.getConnexionLoginField().getText();
			char[] mdp = connexion.getConnexionMdpField().getPassword();
			String mdpBrut = new String(mdp);
			// Si tous les champs sont vides , affiche une erreur
			if (!Modele.estPasVide(login, mdpBrut)) {
				this.connexion.setConnexionJOptionPaneVisible(true);
			} 
			else {
					this.connexion.setConnexionJOptionPaneVisible(false);
					// Si le login et le mot de passe fournis existent dans la base de données et correspondent à un administrateur , interface administrateur activé
					if(Modele.verificationAdmin(login, mdpBrut)) {
						JOptionPane.showMessageDialog(null, "Interface secretaire activé.");
						this.uneFenetre.remove(this.connexion.getVueConnexion());
						this.connexion.getConnexionButtonPanel().remove(this.connexion.getConnexionBtnConnexion());
				        this.connexion.getConnexionButtonPanel().remove(this.connexion.getConnexionBtnQuitter());
				        this.connexion.getConnexionButtonPanel().add(this.connexion.getConnexionBtnQuitter());
				        this.connexion.getConnexionMdpField().setText("");
				        this.connexion.getConnexionLoginField().setText("");
				        this.connexion.getConnexionTextFieldPanel().remove(this.connexion.getConnexionMdpLabel());
				        this.connexion.getConnexionTextFieldPanel().remove(this.connexion.getConnexionMdpField());
						this.uneFenetre.repaint();
						this.uneFenetre.revalidate();
						new ControleurSecretaire(uneFenetre);
					}
					else {
						// Le login et le mot de passe ne correspondent pas ou l'utilisateur n'est pas un administrateur
						// Si un utilisateur avec l'email et le mot de passe fournis existe dans la base de données , se connecter
						if(Modele.verification(login, mdpBrut)) {
							String nom = Modele.getPrenomVerif(login);
							JOptionPane.showMessageDialog(null, "Bienvenue sur GSB2 " + nom);
							this.uneFenetre.remove(this.connexion.getVueConnexion());
							this.uneFenetre.repaint();
							this.uneFenetre.revalidate();
							new ControleurResponsable(this.uneFenetre);

						}
						else {
							// Le login et/ou le mot de passe est incorrect
							// Si le compteur mot de passe est égal ou inférieur à 0 , fermeture de la fenêtre
							if(this.connexion.getConnexionCompteurMdp() <= 0) {
								JOptionPane.showMessageDialog(null,  "Trop de tentatives effectués. \nFermeture de l'application.");
								System.exit(0);
							}
							else {
								// Affiche mot de passe incorrect avec le nombre de tentatives restantes
								JOptionPane.showMessageDialog(null,  "Mot de passe incorrect. ("+this.connexion.getConnexionCompteurMdp()+")");
								this.connexion.setConnexionCompteurMdp(this.connexion.getConnexionCompteurMdp() - 1);
								this.connexion.setConnexionJOptionPaneVisible(true);
								
							}
						}
					}
				}
			}
		}
	}
