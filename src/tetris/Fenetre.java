/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Azfar
 */
public class Fenetre extends JFrame implements ActionListener{
    private JLabel block[][] = new JLabel[10][22]; // Vérification de l'optimisation --> plus tard
    private JPanel pan;
    
    public Fenetre() {
        super("Tetris");
        setFocusable(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        init();
    }

    /**
     * Permet d'initalisé les composantes de la fenêtre
     */
    private void init() {
        setSize(550,700); //On donne une taille à notre fenêtre
        setResizable(false);
        setLocationRelativeTo(null);
        
        pan = new JPanel(null);
        this.setContentPane(pan);
        
        for (int i=0; i<10; i++){
            for (int j = 0; j<22; j++){
                block[i][j] = new JLabel();
                block[i][j].setLocation(i*20, j*20+50);
                block[i][j].setSize(20, 20);
                block[i][j].setOpaque(true);
                block[i][j].setBackground(Color.WHITE);
                block[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                pan.add(block[i][j]);
                if (j < 2)
                    block[i][j].setVisible(false);
            }
        }
    }
    
    public void setColor(int i, int j, int type){
        switch (type){
            case 1: block[i][j].setBackground(Color.CYAN); break;
            case 2: block[i][j].setBackground(Color.BLUE); break;
            case 3: block[i][j].setBackground(Color.ORANGE); break;
            case 4: block[i][j].setBackground(Color.YELLOW); break;
            case 5: block[i][j].setBackground(Color.GREEN); break;
            case 6: block[i][j].setBackground(Color.PINK); break;
            case 7: block[i][j].setBackground(Color.RED); break;
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
}
