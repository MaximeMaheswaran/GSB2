package Vue;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

//La classe VueResponsable étend JPanel, ce qui signifie qu'elle est destinée à être utilisée comme un composant graphique.

public class VueResponsable extends JPanel {
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// Attribut déclaré pour stocker un JPanel.
 private JPanel mPanel;

 // Constructeur de la classe.
 public VueResponsable() {
     // Définit la couleur de fond de cette instance de JPanel sur DARK_GRAY.
     setBackground(Color.DARK_GRAY);
 }

 // Méthode pour initialiser un nouveau JPanel.
 public void setNewPanel() {
     this.mPanel = new JPanel();
     this.mPanel.setBackground(Color.DARK_GRAY);
 }

 // Méthode pour configurer un nouveau JLabel avec une couleur de texte blanche et l'ajouter au JPanel existant.
 public void setNewJLabel(JLabel lbl) {
     lbl.setForeground(Color.WHITE);
     this.mPanel.add(lbl);
 }

 // Méthode pour ajouter le JPanel créé à l'instance de VueResponsable.
 public void setAddPanel() {
     this.add(mPanel);
 }

 // Méthode pour récupérer le JPanel créé.
 public JPanel getMPanel() {
     return this.mPanel;
 }
}

