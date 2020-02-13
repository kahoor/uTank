import java.awt.*;

public abstract class Bullet extends MovingThing {
    public Tank tank;

    int age ;

    Bullet(int x, int y, float v, double d, int RADIUS, Tank t,int age) {

        super(x, y, v, 0, d, RADIUS);
        this.tank = t;
        this.age = age;
    }

    void growOld() { this.age--; }

    boolean isDead() { return this.age <= 0; }

    void bounceAgainst(Wall wall) {
        this.direction = (wall.isVertical? 0 : Math.PI) - this.direction;
    }
}
