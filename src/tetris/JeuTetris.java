package tetris;
import com.sun.pisces.Surface;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
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
    private Controls controls;
    private boolean vivant;
    //--------------------------------------------------
    
    /**
     * Constructeur du jeu Tetris par défaut
     */
    public JeuTetris() {
        coordonneJeu = new CoordonneeJeu(10,5);
        dimension = new Dimension(100, 50);
        controls = new Controls();
        addKeyListener(controls);
        // Initialise le timer 
        timer = new Timer_Loops(400, this);
        timer.start();
        vivant = true;
        
        // -------------Desinne les casses---------
        
        
        
        /*
        Random rand = new Random();
        for (int i=0; i<50; i++){               // On devra garder le random
            int random = rand.nextInt(7) + 1;
            piece[i] = new Piece(random);
        }*/
        
    }
    
    /*
    public void start(){
        while (vivant){
            for (int i=0; i<50; i++){
                if (piece[i].getActif() == false){
                    piece[i].Activer();
                }
                //if (ligne est complétée)
                //if (game over)
            }
        }
        gameOver();
    }
    *//*
    public void gameOver(){
        //Enregistrer highscore
    }
    */
    
    /**
     * Avancement du jeu dans le temps
     * @param timer Appel de la fonction par le timer
     */
    @Override
    public void actionPerformed(ActionEvent timer) {
        /*if (isFallingFinished) {
            isFallingFinished = false;
            newPiece();
        } else {
            oneLineDown();
       }*/
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
        int x = 1;
        int y = 1;
        /*Point2D de = new Point2D(dimension.getWidth()/x,dimension.getHeight()/y);
        Point2D p1 = new Point2D(dimension.getWidth()/x,dimension.getHeight()/y);*/
        
        for(x=1 ; coordonneJeu.getNombreColonne()>= x; x++)
            g2d.drawLine((((Dimension2D)dimension).getWidth()/x), 30, 200, 30);
        
        
        g2d.drawLine(30, 30, 200, 30);
        g2d.drawLine(200, 30, 30, 200);
        g2d.drawLine(30, 200, 200, 200);
        g2d.drawLine(200, 200, 30, 30);
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
        
        add(new JeuTetris());
        
        setSize(350, 250);
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