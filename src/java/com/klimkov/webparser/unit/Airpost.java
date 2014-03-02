/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klimkov.webparser.unit;

/**
 *
 * @author Nigga
 */
public class Airpost extends Airplane{
    public enum PriorityLevel {HIGH, NORMAL, LOW};
    private PriorityLevel priority;
    
    public Airpost(){
        
    }
    public Airpost(String id, double carrying, double capacity, Integer distance, Integer fuel, PriorityLevel priority) throws AircraftException{
        super(id, carrying, capacity, distance, fuel);
        this.priority = priority;
    }
    public PriorityLevel getPriority(){
        return this.priority;
    }
    public void setPriority(PriorityLevel priority){
        this.priority = priority;
    }
    @Override
    public String toString() {
        return super.toString() + " priority level:" + this.priority;
    }
}
