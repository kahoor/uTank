import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel{
    static JButton button_continue, button_new;
    public boolean isClicked = false;
    static int ammo = 25, goal = 25;
    public JSlider slider_ammo, slider_goal;
    static boolean change = false;
    public boolean newGame = false;
    public boolean gameStarted = false;
    public JLabel winner = new JLabel();
    int[] listen = new int[8];
    public ButtonGroup G1 = new ButtonGroup();
    public JRadioButton map1 = new JRadioButton(), map2= new JRadioButton(),
            map3= new JRadioButton(), map4= new JRadioButton(), map5= new JRadioButton();


    public void showWinner(String winner){
        this.winner.setText(winner);
        this.winner.setBounds(370, 660, 200, 30);
        this.winner.setForeground(Color.GREEN);
        this.add(this.winner);
    }


    public void checkContinue(){
        if (!gameStarted){
            this.remove(button_continue);
        }
        else this.add(button_continue);
    }

    public String getMap(){
        if (map1.isSelected()) return map1.getText();
        if (map2.isSelected()) return map2.getText();
        if (map3.isSelected()) return map3.getText();
        if (map4.isSelected()) return map4.getText();
        if (map5.isSelected()) return map5.getText();
        return "";
    }

    public void main() {

        this.setBackground(Color.black);

        // button_exit:
        JButton button_exit = new JButton("EXIT");
        button_exit.setBounds(30, 620, 100, 30);
        button_exit.setBackground(Color.GRAY);
        this.add(button_exit);
        button_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        // ammo:
        slider_ammo = new JSlider(JSlider.HORIZONTAL, 0, 50, 50);
        slider_ammo.setForeground(Color.white);
        slider_ammo.setBackground(Color.black);
        slider_ammo.setBounds(150, 200, 200, 50);
        slider_ammo.setMinorTickSpacing(2);
        slider_ammo.setMajorTickSpacing(10);
        slider_ammo.setPaintTicks(true);
        slider_ammo.setPaintLabels(true);
        this.add(slider_ammo);
        JLabel label_ammo = new JLabel("AMMO :");
        label_ammo.setBounds(75, 195, 80, 30);
        label_ammo.setForeground(Color.WHITE);
        this.add(label_ammo);
        // goal:
        slider_goal = new JSlider(JSlider.HORIZONTAL, 0, 50, 50);
        slider_goal.setForeground(Color.white);
        slider_goal.setBackground(Color.black);
        slider_goal.setBounds(150, 100, 200, 50);
        slider_goal.setMinorTickSpacing(2);
        slider_goal.setMajorTickSpacing(10);
        slider_goal.setPaintTicks(true);
        slider_goal.setPaintLabels(true);
        this.add(slider_goal);
        JLabel label_goal = new JLabel("GOAL :");
        label_goal.setBounds(75, 95, 80, 30);
        label_goal.setForeground(Color.WHITE);
        this.add(label_goal);

        JLabel label_utank = new JLabel("UTanks");
        label_utank.setBounds(210, 20, 200, 70);
        label_utank.setFont(new Font(label_utank.getFont().getName(), Font.BOLD, 20));
        label_utank.setForeground(Color.WHITE);

        this.add(label_utank);
        char[][] keys = new char[2][4];
        JButton[][] button_keys = new JButton[2][4];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                button_keys[i][j] = new JButton();

            }
        }

        button_keys[0][0].setText("");
        button_keys[0][1].setText("");
        button_keys[0][2].setText("");
        button_keys[0][3].setText("");
        button_keys[1][0].setText("");
        button_keys[1][1].setText("");
        button_keys[1][2].setText("");
        button_keys[1][3].setText("");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                button_keys[i][j].setBounds(100 + 300 * i, 350 + 21 * j, 80, 20);
                this.add(button_keys[i][j]);

            }
        }


        JLabel labelrac = new JLabel("Rotate anti-clock");
        JLabel labelrc = new JLabel("Rotate clock");
        JLabel move = new JLabel("Move");
        JLabel fire = new JLabel("Fire");
        labelrac.setBounds(200,350, 100,20);
        labelrac.setVisible(true);
        labelrac.setForeground(Color.yellow);
        this.add(labelrac);

        labelrc.setBounds(200,371, 100,20);
        labelrc.setVisible(true);
        labelrc.setForeground(Color.yellow);
        this.add(labelrc);

        move.setBounds(200,392, 100,20);
        move.setVisible(true);
        move.setForeground(Color.yellow);
        this.add(move);

        fire.setBounds(200,413, 100,20);
        fire.setVisible(true);
        fire.setForeground(Color.yellow);
        this.add(fire);


        JLabel label_player1 = new JLabel("PLAYER1 :");
        label_player1.setBounds(20, 340, 100, 30);
        label_player1.setFont(new Font(label_player1.getFont().getName(), Font.BOLD, 13));
        label_player1.setForeground(Color.WHITE);
        this.add(label_player1);

        JLabel label_player2 = new JLabel("PLAYER2 :");
        label_player2.setBounds(320, 340, 100, 30);
        label_player2.setFont(new Font(label_player2.getFont().getName(), Font.BOLD, 13));
        label_player2.setForeground(Color.WHITE);
        this.add(label_player2);


        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                final int a = i, b = j;
                button_keys[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        change = true;
                    }
                });
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                final int a = i, b = j;
                button_keys[i][j].addKeyListener(new KeyListener() {
                    public void keyPressed(KeyEvent e) {
                        String s = "";
                        s += e.getKeyChar();
                        listen[a*4 + b] = e.getKeyCode();
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                            s = "up";
                        }
                        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            s = "left";
                        }
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            s = "right";
                        }
                        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                            s = "down";
                        }

                        if (change) {
                            button_keys[a][b].setText(s);
                            change = false;
                        }
                    }

                    public void keyReleased(KeyEvent e) {
                        return;
                    }

                    public void keyTyped(KeyEvent e) {
                        return;
                    }
                });
            }
        }

        // button_continue:
        button_continue = new JButton("CONTINUE");
        button_continue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isClicked = true;
                ammo = slider_ammo.getValue();
                goal = slider_goal.getValue();


            }
        });
        button_continue.setBounds(370, 620, 100, 30);
        button_continue.setBackground(Color.GRAY);

        //button new game
        button_new = new JButton("NEW GAME");
        button_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newGame = true;
                ammo = slider_ammo.getValue();
                goal = slider_goal.getValue();
            }
        });
        button_new.setBounds(200, 620, 100, 30);
        button_new.setBackground(Color.GRAY);
        this.add(button_new);


        map1.setText("map1");
        map1.setBounds(500,300,100,30);
        this.add(map1);
        map2.setText("map2");
        map2.setBounds(500,340,100,30);
        this.add(map2);
        map3.setText("map3");
        map3.setBounds(500,380,100,30);
        this.add(map3);
        map4.setText("map4");
        map4.setBounds(500,420,100,30);
        this.add(map4);
        map5.setText("map5");
        map5.setBounds(500,460,100,30);
        this.add(map5);

        G1.add(map1); G1.add(map2); G1.add(map3); G1.add(map4); G1.add(map5);


        this.setSize(1024, 768);
        this.setLayout(null);
        this.setVisible(true);

    }
}
