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
    private int forme;                       // Forme du Tetromino
    private Color couleur;                   // Couleur du Tetromino
    private boolean[][] coordoneeTetrominoes;// Coordonnée de la forme [2][4]
    private CoordonneeJeu emplacement;       // Emplacement dans le jeu 
    private boolean isFalling;               // La piece est en train de tomber?
    
    /**
     * 
     * @param forme
     * @param couleur
     * @param nombreColonne
     * @param nombreRangee 
     */
    public Tetrominoes(int forme, Color couleur, int nombreColonne, int nombreRangee){
        this.forme = forme;
        emplacement = new CoordonneeJeu(nombreColonne,nombreRangee);
        System.out.print("La forme du Tetrominoe est : "+ forme);
        coordoneeTetrominoes = new boolean[4][4];
        switch(forme){
            case 1: for(int x = 0; x < 4; x++)
                        coordoneeTetrominoes[0][x]= true;
                    setEmplacement(nombreColonne);break;
                
            case 2: for(int x = 0; x < 2; x++)
                        for(int y = 0; y < 2; y++)
                            coordoneeTetrominoes[y][x]= true;
                    setEmplacement(nombreColonne);break;
                
            case 3: for(int x = 0; x < 3; x++)
                            coordoneeTetrominoes[0][x]= true;
                    coordoneeTetrominoes[1][1]= true;
                    setEmplacement(nombreColonne);break;
                
            case 4: for(int x = 0; x < 3; x++)
                            coordoneeTetrominoes[0][x]= true;
                    coordoneeTetrominoes[1][0]= true;
                    setEmplacement(nombreColonne);break;
                
            case 5: for(int x = 0; x < 3; x++)
                            coordoneeTetrominoes[0][x]= true;
                    coordoneeTetrominoes[1][2]= true;
                    setEmplacement(nombreColonne);break;
                
            case 6: for(int x = 1; x < 2; x++)
                            coordoneeTetrominoes[0][x]= true;
                    for(int x = 0; x < 2; x++)
                            coordoneeTetrominoes[1][x]= true;
                    setEmplacement(nombreColonne);break;
                
            case 7: for(int x = 0; x < 2; x++)
                            coordoneeTetrominoes[0][x]= true;
                    for(int x = 1; x < 3; x++)
                            coordoneeTetrominoes[1][x]= true;
                    setEmplacement(nombreColonne);break;
        }
        this.couleur = couleur;
        isFalling = true;
    }
    /**
     * Setteur de l'emplacement de la pièce selon les coordonées du jeu
     * @param nombreColonne 
     */
    public void setEmplacement(int nombreColonne){
        int beginIndex = (nombreColonne/4)+(nombreColonne%4)/2;
        switch(forme){
            case 1: for(int x = beginIndex; x < 4+beginIndex; x++)
                        emplacement.setCoordonee(x, 0, true);break;
                
            case 2: for(int x = beginIndex; x < 2+beginIndex; x++)
                        for(int y = 0; y < 2; y++)
                            emplacement.setCoordonee(x, y, true);;break;
                
            case 3: for(int x = beginIndex; x < 3+beginIndex; x++)
                            emplacement.setCoordonee(x, 0, true);;
                    emplacement.setCoordonee(beginIndex+1, 1, true);;break;
                
            case 4: for(int x = beginIndex; x < 3+beginIndex; x++)
                            emplacement.setCoordonee(x, 0, true);
                    emplacement.setCoordonee(beginIndex, 1, true);break;
                
            case 5: for(int x = beginIndex; x < 3+beginIndex; x++)
                            emplacement.setCoordonee(x, 0, true);
                    emplacement.setCoordonee(beginIndex+2, 1, true);break;
                
            case 6: for(int x = 1 + beginIndex; x < 3+beginIndex; x++)
                            emplacement.setCoordonee(x, 0, true);
                    for(int x = beginIndex; x < 2+beginIndex; x++)
                            emplacement.setCoordonee(x, 1, true);break;
                
            case 7: for(int x = beginIndex; x < 2+beginIndex; x++)
                            emplacement.setCoordonee(x, 0, true);
                    for(int x = 1+beginIndex; x < 3+beginIndex; x++)
                            emplacement.setCoordonee(x, 1, true);break;
        }
        //emplacement.afficheTable();
        System.out.println("L'index de départ est beginIndedx =" + beginIndex);
    }
    /**
     * L'emplacement est-il l'emplacement de la pièce dans le jeu?
     * @param colonne la colonne a vérifier
     * @param rangee la rangee a vérifier
     * @return False == La pièce n'occupe pas cette emplacement True == La pièce occuper cette emplacement
     */
    public boolean IsEmpty(int colonne, int rangee){
        if(emplacement.getCoordoneeJeu().get(rangee)[colonne]==false){
            //System.out.println("La coordonée Tetrominoe ( " + colonne + " , " + rangee + " ) est vide.");
            return true;
        }
        else{
            //System.out.println("La coordonée Tetrominoe ( " + colonne + " , " + rangee + " ) est utilisé.");
            return false;
        }  
    }
    public void dropTetrominoe(){
        for(int x=emplacement.getNombreColonne()-1 ; x>=0 ; x--)
            for(int y=emplacement.getNombreRangee()-1 ; y>=0 ; y--)
                if(!emplacement.IsEmpty(x, y)){
                    try{
                        emplacement.setCoordonee(x, y,   false);
                        emplacement.setCoordonee(x, y+1, true);
                    }catch(Exception e){
                        isFalling=false;
                    }
                }
    }
    /**
     * Getteur de le couleur du Tetrominoe
     * @return couleur la couleur du Tetrominoe
     */
    public Color getCouleur() {
        return couleur;
    }
    /**
     * Getteur "Est-ce que la pièce est en train de tomber"
     * @return isFalling la pièce est t'elle en train de tomber
     */
    public boolean isIsFalling() {
        return isFalling;
    }
    /**
     * Setteur "Est-ce que la pièce est en train de tomber"
     * @param isFalling la pièce est t'elle en train de tomber
     */
    public void setIsFalling(boolean isFalling) {
        this.isFalling = isFalling;
    }

    public CoordonneeJeu getEmplacement() {
        return emplacement;
    }
    
    
    
    
    
    
    
}
