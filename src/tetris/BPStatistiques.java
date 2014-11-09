package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * manipulation du panel statistique en haut à droite de l'interface du jeu
 * @author Azfar Badaroudine et Donavan Martin
 */
public class BPStatistiques extends JPanel {
    private Image image = new ImageIcon("test3.jpg").getImage();
    // Layout = NORTH 
    private JLabel statistique;
    
    // Layout = CENTER
    private JPanel panelCentre;
    private JLabel rangeeComplete;
    private JLabel nombreRangeeComplete;
    private JLabel niveau;
    private JLabel numeroNiveau;
    private JLabel score;
    private JLabel scoreActuel;
    private JLabel temps;
    private JLabel chrono;
    
    /**
     * Constructeur, initialise et positionne les labels dans le panel
     */
    public BPStatistiques(){
        repaint();
        setLayout(new BorderLayout());
        
        // Statistique
        statistique = new JLabel("Statistiques");
        setStyle(statistique,25,1);

        // Rangée complétée
        rangeeComplete = new JLabel("Lignes :  ");
        setStyle(rangeeComplete,20,0);
        nombreRangeeComplete = new JLabel("0");
        setStyle(nombreRangeeComplete,20,0);
        nombreRangeeComplete.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        // Score
        score = new JLabel("Points :  ");
        setStyle(score,20,0);
        scoreActuel = new JLabel("0");
        setStyle(scoreActuel,20,0);
        scoreActuel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        // Temps
        temps = new JLabel("Temps :  ");
        setStyle(temps,20,0);
        chrono = new JLabel("0");
        setStyle(chrono,20,0);
        chrono.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        // Niveau 
        niveau = new JLabel("Niveau :  ");
        setStyle(niveau,20,0);
        numeroNiveau = new JLabel("1");
        setStyle(numeroNiveau,20,0);
        numeroNiveau.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        createCenterPanel();
        add(statistique,BorderLayout.NORTH);
        add(panelCentre,BorderLayout.CENTER);
                
        setVisible(true);
        this.setOpaque(false);
        panelCentre.setOpaque(false);
    }
    
    /**
     * Ajoute tous les labels dans le panel centrale du panel statistique
     */
    public void createCenterPanel(){
        panelCentre = new JPanel(new GridLayout(4,2));
        panelCentre.add(rangeeComplete);
        panelCentre.add(nombreRangeeComplete);
        panelCentre.add(score);
        panelCentre.add(scoreActuel);
        panelCentre.add(temps);
        panelCentre.add(chrono);
        panelCentre.add(niveau);
        panelCentre.add(numeroNiveau);
    }
    
    /**
     * setteur du nombre de lignes accomplies
     * @param rangeeComplete nombre de lignes accomplies
     */
    public void setRangeeComplete(int rangeeComplete) {
        nombreRangeeComplete.setText(String.valueOf(rangeeComplete));
    }
    
    /**
     * setteur du temps écoulé depuis le début de la partie
     * @param temps temps écoulé depuis le début de la partie
     */
    public void setChrono(int temps){
        chrono.setText(String.valueOf(temps));
    }
    
    /**
     * setteur du niveau de difficulté
     * @param niveau niveau de difficulté
     */
    public void setNiveau(int niveau){
        numeroNiveau.setText(String.valueOf(niveau));
    }
    
    /**
     * setteur du score actuel du joueur
     * @param score score actuel du joueur
     */
    public void setScore(int score){
        scoreActuel.setText(String.valueOf(score));
    }
    
    /**
     * Permet de mettre le panel invisible pour en afficher un autre ou de le remettre visible
     * @param bool true = visible, false = invisible
     */
    public void enableStatistique(boolean bool){
        if (bool == true){
            statistique. setVisible(true);
            panelCentre.setVisible(true);
        }else {
            statistique. setVisible(false);
            panelCentre.setVisible(false);
        }
    }
    
    /**
     * Modifie l'apparance d'un label selon les besoins
     * @param label label à modifier
     * @param size taille de la police
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


