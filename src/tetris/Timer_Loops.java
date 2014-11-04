package tetris;

import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Classe TimerLoops pour la classe jeuTetris
 * @author Donavan
 */
public class Timer_Loops extends Timer{
    private int difficulte;  // La difficulter selon le temps
    /**
     * Constructeur du Timer avec une difficulté 
     * @param delay Delay entre les update de la fenêtre
     * @param listener start/stop timer
     */
    public Timer_Loops(int delay, ActionListener listener) {
        super(delay, listener);
        this.difficulte = delay;
    }
    
    /**
     * Getteur de la difficulte par rapport au temps
     * @return difficulte
     */
    public int getDifficulte() {
        return difficulte;
    }
    
    /**
     * Setteur de la difficulte par rapport au temps
     * @param difficulte 
     */
    public void setDifficulte(int difficulte) {
        if(difficulte>0){
            super.setDelay(1000/difficulte);
            this.difficulte = difficulte;
        }
    } 
}
