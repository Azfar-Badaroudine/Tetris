package tetris;

import SoundsMusics.Sounds;
import SoundsMusics.ThemeMusic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Classe PJeuTetris
 * @author Azfar Badaroudine et Donavan Martin
 */
public class PJeuTetris extends JPanel  implements ActionListener{  
    
    // Paramètres de la fenetre 
    private CoordonneeJeu coordonneJeu;         // Coordonnee du jeu
    private Dimension dimension;                // Dimension du jeu

    // Paramètres jeu
    private TimerLoop timer;                      
    private boolean vivant;                     // True == Joueur joue False == Fin de la partie
    private int nbRangeeCompleted;              // Nombre de rangée completé
    private int niveau;                         // Niveau de difficulté

    // Boucle du jeu
    private ArrayList<Tetrominoes> tetrominoes; // List des Tetrominoe du jeu
    private int difficultee;                    // Difficulté du jeu
    
    // Graphics
    private Graphics bufferGraphics;            // Buffer évite le scintillement
    private Image offscreen;                    // Image du buffer
    
    // Controls
    private KeyboardFocusManager manager;
    private Controls controls;
    
    // Ecoulement du temps
    private int temps;
    private long startTime;
    private long stopTime;

    // Score
    private int score;
    
    // Sounds
    private ThemeMusic themeMusic;
    private Sounds drop;
    
    private boolean mute; 
    private int cleared;
    private Sounds clearSingle;
    private Sounds clearDouble;
    private Sounds clearTriple;
    private Sounds clearTetris;
    private Sounds lineCompleted;
    
    /**
     * Constructeur du jeu Tetris par défaut
     * @param colonne nombre de colonne du jeu
     * @param rangee Nombre de rangee du jeu
     * @param dimension Dimension du JPannel
     */
    public PJeuTetris(int colonne, int rangee, Dimension dimension) {
        // Dimension du jeu
        coordonneJeu = new CoordonneeJeu(colonne,rangee);
        this.dimension = dimension;
        setSize(dimension);
  
        // Initialise la liste des tetrominoes
        tetrominoes = new ArrayList<>();
        
        // Pour les événements du clavier
        controls = new Controls(this);
        manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(controls);
        themeMusic = new ThemeMusic(); 
    }
    
    /**
     * Commencement de la partie
     * @param difficultee Difficulté du jeu [1 à 10]
     */
    public void start(int difficultee){
        newBlock();
        niveau = difficultee;
        timer = new TimerLoop(1000/difficultee, this);
        timer.start();
        vivant = true;
        nbRangeeCompleted=0;
        startTime = System.currentTimeMillis();
    }
   
    /**
     * Fin de la partie
     */
    public void stop(){
        stopTime = System.currentTimeMillis();
        timer.stop();
        vivant = false;
    }
    
    /**
     * Fait une pause de la partie 
     */
    public void pause(){
        stopTime = System.currentTimeMillis();
        timer.stop();
    }
    
    /**
     * Resume la partie
     */
    public void resume(){
        startTime = startTime + System.currentTimeMillis() - stopTime;
        timer.start();
    }
    
    /**
     * Construit un nouveau Tetrominoe aléatoirement
     */
    public void newBlock(){
        Random random = new Random();
        
        // Forme aléatoire
        int forme = abs(random.nextInt() % 7)+1;
        
        // Couleur pastelle aléatoire
        float hue = random.nextFloat(); // teinte
        float saturation = 0.9f;        //1.0 brilliant, 0.0 terne
        float luminance = 1.0f;         //1.0 luminant , 0.0 sombre
        Color couleur = Color.getHSBColor(hue, saturation, luminance);
        
        // Ajout du Tetrominoe à l'ArrayList<> 
        tetrominoes.add(new Tetrominoes(forme, couleur, coordonneJeu.getNombreColonne(), coordonneJeu.getNombreRangee()));
        repaint();
    }
    
