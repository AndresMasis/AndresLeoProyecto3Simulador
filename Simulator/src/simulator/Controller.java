/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Controller implements KeyListener{
    private int option;
     
    // Detects if any key is presses
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_UP){
            // The up arrow has been pressed
            System.out.println("1");
            
        } else if (code == KeyEvent.VK_DOWN){
            // The down arrow has been pressed
            System.out.println("2");
            
        } else if (code == KeyEvent.VK_LEFT){
            // The left arrow has been pressed
            System.out.println("3");
            
        } else if (code == KeyEvent.VK_RIGHT){
            // The right arrow has been pressed
            System.out.println("4");
            
        } else if (code == KeyEvent.VK_SPACE){
            // The spacebar has been pressed
            System.out.println("5");
        }
    }
   

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }

   
    
}
