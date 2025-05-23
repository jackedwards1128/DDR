import java.awt.*;

public class Effect {
    // Instance variables
    private int type;
    private int arrow;
    private double originTime;
    private boolean secondPlayer;

    private Image image;
    private FrontEnd window;

    public Effect (FrontEnd window, int type, int arrow, double time, boolean secondPlayer) {
        this.originTime = time;
        this.window = window;
        this.type = type;
        this.arrow = arrow;
        this.secondPlayer = secondPlayer;

        // Depending on whether the image can be rotated or not, set the correct image to be loaded
        if (arrow == 0 || arrow == 3 || type == 3) {
            image = window.effectImages[type];
        } else {
            image = window.effectImages[type + 4];
        }
    }

    public void draw(Graphics g) {
        int offset = 0;

        if (window.getGameState() == "two player song") {
            offset = 744;
            if (secondPlayer)
                offset = -55;
        }

        // Draw the arrow at the correction position
        switch (arrow) {
            case 0:
                g.drawImage(image, offset + 87, 111, 160, 160, window);
                break;
            case 1:
                g.drawImage(image, offset + 246, 113, 160, 160, window);
                break;
            case 2:
                // Reflect over x-axis
                g.drawImage(image, offset + 408, 273, 160, -160, window);
                break;
            case 3:
                // Reflect over y-axis
                g.drawImage(image, offset + 734, 113, -160, 160, window);
                break;
        }
    }

    public double getOriginTime() {
        return originTime;
    }
}
