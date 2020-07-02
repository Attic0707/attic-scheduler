package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
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

import model.Issue;

public class TaskCards extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLayeredPane layeredPane;
	private JTextArea card;
	private String[] paneNames;
	private ArrayList<JLayeredPane> paneArray;
	private ArrayList<JTextArea> cardArray;
	private List<Issue> listFromDatabase;
	private JPopupMenu popupMenu;
	private TaskCardListener listener;
	private int paneId, cardNo;
	private Component cardComp;
	private JPanel editPanel;
	private static int count = 0;
	private int cardNumber;
	private JScrollPane scroll;
	private Dimension area;
	private Dimension scrollArea;

	public void setCardListener(TaskCardListener listen) {
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

	public TaskCards() {
		setLayout(new BoxLayout(this, 1));
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension());
		layeredPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		layeredPane.setLayout(new FlowLayout());

		popupMenu = new JPopupMenu();
		JMenuItem editTask = new JMenuItem("Edit Task");
		JMenuItem removeTask = new JMenuItem("Remove Task");

		editTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					try {
						listener.editCard(paneId, cardNo);
					} catch (Exception exc) {
						System.out.println("I dunno man, just do it");
					}
				}
			}
		});

		removeTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					try {
						int check = JOptionPane.showConfirmDialog(TaskCards.this, "Are you sure", "Wait",
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
		});

		popupMenu.add(editTask);
		popupMenu.add(removeTask);

		area = new Dimension(0, 0);
		scrollArea = new Dimension(190, 450);
		paneArray = new ArrayList<>(5);
		cardArray = new ArrayList<>();
		paneNames = new String[] { "Backlog", "Selected for Development", "In Progress", "Development Done",
				"Peer Review", "Finished" };

		for (int i = 1; i < 6; i++) {
			JLayeredPane stages = new JLayeredPane();
			stages.setPreferredSize(area);
			scroll = new JScrollPane(stages);
			scroll.setPreferredSize(scrollArea);
			scroll.setWheelScrollingEnabled(true);
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			scroll.getVerticalScrollBar().setUnitIncrement(10);
			scroll.setAutoscrolls(true);
			Border outerBorder = BorderFactory.createTitledBorder(paneNames[i]);
			Border innerBorder = BorderFactory.createEmptyBorder();
			stages.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
			stages.setLayout(new FlowLayout());
			stages.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			paneArray.add(stages);
			layeredPane.add(scroll, 100, 0);
		}
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(layeredPane);
	}

	public void addCard() {
		Point origin = new Point(10, 20);
		int offset = 30;

		card = new JTextArea();
		cardNumber = count;
		count++;
		card.setPreferredSize(new Dimension(135, 125));
		card.setForeground(Color.WHITE);
		card.setEditable(false);
		card.setLineWrap(true);
		card.setBounds(origin.x, origin.y, 100, 100);
		card.setComponentPopupMenu(popupMenu);
		card.setText(listFromDatabase.get(cardNumber).toString());
		Border outerBorder = BorderFactory.createLineBorder(Color.BLACK);
		Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		card.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		MouseAdapter myMouse = new MouseAdapter() {
			private Point p0;
			private Point loc0;

			public void mousePressed(MouseEvent e) {
				p0 = e.getLocationOnScreen();
				loc0 = ((Component) e.getSource()).getLocation();
				cardNo = cardArray.indexOf(e.getComponent());
				paneId = paneArray.indexOf(e.getComponent().getParent());
				cardComp = e.getComponent();
				System.out.println("The pane I clicked is: " + paneId);
				System.out.println("Card number you clicked is:" + cardNo);
			}

			private void moveCard(MouseEvent e) {
				if (p0 == null || loc0 == null) {
					return;
				}
				Point p1 = e.getLocationOnScreen();
				JComponent comp = (JComponent) e.getComponent();
				Container cont = comp.getParent();
				int x = loc0.x + p1.x - p0.x;
				int y = loc0.y + p1.y - p0.y;
				Point loc1 = new Point(x, y);
				comp.setLocation(loc1);
				cont.repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				moveCard(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				moveCard(e);
				p0 = null;
				loc0 = null;
			}
		};

		card.addMouseListener(myMouse);
		card.addMouseMotionListener(myMouse);

		if (stage == Status.Backlog) {
			cardArray.add(card);
			card.setVisible(false);
		} else if (stage == Status.SelectedForDevelopment) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(0).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(0).setPreferredSize(new Dimension(190, area.height));

			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(0).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(0).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(0).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.InProgress) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(1).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(1).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(1).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.DevelopmentDone) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(2).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(2).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(2).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.PeerReview) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(3).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(3).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(3).setPreferredSize(new Dimension(190, area.height));
			}

		} else if (stage == Status.Finished) {
			if (type == Tip.Bug) {
				card.setBackground(new Color(242, 24, 86));
				paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Feature) {
				card.setBackground(new Color(6, 50, 140));
				paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
			} else if (type == Tip.Story) {
				card.setBackground(new Color(14, 153, 51));
				paneArray.get(4).add(card, JLayeredPane.PALETTE_LAYER);
				cardArray.add(card);
				area.height = (card.getHeight() * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER))
						+ (40 * paneArray.get(4).getComponentCountInLayer(JLayeredPane.PALETTE_LAYER));
				paneArray.get(4).setPreferredSize(new Dimension(190, area.height));
			}

			origin.x += offset;
			origin.y += offset;
			layeredPane.revalidate();
			layeredPane.repaint();

		}
		System.out.println("The size of my card array is: " + cardArray.size());
		System.out.println("The size of my database is:   " + listFromDatabase.size());
	}

	public void removeCard(int paneNo, int cardNo, Component cardComp) {
		if (cardArray.size() != 0) {
			paneArray.get(paneNo).remove(cardComp);
			cardArray.remove(cardNo);
			count--;
			System.out.println("new cardArray size: " + cardArray.size());
			System.out.println("new database size: " + listFromDatabase.size());
			layeredPane.revalidate();
			layeredPane.repaint();
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

	public void setData(List<Issue> issueList) {
		this.listFromDatabase = issueList;
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
}
