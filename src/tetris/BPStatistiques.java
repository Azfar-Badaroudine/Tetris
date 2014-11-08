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
 *
 * @author Donavan
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
    
    public BPStatistiques(Dimension dimension){
        repaint();
        System.out.println(this.getWidth());
        setLayout(new BorderLayout());
        // Statistique
        statistique = new JLabel("Statistique");
        setStyle(statistique,25,0);

        // Rangée complétée
        rangeeComplete = new JLabel("Rangé Complèter : ");
        setStyle(rangeeComplete,16,4);
        nombreRangeeComplete = new JLabel("1");
        setStyle(nombreRangeeComplete,16,0);
        nombreRangeeComplete.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        // Niveau 
        niveau = new JLabel("Niveau : ");
        setStyle(niveau,16,4);
        numeroNiveau = new JLabel("5");
        setStyle(numeroNiveau,16,0);
        numeroNiveau.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        createCenterPanel();
        add(statistique,BorderLayout.NORTH);
        add(panelCentre,BorderLayout.CENTER);
                
        setVisible(true);
        this.setOpaque(false);
        panelCentre.setOpaque(false);
    }
    
    public void createCenterPanel(){
        panelCentre = new JPanel(new GridLayout(2,2));
        panelCentre.add(rangeeComplete);
        panelCentre.add(nombreRangeeComplete);
        panelCentre.add(niveau);
        panelCentre.add(numeroNiveau);
    }
    
    public void setRangeeComplete(int rangeeComplete) {
        this.nombreRangeeComplete.setText(String.valueOf(rangeeComplete));
    }
    
    public void enableStatistique(boolean bool){
        if (bool == true){
            statistique. setVisible(true);
            panelCentre.setVisible(true);
        }else {
            statistique. setVisible(false);
            panelCentre.setVisible(false);
        }
    }
    
    public void setStyle(JLabel label,int size, int coté){
         label.setForeground(Color.BLACK);
         label.setFont(new Font("Times New Roman", Font.BOLD, size));
         label.setHorizontalAlignment(coté); 
     }
    
    @Override
    public void paintComponent(Graphics g) {
         g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}    


