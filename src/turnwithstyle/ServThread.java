/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnwithstyle;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class ServThread extends Thread{
    private Socket s;
    private PrintWriter pw;
    private Scanner scan;
    private int local=0;
    private Count c;
    

    public ServThread(Socket s, Count c) {
        this.s = s;
        this.c = c;
    }
    
    public void manuelTurns(){
        pw.println("");
        pw.println("Manuel Turnstile: " + c.getStill());
        while(true){
            String line = scan.nextLine();
            if(line.equals("turn")){

                local++;
                c.addTurn();
                pw.println("Turns: " + local);

            }
            else{
                pw.println("You can only turn a TurnStile");
            }
            
        }
    }
    
    public void autoTurns(){
        pw.println("");
        pw.println("Automated Turnstile " + c.getStill());
        while(true){

            try {
                local++;
                c.addTurn();
                pw.println("Turns: " + local);
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
     
    }
    
    public void monitor(){
        pw.println("");
        pw.println("Monitor");
        while(true){
            try {
                pw.println("Spectators: " + c.getTurnt());
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void run() {
        
        try {
            
            pw = new PrintWriter(s.getOutputStream(), true);
            scan = new Scanner(s.getInputStream());
            pw.println("");
            pw.println("Automated TurnStill or Manuel Turnstil or Monitor? (a/t/m)");
        
            while(true){
                String line = scan.nextLine();
                
                if(line.equals("t")){
                    
                    c.addStil();
                    manuelTurns();
                }
                if(line.equals("a")){
                    c.addStil();
                    autoTurns();
                }
                if(line.equals("m")){
                    monitor();
                }
                else{
                    pw.println("Please chose one of the three:");
                    pw.println("Automated TurnStill or Manuel Turnstil or Monitor? (a/t/m)");
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServThread.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}