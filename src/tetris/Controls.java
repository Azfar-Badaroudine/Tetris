package tetris;

import SoundsMusics.Sounds;
import java.awt.KeyEventDispatcher;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

/**
 *
 * @author Donavan Martin
 */
public class Controls implements KeyEventDispatcher {
    private PJeuTetris jeu;
    private Sounds rotateFail;
    
    
    public Controls (PJeuTetris jeu){
        this.jeu = jeu;
        
    }

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

        if (ke.getID()==KeyEvent.KEY_RELEASED){
            // +  Augumentation de la difficulté
            if(ke.getKeyCode() == 107 || ke.getKeyCode() == 45) {
                jeu.getTimer().setDifficulte( jeu.getTimer().getDifficulte()-50);
            }
            // -   Diminution de la difficulté
            else if(ke.getKeyCode() == 109|| ke.getKeyCode() == 61) 
                 jeu.getTimer().setDifficulte( jeu.getTimer().getDifficulte()+50);  
        }
        return true;
    }
}


