import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameActionListener extends KeyAdapter {
    boolean p1Move, p1Left, p1Right, p1Fire,
            p2Move, p2Left, p2Right, p2Fire, p1shoot, p2shoot,
            escape = false;

    public int oneLeft = 37 , oneRight = 39, oneMove = 38, oneFire = 77, twoLeft = 65, twoRight = 68, twoMove = 87, twoFire = 70;

    public GameActionListener(int oneLeft, int oneRight, int oneMove, int oneFire,
                              int twoLeft, int twoRight, int twoMove, int twoFire){
        this.oneFire = oneFire;
        this.oneRight = oneRight;
        this.oneLeft = oneLeft;
        this.oneMove = oneMove;
        this.twoRight = twoRight;
        this.twoLeft = twoLeft;
        this.twoFire = twoFire;
        this.twoMove = twoMove;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == this.oneLeft) {
            this.p1Left = true;
        }
        if (e.getKeyCode() == this.oneRight)
            this.p1Right = true;
        if (e.getKeyCode() == this.oneMove)
            this.p1Move = true;
        if (e.getKeyCode() == this.oneFire)
            if (!p1Fire)
                this.p1Fire = true;
        if (e.getKeyCode() == this.twoLeft)
            this.p2Left = true;
        if (e.getKeyCode() == this.twoRight)
            this.p2Right = true;
        if (e.getKeyCode() == this.twoMove)
            this.p2Move = true;
        if (e.getKeyCode() == this.twoFire)
            if (!p2Fire)
                this.p2Fire = true;

        e.consume();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == this.oneLeft)
            this.p1Left = false;
        if (e.getKeyCode() == this.oneRight)
            this.p1Right = false;
        if (e.getKeyCode() == this.oneMove)
            this.p1Move = false;
        if (e.getKeyCode() == this.oneFire) {
            this.p1Fire = false;
            this.p1shoot = false;
        }
        if (e.getKeyCode() == this.twoLeft)
            this.p2Left = false;
        if (e.getKeyCode() == this.twoRight)
            this.p2Right = false;
        if (e.getKeyCode() == this.twoMove)
            this.p2Move = false;
        if (e.getKeyCode() == this.twoFire){
            this.p2shoot = false;
            this.p2Fire = false;
        }
        if (e.getKeyCode() == 27) {
            this.escape = !this.escape;
        }

        e.consume();
    }
}
