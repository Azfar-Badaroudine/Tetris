package tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 *
 * @author Azfar
 */
public class Fenetre extends JFrame implements ActionListener{
    //Menu principal
    private JLabel principal;
    private JButton play;
    private JButton regle;
    private JButton high;
    private JButton quit;
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
    private GridBagLayout layout;
    private GridBagConstraints grid;
    private BorderLayout layoutFenetre;
    private JPanel nord; // Les pièces suivantes du jeu
    private JPanel centre;// Le jeu et les satistique

    // Contenue du panel nord
    private JLabel message_suivant;
    ///////////////private TShape next_block[3];

    // Contenue du panel centre
     private JeuTetris jeu;
    //////////private Statistique statistique;
    //---------------------------------------------------

    private JLabel block[][] = new JLabel[10][22]; // Vérification de l'optimisation --> plus tard
    private JPanel pan;
    
    //a deleter
    private JPanel temp1;


    public Fenetre() {
        super("Tetris");
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BuildAccueil();
        //play();// <--------------------------------------------------------- À DELETER
    }

    /**
    * Permet d'initalisé les composantes de la fenêtre
    */
    private void BuildAccueil() {
    //-------------PAGE D'ACCUEIL---------
        setSize(1550,700); //On donne une taille à notre fenêtre
        setResizable(false);
        setLocationRelativeTo(null);

        // ---------------------Menu--------------------------
        menuBar = new JMenuBar();
        //[1] Menu -> Affichage
        menuJeu = new JMenu("Jeu");
        nouvellePartie = new JMenuItem("Nouvelle Partie");
        couperSon = new JMenuItem("Couper le son");
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
        
        nouvellePartie.addActionListener(this);
        // -----------------------------------------------
        
        

        int longueur = 230;
        int hauteur = 70;
        
        play = new JButton("Play");
        play.setSize(longueur, hauteur);
        play.setLocation(this.getWidth()/2 - longueur/2, (int)(0.42 * this.getHeight() - hauteur/2));
        setStyle(play);
        play.setFont(new Font("Times New Roman", Font.BOLD, 60));
        this.add(play);
        play.addActionListener(this);
        
        regle = new JButton("Réglement");
        regle.addActionListener(this);
        setStyle(regle);
        regle.setSize(longueur, hauteur);
        regle.setLocation(this.getWidth()/2 - longueur/2, (int)(0.6 * this.getHeight() - hauteur/2));
        this.add(regle);
        
        high = new JButton("High Score");
        high.addActionListener(this);
        setStyle(high);
        high.setSize(longueur, hauteur);
        high.setLocation(this.getWidth()/2 - longueur/2, (int)(0.7 * this.getHeight() - hauteur/2));
        this.add(high);
        
        quit = new JButton("Quitter");
        quit.addActionListener(this);
        setStyle(quit);
        quit.setSize(longueur, hauteur);
        quit.setLocation(this.getWidth()/2 - longueur/2, (int)(0.8 * this.getHeight() - hauteur/2));
        this.add(quit);
        
        principal = new JLabel();
        //principal.setIcon(new ImageIcon("principal.png"));  //il faut scale l'image pour fité dans la fenetre
        this.setIconImage(new ImageIcon("principal.png").getImage());//Trouver un autre iconimage eventuellement
        principal.setSize(this.getWidth(), this.getHeight());
        principal.setIcon(new ImageIcon(imageFit(principal)));
        this.add(principal);
        
        
        
        
        
    }
    
    public BufferedImage imageFit(JLabel label){
        BufferedImage bi = null;
        try {
            bi=new BufferedImage(label.getWidth(),label.getHeight(),BufferedImage.TYPE_INT_RGB);
            
            Graphics2D g=bi.createGraphics();
            
            Image img=ImageIO.read(new File("principal2.png"));
            
            g.drawImage(img, 0, 0, label.getWidth(),label.getHeight(), null);
            
            g.dispose();
            
            return bi;
        } catch (IOException ex) {
            
        }
        return bi;
    }
    
    public void add(JComponent component, GridBagLayout layout, GridBagConstraints constraints, int x, int y, int width, int height, double weightx, double weighty){
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        layout.setConstraints(component, constraints);
        add(component);
    }
    
    public void addJeuTetris(Dimension dim){
        // Initialise le jeu
        jeu = new JeuTetris(10,20,dim);
        //Démarre le jeu avec une difficulté
        jeu.start(2);
        // Pause le jeu
        //jeu.stop();
        // Resume 
        //jeu.resume();
        add(jeu);
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
        /*layoutFenetre= new BorderLayout();
        setLayout(layoutFenetre);
        nord = new JPanel(new GridLayout(1,4));
        add(nord, BorderLayout.NORTH);*/

        play.setVisible(false);
        regle.setVisible(false);
        high.setVisible(false);
        quit.setVisible(false);
        principal.setVisible(false);
        
        layout = new GridBagLayout();
        setLayout(layout);
        
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.BOTH;
        grid.insets = new Insets(0,0,0,0);
        
        temp1 = new JPanel();
        temp1.setBackground(Color.BLACK);
        //temp1.setPreferredSize(new Dimension(250,300));
        grid.anchor = GridBagConstraints.NORTH;
        add(temp1,layout,grid,0,0,0,1,1,0.2);
        
        System.out.print(this.getHeight());
        Dimension dim = new Dimension(this.getWidth()/2, (int) (this.getHeight()*0.7)); //<--------Devrait etre 0.8
        //Dimension dim = new Dimension(250,300);
        addJeuTetris(dim);
        //grid.anchor = GridBagConstraints.SOUTHWEST;
        add(jeu,layout,grid,0,0,1,1,1,0.8);
        jeu.setPreferredSize(dim);
        
        JPanel temp2 = new JPanel();
        temp2.setBackground(Color.BLUE);
        //temp2.setPreferredSize(new Dimension(250, (int) (this.getHeight()*0.7)));
        grid.anchor = GridBagConstraints.SOUTHEAST;
        add(temp2,layout,grid,1,0,0,1,1,0.8);
        
        /*JPanel temp3 = new JPanel();
        temp3.setBackground(Color.YELLOW);
        temp3.setPreferredSize(new Dimension(250,300));
        add(temp3,layout,grid,1,2,1,4,1,0.3);*/
         

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        
        if (source == play || source == nouvellePartie)
            play();
        else if(source == high)
            openHighScore();
        else if (source == regle)
            openRegle();
        else if (source == quit)
            dispose();
    }

    public void openHighScore() {
        
    }
    
    public void openRegle(){
        
    }
    
    public void setStyle(JButton bouton){
        bouton.setOpaque(false);
        bouton.setContentAreaFilled(false);
        bouton.setBorderPainted(false);
        bouton.setFont(new Font("Times New Roman", Font.BOLD, 40));
        bouton.setForeground(Color.YELLOW);
    }
    
}