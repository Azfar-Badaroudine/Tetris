/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package base.de.données;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author DLU_usager
 */
public class Fenetre extends JFrame implements ActionListener{
    public Fenetre() {
        super("Login");
        setFocusable(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        init();
    }
    
    public void init(){
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
