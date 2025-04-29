import java.awt.*;

public class Arrow {

    private Image arrowImage;
    private int direction;
    private double hitTime;
    private Game backend;
    private FrontEnd window;
    private boolean secondPlayer;

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

    public Arrow(Game backend, FrontEnd window, int direction, double hitTime, boolean second) {
        this.backend = backend;
        this.window = window;
        this.direction = direction;
        this.hitTime = hitTime;
        this.secondPlayer = second;
        setArrowImage();
        System.out.println("ive been made " + hitTime);
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

        int drawYPos = (int)(120 + (hitTime - backend.getTime()) * backend.getArrowSpeed());

        if (drawYPos < 1000) {
            if (window.getGameState() == "two player song") {
                if (secondPlayer)
                    g.drawImage(arrowImage, (direction*161) + 45, drawYPos, 140,140, window);
                else
                    g.drawImage(arrowImage, (direction*161) + 845, drawYPos, 140,140, window);
            } else
                g.drawImage(arrowImage, (direction*161) + 100, drawYPos, 140,140, window);

        }

    }
}
