package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Toolbar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton login;
	private JButton profile;
	private JButton calc;
	private JButton reports;
	private JButton calendar;
	private ToolListener listener;
	private JTextField timeStamp;
	private JButton feedback;
	private JButton settings;
	private ArrayList<String> userList;
	private ArrayList<String> passList;

	public void setToolListener(ToolListener listen) {
		this.listener = listen;
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		Color color1 = new Color(52, 143, 80);
		Color color2 = new Color(86, 180, 211);
		GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
	}

	public Toolbar() {
		buttonParams();
		actionsForButtons();
		layoutParams();
		setPreferredSize(new Dimension(150, 500));
		setBorder(BorderFactory.createTitledBorder("Toolbar"));

		userList = new ArrayList<String>();
		passList = new ArrayList<String>();
		userList.add("sametatik7@gmail.com");
		userList.add("samet.atik@boun.edu.tr");
		userList.add("kdlvfore1224@yahoo.co.jp");
		passList.add("Sam123");

	}

	GridBagConstraints gc;

	public void layoutParams() {
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(10, 20, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(login, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(profile, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(calc, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(calendar, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 50;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.anchor = GridBagConstraints.NORTHWEST;
		add(reports, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(feedback, gc);

		gc.gridx = 0;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 55, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(settings, gc);

		gc.gridx = 0;
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.insets = new Insets(0, 20, 0, 0);
		gc.anchor = GridBagConstraints.LINE_START;
		add(timeStamp, gc);

	}

	public void buttonParams() {
		login = new JButton("Login");
		login.setPreferredSize(new Dimension(113, 25));
		login.setBackground(Color.WHITE);
		profile = new JButton("Profile");
		profile.setPreferredSize(new Dimension(113, 25));
		profile.setBackground(Color.WHITE);
		profile.setActionCommand("Profile Clicked");
		calc = new JButton("Calculator");
		calc.setPreferredSize(new Dimension(113, 25));
		calc.setBackground(Color.WHITE);
		reports = new JButton("Reports");
		reports.setPreferredSize(new Dimension(113, 25));
		reports.setBackground(Color.WHITE);
		reports.setActionCommand("Reports Clicked");
		calendar = new JButton("Calendar");
		calendar.setPreferredSize(new Dimension(113, 25));
		calendar.setBackground(Color.WHITE);
		feedback = new JButton();
		feedback.setPreferredSize(new Dimension(30, 30));
		feedback.setBackground(Color.WHITE);
		settings = new JButton();
		settings.setPreferredSize(new Dimension(30, 30));
		settings.setBackground(Color.WHITE);

		ImageIcon reportIcon = new ImageIcon(getClass().getResource("/images/graph.png"));
		Image scaledImg = reportIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		reportIcon = new ImageIcon(scaledImg);
		reports.setIcon(reportIcon);
		reports.setHorizontalAlignment(SwingConstants.LEFT);
		reports.setVerticalAlignment(JButton.CENTER);

		ImageIcon calcIcon = new ImageIcon(getClass().getResource("/images/calc.png"));
		Image scaledCalc = calcIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		calcIcon = new ImageIcon(scaledCalc);
		calc.setIcon(calcIcon);
		calc.setHorizontalAlignment(SwingConstants.LEFT);
		calc.setVerticalAlignment(JButton.CENTER);
		calc.setActionCommand("Calculator Clicked");

		ImageIcon profIcon = new ImageIcon(getClass().getResource("/images/profile.png"));
		Image scaledProf = profIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		profIcon = new ImageIcon(scaledProf);
		profile.setIcon(profIcon);
		profile.setHorizontalAlignment(SwingConstants.LEFT);
		profile.setVerticalAlignment(JButton.CENTER);

		ImageIcon loginIcon = new ImageIcon(getClass().getResource("/images/login.png"));
		Image scaledLog = loginIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		loginIcon = new ImageIcon(scaledLog);
		login.setIcon(loginIcon);
		login.setHorizontalAlignment(SwingConstants.LEFT);
		login.setVerticalAlignment(JButton.CENTER);

		ImageIcon caleIcon = new ImageIcon(getClass().getResource("/images/calendar.png"));
		Image scaledCale = caleIcon.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
		caleIcon = new ImageIcon(scaledCale);
		calendar.setIcon(caleIcon);
		calendar.setHorizontalAlignment(SwingConstants.LEFT);
		calendar.setVerticalAlignment(JButton.CENTER);
		calendar.setActionCommand("Calendar Clicked");

		ImageIcon feedIcon = new ImageIcon(getClass().getResource("/images/feed.png"));
		Image scaledFeed = feedIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		feedIcon = new ImageIcon(scaledFeed);
		feedback.setIcon(feedIcon);
		feedback.setHorizontalAlignment(JButton.CENTER);
		feedback.setVerticalAlignment(JButton.CENTER);
		feedback.setActionCommand("Feedback Clicked");

		ImageIcon settingsIcon = new ImageIcon(getClass().getResource("/images/settings.png"));
		Image scaledSet = settingsIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		settingsIcon = new ImageIcon(scaledSet);
		settings.setIcon(settingsIcon);
		settings.setHorizontalAlignment(JButton.CENTER);
		settings.setVerticalAlignment(JButton.CENTER);
		settings.setActionCommand("Settings Clicked");

		timeStamp = new JTextField();
		timeStamp.setPreferredSize(new Dimension(113, 25));
		timeStamp.setEditable(false);
		timeStamp.setBackground(Color.GRAY);
		timeStamp.setForeground(Color.WHITE);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		timeStamp.setText(dtf.format(now));
		timeStamp.setHorizontalAlignment(SwingConstants.CENTER);

	}

	public void actionsForButtons() {
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = "";
				String pass = "";
//				//TODO pass'i char array yapmam ve UI'da gizli göstermem lazým
//				//TODO Option pane 'de çarpýya bastýðýmý nasýl anlarým

				do {
					if (!userList.contains(userName)) {
						userName = JOptionPane.showInputDialog(null, "Enter Username", "Login",
								JOptionPane.PLAIN_MESSAGE);

					} else if (!passList.contains(pass)) {
						pass = JOptionPane.showInputDialog(null, "Enter Password", "Login", JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Access Granted", "Success",
								JOptionPane.INFORMATION_MESSAGE);
						login.setVisible(false);
						JButton welcome = new JButton("Welcome");
						welcome.setPreferredSize(new Dimension(113, 25));
						welcome.setBackground(Color.white);
						welcome.setEnabled(false);
						gc.gridx = 0;
						gc.gridy = 0;
						gc.weightx = 1;
						gc.weighty = 1;
						gc.insets = new Insets(10, 20, 0, 0);
						gc.anchor = GridBagConstraints.LINE_START;
						add(welcome, gc);
						break;
					}
				} while (true);
			}
		});

		calendar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == calendar) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}
				}
			}
		});

		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == profile) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}

				}

			}

		});

		reports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == reports) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}
				}
			}

		});

		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == settings) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}
				}
			}

		});

		feedback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == feedback) {
					String feedback = JOptionPane.showInputDialog(Toolbar.this, "Want to share your feedback?",
							"Feedback", JOptionPane.YES_NO_CANCEL_OPTION);
					System.out.println(feedback);
					if (feedback != null) {
						File feedbacks = new File("User Feedback.txt");
						try {
							BufferedWriter bw = new BufferedWriter(new FileWriter(feedbacks));
							bw.write(feedback);
							bw.close();
						} catch (IOException e1) {
							System.out.println("Unable to create file");
						}
						JOptionPane.showMessageDialog(Toolbar.this, "Thank you for your feedback", "Feedback",
								JOptionPane.PLAIN_MESSAGE);
					} else {
						JOptionPane.showConfirmDialog(Toolbar.this, "No worries", "Feedback",
								JOptionPane.CLOSED_OPTION);
					}
				}
			}

		});

		calc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton clicked = (JButton) e.getSource();
				if (clicked == calc) {
					if (listener != null) {
						e.getActionCommand();
						listener.toolEventOccured(e);
					}
				}
			}

		});

	}

}
