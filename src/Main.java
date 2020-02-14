import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {

        Player player1 = new Player();
        Player player2 = new Player();

        int WIDTH = 1024;
        int HEIGHT = 768;

        GameActionListener listener = new GameActionListener();
        GamePlay gamePlay = new GamePlay(player1, player2, listener, WIDTH, HEIGHT);
        Menu menu = new Menu();
        menu.main();
        GameFrame gameFrame = new GameFrame(menu, gamePlay, WIDTH, HEIGHT);
        gamePlay.addKeyListener(listener);
//        gamePlay.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                System.out.println("GAINED");
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                System.out.println("LOST");
//            }
//        });
        gameFrame.setVisible(true);

        long lastTime = System.currentTimeMillis();
        long delay = 25;
        while (true){
            gameFrame.updateState();
//            gamePlay.updateState();

            gameFrame.repaint();
            long currentTime = System.currentTimeMillis();
            long timeDiff = currentTime - lastTime;

            long sleep = delay - timeDiff;
            if (sleep < 0){
                sleep = 2;
            }
            try{
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lastTime = System.currentTimeMillis();


        }

//        new Timer(5, e -> {
//            gameFrame.repaint();
////            if (menu.isClicked) {
//                gamePlay.updateState();
//                gamePlay.repaint();
////            }
//        }).start();

//        GameScreen gameScreen = new GameScreen();
//        Menu menu = new Menu();
//        // gameScreen.main();
//        menu.main();

    }
}
