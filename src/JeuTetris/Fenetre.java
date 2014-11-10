package JeuTetris;

import SoundsMusics.Sounds;
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
 * Manipule l'interface du menu principal, du menubar, du jeu, du highscore
 * @author Azfar Badaroudine et Donavan Martin
 */
public class Fenetre extends JFrame implements ActionListener{
    //Menu principal
    private JLabel principal;
    private JButton play;
    private JButton regle;
    private JButton high;
    private JButton quit;
    
    // Bar menu
    private JMenuBar menuBar;
    // --> Jeu 
    private JMenu menuJeu;
    private JMenuItem nouvellePartie;
    private JCheckBoxMenuItem couperSon;
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
    // -> ?
    private JMenu menuQuestionnement;
    private JMenuItem aide;
    private JMenuItem createurs;
    
    // Les pannels
    private GridBagLayout layout;
    private GridBagConstraints grid;
    private JPanel bestScore;
    private BPClassements HSPanel;
    private PJeuTetris jeu;
    private BPStatistiques statistique; 
    
    // Sounds
    private boolean mute;
    private Sounds splash;

    /**
     * Constructeur, renomme le titre de la fenetre et appelle la méthode d'initilisation du contenue
     */
    public Fenetre() {
        super("Tetris");
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BuildAccueil();
    }

    /**
    * Initalise et positionne les composantes de la page d'accueil et du menubar 
    */
    public void BuildAccueil() {
        setSize(new Dimension(800,700));  // Taille de la fenêtre
        setResizable(false);
        setLocationRelativeTo(null);

        // ---------------------Menu Bar--------------------------
        menuBar = new JMenuBar();
        //--> Jeu
        menuJeu        = new JMenu("Jeu");
        nouvellePartie = new JMenuItem("Nouvelle Partie");
        couperSon      = new JCheckBoxMenuItem("Couper le son");
        classement     = new JMenuItem("Classement");
        
        //--> Jeu --> Difficulté
        difficulte   = new JMenu("Difficulté");
        facile       = new JMenuItem("Facile");
        moyen        = new JMenuItem("Moyen");
        difficile    = new JMenuItem("Difficile");
        facile.setEnabled(false);
        moyen.setEnabled(false);
        difficile.setEnabled(false);
        
        //--> Jeu --> Difficulté --> Personnalisé
        personnalise = new JMenu("Personnalisé");
        personnalise.setEnabled(false);

        level    = new JMenuItem("1");
        level.setBorderPainted(false);
        level.setEnabled(false);
        level.setAlignmentX(JLabel.CENTER);
        sliderDifficulte = new JSlider(JSlider.HORIZONTAL, 1, 15, 1);
        sliderDifficulte.setEnabled(false);
        personnalise.add(level);
        personnalise.add(sliderDifficulte);
        
        //--> Affichage -> difficulté
        difficulte.add(facile);
        difficulte.add(moyen);
        difficulte.add(difficile);
        difficulte.add(personnalise);
        menuJeu.add(nouvellePartie);
        menuJeu.add(couperSon);
        menuJeu.add(classement);
        menuJeu.add(difficulte);
        
        //--> ?
        menuQuestionnement = new JMenu("?");
        aide = new JMenuItem("Aide");
        createurs = new JMenuItem("Créateurs");
        menuQuestionnement.add(aide);
        menuQuestionnement.add(createurs);

        menuBar.add(menuJeu);
        menuBar.add(menuQuestionnement);
        setJMenuBar(menuBar);
        
        // Ajout des listener du menubar
        nouvellePartie.addActionListener(this);
        couperSon     .addActionListener(this);
        classement    .addActionListener(this);
        facile        .addActionListener(this);
        moyen         .addActionListener(this);
        difficile     .addActionListener(this);
        sliderDifficulte.addChangeListener(new SliderListener());
        aide.addActionListener(this);
        createurs.addActionListener(this);
        
        //-------------Page D'acceuil---------
        int longueur = 230;
        int hauteur = 70;
        play = new JButton("Jouer");
        regle = new JButton("Règlement");
        high = new JButton("Classement");
        quit = new JButton("Quitter");
        setStyle (play,this.getWidth()/2 - longueur/2, (int)(0.42 * this.getHeight() - hauteur/2), 60 );
        setStyle (regle,this.getWidth()/2 - longueur/2, (int)(0.6 * this.getHeight() - hauteur/2), 40 );
        setStyle (high,this.getWidth()/2 - longueur/2, (int)(0.7 * this.getHeight() - hauteur/2), 40 );
        setStyle (quit,this.getWidth()/2 - longueur/2, (int)(0.8 * this.getHeight() - hauteur/2), 40 );
        
         // Image BackGround
        this.setIconImage(new ImageIcon("image\\principal.png").getImage());
        principal = new JLabel();
        principal.setSize(this.getWidth(), this.getHeight());
        principal.setIcon(new ImageIcon(imageFit(principal, "image\\principal.png")));
        this.add(principal);
        
        // Sons D'acceuil
        splash = new Sounds("splash");
    }
    
