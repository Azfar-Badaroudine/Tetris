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

/**
 *
 * @author DLU_usager
 */
class BPBestScore extends JPanel {
    private Image image = new ImageIcon("Sans titre.png").getImage();
        
    public BPBestScore(){
        setLayout(new BorderLayout());
        add(new BestScore(), BorderLayout.CENTER);
        repaint();
    }
       
    @Override
    public void paintComponent(Graphics g) {
         g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}

class BestScore extends JPanel {
    private JLabel bestScore  = new JLabel("Best Score");
    private JLabel joueur     = new JLabel("Joueur : ");
    private JLabel playerName = new JLabel("qwerty");
    private JLabel score      = new JLabel("31455441");
    private JLabel points     = new JLabel("Points :");
    
    public BestScore() {
        this.setLayout(new GridLayout(2,2));
        this.add(joueur);
        this.add(playerName);
        this.add(score);
        this.add(points);
        this.setOpaque(false);
        
    }
}