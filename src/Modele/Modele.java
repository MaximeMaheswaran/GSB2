package Modele;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class Modele {

	private static Connection connexion ;
	private static ResultSet rs ;
	private static PreparedStatement preparedStatement ;

	/**
	 * Établir une connexion à la base de données et renvoie l'objet Connection.
	 * 
	 * Cette méthode utilise JDBC pour se connecter à la base de données MySQL.
	 *
	 * @return Une instance de l'objet Connection représentant la connexion à la base de données.
	 */
	public static Connection connexionBD() {
		try {
			// URL de connexion à la base de données MySQL
			String url = "jdbc:mysql://127.0.0.1/GSB3?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

			// Identifiants de connexion à la base de données (nom d'utilisateur et mot de passe)
			String utilisateur = "root";
			String motDePasse = "root";

			// Ã‰tablir la connexion à la base de données
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);

			connexion.createStatement();
		} catch (SQLException erreur) {
			// En cas d'erreur de connexion, afficher un message d'erreur
			JOptionPane.showMessageDialog(null, "Erreur lors de la connexion à la base de données : " + erreur.getMessage());
		}
		return connexion;
	}

	/**
	 * Ferme la connexion à la base de données.
	 *
	 * @return Une instance de l'objet Connection représentant la connexion à la base de données (peut être nulle en cas d'erreur).
	 */
	public static Connection deconnexionBD() {
		try {
			// Fermer la connexion à la base de données
			connexion.close();
		} catch (SQLException erreur) {
			// En cas d'erreur de déconnexion, afficher un message d'erreur
			JOptionPane.showMessageDialog(null, "Erreur lors de la déconnexion à la base de données : " + erreur.getMessage());
		}
		return connexion;
	}

	/**
	 * Vérifie si un utilisateur avec l'e-mail et le mot de passe fournis existe dans la base de données.
	 *
	 * @param unLogin L'e-mail de l'utilisateur à vérifier.
	 * @param unMdp Le mot de passe de l'utilisateur à vérifier.
	 * @return true si l'utilisateur existe, sinon false.
	 */
	public static boolean verification(String unLogin, String unMdp) {
		boolean rep = false;
		try {
			// Établir une connexion à la base de données
			connexionBD();

			// Hachage du mot de passe
			String mdp = hashSHA256(unMdp);

			// Préparer une requête SQL pour vérifier l'existence de l'utilisateur
			preparedStatement = connexion.prepareStatement("SELECT id, login, mdp FROM personne WHERE login = ? AND mdp = ?");
			preparedStatement.setString(1, unLogin);
			preparedStatement.setString(2, mdp);

			// exécuter la requête SQL
			ResultSet rs = preparedStatement.executeQuery();

			// Si une ligne est retournée, l'utilisateur existe
			if (rs.next()) {
				rep = true;
			}

			// Fermer le ResultSet
			rs.close();
		} catch (SQLException erreur) {
			// En cas d'erreur lors de la requête SQL, afficher un message d'erreur
			JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL verification : " + erreur.getMessage());
		} finally {
			// déconnecter de la base de données, quelle que soit la situation
			deconnexionBD();
		}
		return rep;
	}

	/**
	 * Vérifie si le login et le mot de passe fournis existent dans la base de données et correspondent à un administrateur.
	 *
	 * @param unLogin Le login de l'administrateur à vérifier.
	 * @param unMdp   Le mot de passe de l'administrateur à vérifier.
	 * @return true si l'administrateur existe, sinon false.
	 */
	public static boolean verificationAdmin(String unLogin, String unMdp) {
		boolean rep = false;
		try {
			// Établir une connexion à la base de données
			connexionBD();

			// Hachage du mot de passe
			String mdp = hashSHA256(unMdp);

			// Préparer une requête SQL pour vérifier l'existence de l'administrateur avec l'ID 1
			preparedStatement = connexion.prepareStatement("SELECT id, login, mdp FROM personne WHERE login = ? AND mdp = ? AND id = 1");
			preparedStatement.setString(1, unLogin);
			preparedStatement.setString(2, mdp);

			// Exécute la requête SQL
			ResultSet rs = preparedStatement.executeQuery();

			// Si une ligne est retournée, l'e-mail et le mot de passe correspondent à un compte administrateur
			if (rs.next()) {
				rep = true;
			}

			// Fermer le ResultSet
			rs.close();
		} catch (SQLException erreur) {
			// En cas d'erreur lors de la requête SQL, afficher un message d'erreur
			JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL verificationAdmin : " + erreur.getMessage());
		} finally {
			// Se déconnecter de la base de données, quelle que soit la situation
			deconnexionBD();
		}
		return rep;
	}

	/**
	 * Récupère le prénom associé à un login dans la base de données.
	 *
	 * @param unLogin Le login de la personne.
	 * @return Le prénom associé au login, ou null si le login n'est pas trouvé.
	 */
	public static String getPrenomVerif(String unLogin) {
	    String prenom = null;
	    
	    try {
	        // Établissement de la connexion à la base de données
	        connexionBD();

	        // Préparation et exécution de la requête SQL pour récupérer le prénom
	        preparedStatement = connexion.prepareStatement("SELECT prenom FROM personne WHERE login = ?");
	        preparedStatement.setString(1, unLogin);
	        ResultSet rs = preparedStatement.executeQuery();

	        // Si le résultat contient une ligne, récupération du prénom
	        if (rs.next()) {
	            prenom = rs.getString("prenom");
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getPrenomVerif : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }
	    
	    // Retour du prénom récupéré (ou null si le login n'est pas trouvé)
	    return prenom;
	}

	/**
	 * Vérifie l'existence d'une personne dans la base de données en utilisant le login.
	 *
	 * @param unLogin Le login de la personne à vérifier.
	 * @return true si la personne existe, sinon false.
	 */
	public static boolean verifPersonneViaLoginBoolean(String unLogin) {
	    boolean rep = false;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Préparation et exécution de la requête SQL pour vérifier l'existence de la personne
	        preparedStatement = connexion.prepareStatement("SELECT * FROM personne WHERE login = ?");
	        preparedStatement.setString(1, unLogin);
	        ResultSet rs = preparedStatement.executeQuery();

	        // Mise à jour de la valeur de rep en fonction de la présence ou l'absence de résultat
	        rep = rs.next();
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Une erreur est survenue dans la requête SQL verifPersonneViaLoginBoolean : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de la valeur de rep indiquant si la personne existe
	    return rep;
	}

	/**
	 * Vérifie si une chaîne de caractères (email) n'est pas vide.
	 *
	 * @param unEmail La chaîne de caractères à vérifier.
	 * @return true si la chaîne n'est pas vide, sinon false.
	 */
	public static boolean estPasVide(String unEmail) {
	    boolean nonVide = false; // Variable pour stocker le résultat de la vérification

	    // Vérification si la chaîne est vide
	    if (unEmail.isEmpty()) {
	        // Affichage d'un message d'erreur si la chaîne est vide
	        JOptionPane.showMessageDialog(null, "Veuillez remplir le champ Email.");
	    } else {
	        // La chaîne n'est pas vide, met à jour la valeur de nonVide à true
	        nonVide = true;
	    }

	    // Retourne true si la chaîne n'est pas vide, sinon false
	    return nonVide;
	}

	/**
	 * Vérifie si deux chaînes de caractères (email et mot de passe) ne sont pas vides.
	 *
	 * @param unEmail La chaîne de caractères représentant l'email à vérifier.
	 * @param unMdp La chaîne de caractères représentant le mot de passe à vérifier.
	 * @return true si les deux chaînes ne sont pas vides, sinon false.
	 */
	public static boolean estPasVide(String unEmail, String unMdp) {
	    boolean nonVide = false; // Variable pour stocker le résultat de la vérification

	    // Vérification si l'une des chaînes est vide
	    if (unEmail.isEmpty() || unMdp.isEmpty()) {
	        // Affichage d'un message d'erreur si l'une des chaînes est vide
	        JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs.");
	    } else {
	        // Les deux chaînes ne sont pas vides, met à jour la valeur de nonVide à true
	        nonVide = true;
	    }

	    // Retourne true si les deux chaînes ne sont pas vides, sinon false
	    return nonVide;
	}


	/**
	 * Change la casse du premier caractère d'une chaîne de caractères pour qu'il soit en majuscule suivi de caractères en minuscules.
	 *
	 * @param unNom La chaîne de caractères à traiter.
	 * @return La chaine de caractères avec le premier caractère en majuscule et les autres en minuscules.
	 */
	public static String changerPremiereLettre(String unString) {
		return unString.substring(0, 1).toUpperCase() + unString.substring(1).toLowerCase();
	}

	/**
	 * Ajoute un nombre spécifié de présentations pour une conférence donnée.
	 *
	 * @param idConference L'identifiant de la conférence pour laquelle ajouter les présentations.
	 * @param unNombre Le nombre de présentations à ajouter.
	 * @return true si l'ajout des présentations est réussi, sinon false.
	 */
	public static boolean ajouterNbPresentation(int idConference, int unNombre) {
	    boolean rep = false;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Boucle pour ajouter le nombre spécifié de présentations
	        for (int i = 0; i < unNombre; i++) {
	            preparedStatement = connexion.prepareStatement("INSERT INTO presentation (id, datee, nbPlacesDispo, horaire, dureePrevue, salle_id, conference_id) VALUES (null, null, null, null, null, null, " + idConference + ")");
	            int rowsAffected = preparedStatement.executeUpdate();

	            // Mise à jour de la valeur de rep en fonction du succès de l'ajout de la présentation
	            if (rowsAffected > 0) {
	                rep = true;
	            }
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL ajouterNbPresentation : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de la valeur de rep indiquant si l'ajout des présentations est réussi
	    return rep;
	}

	/**
	 * Vérifie si des présentations existent dans la base de données.
	 *
	 * @return true si des présentations existent, sinon false.
	 */
	public static boolean existePresentation() {
	    boolean rep = false;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Préparation et exécution de la requête SQL pour vérifier l'existence de présentations
	        preparedStatement = connexion.prepareStatement("SELECT * FROM presentation");
	        rs = preparedStatement.executeQuery();

	        // Mise à jour de la valeur de rep en fonction de la présence ou l'absence de résultats
	        if (rs.next()) {
	            rep = true;
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL existePresentation : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de la valeur de rep indiquant si des présentations existent
	    return rep;
	}

	/**
	 * Vérifie si des conférences existent dans la base de données.
	 *
	 * @return true si des conférences existent, sinon false.
	 */
	public static boolean existeConference() {
	    boolean rep = false;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Préparation et exécution de la requête SQL pour vérifier l'existence de conférences
	        preparedStatement = connexion.prepareStatement("SELECT * FROM conference");
	        rs = preparedStatement.executeQuery();

	        // Mise à jour de la valeur de rep en fonction de la présence ou l'absence de résultats
	        if (rs.next()) {
	            rep = true;
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL existeConference : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de la valeur de rep indiquant si des conférences existent
	    return rep;
	}

	/**
	 * Sélectionne toutes les présentations depuis la table "presentation" de la base de données.
	 *
	 * @return Une ArrayList d'objets Presentation représentant toutes les présentations disponibles.
	 */
	public static ArrayList<Presentation> getToutesLesPresentations() {
	    ArrayList<Presentation> lesPresentations = new ArrayList<>();
	    Presentation unePresentation;
	    int id;
	    Date date;
	    int nbPlacesDispo;
	    Time horaire;
	    Time dureePrevue;
	    int idSalle;
	    int idConference;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Préparation et exécution de la requête SQL pour sélectionner toutes les présentations
	        preparedStatement = connexion.prepareStatement("SELECT * FROM presentation");
	        rs = preparedStatement.executeQuery();

	        // Parcours des résultats et création des objets Presentation correspondants
	        while (rs.next()) {
	            id = rs.getInt("id");
	            date = rs.getDate("datee");
	            nbPlacesDispo = rs.getInt("nbPlacesDispo");
	            horaire = rs.getTime("horaire");
	            dureePrevue = rs.getTime("dureePrevue");
	            idSalle = rs.getInt("salle_id");
	            idConference = rs.getInt("conference_id");

	            unePresentation = new Presentation(id, date, nbPlacesDispo, horaire, dureePrevue, idSalle, idConference);
	            lesPresentations.add(unePresentation);
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getToutesLesPresentations : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de l'ArrayList contenant toutes les présentations
	    return lesPresentations;
	}

	/**
	 * Sélectionne une présentation à partir de son identifiant.
	 *
	 * @param unId L'identifiant de la présentation à sélectionner.
	 * @return Un objet Presentation représentant la présentation correspondante, ou null si non trouvée.
	 */
	public static Presentation getUnePresentationParId(int unId) {
	    Presentation unePresentation = null;
	    int id;
	    Date date;
	    int nbPlacesDispo;
	    Time horaire;
	    Time dureePrevue;
	    int idSalle;
	    int idConference;

	    // Requête SQL pour sélectionner une présentation par son identifiant
	    String requete = "SELECT * FROM presentation WHERE id = ?";
	    
	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Préparation et exécution de la requête SQL avec le paramètre unId
	        preparedStatement = connexion.prepareStatement(requete);
	        preparedStatement.setInt(1, unId);
	        rs = preparedStatement.executeQuery();

	        // Création de l'objet Presentation correspondant si une présentation est trouvée
	        if (rs.next()) {
	            id = rs.getInt("id");
	            date = rs.getDate("datee");
	            nbPlacesDispo = rs.getInt("nbPlacesDispo");
	            horaire = rs.getTime("horaire");
	            dureePrevue = rs.getTime("dureePrevue");
	            idSalle = rs.getInt("salle_id");
	            idConference = rs.getInt("conference_id");

	            unePresentation = new Presentation(id, date, nbPlacesDispo, horaire, dureePrevue, idSalle, idConference);
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getUnePresentationParId : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de l'objet Presentation correspondant (ou null si non trouvé)
	    return unePresentation;
	}

	/**
	 * Ajoute une nouvelle conférence à la base de données.
	 *
	 * @param unNomConference Le nom de la nouvelle conférence à ajouter.
	 * @return true si l'ajout est réussi, sinon false.
	 */
	public static boolean ajouterUneConference(String unNomConference) {
	    boolean rep = false;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    // Requête SQL pour ajouter une nouvelle conférence
	    String requete = "INSERT INTO conference (theme) VALUES (?)";

	    try {
	        // Préparation et exécution de la requête SQL avec le paramètre unNomConference
	        preparedStatement = connexion.prepareStatement(requete);
	        preparedStatement.setString(1, unNomConference);

	        // Exécution de la requête et mise à jour de la valeur de rep en fonction du résultat
	        int rs = preparedStatement.executeUpdate();
	        if (rs > 0) {
	            rep = true;
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL ajouterConference : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de la valeur de rep indiquant si l'ajout de la conférence a réussi
	    return rep;
	}

	/**
	 * Ajoute une nouvelle présentation à la base de données.
	 *
	 * @param uneDate La date de la présentation.
	 * @param NombrePlacesDispo Le nombre de places disponibles pour la présentation.
	 * @param uneHoraire L'horaire de la présentation.
	 * @param uneDureePrevue La durée prévue de la présentation.
	 * @param salleId L'identifiant de la salle où aura lieu la présentation.
	 * @param conferenceId L'identifiant de la conférence à laquelle la présentation est associée.
	 * @param animateurId L'identifiant de l'animateur de la présentation.
	 * @return true si l'ajout de la présentation est réussi, sinon false.
	 */
	public static boolean ajouterUnePresentation(String uneDate, int nombrePlacesDispo, Time uneHoraire,
	        Time uneDureePrevue, int salleId, int conferenceId, int animateurId) {
	    boolean rep = false;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Insertion dans la table presentation
	        String requetePresentation = "INSERT INTO presentation (datee, nbPlaceDispo, horaire, dureePrevue, salle_id, conference_id) VALUES (?, ?, ?, ?, ?, ?)";
	        preparedStatement = connexion.prepareStatement(requetePresentation, Statement.RETURN_GENERATED_KEYS);
	        preparedStatement.setString(1, uneDate);
	        preparedStatement.setInt(2, nombrePlacesDispo);
	        preparedStatement.setTime(3, uneHoraire);
	        preparedStatement.setTime(4, uneDureePrevue);
	        preparedStatement.setInt(5, salleId);
	        preparedStatement.setInt(6, conferenceId);

	        int rsPresentation = preparedStatement.executeUpdate();

	        if (rsPresentation > 0) {
	            // Récupération de l'ID de la dernière présentation effectuée
	            String requeteDernierePresentation = "SELECT MAX(id) FROM presentation";
	            preparedStatement = connexion.prepareStatement(requeteDernierePresentation);
	            ResultSet resultSet = preparedStatement.executeQuery();

	            int presentationId = -1;
	            if (resultSet.next()) {
	                presentationId = resultSet.getInt(1);
	            }

	            // Insertion dans la table animer
	            String requeteAnimer = "INSERT INTO animer (animateur_id, presentation_id) VALUES (?, ?)";
	            preparedStatement = connexion.prepareStatement(requeteAnimer);
	            preparedStatement.setInt(1, animateurId);
	            preparedStatement.setInt(2, presentationId);

	            int rsAnimer = preparedStatement.executeUpdate();
	            
	            if (rsAnimer > 0) {
	                rep = true;
	                
	                // ajout des siéges
	                setSiege(nombrePlacesDispo);
	            }
	        }

	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL ajouterUnePresentation : " + erreur);
	    }

	    // Fermeture de la connexion à la base de données, même en cas d'exception
	    deconnexionBD();
	    return rep;
	}
	
	/**
	 * 
	 * */
	public static void setSiege(int nbPlacesDispo) {
		// Établissement de la connexion à la base de données
	    connexionBD();
	    int id_presentation = 0;
	    
		try {
			
			preparedStatement = connexion.prepareStatement("SELECT id FROM presentation ORDER BY id DESC LIMIT 1;");
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				id_presentation = rs.getInt("id");
			}
			
			
			// ajout des siege 
			for(int i = 0; i < nbPlacesDispo; i++ ) {
				preparedStatement = connexion.prepareStatement("INSERT INTO siege(presentation_id, place_id) VALUES(?,?);");
				preparedStatement.setInt(1, id_presentation);
				preparedStatement.setInt(2, i+1);
				preparedStatement.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * Récupère l'identifiant de la dernière conférence ajoutée à la base de données.
	 *
	 * @return L'identifiant de la dernière conférence ajoutée.
	 */
	public static int getIdDerniereConference() {
	    int rep = 0;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Sélection de la dernière conférence ajoutée en triant par l'identifiant de manière décroissante
	        // et en limitant le résultat à une seule ligne
	        preparedStatement = connexion.prepareStatement("SELECT * FROM conference ORDER BY id DESC LIMIT 1");
	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            // Récupération de l'identifiant de la dernière conférence
	            rep = rs.getInt("id");
	        }

	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getIdDerniereConference : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de l'identifiant de la dernière conférence ajoutée
	    return rep;
	}

	/**
	 * Vérifie si le nom de la conférence respecte le format spécifié.
	 *
	 * @param uneConference Le nom de la conférence à vérifier.
	 * @return true si le nom de la conférence est valide, sinon false.
	 */
	public static boolean verifNomConference(String uneConference) {
	    // La regex ^[a-zA-Z0-9]+(?: [a-zA-Z0-9]+)*$ signifie que la chaîne doit commencer par une lettre ou un chiffre,
	    // suivie par éventuellement des groupes d'un espace suivi par une lettre ou un chiffre, et se terminer par une lettre ou un chiffre.
	    String regex = "^[a-zA-Z0-9]+(?: [a-zA-Z0-9]+)*$";

	    // Vérification du nom de la conférence en utilisant la regex.
	    boolean rep = Pattern.matches(regex, uneConference);

	    return rep;
	}


	/**
	 * Récupère le nombre de places disponibles dans une salle avec l'identifiant spécifié.
	 *
	 * @param unIdSalle L'identifiant de la salle.
	 * @return Le nombre de places disponibles dans la salle.
	 */
	public static int getNbPlacesDispo(int unIdSalle) {
	    int rep = 0;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Sélection de la capacité maximale de la salle avec l'identifiant spécifié
	        preparedStatement = connexion.prepareStatement("SELECT capaciteMax FROM salle WHERE id = ?");
	        preparedStatement.setInt(1, unIdSalle);
	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            // Récupération du nombre de places disponibles
	            rep = rs.getInt("capaciteMax");
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getNbPlacesDispo : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour du nombre de places disponibles dans la salle
	    return rep;
	}

	/**
	 * Récupère la conférence associée à une présentation avec l'identifiant spécifié.
	 *
	 * @param unId L'identifiant de la présentation.
	 * @return La conférence associée à la présentation.
	 */
	public static Conference getConference(int unId) {
	    Conference uneConference = null ;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Sélection du thème de la conférence associée à la présentation avec l'identifiant spécifié
	        preparedStatement = connexion.prepareStatement("SELECT conference.id , conference.theme"
	                + " FROM conference, presentation"
	                + " WHERE conference.id = presentation.conference_id"
	                + " AND presentation.id = ?");
	        preparedStatement.setInt(1, unId);
	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            uneConference = new Conference(rs.getInt(1) , rs.getString(2));
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getConference : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour du thème de la conférence associée à la présentation
	    return uneConference ;
	}

	/**
	 * Formate les composants de date (jour, mois, année) en une chaîne de caractères au format "YYYY-MM-DD".
	 *
	 * @param jour  Le composant jour de la date.
	 * @param mois  Le composant mois de la date.
	 * @param annee Le composant année de la date.
	 * @return Une chaîne de caractères représentant la date formatée au format "YYYY-MM-DD".
	 */
	public static String formaterEnDate(Object jour, Object mois, Object annee) {
	    String unJour = (String) jour;
	    String unMois = (String) mois;
	    String uneAnnee = (String) annee;
	    return uneAnnee + "-" + unMois + "-" + unJour;
	}

	/**
	 * Formate les composants d'heure (heure, minute) en un objet Time.
	 *
	 * @param heure  Le composant heure de l'heure.
	 * @param minute Le composant minute de l'heure.
	 * @return Un objet Time représentant l'heure formatée.
	 */
	public static Time formaterEnTime(Object heure, Object minute) {
	    return Time.valueOf(LocalTime.of(Integer.parseInt((String) heure), Integer.parseInt((String) minute)));
	}

	/**
	 * Formate une chaîne de caractères en un entier.
	 *
	 * @param string La chaîne de caractères à formater en entier.
	 * @return L'entier résultant du formatage. Retourne -1 en cas d'erreur de formatage.
	 */
	public static int formaterEnEntier(String string) {
	    int integer = -1;
	    try {
	        integer = Integer.parseInt(string);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Erreur lors du formatage en entier.");
	    }
	    return integer;
	}

	/**
	 * Formate un objet Time en une chaîne de caractères représentant l'heure au format d'affichage (HH:mm).
	 *
	 * @param time L'objet Time à formater.
	 * @return Une chaîne de caractères représentant l'heure au format d'affichage (HH:mm).
	 */
	public static String formaterEnTimeAffichage(Time time) {
	    String timeString = time.toString();
	    return timeString.substring(0, 5);
	}

	/**
	 * Récupère la liste des animateurs sous forme d'objets Personne.
	 *
	 * @return Une ArrayList d'objets Personne représentant les animateurs.
	 */
	public static ArrayList<Personne> getLesAnimateurs() {
	    ArrayList<Personne> lesAnimateurs = new ArrayList<>();

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Sélection de tous les animateurs dans la table "animateur"
	        preparedStatement = connexion.prepareStatement("SELECT * FROM animateur");
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            // Récupération de l'identifiant de l'animateur
	            int id = rs.getInt("id");

	            // Sélection des informations de la personne correspondant à l'animateur
	            preparedStatement = connexion.prepareStatement("SELECT * FROM personne WHERE id = ?");
	            preparedStatement.setInt(1, id);
	            ResultSet rsPersonne = preparedStatement.executeQuery();

	            if (rsPersonne.next()) {
	                // Récupération des informations de la personne
	                String nom = rsPersonne.getString("nom");
	                String prenom = rsPersonne.getString("prenom");
	                String login = rsPersonne.getString("login");
	                int idSecretaire = rsPersonne.getInt("secretaire");

	                // Création de l'objet Personne représentant l'animateur
	                Personne unePersonneAnimateur = new Personne(id, nom, prenom, login, idSecretaire);
	                lesAnimateurs.add(unePersonneAnimateur);
	            }

	            // Fermeture du ResultSet de la requête sur la table "personne"
	            rsPersonne.close();
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getLesAnimateurs : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de la liste des animateurs
	    return lesAnimateurs;
	}

	/**
	 * Récupère la liste des salles sous forme d'objets Salle.
	 *
	 * @return Une ArrayList d'objets Salle représentant les salles.
	 */
	public static ArrayList<Salle> getLesSalles() {
	    ArrayList<Salle> lesSalles = new ArrayList<>();

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Sélection de toutes les salles dans la table "salle"
	        preparedStatement = connexion.prepareStatement("SELECT * FROM salle");
	        rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            // Récupération des informations de la salle
	            int id = rs.getInt("id");
	            String nom = rs.getString("nom");
	            int capaciteMax = rs.getInt("capaciteMax");

	            // Création de l'objet Salle représentant la salle
	            Salle uneSalle = new Salle(id, nom, capaciteMax);
	            lesSalles.add(uneSalle);
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getLesSalles : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de la liste des salles
	    return lesSalles;
	}

	/**
	 * Récupère le nom d'une salle en fonction de son identifiant.
	 *
	 * @param unId L'identifiant de la salle.
	 * @return La salle ou null si l'identifiant n'est pas trouvé.
	 */
	public static Salle getSalleAvecUnId(int unId) {
	    Salle uneSalle = null ;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Sélection la salle dans la table "salle" en fonction de l'identifiant
	        preparedStatement = connexion.prepareStatement("SELECT * FROM salle WHERE id = ?");
	        preparedStatement.setInt(1, unId);
	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            uneSalle = new Salle(rs.getInt(1) , rs.getString(2) , rs.getInt(3));
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getSalleAvecUnId : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour de la salle
	    return uneSalle ;
	}

	/**
	 * Récupère l'animateur en fonction de l'identifiant de la présentation.
	 *
	 * @param unId L'identifiant de la présentation.
	 * @return L'animateur qui anime ou null si l'identifiant n'est pas trouvé.
	 */
	private static Animer getAnimateurQuiAnimeUnePresentation(int unId) {
		Animer unAnimateurQuiAnime = null ;
	    // Établissement de la connexion à la base de données
	    connexionBD();
	    	    	     
	    try {
	        // Sélection de l'identifiant de l'animateur dans la table "animer" en fonction de l'identifiant de la présentation
	        preparedStatement = connexion.prepareStatement(
	                		"SELECT * "
	                        + "FROM animer " 
	                        + "WHERE presentation_id = ?");
	        preparedStatement.setInt(1, unId);
	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	           unAnimateurQuiAnime = new Animer(rs.getInt(1) , rs.getInt(2));
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getAnimateurQuiAnimeUnePresentation : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour du nom et prénom de l'animateur
	    return unAnimateurQuiAnime;
	}

	/**
	 * Récupère l'animateur en fonction de la présentation qu'il anime.
	 *
	 * @param unId L'identifiant de de la présentation.
	 * @return L'animateur qui anime cette présentation.
	 */
	public static Animateur getAnimateurQuiAnime(int unId) {
		int idAnimateurQuiAnime = 0 ;
		Animateur unAnimateur = null ;
	    // Établissement de la connexion à la base de données

	    try {
	    	if(Modele.getAnimateurQuiAnimeUnePresentation(unId) != null) {
	    		idAnimateurQuiAnime = Modele.getAnimateurQuiAnimeUnePresentation(unId).getAnimateur_id();
	    	}
	        // Sélection de l'identifiant de l'animateur dans la table "animer" en fonction de l'identifiant de la présentation
	    	connexionBD();
	        preparedStatement = connexion.prepareStatement(
	                		"SELECT * "
	                        + "FROM personne " 
	                        + "WHERE id = ?");
	        preparedStatement.setInt(1, idAnimateurQuiAnime);
	        rs = preparedStatement.executeQuery();
	        if (rs.next()) {
	        	unAnimateur = new Animateur(rs.getInt("id") , rs.getString("nom") , rs.getString("prenom") , rs.getString("login") , rs.getInt("secretaire"));
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getAnimateurQuiAnime : " + erreur);
	    } finally {
	        // Fermeture de la connexion à la base de données, même en cas d'exception
	        deconnexionBD();
	    }

	    // Retour du nom et prénom de l'animateur
	    return unAnimateur ;
	}
	
	/**
	 * Récupère un animateur en fonction de son nom et prénom.
	 *
	 * @param unNom    Le nom de l'animateur.
	 * @param unPrenom Le prénom de l'animateur.
	 * @return L'identifiant de l'animateur ou -1 si non trouvé.
	 */
	public static Animateur getAnimateurAvecNomPrenom(String unNom, String unPrenom) {
	    Animateur unAnimateur = null ;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Requête SQL pour récupérer l'identifiant d'un animateur en fonction de son nom et prénom
	        preparedStatement = connexion.prepareStatement("SELECT * FROM personne " +
	                "INNER JOIN animateur ON personne.id = animateur.id " +
	                "WHERE personne.nom = ? AND personne.prenom = ?");
	        preparedStatement.setString(1, unNom);
	        preparedStatement.setString(2, unPrenom);

	        // Exécution de la requête
	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	        	unAnimateur = new Animateur(rs.getInt(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getInt(6));
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getIdAnimateurAvecNomPrenom : " + erreur);
	    }

	    // Fermeture de la connexion à la base de données
	    deconnexionBD();

	    // Retour de l'identifiant de l'animateur
	    return unAnimateur;
	}

	/**
	 * Récupère l'identifiant d'une salle en fonction de son nom.
	 *
	 * @param nomSalle Le nom de la salle.
	 * @return L'identifiant de la salle ou -1 si non trouvé.
	 */
	public static int getIdSalleAvecNom(String nomSalle) {
	    int idSalle = -1;

	    // Établissement de la connexion à la base de données
	    connexionBD();

	    try {
	        // Requête SQL pour récupérer l'identifiant d'une salle en fonction de son nom
	        preparedStatement = connexion.prepareStatement("SELECT id FROM salle WHERE nom = ?");
	        preparedStatement.setString(1, nomSalle);

	        // Exécution de la requête
	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            // Récupération de l'identifiant de la salle
	            idSalle = rs.getInt("id");
	        }
	    } catch (SQLException erreur) {
	        // Affichage d'un message d'erreur en cas d'échec de la requête SQL
	        JOptionPane.showMessageDialog(null, "Erreur lors de la requête SQL getIdSalleAvecNom : " + erreur);
	    }

	    // Fermeture de la connexion à la base de données
	    deconnexionBD();

	    // Retour de l'identifiant de la salle
	    return idSalle;
	}

	/**
	 * Met à jour les informations d'une présentation dans la base de données.
	 *
	 * @param uneDate          La nouvelle date de la présentation.
	 * @param nbPlacesDispo    Le nouveau nombre de places disponibles.
	 * @param unHoraire        Le nouvel horaire de la présentation.
	 * @param uneDureePrevue   La nouvelle durée prévue de la présentation.
	 * @param unIdSalle        L'identifiant de la nouvelle salle de la présentation.
	 * @param unIdPresentation L'identifiant de la présentation à mettre à jour.
	 * @param unIdAnimateur    L'identifiant du nouvel animateur de la présentation.
	 * @return true si la mise à jour est réussie, sinon false.
	 */
	public static boolean updateUnePresentation(String uneDate, int nbPlacesDispo, Time unHoraire, Time uneDureePrevue,
	                                            int unIdSalle, int unIdPresentation, int unIdAnimateur) {
	    connexionBD();
	    boolean rep = false;

	    try {
	        // Vérification de l'existence de la salle avec l'identifiant spécifié
	        preparedStatement = connexion.prepareStatement("SELECT id FROM salle WHERE id = ?");
	        preparedStatement.setInt(1, unIdSalle);
	        rs = preparedStatement.executeQuery();

	        if (!rs.next()) {
	            JOptionPane.showMessageDialog(null, "La salle avec l'ID " + unIdSalle + " n'existe pas.");
	        } else {
	            // Mise à jour des informations de la présentation
	            preparedStatement = connexion.prepareStatement("UPDATE presentation " +
	                    "SET datee = ?, " +
	                    "nbPlacesDispo = ?, " +
	                    "horaire = ?, " +
	                    "dureePrevue = ?, " +
	                    "salle_id = ? " +
	                    "WHERE id = ?");
	            preparedStatement.setString(1, uneDate);
	            preparedStatement.setInt(2, nbPlacesDispo);
	            preparedStatement.setTime(3, unHoraire);
	            preparedStatement.setTime(4, uneDureePrevue);
	            preparedStatement.setInt(5, unIdSalle);
	            preparedStatement.setInt(6, unIdPresentation);

	            int rowsUpdated = preparedStatement.executeUpdate();

	            // Suppression des animateurs existants de la présentation
	            preparedStatement = connexion.prepareStatement(
	                    "DELETE FROM animer WHERE presentation_id = ?");
	            preparedStatement.setInt(1, unIdPresentation);
	            int rowsDeleted = preparedStatement.executeUpdate();

	            // Ajout du nouvel animateur
	            if (rowsUpdated >= 1 && rowsDeleted >= 0) {
	                preparedStatement = connexion.prepareStatement(
	                        "INSERT INTO animer (animateur_id, presentation_id) VALUES (?, ?)");
	                preparedStatement.setInt(1, unIdAnimateur);
	                preparedStatement.setInt(2, unIdPresentation);
	                int rowsInserted = preparedStatement.executeUpdate();

	                if (rowsInserted > 0) {
	                    rep = true;
	                }
	            }
	        }
	    } catch (SQLException erreur) {
	        JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour de la présentation : " + erreur);
	    } finally {
	        deconnexionBD();
	    }

	    return rep;
	}

	/**
	 * Supprime une présentation de la base de données.
	 *
	 * @param unIdPresentation L'identifiant de la présentation à supprimer.
	 * @return true si la suppression est réussie, sinon false.
	 */
	public static boolean supprimerUnePresentation(int unIdPresentation) {
	    connexionBD();
	    boolean rep = false;

	    try {
	        // Vérification de l'existence de la présentation avec l'identifiant spécifié
	        preparedStatement = connexion.prepareStatement("SELECT id FROM presentation WHERE id = ?;");
	        preparedStatement.setInt(1, unIdPresentation);
	        rs = preparedStatement.executeQuery();

	        if (!rs.next()) {
	            JOptionPane.showMessageDialog(null, "La présentation avec l'ID " + unIdPresentation + " n'existe pas.");
	        } else {
	            // Suppression des animateurs associés à la présentation
	            preparedStatement = connexion.prepareStatement("SELECT COUNT(*) FROM animer WHERE presentation_id = ?;");
	            preparedStatement.setInt(1, unIdPresentation);
	            rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	                preparedStatement = connexion.prepareStatement("DELETE FROM animer WHERE presentation_id = ?;");
	                preparedStatement.setInt(1, unIdPresentation);
	                preparedStatement.executeUpdate();
	            }

	            // Suppression de la présentation
	            preparedStatement = connexion.prepareStatement("DELETE FROM presentation WHERE id = ?;");
	            preparedStatement.setInt(1, unIdPresentation);
	            int rowsDeleted2 = preparedStatement.executeUpdate();

	            if (rowsDeleted2 >= 1) {
	                rep = true;
	            }
	        }
	    } catch (SQLException erreur) {
	        JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de la présentation : " + erreur);
	    } finally {
	        deconnexionBD();
	    }

	    return rep;
	}

	/**
	 * Supprime toutes les présentations associées à une conférence de la base de données.
	 *
	 * @param unIdConference L'identifiant de la conférence dont les présentations doivent être supprimées.
	 */
	public static void supprimerToutesLesPresentationsDeUneConference(int unIdConference) {
	    ArrayList<Presentation> lesPresentations = getLesPresentations(unIdConference);

	    for (Presentation unePresentation : lesPresentations) {
	        supprimerUnePresentation(unePresentation.getId());
	    }
	}

	/**
	 * Supprime une conférence de la base de données, ainsi que toutes les présentations associées.
	 *
	 * @param unIdConference L'identifiant de la conférence à supprimer.
	 * @return {@code true} si la suppression a réussi, sinon {@code false}.
	 */
	public static boolean supprimerUneConference(int unIdConference) {
	    boolean rep = false;

	    try {
	        // Supprimer toutes les présentations associées à la conférence
	        supprimerToutesLesPresentationsDeUneConference(unIdConference);
	        connexionBD();

	        // Supprimer la conférence
	        preparedStatement = connexion.prepareStatement("DELETE FROM conference WHERE id = ?;");
	        preparedStatement.setInt(1, unIdConference);

	        int rowsDeleted = preparedStatement.executeUpdate();
	        if (rowsDeleted >= 1) {
	            rep = true;
	        }
	    } catch (SQLException erreur) {
	        JOptionPane.showMessageDialog(null, "Erreur lors de la suppression de la conférence : " + erreur);
	    } finally {
	        deconnexionBD();
	    }

	    return rep;
	}

	/**
	 * Supprime toutes les conférences de la base de données, ainsi que toutes les présentations associées à chaque conférence.
	 *
	 * @return true si la suppression de toutes les conférences a réussi, false sinon.
	 */
	public static boolean supprimerToutesLesConferences() {
	    boolean rep = false;
	    ArrayList<Conference> lesConferences = getLesConferences();

	    for (Conference uneConference : lesConferences) {
	        supprimerUneConference(uneConference.getIdConf());
	        System.out.println(lesConferences.size());
	    }

	    if (lesConferences.size() == 0) {
	        rep = true;
	    }

	    return rep;
	}
	
	/**
	 * Vérifie s'il existe une présentation à la même heure pour une date donnée.
	 *
	 * @param uneDate La date de la présentation.
	 * @param unHoraire L'horaire de début de la présentation.
	 * @param uneDureePrevue La durée prévue de la présentation.
	 * @return {@code true} s'il existe une présentation à la même heure, sinon {@code false}.
	 */
	public static boolean existePresentationALaMemeHeure(String uneDate, Time unHoraire, Time uneDureePrevue) {
	    connexionBD();
	    boolean existe = false;

	    try {
	        preparedStatement = connexion.prepareStatement(
	                "SELECT * FROM presentation " +
	                "WHERE datee = ? " +
	                "AND horaire >= ? " +
	                "AND horaire < ADDTIME(?, ?)"
	        );
	        preparedStatement.setString(1, uneDate);
	        preparedStatement.setTime(2, unHoraire);
	        preparedStatement.setTime(3, unHoraire);
	        preparedStatement.setTime(4, uneDureePrevue);

	        ResultSet resultSet = preparedStatement.executeQuery();

	        if (resultSet.next()) {
	            existe = true;
	        }
	    } catch (SQLException erreur) {
	        JOptionPane.showMessageDialog(null, "Erreur lors de la vérification de la présentation : " + erreur);
	    }

	    deconnexionBD();
	    return existe;
	}
	
	/**
	 * Récupère le lien de l'image associée à un identifiant d'image spécifié.
	 * 
	 * Cette méthode permet de déterminer si l'image existe bien dans la base de données.
	 *
	 * @param uneImage L'identifiant de l'image pour laquelle récupérer le lien.
	 * @return Le lien de l'image s'il est trouvé, sinon une chaîne vide.
	 */
	public static String getUnePhoto(int uneImage) {
	    String rep = "";
	    connexionBD();
	    try {				
	        preparedStatement = connexion.prepareStatement("SELECT lienImage FROM image WHERE idImage = ?;");
	        preparedStatement.setInt(1, uneImage);
	        ResultSet rs = preparedStatement.executeQuery();
	        if(rs.next()) {
	            rep = rs.getString("lienImage");
	        }
	    }
	    catch (SQLException erreur) {
	        JOptionPane.showMessageDialog(null, "Une erreur est survenue dans la requête SQL getUnePhoto : " + erreur);
	    }	  
	    deconnexionBD();
	    return rep;
	}
	


	/*********************************************** partie maxime ****************************************************/

	/**
	 * Récupère le nombre de visiteurs dans la base de données.
	 *
	 * @return Le nombre de visiteurs.
	 */
	public static int getNbVisiteur() {
	    int nb = 0;

	    // Connexion à la base de données
	    connexionBD();

	    try {
	        Statement st;
	        String rq = "SELECT COUNT(*) AS nb FROM visiteur";
	        st = connexion.createStatement();
	        ResultSet res = st.executeQuery(rq);

	        // Récupération du résultat de la requête
	        res.next();
	        nb = res.getInt("nb");

	    } catch (SQLException e) {
	        // Gestion des exceptions liées à la base de données
	        e.printStackTrace();
	    } finally {
	        // Déconnexion de la base de données
	        deconnexionBD();
	    }

	    return nb;
	}

	/**
	 * Récupère le nombre total de présentations dans la base de données.
	 *
	 * @return Le nombre total de présentations.
	 */
	public static int getNbPresentation() {
	    // Utilisation d'une fonction existante pour obtenir la liste de toutes les présentations
	    ArrayList<Presentation> list = getToutesLesPresentations();

	    // Taille de la liste représente le nombre de présentations
	    int nb = list.size();
	    return nb;
	}

	/**
	 * Récupère le nombre total de conférences dans la base de données.
	 *
	 * @return Le nombre total de conférences.
	 */
	public static int getNbConference() {
	    int nb = 0;

	    // Connexion à la base de données
	    connexionBD();

	    try {
	        Statement st;
	        String rq = "SELECT COUNT(*) AS nb FROM conference";
	        st = connexion.createStatement();
	        ResultSet res = st.executeQuery(rq);

	        // Récupération du résultat de la requête
	        res.next();
	        nb = res.getInt("nb");

	    } catch (SQLException e) {
	        // Gestion des exceptions liées à la base de données
	        e.printStackTrace();
	    } finally {
	        // Déconnexion de la base de données
	        deconnexionBD();
	    }

	    return nb;
	}

	/**
	 * Récupère la liste des visiteurs associés à une présentation.
	 *
	 * @param idPresentation L'identifiant de la présentation.
	 * @return Une ArrayList d'objets Visiteur représentant les visiteurs associés à la présentation.
	 */
	public static ArrayList<Visiteur> getVisiteurPresentation(int idPresentation) {
	    ArrayList<Visiteur> list = new ArrayList<>();

	    // Connexion à la base de données
	    connexionBD();

	    try {
	        String rq = "SELECT id, nom, prenom "
	                + "FROM visiteur, reserver "
	                + "WHERE visiteur.id = reserver.id_visiteur "
	                + "AND id_Presentation = ?";
	        preparedStatement = connexion.prepareStatement(rq);
	        preparedStatement.setInt(1, idPresentation);
	        rs = preparedStatement.executeQuery();

	        // Ajout des visiteurs à la liste
	        while (rs.next()) {
	            list.add(new Visiteur(rs.getString(1), rs.getString(2), rs.getString(3)));
	        }

	    } catch (SQLException e) {
	        // Gestion des exceptions liées à la base de données
	        e.printStackTrace();
	    } finally {
	        // Déconnexion de la base de données
	        deconnexionBD();
	    }

	    return list;
	}

	/**
	 * Récupère la liste de toutes les conférences depuis la table "conference".
	 *
	 * @return Une ArrayList d'objets Conference représentant toutes les conférences disponibles.
	 */
	public static ArrayList<Conference> getLesConferences() {
	    ArrayList<Conference> list = new ArrayList<>();

	    // Connexion à la base de données
	    connexionBD();

	    try {
	        String rq = "SELECT * FROM conference;";
	        preparedStatement = connexion.prepareStatement(rq);
	        rs = preparedStatement.executeQuery();

	        // Ajout des conférences à la liste
	        while (rs.next()) {
	            list.add(new Conference(rs.getInt("id"), rs.getString("theme")));
	        }
	    } catch (SQLException e) {
	        // Gestion des exceptions liées à la base de données
	        e.printStackTrace();
	    } finally {
	        // Déconnexion de la base de données
	        deconnexionBD();
	    }

	    return list;
	}

	/**
	 * Récupère la liste de toutes les présentations pour une conférence donnée depuis la table "presentation".
	 *
	 * @param idConference L'ID de la conférence pour laquelle récupérer les présentations.
	 * @return Une ArrayList d'objets Presentation représentant toutes les présentations de la conférence.
	 */
	public static ArrayList<Presentation> getLesPresentations(int idConference) {
	    ArrayList<Presentation> list = new ArrayList<>();

	    // Connexion à la base de données
	    connexionBD();

	    try {
	        String rq = "SELECT * FROM presentation WHERE conference_id = ? ;";
	        preparedStatement = connexion.prepareStatement(rq);
	        preparedStatement.setInt(1, idConference);
	        rs = preparedStatement.executeQuery();

	        // Ajout des présentations à la liste
	        while (rs.next()) {
	            Presentation unePresentation = new Presentation(
	                rs.getInt("id"),
	                rs.getDate("datee"),
	                rs.getInt("nbPlaceDispo"),
	                rs.getTime("horaire"),
	                rs.getTime("dureePrevue"),
	                rs.getInt("salle_id"),
	                idConference
	            );
	            list.add(unePresentation);
	        }
	    } catch (SQLException e) {
	        // Gestion des exceptions liées à la base de données
	        e.printStackTrace();
	    } finally {
	        // Déconnexion de la base de données
	        deconnexionBD();
	    }

	    return list;
	}

	/**
	 * Hache la chaîne de caractères en utilisant l'algorithme SHA-256.
	 *
	 * @param mdp Le mot de passe à hacher.
	 * @return Le résultat du hachage en format hexadécimal.
	 */
	public static String hashSHA256(String mdp) {
	    // Utilisation de la classe DigestUtils de Commons Codec pour hacher avec SHA-256
	    return Hex.encodeHexString(DigestUtils.sha256(mdp));
	}
	
	/**
	 * Obtient le thème de la conférence la plus visitée.
	 *
	 * @return Le thème de la conférence la plus visitée.
	 */
	public static String getConferencePlusVisiter() {
	    // Le thème de la conférence la plus visitée
	    String themeConference = "";

	    try {
	        // Connexion à la base de données
	        connexionBD();

	        // Requête SQL pour obtenir le thème de la conférence la plus visitée
	        String requeteSQL = "SELECT conference.theme, COUNT(visiter.id_visiteur) AS nb "
	                + "FROM conference, presentation, visiter "
	                + "WHERE conference.id = presentation.conference_id "
	                + "AND presentation.id = visiter.id_presentation "
	                + "GROUP BY conference.id "
	                + "ORDER BY nb DESC "
	                + "LIMIT 1;";

	        // Préparation et exécution de la requête
	        preparedStatement = connexion.prepareStatement(requeteSQL);
	        rs = preparedStatement.executeQuery();

	        // Traitement des résultats de la requête
	        while (rs.next()) {
	            themeConference = rs.getString(1);
	        }
	    } catch (SQLException e) {
	        // Gestion des erreurs liées à la base de données
	        e.printStackTrace();
	    } finally {
	        // Déconnexion de la base de données dans le bloc finally pour garantir la déconnexion même en cas d'exception
	        deconnexionBD();
	    }

	    return themeConference;
	}

	/**
	 * Fonction qui retourne le thème de la conférence la moins visitée.
	 * 
	 * @return Le thème de la conférence la moins visitée.
	 */
	public static String getConferenceMoinsVisiter() {
	    // Le thème de la conférence la moins visitée
	    String conf = "";

	    try {
	        // Établir la connexion à la base de données
	        connexionBD();

	        // Requête SQL pour obtenir le thème de la conférence la moins visitée
	        String rq = "SELECT conference.theme, COUNT(visiter.id_visiteur) AS nb "
	                + "FROM conference, presentation, visiter "
	                + "WHERE conference.id = presentation.conference_id "
	                + "AND presentation.id = visiter.id_presentation "
	                + "GROUP BY conference.id "
	                + "ORDER BY nb ASC "
	                + "LIMIT 1;";

	        // Préparer et exécuter la requête SQL
	        preparedStatement = connexion.prepareStatement(rq);
	        rs = preparedStatement.executeQuery();

	        // Récupérer le thème de la conférence la moins visitée
	        while (rs.next()) {
	            conf = rs.getString(1);
	        }
	    } catch (SQLException e) {
	        // Gérer les exceptions liées à la base de données
	        e.printStackTrace();
	    } finally {
	        // Fermer la connexion à la base de données
	        deconnexionBD();
	    }

	    // Retourner le thème de la conférence la moins visitée
	    return conf;
	}

	/**
	 * Fonction qui renvoie le nombre de visiteurs ayant visité des présentations.
	 * 
	 * @return Le nombre total de visiteurs ayant visité des présentations.
	 */
	public static int getNbVisiteurVisiter() {
	    // Le nombre total de visiteurs ayant visité des présentations
	    int nbVisiteur = 0;

	    try {
	        // Établir la connexion à la base de données
	        connexionBD();

	        // Requête SQL pour obtenir le nombre de visiteurs ayant visité des présentations
	        String rq = "SELECT COUNT(id_visiteur) AS nb FROM visiter;";

	        // Préparer et exécuter la requête SQL
	        preparedStatement = connexion.prepareStatement(rq);
	        rs = preparedStatement.executeQuery();

	        // Récupérer le nombre total de visiteurs
	        while (rs.next()) {
	            nbVisiteur = rs.getInt(1);
	        }
	    } catch (SQLException e) {
	        // Gérer les exceptions liées à la base de données
	        e.printStackTrace();
	    } finally {
	        // Fermer la connexion à la base de données
	        deconnexionBD();
	    }

	    // Retourner le nombre total de visiteurs ayant visité des présentations
	    return nbVisiteur;
	}

}

