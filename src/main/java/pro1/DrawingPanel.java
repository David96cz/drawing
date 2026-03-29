package pro1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    private final List<Arrow> arrows = new ArrayList<>();
    private boolean useRedColor = false;
    private int currentAngle = 0;

    public DrawingPanel() {
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                arrows.add(new Arrow(e.getX(), e.getY(), currentAngle));
                repaint();
            }
        });
    }

    public void setCurrentAngle(int angle) {
        this.currentAngle = angle;
    }

    public void setUseRedColor(boolean useRedColor) {
        this.useRedColor = useRedColor;
        repaint();
    }

    public void clearArrows() {
        arrows.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Arrow arrow : arrows) {
            arrow.draw(g2d, useRedColor);
        }
    }
}