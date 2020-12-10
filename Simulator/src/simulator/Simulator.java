/*
 * This is the part that controls the logic of the console
 */
package simulator;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Simulator implements Runnable {

    public Simulator() {
        Thread mihilo = new Thread(this);
        mihilo.start();
    }

    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(9999);
            while (true) {
                Socket misocket = server.accept();
                DataInputStream flujo_entrada = new DataInputStream(misocket.getInputStream());
                int mensaje = flujo_entrada.readInt();
                System.out.println("A la consola le llego un " + mensaje);
                misocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        new Simulator();
    }

}
