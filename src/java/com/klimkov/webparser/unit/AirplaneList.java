/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.klimkov.webparser.unit;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import org.apache.log4j.Logger;


/**
 *
 * @author Nigga
 */
public class AirplaneList {
    private static final Logger LOG = Logger.getLogger(AirplaneList.class);
    private ArrayList<Airplane> lAirplane = new ArrayList<Airplane>();
    
    public void addAirplane(Airplane airplane) throws AircraftException{
        if(airplane == null){
            throw new AircraftException("Airplane value is null");
        }
        lAirplane.add(airplane);
    }
    public void release(){
        lAirplane.clear();
    }
    public Airplane getAirplane(Integer index){
        return lAirplane.get(index);
    }
    public Set getAirplaneList(){
        Set<Airplane> set = new HashSet<Airplane>(lAirplane);
        return set;
    }
    public void findMethod(Integer index, String methodName, Object param){
        try {
            Airplane airplane = getAirplane(index);
            Class c = airplane.getClass();
            Method method = c.getMethod(methodName, param.getClass());
            Object[] args = new Object[] { param };
            method.invoke(airplane, args);
            
        } catch (NoSuchMethodException ex) {
            LOG.error(ex);
        } catch (SecurityException ex) {
            LOG.error(ex);
        } catch (IllegalAccessException ex) {
            LOG.error(ex);           
        } catch (IllegalArgumentException ex) {
            LOG.error(ex);
        } catch (InvocationTargetException ex) {
            LOG.error(ex);
        }
    }
    
    
}
