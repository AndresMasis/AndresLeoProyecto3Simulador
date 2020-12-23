package simulator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

public class SpaInv {
    int screenPac[][]= new int [50][50];
    int screenPac2[][]= new int [50][50];
    int posY = 24;
    int posX = 47;
    int posYEn = 4;
    int posXEn = 3;
    int posYEnAu;
    int posXEnAu; 
    int posChe = 0;
    int veri = 0;
    ServerSocket server;    
           
    void dibujarLinea2(int fil, int colum,int fil2, int colum2,int color){
        int aux = fil;
        while (colum != colum2){
            while (fil != fil2){   
                screenPac[fil][colum] = color;
                fil++;
            }
            fil = aux;
            colum++;                
        }
    }
    
    void baseSpa(){
        int fil = 0;
        int colum = 0;   
        while (colum != 50){
            while (fil != 50){   
                screenPac2[fil][colum] = 0;
                fil++;
            }
            fil = 0;
            colum++;                
        }
        
        dibujarLinea2(0,0,50,50,1);
        dibujarLinea2(0,0,50,1,7);
        dibujarLinea2(49,0,50,50,7);
        dibujarLinea2(0,0,1,50,7);
        dibujarLinea2(0,49,50,50,7);
        
      
        dibujarLinea2(posY,posX-1,posY+1,posX,6);
        dibujarLinea2(posY-1,posX,posY+2,posX+1,6);
        sendScr2();
    }
    
    void sendScr2(){
        Communication server = new Communication();   
        String json1 = "{\"list\":[";
        int fil = 0;
        int colum = 0;   
        int check = 0;
        while (colum != 50){
            while (fil != 50){   
                if(screenPac[fil][colum] != screenPac2[fil][colum]){
                    int salida = screenPac[fil][colum];
                    json1 += "["+fil+","+colum+","+salida+"],";     
                }
                if(screenPac[fil][colum] == 11){
                    check = 1;  
                }
                fil++;
            }
            fil = 0;
            colum++;                
        }
        json1 += "[]]}";  
        if(check == 0 && veri == 1){
            posYEn = 4;
            posXEn = 3;
            veri = 0;
        }
            
        
        copyScr2();
        
        server.sendSocScre(json1,9995);    
    }
    
    public void copyScr2(){
    int fil = 0;
    int colum = 0;   
    while (colum != 50){
        while (fil != 50){   
            screenPac2[fil][colum] = screenPac[fil][colum];
            fil++;
        }
        fil = 0;
        colum++;                
        }
    }
    
    
    public SpaInv(){
        try{
            server = new ServerSocket(9999);
        } catch (IOException ex) {
            Logger.getLogger(SpaInv.class.getName()).log(Level.SEVERE, null, ex);
        }
        movEne();
        baseSpa();
    }
    
    public int block(){
    
        return 0;
    }
    
    public void action2(int code) throws InterruptedException{            
        if (code == 3) {
            // The left arrow has been pressed 
            if (screenPac[posY-2][posX+1] != 7 ){
                dibujarLinea2(posY+1,posX,posY+2,posX+1,1);
                dibujarLinea2(posY,posX-1,posY+1,posX,1);
                posY--;
                dibujarLinea2(posY,posX-1,posY+1,posX,6);
                dibujarLinea2(posY-1,posX,posY+2,posX+1,6);
            }
        } else if (code == 4) {
            // The right arrow has been pressed
            if (screenPac[posY+2][posX+1] != 7 ){
                dibujarLinea2(posY-1,posX,posY,posX+1,1);
                dibujarLinea2(posY,posX-1,posY+1,posX,1);
                posY++;
                dibujarLinea2(posY,posX-1,posY+1,posX,6);
                dibujarLinea2(posY-1,posX,posY+2,posX+1,6);  
            }
        } else if (code == 5) {
            // The spacebar has been pressed shot
            dibujarLinea2(posY,1,posY+1,posX-2,2);
            sendScr2();
            Thread.sleep(1000);
            dibujarLinea2(posY,1,posY+1,posX-2,1);
        }
    sendScr2();
    }
    
