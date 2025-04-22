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

    private Button startButton;

    public FrontEnd (Game backend) {
        this.backend = backend;

        startButton = new Button(this, 430, 400, 600, 200, "Start game", Color.black);

        this.fx = new ArrayList<Effect>();

        this.buttons = new ArrayList<Button>();

        buttons.add(startButton);

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
        if (state == "single player song") {
            g.setColor(Color.white);
            g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);

            g.drawImage(arrowImages[4], 95, 50, 630, 150, this);

            Font smallFont = new Font("arial", Font.PLAIN, 70);
            g.setColor(Color.black);
            g.setFont(smallFont);
            g.drawString("Score: " + backend.getScore(), 900, 200);

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
        } else if (state == "menu") {
            g.setColor(Color.darkGray);
            g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
            startButton.draw(g);
        }

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
}
