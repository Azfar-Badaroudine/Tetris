/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


/**
 *
 * @author Azfar
 */
public class Fenetre extends JFrame implements ActionListener{
    //Menu principal
    private JLabel principal = new JLabel();
    // ---------------------Menu------------------------
    private JMenuBar menuBar;
       
    //[1] Menu -> Affichage
    private JMenu menuJeu;
    private JMenuItem nouvellePartie;
    private JMenuItem couperSon;
    private JMenuItem classement;
    // Menu -> Affichage -> difficulté
    private JMenu difficulte;
    private JMenuItem facile;
    private JMenuItem moyen;
    private JMenuItem difficile;
    private JMenuItem personnalise;
    
    //[2] Menu -> ?
    private JMenu menuQuestionnement;
    private JMenuItem aide;
    private JMenuItem createurs;
    //--------------------------------------------------
    
    //-----------------Layout---------------------------
    
    // Les pannels 
    private BorderLayout layoutFenetre;
    private JPanel nord;  // Les pièces suivantes du jeu
    private JPanel centre;// Le jeu et les satistique
    
    // Contenue du panel nord
    private JLabel message_suivant;
    ///////////////private TShape next_block[3];
    
    // Contenue du panel centre
    //////////private jeu_tetris jeu;
    //////////private Statistique statistique;
    //---------------------------------------------------
    
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
        // menu principal
        principal.setIcon(new ImageIcon("principal.png"));
        ImageIcon image = new ImageIcon("principal.png");   //Trouver un autre iconimage eventuellement
        this.setIconImage(image.getImage());
        GridBagLayout layout = new GridBagLayout();
        
        this.add(principal);
        
        // ---------------------Menu--------------------------
        menuBar = new JMenuBar();
        //[1] Menu -> Affichage
        menuJeu = new JMenu("Jeu");
        nouvellePartie = new JMenuItem("Nouvelle Partie");
        couperSon  = new JMenuItem("Couper le son");
        classement = new JMenuItem("Classement");
        // Menu -> Affichage -> difficulté
        difficulte = new JMenu("Difficulté");
        facile = new JMenuItem("Facile");
        moyen = new JMenuItem("Moyen");
        difficile = new JMenuItem("Difficile");
        personnalise = new JMenuItem("Personnalisé");
        difficulte.add(facile);
        difficulte.add(moyen);
        difficulte.add(difficile);
        difficulte.add(personnalise);
        menuJeu.add(nouvellePartie);
        menuJeu.add(couperSon);
        menuJeu.add(classement);
        menuJeu.add(difficulte);
        //[2] Menu -> ?
        menuQuestionnement = new JMenu("?");
        aide = new JMenuItem("Aide");
        createurs = new JMenuItem("Créateurs");
        menuQuestionnement.add(aide);
        menuQuestionnement.add(createurs);
       
        menuBar.add(menuJeu);
        menuBar.add(menuQuestionnement);
        setJMenuBar(menuBar);
        // -----------------------------------------------
        
        
        
        

        
        setSize(550,700); //On donne une taille à notre fenêtre
        setResizable(false);
        setLocationRelativeTo(null);
        
        

        
        /*for (int i=0; i<10; i++){
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
        }*/
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
    
    public void play(){
        layoutFenetre= new BorderLayout();
        setLayout(layoutFenetre);
        nord   = new JPanel(new GridLayout(1,4));
        add(nord, BorderLayout.NORTH);
        
        
        
        centre = new JPanel(new GridLayout(1,2));
        add(centre, BorderLayout.CENTER);
        
        centre.add(new JeuTetris());
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
}
