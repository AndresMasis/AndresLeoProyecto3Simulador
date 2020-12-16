/*
 * This is the part that controls the logic of the console
 */
package simulator;



public class Simulator {

    public Simulator() {
        Communication c = new Communication();
        Thread t = new Thread(c);
        t.start();
    }
   

    public static void main(String args[]) {
        new Simulator();
    }


}