    /**
     * Est-ce que le tetrominoe peut descendre?
     * @return False == non True == Oui
     */
    public boolean canFall(){
        // Parcours les coordonnées du jeu 
        for(int x=0; x <= coordonneJeu.getNombreColonne()-1; x++)
            for(int y=0; y <= coordonneJeu.getNombreRangee()-1; y++)
                // Si un Tetrominoe est présent vérifie la position du desous
                if(!tetrominoes.get(tetrominoes.size()-1).IsEmpty(x, y))
                    try{
                        if(!coordonneJeu.IsEmpty(x, y+1) && tetrominoes.get(tetrominoes.size()-1).getEmplacement().IsEmpty(x, y+1) ){
                            updateFallingEndBlocks();
                            return false;
                        }
                    }catch(Exception e){
                        updateFallingEndBlocks();
                        return false;
                    }
        return true;            
    }
    
    /**
     * Mise à jour des blocks qui ne peuvent plus descendre
     */
    public void updateFallingEndBlocks(){ 
        for(int x=0; x < coordonneJeu.getNombreColonne(); x++)
            for(int y=0; y < coordonneJeu.getNombreRangee(); y++)
                if(!tetrominoes.get(tetrominoes.size()-1).IsEmpty(x, y))
                    coordonneJeu.setCoordonee(x, y, true);
        // Vérifie si une ligne est compléter
        for(int y=1; y < coordonneJeu.getNombreRangee(); y++){
            if(verifyCompletedRow(y))
                cleared++;
        }
        if (mute == false)
        switch (cleared ){
            case 0 : clearSingle = new Sounds("Drop"); 
                     addScore(5*niveau);
                     break;
            case 1 : clearSingle = new Sounds("ClearSingle");
                     addScore(100*niveau);
                     break;
            case 2 : clearSingle = new Sounds("ClearDouble");
                     addScore(200*niveau);
                     break;
            case 3 : clearSingle = new Sounds("ClearTriple");
                     addScore(500*niveau);
                     break;
            case 4 : clearSingle = new Sounds("ClearTetris");
                     addScore(1500*niveau);
                     break;
            default : break;   
        }
        cleared=-1;
    }
    /**
     * Paint les blocks qui ne peuvent plus descendre
     * @param g  Buffer de l'image du JPannel
     */
    public void paintEndFallingBlocks(Graphics g){       
        Graphics2D g2d = (Graphics2D) g;
        if(tetrominoes.size()>1)
            for(int index =0 ; index<tetrominoes.size()-1;index++)
                for(int x=0; x < coordonneJeu.getNombreColonne(); x++)
                for(int y=0; y < coordonneJeu.getNombreRangee(); y++)
                    if(!tetrominoes.get(index).IsEmpty(x, y)){
                        paintBlock((dimension.getWidth()/coordonneJeu.getNombreColonne())*x,
                                    (dimension.getHeight()/coordonneJeu.getNombreRangee())*y,
                                     tetrominoes.get(index).getCouleur(), g2d);
                    }
    }
    
    /**
     * Fonction qui paint le jeu
     * @param g Graphics du JPannePJeuTetrisris)
     */   
    @Override
    public void paintComponent(Graphics g) {
        
        // Set le buffer de l'Affichage
        super.paintComponent(g);
        offscreen = this.createImage(dimension.width,dimension.height);
        bufferGraphics = offscreen.getGraphics();
        
        // Update le buffer 
        updateBlockPaint(bufferGraphics);
        paintGrille(bufferGraphics);
        
        // Paint l'image du buffer dans le jeu
        g.drawImage(offscreen,0,0,this); 
    }
    
