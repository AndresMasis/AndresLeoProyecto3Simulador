package simulator;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pacman {
    int screenPac[][]= new int [50][50];
    int screenPac2[][]= new int [50][50];
    int posY = 25;
    int posX = 23;
    ServerSocket server;    
           
    void dibujarLinea(int fil, int colum,int fil2, int colum2,int color){
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
    
    void basePac(){
        try{
        server = new ServerSocket(9999);
        } catch (IOException ex) {
            Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        dibujarLinea(0,0,50,50,1);
        dibujarLinea(0,0,50,1,15);
        dibujarLinea(49,0,50,50,15);
        dibujarLinea(0,0,1,50,15);
        dibujarLinea(0,49,50,50,15);
        
        dibujarLinea(0,24,5,25,15);
        dibujarLinea(44,24,50,25,15);
        
        dibujarLinea(24,0,25,5,15);
        dibujarLinea(24,44,25,50,15);        

        dibujarLinea(9,9,10,15,15);
        dibujarLinea(9,9,15,10,15);
        
        dibujarLinea(9,33,10,39,15);
        dibujarLinea(9,39,15,40,15);
        
        dibujarLinea(33,9,40,10,15);
        dibujarLinea(39,9,40,15,15);
        
        dibujarLinea(33,39,40,40,15);
        dibujarLinea(39,33,40,40,15);        
        
        dibujarLinea(19,18,20,29,15);
        dibujarLinea(29,18,30,29,15);
        dibujarLinea(19,29,30,30,15);        
        
        dibujarLinea(5,5,6,6,10); 
        dibujarLinea(19,3,20,4,10); 
        dibujarLinea(3,19,4,20,10); 
        
        dibujarLinea(43,5,44,6,10); 
        dibujarLinea(28,3,29,4,10); 
        dibujarLinea(44,19,45,20,10); 
        
        dibujarLinea(5,43,6,44,10); 
        dibujarLinea(3,28,4,29,10); 
        dibujarLinea(19,44,20,45,10); 
        
        dibujarLinea(43,43,44,44,10); 
        dibujarLinea(32,44,33,45,10); 
        dibujarLinea(44,33,45,34,10);
        
        dibujarLinea(24,36,25,37,10); 
        
        dibujarLinea(posY,posX,posY+1,posX+2,5);
        dibujarLinea(posY+1,posX,posY+2,posX+2,5);
        sendScr();
    }
    
    void sendScr(){
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
                if(screenPac[fil][colum] == 10){
                    check = 1;  
                }
                fil++;
            }
            fil = 0;
            colum++;                
        }
        json1 += "[]]}";  
        if(check == 0)
            basePac();
        
        copyScr();
        
        server.sendSocScre(json1,9995);    
    }
    
    public void copyScr(){
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
    
    
    public Pacman(){
        basePac();
    }
    
    public int block(){
    
        return 0;
    }
    
    public void action(int code){            
        if (code == 1) {
            // The up arrow has been pressed
            if (screenPac[posY][posX-1] != 15 && screenPac[posY+1][posX-1] != 15){
                dibujarLinea(posY,posX+1,posY+1,posX+2,1);
                dibujarLinea(posY+1,posX+1,posY+2,posX+2,1);
                posX--;
                dibujarLinea(posY,posX,posY+1,posX+2,5);
                dibujarLinea(posY+1,posX,posY+2,posX+2,5);   
            }

        } else if (code == 2) {
            // The down arrow has been pressed
            if (screenPac[posY][posX+2] != 15 && screenPac[posY+1][posX+2] != 15){
                dibujarLinea(posY,posX,posY+1,posX+1,1);
                dibujarLinea(posY+1,posX,posY+2,posX+1,1);
                posX++;
                dibujarLinea(posY,posX,posY+1,posX+2,5);
                dibujarLinea(posY+1,posX,posY+2,posX+2,5); 
            }

        } else if (code == 3) {
            // The left arrow has been pressed 
            if (screenPac[posY-1][posX] != 15 && screenPac[posY-1][posX+1] != 15){
                dibujarLinea(posY+1,posX,posY+2,posX+2,1);
                posY--;
                dibujarLinea(posY,posX,posY+1,posX+2,5);
                dibujarLinea(posY+1,posX,posY+2,posX+2,5);  
            }
        } else if (code == 4) {
            // The right arrow has been pressed
            if (screenPac[posY+2][posX] != 15 && screenPac[posY+2][posX+1] != 15){
                dibujarLinea(posY,posX,posY+1,posX+2,1);
                posY++;
                dibujarLinea(posY,posX,posY+1,posX+2,5);
                dibujarLinea(posY+1,posX,posY+2,posX+2,5);   
            }


        } else if (code == 5) {
            // The spacebar has been pressed
            
        }
    sendScr();
    }
    
    public void cliente(){
                try {
                    
                    while (true) {
                        Socket mySocket = server.accept();
                        DataInputStream input = new DataInputStream(mySocket.getInputStream());
                        int mensaje = input.readInt();
                        
                        action(mensaje);
                        mySocket.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
    public static void main(String args[]) {
        Pacman pru =  new Pacman();
        pru.cliente();
    }

}

/*


        JSONArray json2 = new JSONArray(json1);                // convertimos en array

        
        JSONObject objeto3 = json2.getJSONObject(0);           // ahora un objeto json
        JSONArray precio = objeto3.getJSONArray("products");                  //buscamos el array de productos
        
        for(int i = 0; i <= 19; i++)
{
        JSONObject precio2 = (JSONObject) precio.get(i);
                      
        JSONObject reviews = (JSONObject) precio2.get("reviews");                  //buscamos reviews
        int reviewsNum = (int) reviews.get("total_reviews");                  //buscamos current_price
          
        while (reviewsNum > 100){        
            reviewsNum = reviewsNum/2;
        }
       
       this.listPrecio[i]= ((reviewsNum*2)/5)+15;
       this.listReviews[i]= (reviewsNum/2)+13;
       }
        
    }
}

*/