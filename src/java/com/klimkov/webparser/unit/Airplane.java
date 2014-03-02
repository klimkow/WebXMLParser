/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klimkov.webparser.unit;

/**
 *
 * @author Nigga
 */
public abstract class Airplane {
    private StringBuilder id = new StringBuilder(); 
    private double carrying;
    private double capacity;
    private Integer distance;
    private Integer fuel;
    
    public Airplane(){
        
    }
    public Airplane(String id, double carrying, double capacity, Integer distance, Integer fuel) throws AircraftException{        
        if (id != null && carrying > 0 && capacity > 0 && distance > 0 && fuel > 0){
            this.id.append(id);
            this.capacity = capacity;
            this.carrying = carrying;
            this.distance = distance;
            this.fuel= fuel;
        }
        else{
            throw new AircraftException("Invalid init values in Airplane abstract class");
        }        
    }    
    public double getCarrying(){
        return this.carrying;
    }
    public void setId(String id){
        this.id.append(id);
    }
    public String getId(){
        return this.id.toString();
    }
    public void setCarrying(double carrying) throws AircraftException{                
        if(carrying > 0){
            this.carrying = carrying;
        }
        else{
            throw new AircraftException("Invalid carrying value");
        }     
    }
    public double getCapacity(){
        return this.capacity;
    }
    public void setCapacity(Double capacity) throws AircraftException{                   
        if(capacity > 0){
            this.capacity = capacity;
        }
        else{
            throw new AircraftException("Invalid capacity value");
        }     
    }
    public Integer getFuel(){
        return this.fuel;
    }
    public void setFuel(Integer fuel) throws AircraftException{
              
        if(fuel > 0){
            this.fuel = fuel;
        }
        else{
            throw new AircraftException("Invalid fuel value");
        }
        
    }
    public Integer getDistance(){
        return this.distance;
    }
    public void setDistance(Integer distance) throws AircraftException{
          
        if(distance > 0){
            this.distance = distance;
        }
        else{
            throw new AircraftException("Invalid distance value");
        }        
    }
    @Override
    public String toString() {
        return "ID:" + id + " Carrying:" + carrying + "kg capacity:" + capacity + "cub.m distance:" + distance + "km fuel:" + fuel +"l";
    }
}
