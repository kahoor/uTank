//import javax.swing.*;
//
//public class GameFrame extends JFrame{
//    private Menu menu;
//    private GamePlay gameplay;
//
//
//    public GameFrame(Menu m,GamePlay g, int WIDTH, int HEIGHT){
//        this.setSize(WIDTH, HEIGHT);
//        menu = m;
//        gameplay = g;
//        this.add(menu);
//        this.setResizable(false);
//
//    }
//
//
//
//
//}
import javax.swing.*;

public class GameFrame extends JFrame{
    private Menu menu;
    private GamePlay gameplay;




    public GameFrame(Menu m,GamePlay g, int WIDTH, int HEIGHT){
        this.setSize(WIDTH, HEIGHT);
        menu = m;
        gameplay = g;
        this.setResizable(false);
        gameplay.setVisible(true);
        this.add(gameplay);
    }

    public void showMenu(){
        this.remove(this.gameplay);
        this.gameplay.setVisible(false);
        this.menu.setVisible(true);
        this.add(this.menu);
    }

    public void showGamePlay() {
        this.remove(this.menu);
        this.menu.setVisible(false);
        this.gameplay.setVisible(true);
        this.gameplay.updateState();
        this.add(this.gameplay);
        gameplay.requestFocusInWindow();
    }

    public void updateState(){
        if (gameplay.listener.escape){

            this.showGamePlay();
        }
        else this.showMenu();
        if (this.menu.isClicked) {
            this.showGamePlay();
//            this.menu.isClicked = false;
        }
//        System.out.println(this.menu.isClicked);
    }
}

