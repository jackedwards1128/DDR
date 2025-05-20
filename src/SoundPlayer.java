import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * A simple audio playback utility using the Java Sound API.
 * This class loads and plays a sound clip from the classpath
 * and listens for playback events.
 *
 * @author: Nandhini Namasivayam
 * @version: April 2025
 */

public class SoundPlayer implements LineListener {

    private boolean isPlaybackComplete;
    private Clip audioClip;
    private AudioInputStream audioStream;

    @Override
    // Checks the status of the audio stream
    public void update(LineEvent event) {
        // Receives line events
        if (LineEvent.Type.START == event.getType()) {
            isPlaybackComplete = false;
            System.out.println("Playback started.");
        } else if (LineEvent.Type.STOP == event.getType()) {
            isPlaybackComplete = true;
            System.out.println("Playback completed.");
        }
    }

    // Getters
    public boolean getIsPlaybackComplete() {
        return isPlaybackComplete;
    }

    public Clip getAudioClip() {
        return audioClip;
    }

    public AudioInputStream getAudioStream() {
        return audioStream;
    }

    public void play(String audioFilePath) {
        // Read the audio file
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(audioFilePath);

        try {
            // Create an audio input stream
            audioStream = AudioSystem.getAudioInputStream(inputStream);
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Audio file unsupported");
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Create DataLine info object from the stream
        AudioFormat audioFormat = audioStream.getFormat();
        DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
        try {
            audioClip = (Clip)AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
            audioClip.start();
        } catch (LineUnavailableException e) {
            System.out.println("Line unavailable");
            return;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}