package mySQL;

import java.util.Comparator;

/**
 * Classe ScoreJoueur 
 * @author Donavan Martin
 */
public class ScoreJoueur {
   
    private int    rang;        // Rang du joueur
    private String joueur;      // Nom du joueur
    private int    temps;       // Temps jouée
    private int    lignes;      // Lignes complétés
    private int    niveau;      // Niveau atteint
    private int    score;       // Score du joueur

    /**
     * Constructeur du scoreJoueur
     * @param joueur Nom du joueur
     * @param temps  Temps jouée
     * @param lignes Lignes complétés
     * @param niveau le niveau jouée
     * @param score  Score du joueur
     */
    public ScoreJoueur(String joueur, int temps, int lignes,int niveau, int score) {
        this.rang   = 0;      // gérer manuellement avec la fonction comparator
        this.joueur = joueur;
        this.temps  = temps;
        this.lignes = lignes;
        this.niveau = niveau;
        this.score  = score;  
    }

    /**
     * Getteur du rang du joeur
     * @return rang le rang du joueur
     */
    public int getRang() {
        return rang;
    }
    
    /**
     * Setteur du rang du joueur
     * @param rang le rang du joueur
     */
    public void setRang(int rang) {
        this.rang = rang;
    }
  
    /**
     * Getteur le nom du joueur
     * @return joueur nom du joueur
     */
    public String getJoueur() {
        return joueur;
    }
    
    /**
     * Setteur du nom joueur
     * @param joueur le nom du joueur
     */
    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }
    
    /**
     * Getteur du temps jouée
     * @return temps le temps jouée 
     */
    public int getTemps() {
        return temps;
    }

    /**
     * Setteur du temps jouées
     * @param temps le temps jouée
     */
    public void setTemps(int temps) {
        this.temps = temps;
    }

    /**
     * Getteur du nombre de lignes complétés
     * @return lignes le nombres de lugne complétés
     */
    public int getLignes() {
        return lignes;
    }
    
    /**
     * Setteur du nombre de lignes complétés
     * @param lignes le nombre de lignes complétés
     */
    public void setLignes(int lignes) {
        this.lignes = lignes;
    }
    
    /**
     * Getteur du niveau jouée
     * @return niveau le niveau jouée
     */
    public int getNiveau() {
        return niveau;
    }
    
    /**
     * Setteur du niveau jouée
     * @param niveau le niveau jouée
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }
    
   /**
    * Gatteur du score du joueur
    * @return score le score du joueur
    */
    public int getScore() {
        return score;
    }

    /**
     * Setteur du score du joeur
     * @param score le score du joueur
     */
    public void setScore(int score) {
        this.score = score;
    }
      
    /**
     * Afficahge en console de l'inscrit
     */
    public void affiche(){
        System.out.print(String.format  ("%-20s", rang  ));
        System.out.print(String.format  ("%-20s", joueur));
        System.out.print(String.format  ("%-20s", temps ));
        System.out.print(String.format  ("%-20s", lignes));
        System.out.print(String.format  ("%-20s", niveau));
        System.out.println(String.format("%-20s", score ));
    }

    /**
     * Comparator ordre du score des joueurs
     */
    public static Comparator<ScoreJoueur> InscritNomComparator = new Comparator<ScoreJoueur>() {

     
        @Override
        public int compare(ScoreJoueur inscrit1, ScoreJoueur inscrit2) {
            Integer nom1 = inscrit1.getScore();
	    Integer nom2 = inscrit2.getScore();
 
	    //ascending order
	    //return nom1.compareTo(nom2);
 
	    //descending order
	    return nom2.compareTo(nom1);
        }
    };
}
