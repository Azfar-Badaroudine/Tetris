package tetris;
;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Classe JeuTetris
 * @author Azfar et Donaven
 */
public class JeuTetris extends JPanel  implements ActionListener{
    // private Piece[] piece = new Piece[50]; // une 
  
    
    // ----------------Paramètres de la fenetre --------
    private CoordonneeJeu coordonneJeu;
    private Dimension dimension; // On devra gérer les dimension par rapport a la table des coordonnées du jeu choisis
    //--------------------------------------------------
    
    // ----------------Paramètres jeu ------------------
    private Timer_Loops timer;
    private boolean vivant;
    //--------------------------------------------------
    
    //-------------Boucle du jeu------------------------
    private ArrayList<Tetrominoes> tetrominoes;
    
    
    /**
     * Constructeur du jeu Tetris par défaut
     */
    public JeuTetris() {
        coordonneJeu = new CoordonneeJeu(10,20);
        dimension = new Dimension(200, 400);
        setSize(dimension);
         // Pour les événements du clavier
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new Controls());
        
        // Initialise le timer 
        timer = new Timer_Loops(1000, this);
        timer.start();
        vivant = true;
        
        // -------------Desinne les casses---------
        tetrominoes = new ArrayList<>();
        newBlock();
        
        
        
        
        
        
    }


      
    /**
     * Avancement du jeu dans le temps
     * @param timer Appel de la fonction par le timer
     */
    @Override
    public void actionPerformed(ActionEvent timer) {
        System.out.println("\nTimer event");

        if (vivant == true)
            if (!canFall()){
                System.out.print("newBlock");
                newBlock();
                dropTetrominoes();
                if(!canFall()){
                    vivant = false;
                    System.out.print("Fin de la partie!");
                    this.timer.stop();
                }
                
            }
            else if(canFall())
               
                dropTetrominoes(); 
       // paintBlocks();
    }
  

    
    public void newBlock(){
        Random random = new Random();
        int forme = abs(random.nextInt() % 7)+1;
        float hue = random.nextFloat();
        float saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
        float luminance = 1.0f; //1.0 for brighter, 0.0 for black
        Color couleur = Color.getHSBColor(hue, saturation, luminance);
        
        Double blockWidth  = dimension.getWidth()/coordonneJeu.getNombreColonne();
        Double blockHeight = dimension.getHeight()/coordonneJeu.getNombreRangee();
        tetrominoes.add(new Tetrominoes(forme, couleur, coordonneJeu.getNombreColonne(), coordonneJeu.getNombreRangee()));
      
    }
    /**
     * Est-ce que les block peuvent descendre?
     * @return False == non True == Oui
     */
    public boolean canFall(){
        for(int x=0; x < coordonneJeu.getNombreColonne()-1; x++)
            for(int y=0; y <= coordonneJeu.getNombreRangee()-1; y++)
                if(!tetrominoes.get(tetrominoes.size()-1).IsEmpty(x, y))
                    try{
                        if(!coordonneJeu.IsEmpty(x, y+1) && tetrominoes.get(tetrominoes.size()-1).getEmplacement().IsEmpty(x, y+1) ){
                            tetrominoes.get(tetrominoes.size()-1).setIsFalling(false);
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
     * Descendre les blocks
     */
    public void removeDroppingTetrominoe(){
    Graphics2D g2d = (Graphics2D) this.getGraphics();
        
        // Enlève l'emplacement du Tetrominoe actuel
        for(int x=0; x < coordonneJeu.getNombreColonne()-1; x++)
            for(int y=0; y < coordonneJeu.getNombreRangee()-1; y++)
                if(!tetrominoes.get(tetrominoes.size()-1).IsEmpty(x, y)){
                    coordonneJeu.setCoordonee(x, y, false);
                    paintBlock((dimension.getWidth()/coordonneJeu.getNombreColonne())*x,
                               (dimension.getHeight()/coordonneJeu.getNombreRangee())*y,
                                this.getBackground(), g2d);
                }
    }
    public void dropTetrominoes(){
        removeDroppingTetrominoe();
        if(tetrominoes.get(tetrominoes.size()-1).isIsFalling()==false){
            tetrominoes.get(tetrominoes.size()-1).setIsFalling(true);
        }else
            tetrominoes.get(tetrominoes.size()-1).dropTetrominoe();
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        
            
        
        // Enlève l'emplacement du Tetrominoe actuel
        removeDroppingTetrominoe();
        updateBlockPaint();

                
        //System.out.print("\nTable de coordonnée du JEu");       
        //coordonneJeu.afficheTable();
        //doDrawing(g2d);
    }
    public void updateBlockPaint(){
        Graphics2D g2d = (Graphics2D) this.getGraphics();
    
        for(int x=0; x < coordonneJeu.getNombreColonne(); x++)
            for(int y=0; y < coordonneJeu.getNombreRangee(); y++)
                if(!tetrominoes.get(tetrominoes.size()-1).IsEmpty(x, y)){
                    
                    paintBlock((dimension.getWidth()/coordonneJeu.getNombreColonne())*x,
                                    (dimension.getHeight()/coordonneJeu.getNombreRangee())*y,
                                      tetrominoes.get(tetrominoes.size()-1).getCouleur(), g2d);
        }
    }
    public void updateFallingEndBlocks(){
        
        for(int x=0; x < coordonneJeu.getNombreColonne(); x++)
            for(int y=0; y < coordonneJeu.getNombreRangee(); y++)
                if(!tetrominoes.get(tetrominoes.size()-1).IsEmpty(x, y))
                    coordonneJeu.setCoordonee(x, y, true);     
        
        System.out.print("Falling end blocks Updated !");
        coordonneJeu.afficheTable();
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
     * Pause du jeu
     */
    public void pause(){
        timer.stop();
    }
    /**
     * Démarre le jeu
     */
    public void start(){
        timer.start();
    }
    /**
     * Démarre le jeu
     */
    public void restart(){
        timer.restart();
    }
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        int x = 0;
        for(x=0 ; coordonneJeu.getNombreColonne()> x; x++)
            g2d.draw(new Line2D.Double((dimension.getWidth()/coordonneJeu.getNombreColonne())*x,0,
                                        (dimension.getWidth()/coordonneJeu.getNombreColonne())*x, dimension.getHeight()));
        for(x=0 ; coordonneJeu.getNombreRangee()> x; x++)
            g2d.draw(new Line2D.Double(0,(dimension.getHeight()/coordonneJeu.getNombreRangee())*x,
                                        dimension.getWidth(),(dimension.getHeight()/coordonneJeu.getNombreRangee())*x));
        
        int y=0;
        /*coordonneJeu.setCoordonee(5, 5, true);
        coordonneJeu.setCoordonee(0, 0, true);
        coordonneJeu.setCoordonee(7, 9, true);*/
        

       
    
    
    }
 
    public void paintBlock(Double colonne, Double rangee, Color color, Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        Rectangle2D rect = new Rectangle2D.Double(colonne ,
                                                  rangee  ,
                                                  dimension.getWidth()/coordonneJeu.getNombreColonne(),
                                                  dimension.getHeight()/coordonneJeu.getNombreRangee());
       // g2d.setColor(color);
        g2d.setPaint(color);
        g2d.fill(rect);
        g2d.draw(rect);
        
        
        
        
        
        
        
        
        
    }

    public Dimension getDimension() {
        return dimension;
    }
    

    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g);
        
    }

    public static class Lines2 extends JFrame {

    public Lines2() {
        initUI();
    }
    
    private void initUI() {
        setTitle("Lines");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JeuTetris jeu = new JeuTetris();
        setSize(500,500);
        add(jeu);
        setLocationRelativeTo(null);
        
    }
}

    

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                Lines2 lines = new Lines2();
                lines.setVisible(true);
            }
        });
    }
    public class Controls implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent ke){
         
            System.out.print("KeyRealsesd");
            if (ke.getID()==KeyEvent.KEY_RELEASED){
                  
                if(ke.getKeyCode() == VK_LEFT) {
                    removeDroppingTetrominoe();
                    if(tetrominoes.get(tetrominoes.size()-1).left()){
                        tetrominoes.get(tetrominoes.size()-1).getEmplacement().afficheTable();
                        
                        
                        tetrominoes.get(tetrominoes.size()-1).getEmplacement().afficheTable();
                    }
                    updateBlockPaint();
                            
                }
                else if(ke.getKeyCode() == VK_RIGHT) {
                    removeDroppingTetrominoe();
                    if(tetrominoes.get(tetrominoes.size()-1).right()){
                        removeDroppingTetrominoe();
                    }
                    updateBlockPaint();
                }   
            }
            return true;
        }
    }
}