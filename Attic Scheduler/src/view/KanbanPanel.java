package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class KanbanPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea card;
	private String[] paneNames, typeNames;
	private ArrayList<JLayeredPane> paneArray;
	private JPopupMenu popupMenu;
	private TaskCardListener listener;
	private int paneId, cardNo;
	private Component cardComp;
	private JPanel editPanel;
	private JScrollPane scroll;
	private Dimension area, scrollArea;
	private JMenuItem editTask, removeTask;
	private boolean darkMode;

	public void setCardListener(TaskCardListener listen) {
		this.listener = listen;
	}

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

	public KanbanPanel() {
		setLayout(new FlowLayout());
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		setPreferredSize(new Dimension());

		popupMenu = new JPopupMenu();
		editTask = new JMenuItem("Edit Task");
		editTask.addActionListener(this);
		removeTask = new JMenuItem("Remove Task");
		removeTask.addActionListener(this);
		popupMenu.add(editTask);
		popupMenu.add(removeTask);

		area = new Dimension(0, 0);
		scrollArea = new Dimension(190, 450);
		paneArray = new ArrayList<>(5);
		paneNames = new String[] { "Selected for Development", "In Progress", "Development Done",
				"Peer Review", "Finished" };
		typeNames = new String[] { "Bug", "Feature", "Story" };

		for (int i = 0; i < 5; i++) {
			Border outerBorder = BorderFactory.createTitledBorder(paneNames[i]);
			Border innerBorder = BorderFactory.createEmptyBorder();

			JLayeredPane stages = new JLayeredPane();
			stages.setPreferredSize(area);
			stages.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			stages.setLayout(new FlowLayout());
			stages.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			scroll = new JScrollPane(stages);
			scroll.setPreferredSize(scrollArea);
			scroll.setWheelScrollingEnabled(true);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroll.getVerticalScrollBar().setUnitIncrement(10);

			paneArray.add(stages);
			add(scroll, 100, 0);
		}
	}

//	public void placeCard(String issueId, String name, String date) {
//		Point origin = new Point(10, 20);
//		task = new JPanel();
//		task.setPreferredSize(new Dimension(135, 125));
//		task.setForeground(Color.WHITE);
//		task.setEnabled(false);
//		task.setBounds(origin.x, origin.y, 100, 100);
//		task.setComponentPopupMenu(popupMenu);
//		Border outerBorder = BorderFactory.createLineBorder(Color.BLACK);
//		Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
//		task.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
//		
//		task.setName(issueId);
//		task.setLayout(new FlowLayout());
//		JTextField taskName = new JTextField(25);
//		JTextField taskDate = new JTextField(25);
//		taskName.setPreferredSize(new Dimension(25, 5));
//		taskDate.setPreferredSize(new Dimension(25, 5));
//		taskName.setText(name);
//		taskDate.setText(date);
//		add(taskName);
//		add(taskDate);
//		
//	}

	public void addCard(String issueId, String cardText) {
		Point origin = new Point(10, 20);
		Border outerBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		
		card = new JTextArea();
		card.setPreferredSize(new Dimension(135, 125));
		card.setForeground(Color.WHITE);
		// card.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		card.setEditable(false);
		card.setLineWrap(true);
		card.setBounds(origin.x, origin.y, 100, 100);
		card.setComponentPopupMenu(popupMenu);
		card.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		card.setText(cardText);

		if (stage == Status.SelectedForDevelopment) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(0).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(0).setPreferredSize(new Dimension(190, area.height));

			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(0).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.InProgress) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.DevelopmentDone) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.PeerReview) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.Finished) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
				area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
			}
		}
	}
	
	public void addCard(String issueId, String cardText, String status, String type) {
		Point origin = new Point(10, 20);

		card = new JTextArea();
		card.setPreferredSize(new Dimension(135, 125));
		card.setForeground(Color.WHITE);
		card.setEditable(false);
		card.setLineWrap(true);
		card.setBounds(origin.x, origin.y, 100, 100);
		card.setComponentPopupMenu(popupMenu);
		Border outerBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		card.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		card.setText(cardText);
		
		for(String s: paneNames) {
			if(s == status) {
				for(String t: typeNames) {
					if(t == type) {
						
					}
				}
			}
		}
	}

	public void tasksLoaded() {
//		I want to create cards for each task that is uploaded
		System.out.println("Success");
	}

	public void editCard(int paneNo, int cardNo) {
		paneArray.get(paneNo).getComponent(cardNo);
		JFrame editFrame = new JFrame();
		editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		editFrame.setSize(new Dimension(310, 550));
		editFrame.setVisible(true);
		editFrame.setLayout(new FlowLayout());
		editFrame.setResizable(true);
		editFrame.setLocationRelativeTo(null);
		editFrame.setTitle("Edit Task");
		editFrame.add(editPanel);
		editPanel = new JPanel();
		editPanelParams();
	}

	Status stage = null;

	public void receiveStatus(Status state) {
		this.stage = state;
	}

	Tip type = null;

	public void receiveType(Tip type) {
		this.type = type;
	}

	public void editPanelParams() {
		JTextField taskName = new JTextField(10);
		editPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(10, 5, 0, 0);
		add(new JLabel("Task Name: "), gbc);

		gbc.gridx++;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.insets = new Insets(10, 0, 0, 5);
		add(taskName, gbc);
	}

	public void actionPerformed(ActionEvent e) {
		JMenuItem clicked = (JMenuItem) e.getSource();
		if (clicked == editTask) {
			System.out.println("edit task clicked");
			if (listener != null) {
				try {
					int check = JOptionPane.showConfirmDialog(KanbanPanel.this, "Are you sure", "Wait",
							JOptionPane.OK_OPTION);
					if (check == 0) {
						listener.cardRemoved(paneId, cardNo, cardComp);
						System.out.println("Pane id is: " + paneId + "Card No is: " + cardNo);
					}
				} catch (Exception ex) {
					ex.getMessage();
					System.out.println("Pane id is: " + paneId + " Card No is: " + cardNo);
					System.out.println("You cannot remove this card");
				}
			}
		}
	}
}