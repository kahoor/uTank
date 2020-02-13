import java.awt.*;

public class Laser extends Bullet {
    final static int RADIUS = 4;
    final static int LIFE = 100; // step

    Laser(int x, int y, double direction, Tank t) {

        super(x, y, 1, direction, RADIUS, t, LIFE);

    }

    void draw(Graphics graphics) {
        graphics.setColor(Color.green);
        graphics.fillOval((int)this.x - Shot.RADIUS, (int)this.y - Shot.RADIUS,
                Shot.RADIUS * 2, Shot.RADIUS * 2);
    }



}