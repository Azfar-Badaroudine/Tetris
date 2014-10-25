package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Classe JeuTetris
 * @author Azfar et Donaven
 */
public class JeuTetris extends JPanel  implements ActionListener{  
    
    // Paramètres de la fenetre 
    private CoordonneeJeu coordonneJeu;         // Coordonnee du jeu
    private Dimension dimension;                // Dimension du jeu

    // Paramètres jeu
    private Timer_Loops timer;                  // Timer du jeu
    private boolean vivant;                     // True == Joueur joue False == Fin de la partie
    private int nbRangeeCompleted;              // Nombre de rangée completé

    // Boucle du jeu
    private ArrayList<Tetrominoes> tetrominoes; // List des Tetrominoe du jeu
    private int difficultee;                    // Difficulté du jeu
    
    // Graphics
    private Graphics bufferGraphics;            // Buffer évite le scintillement
    private Image offscreen;                    // Image du buffer
    
    /**
     * Constructeur du jeu Tetris par défaut
     * @param colonne nombre de colonne du jeu
     * @param rangee Nombre de rangee du jeu
     * @param dimension Dimension du JPannel
     */
    public JeuTetris(int colonne, int rangee, Dimension dimension) {
        // Dimension du jeu
        coordonneJeu = new CoordonneeJeu(colonne,rangee);
        this.dimension = dimension;
        setSize(dimension);
  
        // Initialise la liste des tetrominoes
        tetrominoes = new ArrayList<>();
        
        
        // Pour les événements du clavier
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new Controls());

        
    }
    
    /**
     * Commencement de la partie
     * @param difficultee Difficulté du jeu [1 à 10]
     */
    public void start(int difficultee){
        newBlock();
        this.difficultee = 1000/difficultee;
        timer = new Timer_Loops(this.difficultee, this);
        timer.start();
        vivant = true;
        nbRangeeCompleted=0;
    }
    
    /**
     * Fin de la partie
     */
    public void stop(){
        timer.stop();
        vivant = false;
    }
    
    /**
     * Fait une pause de la partie 
     */
    public void pause(){
        timer.stop();
    }
    
    /**
     * Resume la partie
     */
    public void resume(){
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
        for(int y=1; y < coordonneJeu.getNombreRangee(); y++)
            verifyCompletedRow(y);   
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
     * @param g Graphics du JPannel (JeuTetris)
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
    public Timer_Loops getTimerDifficulte() {
        return timer;
    }
    
    /**
     * Setteur de la difficultée 
     * @param timerDifficulte La difficulté du jeu
     */
    public void setTimerDifficulte(Timer_Loops timerDifficulte) {
        this.timer = timerDifficulte; 
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
                if(true==bool[x]&& bool[x]== !coordonneJeu.IsEmpty(x, rangee))
                    return false;
        }
        return true;
    }
    
    /**
     * Avancement du jeu dans le temps
     * @param timer Appel de la fonction par le timer
     */
    @Override
    public void actionPerformed(ActionEvent timer) {
        
        // Si le tetrominoe ne peut pas descendre
        if (!canFall()){
            newBlock();
            tetrominoes.get(tetrominoes.size()-1).dropTetrominoe();
            if(!canFall()){
                vivant = false;
                this.timer.stop();
                JOptionPane.showMessageDialog(this, "Fin de la partie \nNombre de rangée compléter : " + nbRangeeCompleted);
            }
        }
        // Si le tetrominoe peut descendre
        else if(canFall())
            tetrominoes.get(tetrominoes.size()-1).dropTetrominoe(); 
        repaint();
    }   
 
    /**
     * Classe Controls Gestion des controles du jeu
     */
    public class Controls implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent ke){
            
            // Touches enfoncées
            if (ke.getID()==KeyEvent.KEY_PRESSED){
                // Gauche
                if(ke.getKeyCode() == VK_LEFT){ 
                    if(tetrominoes.get(tetrominoes.size()-1).left())
                        if(!canMove())
                            tetrominoes.get(tetrominoes.size()-1).right();
                        else
                            repaint();
                }

                // Droite
                else if(ke.getKeyCode() == VK_RIGHT) {
                    if(tetrominoes.get(tetrominoes.size()-1).right())
                        if(!canMove())
                            tetrominoes.get(tetrominoes.size()-1).left();
                        else
                            repaint(); 
                    }

                // Haut
                else if(ke.getKeyCode() == VK_UP) {
                    tetrominoes.get(tetrominoes.size()-1).rotateLeft();
                    if(canMove())
                        repaint();
                    else
                        tetrominoes.get(tetrominoes.size()-1).rotateRight();
                }
                
                // Bas
                else if(ke.getKeyCode() == VK_DOWN) {
                    if(canFall()){
                        tetrominoes.get(tetrominoes.size()-1).dropTetrominoe();
                        repaint();
                    }  
                }
            }
            
            if (ke.getID()==KeyEvent.KEY_RELEASED){
                // +  Augumentation de la difficulté
                if(ke.getKeyCode() == 107 || ke.getKeyCode() == 45) {
                    timer.setDifficulte(timer.getDifficulte()-50);
                }
                // -   Diminution de la difficulté
                else if(ke.getKeyCode() == 109|| ke.getKeyCode() == 61) 
                    timer.setDifficulte(timer.getDifficulte()+50);  
            }
            return true;
        }
    }  
}
    
    
