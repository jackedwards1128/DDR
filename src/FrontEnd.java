import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class FrontEnd extends JFrame {

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

    private int testbpm = 132;
//    private Color low = new Color(150, 150, 150);
//    private Color low = new Color(70, 80, 90);
    private Color low = Color.white;
    private Color high = new Color(240, 200, 200);

    public FrontEnd (Game backend) {
        this.backend = backend;
        this.buttons = new ArrayList<Button>();
        this.unce = new ArrayList<Double>();
        this.unceCooldown = 0;

        buttons.add(new Button(this, 700, 400, 600, 200, "Start Game", Color.black, "start"));
        buttons.add(new Button(this, 700, 700, 600, 200, "Start 2 Player Game", Color.black, "twoplayer"));
        buttons.add(new Button(this, 700, 100, 600, 200, "Map Song", Color.red, "map"));
        buttons.add(new Button(this, 1170, 50, 300, 150, "Exit Song", Color.red, "exit"));
        buttons.add(new Button(this, 150, 100, 400, 95, "Trap Queen ", Color.lightGray, "trapqueen"));
        buttons.add(new Button(this, 150, 210, 400, 95, "Nokia", Color.lightGray, "nokia"));
        buttons.add(new Button(this, 150, 320, 400, 95, "Take U", Color.lightGray, "takeu"));

        this.fx = new ArrayList<Effect>();




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
//            if (backend.isMappingMode())
                g.setColor(Color.white);
//            else
//                g.setColor(unceUnceUnceUnce());

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
            g.drawString("Score: " + backend.getScore(), 100, 100);

            for (Arrow arrow : backend.getUpcomingArrows()) {
                arrow.draw(g);
            }

            if (fx.size() > 0) {
                if (backend.getTime() - fx.get(0).getOriginTime() > EFFECT_DURATION) {
                    fx.remove(0);
                }
            }
            for (Effect fX: fx) {
                fX.draw(g);
            }
            for (Button b : buttons) {
                switch(b.getFunction()) {
                    case "start":
                    case "twoplayer":
                    case "map":
                    case "trapqueen":
                    case "nokia":
                    case "takeu":
                        b.hide();
                        break;
                    case "exit":
                        if (state == "single player song")
                            b.show();
                        else
                            b.hide();
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
                        b.show();
                        break;
                    case "exit":
                        b.hide();
                        break;
                }
                b.draw(g);
            }
        }


    }

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

    public void createEffect(int type, int arrow, double time) {
        fx.add(new Effect(this, type, arrow, time));
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void loadUnce() {
        String unces = backend.getSong().getUnceString();
        while (unces.indexOf("U") != -1 && unces.length() != 1) {
            System.out.println(unces);
            unces = unces.substring(1);
            System.out.println(unces);
            double time = Float.parseFloat(unces.substring(0,unces.indexOf("U"))) / 100;
            unces = unces.substring(unces.indexOf("U"));
            System.out.println(unces);
            unce.add(time);
        }
        System.out.println(unce.size());
    }

    public void deleteEffects() {
        fx.clear();
    }

    public boolean isTwoPlayerSync() {
        // note: will have seperate state for async 2 player (luther)
        if(state == "two player song") {
            return true;
        }
        return false;
    }
}
