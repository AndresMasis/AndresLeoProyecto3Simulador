package simulator;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer; 


import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

public final class Screen extends javax.swing.JFrame {
    
    int screen[][]= new int [50][50];
    javax.swing.JLabel[][] screenLab = new javax.swing.JLabel [50][50];
    
    ServerSocket server;  
    String mess;
        /*
        0 = white
        1 = black
        2 = red
        3 = lime
        4 = blue
        5 = yellow
        6 = silver
        7 = gray
        8 = maroon
        9 = purple
        10 = fuchsia
        11 = green
        12 = olive
        13 = navy
        14 = teal
        15 = aqua     
        */
    
    public Screen() throws IOException{ //Start process
        this.initComponents();       
        this.setLocationRelativeTo(null);         
        try{
            server = new ServerSocket(9995);
        } catch (IOException ex) {
            Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        startSc();     
        printCol();
        cliente();
    }  
    
    void startSc(){//Set screen with 0(white), and set screenLab with new Labels
        int fil = 0;
        int colum = 0;   
        while (colum != 50){
            while (fil != 50){   
                screen[fil][colum] = 9;
                screenLab[fil][colum] = new javax.swing.JLabel();
                fil++;
            }
            fil = 0;
            colum++;                
        }
    }

    
    void printCol(){//It refresh the pixels of the screen
            int posF = 8;
            int posC = 8;
            int fila = 0;
            int columna = 0;

            while (columna != 50){
                while (fila != 50){                   
                    screenLab[fila][columna].setSize(16, 16);

                    if (screen[fila][columna] == 0){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/white.png")));    
                    } 
                    else if (screen[fila][columna] == 1){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/black.png")));                                  
                    }
                    else if (screen[fila][columna] == 2){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/red.png")));  
                    }
                    else if (screen[fila][columna] == 3){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/lime.png")));;
                    }
                    else if (screen[fila][columna] == 4){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/blue.png")));
                    }
                    else if (screen[fila][columna] == 5){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/yellow.png")));  
                    }
                    else if (screen[fila][columna] == 6){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/silver.png"))); 
                    }
                    else if (screen[fila][columna] == 7){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/gray.png")));    
                    }
                    else if (screen[fila][columna] == 8){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/maroon.png")));                
                    }
                    else if (screen[fila][columna] == 9){                        
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/purple.png")));
                    }               
                    else if (screen[fila][columna] == 10){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/fuchsia.png")));
                    }                                                                
                    else if (screen[fila][columna] == 11){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/green.png")));
                    }
                    else if (screen[fila][columna] == 12){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/olive.png")));
                    }
                    else if (screen[fila][columna] == 13){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/navy.png")));
                    }
                    else if (screen[fila][columna] == 14){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/teal.png")));
                    }
                    else if (screen[fila][columna] == 15){
                        screenLab[fila][columna].setIcon(new ImageIcon(getClass().getResource("/imagen/aqua.png")));
                    }
                    screenLab[fila][columna].setLocation(posF, posC);
                    screenLab[fila][columna].setOpaque(rootPaneCheckingEnabled);
                    screenLab[fila][columna].setText("");
                    mapa.add(screenLab[fila][columna]);
                    fila++;
                    posF += 16;
                }
                posF = 8;
                posC += 16;
                fila = 0;
                columna++; 
                }
            }
         
           
    
    public void seeScre(String mensaje){
        JSONObject json1 = new JSONObject(mensaje);               
        JSONArray json2 = (JSONArray) json1.get("list"); 
        int elements = json2.length();
        elements--;
        int count = 0;
        int fil;
        int colum;
        int color;   
        JSONArray array; 
        while(count != elements){    
            array = (JSONArray)json2.get(count);
            fil = (int)array.get(0);
            colum = (int)array.get(1);
            color = (int)array.get(2);
            screen[fil][colum] = color;
            count++;
        }
        printCol();
        
    }
    
    

    public void cliente(){
        Timer timer = new Timer(1, new ActionListener() {
        public void actionPerformed(ActionEvent ae) {
                try{
                    //ServerSocket server = new ServerSocket(9995);
                    //while (true) {
                        Socket mySocket = server.accept();
                        DataInputStream input = new DataInputStream(mySocket.getInputStream());
                        String mensaje = input.readUTF();
                        seeScre(mensaje);
                        mySocket.close();
                    //}
                } catch (IOException ex) {
                    Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                }
    }});
        
    timer.start();  
    }
    
    

    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mapa = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(java.awt.Color.black);
        setIconImage(Toolkit.getDefaultToolkit().getImage("Icon.png"));
        setSize(new java.awt.Dimension(500, 500));

        mapa.setBackground(new java.awt.Color(255, 255, 255));
        mapa.setMaximumSize(new java.awt.Dimension(615, 410));
        mapa.setPreferredSize(new java.awt.Dimension(820, 820));
        mapa.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout mapaLayout = new javax.swing.GroupLayout(mapa);
        mapa.setLayout(mapaLayout);
        mapaLayout.setHorizontalGroup(
            mapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );
        mapaLayout.setVerticalGroup(
            mapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
      
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Screen().setVisible(true);                    
                } catch (IOException ex) {
                    Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel mapa;
    // End of variables declaration//GEN-END:variables

}
