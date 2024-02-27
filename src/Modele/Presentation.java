package Modele;

import java.sql.Date;
import java.sql.Time;

public class Presentation {
	private int id ;
	private Date date ;
	private int nbPlaces ;
	private Time horaire ;
	private Time dureePrevue ;
	private int idSalle ;
	private int idConference ;
	
	public Presentation(int unId , Date uneDate , int unNbPlaces , Time uneHoraire , Time uneDureePrevue , int unIdSalle , int unIdConference) {
		this.id = unId ;
		this.date = uneDate ;
		this.nbPlaces = unNbPlaces ;
		this.horaire = uneHoraire ;
		this.dureePrevue = uneDureePrevue ;
		this.idSalle = unIdSalle ;
		this.idConference = unIdConference ;
	}
	
	public String afficher() {
		String rep = "";
		if(this.date == null) {
			rep = rep + "&nbsp;&nbsp;Date de la présentation : non attribué <br>";
		}
		else {
			rep = rep + "&nbsp;&nbsp;Date de la présentation : " + this.date + "<br>";
		}
		if(this.nbPlaces == 0) {
			rep = rep + "&nbsp;&nbsp;Nombre de places disponibles : non attribué <br>";
		}
		else {
			rep = rep + "&nbsp;&nbsp;Nombre de places disponibles : " + this.nbPlaces + "<br>";
		}
		if(this.horaire == null) {
			rep = rep + "&nbsp;&nbsp;Horaire de la présentation : non attribué <br>";
		}
		else {
			rep = rep + "&nbsp;&nbsp;Horaire de la présentation : " + this.horaire + "<br>";
		}
		if(this.dureePrevue == null) {
			rep = rep + "&nbsp;&nbsp;Durée prévue de la présentation : non attribué <br>";
		}
		else {
			rep = rep + "&nbsp;&nbsp;Durée prévue de la présentation : " + this.dureePrevue + "<br>";
		}
		return rep ;
			
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public Time getHoraire() {
		return horaire;
	}

	public void setHoraire(Time horaire) {
		this.horaire = horaire;
	}

	public Time getDureePrevue() {
		return dureePrevue;
	}

	public void setDureePrevue(Time dureePrevue) {
		this.dureePrevue = dureePrevue;
	}

	public int getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(int idSalle) {
		this.idSalle = idSalle;
	}

	public int getIdConference() {
		return idConference;
	}

	public void setIdConference(int idConference) {
		this.idConference = idConference;
	}
	
	// Méthode pour convertir la présentation en format CSV sous forme de chaîne de caractères
    public String toCSVString() {
        return id + " ; " + date + " ; " + nbPlaces + " ; " + horaire + " ; " + dureePrevue + " ; " + idSalle + " ; " + idConference;
    }

    // Méthode pour convertir la présentation en format XML sous forme de chaîne de caractères
    public String toXMLString() {
    	    return  "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt Id > " + id + " &lt /Id > <br>"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt Date > " + date + " &lt /Date > <br>"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt NbPlaces > " + nbPlaces + " &lt /NbPlaces > <br>"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt Horaire > " + horaire + " &lt /Horaire > <br>"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt DureePrevue > " + dureePrevue + " &lt /DureePrevue > <br>"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt IdSalle > " + idSalle + " &lt /IdSalle > <br>"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
    	            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt IdConference > " + idConference + " &lt /IdConference >";
    }



    // Méthode pour convertir la présentation en format JSON sous forme de chaîne de caractères
    public String toJSONString() {
        return "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{<br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" id \": " + id + ",<br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" date \": \"" + date + "\",<br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" nbPlaces \": " + nbPlaces + "\",<br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" horaire \": \"" + horaire + "\",<br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" dureePrevue \": \"" + dureePrevue + "\",<br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" idSalle \": " + idSalle + "\",<br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\" idConference \": " + idConference + "\" <br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;}";
    }
	
	
}
