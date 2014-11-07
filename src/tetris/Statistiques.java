/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Donavan
 */
public class Statistiques extends JPanel {
    private BorderLayout layout;
    private GridLayout Center;
    private JLabel statistique;
    
    private JLabel rangeeComplete;
    private JLabel nombreRangeeComplete;
    
    
    
    public Statistiques(Dimension dimension){
        
        layout = new BorderLayout();
        statistique = new JLabel("Statistique");
        statistique.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        statistique.setHorizontalAlignment(SwingConstants.CENTER);;
        
        //add(statistique,1,1);
        
        setLayout(layout);
        
        add(statistique,BorderLayout.NORTH);
       
        
        
        rangeeComplete = new JLabel("Rangé Complèter = 0");
        rangeeComplete.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        
        
        
        //add(rangeeComplete,1,0);
        setBackground(Color.red);
        setSize(dimension);
        setVisible(true);
    }

    public void setRangeeComplete(int rangeeComplete) {
        this.rangeeComplete.setText(String.valueOf(rangeeComplete));
    }
}    


