import java.awt.*;

public class PowerUp extends Thing{
    private final static int RADIUS = 10;
    String powerUpType;

    PowerUp(int x, int y, String s){
        super(x,y);
        powerUpType = s;
    }

    void draw(Graphics graphics) {
        if (powerUpType == "laser")
            graphics.setColor(Color.green);
        if (powerUpType == "mine")
            graphics.setColor(Color.BLUE);
        graphics.fillOval((int)this.x - RADIUS, (int)this.y - RADIUS,
                RADIUS * 2, RADIUS * 2);
    }

    boolean contact(Tank t){
        double distance = Math.sqrt( ((this.x - t.x)*(this.x - t.x)) + ((this.y - t.y)*(this.y - t.y)));

        if ((distance <= (int) RADIUS+ (int) t.getRADIUS())) {
            return true;
        }
        return false;
    }

}
