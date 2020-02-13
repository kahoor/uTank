import java.awt.*;

public class Shot extends Bullet {
    final static int RADIUS = 10;
    final static int LIFE = 100; // step
    Color color;

    Shot(int x, int y, double direction, Tank t, Color color) {

        super(x, y, 1f, direction, RADIUS, t, LIFE);
        this.color = color;

    }

    void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval((int)this.x - Shot.RADIUS, (int)this.y - Shot.RADIUS,
            Shot.RADIUS * 2, Shot.RADIUS * 2);
    }



}