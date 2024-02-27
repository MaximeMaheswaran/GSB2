package Controleur;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import Modele.Conference;
import Modele.Modele;
import Vue.VueConference;
import Vue.VueSecretaire;

public class ControleurConference implements ActionListener{

	private JFrame fenetre ;
	private VueConference conference ;
	private VueSecretaire secretaire ;
	
	public ControleurConference(JFrame uneFenetre , String verification) {

		String verif = verification ;
	    this.fenetre = uneFenetre;
	    this.conference = new VueConference();
	    this.secretaire = new VueSecretaire();
	    
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
        if(verif == "ajouter") {
    		this.conference.getBoutonSupprimerConference().setVisible(false);
    		this.conference.getBoutonSupprimerConference().removeActionListener(this);
        }
        else {
    		this.conference.getBoutonSupprimerConference().setVisible(true);
    		this.conference.getBoutonSupprimerConference().addActionListener(this);
        }
        this.secretaire.getAjouterConference().addActionListener(this);
        this.secretaire.getQuitter().addActionListener(this);
        this.fenetre.repaint();
        this.fenetre.validate();

	    // Crée le panel une seule fois en dehors de la boucle
	    JPanel panelPrincipal = this.conference.getUnPanel();
	    panelPrincipal.setBackground(new Color(119 , 170 , 221));

	    // Récupère toutes les données de la base de données
	    ArrayList<Conference> lesConferences = Modele.getLesConferences();
	    int conferencesParLigne = 3;
	    int lignes = (int) Math.ceil((double) lesConferences.size() / conferencesParLigne);

	    // Ajuste le gestionnaire de disposition pour afficher 3 conférences par ligne
	    panelPrincipal.setLayout(new GridLayout(lignes, conferencesParLigne));

        for (Conference uneConference : lesConferences) {
            // Crée un nouveau panel pour chaque conférence
            JPanel unPanel = new JPanel();
            unPanel.setBackground(new Color(119 , 170 , 221));
            unPanel.setLayout(new GridLayout(0, 1));
            unPanel.setBorder(new CompoundBorder(
                    new EmptyBorder(10, 10, 10, 10), // Espace autour du panel
                    BorderFactory.createLineBorder(Color.WHITE, 1))); // Ligne autour du panel
            
            // Utilise un JLabel distinct pour chaque description
            JLabel descriptionLabel = new JLabel(""
            		+ "<html>"
            		+ "<body> "
            		+ "<br>"
            		+ "&nbsp; Nom de la conférence : &nbsp;" + uneConference.getTheme()
            		+ "<br>"
            		+ "&nbsp; Nombre de présentations : " + Modele.getLesPresentations(uneConference.getIdConf()).size() 
            		+ "&nbsp;"
            		+ "<br><br>"
            		); 
            unPanel.add(descriptionLabel);
            // Ajoute un gestionnaire d'événements pour rendre le panel cliquable
            unPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	if(verif == null) {
		                int confirm = JOptionPane.showConfirmDialog(fenetre,
		                        "<html>"
		                        + "<style type='text/css'>"
		                        + "div#red { color: red; text-align: center;}"
		                        + "body{text-align: center}"
		                        + "</style>"
		                        + "<body>"
		                        + "Voulez-vous vraiment supprimer cette conférence ? <br><br>"
		                        + "<div id='red'>"
		                		+ "ATTENTION "
		                        + "</div>"
		                        +" Cette action entraînera aussi la suppression de toutes ses présentations."
		                        + "</body>"
		                        + "</html>", "Confirmation", JOptionPane.YES_NO_OPTION);
		                if (confirm == JOptionPane.YES_OPTION) {
		                	if(Modele.supprimerUneConference(uneConference.getIdConf())) {
			    	    		JOptionPane.showMessageDialog(fenetre ,"La conférence a bien été supprimer.");
			    	    		fenetre.remove(conference.getVueConference());
			    	    		fenetre.repaint();
			    	    		fenetre.revalidate();
			                    if(!Modele.existeConference()) {
			                        new ControleurSecretaire(fenetre);
			                    }
			                    else {
			                    	new ControleurConference(fenetre , verification);
			                    }
		                	}
		                	else {
			    	    		JOptionPane.showMessageDialog(fenetre ,"Un problème est survenue. \nVeuillez réessayer.");
		
		                	}
		                }
                	}
                	else {
                		if(verif == "ajouter") {
                            fenetre.getContentPane().removeAll();
                            fenetre.repaint();
                            fenetre.revalidate();
                            new ControleurUnePresentation(fenetre , 0 , uneConference.getIdConf());
                		}
                	}
                }
            });

            panelPrincipal.add(unPanel);
        }

	    // Ajoute le panel principal à la fenêtre
	    this.conference.getVueConference().add(panelPrincipal);
	    this.fenetre.add(this.conference.getVueConference());

	    // Met à jour et valide la fenêtre après avoir ajouté tous les composants
	    this.fenetre.getContentPane().repaint();
	    this.fenetre.getContentPane().validate();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
    	String verification = null ;
        // Si le bouton Quitter du panel Secretaire est appuyé, demande de fermer le panel
        if(e.getSource() == this.secretaire.getQuitter()) {
            int confirm = JOptionPane.showConfirmDialog(null, "\nSe déconnecter ?", "Déconnexion Secretaire", JOptionPane.YES_NO_OPTION);     
            // Si l'utilisateur confirme la fermeture, fermeture du panel
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, "Déconnecté de l'interface secrétaire.");
                this.fenetre.getContentPane().removeAll(); // Retire tous les composants du ContentPane
                this.fenetre.repaint();
                this.fenetre.revalidate();
                new ControleurConnexion(this.fenetre);
            }
            else {
                // L'utilisateur ne confirme pas la fermeture
                // Ne rien faire
            }
        }
        if(e.getSource() == this.secretaire.getAjouterConference()) {
            this.fenetre.getContentPane().removeAll();
            this.fenetre.revalidate();
            this.fenetre.repaint();
            new ControleurAjouterConference(this.fenetre);
        }
        if(e.getSource() == this.secretaire.getAjouterPresentation()) {
            this.fenetre.getContentPane().removeAll();
            this.fenetre.revalidate();
            this.fenetre.repaint();
            verification = "ajouter";
            new ControleurConference(this.fenetre , verification);
        }
        if(e.getSource() == this.secretaire.getVoirPresentations()) {
            this.fenetre.getContentPane().removeAll();
            this.fenetre.revalidate();
            this.fenetre.repaint();
            new ControleurPresentation(this.fenetre , verification);
        }
        if(e.getSource() == this.secretaire.getSupprimerPresentation()) {
            this.fenetre.getContentPane().removeAll();
            this.fenetre.revalidate();
            this.fenetre.repaint();
            verification = "supprimer";
            new ControleurPresentation(this.fenetre , verification);
        }
        if(e.getSource() == this.secretaire.getSupprimerConference()) {
            this.fenetre.getContentPane().removeAll();
            this.fenetre.revalidate();
            this.fenetre.repaint();
            new ControleurConference(this.fenetre , verification);
        }
        if(e.getSource() == this.conference.getBoutonSupprimerConference()) {
            int confirm = JOptionPane.showConfirmDialog(null, "\nVoulez-vous vraiment supprimer toutes les conférences ?", "Suppression", JOptionPane.YES_NO_OPTION);     
            // Si l'utilisateur confirme
            if (confirm == JOptionPane.YES_OPTION) {
            	Modele.supprimerToutesLesConferences();
                JOptionPane.showMessageDialog(null, "Toutes les conférences ont bien étés supprimées");
                this.fenetre.getContentPane().removeAll(); // Retire tous les composants du ContentPane
                this.fenetre.repaint();
                this.fenetre.revalidate();
                if(!Modele.existeConference()) {
                    new ControleurSecretaire(this.fenetre);
                }
                else {
                	new ControleurConference(this.fenetre , verification);
                }
            }
            else {
                // L'utilisateur ne confirme pas la fermeture
                // Ne rien faire
            }
        }
	}
}
