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
import java.awt.Dimension;
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
import javax.swing.SwingConstants;

/**
 *
 * @author DLU_usager
 */
class BPBestScore extends JPanel {
    private Image image = new ImageIcon("Sans titre.png").getImage();


    private JPanel centre; 
    
    
    private JLabel bestScore  = new JLabel("Best Score");
    private JLabel joueur     = new JLabel("Joueur : ");
    private JLabel playerName = new JLabel("qwerty");
    private JLabel score      = new JLabel("31455441");
    private JLabel points     = new JLabel("Points :");
        
    public BPBestScore(){
        setLayout(new BorderLayout());
        BestScore();
        repaint();
    }
     public void BestScore() {
    
  
        centre = new JPanel(new GridLayout(2,2));
        
        centre.add(joueur,     BorderLayout.CENTER);
        centre.add(playerName, BorderLayout.CENTER);
        centre.add(points,     BorderLayout.CENTER);
        centre.add(score,      BorderLayout.CENTER);
        
        bestScore.setHorizontalAlignment(SwingConstants.CENTER);

        this.add(bestScore, BorderLayout.NORTH);
        this.add(centre, BorderLayout.CENTER);
        this.setOpaque(false); 
        centre.setOpaque(false);
     }   
    @Override
    public void paintComponent(Graphics g) {
         g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}


   
        

