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
    private static HashMap<String, Clip> soundClips = new HashMap<>(); 
    private static Clip currentMusic; 
    
    public static void playMusic(String filePath, boolean loop) {
        try{
            if (currentMusic != null) {
                if (getFileName(currentMusic).equals(filePath)){
                    return; 
                }
                else {
                    stopMusic(); 
                }
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
            File file = new File(filePath); 
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file); 
            Clip clip = AudioSystem.getClip(); 
            clip.open(audioStream); 
            clip.start(); 
            soundClips.put(filePath, clip); 

            clip.addLineListener(event -> {
                if(event.getType() == LineEvent.Type.STOP) {
                    clip.close(); 
                    soundClips.remove(filePath); 
                }
            });  
        
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace(); 
        }
    }

    public static boolean isPlaying() {
        return currentMusic != null && currentMusic.isRunning(); 
    }

    private static String getFileName(Clip clip) {
        return clip == null ? "" : clip.toString(); 
    }

}
