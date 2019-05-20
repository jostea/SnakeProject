import javax.swing.*;

public class Snakes {

     static JFrame frame;

    public Snakes() {
        frame = new JFrame();
        GamePanel gamepanel = new GamePanel();
        frame.add(gamepanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Snake Game");
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Snakes();
    }



}
