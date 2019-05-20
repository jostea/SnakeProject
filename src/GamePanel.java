import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    private static final int width = 500;
    private static final int height = 500;
    private Thread thread;
    private boolean running;
    private int score = 0;
    //Keys for controls
    private boolean right = true;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;

    private SnakeBody snakeBody;
    private List<SnakeBody> snake;

    private Apple apple;
    private List<Apple> apples;

    private Random r;

    private int xCoordinates = 10;
    private int yCoordinates = 10;
    private int size = 5;
    private int ticks = 0;


    public GamePanel() {

        setFocusable(true);

        setPreferredSize(new Dimension(width, height));
        addKeyListener(this);
        snake = new ArrayList<>();
        apples = new ArrayList<>();
        r = new Random();
        start();

    }

    private void start() {
        running = true;
        thread = new Thread(this);
        thread.start();

    }

    private void stop() {
        running = false;

    }

    private void tick() {
        if (snake.size() == 0) {
            snakeBody = new SnakeBody(xCoordinates, yCoordinates, 10);
            snake.add(snakeBody);
        }
        ticks++;
        if (ticks > 250000) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (right) xCoordinates++;
            if (left) xCoordinates--;
            if (up) yCoordinates--;
            if (down) yCoordinates++;
            ticks = 0;
            snakeBody = new SnakeBody(xCoordinates, yCoordinates, 10);
            snake.add(snakeBody);
            if (snake.size() > size) {
                snake.remove(0);
            }
            if (apples.size() == 0) {
                int xCoor = r.nextInt(49);
                int yCoor = r.nextInt(49);
                apple = new Apple(xCoor, yCoor, 10);
                apples.add(apple);
            }
            for (int i = 0; i < apples.size(); i++) {
                if (xCoordinates == apples.get(i).getxCoordinates()
                        && yCoordinates == apples.get(i).getyCoordinates()) {
                    size++;
                    apples.remove(i);
                    score++;
                    i++;

                }
            }
        }

        for (int i = 0; i < snake.size(); i++) {
            if (xCoordinates == snake.get(i).getxCoordinates()
                    && yCoordinates == snake.get(i).getyCoordinates()) {
                if (i != snake.size() - 1) {
                    stop();
                }
            }
        }


        if (xCoordinates < 0 || xCoordinates > 49 || yCoordinates < 0 ||
                yCoordinates > 49) {
            stop();
        }
    }

    public void paint(Graphics graphics) {

        graphics.setColor(Color.BLACK);
        graphics.clearRect(0, 0, width, height);
        graphics.fillRect(0, 0, width, height);

        for (int i = 0; i < width / 10; i++) {
            graphics.drawLine(i * 10, 0, i * 10, height);
        }
        for (int i = 0; i < height / 10; i++) {
            graphics.drawLine(0, i * 10, height, i * 10);
        }
        for (SnakeBody snakeBody : snake) {
            snakeBody.draw(graphics);
        }

        for (Apple apple : apples) {
            apple.draw(graphics);
        }
        if (!running) {
            graphics.setColor(Color.GREEN);
            graphics.drawString("Game Over", 220, 250);
            graphics.drawString("Your score was:" + score, 210, 260);
            graphics.drawString("Press R to restart",210,270);
        }

    }

    @Override
    public void run() {
        while (running) {
            tick();
            repaint();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT && !left) {
            right = true;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_LEFT && !right) {
            left = true;
            up = false;
            down = false;
        }
        if (key == KeyEvent.VK_UP && !down) {
            up = true;
            left = false;
            right = false;
    }
        if (key == KeyEvent.VK_DOWN && !up) {
            down = true;
            left = false;
            right = false;
        }
        if(key==KeyEvent.VK_R){
             Snakes.frame.dispose();
            new Snakes();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
