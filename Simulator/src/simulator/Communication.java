package simulator;

//import org.json.simple.JSONObject;        este import no me sirve xD
import org.json.JSONObject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Communication implements Runnable {


    
    public void sendSocket(int number,int cable) {
        // Gets the IP address of this device
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();

            // Creates the socket
            try {
                Socket mySocket = new Socket(ip, cable);
                DataOutputStream output = new DataOutputStream(mySocket.getOutputStream());
                output.writeInt(number);
                output.close();
            } catch (UnknownHostException e) {
                // Error creating the socket
                e.printStackTrace();

            } catch (IOException e) {
                // Error creating the socket
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            // Error getting the IP
            e.printStackTrace();
        }
    }
       
    public void sendSocScre(String number,int cable) {
        // Gets the IP address of this device
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();

            // Creates the socket
            try {
                Socket mySocket = new Socket(ip, cable);
                DataOutputStream output = new DataOutputStream(mySocket.getOutputStream());
                output.writeUTF(number);
                output.close();
            } catch (UnknownHostException e) {
                // Error creating the socket
                e.printStackTrace();

            } catch (IOException e) {
                // Error creating the socket
                e.printStackTrace();
            }

        } catch (UnknownHostException e) {
            // Error getting the IP
            e.printStackTrace();
        }
    }
    @Override
    public void run() {/*
        try {
            ServerSocket server = new ServerSocket(9999);
            while (true) {
                Socket mySocket = server.accept();
                DataInputStream input = new DataInputStream(mySocket.getInputStream());
                int mensaje = input.readInt();
                System.out.println("A comunication le llego un " + mensaje);
                mySocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Simulator.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

}


