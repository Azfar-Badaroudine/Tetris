package tetris;

import java.util.ArrayList;

/**
 * Classe Coordonnee_Jeu
 * @author Donavan
 */
public class Coordonnee_Jeu {
    private ArrayList<boolean[]> coordonee_jeu;
    private boolean[] coordonnee_colonne; //  False == vide   True == utilisée 
    private int nombre_colonne;
    private int nombre_rangee;
    
    /**
     * Constructeur de la classe Coordonnee_Jeu 
     * <p> Toute les cases sont initialement vide.
     * @param nombre_colonne le nombre de colonne du jeu
     * @param nombre_rangee  le nombre de rangee du jeu
     */
    public Coordonnee_Jeu (int nombre_colonne, int nombre_rangee){       
        this.nombre_colonne = nombre_colonne;
        this.nombre_rangee  = nombre_rangee;
        
        coordonee_jeu = new ArrayList<>();
        for(int i=0; i<nombre_rangee; i++){
            // Détermine le nombre de colonne
            coordonnee_colonne = new boolean[nombre_colonne];
            for (int j=0; j<nombre_colonne; j++){
                coordonnee_colonne[j]=false;
            }
            coordonee_jeu.add(coordonnee_colonne);
        }
    }
    /**
     * Affiche les coordonnée actuel du jeu.
     * <p> False == vide
     * <p> True  == utilisée
     */
    public void afficheTable(){

        System.out.println("Le nombre de colonne est :" + nombre_colonne);
        System.out.println("Le nombre de rangée  est :" + nombre_rangee );
        
        int rangee = 0;
        for(boolean[] temp : coordonee_jeu){
            System.out.print("Poura la rangée " + rangee);
            for(boolean used : temp)
                System.out.print(" "+ used);
            System.out.println();
            rangee++;
        }   
    }
    /**
     * Getteur du nombre de colonne
     * @return nombre_colonne le nombre de colonne
     */
    public int getNombreColonne() {
        return nombre_colonne;
    }
    /**
     * Setteur du nombre de colonne
     * @param nombre_colonne le nombre de colonne
     */
    public void setNombreColonne(int nombre_colonne){
        this.nombre_colonne = nombre_colonne;
    }
    /**
     * Getteur du nombre de rangee
     * @return nombre_rangee le nombre de rangee
     */
    public int getNombreRangee() {
        return nombre_rangee;
    }
    /**
     * Setteur du nombre de rangée
     * @param nombre_rangee le nombre de rangee
     */
    public void setNombreRangee(int nombre_rangee){
        this.nombre_rangee = nombre_rangee;
    }

    /**
     * Vérifie si la coordonée en paramètre est dans la table de coordonées.
     * @param colonne le numéro de colonne a vérifier
     * @param rangee le numéro de rangée a vérifier
     * @return  True == Coordonnée n'est pas dans la table  |  False == Coordonnée dans la table 
     */
    public boolean outOfBound(int colonne, int rangee){
        if(colonne > nombre_colonne || rangee > nombre_rangee)
            return true;
        else
            return false;
    }
    /**
     * Vérifie si la coordonée est vide ou utilisé.
     * @param colonne le numéro de colonne a vérifier
     * @param rangee le numéro de rangée a vérifier
     * @return  False == vide   True == utilisée 
     */
    public boolean isEmpty(int colonne, int rangee){
        if(colonne > nombre_colonne || rangee > nombre_rangee)
            return true;
        else
            return false;
    }
    
    
    
    public static void main(String[] args) {
        Coordonnee_Jeu coordonnee_jeu = new Coordonnee_Jeu(10,9);
        coordonnee_jeu.afficheTable();
    }
    
    
}
    

