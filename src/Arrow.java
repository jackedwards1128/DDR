import java.awt.*;

public class Arrow {

    private Image arrowImage;
    private int direction;
    private double hitTime;
    private Game backend;
    private FrontEnd window;

    public Arrow(Game backend, FrontEnd window) {
        this.backend = backend;
        this.window = window;
        direction = (int)(Math.random() * 4);
        hitTime = backend.getTime() + 3;
        setArrowImage();
    }

    public Arrow(Game backend, FrontEnd window, double hitTime) {
        this.backend = backend;
        this.window = window;
        direction = (int)(Math.random() * 4);
        this.hitTime = hitTime;
        setArrowImage();
    }

    public Arrow(Game backend, FrontEnd window, int direction, double hitTime) {
        this.backend = backend;
        this.window = window;
        this.direction = direction;
        this.hitTime = hitTime;
        setArrowImage();
    }

    public void setArrowImage() {
        arrowImage = window.arrowImages[direction];
//        System.out.println(arrowImage);
    }

    public int getDirection() {
        return direction;
    }

    public double getHitTime() {
        return hitTime;
    }

    public void draw(Graphics g) {

        int drawYPos = (int)(50 + (hitTime - backend.getTime()) * backend.getArrowSpeed());

        if (drawYPos < 1000) {
            g.drawImage(arrowImage, (direction*161) + 100, drawYPos, 140,140, window);
        }

    }
}
