import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JPanel{
    static JButton button_start;
    public boolean isClicked = false;
    static int ammo = 25, goal = 25;
    static JSlider slider_ammo, slider_goal;
    static boolean change = false;


    public void main() {

        this.setBackground(Color.black);
        // button_start:
        button_start = new JButton("START");
        button_start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isClicked = true;


//                this.setVisible(false);
                ammo = slider_ammo.getValue();
                goal = slider_goal.getValue();


            }
        });
        // button_start.setBorder(BorderFactory.createEtchedBorder(0));
        button_start.setBounds(370, 620, 100, 30);
        button_start.setBackground(Color.GRAY);
        this.add(button_start);
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
        slider_ammo = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
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
        this.add(label_ammo);
        // goal:
        slider_goal = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
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

        button_keys[0][0].setText("W");
        button_keys[0][1].setText("A");
        button_keys[0][2].setText("D");
        button_keys[0][3].setText("F");
        button_keys[1][0].setText("up");
        button_keys[1][1].setText("left");
        button_keys[1][2].setText("right");
        button_keys[1][3].setText("M");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                button_keys[i][j].setBounds(100 + 300 * i, 350 + 21 * j, 80, 20);
                this.add(button_keys[i][j]);

            }
        }
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
//                        System.out.println(a + 100 * b);
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
//                        System.out.println(e.getKeyChar());
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

        this.setSize(1024, 768);
        this.setLayout(null);
        this.setVisible(true);

    }
}
