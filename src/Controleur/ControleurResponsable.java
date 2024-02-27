
package Controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import Modele.Animateur;
import Modele.Conference;
import Modele.Presentation;
import Modele.Visiteur;
import Vue.VueMenuResponsable;
import Vue.VueResponsable;
import Modele.Modele ;

//Contrôleur responsable gérant les actions liées à l'interface responsable.

public class ControleurResponsable implements ActionListener {

	// Déclaration des attributs
	private JFrame fenetre;
	private VueResponsable responsable;
	private VueMenuResponsable menu;

	/**
	 * Constructeur de la classe ControleurResponsable.
	 * @param uneFenetre JFrame représentant la fenêtre de l'application.
	 */
	public ControleurResponsable(JFrame uneFenetre) {
		// Instanciation des attributs de la classe.
		this.fenetre = uneFenetre;
		this.menu = new VueMenuResponsable();

		// Ajout de la barre de menu à la fenêtre.
		this.fenetre.setJMenuBar(this.menu);

		// Instanciation de la vue responsable et ajout à la fenêtre.
		this.responsable = new VueResponsable();
		this.fenetre.getContentPane().add(responsable);

		// Redessine la fenêtre.
		this.fenetre.repaint();

		// Valide la mise en page de la fenêtre.
		this.fenetre.validate();

		// Ajout des écouteurs d'événements aux éléments de menu.
		this.menu.getAfficherCsv().addActionListener(this);
		this.menu.getAfficherJson().addActionListener(this);
		this.menu.getAfficherXml().addActionListener(this);
		this.menu.getStatistiques().addActionListener(this);
		this.menu.getDeconnexion().addActionListener(this);
	}


