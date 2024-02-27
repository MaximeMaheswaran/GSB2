package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Modele.Modele;
import Vue.VueSecretaire;

public class ControleurSecretaire implements ActionListener {
    
    private JFrame fenetre ;
    private VueSecretaire secretaire ;
    
    public ControleurSecretaire(JFrame fenetre) {
        
        this.fenetre = fenetre ;
        this.secretaire = new VueSecretaire();
        ImageIcon uneImage = new ImageIcon(Modele.getUnePhoto(1));
        JLabel imageLabel = new JLabel(uneImage);
        this.secretaire.getSecretairePanel1().add(imageLabel);   
        
        if(Modele.existeConference()) {
        	// Si des Conférences existent, configure l'interface pour l'utilisateur
            this.secretaire.getAjouterPresentation().addActionListener(this);
            this.secretaire.getSupprimerConference().addActionListener(this);
            
            // Rend les boutons et les actions associées visibles
            this.secretaire.getAjouterPresentation().setVisible(true);        
            this.secretaire.getSupprimerConference().setVisible(true);

            if (Modele.existePresentation()) {
                // Si des présentations existent, configure l'interface pour l'utilisateur
                this.secretaire.getVoirPresentations().addActionListener(this);
                this.secretaire.getSupprimerPresentation().addActionListener(this);
                this.secretaire.getSupprimerConference().addActionListener(this);
                
                // Rend les boutons et les actions associées visibles
                this.secretaire.getAjouterPresentation().setVisible(true);
                this.secretaire.getVoirPresentations().setVisible(true);
                this.secretaire.getSupprimerPresentation().setVisible(true);
                this.secretaire.getSupprimerConference().setVisible(true);
            }
            else {
                // Si aucune présentation n'existe, masque les éléments associés dans l'interface
                this.secretaire.getVoirPresentations().removeActionListener(this);
                this.secretaire.getSupprimerPresentation().removeActionListener(this);
                
                // Rend les boutons et les actions associées invisibles
                this.secretaire.getVoirPresentations().setVisible(false);
                this.secretaire.getSupprimerPresentation().setVisible(false);
            }
        }
        else {
            this.secretaire.getAjouterPresentation().removeActionListener(this);
            this.secretaire.getAjouterPresentation().setVisible(false);
        }
        // Configure d'autres éléments de l'interface
        this.secretaire.getAjouterConference().addActionListener(this);
        this.secretaire.getQuitter().addActionListener(this);
        this.fenetre.setJMenuBar(this.secretaire.getMenuBar());
        this.fenetre.getContentPane().add(this.secretaire.getVueSecretaire());
        this.fenetre.repaint();
        this.fenetre.validate();
    }
    
    public void actionPerformed(ActionEvent e) {
    	String verification = null ;
        // Si le bouton Quitter du panel Secretaire est appuyé, demande de fermer le panel
        if(e.getSource() == this.secretaire.getQuitter()) {
            int confirm = JOptionPane.showConfirmDialog(null, "\nSe déconnecter ?", "Déconnexion Secretaire", JOptionPane.YES_NO_OPTION);     
            // Si l'utilisateur confirme la fermeture, fermeture du panel
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Déconnecté de l'interface secrétaire.");
                this.fenetre.getContentPane().removeAll();
                // Force la revalidation du contenu de la fenêtre
                this.fenetre.revalidate();
                // Force le redessin de la fenêtre
                this.fenetre.repaint();
                // Crée un nouveau contrôleur
                new ControleurConnexion(this.fenetre);
            }
            else {
                // L'utilisateur ne confirme pas la fermeture
                // Ne rien faire
            }
        }
     // Si le bouton "Ajouter Conférence" est cliqué
        if (e.getSource() == this.secretaire.getAjouterConference()) {
            // Nettoie le contenu de la fenêtre
            this.fenetre.getContentPane().removeAll();
            // Force la revalidation du contenu de la fenêtre
            this.fenetre.revalidate();
            // Force le redessin de la fenêtre
            this.fenetre.repaint();
            // Crée un nouveau contrôleur pour gérer l'ajout de conférence
            new ControleurAjouterConference(this.fenetre);
        }

        // Si le bouton "Ajouter Présentation" est cliqué
        if (e.getSource() == this.secretaire.getAjouterPresentation()) {
            // Nettoie le contenu de la fenêtre
            this.fenetre.getContentPane().removeAll();
            // Force la revalidation du contenu de la fenêtre
            this.fenetre.revalidate();
            // Force le redessin de la fenêtre
            this.fenetre.repaint();
            // Définit le type de vérification à "ajouter"
            verification = "ajouter";
            // Crée un nouveau contrôleur pour gérer les conférences avec le mode "ajouter"
            new ControleurConference(this.fenetre, verification);
        }

        // Si le bouton "Voir Présentations" est cliqué
        if (e.getSource() == this.secretaire.getVoirPresentations()) {
            // Nettoie le contenu de la fenêtre
            this.fenetre.getContentPane().removeAll();
            // Force la revalidation du contenu de la fenêtre
            this.fenetre.revalidate();
            // Force le redessin de la fenêtre
            this.fenetre.repaint();
            // Crée un nouveau contrôleur pour afficher les présentations
            new ControleurPresentation(this.fenetre, verification);
        }

        // Si le bouton "Supprimer Présentation" est cliqué
        if (e.getSource() == this.secretaire.getSupprimerPresentation()) {
            // Nettoie le contenu de la fenêtre
            this.fenetre.getContentPane().removeAll();
            // Force la revalidation du contenu de la fenêtre
            this.fenetre.revalidate();
            // Force le redessin de la fenêtre
            this.fenetre.repaint();
            // Définit le type de vérification à "supprimer"
            verification = "supprimer";
            // Crée un nouveau contrôleur pour gérer les présentations avec le mode "supprimer"
            new ControleurPresentation(this.fenetre, verification);
        }

        // Si le bouton "Supprimer Conférence" est cliqué
        if (e.getSource() == this.secretaire.getSupprimerConference()) {
            // Nettoie le contenu de la fenêtre
            this.fenetre.getContentPane().removeAll();
            // Force la revalidation du contenu de la fenêtre
            this.fenetre.revalidate();
            // Force le redessin de la fenêtre
            this.fenetre.repaint();
            // Crée un nouveau contrôleur pour gérer la suppression de conférence
            new ControleurConference(this.fenetre, verification);
        }
    }
}


