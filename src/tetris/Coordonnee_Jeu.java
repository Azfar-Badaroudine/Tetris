package tetris;

import java.util.ArrayList;

/**
 * Classe Coordonnee_Jeu
 * @author Donavan
 */
public class Coordonnee_Jeu {
    private ArrayList<boolean[]> coordonee_jeu;
    private boolean[] coordonnee_colonne;
    private int nombre_colonne;
    private int nombre_rangee;
    
    /**
     * Constructeur tout les case du jeu sont vide
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
     * False == case vide
     * True  == case utilisée
     */
    public void affiche_coordonee_jeu(){

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
     * Getteur nombre de colonne
     * @return nombre_colonne
     */
    public int getNombre_colonne() {
        return nombre_colonne;
    }
    /**
     * Getteur nombre de rangee
     * @return nombre_rangee
     */
    public int getNombre_rangee() {
        return nombre_rangee;
    }
    /**
     * main de la classe Coordonnee_Jeu 
     * @param args 
     */
    public static void main(String[] args) {
        Coordonnee_Jeu coordonnee_jeu = new Coordonnee_Jeu(10,9);
        coordonnee_jeu.affiche_coordonee_jeu();
    }
    
}
    

