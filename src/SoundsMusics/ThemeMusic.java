package SoundsMusics;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
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

    public ThemeMusic() {
               
        try
        {
            audioFile = new File("src\\SoundsMusics\\Music\\Tetris.wav");
            
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format); 
            	
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            
            startMusic();
            audioClip.loop(9999);
            
             

        }
        catch(IOException e){
            System.out.print(e.toString());
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ThemeMusic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ThemeMusic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
    public void startMusic(){
        audioClip.start(); 
    }
    
    public void stopMusic(){
        audioClip.stop(); 
    }
}