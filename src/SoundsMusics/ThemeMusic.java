package SoundsMusics;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Permet de faire jouer de la musique et des sons
 * @author Donavan Martin
 */
public class ThemeMusic {
    private File audioFile;
    private Clip audioClip;
    private BooleanControl muteControl;
    /**
     * Constructeur ThemeMusic
     */
    public ThemeMusic() {      
        try{   
            // AudioFile
            audioFile = new File("src\\SoundsMusics\\Music\\Tetris.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            
            // AudioClip	
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            startMusic();
            audioClip.loop(9999);
            
            // Mute control
            muteControl = (BooleanControl) audioClip.getControl(BooleanControl.Type.MUTE);
        }
        catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            //Logger.getLogger(ThemeMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    /**
     * Démarre la music
     */
    public void startMusic(){
        audioClip.start(); 
    }
    
    /**
     * Arrêtre la musique
     */
    public void stopMusic(){
        audioClip.stop(); 
    }
    /**
     * Coupe le sons
     * @param bool True == mute  False != Mute 
     */
    public void mute(boolean bool){
        muteControl.setValue(bool);
    }
}