package SoundsMusics;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 * Permet de faire jouer de la musique et des sons
 * @author Clovis Hallé
 */
public class Sound {
    private AudioPlayer MGP;
    private String song;
    private boolean playing;
    private AudioStream BGM;
    private ContinuousAudioDataStream loop;

    public Sound() {
        MGP = AudioPlayer.player;
        
        try
        {
            InputStream test = new FileInputStream(song);
            BGM = new AudioStream(test);
            loop = null;
            AudioPlayer.player.start(loop);

        }
        catch(IOException e){
            System.out.print(e.toString());
        }
    }
    
    
    public void startMusic(){
        MGP.start(loop);
        playing = true;
    }
    
    public void stopMusic(){
        MGP.stop(BGM);
        playing = false;
    }
    
    public void Sound() throws InterruptedException{
      MGP.start(loop);
      AudioPlayer.player.wait(5);
    }
}