    public void movEne(){
        Timer timer = new Timer(2500, new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                
                if (screenPac[posYEn][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn,posXEn,posYEn+1,posXEn+1,1);
                else 
                    dibujarLinea2(posYEn,posXEn,posYEn+1,posXEn+1,0);
                
                if (screenPac[posYEn+3][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+3,posXEn,posYEn+4,posXEn+1,1);
                else 
                    dibujarLinea2(posYEn+3,posXEn,posYEn+4,posXEn+1,0);
                
                if (screenPac[posYEn+6][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+6,posXEn,posYEn+7,posXEn+1,1);  
                else 
                    dibujarLinea2(posYEn+6,posXEn,posYEn+7,posXEn+1,0);  
                
                if (screenPac[posYEn+9][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+9,posXEn,posYEn+10,posXEn+1,1); 
                else 
                    dibujarLinea2(posYEn+9,posXEn,posYEn+10,posXEn+1,0); 
                
                if (screenPac[posYEn+12][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+12,posXEn,posYEn+13,posXEn+1,1); 
                else 
                    dibujarLinea2(posYEn+12,posXEn,posYEn+13,posXEn+1,0); 
                
                if (screenPac[posYEn+15][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+15,posXEn,posYEn+16,posXEn+1,1);
                else 
                    dibujarLinea2(posYEn+15,posXEn,posYEn+16,posXEn+1,0); 
                
                if (screenPac[posYEn+26][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+26,posXEn,posYEn+27,posXEn+1,1); 
                else 
                    dibujarLinea2(posYEn+26,posXEn,posYEn+27,posXEn+1,0); 
                 
                if (screenPac[posYEn+29][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+29,posXEn,posYEn+30,posXEn+1,1); 
                else 
                    dibujarLinea2(posYEn+29,posXEn,posYEn+30,posXEn+1,0); 
                
                if (screenPac[posYEn+32][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+32,posXEn,posYEn+33,posXEn+1,1);
                else 
                    dibujarLinea2(posYEn+32,posXEn,posYEn+33,posXEn+1,0);
                
                if (screenPac[posYEn+35][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+35,posXEn,posYEn+36,posXEn+1,1); 
                else 
                    dibujarLinea2(posYEn+35,posXEn,posYEn+36,posXEn+1,0); 
                
                if (screenPac[posYEn+38][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+38,posXEn,posYEn+39,posXEn+1,1);
                else 
                    dibujarLinea2(posYEn+38,posXEn,posYEn+39,posXEn+1,0);
                
                if (screenPac[posYEn+41][posXEn] == 11 || veri == 0)
                    dibujarLinea2(posYEn+41,posXEn,posYEn+42,posXEn+1,1); 
                else 
                    dibujarLinea2(posYEn+41,posXEn,posYEn+42,posXEn+1,0);       
   
                posYEnAu = posYEn;
                posXEnAu = posXEn;
                
                if(posChe == 0){
                    posChe = 1; 
                    posYEn++;
                }else{
                    posChe = 0; 
                    posYEn--;
                }
                
                posXEn++;
                if(posXEn > 46){
                    posYEn = 4;
                    posXEn = 3;
                    baseSpa();                
                }
                
                if (screenPac[posYEnAu][posXEnAu] != 0)
                    dibujarLinea2(posYEn,posXEn,posYEn+1,posXEn+1,11);
                else
                    dibujarLinea2(posYEn-1,posXEn-1,posYEn+2,posXEn+1,1);
                
                if (screenPac[posYEnAu+3][posXEnAu] != 0)    
                    dibujarLinea2(posYEn+3,posXEn,posYEn+4,posXEn+1,11);
                else
                    dibujarLinea2(posYEn+2,posXEn-1,posYEn+5,posXEn+1,1);
                if (screenPac[posYEnAu+6][posXEnAu] != 0)
                    dibujarLinea2(posYEn+6,posXEn,posYEn+7,posXEn+1,11);
                else
                    dibujarLinea2(posYEn+5,posXEn-1,posYEn+8,posXEn+1,1);
                if (screenPac[posYEnAu+9][posXEnAu] != 0)
                    dibujarLinea2(posYEn+9,posXEn,posYEn+10,posXEn+1,11);
                else
                    dibujarLinea2(posYEn+8,posXEn-1,posYEn+11,posXEn+1,1);
                if (screenPac[posYEnAu+12][posXEnAu] != 0)
                    dibujarLinea2(posYEn+12,posXEn,posYEn+13,posXEn+1,11);
                else
                    dibujarLinea2(posYEn+11,posXEn-1,posYEn+14,posXEn+1,1);
                if (screenPac[posYEnAu+15][posXEnAu] != 0)
                    dibujarLinea2(posYEn+15,posXEn,posYEn+16,posXEn+1,11);  
                else
                    dibujarLinea2(posYEn+14,posXEn-1,posYEn+17,posXEn+1,1);
                
                if (screenPac[posYEnAu+26][posXEnAu] != 0)
                    dibujarLinea2(posYEn+26,posXEn,posYEn+27,posXEn+1,11);
                else
                    dibujarLinea2(posYEn+25,posXEn-1,posYEn+28,posXEn+1,1);
                if (screenPac[posYEnAu+29][posXEnAu] != 0)
                    dibujarLinea2(posYEn+29,posXEn,posYEn+30,posXEn+1,11);
                else
                    dibujarLinea2(posYEn+28,posXEn-1,posYEn+31,posXEn+1,1);
                if (screenPac[posYEnAu+32][posXEnAu] != 0)
                    dibujarLinea2(posYEn+32,posXEn,posYEn+33,posXEn+1,11);
                else
                    dibujarLinea2(posYEn+31,posXEn-1,posYEn+34,posXEn+1,1);
                if (screenPac[posYEnAu+35][posXEnAu] != 0)
                    dibujarLinea2(posYEn+35,posXEn,posYEn+36,posXEn+1,11);
                else
                    dibujarLinea2(posYEn+34,posXEn-1,posYEn+37,posXEn+1,1);
                if (screenPac[posYEnAu+38][posXEnAu] != 0)
                    dibujarLinea2(posYEn+38,posXEn,posYEn+39,posXEn+1,11);
                else
                    dibujarLinea2(posYEn+37,posXEn-1,posYEn+40,posXEn+1,1);
                if (screenPac[posYEnAu+41][posXEnAu] != 0)
                    dibujarLinea2(posYEn+41,posXEn,posYEn+42,posXEn+1,11);  
                else
                    dibujarLinea2(posYEn+40,posXEn-1,posYEn+43,posXEn+1,1);
                
                
                
                sendScr2();
                veri = 1;
              }
        });
        timer.start();
    }
        
    public void clienteS() throws InterruptedException{
                try {                    
                    while (true) {
                        Socket mySocket = server.accept();
                        DataInputStream input = new DataInputStream(mySocket.getInputStream());
                        int mensaje = input.readInt();
                        
                        action2(mensaje);
                        mySocket.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public static void main(String args[]) throws InterruptedException {
        SpaInv pru =  new SpaInv();
        pru.clienteS();
    }

}
