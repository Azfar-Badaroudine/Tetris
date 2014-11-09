package JeuTetris;


import Scores.ListeScore;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * manipulation du panel bestscore en bas à droite de l'interface du jeu
 * @author Azfar Badaroudine et Donavan Martin
 */
class BPBestScore extends JPanel {
     // Image background
    private Image image = new ImageIcon("test2.jpg").getImage();

    private JPanel centre; 
    
    private JLabel bestScore  = new JLabel("Best Score");
    private JLabel joueur     = new JLabel("Joueur :  ");
    private JLabel playerName = new JLabel("qwerty");
    private JLabel score      = new JLabel("31455441");
    private JLabel points     = new JLabel("Points :  ");
      
    /**
     * Constructeur, initialise et positionne les labels dans le panel
     */
    public BPBestScore(){
        setBorder(BorderFactory.createEmptyBorder(30, 5, 0, 0));
        
        //Recherche le highest score
        ListeScore listeScore = new ListeScore();
        playerName.setText(String.valueOf(listeScore.getListeScores().get(0).getJoueur()));
        score.     setText(String.valueOf(listeScore.getListeScores().get(0).getScore ()));
        
        // BestScore 
        setStyle(bestScore,25,1);
        
        // Nom du Joueur et Pointage
        centre = new JPanel(new GridLayout(2,2));
        centre.add(joueur,     BorderLayout.CENTER);
        centre.add(playerName, BorderLayout.CENTER);
        centre.add(points,     BorderLayout.CENTER);
        centre.add(score,      BorderLayout.CENTER);
        centre.setOpaque(false);
        
        setStyle(joueur,    20,0);
        setStyle(playerName,20,0);
        setStyle(points,    20,0);
        setStyle(score,     20,0);
        
        // Ajout au panel
        setLayout(new BorderLayout());
        this.add(bestScore, BorderLayout.NORTH);
        this.add(centre, BorderLayout.CENTER);
        this.setOpaque(false);   
        repaint();
     } 
     
    /**
     * Modifie l'apparance d'un label selon les besoins
     * @param label le label a modifié
     * @param size  taille de la police
     * @param font type d'écriture (1 = gras, 0 = standard)
     */
     public void setStyle(JLabel label,int size, int font){
         label.setForeground(Color.BLACK);
         label.setFont(new Font("Times New Roman", font, size));
         label.setHorizontalAlignment(SwingConstants.CENTER); 
     }
     
    /**
     * Permet de mettre une image dans le background du panel
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
         g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}


   
        

