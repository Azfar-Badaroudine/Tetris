package tetris;



import SoundsMusics.Sounds;
import SoundsMusics.ThemeMusic;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.ColorUIResource;



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

    // --> Jeu 
    private JMenu menuJeu;
    private JMenuItem nouvellePartie;
    private JMenuItem couperSon;
    private JMenuItem classement;
    // --> Jeu  --> difficulté
    private JMenu difficulte;
    private JMenuItem facile;
    private JMenuItem moyen;
    private JMenuItem difficile;
    
    // --> Jeu  --> difficulté -- > personnalisé
    private JMenu personnalise;
    private JMenuItem level;
    private JSlider sliderDifficulte;

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
     private PJeuTetris jeu;
    private PStatistiques statistique;


    private JLabel block[][] = new JLabel[10][22]; // Vérification de l'optimisation --> plus tard
    private JPanel pan;
    
    //<--------------------------------INCOMPLET<----------------------------------------------------------------------
    private JPanel topPanel;
    private JPanel HS;
    
    // Dimension frame
    private Dimension dimension;
    
    // Music Theme
    private ThemeMusic themeMusic;
    
    // Sounds 
    private Sounds splash;


    public Fenetre() {
        super("Tetris");
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BuildAccueil();
        //openHighScore();// <--------------------------------------------------------- À DELETER
    }

    /**
    * Permet d'initalisé les composantes de la fenêtre
    */
    private void BuildAccueil() {
    //-------------PAGE D'ACCUEIL---------
        
        dimension = new Dimension(800,700);
        setSize(dimension); //On donne une taille à notre fenêtre
        setResizable(false);
        setLocationRelativeTo(null);

        // ---------------------Menu--------------------------
        menuBar = new JMenuBar();
        //[1] --> Jeu
        menuJeu        = new JMenu("Jeu");
        nouvellePartie = new JMenuItem("Nouvelle Partie");
        couperSon      = new JMenuItem("Couper le son");
        classement     = new JMenuItem("Classement");
        
        //[1] --> Jeu --> Difficulté
        difficulte   = new JMenu("Difficulté");
        facile       = new JMenuItem("Facile");
        moyen        = new JMenuItem("Moyen");
        difficile    = new JMenuItem("Difficile");
        
        //[1] --> Jeu --> Difficulté --> Personnalisé
        personnalise = new JMenu("Personnalisé");

        level    = new JMenuItem("1");
        level.setBorderPainted(false);
        level.setEnabled(false);
        level.setAlignmentX(JLabel.CENTER);
        sliderDifficulte = new JSlider(JSlider.HORIZONTAL, 1, 15, 1);
        sliderDifficulte.setEnabled(false);
        personnalise.add(level);
        personnalise.add(sliderDifficulte);
        
        
        // Menu -> Affichage -> difficulté
        difficulte.add(facile);
        difficulte.add(moyen);
        difficulte.add(difficile);
        difficulte.add(personnalise);
        menuJeu.add(nouvellePartie);
        menuJeu.add(couperSon);
        menuJeu.add(classement);
        menuJeu.add(difficulte);
        //// Menu -> Affichage -> difficulté
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
        couperSon     .addActionListener(this);
        classement    .addActionListener(this);
        facile        .addActionListener(this);
        moyen         .addActionListener(this);
        difficile     .addActionListener(this);
        sliderDifficulte.addChangeListener(new SliderListener());
        
        
        
        // -----------------------------------------------
        int longueur = 230;
        int hauteur = 70;
        
        play = new JButton("Play");
        play.setSize(longueur, hauteur);
        play.setLocation(this.getWidth()/2 - longueur/2, (int)(0.42 * this.getHeight() - hauteur/2));
        setStyle(play,60);
        this.add(play);
        play.addActionListener(this);
        
        regle = new JButton("Réglement");
        regle.addActionListener(this);
        setStyle(regle,40);
        regle.setSize(longueur, hauteur);
        regle.setLocation(this.getWidth()/2 - longueur/2, (int)(0.6 * this.getHeight() - hauteur/2));
        this.add(regle);
        
        high = new JButton("High Score");
        high.addActionListener(this);
        setStyle(high,40);
        high.setSize(longueur, hauteur);
        high.setLocation(this.getWidth()/2 - longueur/2, (int)(0.7 * this.getHeight() - hauteur/2));
        this.add(high);
        
        quit = new JButton("Quitter");
        quit.addActionListener(this);
        setStyle(quit,40);
        quit.setSize(longueur, hauteur);
        quit.setLocation(this.getWidth()/2 - longueur/2, (int)(0.8 * this.getHeight() - hauteur/2));
        this.add(quit);
        
        principal = new JLabel();
        //principal.setIcon(new ImageIcon("principal.png"));  //il faut scale l'image pour fité dans la fenetre
        this.setIconImage(new ImageIcon("principal.png").getImage());//Trouver un autre iconimage eventuellement
        principal.setSize(this.getWidth(), this.getHeight());
        principal.setIcon(new ImageIcon(imageFit(principal, "principal2.png")));
        this.add(principal);
   
        splash = new Sounds("splash");
       }
    
    public void menuPrincipal(){
        
    }
    
    public BufferedImage imageFit(JLabel label, String path){
        BufferedImage bi = null;
        try {
            bi=new BufferedImage(label.getWidth(),label.getHeight(),BufferedImage.TYPE_INT_RGB);
            
            Graphics2D g=bi.createGraphics();
            
            Image img=ImageIO.read(new File(path));
            
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
    
    public PJeuTetris addJeuTetris(Dimension dim){
        // Initialise le jeu
        jeu = new PJeuTetris(10,20,dim);
        //Démarre le jeu avec une difficulté
        jeu.start(2);
        // Pause le jeu
        //jeu.stop();
        // Resume 
        //jeu.resume();
        return jeu;
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

    public void interfaceJeu(){

    enableMenuPrincipal(false);
    sliderDifficulte.setEnabled(true);
    level.setEnabled(true);
  
    layout = new GridBagLayout();
    setLayout(layout);
    grid = new GridBagConstraints();
    grid.fill = GridBagConstraints.BOTH;
    grid.insets = new Insets(0,0,0,0);
        
    // Initialisation des pannels :
        
    //TOP PANEL INIT <---------------------------------
    topPanel = new JPanel();
    topPanel.setBackground(Color.BLACK);
    topPanel.setPreferredSize(new Dimension(dimension.width,  dimension.height));
    grid.anchor = GridBagConstraints.NORTH;
    add(topPanel,layout,grid,0,0,2,1,0.5,0.2);
    topPanel.setSize(this.getWidth(), (int) (this.getHeight()*0.2));
        

        
    //HIGHSCORE PANEL INIT <---------------------------------    
    HS = new JPanel();
    HS.setBackground(Color.YELLOW);
    HS.setSize(this.getWidth()/2, (int) (this.getHeight()*0.2));
    add(HS,layout,grid,1,2,1,1,0.5,0.2);
        
 

   
   
    // JEU
    Dimension dim = new Dimension(this.getWidth()/2, (int) (this.getHeight()*0.73)); //<--------Devrait etre 0.8
    grid.anchor = GridBagConstraints.SOUTHWEST;
    add(addJeuTetris(dim),layout,grid,0,1,1,2,0.7,0.7);
        
    //STATISTIQUE 
    statistique = new PStatistiques(new Dimension(this.getWidth()/2, this.getHeight())); 
    add(statistique,layout,grid,1,1,1,1,0.5,0.6);
       
    // Re/Commence le sons du jeu
    themeMusic = new ThemeMusic();    
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == play )
            interfaceJeu();
        else if(source == nouvellePartie){
            jeu.nouvellePartie(10, 20);
        }
        else if(source == facile){
            sliderDifficulte.setValue(1);
        }
        else if(source == moyen){
           sliderDifficulte.setValue(5);
        }
        else if(source == difficile){
            sliderDifficulte.setValue(10);
        }
        else if(source == personnalise){
            jeu.nouvellePartie(10, 20);
        }else if(source == high)
            openHighScore();
        else if (source == regle)
            openRegle();
        else if (source == quit)
            dispose();
    }

    public void openHighScore() {
        enableMenuPrincipal(false);

      
        Panel_test  HSPanel = new Panel_test(this.getWidth(),this.getHeight());
        add(HSPanel); 

    }
    
    public void openRegle(){
        ImageIcon image = new ImageIcon("Regle.jpg");
        new UIManager();
        UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 255));
        UIManager.put("Panel.background", new ColorUIResource(255, 255, 255));
        JOptionPane.showMessageDialog(null, null, "Règlement", JOptionPane.PLAIN_MESSAGE, image);
    }
    
    public void setStyle(JButton bouton, int size){
        bouton.setOpaque(false);
        bouton.setContentAreaFilled(false);
        bouton.setBorderPainted(false);
        bouton.setFont(new Font("Times New Roman", Font.BOLD, size));
        bouton.setForeground(Color.YELLOW);
    }

    public void enableMenuPrincipal(boolean bool){
        if (bool == true){
            play. setVisible(true);
            regle.setVisible(true);
            high. setVisible(true);
            quit. setVisible(true);
            principal.setVisible(true);
        }else {
            play. setVisible(false);
            regle.setVisible(false);
            high. setVisible(false);
            quit. setVisible(false);
            principal.setVisible(false);
        }
    }
    
    
    class SliderListener implements javax.swing.event.ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
           JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                jeu.setTimerDifficulte((int)source.getValue());
                level.setText(String.valueOf((int)source.getValue()));
            }    
        }
    }

    public PStatistiques getStatistique() {
        return statistique; 
    }
}