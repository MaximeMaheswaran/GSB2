package Vue;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VueAjouterConference {
	
	private JPanel unMainPanel ;
	
	private JPanel unPanelConference ;
	private JLabel unLabelConference ;
	private JTextField unJTextFieldConference ;
	private JLabel unNomConferenceInvalide ;
		
	private JButton btnValider ;
		
	
	public VueAjouterConference() {
		
		this.unMainPanel = new JPanel();
		this.unPanelConference = new JPanel();
		this.btnValider = new JButton("Valider");
		this.unLabelConference = new JLabel("Théme de la conférence : ");
		
		this.unMainPanel.setLayout(new GridLayout(1 , 2));
		this.unPanelConference.setLayout(new FlowLayout());
		
	    this.btnValider.setBorder(BorderFactory.createLineBorder(new Color(226 , 241 , 255), 5));
	    this.btnValider.setBackground(new Color(226 , 241 , 255));
		this.unPanelConference.setBackground(new Color(119 , 170 , 221));
		
		this.unJTextFieldConference = new JTextField(50);
		this.unNomConferenceInvalide = new JLabel();
		
		this.unPanelConference.add(this.unLabelConference);
		this.unPanelConference.add(this.unJTextFieldConference);
		this.unPanelConference.add(this.unNomConferenceInvalide);
		
		this.unPanelConference.add(this.btnValider);
		
		
		this.unMainPanel.add(this.unPanelConference);
		
		this.unNomConferenceInvalide.setVisible(false);
		
	}


	public JPanel getVueAjouterConference() {
		return unMainPanel;
	}


	public JPanel getUnPanelConference() {
		return unPanelConference;
	}


	public JLabel getUnLabelConference() {
		return unLabelConference;
	}


	public JTextField getUnJTextFieldConference() {
		return unJTextFieldConference;
	}


	public void setVueAjouterConference(JPanel unMainPanel) {
		this.unMainPanel = unMainPanel;
	}


	public void setUnPanelConference(JPanel unPanelConference) {
		this.unPanelConference = unPanelConference;
	}


	public void setUnLabelConference(JLabel unLabelConference) {
		this.unLabelConference = unLabelConference;
	}


	public void setUnJTextFieldConference(JTextField unJTextFieldConference) {
		this.unJTextFieldConference = unJTextFieldConference;
	}

	public JButton getBoutonValider() {
		return btnValider;
	}


	public void setBoutonValider(JButton valider) {
		this.btnValider = valider;
	}


	public JLabel getUnNomConferenceInvalide() {
		return unNomConferenceInvalide;
	}


	public void setUnNomConferenceInvalide(JLabel unNomConferenceInvalide) {
		this.unNomConferenceInvalide = unNomConferenceInvalide;
	}
	
}
