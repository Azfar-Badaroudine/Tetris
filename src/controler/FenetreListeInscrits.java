/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Permet de créer la fenêtre graphique pour afficher ma liste
 * @author Clovis Hallé
 */
public class FenetreListeInscrits extends JFrame{
    private ControlerInscription listeInscrit;
    private JTable table;
    private DefaultTableModel model; 

    /**
     * Permet de créer la fenêtre graphique pour afficher ma liste
     * @param listeInscrit 
     */
    public FenetreListeInscrits(ControlerInscription listeInscrit) {
        this.listeInscrit = listeInscrit; 
        build();
    }
    
    /**
     * Fait le build de la fenetre
     */
    private void build(){
        
        setTitle("Donnés");
        setSize(700,280);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
    }

    /**
     * Contient toute les composantes de ma fenetre
     * @return Un pânel a afficher
     */
    private JPanel buildContentPane() {
    int i = 0;
    JPanel panel = new JPanel(new BorderLayout());   //Panel principale de fenêtre
    
    model = new DefaultTableModel(new Object[]{"Id inscrit",
                                            "Nom","Prenom","No Telephone"},0);
    model.addRow(new Object[]{"Id inscrit","Nom","Prenom","No Telephone"});
    table = new JTable(model);
    while (i < listeInscrit.size()){
        addRow(i);
        i++;
    }
    panel.add(table);
    
    return panel;
    }
    
    /**
     * Permet d'ajouter une rangé d'info dans la table
     * @param i positon de l'info qu'on veut ajouter
     */
    private void addRow(int i){
        this.model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{
            listeInscrit.getInscrit(i).getId(),
            listeInscrit.getInscrit(i).getNom(),
            listeInscrit.getInscrit(i).getPrenom(),
            listeInscrit.getInscrit(i).getTelephone()
        });
    }
}
