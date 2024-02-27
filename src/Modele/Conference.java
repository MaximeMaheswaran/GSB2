package Modele;

public class Conference {
	private int idConf;
	private String theme;
	
	
	public Conference(int idConf, String theme) {
		this.idConf = idConf;
		this.theme = theme;
	}
	
	public int getIdConf() {
		return idConf;
	}
	public String getTheme() {
		return theme;
	}
	public void setIdConf(int idConf) {
		this.idConf = idConf;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	 // Méthode pour convertir la conférence en format XML sous forme de chaîne de caractères
    public String toXML() {
        return "&nbsp;&nbsp;&nbsp;&nbsp; &lt IdConf > " + this.idConf + " &lt /IdConf > <br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt Theme > " + this.theme + " &lt /Theme >";
    }

    // Méthode pour convertir la conférence en format CSV sous forme de chaîne de caractères
    public String toCSV() {
        return this.idConf + " ; " + this.theme;
    }

 // Méthode pour convertir la conférence en format JSON sous forme de chaîne de caractères
    public String toJSON() {
        return "&nbsp;&nbsp&nbsp;&nbsp;{ <br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp\" idConf \" : " + this.idConf + ",<br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp\" theme \" : \"" + this.theme + "\"<br>"
        		+ "&nbsp;&nbsp;&nbsp;&nbsp}";
    }
	
	
	
}
