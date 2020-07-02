package gui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 370481838840273932L;
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		Color color1 = new Color(52, 143, 80);
		Color color2 = new Color(86, 180, 211);
		GradientPaint gp = new GradientPaint(0, 0, color1, 100, height, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
	}
	public ControlPanel() {
		setEnabled(true);
		setLayout(new GridBagLayout());
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
//		add(editorPane, gc);
		
		
	}

}
