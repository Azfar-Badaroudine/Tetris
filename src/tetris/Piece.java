/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

/**
 *
 * @author Azfar
 */
public class Piece {
  
    private int tetrominoes; // 1==T 2=
    private boolean actif;
    private boolean enDeplacement;
    
    public Piece(int i){
        //type = i;
        actif = false;
        enDeplacement = false;
    }
    
    public void Activer(){
        actif = true;
        enDeplacement = true;
        
       // switch(type){
        //    case 1: setColor(4,1,type);
        //}
    }
    
    public boolean getActif(){
        return actif;
    }
    
    public boolean getEnDeplacement(){
        return enDeplacement;
    }
}
