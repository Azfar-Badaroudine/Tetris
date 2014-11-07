package tetris;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Donavan
 */
public class BPStatistiques extends JPanel {
    // Layout = NORTH 
    private JLabel statistique;
    
    // Layout = CENTER
    private JPanel panelCentre;
    private JLabel rangeeComplete;
    private JLabel nombreRangeeComplete;
    private JLabel niveau;
    private JLabel numeroNiveau;
    
    public BPStatistiques(Dimension dimension){
        setLayout(new BorderLayout());
        // Statistique
        statistique = new JLabel("Statistique");
        statistique.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        statistique.setHorizontalAlignment(SwingConstants.CENTER);;

        // Rangée complétée
        rangeeComplete = new JLabel("Rangé Complèter : ");
        rangeeComplete.setFont(new Font("Times New Roman", Font.BOLD, 16)); 
        nombreRangeeComplete = new JLabel("1");
        nombreRangeeComplete.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        // Niveau 
        niveau = new JLabel("Niveau : ");
        niveau.setFont(new Font("Times New Roman", Font.BOLD, 16));
        numeroNiveau = new JLabel("5");
        numeroNiveau.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        
        createCenterPanel();
        add(statistique,BorderLayout.NORTH);
        add(panelCentre,BorderLayout.CENTER);
                
        setVisible(true);
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
}    


