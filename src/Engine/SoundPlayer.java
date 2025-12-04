package Engine;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import javax.sound.sampled.LineEvent;

public class SoundPlayer {
    private static HashMap<String, Clip> preloadedSound = new HashMap<>();
    private static Clip currentMusic;

    public static final String walkFile = "walking.wav";
    public static final String jumpFile = "jump.wav";
    public static final String swordFile = "swords.wav";

    public static void preloadSounds(String... filePaths){
        for(String path : filePaths){
            if(!preloadedSound.containsKey(path)) {
                try{
                   // File file = new File(path);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(SoundPlayer.class.getClassLoader().getResourceAsStream(path));
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    preloadedSound.put(path, clip);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    System.err.println("Failed to preload sound: " + path);
                    e.printStackTrace();
                }
            }
        }
    }

    public static void playMusic(String filePath, boolean loop) {
        currentMusic = preloadedSound.get(filePath);
        currentMusic.setFramePosition(0);

        if (loop) {
            currentMusic.loop(Clip.LOOP_CONTINUOUSLY);
        }
        else {
            currentMusic.start();
        }


    }

    public static void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic = null;
        }
    }

    public static boolean isPlaying() {
        return currentMusic != null && currentMusic.isRunning();
    }

}