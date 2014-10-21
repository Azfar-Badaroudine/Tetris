/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;

/**
 *
 * @author Donavan
 */
public class Controls extends KeyAdapter{
    private int key_code_pressed; // 1==UP 2==DOWN 3==LEFT 4==RIGHT
    
    /**
     * Lorsque l'utilisateur enfonce une touche
     * @param ke Événement du clavier 
     */
    @Override
    public void keyPressed(KeyEvent ke) {
        if(ke.getKeyCode() == VK_UP) {
          key_code_pressed = 1;
        }
        if(ke.getKeyCode() == VK_DOWN) {
          key_code_pressed = 2;
        }
        if(ke.getKeyCode() == VK_LEFT) {
          key_code_pressed = 3;
        }
        if(ke.getKeyCode() == VK_RIGHT) {
          key_code_pressed = 4;
        }
    }
    /**
     * Lorsque l'utilisateur relache la touche
     * @param ke Événement du clavier 
     */
    @Override
    public void keyReleased(KeyEvent ke) {
          key_code_pressed = 0;
    }
    
    /**
     * Getteur de la touche enfoncé
     * @return 
     */
    public int getKey_code_pressed() {
        return key_code_pressed;
    }
}

