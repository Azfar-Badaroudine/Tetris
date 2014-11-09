package tetris;

import java.awt.Color;


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


     Coordonnées d'un Tetrominoe
     Puisque, Tetrominoe "bar verticale" et "bar horizontale"
     L'un des système de coordonnées peut être représenter par:
    
       [0,0][0,1][0,2][0,3]
       [1,0][1,1][1,2][1,3]
       [2,0][2,1][2,2][2,3]
       [3,0][3,1][3,2][3,3]
*/

 /**
 * Classe Tetrominoe
 * @author Azfar Badaroudine et Donavan Martin
 */

public class Tetrominoes {
    private int forme;                       // Forme du Tetromino
    private Color couleur;                   // Couleur du Tetromino
    private CoordonneeJeu emplacement;       // Emplacement dans le jeu 
    private boolean isFalling;               // La piece est en train de tomber?
    private int rotation;
    
    /**
     * Constructeur d'un Tetrominoe
     * @param forme La forme du tetromine [ 1 - 6 ]
     * @param couleur La couleur du tetromine
     * @param nombreColonne Le nombre de colonne de la table de coordonnées 
     * @param nombreRangee Le nombre de rangées de la table de coordonnées 
     */
    public Tetrominoes(int forme, Color couleur, int nombreColonne, int nombreRangee){
        this.forme = forme;                                         // forme 
        emplacement = new CoordonneeJeu(nombreColonne,nombreRangee);// Emplacement
        setEmplacement(nombreColonne);
        rotation = 0;                                               // Rotation
        this.couleur = couleur;                                     // Couleur
    }
    /**
     * Setteur de l'emplacement de la pièce selon les coordonées du jeu
     * @param nombreColonne 
     */
    public void setEmplacement(int nombreColonne){
        // Le tetrominoe doit apparaitre en haut et au centre du jeu
        int beginIndex = (nombreColonne/4)+(nombreColonne%4)/2; // Centrer
        
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
    }
    
