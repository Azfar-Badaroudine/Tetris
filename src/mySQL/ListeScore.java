package mySQL;

/**
 * Classe ListeInscritsConsole Affichage console de la liste des inscrits
 * @author Donavan Martin
 */
public class ListeScore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	try{
            ControlerScoreJoueur listeScore = new ControlerScoreJoueur();
            listeScore.creerListeInscrit();
          
            // Ajout le rang au joeur
            listeScore.SetRangByScore();
            
            System.out.println("Voici la liste des scores:");
            for(int i=0;i< listeScore.size();i++)
                    listeScore.getInscrit(i).affiche();
	}
	catch (Exception e){
            System.err.println("Erreur dans connexion " + e.getMessage());
	}
    }
}
