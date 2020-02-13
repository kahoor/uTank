import java.awt.*;

public abstract class Thing {
    double x;
    double y;

     Thing(int x, int y) {
        this.x = x;
        this.y = y;
     }

    public double getX(){ return this.x; }
    public double getY(){ return this.y; }


    abstract void draw(Graphics graphics);

}