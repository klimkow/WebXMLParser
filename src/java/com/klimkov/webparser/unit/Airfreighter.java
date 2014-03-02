/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klimkov.webparser.unit;

/**
 *
 * @author Nigga
 */
public class Airfreighter extends Airplane{
    
    public Airfreighter(){
        
    }
    public Airfreighter(String id, double carrying, double capacity, Integer distance, Integer fuel) throws AircraftException{
        super(id, carrying, capacity, distance, fuel);        
        
    }
   
}
