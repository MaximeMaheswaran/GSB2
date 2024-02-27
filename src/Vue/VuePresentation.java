package Vue;

import java.awt.Color;

import javax.swing.JPanel;

public class VuePresentation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel presentationMainPanel ;
	private JPanel unPanel ;
	
	public VuePresentation() {
		
		this.presentationMainPanel = new JPanel();
		this.unPanel = new JPanel();
		
		this.presentationMainPanel.setBackground(new Color(119 , 170 , 221));
	}

	public JPanel getVuePresentation() {
		return presentationMainPanel;
	}

	public void setVuePresentation(JPanel presentationMainPanel) {
		this.presentationMainPanel = presentationMainPanel;
	}

	public JPanel getUnPanel() {
		return unPanel;
	}

	public void setUnPanel(JPanel unPanel) {
		this.unPanel = unPanel;
	}
	
	
}
