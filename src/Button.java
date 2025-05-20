import java.awt.*;

import static java.lang.Math.round;

public class Button {
    // Instance variables
    private FrontEnd window;
    private int x;
    private int y;
    private int width;
    private int height;
    private String text;
    private Color color;
    private Font bigFont;
    private String function;
    private boolean visible;

    public Button(FrontEnd window, int x, int y, int width, int height, String text, Color color, String function) {
        this.window = window;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.color = color;
        this.bigFont = new Font("arial", Font.PLAIN, 50);
        this.function = function;
        this.visible = true;
    }

    public void draw(Graphics g) {
        if (visible) {
            // Draw color block of button
            g.setColor(color);
            g.fillRect(x, y, width, height);
            g.setColor(Color.white);
            g.setFont(bigFont);

            double strLength = text.length();

            int pixLength = (int)((bigFont.getSize() * strLength) / 2);

            // Draw text in middle of button
            g.drawString(text, x + ((width - pixLength) / 2), y+height - ((height-bigFont.getSize()) / 2));
        }
    }

    // Check whether the button is being hovered over by the mouse
    public boolean isClicked(int cx, int cy) {
        if (visible) {
            if (cx < x || cx > x+ width) {
                return false;
            }
            if (cy < y || cy > y + height) {
                return false;
            }
            return true;
        }
        return false;

    }

    // Used to move the exit button around
    public void changePosition(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public void show() {
        visible = true;
    }

    public void hide() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getFunction() {
        return function;
    }
}
