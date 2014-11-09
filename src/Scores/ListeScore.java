package Scores;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Classe ListeScore
 * @author Donavan Martin
 */
public class ListeScore {
    private ArrayList<ScoreJoueur> listeScores;   //Liste de scores 
    /**
     * Constructeur ListeScore 
     */
    public ListeScore(){
        listeScores = new ArrayList<>();
        String filename = "src\\Scores\\ScoreListe.txt";

        File file = new File(filename);
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNext()){
                
                String joueur  = scan.next();
                while (scan.hasNextInt() == false)
                     joueur = joueur + " " + scan.next();
                int temps   = Integer.valueOf(scan.next());
                int lignes  = Integer.valueOf(scan.next());
                int niveau  = Integer.valueOf(scan.next());
                int score   = Integer.valueOf(scan.next());
                
                //Ajout l'inscrit à l'ArrayList
                ScoreJoueur temp = new ScoreJoueur(joueur, temps, lignes,niveau,score);
                listeScores.add(temp);
                
                // Comprator de joueurs selon le score
                SetRangByScore();
            }
	}
	catch (Exception e){
            //System.err.println("Erreur d'obtention de la liste des scores ");
	}
    }
    /**
     * Comprator de joueurs selon le score
     */
    public void SetRangByScore(){
       // Ordre meilleur joueur selon le score
       Collections.sort(listeScores, ScoreJoueur.InscritNomComparator);
       int i=0;
       // ajout le rang au joueur
       for(ScoreJoueur joueur : listeScores){
           i++;
           joueur.setRang(i); 
       } 
    }
    /**
     * Getteur de la liste des scores
     * @return listeScores
     */
    public ArrayList<ScoreJoueur> getListeScores() {
        return listeScores;
    }
}
