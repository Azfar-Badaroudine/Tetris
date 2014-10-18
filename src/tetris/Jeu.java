package tetris;

import java.util.Random;

/**
 *
 * @author Azfar
 */
public class Jeu {
    private Piece[] piece = new Piece[50]; // une 
    private boolean vivant;
    
    public Jeu(){
        Random rand = new Random();
        for (int i=0; i<50; i++){               //Il est impossible d'avoir plus de 50 pièces simultanément dans le jeu
            int random = rand.nextInt(7) + 1;
            piece[i] = new Piece(random);
        }
        vivant = true;
    }
    
    public void start(){
        while (vivant){
            for (int i=0; i<50; i++){
                if (piece[i].getActif() == false){
                    piece[i].Activer();
                }
                //if (ligne est complétée)
                //if (game over)
            }
        }
        gameOver();
    }
    
    public void gameOver(){
        //Enregistrer highscore
    }
}
