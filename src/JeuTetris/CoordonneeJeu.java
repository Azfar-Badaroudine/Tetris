package JeuTetris;
import java.util.ArrayList;

/**
 * Classe CoordonneeJeu de dimension 2
 * @author Azfar Badaroudine et Donavan Martin
 */
public class CoordonneeJeu {
    private final ArrayList<boolean[]> coordoneeJeu;// Liste des coordonnée
    private boolean[] coordonneeColonne;            // False == vide   True == utilisée 
    private int nombreColonne;                      // Nombre de colonne
    private int nombreRangee;                       // Nombre de rangee
    
    /**
     * Constructeur de la classe Coordonnee_Jeu 
     * <p> Toute les cases sont initialement vide.
     * @param nombre_colonne le nombre de colonne du jeu
     * @param nombre_rangee  le nombre de rangee du jeu
     */
    public CoordonneeJeu (int nombre_colonne, int nombre_rangee){       
        this.nombreColonne = nombre_colonne;
        this.nombreRangee  = nombre_rangee;
        
        // Initialisation des coordonne (boolean default = false)
        coordoneeJeu = new ArrayList<>();
        for(int i=0; i<nombre_rangee; i++){
            coordonneeColonne = new boolean[nombre_colonne];
            coordoneeJeu.add(coordonneeColonne);
        }
    }
    
    /**
     * Affiche les coordonnée actuel du jeu.
     * <p> False == vide
     * <p> True  == utilisée
     */
    public void afficheTable(){
        System.out.println("\nVoici la table de coordonnées [" + nombreColonne + "]["+ nombreRangee+ "]");
        for(boolean[] temp : coordoneeJeu){
            //System.out.print("Poura la rangée " + rangee);
            for(boolean used : temp)
                System.out.print(used+ " ");
            System.out.println();
        }   
    }
    
    /**
     * Getteur de la table des coordonées du jeu
     * @return coordoneeJeu table des coordonées du jeu
     */
    public ArrayList<boolean[]> getCoordoneeJeu() {
        return coordoneeJeu;
    }
    
    /**
     * Getteur du nombre de colonne
     * @return Le nombre de colonne
     */
    public int getNombreColonne() {
        return nombreColonne;
    }
    
    /**
     * Setteur du nombre de colonne
     * @param nombre_colonne Le nombre de colonne
     */
    public void setNombreColonne(int nombre_colonne){
        this.nombreColonne = nombre_colonne;
    }
    
    /**
     * Getteur du nombre de rangée
     * @return Le nombre de rangée
     */
    public int getNombreRangee() {
        return nombreRangee;
    }
    
    /**
     * Setteur du nombre de rangée
     * @param nombre_rangee Le nombre de rangée
     */
    public void setNombreRangee(int nombre_rangee){
        this.nombreRangee = nombre_rangee;
    }

    /**
     * Vérifie si la coordonée en paramètre fait partit de la table.
     * @param colonne le numéro de colonne a vérifier
     * @param rangee le numéro de rangée a vérifier
     * @return  True == Coordonnée n'est pas dans la table  <p>  False == Coordonnée dans la table 
     */
    public boolean outOfBound(int colonne, int rangee){
        if(colonne > nombreColonne || rangee > nombreRangee){
            System.out.println("Cette coordonée ( " + colonne + " , " + rangee + " ) n'est pas dans la table.");
            return true;
        }
        else{
            System.out.println("Cette coordonée ( " + colonne + " , " + rangee + " ) est dans table.");
            return false;
        }
    }
    
    /**
     * Vérifie si la coordonée est vide ou utilisé.
     * @param colonne le numéro de colonne a vérifier
     * @param rangee le numéro de rangée a vérifier
     * @return  False == vide <p>True == utilisée 
     */
    public boolean IsEmpty(int colonne, int rangee){
        if(coordoneeJeu.get(rangee)[colonne]==false){
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * Setteur d'une coordonée dans la table.
     * @param colonne le numéro de colonne a setter
     * @param rangee  le numéro de rangée a setter
     * @param use     False == vide True == utilisée 
     */
    public void setCoordonee(int colonne, int rangee , boolean use){
        coordoneeJeu.get(rangee)[colonne]= use;
    }
    /**
     * Vérfie une table de coordonnée completement vide
     * @return true == vide  false != vide
     */
    public boolean isAllEmpty(){
        for(boolean[] bool_ : coordoneeJeu)
            for(boolean bool : bool_)
                if(bool)
                    return false;
        return true;
    }
}
    

