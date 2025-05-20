import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class FrontEnd extends JFrame {
    // Instance variables
    private Game backend;

    private String state;

    private final int WINDOW_WIDTH = 1920;
    private final int WINDOW_HEIGHT = 1080;
    private final double EFFECT_DURATION = 1.0;

    public Image[] arrowImages;

    public Image[] effectImages;

    private ArrayList<Effect> fx;

    private ArrayList<Button> buttons;

    private ArrayList<Double> unce;
    private double unceCooldown;

    private Color low = Color.white;
    private Color high = new Color(240, 200, 200);

    public FrontEnd (Game backend) {
        this.backend = backend;
        this.buttons = new ArrayList<Button>();
        this.unce = new ArrayList<Double>();
        this.unceCooldown = 0;

        Color unmapped = new Color(128, 0 ,0);

        // Create all buttons for UI function and song selection

        buttons.add(new Button(this, 1170, 50, 300, 90, "Exit Song", Color.red, "exit"));

        buttons.add(new Button(this, 950, 100, 450, 200, "Map Song", Color.red, "map"));
        buttons.add(new Button(this, 950, 400, 450, 200, "Start Game", Color.black, "start"));
        buttons.add(new Button(this, 890, 700, 600, 200, "Start 2 Player Game", Color.black, "twoplayer"));

        buttons.add(new Button(this, 50, 60, 400, 95, "Trap Queen ", Color.lightGray, "trapqueen"));
        buttons.add(new Button(this, 50, 170, 400, 95, "Nokia", Color.lightGray, "nokia"));
        buttons.add(new Button(this, 50, 280, 400, 95, "Take U", Color.lightGray, "takeu"));
        buttons.add(new Button(this, 50, 390, 400, 95, "Luther", Color.lightGray, "luther"));
//        buttons.add(new Button(this, 50, 500, 400, 95, "test", Color.lightGray, "testnotes"));
//        buttons.add(new Button(this, 50, 500, 400, 95, "", Color.lightGray, "testnotes"));
//        buttons.add(new Button(this, 50, 610, 400, 95, "Dark Thoughts", Color.green, "darkthoughts"));
//        buttons.add(new Button(this, 50, 610, 400, 95, "", Color.green, "darkthoughts"));
//        buttons.add(new Button(this, 50, 720, 400, 95, "Unwritten", unmapped, "unwritten"));
//        buttons.add(new Button(this, 50, 720, 400, 95, "", unmapped, "unwritten"));
        buttons.add(new Button(this, 50, 500, 400, 95, "Tweaker", Color.lightGray, "tweaker"));
        buttons.add(new Button(this, 480, 60, 400, 95, "Darling, I", Color.lightGray, "darlingI"));
//        buttons.add(new Button(this, 480, 60, 400, 95, "", unmapped, "darlingI"));
        buttons.add(new Button(this, 50, 610, 400, 95, "The Spins", Color.lightGray, "thespins"));
//        buttons.add(new Button(this, 480, 280, 400, 95, "", unmapped, "vondutch"));
//        buttons.add(new Button(this, 480, 390, 400, 95, "Under Your Spell", unmapped, "underyourspell"));
//        buttons.add(new Button(this, 480, 390, 400, 95, "", unmapped, "underyourspell"));

        this.fx = new ArrayList<Effect>();



        // Initialize images
        this.arrowImages = new Image[5];
        this.effectImages = new Image[7];
        arrowImages[0] = new ImageIcon("Resouces/images/left_arrow.png").getImage();
        arrowImages[1] = new ImageIcon("Resouces/images/down_arrow.png").getImage();
        arrowImages[2] = new ImageIcon("Resouces/images/up_arrow.png").getImage();
        arrowImages[3] = new ImageIcon("Resouces/images/right_arrow.png").getImage();
        arrowImages[4] = new ImageIcon("Resouces/images/guide_arrows.png").getImage();

        effectImages[0] = new ImageIcon("Resouces/images/red_fx.png").getImage();
        effectImages[1] = new ImageIcon("Resouces/images/yellow_fx.png").getImage();
        effectImages[2] = new ImageIcon("Resouces/images/green_fx.png").getImage();
        effectImages[3] = new ImageIcon("Resouces/images/miss_fx.png").getImage();
        effectImages[4] = new ImageIcon("Resouces/images/red_fx_down.png").getImage();
        effectImages[5] = new ImageIcon("Resouces/images/yellow_fx_down.png").getImage();
        effectImages[6] = new ImageIcon("Resouces/images/green_fx_down.png").getImage();

        // Setup the window and the buffer strategy.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("DDR");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
        repaint();
    }

    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void myPaint(Graphics g) {
        if (state == "single player song" || state == "two player song") {
            // Paint basic song screen
            g.setColor(Color.white);

            g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);

            if (state == "single player song")
                g.drawImage(arrowImages[4], 95, 120, 630, 150, this);
            else {
                g.drawImage(arrowImages[4], 40, 120, 630, 150, this);
                g.drawImage(arrowImages[4], 840, 120, 630, 150, this);
            }



            Font smallFont = new Font("arial", Font.PLAIN, 70);
            g.setColor(Color.black);
            g.setFont(smallFont);
            // Print scores
            if (state == "two player song") {
                if (backend.getSong().getName() == "luther") {
                    g.drawString("Score: " + (backend.getScoreSecond() + backend.getScore()), 100, 100);
                } else {
                    g.drawString("Score: " + backend.getScoreSecond(), 100, 100);
                    g.drawString("Score: " + backend.getScore(), 900, 100);
                }
            } else
                g.drawString("Score: " + backend.getScore(), 100, 100);

            // Draw Arrows
            for (Arrow arrow : backend.getUpcomingArrows()) {
                arrow.draw(g);
            }
            for (Arrow arrow : backend.getUpcomingArrowsSecond()) {
                arrow.draw(g);
            }

            // Remove outdated effects
            if (fx.size() > 0) {
                if (backend.getTime() - fx.get(0).getOriginTime() > EFFECT_DURATION) {
                    fx.remove(0);
                }
            }

            // Draw effects
            for (Effect fX: fx) {
                fX.draw(g);
            }

            // Draw buttons if they are on the correct screen
            for (Button b : buttons) {
                switch(b.getFunction()) {
                    case "start":
                    case "twoplayer":
                    case "map":
                    case "trapqueen":
                    case "nokia":
                    case "takeu":
                    case "luther":
                    case "testnotes":
                    case "darkthoughts":
                    case "unwritten":
                    case "tweaker":
                    case "darlingI":
                    case "thespins":
                    case "vondutch":
                    case "underyourspell":
                        b.hide();
                        break;
                    case "exit":
                        b.show();
                        break;
                }
                if (b.isVisible()) {
                    b.draw(g);
                }
            }
        } else if (state == "menu") {
            g.setColor(Color.darkGray);
            g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
            for (Button b : buttons) {
                switch(b.getFunction()) {
                    case "start":
                    case "twoplayer":
                    case "map":
                    case "trapqueen":
                    case "nokia":
                    case "takeu":
                    case "luther":
                    case "testnotes":
                    case "darkthoughts":
                    case "unwritten":
                    case "tweaker":
                    case "darlingI":
                    case "thespins":
                    case "vondutch":
                    case "underyourspell":
                        b.show();
                        break;
                    case "exit":
                        b.hide();
                        break;
                }
                b.draw(g);
            }
        } else if (state == "song over") {
            // Draw game over screen and print score(s)
            g.setColor(Color.darkGray);
            g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);

            Font biggerFont = new Font("arial", Font.PLAIN, 60);
            g.setColor(Color.white);
            g.setFont(biggerFont);
            if (backend.getScoreSecond() > 0) {
                g.drawString("Song over!" , 400, 300);
                g.drawString("Right side got a score of " + backend.getScore() , 300, 400);
                g.drawString("Left side got a score of " + backend.getScoreSecond(), 300, 500);
            } else
                g.drawString("Song over! You got a score of " + backend.getScore(), 300, 300);

            for (Button b : buttons) {
                switch(b.getFunction()) {
                    case "exit":
                        b.show();
                        break;
                }
                b.draw(g);
            }
        }


    }

    // Outdated function used to make background flash to the beat. May be implemented in future
    public Color unceUnceUnceUnce() {

        if (unceCooldown > 0)
            unceCooldown--;

        if (Math.abs(unce.get(0) - backend.getTime()) < 0.03) {
            unceCooldown = 20;
            unce.remove(0);
        }

        double interpolate = 1 - (unceCooldown/20.0);

        double red = (low.getRed() * interpolate) + (high.getRed() * (1-interpolate));
        double green = (low.getGreen() * interpolate) + (high.getGreen() * (1-interpolate));
        double blue = (low.getBlue() * interpolate) + (high.getBlue() * (1-interpolate));

        return new Color((int)red, (int)green, (int)blue);
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGameState() {
        return state;
    }

    public void createEffect(int type, int arrow, double time, boolean second) {
        fx.add(new Effect(this, type, arrow, time, second));
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }


    public void deleteEffects() {
        fx.clear();
    }

    // Moves the exit spot depending on whether a 2-player or 1-player song is being player
    public void switchExitSpot() {
        if (state == "two player song")
            buttons.get(0).changePosition(580, 20);
        else
            buttons.get(0).changePosition(1170, 50);

    }
}