    /**
     * Vérifie l'emplacement de la pièce dans le jeu?
     * @param colonne la colonne a vérifier
     * @param rangee la rangee a vérifier
     * @return False == La pièce n'occupe pas cette emplacement True == La pièce occuper cette emplacement
     */
    public boolean IsEmpty(int colonne, int rangee){
        if(emplacement.getCoordoneeJeu().get(rangee)[colonne]==false)
            return true;
        return false;
    }
    /**
     * Descent de l'emplacement du tetrominoe dans la table de coordonnées
     */
    public void dropTetrominoe(){
        for(int x=emplacement.getNombreColonne()-1 ; x>=0 ; x--)
            for(int y=emplacement.getNombreRangee()-1 ; y>=0 ; y--)
                if(!emplacement.IsEmpty(x, y))
                    try{
                        emplacement.setCoordonee(x, y,   false);
                        emplacement.setCoordonee(x, y+1, true);
                    }catch(Exception e){
                        isFalling=false;
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
    
    /**
     * Getteur des coordonées du tetrominoe
     * @return 
     */
    public CoordonneeJeu getEmplacement() {
        return emplacement;
    }
    
    /**
     * Change la position vers la gauche
     * @return True == Si c'est possible False == Impossible
     */
    public boolean left() {
        for(boolean[] position : emplacement.getCoordoneeJeu())
            if(position[0] == true)
                return false;
        
        for(boolean[] position : emplacement.getCoordoneeJeu())
            for(int x=0; x<emplacement.getNombreColonne(); x++)
                if (position[x] == true){                    
                        position[x]  = false;
                        position[x-1]= true;
                }
        return true;
    }
    
    /**
     * Change la position vers la droite
     * @return True == Si c'est possible False == Impossible
     */
    public boolean right() {
        for(boolean[] position : emplacement.getCoordoneeJeu())
            if(position[emplacement.getNombreColonne()-1]==true)
                return false;
        
        for(boolean[] position : emplacement.getCoordoneeJeu())
            for(int x=emplacement.getNombreColonne()-1; x>=0; x--)
                if (position[x] == true){                    
                        position[x]  = false;
                        position[x+1]= true;
                }
        return true;
    }
    
    /**
     * Rotationne sens anti-horaire
     * @return True == Si c'est possible False == Impossible
     */
    public boolean rotateLeft(){
        // Copy l'emplacement actuel en cas d'erreur d'attribution
        CoordonneeJeu temporaire = new CoordonneeJeu(emplacement.getNombreColonne(),emplacement.getNombreRangee());
        copyEmplacement(temporaire);
  
        switch(forme){
            case 1: // Bar verticale
                    if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+1, rangee-2, true);
                                        emplacement.setCoordonee(x+1, rangee-1, true);
                                        emplacement.setCoordonee(x+1, rangee+1, true);
                                        emplacement.setCoordonee(x  , rangee, false);
                                        emplacement.setCoordonee(x+2, rangee, false);
                                        emplacement.setCoordonee(x+3, rangee, false);
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    
                    // Bar Horizontale
                    else
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-1, rangee+2, true);
                                        emplacement.setCoordonee(x+1, rangee+2, true);
                                        emplacement.setCoordonee(x+2, rangee+2, true);
                                        emplacement.setCoordonee(x  , rangee,   false);
                                        emplacement.setCoordonee(x  , rangee+1, false);
                                        emplacement.setCoordonee(x  , rangee+3, false);
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }break;  
             /*Toujours la même forme   
            case 2: for(int x = beginIndex; x < 2+beginIndex; x++)*/
                        
            case 3: if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+1, rangee-1, true);
                                        emplacement.setCoordonee(x, rangee,   false);
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==1)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-1, rangee+1, true);
                                        emplacement.setCoordonee(x, rangee+2,   false);
                                        rotation=2;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==2)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x, rangee+2, true);
                                        emplacement.setCoordonee(x+1, rangee+1,   false);
                                        rotation=3;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==3)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+1, rangee+1, true);
                                        emplacement.setCoordonee(x, rangee,false);
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }break;  
                
            case 4: if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x, rangee-1, true);
                                        emplacement.setCoordonee(x+1, rangee+1, true);
                                      
                                        emplacement.setCoordonee(x+2, rangee,     false);
                                        emplacement.setCoordonee(x+1, rangee,   false);
                                        
                                        rotation=3;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==3)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+2, rangee+1,true);
                                        emplacement.setCoordonee(x+2, rangee+2,  true);
                                        
                                        emplacement.setCoordonee(x, rangee,    false);
                                        emplacement.setCoordonee(x, rangee+1,  false);
                                        
                                        rotation=2;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==2)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-1, rangee-1,  true);
                                        emplacement.setCoordonee(x, rangee-1,true);
                       
                                        emplacement.setCoordonee(x-1, rangee+1,  false);
                                        emplacement.setCoordonee(x-2, rangee+1,  false);
                                   
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==1)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-1, rangee+1,true);
                                        emplacement.setCoordonee(x-1, rangee+2,true);
                                        emplacement.setCoordonee(x, rangee+1,true);
                                        emplacement.setCoordonee(x, rangee,false);
                                        emplacement.setCoordonee(x+1, rangee, false);
                                        emplacement.setCoordonee(x+1, rangee+2,false);
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }break;
                
            case 5: if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x, rangee-1, true);
                                        emplacement.setCoordonee(x+1, rangee-1, true);
                                        emplacement.setCoordonee(x, rangee+1, true);
                                        
                                        emplacement.setCoordonee(x+1, rangee,     false);
                                        emplacement.setCoordonee(x+2, rangee,   false);
                                        emplacement.setCoordonee(x+2, rangee+1, false);
                                        
                                        rotation=3;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==3)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+1, rangee+2,true);
                                        emplacement.setCoordonee(x+2, rangee+2,  true);
                                        emplacement.setCoordonee(x, rangee,    false);
                                        emplacement.setCoordonee(x+1, rangee,  false);
                                        rotation=2;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==2)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+2, rangee,  true);
                                        emplacement.setCoordonee(x+2, rangee-1,true);
                       
                                        emplacement.setCoordonee(x, rangee,  false);
                                        emplacement.setCoordonee(x, rangee+1,  false);
                                   
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==1)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-2, rangee+1,  true);
                                        emplacement.setCoordonee(x-1, rangee+1,true);
                       
                                        emplacement.setCoordonee(x, rangee,  false);
                                        emplacement.setCoordonee(x-1, rangee+2,  false);
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }break; 
                
            case 6: if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true && bool[x+1]){
                                        emplacement.setCoordonee(x+1, rangee+1, true);
                                        emplacement.setCoordonee(x+1, rangee+2, true);
                                        
                                        emplacement.setCoordonee(x+1, rangee,   false);
                                        emplacement.setCoordonee(x-1, rangee+1, false);
                                        
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+1, rangee,   true);
                                        emplacement.setCoordonee(x-1, rangee+1, true);
                                        
                                        emplacement.setCoordonee(x+1, rangee+2, false);
                                        emplacement.setCoordonee(x+1, rangee+1, false);
                                        
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }break;  
                
           case 7: if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true && bool[x+1]){           
                                        emplacement.setCoordonee(x, rangee+1, true);
                                        emplacement.setCoordonee(x, rangee+2, true);
                                        
                                        emplacement.setCoordonee(x, rangee,   false);
                                        emplacement.setCoordonee(x+2, rangee+1, false);
                                        
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-1, rangee, true);
                                        emplacement.setCoordonee(x+1, rangee+1, true);
                                        emplacement.setCoordonee(x-1, rangee+1,  false);
                                        emplacement.setCoordonee(x-1, rangee+2, false);
                                        
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;  
                        }break;  
        }
        return true;
    }
    /**
     * Rotation sens horaire
     * @return True == Si c'est possible False == Impossible
     */
    public boolean rotateRight(){
        // Copy l'emplacement actuel en cas d'erreur d'attribution
        CoordonneeJeu temporaire = new CoordonneeJeu(emplacement.getNombreColonne(),emplacement.getNombreRangee());
        copyEmplacement(temporaire);
  
        switch(forme){
            case 1: 
                    if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+1, rangee-2, true);
                                        emplacement.setCoordonee(x+1, rangee-1, true);
                                        emplacement.setCoordonee(x+1, rangee+1, true);
                                        
                                        emplacement.setCoordonee(x  , rangee, false);
                                        emplacement.setCoordonee(x+2, rangee, false);
                                        emplacement.setCoordonee(x+3, rangee, false);
                                        
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-1, rangee+2, true);
                                        emplacement.setCoordonee(x+1, rangee+2, true);
                                        emplacement.setCoordonee(x+2, rangee+2, true);
                                        
                                        emplacement.setCoordonee(x  , rangee,   false);
                                        emplacement.setCoordonee(x  , rangee+1, false);
                                        emplacement.setCoordonee(x  , rangee+3, false);
                                        
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    break;  
            /* Toujours pareil
            case 2: for(int x = beginIndex; x < 2+beginIndex; x++)*/

            case 3: if(rotation==3)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true ){
                                        emplacement.setCoordonee(x+1, rangee+1, true);
                                        
                                        emplacement.setCoordonee(x, rangee+2,   false);
                                        
                                        rotation=2;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==2)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x, rangee+2, true);
                                        emplacement.setCoordonee(x-1, rangee+1,   false);
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==1)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-1, rangee+1, true);
                                        emplacement.setCoordonee(x, rangee,  false);
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+1, rangee-1, true);
                                        emplacement.setCoordonee(x+2, rangee,false);
                                        rotation=3;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }break;  
                
            case 4: if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        
                                        emplacement.setCoordonee(x+2, rangee-1, true);
                                        emplacement.setCoordonee(x+1, rangee-1, true);
                                        emplacement.setCoordonee(x+2, rangee+1, true);
                                        
                                        emplacement.setCoordonee(x, rangee,     false);
                                        emplacement.setCoordonee(x+1, rangee,   false);
                                        emplacement.setCoordonee(x, rangee+1,   false);
                                        
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==1)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-1, rangee+2,true);
                                        emplacement.setCoordonee(x, rangee+2,  true);
                                        
                                        emplacement.setCoordonee(x, rangee,    false);
                                        emplacement.setCoordonee(x+1, rangee,  false);
                                        
                                        rotation=2;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==2)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-2, rangee-1,  true);
                                        emplacement.setCoordonee(x-2, rangee,true);
                                     
                                        emplacement.setCoordonee(x, rangee,    false);
                                        emplacement.setCoordonee(x, rangee+1,  false);
                                        
                                        rotation=3;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    
                    else if(rotation==3)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+1, rangee+1,true);
                                        emplacement.setCoordonee(x+2, rangee+1,true);
                                        
                                        emplacement.setCoordonee(x, rangee,    false);
                                        emplacement.setCoordonee(x+1, rangee+2,false);
                                        
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }break;
                
            case 5: if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        
                                        emplacement.setCoordonee(x+2, rangee-1, true);
                                        emplacement.setCoordonee(x+1, rangee+1, true);
                                        
                                        emplacement.setCoordonee(x, rangee,   false);
                                        emplacement.setCoordonee(x+1, rangee, false);
                                        
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==1)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-2, rangee+1,true);
                                        emplacement.setCoordonee(x-2, rangee+2,  true);
                                        
                                        emplacement.setCoordonee(x, rangee,    false);
                                        emplacement.setCoordonee(x, rangee+1,  false);
                                        
                                        rotation=2;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==2)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x, rangee-1,  true);
                                        emplacement.setCoordonee(x+1, rangee-1,true);
                       
                                        emplacement.setCoordonee(x+1, rangee+1,  false);
                                        emplacement.setCoordonee(x+2, rangee+1,  false);

                                        rotation=3;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else if(rotation==3)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+1, rangee+1,  true);
                                        emplacement.setCoordonee(x+2, rangee+1,  true);
                                        emplacement.setCoordonee(x+2, rangee+2,  true);
                                        
                                        emplacement.setCoordonee(x,   rangee,    false);
                                        emplacement.setCoordonee(x+1, rangee,    false);
                                        emplacement.setCoordonee(x,   rangee+2,  false);
                                        
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }break;
                
            case 6: if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true && bool[x+1]){
                                        
                                        emplacement.setCoordonee(x+1, rangee+1, true);
                                        emplacement.setCoordonee(x+1, rangee+2, true);
                                        
                                        emplacement.setCoordonee(x+1, rangee,   false);
                                        emplacement.setCoordonee(x-1, rangee+1, false);
                                        
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x+1, rangee,   true);
                                        emplacement.setCoordonee(x-1, rangee+1, true);
                                        
                                        emplacement.setCoordonee(x+1, rangee+2, false);
                                        emplacement.setCoordonee(x+1, rangee+1, false);
                                        
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                            
                        }break;  
                
            case 7: if(rotation==0)
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true && bool[x+1]){
                                        
                                        emplacement.setCoordonee(x, rangee+1, true);
                                        emplacement.setCoordonee(x, rangee+2, true);
                                        
                                        emplacement.setCoordonee(x,   rangee,   false);
                                        emplacement.setCoordonee(x+2, rangee+1, false);
                                       
                                        rotation=1;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }
                    else
                        try{
                            int rangee = -1;
                            for(boolean bool[] : emplacement.getCoordoneeJeu()){
                                rangee++;
                                for(int x=0; x<emplacement.getNombreColonne() ; x++){
                                    if(bool[x]==true){
                                        emplacement.setCoordonee(x-1, rangee,   true);
                                        emplacement.setCoordonee(x+1, rangee+1, true);
                                        
                                        emplacement.setCoordonee(x-1, rangee+1, false);
                                        emplacement.setCoordonee(x-1, rangee+2, false);
                                        
                                        rotation=0;
                                        return true;
                                    }
                                }
                            }
                        }catch(Exception e){
                            copyEmplacement(temporaire, emplacement);
                            return false;
                        }break;  
        }
        return true;
    }
    
    /**
     * Effacement des position d'une rangée complétée
     * @param rangee Numéro de la rangée complétée
     */
    public void completedRow(int rangee){
        for(int x=0; x<emplacement.getNombreColonne() ; x++)
            emplacement.setCoordonee(x, rangee, false);
        for(int y=rangee; y>0 ; y--)
            for(int x=0; x<emplacement.getNombreColonne() ; x++)
                if(!emplacement.IsEmpty(x, y)){
                    emplacement.setCoordonee(x, y+1, true);
                    emplacement.setCoordonee(x, y, false);
                }
    }
    /**
     * Copy de l'emplacement actuel
     * @param temporaire Copy de la l'emplacement actuel
     */
    public void copyEmplacement(CoordonneeJeu temporaire){
        int rangee = -1;
        for(boolean bool[] : emplacement.getCoordoneeJeu()){
            rangee++;
            for(int x=0; x< emplacement.getNombreColonne(); x++)
                temporaire.setCoordonee(x, rangee, bool[x]);
        }
    }
    /**
     * Copy de l'emplacement vers un autre l'emplacement 
     * @param copyFrom L'emplacement à copier
     * @param copyTo L'emplacement à modifier
     */
    public void copyEmplacement(CoordonneeJeu copyFrom, CoordonneeJeu copyTo){
        int rangee = -1;
        for(boolean bool[] : copyFrom.getCoordoneeJeu()){
            rangee++;
            for(int x=0; x< copyFrom.getNombreColonne(); x++)
                copyTo.setCoordonee(x, rangee, bool[x]);
        }
    }
}
