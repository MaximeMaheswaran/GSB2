package Controleur;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Modele.Modele;
import Vue.VueAjouterConference;

public class ControleurAjouterConference implements ActionListener {
	
	//Declaration des attributs
	private JFrame fenetre ;
	private VueAjouterConference vueConference ;
	
	public ControleurAjouterConference(JFrame uneFenetre) {
		
		//recuperation de la Frame existant
		this.fenetre = uneFenetre ;
		//instantciation d'une panel 
		this.vueConference = new VueAjouterConference();
		
		//ajouter ActionListner pour ecouter le boutton
		this.vueConference.getBoutonValider().addActionListener(this);
		//??????????????
        this.vueConference.getUnJTextFieldConference().getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                changementPrenom();
            }

            public void removeUpdate(DocumentEvent e) {
                changementPrenom();
            }

            public void changedUpdate(DocumentEvent e) {

            }

            private void changementPrenom() {
                String nomConference = vueConference.getUnJTextFieldConference().getText();
                if (!Modele.verifNomConference(nomConference)) {
                	nomConference = vueConference.getUnJTextFieldConference().getText();
                	if(nomConference.length() == 0) {
                		vueConference.getUnNomConferenceInvalide().setForeground(Color.RED);
                		vueConference.getUnNomConferenceInvalide().setText("Veuillez saisir un thème de conférence.");
                		vueConference.getUnNomConferenceInvalide().setVisible(true);
                		vueConference.getUnJTextFieldConference().requestFocusInWindow();
                	}
                	else {
                		vueConference.getUnNomConferenceInvalide().setForeground(Color.RED);
                		vueConference.getUnNomConferenceInvalide().setText("Ce nom n'est pas valide.");
                		vueConference.getUnNomConferenceInvalide().setVisible(true);
	                    vueConference.getUnJTextFieldConference().requestFocusInWindow();
                	}
                } else {
                	vueConference.getUnNomConferenceInvalide().setText("✅");
                	vueConference.getUnNomConferenceInvalide().setForeground(Color.GREEN);
                }
            }
        });
		
		this.fenetre.getContentPane().add(this.vueConference.getVueAjouterConference());
		this.fenetre.repaint();
		this.fenetre.revalidate();
	}

	public void actionPerformed(ActionEvent e) {
		// Si le bouton valider est appuyé
		if(e.getSource() == this.vueConference.getBoutonValider()) {
			String nomConference = this.vueConference.getUnJTextFieldConference().getText();
			if(!Modele.verifNomConference(nomConference)) { // Si le nom de la conférence est incorrect
				JOptionPane.showMessageDialog(fenetre, "Le nom de la conférence est incorrect.");
				this.vueConference.getUnJTextFieldConference().requestFocusInWindow();
			}
			else { // Le nom de la conférence est correct	
		        int confirm = JOptionPane.showConfirmDialog(fenetre, "Ajouter une nouvelle conférence '"+nomConference+"' ?" , "Confirmation", JOptionPane.YES_NO_OPTION);
	            if (confirm == JOptionPane.YES_OPTION) {
	            	if(Modele.ajouterUneConference(nomConference)) {
                        JOptionPane.showMessageDialog(this.fenetre, "La nouvelle présentation a bien été ajoutée.");
                        // Supprime la vue de la présentation actuelle et actualise l'interface
                        this.fenetre.remove(this.vueConference.getVueAjouterConference());
                        this.fenetre.repaint();
                        this.fenetre.revalidate();
                        new ControleurSecretaire(this.fenetre);
	            	}
	            	else {
	            		JOptionPane.showMessageDialog(null, "Une erreur est survenue lors de l'ajout de la nouvele conférence.");
	            	}	            	
	            }
			}				
		}	
	}		
}

