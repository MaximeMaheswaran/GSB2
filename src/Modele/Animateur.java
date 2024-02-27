package Modele;

public class Animateur extends Personne {
    
    public Animateur(int unId, String unNom, String unPrenom, String unLogin, int unSecretaire) {
        super(unId, unNom, unPrenom, unLogin, unSecretaire);
    }
    
 // Méthode pour convertir le visiteur en format CSV sous forme de chaîne de caractères
    public String toCSVString() {
        return super.toCSVString();
    }

    // Méthode pour convertir le visiteur en format XML sous forme de chaîne de caractères
    public String toXMLString() {
        return"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt Animateur > <br>" 
        	+ super.toXMLString()
        	+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &lt /Animateur > ";
    }

    // Méthode pour convertir le visiteur en format JSON sous forme de chaîne de caractères
    public String toJSONString() {
        return super.toJSONString();
    }
}
