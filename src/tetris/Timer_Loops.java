/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Donavan
 */
public class Timer_Loops extends Timer{
    private int difficulte;  // La difficulter dselon le temps
    /**
     * Constructeur du Timer avec un difficulté par défaut
     * @param delay Delay entre les update de la fenêtre
     * @param listener start/stop timer
     */
    public Timer_Loops(int delay, ActionListener listener) {
        super(delay, listener);
        this.difficulte = 0;
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
        this.difficulte = difficulte;
    }
    
}
