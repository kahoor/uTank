public class Main {
    public static void main(String[] args) {

        Player player1 = new Player();
        Player player2 = new Player();

        int WIDTH = 1224;
        int HEIGHT = 768;

        Menu menu = new Menu();
        menu.main();
        GameActionListener listener = new GameActionListener(menu.listen[0],menu.listen[1],menu.listen[2],menu.listen[3],
                menu.listen[4],menu.listen[5],menu.listen[6],menu.listen[7]);
        GamePlay gamePlay = new GamePlay(player1, player2, menu.listen, WIDTH,
                HEIGHT, menu.slider_ammo.getValue(), menu.slider_goal.getValue(), menu.getMap());
        GameFrame gameFrame = new GameFrame(menu, gamePlay, WIDTH, HEIGHT);
        gamePlay.addKeyListener(listener);

        gameFrame.setVisible(true);

        long lastTime = System.currentTimeMillis();
        long delay = 25;
        while (true){
            gameFrame.updateState();

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

    }
}
