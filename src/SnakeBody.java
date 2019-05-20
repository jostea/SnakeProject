import java.awt.*;

public class SnakeBody {

    private int xCoordinates;
    private int yCoordinates;
    private int width;
    private int height;

    public SnakeBody(int x, int y, int tailSize) {
        setxCoordinates(x);
        setyCoordinates(y);
        setWidth(tailSize);
        setHeight(tailSize);
    }

    public void tick() {

    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(xCoordinates * width, yCoordinates * height, width, height);
    }

    public int getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(int xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public int getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(int yCoordinates) {
        this.yCoordinates = yCoordinates;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