	/**
	 * Méthode appelée en réponse à des événements d'action, tels que le clic sur des éléments de menu.
	 * @param e ActionEvent généré lors de l'événement d'action.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// Action en fonction de la source de l'événement.

		// Traitement pour afficher les données au format CSV lorsque l'option "Afficher CSV" est sélectionnée.

		if (e.getSource() == this.menu.getAfficherCsv()) {
			// Supprime tous les composants du ContentPane.
			this.fenetre.getContentPane().removeAll();

			// Instancie une nouvelle vue responsable.
			this.responsable = new VueResponsable();
			this.responsable.setNewPanel();

			// Crée une instance de JScrollPane en utilisant VueResponsable comme composant de vue.
			JScrollPane scrollPane = new JScrollPane(responsable);

			// Récupère la liste des conférences depuis le modèle.
			ArrayList<Conference> listConference = Modele.getLesConferences();

			// Initialise un texte au format HTML.
			String text = "<html>";

			// Parcourt la liste des conférences.
			for (Conference c : listConference) {
				text = text + c.toCSV() + " ; ";

				// Récupère la liste des présentations pour chaque conférence.
				ArrayList<Presentation> listPresentation = Modele.getLesPresentations(c.getIdConf());

				// Parcourt la liste des présentations.
				for (Presentation p : listPresentation) {
					text = text + p.toCSVString() + " ; ";
					
					
					Animateur unAnimateur = Modele.getAnimateurQuiAnime(p.getId());
					text = text + unAnimateur.toCSVString() + " ; ";
					// Récupère la liste des visiteurs pour chaque présentation.
					ArrayList<Visiteur> listVisiteur = Modele.getVisiteurPresentation(p.getId());

					// Parcourt la liste des visiteurs.
					for (Visiteur v : listVisiteur) {
						text = text + v.toCSVString() + " ; ";
					}
				}
				text = text + "<br>";
			}

			// Initialise un nouveau JLabel avec le texte HTML.
			this.responsable.setNewJLabel(new JLabel(text));

			// Ajoute le JLabel à la vue responsable.
			this.responsable.setAddPanel();

			// Ajoute le JScrollPane au ContentPane de la fenêtre.
			this.fenetre.getContentPane().add(scrollPane);

			// Redessine la fenêtre.
			this.fenetre.repaint();

			// Valide la mise en page de la fenêtre.
			this.fenetre.revalidate();
		}


		// Traitement pour afficher les données au format JSON lorsque l'option "Afficher JSON" est sélectionnée.

		if (e.getSource() == this.menu.getAfficherJson()) {
			// Supprime tous les composants du ContentPane.
			this.fenetre.getContentPane().removeAll();

			// Instancie une nouvelle vue responsable.
			this.responsable = new VueResponsable();
			
			// Initialise un nouveau JPanel dans la vue responsable.
			this.responsable.setNewPanel();

			// Crée une instance de JScrollPane en utilisant VueResponsable comme composant de vue.
			JScrollPane scrollPane = new JScrollPane(responsable);

			// Récupère la liste des conférences depuis le modèle.
			ArrayList<Conference> listConference = Modele.getLesConferences();

			// Initialise un texte au format HTML.
			String text = "<html>";

			// Parcourt la liste des conférences.
			for (Conference c : listConference) {
				text = text + " { <br>";
				text = text + c.toJSON() + "<br>";

				// Récupère la liste des présentations pour chaque conférence.
				ArrayList<Presentation> listPresentation = Modele.getLesPresentations(c.getIdConf());

				// Parcourt la liste des présentations.
				for (Presentation p : listPresentation) {
					text = text + p.toJSONString() + "<br>";

					Animateur unAnimateur = Modele.getAnimateurQuiAnime(p.getId());
					text = text + unAnimateur.toJSONString() + " <br> ";
					// Récupère la liste des visiteurs pour chaque présentation.
					ArrayList<Visiteur> listVisiteur = Modele.getVisiteurPresentation(p.getId());

					// Parcourt la liste des visiteurs.
					for (Visiteur v : listVisiteur) {
						text = text + v.toJSONString() + "<br>";
					}
				}
				text = text + "}<br>";
			}

			// Initialise un nouveau JLabel avec le texte HTML.
			this.responsable.setNewJLabel(new JLabel(text));

			// Ajoute le JLabel à la vue responsable.
			this.responsable.setAddPanel();

			// Ajoute le JScrollPane au ContentPane de la fenêtre.
			this.fenetre.getContentPane().add(scrollPane);

			// Redessine la fenêtre.
			this.fenetre.repaint();

			// Valide la mise en page de la fenêtre.
			this.fenetre.revalidate();
		}


		// Traitement pour afficher les données au format XML lorsque l'option "Afficher XML" est sélectionnée.

		if (e.getSource() == this.menu.getAfficherXml()) {
			// Supprime tous les composants du ContentPane.
			this.fenetre.getContentPane().removeAll();

			// Instancie une nouvelle vue responsable.
			this.responsable = new VueResponsable();
			
			// Initialise un nouveau JPanel dans la vue responsable.
			this.responsable.setNewPanel();

			// Crée une instance de JScrollPane en utilisant VueResponsable comme composant de vue.
			JScrollPane scrollPane = new JScrollPane(responsable);

			// Récupère la liste des conférences depuis le modèle.
			ArrayList<Conference> listConference = Modele.getLesConferences();

			// Initialise un texte au format HTML.
			String text = "<html> &lt; Conferences &gt;<br>";

			// Parcourt la liste des conférences.
			for (Conference c : listConference) {
				text += "  &nbsp;&nbsp;&nbsp;&nbsp; &lt; Conference &gt; <br>";
				text += "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  " + c.toXML() + "<br>";

				// Récupère la liste des présentations pour chaque conférence.
				ArrayList<Presentation> listPresentation = Modele.getLesPresentations(c.getIdConf());

				// Parcourt la liste des présentations.
				for (Presentation p : listPresentation) {
					text += " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   &lt; Presentation &gt;<br>";
					text += "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;    " + p.toXMLString() + "<br>";

					Animateur unAnimateur = Modele.getAnimateurQuiAnime(p.getId());
					text += "" + unAnimateur.toXMLString()+ "<br>";
					// Récupère la liste des visiteurs pour chaque présentation.
					ArrayList<Visiteur> listVisiteur = Modele.getVisiteurPresentation(p.getId());

					text += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   &lt; Visiteurs &gt; <br>";

					// Parcourt la liste des visiteurs.
					for (Visiteur v : listVisiteur) {
						text += ""+ v.toXMLString() + "<br>";  
					}

					text += "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   &lt; /Visiteurs &gt; <br>";
					text += "  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &lt; /Presentation &gt; <br> ";
				}

				text += " &nbsp;&nbsp;&nbsp;&nbsp; &lt; /Conference &gt; <br> ";
			}

			text += " &lt; /Conferences &gt; </html>";

			// Initialise un nouveau JLabel avec le texte HTML.
			this.responsable.setNewJLabel(new JLabel(text));

			// Ajoute le JLabel à la vue responsable.
			this.responsable.setAddPanel();

			// Ajoute le JScrollPane au ContentPane de la fenêtre.
			this.fenetre.getContentPane().add(scrollPane);

			// Redessine la fenêtre.
			this.fenetre.repaint();

			// Valide la mise en page de la fenêtre.
			this.fenetre.revalidate();
		}


		// Traitement pour afficher les statistiques lorsque l'option "Statistiques" est sélectionnée.

		if (e.getSource() == this.menu.getStatistiques()) {
			// Supprime tous les composants du ContentPane.
			this.fenetre.getContentPane().removeAll();

			// Instancie une nouvelle vue responsable.
			this.responsable = new VueResponsable();

			// Initialise un nouveau JPanel dans la vue responsable.
			this.responsable.setNewPanel();

			// Récupère les statistiques depuis le modèle.
			int nbConference = Modele.getNbConference();
			int nbPresentation = Modele.getNbPresentation();
			int nbVisiteur = Modele.getNbVisiteur();
			String confPlusVisiter = Modele.getConferencePlusVisiter();
			String confMoinsVisiter = Modele.getConferenceMoinsVisiter();
			int nbVisiteurVisiter = Modele.getNbVisiteurVisiter();

			// Initialise le texte des statistiques au format HTML.
			String textStatistiques = "<html>Total de conférences enregistrées : " + nbConference
					+ " <br> Total de présentations enregistrées : "+ nbPresentation
					+ " <br> Total de visiteurs existants : " + nbVisiteur
					+ " <br> La conférence la plus visitée : " + confPlusVisiter
					+ " <br> La conférence la moins visitée : " + confMoinsVisiter
					+" <br> Taux de visiteurs actifs : " + (nbVisiteurVisiter * 100 / nbVisiteur) + "%";		

			// Initialise un nouveau JLabel avec le texte HTML des statistiques.
			this.responsable.setNewJLabel(new JLabel(textStatistiques));

			// Ajoute le JLabel contenant les statistiques à la vue responsable.
			this.responsable.setAddPanel();

			// Ajoute la vue responsable au ContentPane de la fenêtre.
			this.fenetre.getContentPane().add(responsable);

			// Redessine la fenêtre.
			this.fenetre.repaint();

			// Valide la mise en page de la fenêtre.
			this.fenetre.revalidate();
		}


		// Traitement pour gérer la déconnexion lors de la sélection de l'option "Déconnexion".

		if (e.getSource() == this.menu.getDeconnexion()) {
			// Affiche une boîte de dialogue de confirmation pour la déconnexion.
			int confirm = JOptionPane.showConfirmDialog(
					null, "\nSe déconnecter ?", "Déconnexion Responsable", JOptionPane.YES_NO_OPTION);

			// Si l'utilisateur confirme la déconnexion, effectue les actions correspondantes.
			if (confirm == JOptionPane.YES_OPTION) {
				// Affiche un message de déconnexion.
				JOptionPane.showMessageDialog(null, "Déconnecté de l'interface responsable.");

				// Supprime tous les composants du ContentPane.
				this.fenetre.getContentPane().removeAll();

				// Redessine la fenêtre.
				this.fenetre.repaint();

				// Valide la mise en page de la fenêtre.
				this.fenetre.revalidate();

				// Instancie un nouveau contrôleur de connexion pour revenir à l'écran de connexion.
				new ControleurConnexion(this.fenetre);
			} else {
				// L'utilisateur a annulé la déconnexion, aucune action nécessaire.
				// Ne rien faire.
			}
		}

	}




}
