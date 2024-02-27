package Vue;
import javax.swing.JFrame;

public class Fenetre extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Fenetre() {
		
		this.setTitle("GSB");
		this.setSize(1500, 850);
        // Définir l'icône de la fenêtre
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); // Centrer la fenêtre
	}


	
}

