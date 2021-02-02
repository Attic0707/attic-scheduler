package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ProfilePanel extends JPanel implements MouseListener {
	private ArrayList<JTextField> fieldArray;
	private ArrayList<JPanel> panelArray;
	private ArrayList<JButton> buttonArray;
	private String[] panelNames, fieldNames, buttonNames;
	private HashMap<JTextField, String> dataMapping;
	private ProfileListener profileListener;
	private JScrollPane scroll;
	private JPopupMenu popup;
	private JMenuItem edit;

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
		if (!darkMode) {
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

	public void setProfileListener(ProfileListener listener) {
		this.profileListener = listener;
	}

	public ProfilePanel() {
		setLayout(new FlowLayout());
		buttonArray = new ArrayList<>();
		fieldArray = new ArrayList<>();
		panelArray = new ArrayList<>();
		dataMapping = new HashMap<JTextField, String>();

		JPanel mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension(800, 2000));
		mainPanel.setBackground(null);
		mainPanel.setOpaque(false);

		scroll = new JScrollPane(mainPanel);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.getVerticalScrollBar().setUnitIncrement(10);
		scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		scroll.setPreferredSize(new Dimension(1024, 1000));
		scroll.setOpaque(false);

		popup = new JPopupMenu();
		edit = new JMenuItem("Edit");
		edit.setActionCommand("Edit Clicked");
		edit.setBorderPainted(true);
		popup.add(edit);

		add(scroll);

		buttonNames = new String[] { "LogOut", "Delete Account", "Edit", "Save", "Cancel" };
		panelNames = new String[] { "Cover Picture", "Account Information", "Address Information",
				"Personal Information", "Other Information" };
		fieldNames = new String[] { "First Name", "Last Name", "UserName", "Street", "Postal Code", "City", "State",
				"Country", "Bio", "Phone", "Email", "Company", "Website", "Social", "Member Since", "Password" };

		// Set the buttons
		for (int i = 0; i < buttonNames.length; i++) {
			JButton button = new JButton();
			button.setText(buttonNames[i]);
			button.setEnabled(false);
			button.addMouseListener(this);
			button.setPreferredSize(new Dimension(120, 25));
			button.setBackground(Color.WHITE);
			button.setForeground(Color.BLUE);
			buttonArray.add(button);
		}

		for (int i = 0; i < panelNames.length; i++) {
			JPanel panel = new JPanel();
			panel.setPreferredSize(new Dimension(800, 250));
			panel.setLayout(new GridLayout(0, 2, 10, 5));
			panel.setEnabled(true);
			panel.setOpaque(false);
			panel.setBorder(BorderFactory.createTitledBorder(panelNames[i]));
			panelArray.add(panel);
		}

		for (int i = 0; i < fieldNames.length; i++) {
			JTextField field = new JTextField(15);
			field.setName(fieldNames[i]);
			field.setEditable(false);
			field.setComponentPopupMenu(popup);
			field.setActionCommand("field clicked " + i);
			fieldArray.add(field);
		}

		for (JPanel p : panelArray) {
			if (p == panelArray.get(0)) {
				p.setPreferredSize(new Dimension(900, 200));
				mainPanel.add(p);
			} else if (p == panelArray.get(1)) {
				p.add(new JLabel(fieldNames[0]));
				p.add(fieldArray.get(0));
				p.add(new JLabel(fieldNames[1]));
				p.add(fieldArray.get(1));
				p.add(new JLabel(fieldNames[2]));
				p.add(fieldArray.get(2));
				p.add(new JLabel(fieldNames[15]));
				p.add(fieldArray.get(15));
				p.add(buttonArray.get(2));
				p.add(buttonArray.get(4));
				p.add(buttonArray.get(0));
				buttonArray.get(0).setVisible(false);
				p.add(buttonArray.get(1));
				buttonArray.get(1).setVisible(false);
				mainPanel.add(p);
			} else if (p == panelArray.get(2)) {
				p.add(new JLabel(fieldNames[3]));
				p.add(fieldArray.get(3));
				p.add(new JLabel(fieldNames[4]));
				p.add(fieldArray.get(4));
				p.add(new JLabel(fieldNames[5]));
				p.add(fieldArray.get(5));
				p.add(new JLabel(fieldNames[6]));
				p.add(fieldArray.get(6));
				p.add(new JLabel(fieldNames[7]));
				p.add(fieldArray.get(7));
				mainPanel.add(p);
			} else if (p == panelArray.get(3)) {
				p.add(new JLabel(fieldNames[8]));
				p.add(fieldArray.get(8));
				p.add(new JLabel(fieldNames[9]));
				p.add(fieldArray.get(9));
				p.add(new JLabel(fieldNames[10]));
				p.add(fieldArray.get(10));
				p.add(new JLabel(fieldNames[11]));
				p.add(fieldArray.get(11));
				p.add(new JLabel(fieldNames[12]));
				p.add(fieldArray.get(12));
				p.add(new JLabel(fieldNames[13]));
				p.add(fieldArray.get(13));
				p.add(new JLabel(fieldNames[14]));
				p.add(fieldArray.get(14));
				mainPanel.add(p);
			} else if (p == panelArray.get(4)) {
				mainPanel.add(p);
			}
		}
	}

	public void userInfoDetails(boolean loggedIn, String firstName, String lastName, String userName, char[] password, String street, String postalCode, String city, String state,
			String country, String bio, String email, String company, String website, String social, int phone,
			int memberSince) {
		fieldArray.get(15).setText(password.toString());
		buttonArray.get(0).setVisible(loggedIn);
		buttonArray.get(1).setVisible(loggedIn);
		fieldArray.get(0).setText(firstName);
		fieldArray.get(1).setText(lastName);
		fieldArray.get(2).setText(userName);
		fieldArray.get(3).setText(street);
		fieldArray.get(4).setText(postalCode);
		fieldArray.get(5).setText(city);
		fieldArray.get(6).setText(state);
		fieldArray.get(7).setText(country);
		fieldArray.get(8).setText(bio);
		fieldArray.get(9).setText(email);
		fieldArray.get(10).setText(company);
		fieldArray.get(11).setText(website);
		fieldArray.get(12).setText(social);
//		fieldArray.get(13).setText(phone);
//		fieldArray.get(14).setText(memberSince);
	}

	public void mouseClicked(MouseEvent e) {
		JButton clicked = (JButton) e.getSource();
		if (clicked == buttonArray.get(0)) {
			int logging = JOptionPane.showConfirmDialog(ProfilePanel.this, "Are you sure you want to log out?",
					"Sign Out", JOptionPane.YES_NO_OPTION);
			if (logging == 0) {
				ProfileEvent pe = new ProfileEvent(this, true);
				if (profileListener != null) {
					profileListener.profileEventHappened(pe);
					buttonArray.get(0).setVisible(false);
					buttonArray.get(1).setVisible(false);
					fieldArray.get(0).setText(null);
					fieldArray.get(1).setText(null);
					fieldArray.get(2).setText(null);
					fieldArray.get(15).setText(null);
				}
			}
		} else if (clicked == buttonArray.get(2)) {
			fieldArray.get(0).setEditable(true);
			fieldArray.get(1).setEditable(true);
			fieldArray.get(2).setEditable(true);
			fieldArray.get(15).setEditable(true);
			buttonArray.get(4).setEnabled(true);

		} else if (clicked == buttonArray.get(4)) {
			for (JTextField f : fieldArray) {
				dataMapping.put(f, f.getText());

			}
			System.out.println(dataMapping);
		} else if (clicked == buttonArray.get(1)) {
			int delete = JOptionPane
					.showConfirmDialog(ProfilePanel.this,
							"Are you absolutely sure you want to delete this account? " + "\n"
									+ "Deleted accounts cannot be restored",
							"Delete my account", JOptionPane.YES_NO_OPTION);
			if (delete == 0) {
				String pass = JOptionPane.showInputDialog(ProfilePanel.this, "Enter your password", "Confirmation",
						JOptionPane.PLAIN_MESSAGE);
				if (pass != null) {
					char[] passConfirm = pass.toCharArray();
					// char[] currentPass = 
					ProfileEvent pe = new ProfileEvent(this, true, passConfirm);
					if (profileListener != null) {
						profileListener.profileEventHappened(pe);
					}
				}
			} else {
				JOptionPane.showConfirmDialog(ProfilePanel.this, "Never mind", "Never mind", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {
		JButton entered = (JButton) e.getSource();
		if (entered == buttonArray.get(0)) {
			buttonArray.get(0).setEnabled(true);
		} else if (entered == buttonArray.get(1)) {
			buttonArray.get(1).setEnabled(true);
		} else if (entered == buttonArray.get(2)) {
			buttonArray.get(2).setEnabled(true);
		}
	}

	public void mouseExited(MouseEvent e) {
		JButton exited = (JButton) e.getSource();
		if (exited == buttonArray.get(0)) {
			buttonArray.get(0).setEnabled(false);
		} else if (exited == buttonArray.get(1)) {
			buttonArray.get(1).setEnabled(false);
		} else if (exited == buttonArray.get(2)) {
			buttonArray.get(2).setEnabled(false);
		}
	}
}