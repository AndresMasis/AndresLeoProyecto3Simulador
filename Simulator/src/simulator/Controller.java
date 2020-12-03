package simulator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;


public class Controller extends JFrame {

    public Controller() {
        JFrame frame = new JFrame();
        frame.setVisible(false);
        frame.setSize(1, 1);
        frame.setFocusable(true);

        frame.addKeyListener(new KeyListener() {

            
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();

                if (code == KeyEvent.VK_UP) {
                    System.out.println("Hola");
                    // The up arrow has been pressed

                } else if (code == KeyEvent.VK_DOWN) {
                    // The down arrow has been pressed;

                } else if (code == KeyEvent.VK_LEFT) {
                    // The left arrow has been pressed

                } else if (code == KeyEvent.VK_RIGHT) {
                    // The right arrow has been pressed

                } else if (code == KeyEvent.VK_SPACE) {
                    System.out.println("ADIOS");
                    // The spacebar has been pressed
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

        });
    }

    public static void main(String args[]) {
        new Controller();
    }

}