    /**
     * Modifie l'apparance d'un bouton selon les besoins
     * @param bouton bouton à modifier
     * @param x position en x sur le frame
     * @param y position en y sur le frame
     * @param size taille de la police du texte
     */
    public void setStyle (JButton bouton, int x , int y, int size ){
        int longueur = 230;
        int hauteur = 70;
        bouton.setSize(longueur, hauteur);
        bouton.setLocation(x, y);
        bouton.setOpaque(false);
        bouton.setContentAreaFilled(false);
        bouton.setBorderPainted(false);
        bouton.setFont(new Font("Times New Roman", Font.BOLD, size));
        bouton.setForeground(Color.YELLOW);
        this.add(bouton);
        bouton.addActionListener(this);
    }
    
    /**
     * Permet à une image d'être placée en background et être étirée selon la taille du frame
     * @param label label contenant l'image
     * @param path path de l'image dans l'ordinateur
     * @return retourne une image ajustée selon la taille du frame
     */
    public BufferedImage imageFit(JLabel label, String path){
        BufferedImage bi = null;
        try {
            bi=new BufferedImage(label.getWidth(),label.getHeight(),BufferedImage.TYPE_INT_RGB);
            Graphics2D g=bi.createGraphics();            
            Image img=ImageIO.read(new File(path));            
            g.drawImage(img, 0, 0, label.getWidth(),label.getHeight(), null);            
            g.dispose();            
        } catch (IOException ex) {}
        return bi;
    }
    
    /**
     * Ajoute une composante dans un GridBagLayout selon les paramètre requis
     * @param component composante à ajouter
     * @param layout layout dans le quel l'ajouter
     * @param constraints les contraintes du gridBagLayout
     * @param x position relative en x
     * @param y position relative en y
     * @param width longueur relative du composante
     * @param height hauteur relative du composante
     * @param weightx occupation relative de son espace en x
     * @param weighty occupation relative de son espace en y
     */
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

    /**
     * Initialise les panels de l'interface du jeu
     */
     public void initInterfaceJeu(){
        //HIGHSCORE
        bestScore = new BPBestScore();

        //STATISTIQUE 
        statistique = new BPStatistiques();
        
        // JEU
        jeu = new PJeuTetris(10,20,new Dimension((int) (this.getWidth()*0.6), (int) (this.getHeight()*0.925)));
        if (couperSon.isSelected()){
            mute = true;
            jeu.setMute(true);
        }
        jeu.start(2);
        enableDifficulte(true);   
    }
     
    /**
     * positionne les panels de l'interface du jeu
     */
    public void openInterfaceJeu(){
        visibleInterfaceJeu(true);
        visibleMenuPrincipal(false);
        sliderDifficulte.setEnabled(true);
        level.setEnabled(true);

        layout = new GridBagLayout();
        setLayout(layout);
        grid = new GridBagConstraints();
        grid.fill = GridBagConstraints.BOTH;
        grid.insets = new Insets(0,0,0,0);

        // Initialisation des pannels :
        add(jeu,        layout,grid,0,0,1,2,1.5,1.5);
        add(statistique,layout,grid,1,0,1,1,0.53,0.7);
        add(bestScore,  layout,grid,1,1,1,1,0.53,0.3);

    }

