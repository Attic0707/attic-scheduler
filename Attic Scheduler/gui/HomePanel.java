package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class HomePanel extends JPanel implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7685689743747881331L;
	private JTextPane textPane;
	private JCheckBox plus;
	protected void paintComponent (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int width  = getWidth();
		int height = getHeight();
		Color color1 = new Color(52, 143, 80);
		Color color2 = new Color(86, 180, 211);
		GradientPaint gp = new GradientPaint(0,0,color1, 180,height, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
		
	}

	public HomePanel() {
		setFocusable(true);
		requestFocusInWindow();
		plus = new JCheckBox("Hide Welcome Text");
		plus.setBackground(null);
		plus.setOpaque(false);
		plus.setSelected(false);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		textPane = new JTextPane();
		textPane.setEditable(false);
		add(textPane, BorderLayout.CENTER);
		add(plus, BorderLayout.AFTER_LAST_LINE);
		textPane.setVisible(true);
		
		textPane.setForeground(new Color(255,232,223));   
		textPane.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, 20));
		textPane.setText("Welcome to Attic Scheduler\nThis is a Planning tool to keep track of your tasks\nDesigned by Attic Inc. Istanbul 2020");
		textPane.setOpaque(false);
		
		plus.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				boolean isClicked =  plus.isSelected();
				textPane.setVisible(!isClicked);
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(KeyEvent.VK_ENTER == e.getKeyCode()) {
			System.out.println("You pressed 0");
			
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
