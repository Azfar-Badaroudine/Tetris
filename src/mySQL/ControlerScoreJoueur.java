package mySQL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe ControlerScoreJoueur qui gère la liste inscriptions sur la Base de données
 * @author Donavan Martin
 */
public class ControlerScoreJoueur {
    private ArrayList<ScoreJoueur> listJoueur;              // Tableau dynamique des inscrits
    private final DatabaseTools dataInfo = new DatabaseTools();// Outils de transfert de base de données
    
    /**
     * Constructeur ControlerInscription
     */
    public ControlerScoreJoueur(){
        listJoueur = new ArrayList<>();
    }
    
    /**
     * Création d'un ArrayList des inscrits
     * @throws InstantiationException Object cannot be instantiated.
     * @throws IllegalAccessException Method does not have access to the definition of the specified class
     * @throws ClassNotFoundException No definition for the class with the specified name could be found. 
     * @throws SQLException database access error or other errors
     */
    public void creerListeInscrit() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        
        Connection con = dataInfo.createConnexion(); // Ouverture de la connection
        Statement stmt; // Instruction evoyer a MySQL (l'aller)
        ResultSet rs;   // Résultat de la requête     (le retour)
        
        //Get a Statement object
        stmt = con.createStatement();
        rs = stmt.executeQuery("SELECT * " + "from highscore ORDER BY joueur");
        
        // Ajout du résultat dans un ArrayList
        while(rs.next()){

            String joueur  = rs.getString("joueur");
            int temps   = rs.getInt("temps");    
            int lignes  = rs.getInt("lignes");
            int niveau  = rs.getInt("niveau");
            int score   = rs.getInt("score");
                        
            //Ajout l'inscrit à l'ArrayList
            ScoreJoueur temp = new ScoreJoueur(joueur, temps, lignes,niveau,score);
            listJoueur.add(temp);
        }
        dataInfo.closeConnexion(); // Fermeture de la connection
    }
    
    /**
     * Ajout d'un inscrit dand un ArrayList 
     * @param newInscrit L'inscrit a ajouté
     */
    public void add(ScoreJoueur newInscrit){
        listJoueur.add(newInscrit);
    }
    
    /**
     * Getteur d'un inscrits selon l'index de l'ArrayList
     * @param i inedx of ArrayList
     * @return Incrit
     */
    public ScoreJoueur getInscrit(int i){
        return listJoueur.get(i);
    }
    
    /**
     * Get la grandeur de l'ArrayList
     * @return grandeur de l'ArrayList
     */
    public int size(){
        return listJoueur.size();
    }
    
    /**
     * Getteur de l'ArrayList
     * @return listJoueur
     */
    public ArrayList<ScoreJoueur> getListInscrit(){
        return listJoueur;
    }
    
    /**
     * Set le rang des joueurs
     */
    public void SetRangByScore(){
       // Ordre alphabétique selon le score
       Collections.sort(listJoueur, ScoreJoueur.InscritNomComparator);
       int i=0;
       // ajout le rang au joueur
       for(ScoreJoueur joueur : listJoueur){
           i++;
           joueur.setRang(i); 
       } 
    }
    /*public void modify(ScoreJoueur newInscrit) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
        
    }*/
    /*public void remove(ScoreJoueur inscrit) throws SQLException, IllegalAccessException, ClassNotFoundException, InstantiationException{
        
    }*/
    /*public void setInscrit(int index, ScoreJoueur inscrit){
    }
    public String toString(int i){ 
    }
    public int search(int noInscrit){ 
    }*/
    
    
}
