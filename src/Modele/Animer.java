package Modele;

public class Animer {

	private int animateur_id ;
	private int presentation_id ;
	
	public Animer(int unIdAnimateur , int unIdPresentation) {
		this.animateur_id = unIdAnimateur ;
		this.presentation_id = unIdPresentation ;
	}

	public int getAnimateur_id() {
		return animateur_id;
	}

	public void setAnimateur_id(int animateur_id) {
		this.animateur_id = animateur_id;
	}

	public int getPresentation_id() {
		return presentation_id;
	}

	public void setPresentation_id(int presentation_id) {
		this.presentation_id = presentation_id;
	}
	
	
}
