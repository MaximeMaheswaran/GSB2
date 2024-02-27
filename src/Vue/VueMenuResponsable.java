package Vue;

import java.awt.Color;

import javax.swing.*;

//La classe VueMenuResponsable étend JMenuBar, qui est utilisée pour représenter la barre de menus dans une interface utilisateur graphique.

public class VueMenuResponsable extends JMenuBar {

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
// Attributs pour deux menus et différents éléments de menu.
 private JMenu menuAfficher;
 private JMenuItem afficherXml;
 private JMenuItem afficherJson;
 private JMenuItem afficherCsv;
 private JMenu menuExporter;
 private JMenuItem exportXml;
 private JMenuItem exportJson;
 private JMenuItem exportCsv;
 private JMenuItem statistiques;
 private JMenuItem deconnexion;

 // Constructeur de la classe.
 public VueMenuResponsable() {

     // Instanciation des différents éléments de menu.
     this.menuAfficher = new JMenu("Afficher");
     this.afficherCsv = new JMenuItem("CVS");
     this.afficherJson = new JMenuItem("JSON");
     this.afficherXml = new JMenuItem("XML");
     this.statistiques = new JMenuItem("Statistiques");
     this.menuExporter = new JMenu("Exporter");
     this.exportCsv = new JMenuItem("CSV");
     this.exportJson = new JMenuItem("JSON");
     this.exportXml = new JMenuItem("XML");
     this.deconnexion = new JMenuItem("Deconnexion");

     // Configuration des couleurs de fond et de texte pour les éléments de menu.
     setBackground(Color.GRAY);
     this.menuAfficher.setBackground(Color.GRAY);
     this.menuAfficher.setForeground(Color.WHITE);
     this.statistiques.setBackground(Color.GRAY);
     this.statistiques.setForeground(Color.WHITE);
     this.afficherCsv.setBackground(Color.GRAY);
     this.afficherCsv.setForeground(Color.WHITE);
     this.afficherJson.setBackground(Color.GRAY);
     this.afficherJson.setForeground(Color.WHITE);
     this.afficherXml.setBackground(Color.GRAY);
     this.afficherXml.setForeground(Color.WHITE);
     this.deconnexion.setBackground(Color.GRAY);
     this.deconnexion.setForeground(Color.WHITE);
     this.menuExporter.setBackground(Color.GRAY);
     this.menuExporter.setForeground(Color.WHITE);
     this.exportCsv.setBackground(Color.GRAY);
     this.exportCsv.setForeground(Color.WHITE);
     this.exportJson.setBackground(Color.GRAY);
     this.exportJson.setForeground(Color.WHITE);
     this.exportXml.setBackground(Color.GRAY);
     this.exportXml.setForeground(Color.WHITE);

     // Ajout des éléments de menu aux menus correspondants.
     this.menuAfficher.add(this.statistiques);
     this.menuAfficher.add(this.afficherCsv);
     this.menuAfficher.add(this.afficherJson);
     this.menuAfficher.add(this.afficherXml);

     this.menuExporter.add(this.exportCsv);
     this.menuExporter.add(this.exportJson);
     this.menuExporter.add(this.exportXml);

     // Ajout des menus à la barre de menus.
     add(this.menuAfficher);
     add(this.menuExporter);
     add(this.deconnexion);
 }

 // Méthodes de récupération des différents éléments de menu.
 public JMenuItem getAfficherXml() {
     return afficherXml;
 }

 public JMenuItem getAfficherJson() {
     return afficherJson;
 }

 public JMenuItem getAfficherCsv() {
     return afficherCsv;
 }

 public JMenuItem getStatistiques() {
     return statistiques;
 }

 public JMenuItem getDeconnexion() {
     return deconnexion;
 }

 public JMenuItem getExportXml() {
     return exportXml;
 }

 public JMenuItem getExportJson() {
     return exportJson;
 }

 public JMenuItem getExportCsv() {
     return exportCsv;
 }
}
