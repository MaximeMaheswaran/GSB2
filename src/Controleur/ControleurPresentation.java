package Controleur;

import java.awt.Color;
import java.awt.GridLayout;
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

import Modele.Animateur;
import Modele.Modele;
import Modele.Presentation;
import Modele.Salle;
import Vue.VuePresentation;

public class ControleurPresentation {

	private JFrame fenetre ;
	private VuePresentation presentation ;
	
	public ControleurPresentation(JFrame uneFenetre , String verification) {

		String verif = verification ;
	    this.fenetre = uneFenetre;
	    this.presentation = new VuePresentation();
	    
	    // Crée le panel une seule fois en dehors de la boucle
	    JPanel panelPrincipal = this.presentation.getUnPanel();
	    panelPrincipal.setBackground(new Color(119 , 170 , 221));

	    // Récupère toutes les données de la base de données
	    ArrayList<Presentation> lesPresentations = Modele.getToutesLesPresentations();
	    int programmesParLigne = 3;
	    int lignes = (int) Math.ceil((double) lesPresentations.size() / programmesParLigne);

	    // Ajuste le gestionnaire de disposition pour afficher 3 programmes par ligne
	    panelPrincipal.setLayout(new GridLayout(lignes, programmesParLigne));

        for (Presentation unePresentation : lesPresentations) {
            // Crée un nouveau panel pour chaque programme
            JPanel unPanel = new JPanel();
            unPanel.setBackground(new Color(119 , 170 , 221));
            unPanel.setLayout(new GridLayout(0, 1));
            
            // Ajouter de l'espace autour du panel et une ligne
            if(verif == "supprimer") {
                unPanel.setBorder(new CompoundBorder(
                        new EmptyBorder(10, 10, 10, 10), // Espace autour du panel
                        BorderFactory.createLineBorder(Color.RED, 1) // Ligne autour du panel
                ));
            }
            else {
	            unPanel.setBorder(new CompoundBorder(
	                    new EmptyBorder(10, 10, 10, 10), // Espace autour du panel
	                    BorderFactory.createLineBorder(Color.WHITE, 1) // Ligne autour du panel
	            ));
            }
            // Utilise un JLabel distinct pour chaque description
            JLabel titreConference = new JLabel("<html><body><br>&nbsp;&nbsp;Nom de la conférence : " + Modele.getConference(unePresentation.getId()).getTheme() + "<br>");
            Animateur unAnimateur = Modele.getAnimateurQuiAnime(unePresentation.getId());
            Salle uneSalle = Modele.getSalleAvecUnId(unePresentation.getIdSalle()); 
            String nomSalle ;
            String nomPrenomAnimateur ;
            if(unAnimateur != null) {
            	nomPrenomAnimateur = unAnimateur.getNom() + " " + unAnimateur.getPrenom() ;
            }
            else {
            	nomPrenomAnimateur = "non attribué" ;
            }
            if(uneSalle != null) {
            	nomSalle = uneSalle.getNom();
            }
            else {
            	nomSalle = "non attribué" ;
            }        
            JLabel descriptionLabel = new JLabel(
            			titreConference.getText() 
            			+ unePresentation.afficher()
            			+ "&nbsp;&nbsp;Animateur de la présentation : " + nomPrenomAnimateur + "<br>"
            			+ "&nbsp;&nbsp;Nom de la salle de la présentation : " + nomSalle + "&nbsp;&nbsp;<br><br></body></html>");
            unPanel.add(descriptionLabel);
            
            
            
            
            // Ajoute un gestionnaire d'événements pour rendre le panel cliquable
            unPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	if(verif == null) {
                        fenetre.getContentPane().remove(presentation.getVuePresentation());
                        fenetre.repaint();
                        fenetre.revalidate();
                        new ControleurUnePresentation(fenetre, unePresentation.getId() , unePresentation.getIdConference());
                	}
                	else {
                		if(verif == "supprimer") {
                            int confirm = JOptionPane.showConfirmDialog(null, "\nVoulez-vous vraiment supprimer cette présentation ?", "Suppression", JOptionPane.YES_NO_OPTION);     
                            // Si l'utilisateur confirme
                            if (confirm == JOptionPane.YES_OPTION) {
                            	if(Modele.supprimerUnePresentation(unePresentation.getId())) {
                                    JOptionPane.showMessageDialog(null, "La présensation a bien été supprimé.");
                                    ArrayList<Presentation> lesPresentations = Modele.getToutesLesPresentations();
                                    if(lesPresentations.size() != 0) {
	                                    fenetre.getContentPane().removeAll();
	                                    fenetre.repaint();
	                                    fenetre.revalidate();
	                                    new ControleurPresentation(fenetre , verif);
                                    }  
                                    else {
	                                    fenetre.getContentPane().removeAll();
	                                    fenetre.repaint();
	                                    fenetre.revalidate();
	                                    new ControleurSecretaire(fenetre);
                                    }                                    
                            	}
                            	else {
                                    JOptionPane.showMessageDialog(null, "Un problème avec la suppression de la présentation a été detecté.");
                            	}
                            }
                            else {
                                // L'utilisateur ne confirme pas la fermeture
                                // Ne rien faire
                            }
                		}
                	}
                }
            });

            panelPrincipal.add(unPanel);
        }

	    // Ajoute le panel principal à la fenêtre
	    this.presentation.getVuePresentation().add(panelPrincipal);
	    this.fenetre.add(this.presentation.getVuePresentation());

	    // Met à jour et valide la fenêtre après avoir ajouté tous les composants
	    this.fenetre.getContentPane().repaint();
	    this.fenetre.getContentPane().validate();
	}
}
