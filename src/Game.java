import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    private SoundPlayer jukebox;
    private FrontEnd window;
    private double time;
    private double arrowSpeed = 600;
    private ArrayList<Arrow> upcomingArrows;
    private static final int SLEEP_TIME = 30;
    private int score;
    private double BPM;

    public Game() {
        this.jukebox = new SoundPlayer();
        this.window = new FrontEnd(this);
        window.setState("menu");
        window.addKeyListener(this);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);

        this.time = 0;
        this.score = 0;
        this.BPM = 148;
        this.upcomingArrows = new ArrayList<Arrow>();

        Songs song = new Songs("trapQueen");


        double mapTime = 0;
        for (int i = 0; i < song.getSize(); i++) {
            Arrow next = new Arrow(this, window, (int)(song.get(i)[1]), mapTime + song.get(i)[0]);
            mapTime += song.get(i)[0];

            upcomingArrows.add(next);
        }


//        for (int i = 1; i < 502; i++) {
//            upcomingArrows.add(new Arrow(this, window, (60.0 / BPM) * i + 3));
//            if (i % 5 == 0) {
//                if (i % 4 == 0) {
//                    upcomingArrows.add(new Arrow(this, window, (60.0 / BPM) * i + 3));
//                } else {
//                    upcomingArrows.add(new Arrow(this, window, (60.0 / BPM) * (i+0.5) + 3));
//                }
//            }
//        }

        window.repaint();
    }

    public static void main(String[] args) {
        Game DDR = new Game();

    }

    public void StopMusic() {
        if(this.jukebox.getIsPlaybackComplete()) {
            System.out.println("Done with the song!");
            try {
                this.jukebox.getAudioClip().close();
                this.jukebox.getAudioStream().close();
            } catch (IOException error) {
                System.out.println(error.getMessage());
                return;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        window.repaint();

        time += SLEEP_TIME / 1000.0;

        if (upcomingArrows.size() > 0) {
            if (time - upcomingArrows.get(0).getHitTime() > 0.2) {
                upcomingArrows.remove(0);
                score -= 150;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                checkTiming(0);
                break;
            case KeyEvent.VK_DOWN:
                checkTiming(1);
                break;
            case KeyEvent.VK_UP:
                checkTiming(2);
                break;
            case KeyEvent.VK_RIGHT:
                checkTiming(3);
                break;
        }
        window.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        for(Button b : window.getButtons()) {
            System.out.println("but");
            if (b.isClicked(x, y)) {
                window.setState("single player song");
                jukebox.play("music/Fetty Wap - Trap Queen.wav");
                Timer clock = new Timer(SLEEP_TIME, this);
                clock.start();
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //
    }

    public void checkTiming(int direction) {
        Arrow nextInDirection = null;
        int i = 0;
        while(i < upcomingArrows.size()) {
            if (upcomingArrows.get(i).getDirection() == direction) {
                nextInDirection = upcomingArrows.get(i);
                break;
            }
            i++;
        }
        if (nextInDirection == null) {
            return;
        }
        if (Math.abs(nextInDirection.getHitTime() - time) > 0.5) {
            score -= 250;
            window.createEffect(3, direction, time);
            return;
        }
        if (Math.abs(nextInDirection.getHitTime() - time) < 0.03) {
            score += 300;
            window.createEffect(2, direction, time);
            upcomingArrows.remove(i);
            return;
        }
        if (Math.abs(nextInDirection.getHitTime() - time) < 0.1) {
            score += 200;
            window.createEffect(1, direction, time);
            upcomingArrows.remove(i);
            return;
        }
        if (Math.abs(nextInDirection.getHitTime() - time) < 0.2) {
            score += 100;
            window.createEffect(0, direction, time);
            upcomingArrows.remove(i);
            return;
        }
        score -= 50;
        upcomingArrows.remove(i);

    }

    public double getTime() {
        return time;
    }

    public double getArrowSpeed() {
        return arrowSpeed;
    }

    public int getScore() {
        return score;
    }

    public ArrayList<Arrow> getUpcomingArrows() {
        return upcomingArrows;
    }
}











