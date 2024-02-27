package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Modele.Animateur;
import Modele.Modele;
import Modele.Personne;
import Modele.Salle;
import Vue.VueUnePresentation;

public class ControleurUnePresentation implements ActionListener {
	
	private JFrame fenetre ;
	private VueUnePresentation vuePresentation ;
	private int idPresentation ;
	private int idConference ;
	
	public ControleurUnePresentation(JFrame uneFenetre , int unIdPresentation , int unIdConference) {
		
		this.fenetre = uneFenetre ;
		this.vuePresentation = new VueUnePresentation();
		this.idPresentation = unIdPresentation ;
		this.idConference = unIdConference ;
		
		this.vuePresentation.getJcomboboxAnnee().addActionListener(this);
		this.vuePresentation.getJcomboboxMois().addActionListener(this);
		this.vuePresentation.getJcomboboxJour().addActionListener(this);
		this.vuePresentation.getJcomboboxHeureHoraire().addActionListener(this);
		this.vuePresentation.getJcomboboxMinuteHoraire().addActionListener(this);
		this.vuePresentation.getJcomboboxHeureDureePrevue().addActionListener(this);
		this.vuePresentation.getJcomboboxMinuteDureePrevue().addActionListener(this);
		this.vuePresentation.getJComboBoxAnimateur().addActionListener(this);
		this.vuePresentation.getJcomboboxSalle().addActionListener(this);
		if(this.idPresentation == 0) {
			this.vuePresentation.getValiderPresentation().setVisible(false);
			this.vuePresentation.getAjouterPresentation().setVisible(true);
			this.vuePresentation.getAjouterPresentation().addActionListener(this);
			this.vuePresentation.getSupprimerPresentation().setVisible(false);
		}
		else {
			this.vuePresentation.getValiderPresentation().setVisible(true);
			this.vuePresentation.getValiderPresentation().addActionListener(this);;
			this.vuePresentation.getAjouterPresentation().setVisible(false);
			this.vuePresentation.getSupprimerPresentation().setVisible(true);
			this.vuePresentation.getSupprimerPresentation().addActionListener(this);
		}		
	    Modele.getUnePresentationParId(unIdPresentation);
	    int nbPlacesDispoInt = Modele.getNbPlacesDispo(unIdPresentation);
	    String nbPlacesDispoStr = String.valueOf(nbPlacesDispoInt);
	    this.vuePresentation.getJtfNbPlacesDispo().setText(nbPlacesDispoStr);
	    
		ArrayList<Personne> lesPersonnesAnimateurs = Modele.getLesAnimateurs();
		for(Personne unePersonneAnimateur : lesPersonnesAnimateurs){
			this.vuePresentation.getJComboBoxAnimateur().addItem(unePersonneAnimateur.getNom() + " " + unePersonneAnimateur.getPrenom());
		}
		
		ArrayList<Salle> lesSalles = Modele.getLesSalles();
		for(Salle uneSalle : lesSalles){
			this.vuePresentation.getJcomboboxSalle().addItem(uneSalle.getNom());
		}
	    
        this.fenetre.add(this.vuePresentation.getVueUnePresentation());
        this.fenetre.repaint();
        this.fenetre.revalidate();
        
        this.vuePresentation.getJtfNbPlacesDispo().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                changementNbPlacesDispo();
            }

            public void removeUpdate(DocumentEvent e) {
            	changementNbPlacesDispo();
            }

            public void changedUpdate(DocumentEvent e) {

            }

