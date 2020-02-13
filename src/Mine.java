import java.awt.*;

public class Mine extends Bullet {
    final static int RADIUS = 10;
    final static int LIFE = 800; // step
    boolean visible;


    Mine(int x, int y, Tank t) {
        super(x, y, 0, 0, RADIUS, t, LIFE);
        visible=false;
    }
    void draw(Graphics graphics) {
        if (visible) {
            graphics.setColor(Color.gray);
            graphics.fillOval((int)this.x - Mine.RADIUS, (int)this.y - Mine.RADIUS,
                    Mine.RADIUS * 2, Mine.RADIUS * 2);
        }
    }

    void visibility(Tank t){
        double distance = Math.sqrt( ((this.x - t.x)*(this.x - t.x)) + ((this.y - t.y)*(this.y - t.y)));

        if ((distance <=  80 + (int) RADIUS+ (int) t.getRADIUS())) {
            this.visible = true;
        }

    }


}