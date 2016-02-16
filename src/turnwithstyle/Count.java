/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnwithstyle;

import java.util.concurrent.atomic.AtomicInteger;



/**
 *
 * @author Alex
 */
public class Count{
    
    
    private AtomicInteger ai = new AtomicInteger();
    private AtomicInteger bi = new AtomicInteger();
    
    public void addTurn(){
        ai.addAndGet(1);
    }
    
    public void addStil(){
        bi.addAndGet(1);
    }
     
    public long getStill(){
        return bi.get();
    }
    
    public long getTurnt(){
        return ai.get();
    }
    
}
