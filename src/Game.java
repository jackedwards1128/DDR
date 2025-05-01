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
    private ArrayList<Arrow> upcomingArrowsSecond;
    private Song song;
    private static final int MILLISECOND = 1000;
    private static final int DIVISOR = 70;
    private int tick = 0;
    private int score;
    private int scoreSecond;
    private double BPM;
    private boolean songEnded;

    private String mapString;
    private String mapString2;
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
        this.mapString2 = "";
        this.unceString = "";
        this.mappingMode = false;
        this.mapChunk = 0;
        this.time = 0;
        this.songEnded = false;
//        this.arduino = new ArduinoParser(this);
        this.averageTimeGap = 20;
        this.score = 0;
        this.BPM = 148;
        this.upcomingArrows = new ArrayList<Arrow>();
        this.upcomingArrowsSecond = new ArrayList<Arrow>();

//        song = new Song(this,"music/Fetty Wap - Trap Queen.wav", "trapqueen");
        song = new Song(this,"music/Skrt Skrt With Quavo !!! Shorts rap Migos Quavo.wav", "testnotes");
//        song = new Song(this, "music/NOKIA.wav", "nokia");
//        song = new Song(this, "music/Jengi - Take U.wav", "takeu");
//        song = new Song(this, "music/Kendrick Lamar - luther.wav", "luther");

        mappingMode = false;



        window.repaint();
    }



    public void StopMusic() {
        if(this.jukebox.getIsPlaybackComplete()) {
            System.out.println("Song over");
            try {
                this.jukebox.getAudioClip().close();
                this.jukebox.getAudioStream().close();
                window.setState("song over");
                songEnded = true;
            } catch (IOException error) {
                System.out.println(error.getMessage());
                return;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {

        tick++;

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



        if(upcomingArrows.size() == 0 && upcomingArrowsSecond.size() == 0) {
            StopMusic();
        }

        if (upcomingArrows.size() > 0) {
            if (time - upcomingArrows.get(0).getHitTime() > 0.2) {
                upcomingArrows.remove(0);
                score -= 150;
            }
        }

        if (upcomingArrowsSecond.size() > 0) {
            if (time - upcomingArrowsSecond.get(0).getHitTime() > 0.2) {
                upcomingArrowsSecond.remove(0);
                scoreSecond -= 150;
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
        if(window.getGameState().equals("two player song")) {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_A:
                    checkTiming(4);
                    break;
                case KeyEvent.VK_S:
                    checkTiming(5);
                    break;
                case KeyEvent.VK_W:
                    checkTiming(6);
                    break;
                case KeyEvent.VK_D:
                    checkTiming(7);
                    break;
            }
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
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_A:
                    map(4);
                    break;
                case KeyEvent.VK_S:
                    map(5);
                    break;
                case KeyEvent.VK_W:
                    map(6);
                    break;
                case KeyEvent.VK_D:
                    map(7);
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

                        upcomingArrows.clear();
                        upcomingArrowsSecond.clear();

                        loadArrows();

                        clock = new Timer(MILLISECOND/ DIVISOR, this);
                        clock.start();

                        jukebox.play(song.getMusic());
                        songEnded = false;
                        millis = System.currentTimeMillis() % 1000;
                        window.switchExitSpot();
                        b.hide();
                        break;
                    case "twoplayer":
                        window.setState("two player song");
                        time = 0;

                        mappingMode = false;

                        upcomingArrows.clear();
                        upcomingArrowsSecond.clear();

                        loadArrows();

                        clock = new Timer(MILLISECOND/ DIVISOR, this);
                        clock.start();

                        jukebox.play(song.getMusic());
                        songEnded = false;
                        millis = System.currentTimeMillis() % 1000;
                        window.switchExitSpot();
                        b.hide();
                        break;
                    case "map":
                        window.setState("single player song");
                        time = 0;


                        mappingMode = true;

                        clock = new Timer(MILLISECOND/ DIVISOR, this);
                        clock.start();

                        jukebox.play(song.getMusic());
                        songEnded = false;
                        millis = System.currentTimeMillis() % 1000;
                        window.switchExitSpot();
                        b.hide();
                        break;
                    case "exit":
                        songEnded = true;
                        window.setState("menu");
                        clock.stop();
                        time = 0;
                        score = 0;
                        scoreSecond = 0;
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
                    case "luther":
                        song = new Song(this, "music/Kendrick Lamar - luther.wav", "luther");
                        break;
                    case "testnotes":
                        song = new Song(this,"music/Skrt Skrt With Quavo !!! Shorts rap Migos Quavo.wav", "testnotes");
                        break;
                }

            }
        }
    }

    public void loadArrows() {
        if(mappingMode == false) {
            if (song.getName() != "luther") {
                song.loadSongMapping(song.getName());

                boolean moreToMap = true;
                while (moreToMap) {
                    moreToMap = song.parseMapString(false, false);
                }
            } else {
                song.loadSongMapping("luther1");
                boolean moreToMap = true;
                while (moreToMap) {
                    moreToMap = song.parseMapString(true, false);
                }
                song.loadSongMapping("luther2");
                moreToMap = true;
                while (moreToMap) {
                    moreToMap = song.parseMapString(true, true);
                }
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
        if (direction < 4) {
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
                window.createEffect(3, direction, time, false);
                return;
            }
            if (Math.abs(nextInDirection.getHitTime() - time) < 0.03) {
                score += 300;
                window.createEffect(2, direction, time, false);
                upcomingArrows.remove(i);
                return;
            }
            if (Math.abs(nextInDirection.getHitTime() - time) < 0.1) {
                score += 200;
                window.createEffect(1, direction, time, false);
                upcomingArrows.remove(i);
                return;
            }
            if (Math.abs(nextInDirection.getHitTime() - time) < 0.3) {
                score += 100;
                window.createEffect(0, direction, time, false);
                upcomingArrows.remove(i);
                return;
            }
        } else {
            int equiv_dir = direction - 4;
            int i = 0;
            while(i < upcomingArrowsSecond.size()) {
                if (upcomingArrowsSecond.get(i).getDirection() == equiv_dir) {
                    nextInDirection = upcomingArrowsSecond.get(i);
                    break;
                }
                i++;
            }
            if (nextInDirection == null) {
                return;
            }
            if (Math.abs(nextInDirection.getHitTime() - time) > 0.3) {
                scoreSecond -= 250;
                window.createEffect(3, equiv_dir, time, true);
                return;
            }
            if (Math.abs(nextInDirection.getHitTime() - time) < 0.03) {
                scoreSecond += 300;
                window.createEffect(2, equiv_dir, time, true);
                upcomingArrowsSecond.remove(i);
                return;
            }
            if (Math.abs(nextInDirection.getHitTime() - time) < 0.1) {
                scoreSecond += 200;
                window.createEffect(1, equiv_dir, time, true);
                upcomingArrowsSecond.remove(i);
                return;
            }
            if (Math.abs(nextInDirection.getHitTime() - time) < 0.3) {
                scoreSecond += 100;
                window.createEffect(0, equiv_dir, time, true);
                upcomingArrowsSecond.remove(i);
                return;
            }
        }


    }

    public void map(int dir) {
        if (dir == -1) {
            unceString += "U" + Math.round(time * 100);
            return;
        }
        if (dir < 4)
            mapString += "A" + Math.round(time * 100) + "T" + dir;
        else
            mapString2 += "A" + Math.round(time * 100) + "T" + (dir-4);

    }

    public void mapPrint() {
        System.out.println("mapping: " + mapString);
        System.out.println("mapping2: " + mapString2);
    }

    public void addArrow(double hitTime, int dir, boolean second) {
        if (second) {
            upcomingArrowsSecond.add(new Arrow(this, window, dir, hitTime, true));
        } else
            upcomingArrows.add(new Arrow(this, window, dir, hitTime, false));

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

    public FrontEnd getWindow() {
        return window;
    }

    public int getScore() {
        return score;
    }

    public int getScoreSecond() {
        return scoreSecond;
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

    public ArrayList<Arrow> getUpcomingArrowsSecond() {
        return upcomingArrowsSecond;
    }

    public static void main(String[] args) {
        Game DDR = new Game();

    }
}











