import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    private SoundPlayer jukebox;
    private FrontEnd window;
    private ArduinoParser arduino;
    private double time;
    private long millis;
    private double averageTimeGap;
    private double arrowSpeed = 700;
    private ArrayList<Arrow> upcomingArrows;
    private Song song;
    private static final int MILLISECOND = 1000;
    private static final int DIVISOR = 70;
    private int score;
    private double BPM;

    private String mapString;
    private String unceString;
    private boolean mappingMode;
    private int mapChunk;
    private Timer clock;

    public Game() {
        this.jukebox = new SoundPlayer();
        this.window = new FrontEnd(this);
        window.setState("menu");
        window.addKeyListener(this);
        window.addMouseListener(this);
        window.addMouseMotionListener(this);

        this.mapString = "";
        this.unceString = "";
        this.mappingMode = false;
        this.mapChunk = 0;
        this.time = 0;
//        this.arduino = new ArduinoParser(this);
        this.averageTimeGap = 20;
        this.score = 0;
        this.BPM = 148;
        this.upcomingArrows = new ArrayList<Arrow>();

//        song = new Song(this,"music/Fetty Wap - Trap Queen.wav", "trapqueen");
//        song = new Song(this, "music/NOKIA.wav", "nokia");
        song = new Song(this, "music/Jengi - Take U.wav", "takeu");

        mappingMode = false;



        window.repaint();
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

        double difference = (System.currentTimeMillis() % 1000) - millis;
        millis = System.currentTimeMillis() % 1000;

        if (difference > 0) {
            time += difference / 1000;
            averageTimeGap += difference;
            averageTimeGap /= 2;
        } else {
            time += averageTimeGap / 1000;
        }

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
        if(mappingMode) {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_LEFT:
                    map(0);
                    break;
                case KeyEvent.VK_DOWN:
                    map(1);
                    break;
                case KeyEvent.VK_UP:
                    map(2);
                    break;
                case KeyEvent.VK_RIGHT:
                    map(3);
                    break;
                case KeyEvent.VK_SPACE:
                    map(-1);
                    break;
            }
        }
        window.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        for(Button b : window.getButtons()) {
            if (b.isClicked(x, y)) {

                switch (b.getFunction()) {
                    case "start":
                        window.setState("single player song");
                        time = 0;

                        mappingMode = false;

                        loadArrows();

                        window.loadUnce();

                        clock = new Timer(MILLISECOND/ DIVISOR, this);
                        clock.start();

                        jukebox.play(song.getMusic());
                        millis = System.currentTimeMillis() % 1000;
                        b.hide();
                        break;
                    case "twoplayer":
                        window.setState("two player song");
                        time = 0;

                        mappingMode = false;

                        loadArrows();

                        clock = new Timer(MILLISECOND/ DIVISOR, this);
                        clock.start();

                        jukebox.play(song.getMusic());
                        millis = System.currentTimeMillis() % 1000;
                        b.hide();
                        break;
                    case "map":
                        window.setState("single player song");
                        time = 0;


                        mappingMode = true;

                        clock = new Timer(MILLISECOND/ DIVISOR, this);
                        clock.start();

                        jukebox.play(song.getMusic());
                        millis = System.currentTimeMillis() % 1000;
                        b.hide();
                        break;
                    case "exit":
                        window.setState("menu");
                        clock.stop();
                        time = 0;
                        score = 0;
                        upcomingArrows.clear();

                        try {
                            this.jukebox.getAudioClip().close();
                            this.jukebox.getAudioStream().close();
                        } catch (IOException error) {
                            System.out.println(error.getMessage());
                            return;
                        }
                        window.deleteEffects();
                        window.repaint();
                        mapPrint();
                        System.out.println("unce: " + unceString);
                        song.clearMapString();
                    case "trapqueen":
                        song = new Song(this,"music/Fetty Wap - Trap Queen.wav", "trapqueen");
                        break;
                    case "nokia":
                        song = new Song(this, "music/NOKIA.wav", "nokia");
                        break;
                    case "takeu":
                        song = new Song(this, "music/Jengi - Take U.wav", "takeu");
                        break;
                }

            }
        }
    }

    public void loadArrows() {
        song.loadSongMapping(song.getName());
        if(mappingMode == false) {

            boolean moreToMap = true;
            while (moreToMap) {
                moreToMap = song.parseMapString();
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
        if (Math.abs(nextInDirection.getHitTime() - time) > 0.3) {
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
        if (Math.abs(nextInDirection.getHitTime() - time) < 0.3) {
            score += 100;
            window.createEffect(0, direction, time);
            upcomingArrows.remove(i);
            return;
        }

    }

    public void map(int dir) {
        if (dir == -1) {
            unceString += "U" + Math.round(time * 100);
            return;
        }
        mapString += "A" + Math.round(time * 100) + "T" + dir;
    }

    public void mapPrint() {
        System.out.println("mapping: " + mapString);
    }

    public void addArrow(double hitTime, int dir) {
        upcomingArrows.add(new Arrow(this, window, dir, hitTime));
    }

    public void acceptData(String data) {
//        System.out.println("accepting");
        if (data.indexOf("L") != -1) {
            checkTiming(3);
        }
        if (data.indexOf("R") != -1) {
            checkTiming(0);
        }
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

    public Song getSong() {
        return song;
    }

    public boolean isMappingMode() {
        return mappingMode;
    }

    public ArrayList<Arrow> getUpcomingArrows() {
        return upcomingArrows;
    }

    public static void main(String[] args) {
        Game DDR = new Game();

    }
}











