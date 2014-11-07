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
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author DLU_usager
 */

class PClassements extends JPanel {
        Image bg = new ImageIcon("High Score.png").getImage();
        
        public PClassements(){
            setLayout(new BorderLayout());
            add(new PClassements(), BorderLayout.CENTER);
            repaint();
        }
        
        @Override
        public void paintComponent(Graphics g) {
            g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}
class Classements extends JPanel {
    
    public Classements() {
        
        try{
            ListeScore listeScore = new ListeScore();
          
            //Ajout le rang au joeur
            listeScore.SetRangByScore();
     
            this.setLayout(new GridLayout(11,5));

            JLabel[][] label = new JLabel[11][6];
            String text[] = {"Rang", "Nom","Temps","Lignes","Niveau","Score","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"};
            String stats[][] = null;

            for (int i=0; i<6; i++){
                label[0][i] = new JLabel(text[i]);
                label[0][i].setFont(new Font("Times New Roman", Font.BOLD, 10));
                this.add(label[0][i]);
            }
            int row =1;
            for(ScoreJoueur listescore :listeScore.getListeScores()){
                label[row][0] = new JLabel(String.valueOf(listescore.getRang()));
                label[row][1] = new JLabel(listescore.getJoueur());
                label[row][2] = new JLabel(String.valueOf(listescore.getTemps()));
                label[row][3] = new JLabel(String.valueOf(listescore.getLignes()));
                label[row][4] = new JLabel(String.valueOf(listescore.getNiveau()));
                label[row][5] = new JLabel(String.valueOf(listescore.getScore()));
                for (int col=0; col<6; col++){
                    label[row][col].setFont(new Font("Times New Roman", Font.BOLD, 10));
                    this.add(label[row][col]);
                }
                row ++;
            }  

            this.setOpaque(false);
        }catch(Exception e){
        } 
    }
}


