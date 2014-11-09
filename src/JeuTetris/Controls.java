package JeuTetris;

import SoundsMusics.Sounds;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

/**
 * Classe Controls quio gère les controles du joueur dans le jeu Tetris
 * @author Azfar Badaroudine et Donavan Martin
 */
public class Controls implements KeyEventDispatcher {
    private PJeuTetris jeu;     // PJeuTetris
    private Sounds rotateFail;  // Rotation a échouer
    private Boolean mute;       // Couper le sons
    
    /**
     * Constructeur de Controls
     * @param jeu PJeuTetris parent
     */
    public Controls (PJeuTetris jeu){
        mute =false;
        this.jeu = jeu; 
    }
    /**
     * Gestion des touches du clavier 
     * @param ke keyboard event
     * @return always true (default)
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent ke) {
        // Touches enfoncées
        if (ke.getID()==KeyEvent.KEY_PRESSED){
            // Gauche
            if(ke.getKeyCode() == VK_LEFT){ 
                if(jeu.getTetrominoes().get(jeu.getTetrominoes().size()-1).left())
                    if(!jeu.canMove())
                        jeu.getTetrominoes().get(jeu.getTetrominoes().size()-1).right();
                    else
                        jeu.repaint();
            }
            // Droite
            else if(ke.getKeyCode() == VK_RIGHT) {
                if(jeu.getTetrominoes().get(jeu.getTetrominoes().size()-1).right())
                    if(!jeu.canMove())
                        jeu.getTetrominoes().get(jeu.getTetrominoes().size()-1).left();
                    else
                        jeu.repaint(); 
                }

            // Haut
            else if(ke.getKeyCode() == VK_UP) {
                if(jeu.getTetrominoes().get(jeu.getTetrominoes().size()-1).rotateLeft())
                    if(!jeu.canMove()) 
                        jeu.getTetrominoes().get(jeu.getTetrominoes().size()-1).rotateRight();
                    else
                        jeu.repaint();
                else
                    if(!mute)
                        rotateFail = new Sounds("RotateFail");
            }

            // Bas
            else if(ke.getKeyCode() == VK_DOWN) {
                if(jeu.canFall()){
                    jeu.getTetrominoes().get(jeu.getTetrominoes().size()-1).dropTetrominoe();
                    jeu.repaint();
                }  
            }
        }
        // Touche relachée
        if (ke.getID()==KeyEvent.KEY_RELEASED){
            // +  Augumentation de la difficulté
            if(ke.getKeyCode() == 107 || ke.getKeyCode() == 45) {
                jeu.getTimer().setDifficulte( jeu.getTimer().getDifficulte()-1);
            }
            // -  Diminution de la difficulté
            else if(ke.getKeyCode() == 109|| ke.getKeyCode() == 61) 
                 jeu.getTimer().setDifficulte( jeu.getTimer().getDifficulte()+1);  
        }
        return true;
    }
    /**
     * Setteur mute
     * @param mute True == mute False != mute
     */
    public void setMute(Boolean mute) {
        this.mute = mute;
    }
}


