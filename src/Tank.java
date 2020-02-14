import java.awt.*;

public class Tank extends MovingThing {


    final static int RADIUS = 25;
    final static int GUN_LENGTH = 45;

    Color color;

    int tankPowerUpAge;
    public int shots;

    public String bulletType;

    Tank(int x, int y, double direction, Color color) {
        super(x, y, 3f, 0.06f, direction, 25);
        bulletType = "shot";
        tankPowerUpAge = 1000;
        this.color = color;

    }



    public void draw(Graphics graphics) {
        graphics.setColor(this.color);

        if (bulletType == "laser")
            graphics.setColor(Color.green);

        if (bulletType == "mine")
            graphics.setColor(Color.GRAY);

        graphics.drawOval(
                (int)this.x - Tank.RADIUS,
                (int)this.y - Tank.RADIUS,
                Tank.RADIUS * 2,
                Tank.RADIUS * 2
        );

        graphics.drawLine((int)this.x, (int)this.y, this.getGunX(), this.getGunY());

    }

    int getGunX() {
        return (int) Math.round(this.x + (Tank.GUN_LENGTH * Math.sin(this.direction)));
    }

    int getGunY() {
        return (int) Math.round(this.y + Tank.GUN_LENGTH * Math.cos(this.direction) ) ;
    }

    void powerUpGrowOld() { this.tankPowerUpAge--; }

    boolean powerUpIsDead() { return this.tankPowerUpAge <= 0; }


}
