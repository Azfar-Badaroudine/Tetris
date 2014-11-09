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
    private JPanel topPanel;
    private JPanel bestScore;
    private BPClassements HSPanel;
    
    // Contenue du panel centre
    private PJeuTetris jeu;
    private BPStatistiques statistique; 
    
    // Dimension frame
    private Dimension dimension;
    
    // Sounds
    private boolean mute;
    private Sounds splash;

    public Fenetre() {
        super("Tetris");
        setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BuildAccueil();
        splash = new Sounds("splash");
    }

    /**
    * Initalise les composantes de la fenêtre
    */
    public void BuildAccueil() {
        dimension = new Dimension(800,700);
        setSize(dimension); //On donne une taille à notre fenêtre
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
        play = new JButton("Play");
        regle = new JButton("Réglement");
        high = new JButton("High Score");
        quit = new JButton("Quitter");
        addButton (play,this.getWidth()/2 - longueur/2, (int)(0.42 * this.getHeight() - hauteur/2), 60 );
        addButton (regle,this.getWidth()/2 - longueur/2, (int)(0.6 * this.getHeight() - hauteur/2), 40 );
        addButton (high,this.getWidth()/2 - longueur/2, (int)(0.7 * this.getHeight() - hauteur/2), 40 );
        addButton (quit,this.getWidth()/2 - longueur/2, (int)(0.8 * this.getHeight() - hauteur/2), 40 );
         // Image BackGround
        principal = new JLabel();
        this.setIconImage(new ImageIcon("principal.png").getImage());
        principal.setSize(this.getWidth(), this.getHeight());
        principal.setIcon(new ImageIcon(imageFit(principal, "principal2.png")));
        this.add(principal);
        // Sons D'acceuil
    }
    
    public void addButton (JButton button, int x , int y, int style ){
        int longueur = 230;
        int hauteur = 70;
        button.setSize(longueur, hauteur);
        button.setLocation(x, y);
        setStyle(button,style);
        this.add(button);
        button.addActionListener(this);
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

     public void initInterfaceJeu(){
        // Initialisation des pannels :
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
        enableDifficulte();   
    }
     
    public void setTime(int temps){
        statistique.setChrono(temps);
    }
     
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source == play ){
            initInterfaceJeu();
            openInterfaceJeu();
        }
        else if(source == nouvellePartie){
            if(jeu != null)
                jeu.nouvellePartie(10, 20);
            else{
                if (HSPanel != null)
                    HSPanel.setVisible(false);
                initInterfaceJeu();
                openInterfaceJeu();
            }      
        }
        else if(source == couperSon){
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
        else if(source == classement){
            if (jeu != null){
                jeu.pause();
                visibleInterfaceJeu(false);
            }
            openClassements();
        }
        else if(source == facile){
            sliderDifficulte.setValue(1);
            statistique.setNiveau(1);
        }
        else if(source == moyen){
           sliderDifficulte.setValue(5);
           statistique.setNiveau(5);
        }
        else if(source == difficile){
            sliderDifficulte.setValue(10);
            statistique.setNiveau(10);
        }
        else if(source == personnalise){
            jeu.nouvellePartie(10, 20);
        }else if (source == createurs){
            if (jeu != null){
                jeu.pause();
                openCreateursInfo();
                jeu.resume();
            }else
                openCreateursInfo();
        }
        else if(source == high)
            openClassements();
        else if (source == regle)
            openRegle();
        else if (source == quit)
            dispose();
        else if (source == aide){
            if (jeu != null){
                jeu.pause();
                openRegle();
                jeu.resume();
            }else
                openRegle();
        }
    }
    
    public void openCreateursInfo(){
        JOptionPane.showMessageDialog(null, "Ce grand classique des années 80 a été redessiné par Azfar Badaroudine et Donavan Martin", "À propos de Tétris",JOptionPane.INFORMATION_MESSAGE);
    }

    public void openClassements() {
        visibleMenuPrincipal(false);
        BorderLayout layout_ = new BorderLayout();
        setLayout(layout_);
        HSPanel = new BPClassements (this.getWidth(),this.getHeight());
        add(HSPanel); 
    }
    
    public void openRegle(){
        ImageIcon image = new ImageIcon("Regle.jpg");
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

    public void visibleMenuPrincipal(boolean bool){
        play. setVisible(bool);
        regle.setVisible(bool);
        high. setVisible(bool);
        quit. setVisible(bool);
        principal.setVisible(bool);
    }
    
    public void visibleInterfaceJeu(boolean bool){
        bestScore.setVisible(bool);
        statistique.setVisible(bool);
        jeu.setVisible(bool);
    }
    
    
    class SliderListener implements javax.swing.event.ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e) {
           JSlider source = (JSlider)e.getSource();
            if (!source.getValueIsAdjusting()) {
                jeu.setTimerDifficulte((int)source.getValue());
                level.setText(String.valueOf((int)source.getValue()));
                statistique.setNiveau((int)source.getValue());
            }    
        }
    }

    public BPStatistiques getStatistique() {
        return statistique; 
    }

    public PJeuTetris getJeu() {
        return jeu;
    }
    public void mute(boolean mute){ 
        jeu.setMute(mute);
    }
    
    public void enableDifficulte(){
        facile.setEnabled(true);
        moyen.setEnabled(true);
        difficile.setEnabled(true);
        personnalise.setEnabled(true);
    }
}