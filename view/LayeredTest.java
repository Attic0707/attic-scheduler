package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class LayeredTest extends JPanel {

    public LayeredTest() {
        int gridRows = 20;
        int gridCols = 10;
        JPanel panelA = new JPanel(new GridLayout(gridRows, gridCols));
        for (int i = 0; i < gridRows; i++) {
            for (int j = 0; j < gridCols; j++) {
                String labelText = String.format("[%d, %d]", j + 1, i + 1);
                JLabel label = new JLabel(labelText);

                Border outsideBorder = BorderFactory.createLineBorder(Color.BLUE);
                Border insideBorder = BorderFactory.createEmptyBorder(3, 3, 3, 3);
                label.setBorder(BorderFactory.createCompoundBorder(outsideBorder, insideBorder));
                panelA.add(label);
            }
        }
        panelA.setBorder(BorderFactory.createTitledBorder("Panel A"));
        panelA.setSize(panelA.getPreferredSize());
        panelA.setLocation(0, 0);

    	JTextArea card = new JTextArea();
    	card.setText("Test Text");
    	
        JLabel dragMeLabel = new JLabel("Drag Me!");
        dragMeLabel.setFont(dragMeLabel.getFont().deriveFont(Font.BOLD, 54f));
        JPanel panelB = new JPanel(new GridBagLayout());
        panelB.add(dragMeLabel);
        panelB.setBorder(BorderFactory.createTitledBorder("Panel B"));
        panelB.setPreferredSize(new Dimension(800, 500));
        panelB.setSize(panelB.getPreferredSize());
        panelB.setLocation(200, 200);
        
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.add(panelA, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panelB, JLayeredPane.PALETTE_LAYER);
        layeredPane.setPreferredSize(new Dimension(600, 300));

        JScrollPane scrollPane = new JScrollPane(layeredPane);
        scrollPane.getViewport().setPreferredSize(new Dimension(800, 650));

        setLayout(new BorderLayout());
        add(scrollPane);

        MouseAdapter myMouse = new MouseAdapter() {
            private Point p0;
            private Point loc0;

            @Override
            public void mousePressed(MouseEvent e) {
                p0 = e.getLocationOnScreen();
                loc0 = ((JComponent) e.getSource()).getLocation();
            }

            private void moveComponent(MouseEvent e) {
                if (p0 == null || loc0 == null) {
                    return;
                }
                Point p1 = e.getLocationOnScreen();
                JComponent comp = (JComponent)e.getSource();
                Container cont = comp.getParent();
                int x = loc0.x + p1.x - p0.x;
                int y = loc0.y + p1.y - p0.y;
                Point loc1 = new Point(x, y);
                comp.setLocation(loc1);
                cont.repaint();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                moveComponent(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                moveComponent(e);
                p0 = null;
                loc0 = null;
            }
        };

        panelB.addMouseListener(myMouse);
        panelB.addMouseMotionListener(myMouse);

     
    }

    private static void createAndShowGui() {
        JFrame frame = new JFrame("LayeredExample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new LayeredTest());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGui());
    }
}