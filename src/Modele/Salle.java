package Modele;

public class Salle {
	
	private int id ;
	private String nom ;
	private int capaciteMax ;
	
	public Salle(int unId , String unNom , int uneCapacite) {
		this.id = unId ;
		this.nom = unNom ;
		this.capaciteMax =uneCapacite ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCapaciteMax() {
		return capaciteMax;
	}

	public void setCapaciteMax(int capaciteMax) {
		this.capaciteMax = capaciteMax;
	}
	
	
}
