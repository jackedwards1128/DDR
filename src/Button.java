import java.awt.*;

import static java.lang.Math.round;

public class Button {
    private FrontEnd window;
    private int x;
    private int y;
    private int width;
    private int height;
    private String text;
    private Color color;
    private Font bigFont;

    public Button(FrontEnd window, int x, int y, int width, int height, String text, Color color) {
        this.window = window;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.color = color;
        this.bigFont = new Font("arial", Font.PLAIN, 60);
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.white);
        g.setFont(bigFont);

        double strLength = text.length();

        int pixLength = (int)((bigFont.getSize() * strLength) / 2);

        g.drawString(text, x + ((width - pixLength) / 2), y+height - ((height-bigFont.getSize()) / 2));
    }

    public boolean isClicked(int cx, int cy) {
        if (cx < x || cx > x+ width) {
            return false;
        }
        if (cy < y || cy > y + height) {
            return false;
        }
        return true;
    }
}
