/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klimkov.webparser.unit;

/**
 * 
 * @author Nigga
 */
public class Airliner extends Airplane {   
    private Integer seatsNumber;
        
    public Airliner(){
        
    }
    public Airliner(String id, double carrying, double capacity, Integer distance, Integer fuel, Integer seatsNumber) throws AircraftException{
        super(id, carrying, capacity, distance, fuel);        
        if(seatsNumber > 0){
            this.seatsNumber = seatsNumber;
        }
        else{
            throw new AircraftException("Invalid seatsNumber value");
        } 
    } 
    public Integer getSeatsNumber(){
        return this.seatsNumber;
    }
    public void setSeatsNumber(Integer seatsNumber){
        this.seatsNumber = seatsNumber;
    }
    @Override
    public String toString() {
        return super.toString() + " seats number:" + this.seatsNumber;
    }
    
}
