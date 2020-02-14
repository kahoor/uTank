import javax.swing.*;

public class GameFrame extends JFrame{
    private Menu menu;
    private GamePlay gameplay;
    boolean started = false;




    public GameFrame(Menu m,GamePlay g, int WIDTH, int HEIGHT){
        this.setSize(WIDTH, HEIGHT);
//        g.setMax_ammo(m.slider_ammo.getValue());
        menu = m;
        gameplay = g;
//        gameplay.setMax_ammo(menu.slider_ammo.getValue());
        this.setResizable(false);
        gameplay.setVisible(true);
        this.add(gameplay);
    }

    public void showMenu(){
        this.remove(this.gameplay);
        this.gameplay.setVisible(false);
        this.menu.setVisible(true);
        menu.checkContinue();
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



        if (this.menu.newGame) {
            gameplay.setMax_ammo(menu.slider_ammo.getValue());
            gameplay.setGOAL(menu.slider_goal.getValue());
            this.menu.gameStarted = true;
            this.menu.isClicked = true;
            this.menu.newGame = false;
        }


        if (this.gameplay.listener.escape){

            this.showGamePlay();
        }
        else this.showMenu();
        if (this.menu.isClicked) {
            this.gameplay.listener.escape = true;
            this.menu.isClicked = false;
        }
        if( gameplay.getPlayer1().points == menu.slider_goal.getValue() ||
                gameplay.getPlayer2().points == menu.slider_goal.getValue()) {
            this.gameplay.listener.escape = false;
            this.menu.gameStarted = false;
            gameplay.getPlayer1().points = 0;
            gameplay.getPlayer2().points = 0;
        }
    }

    void newGame(){

    }
}

