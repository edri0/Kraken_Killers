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

    public static void preloadSounds(String... filePaths){
        for(String path : filePaths){
            if(!preloadedSound.containsKey(path)) {
                try{
                    File file = new File(path); 
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); 
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
        try{
            if (currentMusic != null) {
                    stopMusic(); 
            }

    File file = new File(filePath); 
    AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); 
    currentMusic = AudioSystem.getClip(); 
    currentMusic.open(audioStream); 

    if (loop) {
        currentMusic.loop(Clip.LOOP_CONTINUOUSLY); 
    }
    else {
        currentMusic.start(); 
    }

    } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
        e.printStackTrace(); 
    }
}

    public static void stopMusic() {
        if (currentMusic != null) {
            currentMusic.stop(); 
            currentMusic.close(); 
            currentMusic = null; 
        }
    }
    public static void playSoundEffect(String filePath) {
        try{ 
            Clip clip; 
            if(preloadedSound.containsKey(filePath)){
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); 
            clip = AudioSystem.getClip(); 
            clip.open(audioStream); 
            }
            else {
                File file = new File(filePath); 
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); 
                clip = AudioSystem.getClip(); 
                clip.open(audioStream); 
            }

            clip.start(); 
            clip.addLineListener(event -> {
                if(event.getType() == LineEvent.Type.STOP) {
                    clip.close();  
                }
            });  
        
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace(); 
        }
    }

    public static boolean isPlaying() {
        return currentMusic != null && currentMusic.isRunning(); 
    }

}
