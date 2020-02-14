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
        this.gameplay.listener.escape = false;
        this.remove(this.gameplay);
        this.gameplay.setVisible(false);
        this.menu.setVisible(true);
        menu.checkContinue();
        this.add(this.menu);
        menu.requestFocusInWindow();
    }

    public void showGamePlay() {
        this.gameplay.listener.escape = true;
        this.remove(this.menu);
        this.menu.setVisible(false);
        this.gameplay.setVisible(true);
        this.gameplay.updateState();
        this.add(this.gameplay);
        gameplay.requestFocusInWindow();
    }

    public void updateState(){



        if (this.menu.newGame) {
            gameplay.setMap(menu.getMap());
            gameplay.setMax_ammo(menu.slider_ammo.getValue());
            gameplay.setGOAL(menu.slider_goal.getValue());
            this.gameplay.removeKeyListener(this.gameplay.listener);
            gameplay.listener = new GameActionListener(this.menu.listen[0],this.menu.listen[1],this.menu.listen[2],this.menu.listen[3],
                    this.menu.listen[4],this.menu.listen[5],this.menu.listen[6],this.menu.listen[7]);
            this.gameplay.addKeyListener(this.gameplay.listener);
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
            if ( gameplay.getPlayer1().points == menu.slider_goal.getValue()) this.menu.showWinner("Last Game Winner : Player 1!");
            if ( gameplay.getPlayer2().points == menu.slider_goal.getValue()) this.menu.showWinner("Last Game Winner : Player 2!");
            this.gameplay.listener.escape = false;
            this.menu.gameStarted = false;
            gameplay.getPlayer1().points = 0;
            gameplay.getPlayer2().points = 0;
        }
    }

}

