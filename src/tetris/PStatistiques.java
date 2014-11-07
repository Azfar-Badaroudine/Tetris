/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Donavan
 */
public class PStatistiques extends JPanel {
    private BorderLayout layout;
    private JPanel panelCentre;
    
    private JLabel statistique;
    
    private JLabel rangeeComplete;
    private JLabel nombreRangeeComplete;
    
    private JLabel niveau;
    private JLabel numeroNiveau;
    
  
    public PStatistiques(Dimension dimension){
        layout = new BorderLayout();
        setLayout(layout);
        statistique = new JLabel("Statistique");
        statistique.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        statistique.setHorizontalAlignment(SwingConstants.CENTER);;

        rangeeComplete = new JLabel("Rangé Complèter : ");
        rangeeComplete.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        nombreRangeeComplete = new JLabel("1");
        nombreRangeeComplete.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        niveau = new JLabel("Niveau : ");
        niveau.setFont(new Font("Times New Roman", Font.BOLD, 16));
        
        numeroNiveau = new JLabel("5");
        numeroNiveau.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        
        panelCentre = new JPanel(new GridLayout(2,2));
        
        panelCentre.add(rangeeComplete);
        panelCentre.add(nombreRangeeComplete);
        panelCentre.add(niveau);
        panelCentre.add(numeroNiveau);
        
        add(statistique,BorderLayout.NORTH);
        add(panelCentre,BorderLayout.CENTER);
                
        setVisible(true);
    }
    
    public void setRangeeComplete(int rangeeComplete) {
        this.nombreRangeeComplete.setText(String.valueOf(rangeeComplete));
    }
}    


