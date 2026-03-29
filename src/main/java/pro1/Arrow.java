package pro1;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Arrow {
    private final int x;
    private final int y;
    private final int angle;

    public Arrow(int x, int y, int angle) {
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void draw(Graphics2D g2d, boolean isRed) {
        AffineTransform oldTransform = g2d.getTransform();

        g2d.translate(x, y);
        g2d.rotate(Math.toRadians(angle));

        g2d.setColor(isRed ? Color.RED : Color.GRAY);
        g2d.setStroke(new BasicStroke(3));

        g2d.drawLine(0, 0, -50, 0);
        g2d.drawLine(0, 0, -15, -10);
        g2d.drawLine(0, 0, -15, 10);

        g2d.setTransform(oldTransform);
    }
}