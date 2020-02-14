import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameActionListener extends KeyAdapter {
    boolean p1Move, p1Left, p1Right, p1Fire,
            p2Move, p2Left, p2Right, p2Fire, p1shoot, p2shoot,
            escape = false;



    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.p1Left = true;
                break;
            case KeyEvent.VK_RIGHT:
                this.p1Right = true;
                break;
            case KeyEvent.VK_UP:
                this.p1Move = true;
                break;
            case KeyEvent.VK_M:
                if (!p1Fire)
                    this.p1Fire = true;

                break;
            case KeyEvent.VK_A:
                this.p2Left = true;
                break;
            case KeyEvent.VK_D:
                this.p2Right = true;
                break;
            case KeyEvent.VK_W:
                this.p2Move = true;
                break;
            case KeyEvent.VK_F:
                this.p2Fire = true;
                break;
            // ... handle other keys
        }
        e.consume();
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.p1Left = false;
                break;
            case KeyEvent.VK_RIGHT:
                this.p1Right = false;
                break;
            case KeyEvent.VK_UP:
                this.p1Move = false;
                break;
            case KeyEvent.VK_M:
                this.p1Fire = false;
                this.p1shoot = false;
                break;
            case KeyEvent.VK_A:
                this.p2Left = false;
                break;
            case KeyEvent.VK_D:
                this.p2Right = false;
                break;
            case KeyEvent.VK_W:
                this.p2Move = false;
                break;
            case KeyEvent.VK_F:
                this.p2Fire = false;
                this.p2shoot = false;
                break;
            case KeyEvent.VK_ESCAPE:
                System.out.println("Escape entered");
                this.escape = !this.escape;
                // ... handle other keys
        }
        e.consume();
    }
}
