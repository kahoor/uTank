import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GamePlay extends JPanel {

    private List<Thing> everyThing = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private List<Bullet> shotsInTheAir = new ArrayList<>();
    private List<PowerUp> powerUpsInTheAir = new ArrayList<>();

    private Player player2;
    private Player player1;

    private int WIDTH, HEIGHT, Max_ammo, GOAL;

    GameActionListener listener;

    private Random rand = new Random();


    GamePlay(Player p1, Player p2, GameActionListener listener , int WIDTH, int HEIGHT, int max_ammo, int goal){
        this.WIDTH = WIDTH-200;
        this.HEIGHT = HEIGHT;
        player1=p1;
        player2=p2;
//        player1.getTank().shots = max_ammo;
//        player2.getTank().shots = max_ammo;
        this.setGOAL(goal);
        this.listener = listener;
        this.setMax_ammo(max_ammo);
        addWalls();
        gameStart(false, false);
    }

//    public boolean showGame(){ return this.listener.escape;}

    void addWalls(){
        try {
            File map = new File("map1.txt");
            Scanner reader = new Scanner(map);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] coords = data.split(",");
                this.walls.add(new Wall(Integer.parseInt(coords[0]),
                        Integer.parseInt(coords[1]),
                        Integer.parseInt(coords[2]),
                        Integer.parseInt(coords[3])));
            } reader.close();
        } catch (FileNotFoundException e){
            System.out.println("Map not found!");
        }

        Wall leftEdge = new Wall(20, 20, 1,HEIGHT - 60);
        this.walls.add(leftEdge);
        Wall topEdge = new Wall(20, 20, WIDTH - 40,1);
        this.walls.add(topEdge);
        Wall rightEdge = new Wall(WIDTH - 20, 20, 1, HEIGHT-60);
        this.walls.add(rightEdge);
        Wall bottomEdge = new Wall(20, HEIGHT - 40, WIDTH - 40,1);
        this.walls.add(bottomEdge);
    }

    private void gameStart(boolean p1win, boolean p2win) {
        this.setBackground(Color.black);

        System.out.println("setting tanks max ammo : " + this.getMax_ammo());

        this.player1.newRound(p1win, rand.nextInt(WIDTH-100)+50,
                rand.nextInt(HEIGHT-100)+50, Color.CYAN, this.getMax_ammo());
        this.player2.newRound(p2win, rand.nextInt(WIDTH-100)+50,
                rand.nextInt(HEIGHT-100)+50, Color.red, this.getMax_ammo());

        this.everyThing.add(player1.getTank());
        this.everyThing.add(player2.getTank());
    }

    void chekPowerUp(){
        String[] s = new String[2];
        s[0] = "mine";
        s[1] = "laser";

        if (rand.nextInt(60000)<500){
            addPowerUp(s[rand.nextInt(2)]);
        }
    }

    void addPowerUp(String type){
        PowerUp p = new PowerUp(rand.nextInt(WIDTH-60)+20, rand.nextInt(HEIGHT-60)+20, type);

        powerUpsInTheAir.add(p);
    }

    void powerUpHandler(Tank t){
        if (!(t.bulletType == "shot")){
            t.powerUpGrowOld();
            if(t.powerUpIsDead()){
                t.bulletType = "shot";
            }

        }

        for (PowerUp p: powerUpsInTheAir) {
            if (p.contact(t)){
                t.bulletType = p.powerUpType;
                powerUpsInTheAir.remove(p);
                t.tankPowerUpAge = 1000;
                break;
            }
        }



    }

    void fireHandler(Tank t){
        if (t.shots > 0) {
            t.shots--;
            if (t.bulletType == "shot") {

                this.shotsInTheAir.add(new Shot(
                        t.getGunX(), t.getGunY(), t.getDirection(), t, t.color
                ));
            }

            if (t.bulletType == "laser") {
                this.shotsInTheAir.add(new Laser(
                        t.getGunX(), t.getGunY(), t.getDirection(), t
                ));
            }

            if (t.bulletType == "mine") {
                this.shotsInTheAir.add(new Mine(
                        t.getGunX(), t.getGunY(), t
                ));
                t.bulletType = "shot";
            }
        }




    }

    void tankAction(Tank p1Tank, Tank p2Tank){
        if (listener.p1Move) {
//        System.out.println("yeahhh");
            p1Tank.step();

        }
        if (this.walls.stream().anyMatch(wall -> wall.contacts(p1Tank)))
            p1Tank.stepBack();

        powerUpHandler(p1Tank);

        if (listener.p1Left)
            p1Tank.turnLeft();
        if (listener.p1Right)
            p1Tank.turnRight();
        if (listener.p1Fire && !listener.p1shoot) {
            listener.p1shoot = true;
            fireHandler(p1Tank);

        }


        if (listener.p2Move )
            p2Tank.step();
        if (this.walls.stream().anyMatch(wall -> wall.contacts(p2Tank)))
            p2Tank.stepBack();

        powerUpHandler(p2Tank);

        if (listener.p2Left)
            p2Tank.turnLeft();
        if (listener.p2Right)
            p2Tank.turnRight();
        if (listener.p2Fire && !listener.p2shoot) {
            listener.p2shoot = true;
            fireHandler(p2Tank);
        }


    }

    void shootHandler(){
        boolean p1win = false;
        boolean p2win = false;

        Tank t ;
        for (Bullet shot: shotsInTheAir) {
            for (Wall wall : this.walls) {
                if (wall.contacts(shot) && (shot instanceof Shot)){
                    shot.bounceAgainst(wall);
                    while (wall.contacts(shot)){
                        shot.step();
                    }
                }
                shot.step();
            }


            if (shot.tank == this.player1.tank){
                t = this.player2.tank;
                p1win = true;
            }
            else {
                t = this.player1.tank;
                p2win = true;
            }

            if (shot instanceof Mine){
                ((Mine) shot).visibility(t);
            }

            double distance = Math.sqrt( ((shot.x - t.x)*(shot.x - t.x)) + ((shot.y - t.y)*(shot.y - t.y)));

            if ((distance <= (int) shot.getRADIUS()+ (int) t.getRADIUS())) {
                everyThing.clear();
                gameStart(p1win,p2win);
                shotsInTheAir.clear();
                powerUpsInTheAir.clear();
                break;
            }
        }
    }


    void updateState() {

//        System.out.println("yeahhh");
        this.shotsInTheAir.forEach(Bullet::growOld);
        this.shotsInTheAir.removeIf(Bullet::isDead);

        if (this.player1.getTank().shots > this.getMax_ammo()) this.player1.getTank().shots = this.getMax_ammo();
        if (this.player2.getTank().shots > this.getMax_ammo()) this.player2.getTank().shots = this.getMax_ammo();
        tankAction(this.player1.tank, this.player2.tank);


        shootHandler();

        chekPowerUp();

        // ... handle other game actions
    }

    public int getMax_ammo(){ return this.Max_ammo; }
    public void setMax_ammo( int maxAmmo ){ this.Max_ammo = maxAmmo; }

    public int getGOAL(){ return this.GOAL; }
    public void setGOAL( int GOAL ){ this.GOAL = GOAL; }

    public Player getPlayer1(){ return this.player1; }

    public Player getPlayer2(){ return this.player2; }

    public void paint(Graphics graphics) {

        graphics.setColor(Color.white);
        super.paint(graphics);
        graphics.setColor(Color.white);
        graphics.drawString("Points to win : " + this.getGOAL(),1094, 40);
        graphics.setColor(Color.CYAN);
        graphics.drawString("Player 1 :", 1044, 90);
        graphics.drawString(player1.points + " points", 1064, 110);
        graphics.drawString(player1.getTank().shots + " shots left", 1064, 130);
        graphics.setColor(Color.RED);
        graphics.drawString(player2.points + " points", 1064, 410);
        graphics.drawString("Player 2 :", 1044, 390);
        graphics.drawString(player2.getTank().shots + " shots left", 1064, 430);
        this.everyThing.forEach(thing -> thing.draw(graphics));
        this.shotsInTheAir.forEach(shot -> shot.draw(graphics));
        this.walls.forEach(wall -> wall.draw(graphics));
        this.powerUpsInTheAir.forEach(shot -> shot.draw(graphics));
        Toolkit.getDefaultToolkit().sync();
    }
}