            private void changementNbPlacesDispo() {
            	String nbPlacesString = vuePresentation.getJtfNbPlacesDispo().getText();
            	try {
            	    int nbPlaces = Integer.parseInt(nbPlacesString);
            	    	if(nbPlaces < 0) {
                	    	vuePresentation.getLblNbPlacesDispoInvalide().setText("Nombre en dessous de 0");
                	    	vuePresentation.getLblNbPlacesDispoInvalide().setForeground(Color.RED);
                	    	vuePresentation.getLblNbPlacesDispoInvalide().setVisible(true);
            	    	}
            	    	else {
            	    		if(nbPlaces > 100) {
                    	    	vuePresentation.getLblNbPlacesDispoInvalide().setText("Le nombre maximal de places est de 100.");
                    	    	vuePresentation.getLblNbPlacesDispoInvalide().setForeground(Color.RED);
                    	    	vuePresentation.getLblNbPlacesDispoInvalide().setVisible(true);
            	    		}
            	    		else {
                    	    	vuePresentation.getLblNbPlacesDispoInvalide().setText("✅");
                    	    	vuePresentation.getLblNbPlacesDispoInvalide().setForeground(Color.GREEN);
                    	    	vuePresentation.getLblNbPlacesDispoInvalide().setVisible(true);
            	    		}
            	    	}
            	} catch (NumberFormatException e) {
            	    // La conversion a échoué, la chaîne n'est pas un nombre valide
            		vuePresentation.getLblNbPlacesDispoInvalide().setText("Veuillez entrer un nombre valide");
        	    	vuePresentation.getLblNbPlacesDispoInvalide().setForeground(Color.RED);
        	    	vuePresentation.getLblNbPlacesDispoInvalide().setVisible(true);
            		
            	}
            }
        });
        
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// Si le ComboBox annee du panel Inscription est appuyé , récupération de la valeur Année
		if (e.getSource() == this.vuePresentation.getJcomboboxAnnee()) {
		    String anneeSelectionne = (String) this.vuePresentation.getJcomboboxAnnee().getSelectedItem();
		    int annee = Integer.parseInt(anneeSelectionne);
		    // Si l'Année est différente de l'année actuelle , mois avec 31 jours et Année avec 12 mois
		    if (annee != this.vuePresentation.getAnneeAujourdhui()) {
		        String[] jourString = new String[31];
		        for (int i = 1; i <= 31; i++) {
		            jourString[i - 1] = Integer.toString(i);
		        }
		        this.vuePresentation.getJcomboboxJour().setModel(new DefaultComboBoxModel<>(jourString));
		        String[] moisString = new String[12];
		        for (int i = 1; i <= 12; i++) {
		            moisString[i - 1] = Integer.toString(i);
		        }
		        this.vuePresentation.getJcomboboxMois().setModel(new DefaultComboBoxModel<>(moisString));

		        this.vuePresentation.getVueUnePresentation().revalidate();
		    } 
		    else {
		        // Année �gale � l'Année actuelle , Année avec le nombre actuel de mois
		        String[] moisString = new String[this.vuePresentation.getMoisAujourdhui()];
		        for (int i = 1; i <= this.vuePresentation.getMoisAujourdhui(); i++) {
		            moisString[i - 1] = Integer.toString(i);
		        }
		        this.vuePresentation.getJcomboboxMois().setModel(new DefaultComboBoxModel<>(moisString));
		        this.vuePresentation.getVueUnePresentation().repaint();
		    }
		}
		// Si le ComboBox mois du panel Inscription est appuyé , récupération des valeurs Année et mois
		if (e.getSource() == this.vuePresentation.getJcomboboxMois()) {
		    String anneeSelectionne = (String) this.vuePresentation.getJcomboboxAnnee().getSelectedItem();
		    String moisSelectionne = (String) this.vuePresentation.getJcomboboxMois().getSelectedItem();
		    int annee = Integer.parseInt(anneeSelectionne);
		    int mois = Integer.parseInt(moisSelectionne);
		    // Si l'année est égal à l'Année actuelle
		    if (annee == this.vuePresentation.getAnneeAujourdhui()) {
		    	// Si le mois est inf�rieur au mois actuelle
		        if (mois < this.vuePresentation.getMoisAujourdhui()) {
		        	// Si le mois est avril , juin , septembre ou novembre , mois avec 30 jours
		            if (mois == 4 || mois == 6 || mois == 9 || mois == 11) {
		                String[] jourString = new String[30];
		                for (int i = 1; i <= 30; i++) {
		                    jourString[i - 1] = Integer.toString(i);
		                }
		                this.vuePresentation.getJcomboboxJour().setModel(new DefaultComboBoxModel<>(jourString));
		                // Sinon si le mois est février
		            } else if (mois == 2) {
		            	// Si c'est une Année bissextile , février avec 29 jours
		                if ((annee % 4 == 0 && annee % 100 != 0) || (annee % 400 == 0)) {
		                    String[] jourString = new String[29];
		                    for (int i = 1; i <= 29; i++) {
		                        jourString[i - 1] = Integer.toString(i);
		                    }
		                    this.vuePresentation.getJcomboboxJour().setModel(new DefaultComboBoxModel<>(jourString));
		                } 
		                else {
		                    // Année non bissextile, février avec 28 jours
		                    String[] jourString = new String[28];
		                    for (int i = 1; i <= 28; i++) {
		                        jourString[i - 1] = Integer.toString(i);
		                    }
		                    this.vuePresentation.getJcomboboxJour().setModel(new DefaultComboBoxModel<>(jourString));
		                }
		            } else {
		                // Mois avec 31 jours
		                String[] jourString = new String[31];
		                for (int i = 1; i <= 31; i++) {
		                    jourString[i - 1] = Integer.toString(i);
		                }
		                this.vuePresentation.getJcomboboxJour().setModel(new DefaultComboBoxModel<>(jourString));
		            }
		            // Sinon si le mois est �gal au mois actuelle , jours jusqu'� au jour actuelle
		        } else if (mois == this.vuePresentation.getMoisAujourdhui()) {
		            String[] jourString = new String[this.vuePresentation.getJourAujourdhui()];
		            for (int i = 1; i <= this.vuePresentation.getJourAujourdhui(); i++) {
		                jourString[i - 1] = Integer.toString(i);
		            }
		            this.vuePresentation.getJcomboboxJour().setModel(new DefaultComboBoxModel<>(jourString));
		        }
		    } else {
		        // Année différente de l'Année actuelle
		    	// Si le mois est avril , juin , septembre ou novembre , mois avec 30 jours
		        if (mois == 4 || mois == 6 || mois == 9 || mois == 11) {
		            String[] jourString = new String[30];
		            for (int i = 1; i <= 30; i++) {
		                jourString[i - 1] = Integer.toString(i);
		            }
		            this.vuePresentation.getJcomboboxJour().setModel(new DefaultComboBoxModel<>(jourString));
		            // Si le mois est février
		        } else if (mois == 2) {
		        	// Si c'est une Année bissextile , février avec 29 jours
		            if ((annee % 4 == 0 && annee % 100 != 0) || (annee % 400 == 0)) {
		                String[] jourString = new String[29];
		                for (int i = 1; i <= 29; i++) {
		                    jourString[i - 1] = Integer.toString(i);
		                }
		                this.vuePresentation.getJcomboboxJour().setModel(new DefaultComboBoxModel<>(jourString));
		            } else {
		                // Année non bissextile, février avec 28 jours
		                String[] jourString = new String[28];
		                for (int i = 1; i <= 28; i++) {
		                    jourString[i - 1] = Integer.toString(i);
		                }
		                this.vuePresentation.getJcomboboxJour().setModel(new DefaultComboBoxModel<>(jourString));
		            }
		        } else {
		            // Mois avec 31 jours
		            String[] jourString = new String[31];
		            for (int i = 1; i <= 31; i++) {
		                jourString[i - 1] = Integer.toString(i);
		            }
		            this.vuePresentation.getJcomboboxJour().setModel(new DefaultComboBoxModel<>(jourString));
		        }
		    }
		    this.vuePresentation.getVueUnePresentation().revalidate();
		}
		// Vérifie si l'événement provient de la combobox "Heure de l'Horaire"
		if (e.getSource() == this.vuePresentation.getJcomboboxHeureHoraire()) {
		    // Récupère l'heure sélectionnée en tant que chaîne de caractères
		    String heureSelectionne = (String) this.vuePresentation.getJcomboboxHeureHoraire().getSelectedItem();
		    
		    // Convertit la chaîne de caractères en entier
		    int heure = Integer.parseInt(heureSelectionne);
		    
		    // Vérifie si l'heure sélectionnée est 21
		    if (heure == 21) {
		        // Supprime toutes les minutes existantes et ajoute "00"
		        this.vuePresentation.getJcomboboxMinuteHoraire().removeAllItems();
		        this.vuePresentation.getJcomboboxMinuteHoraire().addItem("00");
		    } else {
		        // Si l'heure n'est pas 21, configure les minutes de 0 à 55 par intervalles de 5 minutes
		        this.vuePresentation.getJcomboboxMinuteHoraire().removeAllItems();
		        for (int i = 0; i <= 55; i += 5) {
		            this.vuePresentation.getJcomboboxMinuteHoraire().addItem(String.format("%02d", i));
		        }
		    }
		    
		    // Revalide la vue de la présentation
		    this.vuePresentation.getVueUnePresentation().revalidate();
		}
		// Vérifie si l'événement provient de la combobox "Heure de la Durée Prévue"
		if (e.getSource() == this.vuePresentation.getJcomboboxHeureDureePrevue()) {
		    // Récupère l'heure sélectionnée en tant que chaîne de caractères
		    String heureSelectionne = (String) this.vuePresentation.getJcomboboxHeureDureePrevue().getSelectedItem();
		    
		    // Convertit la chaîne de caractères en entier
		    int heure = Integer.parseInt(heureSelectionne);
		    
		    // Vérifie si l'heure sélectionnée est 13
		    if (heure == 13) {
		        // Supprime toutes les minutes existantes et ajoute "00"
		        this.vuePresentation.getJcomboboxMinuteDureePrevue().removeAllItems();
		        this.vuePresentation.getJcomboboxMinuteDureePrevue().addItem("00");
		    } else {
		        // Si l'heure n'est pas 13, configure les minutes de 0 à 55 par intervalles de 5 minutes
		        this.vuePresentation.getJcomboboxMinuteDureePrevue().removeAllItems();
		        for (int i = 0; i <= 55; i += 5) {
		            this.vuePresentation.getJcomboboxMinuteDureePrevue().addItem(String.format("%02d", i));
		        }
		    }
		    
		    // Revalide la vue de la présentation
		    this.vuePresentation.getVueUnePresentation().revalidate();
		}
		// Vérifie si l'événement provient du bouton "Valider Présentation"
		if (e.getSource() == this.vuePresentation.getValiderPresentation()) {
		    // Récupère le nombre de places disponibles
		    int nbPlacesDispo = Modele.formaterEnEntier(this.vuePresentation.getJtfNbPlacesDispo().getText());

		    // Vérifie si le nombre de places disponibles est supérieur à 100
		    if (nbPlacesDispo > 100) {
		        JOptionPane.showMessageDialog(this.fenetre, "Le nombre maximal de places est de 100.");
		    } else {
		        // Vérifie si le nombre de places disponibles est invalide (négatif)
		        if (nbPlacesDispo < 0) {
		            JOptionPane.showMessageDialog(this.fenetre, "Le nombre est invalide.");
		        } else {
		            // Formate la date, l'heure, et la durée de la présentation
		            String date = Modele.formaterEnDate(this.vuePresentation.getJcomboboxJour().getSelectedItem(),
		                                                this.vuePresentation.getJcomboboxMois().getSelectedItem(),
		                                                this.vuePresentation.getJcomboboxAnnee().getSelectedItem());
		            Time timeHoraire = Modele.formaterEnTime(this.vuePresentation.getJcomboboxHeureHoraire().getSelectedItem(),
		                                                     this.vuePresentation.getJcomboboxMinuteHoraire().getSelectedItem());
		            Time timeDureePrevue = Modele.formaterEnTime(this.vuePresentation.getJcomboboxHeureDureePrevue().getSelectedItem(),
		                                                         this.vuePresentation.getJcomboboxMinuteDureePrevue().getSelectedItem());

		            // Vérifie si une présentation existe déjà à la même heure
		            if (Modele.existePresentationALaMemeHeure(date, timeHoraire, timeDureePrevue)) {
		                JOptionPane.showMessageDialog(null, "Une présentation existe déjà à la même heure.");
		            } else {
		                // Obtient l'ID de l'animateur et de la salle
		                String animateurStr = this.vuePresentation.getJComboBoxAnimateur().getSelectedItem().toString();
		                String[] parts = animateurStr.split(" ");
		                String nom = parts[0];
		                String prenom = parts[1];
		                Animateur unAnimateur = Modele.getAnimateurAvecNomPrenom(nom, prenom);
		                int idSalle = Modele.getIdSalleAvecNom(this.vuePresentation.getJcomboboxSalle().getSelectedItem().toString());

		                // Construit le message de confirmation
		                String message = "Date de la présentation : " + date + "\nNombre de places disponibles : " + nbPlacesDispo
		                        + "\nHoraire de la présentation : " + Modele.formaterEnTimeAffichage(timeHoraire)
		                        + "\nDurée prévue de la présentation : " + Modele.formaterEnTimeAffichage(timeDureePrevue)
		                        + "\nAnimateur de la salle : " + animateurStr
		                        + "\nSalle de la présentation : " + this.vuePresentation.getJcomboboxSalle().getSelectedItem().toString();

		                // Affiche une boîte de dialogue de confirmation
		                int confirm = JOptionPane.showConfirmDialog(this.fenetre, message + "\n\nVérifiez que vos informations sont valides.", "Confirmation", JOptionPane.YES_NO_OPTION);

		                // Si l'utilisateur confirme, met à jour la présentation
		                if (confirm == JOptionPane.YES_OPTION) {
		                    if (Modele.updateUnePresentation(date, nbPlacesDispo, timeHoraire, timeDureePrevue, idSalle, this.idPresentation, unAnimateur.getId())) {
		                        JOptionPane.showMessageDialog(this.fenetre, "La modification a bien été prise en compte.");

		                        // Supprime la vue de la présentation actuelle et actualise l'interface
		                        this.fenetre.remove(this.vuePresentation.getVueUnePresentation());
		                        this.fenetre.repaint();
		                        this.fenetre.revalidate();

		                        // Charge à nouveau les présentations
		                        String verification = null;
		                        new ControleurPresentation(this.fenetre, verification);
		                    } else {
		                        JOptionPane.showMessageDialog(this.fenetre, "Un problème est survenu. \nVeuillez réessayer.");
		                    }
		                }
		            }
		        }
		    }
		}
		// Vérifie si l'événement provient du bouton "Supprimer Présentation"
		if (e.getSource() == this.vuePresentation.getSupprimerPresentation()) {
		    // Affiche une boîte de dialogue de confirmation pour la suppression
		    int confirm = JOptionPane.showConfirmDialog(this.fenetre, "Voulez-vous vraiment supprimer cette présentation ?", "Confirmation", JOptionPane.YES_NO_OPTION);

		    // Si l'utilisateur confirme la suppression
		    if (confirm == JOptionPane.YES_OPTION) {
		        // Tente de supprimer la présentation avec l'ID correspondant
		        if (Modele.supprimerUnePresentation(this.idPresentation)) {
		            // Affiche un message de confirmation
		            JOptionPane.showMessageDialog(this.fenetre, "La suppression a bien été effectuée.");

		            // Supprime la vue de la présentation actuelle et actualise l'interface
		            this.fenetre.remove(this.vuePresentation.getVueUnePresentation());
		            this.fenetre.repaint();
		            this.fenetre.revalidate();

		            // Navigue vers la vue du secrétaire
		            new ControleurSecretaire(this.fenetre);
		        } else {
		            // Affiche un message d'erreur en cas de problème lors de la suppression
		            JOptionPane.showMessageDialog(this.fenetre, "Un problème est survenu. \nVeuillez réessayer.");
		        }
		    }
		}
		// Vérifie si l'événement provient du bouton "Ajouter Présentation"
		if (e.getSource() == this.vuePresentation.getAjouterPresentation()) {
		    // Récupère le nombre de places disponibles
		    int nbPlacesDispo = Modele.formaterEnEntier(this.vuePresentation.getJtfNbPlacesDispo().getText());

		    // Vérifie si le nombre de places disponibles est supérieur à 100
		    if (nbPlacesDispo > 100) {
		        JOptionPane.showMessageDialog(this.fenetre, "Le nombre maximal de places est de 100.");
		    } else {
		        // Vérifie si le nombre de places disponibles est invalide (négatif)
		        if (nbPlacesDispo < 0) {
		            JOptionPane.showMessageDialog(this.fenetre, "Le nombre est invalide.");
		        } else {
		            // Formate la date, l'heure, et la durée de la présentation
		            String date = Modele.formaterEnDate(this.vuePresentation.getJcomboboxJour().getSelectedItem(),
		                                                this.vuePresentation.getJcomboboxMois().getSelectedItem(),
		                                                this.vuePresentation.getJcomboboxAnnee().getSelectedItem());
		            Time timeHoraire = Modele.formaterEnTime(this.vuePresentation.getJcomboboxHeureHoraire().getSelectedItem(),
		                                                     this.vuePresentation.getJcomboboxMinuteHoraire().getSelectedItem());
		            Time timeDureePrevue = Modele.formaterEnTime(this.vuePresentation.getJcomboboxHeureDureePrevue().getSelectedItem(),
		                                                         this.vuePresentation.getJcomboboxMinuteDureePrevue().getSelectedItem());

		            // Vérifie si une présentation existe déjà à la même heure
		            if (Modele.existePresentationALaMemeHeure(date, timeHoraire, timeDureePrevue)) {
		                JOptionPane.showMessageDialog(null, "Une présentation existe déjà à la même heure.");
		            } else {
		                // Obtient l'ID de l'animateur et de la salle
		                String animateurStr = this.vuePresentation.getJComboBoxAnimateur().getSelectedItem().toString();
		                String[] parts = animateurStr.split(" ");
		                String nom = parts[0];
		                String prenom = parts[1];
		                Animateur unAnimateur = Modele.getAnimateurAvecNomPrenom(nom, prenom);
		                int idSalle = Modele.getIdSalleAvecNom(this.vuePresentation.getJcomboboxSalle().getSelectedItem().toString());

		                // Construit le message de confirmation
		                String message = "Date de la présentation : " + date + "\nNombre de places disponibles : " + nbPlacesDispo
		                        + "\nHoraire de la présentation : " + Modele.formaterEnTimeAffichage(timeHoraire)
		                        + "\nDurée prévue de la présentation : " + Modele.formaterEnTimeAffichage(timeDureePrevue)
		                        + "\nAnimateur de la salle : " + animateurStr
		                        + "\nSalle de la présentation : " + this.vuePresentation.getJcomboboxSalle().getSelectedItem().toString();

		                // Affiche une boîte de dialogue de confirmation
		                int confirm = JOptionPane.showConfirmDialog(this.fenetre, message + "\n\nVérifiez que vos informations sont valides.", "Confirmation", JOptionPane.YES_NO_OPTION);

		                // Si l'utilisateur confirme, ajoute la présentation
		                if (confirm == JOptionPane.YES_OPTION) {
		                    if (Modele.ajouterUnePresentation(date, nbPlacesDispo, timeHoraire, timeDureePrevue, idSalle, this.idConference, unAnimateur.getId())) {
		                        JOptionPane.showMessageDialog(this.fenetre, "La nouvelle présentation a bien été ajoutée.");

		                        // Supprime la vue de la présentation actuelle et actualise l'interface
		                        this.fenetre.remove(this.vuePresentation.getVueUnePresentation());
		                        this.fenetre.repaint();
		                        this.fenetre.revalidate();

		                        new ControleurSecretaire(this.fenetre);
		                    } else {
		                        JOptionPane.showMessageDialog(this.fenetre, "Un problème est survenu. \nVeuillez réessayer.");
		                    }
		                }
		            }
		        }
		    }
		}
	}
}