    /**
     * Mise à jour du Buffer image selon les Blocks présent
     * @param g  Buffer de l'image du JPannel
     */
    public void updateBlockPaint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        // Parcous la table des coordonnées du jeu
        for(int x=0; x < coordonneJeu.getNombreColonne(); x++)
            for(int y=0; y < coordonneJeu.getNombreRangee(); y++)
                // Si un Tetrominoe est présent on le paint
                if(!tetrominoes.get(tetrominoes.size()-1).IsEmpty(x, y)){
                    paintBlock((dimension.getWidth()/coordonneJeu.getNombreColonne())*x,
                               (dimension.getHeight()/coordonneJeu.getNombreRangee())*y,
                                tetrominoes.get(tetrominoes.size()-1).getCouleur(), g2d);
        }
        paintEndFallingBlocks(g);
    }
    
    /**
     * Vérification d'une rangée compléter
     * @param rangee rangee a vérifier;
     * @return True == Ligne completer False == Ligne non-compléter
     */
    public boolean verifyCompletedRow(int rangee){
        // Vérifie les rangées
        for(int x=0; x < coordonneJeu.getNombreColonne(); x++)
            if(!coordonneJeu.IsEmpty(x, rangee)){}
            else
                return false;
        
        nbRangeeCompleted++;
        Fenetre topFrame = (Fenetre) SwingUtilities.getWindowAncestor(this);
        topFrame.getStatistique().setRangeeComplete(nbRangeeCompleted);
        // [1] Si une rangée est compléter on l'efface
        for(int x=0; x<coordonneJeu.getNombreColonne() ; x++)
            coordonneJeu.setCoordonee(x, rangee, false);
        
        // [2] Et descente des rangée précédante
        for(int y=rangee; y>0 ; y--)
            for(int x=0; x<coordonneJeu.getNombreColonne() ; x++)
                if(!coordonneJeu.IsEmpty(x, y)){
                    coordonneJeu.setCoordonee(x, y+1, true);
                    coordonneJeu.setCoordonee(x, y, false);
                }
        // [3] Coordonnée du Tetrominoe aussi
        for(Tetrominoes tetrominoe : tetrominoes)
            tetrominoe.completedRow(rangee);
        System.out.println("TABLE TETROMINOE");
        tetrominoes.get(tetrominoes.size()-1).getEmplacement().afficheTable();
        System.out.println("TABLE JEU");
        coordonneJeu.afficheTable();
        return true;               
    }
    
    /**
     * Paint le grillage selon la dimension en les coordonnées du jeu;
     * @param g  Buffer de l'image du JPannel
     */
    private void paintGrille(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        // Paint le grillage selon  la dimension
        g2d.setColor(Color.black);
        int separattion; 
        // Lignes Horizontale 
        for(separattion=0 ; coordonneJeu.getNombreColonne()> separattion; separattion++)
            g2d.draw(new Line2D.Double((dimension.getWidth()/coordonneJeu.getNombreColonne())*separattion,0,
                                        (dimension.getWidth()/coordonneJeu.getNombreColonne())*separattion, dimension.getHeight()));
        // Lignes Verticales
        for(separattion=0 ; coordonneJeu.getNombreRangee()> separattion; separattion++)
            g2d.draw(new Line2D.Double(0,(dimension.getHeight()/coordonneJeu.getNombreRangee())*separattion,
                                          dimension.getWidth(),(dimension.getHeight()/coordonneJeu.getNombreRangee())*separattion));
    }
    
    /**
     * Paint un Block
     * @param colonne Coordnonné en x du Block
     * @param rangee Coordnonné en y du Block
     * @param color Couleur du Block
     * @param g Buffer de l'image du JPannel
     */
    public void paintBlock(Double colonne, Double rangee, Color color, Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        Rectangle2D rect = new Rectangle2D.Double(colonne ,
                                                  rangee  ,
                                                  dimension.getWidth()/coordonneJeu.getNombreColonne(),
                                                  dimension.getHeight()/coordonneJeu.getNombreRangee());
        g2d.setPaint(color);
        g2d.fill(rect);
        g2d.draw(rect);
    }   
    
    
    /**
     * Getteur de la difficultée 
     * @return timer La difficulté du jeu
     */
    public TimerLoop getTimerDifficulte() {
        return timer;
    }
    
    /**
     * Setteur de la difficultée 
     * @param timerDifficulte La difficulté du jeu
     * @param niveau Le niveau de difficulté
     */
    public void setTimerDifficulte(int difficulte) { 
        this.timer.setDifficulte(difficulte);
        niveau = difficulte;
    }
    
    /**
     * Getteur de la dimension
     * @return dimension la dimension du pannel
     */
    public Dimension getDimension() {
        return dimension;
    }
    
    /**
     * Getteur de la difficultée
     * @return difficultee La dificultee du jeu
     */
    public int getDifficultee() {
        return difficultee;
    }
    /**
     * Getteur du nombre de rangée complétés
     * @return nbRangeeCompleted nombre de rangée complétés
     */
    public int getNbRangeeCompleted() {
        return nbRangeeCompleted;
    }
    

    /**
     * Est-ce que le mouvement (controls du joueur) est possible 
     * @return False == Non True == Oui
     */
    public boolean canMove(){
        int rangee = -1;
        for(boolean bool[] : tetrominoes.get(tetrominoes.size()-1).getEmplacement().getCoordoneeJeu()){
            rangee++;
            for(int x=0; x< coordonneJeu.getNombreColonne(); x++)
                if(true==bool[x]&& bool[x]== !coordonneJeu.IsEmpty(x, rangee)){
                    return false;
                }
        }
        return true;
    }
    
    /**
     * Avancement du jeu dans le temps
     * @param timer Appel de la fonction par le timer
     */
    @Override
    public void actionPerformed(ActionEvent timer) {
        if (vivant == true){
            actualiseStatistique();
            // Si le tetrominoe ne peut pas descendre
            if (!canFall()|| tetrominoes.get(tetrominoes.size()-1).getEmplacement().isAllEmpty()){
                cleared=0; // Pour le son " Drop "
                newBlock();
                repaint();
                //tetrominoes.get(tetrominoes.size()-1).dropTetrominoe();
                if(!canFall()){
                    vivant = false;
                    
                    this.timer.stop();
                    manager.removeKeyEventDispatcher(controls);
                    String nomJoueur = JOptionPane.showInputDialog(null, "Fin de la partie \nNombre de rangée compléter : " + nbRangeeCompleted +"\nEntrez votre nom pour le classement.");
                    temps = (int)((System.currentTimeMillis() - startTime)/1000) ;

                    if (nomJoueur == null)
                        nomJoueur = ("No Name");
                    addScore(nomJoueur);
                    manager.addKeyEventDispatcher(controls);

                    Fenetre topFrame = (Fenetre) SwingUtilities.getWindowAncestor(this);
                    topFrame.getStatistique().setRangeeComplete(nbRangeeCompleted);
                    topFrame.enableDifficulte(false);
                }
            }
            // Si le tetrominoe peut descendre
            else if(canFall()){
                System.out.println("Action Timer perfromed");
                tetrominoes.get(tetrominoes.size()-1).dropTetrominoe(); 
                repaint();
            }
        }
    }   
 
    public void nouvellePartie(int colonne, int rangee){
        vivant = true;
        Fenetre topFrame = (Fenetre) SwingUtilities.getWindowAncestor(this);
        topFrame.enableDifficulte(true);
        
        startTime = System.currentTimeMillis();
        coordonneJeu = new CoordonneeJeu(colonne,rangee);
        tetrominoes = new ArrayList<>();
        newBlock();
        nbRangeeCompleted=0;
        timer.restart();
        
    }
    /**
     * Getteur de la liste des tetrominoes
     * @return tetrominoes la lsite des tetrominoes
     */
    public ArrayList<Tetrominoes> getTetrominoes() {
        return tetrominoes;
    }
    /**
     * Getteur du timer 
     * @return timer le timer du jeu
     */
    public TimerLoop getTimer() {
        return timer;
    }
     /**
     * Setteur mute
     * @param mute 
     */
    public void setMute(boolean mute) {
        themeMusic.mute(mute);
        controls.setMute(mute);
        this.mute = mute;
    }
    public void addScore(String name){
        Fenetre topFrame = (Fenetre) SwingUtilities.getWindowAncestor(this);
        
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src\\Scores\\ScoreListe.txt", true)))) {
            out.println(name + " "+ 
                        String.valueOf(temps) +" "+
                        String.valueOf(nbRangeeCompleted + " " +
                        String.valueOf(niveau)+ " " +
                        topFrame.getStatistique().getScoreActuel().getText()));
        }catch (IOException e) {
  
            Logger.getLogger(PJeuTetris.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void addScore(int score){
        Fenetre topFrame = (Fenetre) SwingUtilities.getWindowAncestor(this);
        topFrame.getStatistique().addScoreActuel(score);
    }
    public void actualiseStatistique(){
        Fenetre topFrame = (Fenetre) SwingUtilities.getWindowAncestor(this);
        topFrame.getStatistique().setChrono((int) (System.currentTimeMillis()-startTime)/1000);
        topFrame.getStatistique().addScoreActuel(1*niveau);
    }
}
    
    
