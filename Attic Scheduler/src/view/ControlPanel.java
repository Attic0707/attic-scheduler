package view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {

	/**
	 * 
	 */
	private JButton userList;
	private JButton taskList;
	private static final long serialVersionUID = 370481838840273932L;
	
	private boolean darkMode;
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		Color color1 = new Color(52, 143, 80);
		Color color2 = new Color(86, 180, 211);
		Color darkGray = Color.DARK_GRAY;
		Color gray = Color.GRAY;
		GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
		GradientPaint dm = new GradientPaint(0, 0, gray, 100, height, darkGray);
		if(!darkMode) {
			g2d.setPaint(gp);
			g2d.fillRect(0, 0, width, height);
		} else {
			g2d.setPaint(dm);
			g2d.fillRect(0, 0, width, height);
		}
	}
	
	public void setDarkMode(boolean turnDark) {
		this.darkMode = turnDark;
		repaint();
		revalidate();
	}
	
	public ControlPanel() {
		setEnabled(false);
		setLayout(new GridBagLayout());
		
		userList = new JButton("Export Users");
		taskList = new JButton("Export Tasks");
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		add(new JLabel("User List: "), gc);
		
		gc.gridx++;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		add(userList, gc);
		
		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		add(new JLabel("Task List: "), gc);
		
		gc.gridx++;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		add(taskList, gc);
		
		userList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(userList == (JButton) e.getSource()) {
					System.out.println("Export users button clicked");
				}
			}
		});
		
		taskList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(taskList == (JButton) e.getSource()) {
					System.out.println("Export tasks button clicked");
				}
			}
		});
		
	}
	

}
