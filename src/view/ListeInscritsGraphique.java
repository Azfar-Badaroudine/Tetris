/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controler.ControlerInscription;
import controler.FenetreListeInscrits;
import java.sql.Connection;

/**
 *
 * @author DLU_usager
 */
public class ListeInscritsGraphique {
    public static void main(String[] args) {
        try{
            ControlerInscription listeInscrits = new ControlerInscription();
            listeInscrits.creerListeInscrit();
            FenetreListeInscrits = new FenetreListeInscrits(listeInscrits);
	}
	catch (Exception e){
            System.err.println("Erreur dans connexion " + e.getMessage());
	}

    }

}
