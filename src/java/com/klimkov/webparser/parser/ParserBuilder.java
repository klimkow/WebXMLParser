/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.klimkov.webparser.parser;

import com.klimkov.webparser.unit.AirplaneList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Nigga
 */
public abstract class ParserBuilder {    
    private static ParserBuilder instance = null;
    private static final Lock LOCK = new ReentrantLock();
    
    public ParserBuilder(){
        
    }   
    public ParserBuilder getParser(){
        return instance;
    }
    public static ParserBuilder getInstance(ParserBuilder parser) {
        if (instance == null) {
            LOCK.lock();
            instance = parser;
            LOCK.newCondition().signal();
            LOCK.unlock();
        } 
        if(!instance.getClass().equals(parser)){
            LOCK.lock();
            instance = parser;
            LOCK.newCondition().signal();
            LOCK.unlock();
        }
        return instance;
    } 
    public abstract AirplaneList parse(String filename);
}
