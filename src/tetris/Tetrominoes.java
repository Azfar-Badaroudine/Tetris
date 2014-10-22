/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.Color;

/**
 *
 * @author Donavan
 */

/*   Liste des Formes ... 
              _ _ _ _
      1 =    |_|_|_|_|

                _ _
      2 =      |_|_|
               |_|_|
               _ _ _
      3 =     |_|_|_|
                |_|
               _ _ _
      4 =     |_|_|_|
              |_|  
               _ _ _
      5 =     |_|_|_|
                  |_| 
               _ _
      6 =    _|_|_|
            |_|_|
               _ _
      7 =     |_|_|_
                |_|_|

*/
/*   Coordonnées d'une pièce...
     Puisque, Tetrominoes "bar verticale" et "bar horizontale"
    
       [0,0][0,1][0,2][0,3]
       [1,0][1,1][1,2][1,3]
       [2,0][2,1][2,2][2,3]
       [3,0][3,1][3,2][3,3]
    
*/
 
public class Tetrominoes {
    private int forme;                   // Forme du Tetromino
    private Color couleur;
    private boolean[][] coordoneeTetrominoes;// Coordonnée de la forme [2][4]
    private CoordonneeJeu emplacement;   // Emplacement dans le jeu
    /**
     * 
     * @param forme
     * @param couleur
     * @param nombreColonne
     * @param nombreRangee 
     */
    public Tetrominoes(int forme, Color couleur, int nombreColonne, int nombreRangee){
        this.forme = forme;
        coordoneeTetrominoes = new boolean[4][4];
        switch(forme){
            case 1: for(int x = 0; x < 4; x++)
                        coordoneeTetrominoes[0][x]= true;break;
                
            case 2: for(int x = 0; x < 2; x++)
                        for(int y = 0; y < 2; y++)
                            coordoneeTetrominoes[y][x]= true;break;
                
            case 3: for(int x = 0; x < 3; x++)
                            coordoneeTetrominoes[0][x]= true;
                    coordoneeTetrominoes[1][1]= true;break;
                
            case 4: for(int x = 0; x < 3; x++)
                            coordoneeTetrominoes[0][x]= true;
                    coordoneeTetrominoes[1][0]= true;break;
                
            case 5: for(int x = 0; x < 3; x++)
                            coordoneeTetrominoes[0][x]= true;
                    coordoneeTetrominoes[1][2]= true;break;
                
            case 6: for(int x = 1; x < 2; x++)
                            coordoneeTetrominoes[0][x]= true;
                    for(int x = 0; x < 2; x++)
                            coordoneeTetrominoes[1][x]= true;break;
                
            case 7: for(int x = 0; x < 2; x++)
                            coordoneeTetrominoes[0][x]= true;
                    for(int x = 1; x < 2; x++)
                            coordoneeTetrominoes[1][x]= true;break;
        }
        this.couleur = couleur;
        emplacement = new CoordonneeJeu(nombreColonne,nombreRangee);
    }
    
    
    
    
    
}
