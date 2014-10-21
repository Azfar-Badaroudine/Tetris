package tetris;
;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Classe JeuTetris
 * @author Azfar et Donaven
 */
public class JeuTetris extends JPanel  implements ActionListener{
    // private Piece[] piece = new Piece[50]; // une 
    //private ArrayList<Block> blocks;
    
    // ----------------Paramètres de la fenetre --------
    private CoordonneeJeu coordonneJeu;
    private Dimension dimension; // On devra gérer les dimension par rapport a la table des coordonnées du jeu choisis
    //--------------------------------------------------
    
    // ----------------Paramètres jeu ------------------
    private Timer_Loops timer;
    private Controls controls;
    private boolean vivant;
    //--------------------------------------------------
    
    /**
     * Constructeur du jeu Tetris par défaut
     */
    public JeuTetris() {
        coordonneJeu = new CoordonneeJeu(10,10);
        dimension = new Dimension(200, 400);
        setSize(dimension);
        controls = new Controls();
        addKeyListener(controls);
        // Initialise le timer 
        timer = new Timer_Loops(400, this);
        timer.start();
        vivant = true;
        
        // -------------Desinne les casses---------
        /*blocks = new ArrayList<>();
        Random random = new Random();
        float hue = 0;
        float saturation;
        float luminance;
        
        for (int i=0 ; i< 4; i++){
            hue = random.nextFloat();
            saturation = 0.9f;//1.0 for brilliant, 0.0 for dull
            luminance = 1.0f; //1.0 for brighter, 0.0 for black
            Color color = Color.getHSBColor(hue, saturation, luminance);
        
            Double blockWidth  = dimension.getWidth()/coordonneJeu.getNombreColonne();
            Double blockHeight = dimension.getHeight()/coordonneJeu.getNombreRangee();
            blocks.add(new Block(blockWidth,blockHeight, color));
        }
        add(blocks.get(0));
        blockDrop();*/
    }


      
    /**
     * Avancement du jeu dans le temps
     * @param timer Appel de la fonction par le timer
     */
    @Override
    public void actionPerformed(ActionEvent timer) {
        
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
        coordonneJeu.setCoordonee(5, 5, true);
        coordonneJeu.setCoordonee(0, 0, true);
        coordonneJeu.setCoordonee(7, 9, true);
        for(x=0; x < coordonneJeu.getNombreColonne(); x++){
            for(y=0; y < coordonneJeu.getNombreRangee();  y++){
                if(!coordonneJeu.IsEmpty(x, y))
                    paintBlocks((dimension.getWidth()/coordonneJeu.getNombreColonne())*x,
                                 (dimension.getHeight()/coordonneJeu.getNombreRangee())*y,
                                 Color.BLUE, g);
                
            }
        }

       
    
    
    }
    public void paintBlocks(Double colonne, Double rangee, Color color, Graphics g){
        System.out.print("ICI");
        Graphics2D g2d = (Graphics2D) g;
        
        
        System.out.println("Colonne :"+colonne *dimension.getWidth()/coordonneJeu.getNombreColonne());
        System.out.println("Rangee  :"+rangee  *dimension.getHeight()/coordonneJeu.getNombreRangee());
        Rectangle2D rect = new Rectangle2D.Double(colonne ,
                                                  rangee  ,
                                                  dimension.getWidth()/coordonneJeu.getNombreColonne(),
                                                  dimension.getHeight()/coordonneJeu.getNombreRangee());
        g2d.setColor(color);
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
}