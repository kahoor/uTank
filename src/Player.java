import java.awt.*;
import java.util.Random;

public class Player {
    Tank tank;
    int points = 0;
    Random rand = new Random();


    void newRound(boolean hasWon, int startX, int startY, Color color, int maxShots) {
        if (hasWon)
            this.points++;


        this.tank = new Tank(startX, startY, Math.asin(rand.nextDouble()), color);
        this.tank.shots = maxShots;
    }



    Tank getTank(){return this.tank;}
}
