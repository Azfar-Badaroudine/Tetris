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
            System.out.print("RETOUR");
            this.setVisible(false);
            Fenetre topFrame = (Fenetre) SwingUtilities.getWindowAncestor(this);
            if (topFrame.getJeu() == null)
                topFrame.BuildAccueil();
            else{
                topFrame.getJeu().getTimer().start();
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
            titre.setFont(new Font("Times New Roman", Font.BOLD, 35));
            titre.setForeground(Color.yellow);
            titre.setSize(200, 50);
            titre.setLocation((int) (width*0.5-titre.getWidth()*0.5), (int) (height*0.21));
            this.add(titre); 
            
            label = new JLabel[11][6];
            String text[] = {"Rang", "Nom","Temps","Lignes","Niveau","Score"};
            String stats[][] = null;

            for (int i=0; i<6; i++){
                label[0][i] = new JLabel(text[i]);
                label[0][i].setFont(new Font("Times New Roman", Font.BOLD, 25));
                label[0][i].setForeground(Color.yellow);
                label[0][i].setSize(100, 50);
                label[0][i].setLocation((int) (width*0.175*i+20), (int) (height*0.28));
                this.add(label[0][i]);
            }
            
            int row =1;
            for(ScoreJoueur listescore :listeScore.getListeScores()){
                label[row][0] = new JLabel(String.valueOf(listescore.getRang()));
                label[row][1] = new JLabel(String.valueOf(listescore.getJoueur()));
                label[row][2] = new JLabel(String.valueOf(listescore.getTemps()));
                label[row][3] = new JLabel(String.valueOf(listescore.getLignes()));
                label[row][4] = new JLabel(String.valueOf(listescore.getNiveau()));
                label[row][5] = new JLabel(String.valueOf(listescore.getScore()));
                for (int col=0; col<6; col++){
                    label[row][col].setFont(new Font("Times New Roman", Font.BOLD, 18));
                    label[row][col].setForeground(Color.yellow);
                    label[row][col].setSize(60, 30);
                    label[row][col].setLocation((int) (width*0.174*col+20), (int) (height*0.31+row*0.05*height));
                    this.add(label[row][col]);
                    if (col != 0 && col != 1)
                        label[row][col].setHorizontalAlignment(RIGHT);
                    else if(col == 0)
                        label[row][0].setHorizontalAlignment(CENTER);
                    else
                        label[row][col].setSize(150, 30);
                }
                row ++;
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
    
}

