package Scores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tetris.Fenetre;

/**
 * Classe ListeInscritsConsole Affichage console de la liste des inscrits
 * @author Donavan Martin
 */
public class ListeScore {
    private ArrayList<ScoreJoueur> listeScores;   //Liste de scores 
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
                
                SetRangByScore();
            }
	}
	catch (Exception e){
            System.err.println("Erreur dans connexion " + e.getMessage());
	}
        for(int i=0;i< listeScores.size();i++)
                    listeScores.get(i).affiche();
    }
    public void SetRangByScore(){
       // Ordre alphabétique selon le score
       Collections.sort(listeScores, ScoreJoueur.InscritNomComparator);
       int i=0;
       // ajout le rang au joueur
       for(ScoreJoueur joueur : listeScores){
           i++;
           joueur.setRang(i); 
       } 
    }

    public ArrayList<ScoreJoueur> getListeScores() {
        return listeScores;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ListeScore Fen = new ListeScore();

        
        //Une fois que tu click sur Play:
        //JeuTetris jeu = new JeuTetris();
        
        //Une fois que tu appuies sur espace pour starter
        //jeu.start();
    }
    

}
