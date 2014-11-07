package tetris;


import java.awt.BorderLayout;
import java.awt.Font;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
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
         // BestScore 
        bestScore.setHorizontalAlignment(SwingConstants.CENTER); 
        bestScore.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        this.add(bestScore, BorderLayout.NORTH);
        
        // Nom du Joueur et Pointage
        centre = new JPanel(new GridLayout(2,2));
        centre.add(joueur,     BorderLayout.CENTER);
        centre.add(playerName, BorderLayout.CENTER);
        centre.add(points,     BorderLayout.CENTER);
        centre.add(score,      BorderLayout.CENTER);
        centre.setOpaque(false);
        this.add(centre, BorderLayout.CENTER);

        this.setOpaque(false);   
     }   
    @Override
    public void paintComponent(Graphics g) {
         g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}


   
        

