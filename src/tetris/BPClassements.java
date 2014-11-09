/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import Scores.ListeScore;
import Scores.ScoreJoueur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static javax.swing.SwingConstants.*;
import javax.swing.SwingUtilities;

/**
 *
 * @author DLU_usager
 */
class BPClassements extends JPanel implements ActionListener {
        private Image image = new ImageIcon("High Score.png").getImage();
        private Classements classements;
        public BPClassements(int width, int height){
            this.setVisible(true);
            setLayout(new BorderLayout());
            classements = new Classements(width, height, this);
            add(classements, BorderLayout.CENTER);
            repaint();
        }
        
        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == classements.getRetour() ){
            this.setVisible(false);
            Fenetre topFrame = (Fenetre) SwingUtilities.getWindowAncestor(this);
            if (topFrame.getJeu() == null)
                topFrame.visibleMenuPrincipal(true);
            else{
                topFrame.getJeu().resume();
                topFrame.visibleInterfaceJeu(true);
            }
        }
    }
}

class Classements extends JPanel {
    
    private JLabel[][] label;
    private JButton retour;
    public Classements(int width, int height, ActionListener parent) {
        
        try{
            ListeScore listeScore = new ListeScore();
          
            //Ajout le rang au joeur
            listeScore.SetRangByScore();

            this.setLayout(null);
            JLabel titre = new JLabel("High Scores");
            setStyle(titre,35,2,200,50,(int) (width*0.5-100), (int) (height*0.21));
            this.add(titre); 
            
            label = new JLabel[11][6];
            String text[] = {"Rang", "Nom","Temps","Lignes","Niveau","Score"};
            String stats[][] = null;

            for (int i=0; i<6; i++){
                label[0][i] = new JLabel(text[i]);
                setStyle(label[0][i],25,2,100,50,(int) (width*0.175*i+20), (int) (height*0.28));
                this.add(label[0][i]);
            }
            
            for(int row=0; row<10 ; row++){
                label[row+1][0] = new JLabel(String.valueOf(listeScore.getListeScores().get(row).getRang()));
                label[row+1][1] = new JLabel(String.valueOf(listeScore.getListeScores().get(row).getJoueur()));
                label[row+1][2] = new JLabel(String.valueOf(listeScore.getListeScores().get(row).getTemps()));
                label[row+1][3] = new JLabel(String.valueOf(listeScore.getListeScores().get(row).getLignes()));
                label[row+1][4] = new JLabel(String.valueOf(listeScore.getListeScores().get(row).getNiveau()));
                label[row+1][5] = new JLabel(String.valueOf(listeScore.getListeScores().get(row).getScore()));
                for (int col=0; col<6; col++){
                    this.add(label[row+1][col]);
                    if (col != 0 && col != 1)
                        setStyle(label[row+1][col],18,4,60,30,(int) (width*0.174*col+20), (int) (height*0.31+(row+1)*0.05*height));
                    else if(col == 0)
                        setStyle(label[row+1][col],18,0,60,30,(int) (width*0.174*col+20), (int) (height*0.31+(row+1)*0.05*height));
                    else
                        setStyle(label[row+1][col],18,2,150,30,(int) (width*0.174*col+20), (int) (height*0.31+(row+1)*0.05*height));
                }
            }
            retour = new JButton("Retour");
            retour.addActionListener(parent);
            retour.setForeground(Color.yellow);
            retour.setFont(new Font("Times New Roman", Font.BOLD, 20));
            retour.setLocation(width-170, height-100);
            retour.setSize(150, 50);
            retour.setOpaque(true);
            retour.setContentAreaFilled(false);
            retour.setBorderPainted(false);
            add(retour, BorderLayout.SOUTH);
            this.setOpaque(false);
            
        }catch(Exception e){
        } 
    }
    
    public JButton getRetour() {
        return retour;
    }
    
    public void setStyle(JLabel label,int size, int coté, int longueur, int largeur, int positionX, int positionY){
         label.setForeground(Color.yellow);
         label.setFont(new Font("Times New Roman", Font.BOLD, size));
         label.setHorizontalAlignment(coté);
         label.setLocation(positionX, positionY);
         label.setSize(longueur, largeur);
     }
}