    /**
     * Méthode qui réagit selon les bouttons clickés
     * @param ae ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == play ){                       //Part le jeu
            initInterfaceJeu();
            openInterfaceJeu();
        }
        else if(source == nouvellePartie){          //Part le jeu
            if(jeu != null)
                jeu.nouvellePartie(10, 20);
            else{
                if (HSPanel != null)
                    HSPanel.setVisible(false);
                initInterfaceJeu();
                openInterfaceJeu();
            }      
        }
        else if(source == couperSon){               //Coupe le son
            try{
                if (mute == false){
                    jeu.setMute(true);
                    mute=true;
                }
                else{
                    jeu.setMute(false);
                    mute= false;
                }
            }catch(Exception e){
                
            }
        }
        else if(source == classement){              //Affiche la page des High Scores
            if (jeu != null){
                jeu.pause();
                visibleInterfaceJeu(false);
            }
            openClassements();
        }
        else if(source == facile){                  //Change la difficulté pour facile
            sliderDifficulte.setValue(1);
            statistique.setNiveau(1);
        }
        else if(source == moyen){                   //Change la difficulté pour moyen
           sliderDifficulte.setValue(5);
           statistique.setNiveau(5);
           
        }
        else if(source == difficile){               //Change la difficulté pour difficile
            sliderDifficulte.setValue(10);
            statistique.setNiveau(10);
        }
        else if (source == createurs){              //Ouvre le "À Propos De"
            if (jeu != null){
                jeu.pause();
                openCreateursInfo();
                jeu.resume();
            }else
                openCreateursInfo();
        }
        else if(source == high)                     //Affiche la page des High Scores
            openClassements();
        else if (source == regle)                   //Affiche les règles
            openRegle();
        else if (source == quit)                    //Ferme le jeu et la fenetre
            dispose();
        else if (source == aide){                   //Affiche les règles
            if (jeu != null){
                jeu.pause();
                openRegle();
                jeu.resume();
            }else
                openRegle();
        }
    }
    
    /**
     * Ouvre le "Créateurs"
     */
    public void openCreateursInfo(){
        JOptionPane.showMessageDialog(null, "Ce grand classique des années 80 a été redessiné par Azfar Badaroudine et Donavan Martin", "À propos de Tétris",JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Affiche la page des High Scores
     */
    public void openClassements() {
        visibleMenuPrincipal(false);
        BorderLayout layout_ = new BorderLayout();
        setLayout(layout_);
        HSPanel = new BPClassements (this.getWidth(),this.getHeight());
        add(HSPanel); 
    }
    
    /**
     * Affiche les règles
     */
    public void openRegle(){
        ImageIcon image = new ImageIcon("image\\Regle.jpg");
        UIManager.put("OptionPane.background", new ColorUIResource(255, 255, 255));
        UIManager.put("Panel.background", new ColorUIResource(255, 255, 255));
        JOptionPane.showMessageDialog(null, null, "Règlement", JOptionPane.PLAIN_MESSAGE, image);
    }

    /**
     * Rend la page d'accueil visible ou invisible au besoin
     * @param bool true = visible et false = invisible
     */
    public void visibleMenuPrincipal(boolean bool){
        play. setVisible(bool);
        regle.setVisible(bool);
        high. setVisible(bool);
        quit. setVisible(bool);
        principal.setVisible(bool);
    }
    
    /**
     * Rend la page du jeu visible ou invisible au besoin
     * @param bool true = visible et false = invisible
     */
    public void visibleInterfaceJeu(boolean bool){
        bestScore.setVisible(bool);
        statistique.setVisible(bool);
        jeu.setVisible(bool);
    }
    
    /**
     * change la difficulté du jeu selon un échel de 1 à 15
     */
    class SliderListener implements javax.swing.event.ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
           JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                jeu.setTimerDifficulte(source.getValue());
                level.setText(String.valueOf(source.getValue()));
                statistique.setNiveau(source.getValue());
            }    
        }
    }

    /**
     * Retour le panel statistique de l'interface du jeu
     * @return statistique 
     */
    public BPStatistiques getStatistique() {
        return statistique; 
    }

    /**
     * Retourne le panel jeu
     * @return jeu
     */
    public PJeuTetris getJeu() {
        return jeu;
    }
    
    /**
     * coupe le son au besoin
     * @param mute true = son coupé, false = son activé
     */
    public void mute(boolean mute){ 
        jeu.setMute(mute);
    }
    
    /**
     * Empèche la sélection de la difficulté avant le début de la partie
     * @param bool True == enabled False == Disabled
     */
    public void enableDifficulte(boolean bool){
        facile.      setEnabled(bool);
        moyen.       setEnabled(bool);
        difficile.   setEnabled(bool);
        personnalise.setEnabled(bool);
    }
}