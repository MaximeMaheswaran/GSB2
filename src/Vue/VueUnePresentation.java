package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueUnePresentation extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelDate ;
	private JPanel panelDate2 ;
	
	private JLabel lblJour ;
	private JLabel lblMois ;
	private JLabel lblAnnee ;
	private JComboBox<String> jcomboboxJour ;
	private JComboBox<String> jcomboboxMois ;
	private JComboBox<String> jcomboboxAnnee ;
	private int anneeAujourdhui ;
	private int moisAujourdhui ;
	private int jourAujourdhui ;
	
	private JPanel panelNbPlacesDispo ;
	private JPanel panelNbPlacesDispo2 ;
	
	private JLabel lblDate ;
	private JLabel lblNbPlacesDispo ;
	private JLabel lblNbPlacesDispoInvalide ;
	private JTextField jtfNbPlacesDispo ;
	
	private JLabel lblHoraire ;
	private JPanel panelHoraire ;
	private JPanel panelHoraire2 ;
	private JComboBox<String> jcomboboxHeureHoraire ;
	private JComboBox<String> jcomboboxMinuteHoraire ;
	private JLabel lblHeureHoraire ;
	private JLabel lblMinuteHoraire ;
	
	private JLabel lblDureePrevue ;
	private JPanel panelDureePrevue ;
	private JPanel panelDureePrevue2 ;
	private JComboBox<String> jcomboboxHeureDureePrevue ;
	private JComboBox<String> jcomboboxMinuteDureePrevue ;
	private JLabel lblHeureDureePrevue ;
	private JLabel lblMinuteDureePrevue ;

	private JPanel panelAnimateur ;
	private JPanel panelAnimateur2 ;
	private JLabel lblAnimateur ; 
	private JLabel lblTitreAnimateur ;
	private JComboBox<String> jcomboboxAnimateur ;
	
	private JPanel panelSalle ;
	private JPanel panelSalle2 ;
	private JLabel lblSalle ; 
	private JLabel lblTitreSalle ;
	private JComboBox<String> jcomboboxSalle ;
	
	private JPanel panelBouton;
	private JButton ajouterPresentation ;
	private JButton validerPresentation ;
	private JButton supprimerPresentation ;
	
	private Font font ;
	private Font titre ;
	
	public VueUnePresentation() {
		
		this.font = new Font("Liberation Sans", Font.BOLD, 13);
		this.titre = new Font("Liberation Sans", Font.BOLD, 17);
		this.setBackground(new Color(119 , 170 , 221));
		
		this.anneeAujourdhui = LocalDate.now().getYear();
		this.moisAujourdhui = LocalDate.now().getMonthValue();
		this.jourAujourdhui = LocalDate.now().getDayOfMonth();
		this.jcomboboxJour = new JComboBox<>();
		this.jcomboboxMois = new JComboBox<>();
		this.jcomboboxAnnee = new JComboBox<>();		
		this.jcomboboxHeureHoraire = new JComboBox<>();
		this.jcomboboxMinuteHoraire = new JComboBox<>();
		this.jcomboboxHeureDureePrevue = new JComboBox<>();
		this.jcomboboxMinuteDureePrevue = new JComboBox<>();
		this.jcomboboxAnimateur = new JComboBox<>();
		this.jcomboboxSalle = new JComboBox<>();
		this.panelDate = new JPanel();
		this.panelDate2 = new JPanel();
		this.panelNbPlacesDispo = new JPanel();
		this.panelNbPlacesDispo2 = new JPanel();
		this.panelHoraire = new JPanel();
		this.panelHoraire2 = new JPanel();
		this.panelDureePrevue = new JPanel();
		this.panelDureePrevue2 = new JPanel();
		this.panelAnimateur = new JPanel();
		this.panelAnimateur2 = new JPanel();
		this.panelSalle = new JPanel();
		this.panelSalle2 = new JPanel();
		this.lblNbPlacesDispoInvalide = new JLabel();
		this.jtfNbPlacesDispo = new JTextField(4);
		this.panelBouton = new JPanel();
		this.lblHeureHoraire = new JLabel(" Heure : ");
		this.lblMinuteHoraire = new JLabel(" Minute : ");
		this.lblHeureDureePrevue = new JLabel(" Heure : ");
		this.lblMinuteDureePrevue = new JLabel(" Minute : ");
		this.lblTitreAnimateur = new JLabel(" Animateur ");
		this.lblTitreSalle = new JLabel(" Salle ");
		this.lblAnimateur = new JLabel(" Nom et prénom de l'animateur : ");
		this.lblSalle = new JLabel(" Le nom de la salle : ");
		this.lblAnnee = new JLabel("Annee : ");
		this.lblMois = new JLabel("Mois : ");
		this.lblJour = new JLabel("Jour : ");
		this.lblDate = new JLabel(" Date de la présentation ");
		this.lblNbPlacesDispo = new JLabel("Nombre de places disponibles ");
		this.lblHoraire = new JLabel(" Horaire de la présentation ");
		this.lblDureePrevue = new JLabel(" Durée prévue de la présentation ");
		this.ajouterPresentation = new JButton(" Ajouter ");
		this.validerPresentation = new JButton(" Modifier ");
		this.supprimerPresentation = new JButton(" Supprimer ");
		
		this.setLayout(new GridLayout(0 , 1));
		this.panelSalle2.setLayout(new FlowLayout());
		this.panelDate.setLayout(new BorderLayout());	
		this.panelDate2.setLayout(new FlowLayout());
		this.panelNbPlacesDispo.setLayout(new BorderLayout());
		this.panelNbPlacesDispo2.setLayout(new FlowLayout());
		this.panelHoraire.setLayout(new BorderLayout());
		this.panelHoraire2.setLayout(new FlowLayout());
		this.panelDureePrevue.setLayout(new BorderLayout());
		this.panelDureePrevue2.setLayout(new FlowLayout());
		this.panelAnimateur.setLayout(new BorderLayout());
		this.panelAnimateur2.setLayout(new FlowLayout());
		this.panelSalle.setLayout(new BorderLayout());
		
        // Récupérer la date actuelle
        LocalDate dateActuelle = LocalDate.now();

        // Ajouter les jours, mois et années dans les JComboBox
        for (int i = 1; i <= 31; i++) {
            this.jcomboboxJour.addItem(String.format("%02d", i));
        }

        for (int i = 1; i <= 12; i++) {
        	this.jcomboboxMois.addItem(String.format("%02d", i));
        }

        int anneeActuelle = dateActuelle.getYear();
        for (int i = anneeActuelle; i <= anneeActuelle + 1; i++) {
        	this.jcomboboxAnnee.addItem(String.valueOf(i));
        }
        for(int i = 8 ; i <= 21 ; i++) {
        	this.jcomboboxHeureHoraire.addItem(String.format("%02d", i));
        }
        for(int i = 0 ; i <= 55 ; i+=5) {
        	this.jcomboboxMinuteHoraire.addItem(String.format("%02d", i));
        }
        for(int i = 1 ; i <= 13 ; i++) {
        	this.jcomboboxHeureDureePrevue.addItem(String.format("%02d", i));
        }
        for(int i = 0 ; i <= 55 ; i+=5) {
        	this.jcomboboxMinuteDureePrevue.addItem(String.format("%02d", i));
        }
		this.lblAnnee.setFont(this.font);
		this.lblMois.setFont(this.font);
		this.lblJour.setFont(this.font);
		this.lblHeureHoraire.setFont(this.font);
		this.lblMinuteHoraire.setFont(this.font);
		this.lblHeureDureePrevue.setFont(this.font);
		this.lblMinuteDureePrevue.setFont(this.font);
		this.lblTitreAnimateur.setFont(this.titre);
		this.lblTitreSalle.setFont(this.titre);
		this.lblAnimateur.setFont(this.font);
		this.lblSalle.setFont(this.font);
		this.lblDate.setFont(this.titre);
		this.lblNbPlacesDispo.setFont(this.titre);
		this.lblHoraire.setFont(this.titre);
		this.lblDureePrevue.setFont(this.titre);
		
	    this.ajouterPresentation.setBorder(BorderFactory.createLineBorder(new Color(226 , 241 , 255), 5));
	    this.validerPresentation.setBorder(BorderFactory.createLineBorder(new Color(226 , 241 , 255), 5));
	    this.supprimerPresentation.setBorder(BorderFactory.createLineBorder(new Color(226 , 241 , 255), 5));
	    this.supprimerPresentation.setBackground(new Color(226 , 241 , 255));
	    this.validerPresentation.setBackground(new Color(226 , 241 , 255));
	    this.ajouterPresentation.setBackground(new Color(226 , 241 , 255));
		this.panelSalle2.setBackground(new Color(119 , 170 , 221));
		this.panelSalle.setBackground(new Color(119 , 170 , 221));
		this.panelAnimateur2.setBackground(new Color(119 , 170 , 221));
		this.panelAnimateur.setBackground(new Color(119 , 170 , 221));
		this.panelDureePrevue2.setBackground(new Color(119 , 170 , 221));
		this.panelDate.setBackground(new Color(119 , 170 , 221));
		this.panelDate2.setBackground(new Color(119 , 170 , 221));
		this.panelNbPlacesDispo.setBackground(new Color(119 , 170 , 221));
		this.panelNbPlacesDispo2.setBackground(new Color(119 , 170 , 221));
		this.panelHoraire.setBackground(new Color(119 , 170 , 221));
		this.panelHoraire2.setBackground(new Color(119 , 170 , 221));
		this.panelDureePrevue.setBackground(new Color(119 , 170 , 221));
		this.lblDate.setBackground(new Color(119 , 170 , 221));
		this.lblNbPlacesDispo.setBackground(new Color(119 , 170 , 221));
		this.lblHoraire.setBackground(new Color(119 , 170 , 221));
		this.lblDureePrevue.setBackground(new Color(119 , 170 , 221));
		this.panelBouton.setBackground(new Color(119 , 170 , 221));
		
		
		this.panelDate2.add(this.lblAnnee);
		this.panelDate2.add(this.jcomboboxAnnee);
		this.panelDate2.add(this.lblMois);
		this.panelDate2.add(this.jcomboboxMois);
		this.panelDate2.add(this.lblJour);
		this.panelDate2.add(this.jcomboboxJour);
		this.panelHoraire2.add(this.lblHeureHoraire);
		this.panelHoraire2.add(this.jcomboboxHeureHoraire);
		this.panelHoraire2.add(this.lblMinuteHoraire);
		this.panelHoraire2.add(this.jcomboboxMinuteHoraire);
		this.panelDureePrevue2.add(this.lblHeureDureePrevue);
		this.panelDureePrevue2.add(this.jcomboboxHeureDureePrevue);
		this.panelDureePrevue2.add(this.lblMinuteDureePrevue);
		this.panelDureePrevue2.add(this.jcomboboxMinuteDureePrevue);
		this.panelBouton.add(this.ajouterPresentation);
		this.panelBouton.add(this.validerPresentation);
		this.panelBouton.add(this.supprimerPresentation);
		this.panelNbPlacesDispo2.add(this.lblNbPlacesDispo);
		this.panelNbPlacesDispo2.add(this.lblNbPlacesDispoInvalide);
		this.panelAnimateur2.add(this.lblAnimateur);
		this.panelAnimateur2.add(this.jcomboboxAnimateur);
		this.panelSalle2.add(this.lblSalle);
		this.panelSalle2.add(this.jcomboboxSalle);
		
		this.panelNbPlacesDispo.add(this.panelNbPlacesDispo2 , BorderLayout.WEST);
		this.panelDate.add(this.panelDate2 , BorderLayout.WEST);
		this.panelHoraire.add(this.panelHoraire2 , BorderLayout.WEST);
		this.panelDureePrevue.add(this.panelDureePrevue2 , BorderLayout.WEST);
		this.panelAnimateur.add(this.panelAnimateur2 , BorderLayout.WEST);
		this.panelSalle.add(this.panelSalle2 , BorderLayout.WEST);
		
		this.add(this.lblDate);
		this.add(this.panelDate);
		this.add(this.panelNbPlacesDispo);
		this.add(this.jtfNbPlacesDispo);
		this.add(this.lblHoraire);
		this.add(this.panelHoraire);
		this.add(this.lblDureePrevue);
		this.add(this.panelDureePrevue);
		this.add(this.lblTitreAnimateur);
		this.add(this.panelAnimateur);
		this.add(this.lblTitreSalle);
		this.add(this.panelSalle);
		this.add(this.panelBouton);
		
		this.lblNbPlacesDispoInvalide.setVisible(false);
	}

	public JPanel getVueUnePresentation() {
		return this;
	}

	public JLabel getLblNbPlacesDispo() {
		return lblNbPlacesDispo;
	}

	public void setLblNbPlacesDispo(JLabel lblNbPlacesDispo) {
		this.lblNbPlacesDispo = lblNbPlacesDispo;
	}

	public JTextField getJtfNbPlacesDispo() {
		return jtfNbPlacesDispo;
	}

	public void setJtfNbPlacesDispo(JTextField jtfNbPlacesDispo) {
		this.jtfNbPlacesDispo = jtfNbPlacesDispo;
	}

	public JPanel getPanelNbPlacesDispo() {
		return panelNbPlacesDispo;
	}

	public void setPanelNbPlacesDispo(JPanel panelNbPlacesDispo) {
		this.panelNbPlacesDispo = panelNbPlacesDispo;
	}

	public JLabel getLblNbPlacesDispoInvalide() {
		return lblNbPlacesDispoInvalide;
	}

	public void setLblNbPlacesDispoInvalide(JLabel lblNbPlacesDispoInvalide) {
		this.lblNbPlacesDispoInvalide = lblNbPlacesDispoInvalide;
	}

	public JComboBox<String> getJcomboboxJour() {
		return jcomboboxJour;
	}

	public void setJcomboboxJour(JComboBox<String> jcomboboxJour) {
		this.jcomboboxJour = jcomboboxJour;
	}

	public JComboBox<String> getJcomboboxMois() {
		return jcomboboxMois;
	}

	public void setJcomboboxMois(JComboBox<String> jcomboboxMois) {
		this.jcomboboxMois = jcomboboxMois;
	}

	public JComboBox<String> getJcomboboxAnnee() {
		return jcomboboxAnnee;
	}

	public void setJcomboboxAnnee(JComboBox<String> jcomboboxAnnee) {
		this.jcomboboxAnnee = jcomboboxAnnee;
	}

	public JComboBox<String> getJcomboboxHeureHoraire() {
		return jcomboboxHeureHoraire;
	}

	public void setJcomboboxHeure(JComboBox<String> jcomboboxHeure) {
		this.jcomboboxHeureHoraire = jcomboboxHeure;
	}

	public JComboBox<String> getJcomboboxMinuteHoraire() {
		return jcomboboxMinuteHoraire;
	}

	public void setJcomboboxMinuteHoraire(JComboBox<String> jcomboboxMinute) {
		this.jcomboboxMinuteHoraire = jcomboboxMinute;
	}

	public int getAnneeAujourdhui() {
		return anneeAujourdhui;
	}

	public void setAnneeAujourdhui(int anneeAujourdhui) {
		this.anneeAujourdhui = anneeAujourdhui;
	}

	public int getMoisAujourdhui() {
		return moisAujourdhui;
	}

	public void setMoisAujourdhui(int moisAujourdhui) {
		this.moisAujourdhui = moisAujourdhui;
	}

	public int getJourAujourdhui() {
		return jourAujourdhui;
	}

	public void setJourAujourdhui(int jourAujourdhui) {
		this.jourAujourdhui = jourAujourdhui;
	}

	public JComboBox<String> getJcomboboxHeureDureePrevue() {
		return jcomboboxHeureDureePrevue;
	}

	public void setJcomboboxHeureDureePrevue(JComboBox<String> jcomboboxHeureDureePrevue) {
		this.jcomboboxHeureDureePrevue = jcomboboxHeureDureePrevue;
	}

	public JComboBox<String> getJcomboboxMinuteDureePrevue() {
		return jcomboboxMinuteDureePrevue;
	}

	public void setJcomboboxMinuteDureePrevue(JComboBox<String> jcomboboxMinuteDureePrevue) {
		this.jcomboboxMinuteDureePrevue = jcomboboxMinuteDureePrevue;
	}

	public JButton getValiderPresentation() {
		return validerPresentation;
	}

	public void setValiderPresentation(JButton validerPresentation) {
		this.validerPresentation = validerPresentation;
	}

	public JComboBox<String> getJComboBoxAnimateur(){
		return this.jcomboboxAnimateur ;
	}
	
	public JLabel getLblTitreAnimateur() {
		return this.lblTitreAnimateur ;
	}

	public JComboBox<String> getJcomboboxSalle() {
		return jcomboboxSalle;
	}

	public void setJcomboboxSalle(JComboBox<String> jcomboboxSalle) {
		this.jcomboboxSalle = jcomboboxSalle;
	}

	public JButton getSupprimerPresentation() {
		return supprimerPresentation;
	}

	public void setSupprimerPresentation(JButton supprimerPresentation) {
		this.supprimerPresentation = supprimerPresentation;
	}

	public JButton getAjouterPresentation() {
		return ajouterPresentation;
	}

	public void setAjouterPresentation(JButton ajouterPresentation) {
		this.ajouterPresentation = ajouterPresentation;
	}	
	
	
	
	
}