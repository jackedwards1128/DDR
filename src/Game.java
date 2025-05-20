/*
            DANCE DANCE REVOLUTION
                BY JACK EDWARDS
        FOR CS2 TAUGHT BY MS. NAMASIVAYAM
                MENLO SCHOOL
    DO NOT DISTRIBUTE, UNLICENSED MUSIC PRESENT
 */


import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class Game implements ActionListener, KeyListener, MouseListener, MouseMotionListener {
    // Instance Variables
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
    private int score;
    private int scoreSecond;
    private boolean songEnded;

    private String mapString;
    private String mapString2;
    private String unceString;
    private boolean mappingMode;
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
        this.time = 0;
        this.songEnded = false;
        // This line of code is uncommented if the psychical interface is used
//        this.arduino = new ArduinoParser(this);
        this.averageTimeGap = 20;
        this.score = 0;
        this.upcomingArrows = new ArrayList<Arrow>();
        this.upcomingArrowsSecond = new ArrayList<Arrow>();

        // Song is set to trap queen by default
        song = new Song(this,"music/Fetty Wap - Trap Queen.wav", "trapqueen");

        mappingMode = false;



        window.repaint();
    }


    // Check if song audio is over, if it is, switch the game state to display information
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

    // Runs every frame
    public void actionPerformed(ActionEvent e) {

        window.repaint();

        // VSyncs game to match real time
        double difference = (System.currentTimeMillis() % 1000) - millis;
        millis = System.currentTimeMillis() % 1000;

        // Because we are storing the time in milliseconds but resetting every 1000ms for memory, there will be gaps
        // Where the difference jumps from say 9978 to 0011. This negative difference is accounted for by keeping
        // track of the average frame rate and just incrementing the tick amount by that time.
        if (difference > 0) {
            time += difference / 1000;
            averageTimeGap += difference;
            averageTimeGap /= 2;
        } else {
            time += averageTimeGap / 1000;
        }

        // If no more mapped arrows, check if it's the end of the song
        if(upcomingArrows.size() == 0 && upcomingArrowsSecond.size() == 0) {
            StopMusic();
        }

        // If arrows are 200ms past the timing, automatically remove them and deduct score
        if (upcomingArrows.size() > 0) {
            if (time - upcomingArrows.get(0).getHitTime() > 0.2) {
                upcomingArrows.remove(0);
                // Score deduction temporarily removed for the confidence of Share Fair participants using
                // the physical interface
                score -= 0;
            }
        }

        // See above
        if (upcomingArrowsSecond.size() > 0) {
            if (time - upcomingArrowsSecond.get(0).getHitTime() > 0.2) {
                upcomingArrowsSecond.remove(0);
                scoreSecond -= 0;
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
        // Keyboard input, runs function to check timing
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
        // For the WASD keys to be used by the second player
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
        // Maps notes when in mapping mode
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
            // For mapping the second player independently (luther) for asynchronous 2 player gameplay
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

        // Checks if any buttons are clicked
        for(Button b : window.getButtons()) {
            if (b.isClicked(x, y)) {
                // checks the function of any buttons pressed
                switch (b.getFunction()) {
                    case "start":
                        // Initiates a single player song
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
                        // Initiates a two player song
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
                        // Initiates a single player song to be mapped using arrow keys
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
                        // Exits current song
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
                        break;
                    // The following set the song to the respective song selected by the user
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
                    case "tweaker":
                        song = new Song(this,"music/G3 - Tweaker.wav", "tweaker");
                        break;
                    case "darlingI":
                        song = new Song(this,"music/Darling, I - Tyler The Creator.wav", "darlingI");
                        break;
                    case "thespins":
                        song = new Song(this,"music/Mac Miller - The Spins (Clean Version).wav", "thespins");
                        break;
                }

            }
        }
    }

    // Gets Song class to load the map string into the Arrows arraylist
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

    // Uses time variable to check how close the user pressed key to the timing
    public void checkTiming(int direction) {
        Arrow nextInDirection = null;
        if (direction < 4) {
            int i = 0;
            // Finds first instance of an arrow mathcing the direciton in the arrows array
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
                // Score deduction temporarily removed for the confidence of Share Fair participants
                // using the physical interface
                score -= 0;
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
            // Do the same for the second player
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
                scoreSecond -= 0;
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

    // Input direction and use time variable to store mapped timing in string
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

    // Prints out the mapping strings to be copied from terminal during development
    public void mapPrint() {
        System.out.println("mapping: " + mapString);
        System.out.println("mapping2: " + mapString2);
    }

    // Used to load arrows into the arrow arraylist
    public void addArrow(double hitTime, int dir, boolean second) {
        if (second) {
            upcomingArrowsSecond.add(new Arrow(this, window, dir, hitTime, true));
        } else
            upcomingArrows.add(new Arrow(this, window, dir, hitTime, false));

    }

    // Used by physical interface to transfer input to the timing checks
    public void acceptData(String data) {
        if (data.indexOf("L") != -1) {
            checkTiming(3);
        }
        if (data.indexOf("R") != -1) {
            checkTiming(0);
        }
        if (data.indexOf("U") != -1) {
            checkTiming(2);
        }
        if (data.indexOf("D") != -1) {
            checkTiming(1);
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











