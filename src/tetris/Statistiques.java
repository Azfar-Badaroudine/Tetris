/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Donavan
 */
public class Statistiques extends JPanel {
    private GridLayout layout;
    private JLabel statistique;
    
    private JLabel rangeeComplete;
    private JLabel nombreRangeeComplete;
    
    
    
    public Statistiques(Dimension dimension){
        layout = new GridLayout(5,2);
        setLayout(layout);
       
        statistique = new JLabel("Statistique");
        rangeeComplete = new JLabel("Rangé Complèter = 0");
        
        add(statistique,0,0);
        add(rangeeComplete,1,0);
        setBackground(Color.red);
        setSize(dimension);
        setVisible(true);
    }
   
}    


