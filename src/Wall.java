import java.awt.*;

public class Wall extends Thing {

    public int width, height;
    boolean isVertical;

    public Wall(int x, int y, int width, int height) {
        super(x, y);
        this.height = height;
        this.width = width;
        if (width==1)
            isVertical = true;
        else
            isVertical=false;

    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.yellow);
        graphics.fillRect((int)this.getX(), (int)this.getY(), width, height);
    }

    boolean contacts(MovingThing moving) {
        boolean ans = false;

        int end;
        if (isVertical)
            end = (int)this.y+height+20;
        else
            end = (int)this.x+width+20;

        double distance;
        if (isVertical)
            distance = Math.abs(moving.getX()-this.getX());
        else
            distance = Math.abs(moving.getY()-this.getY());

        if (distance<=moving.getRADIUS() && moving instanceof Tank){
            if (isVertical && moving.getY()<=end && moving.getY()>=this.y-20)
                ans=true;
            if (!isVertical && moving.getX()<=end && moving.getX()>=this.x-20)
                ans=true;
        }

        if (distance<=moving.getRADIUS() && moving instanceof Bullet){
            if (isVertical && moving.getY()<=end-20 && moving.getY()>=this.y)
                ans=true;
            if (!isVertical && moving.getX()<=end-20 && moving.getX()>=this.x)
                ans=true;
        }
        return ans;
    }

}
