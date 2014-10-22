package tetris;

import java.util.ArrayList;

/**
 * Classe CoordonneeJeu
 * @author Donavan
 */
public class CoordonneeJeu {
    private final ArrayList<boolean[]> coordoneeJeu;
    private boolean[] coordonneeColonne; //  False == vide   True == utilisée 
    private int nombreColonne;
    private int nombreRangee;
    
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
            //System.out.println("La coordonée ( " + colonne + " , " + rangee + " ) est vide.");
            return true;
        }
        else{
            //System.out.println("La coordonée ( " + colonne + " , " + rangee + " ) est utilisé.");
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

    public static void main(String[] args) {
        // Construit
        CoordonneeJeu coordonnee_jeu = new CoordonneeJeu(10,9);
        // Affiche
        coordonnee_jeu.afficheTable();
        
        // Set 1ere coordonnée
        coordonnee_jeu.setCoordonee(0, 0, true);
        // Set dernière coordonée
        coordonnee_jeu.setCoordonee(coordonnee_jeu.getNombreColonne()-1, coordonnee_jeu.getNombreRangee()-1, true);
        
        
        
        // Cette coordonnée exist t'elle?
        coordonnee_jeu.outOfBound(500, 500);
        coordonnee_jeu.outOfBound(0,0);
        
        // Réaffiche la table
        coordonnee_jeu.afficheTable();
        
        // Vérifie les coordonées
        coordonnee_jeu.IsEmpty(0,0);
        coordonnee_jeu.IsEmpty(9,8);
        
    }
    
    
}
    

