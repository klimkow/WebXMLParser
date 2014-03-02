/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klimkov.webparser.parser.handler;
import com.klimkov.webparser.creator.InitAirplaneList;
import com.klimkov.webparser.parser.TagNameConst;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import com.klimkov.webparser.unit.Airliner;
import com.klimkov.webparser.unit.Airpost;
import com.klimkov.webparser.unit.Airfreighter;
import static com.klimkov.webparser.unit.Airpost.PriorityLevel;
import com.klimkov.webparser.unit.AircraftException;
import com.klimkov.webparser.unit.AirplaneList;
import java.util.logging.Level;
import java.util.logging.Logger;

enum AirplaneProperties {
    CAPACITY, CARRYING, FUEL, DISTANCE, SEATS_NUMBER, PRIORITY
}

/**
 *
 * @author Nigga
 */

public class SaxHandler implements ContentHandler {
    public enum AirplaneProperties {
        CAPACITY, CARRYING, FUEL, DISTANCE, SEATS_NUMBER, PRIORITY
    }
    private AirplaneProperties ap;
    private Integer index = 0;
    private AirplaneList airplaneList = InitAirplaneList.init();
       
    @Override
    public void startElement(String uri, String localName,  String qName, Attributes attrs) {
        try{
            if(qName.equals(TagNameConst.AIRLINER)){                           
                airplaneList.addAirplane(new Airliner());
                airplaneList.getAirplane(index).setId(attrs.getValue(0));
            }  
            if(qName.equals(TagNameConst.AIRFREIGHTER)){
                airplaneList.addAirplane(new Airfreighter());
                airplaneList.getAirplane(index).setId(attrs.getValue(0));           
            } 
            if(qName.equals(TagNameConst.AIRPOST)){
                PriorityLevel level = PriorityLevel.valueOf(attrs.getValue(1).toUpperCase());
                airplaneList.addAirplane(new Airpost());
                airplaneList.getAirplane(index).setId(attrs.getValue(0));
                airplaneList.findMethod(index, "setPriority", level);
            } 
            if(qName.equals(TagNameConst.CAPACITY)){
                ap = AirplaneProperties.CAPACITY;
            }
            if(qName.equals(TagNameConst.CARRYING)){
                ap = AirplaneProperties.CARRYING;
            }
            if(qName.equals(TagNameConst.FUEL)){
                ap = AirplaneProperties.FUEL;
            }
            if(qName.equals(TagNameConst.DISTANCE)){
                ap = AirplaneProperties.DISTANCE;
            }
            if(qName.equals(TagNameConst.SEATS_NUMBER)){
                ap = AirplaneProperties.SEATS_NUMBER;
            }
        }
        catch (AircraftException ex) {
                Logger.getLogger(SaxHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    @Override
    public void endElement(String uri, String localName, String qName) {
        if(qName.equals(TagNameConst.AIRLINER) || qName.equals(TagNameConst.AIRFREIGHTER) || qName.equals(TagNameConst.AIRPOST)){
            index++;
            ap = null;
        }
    } 
    @Override
    public void characters(char[] ch, int start,  int length) {                    
         String s = new String(ch, start, length).trim();
         if(ap == null || s.isEmpty()) return;
         try{
             switch (ap) { 
             case CAPACITY: airplaneList.getAirplane(index).setCapacity(new Double(s)); break; 
             case CARRYING: airplaneList.getAirplane(index).setCarrying(new Double(s)); break; 
             case FUEL: airplaneList.getAirplane(index).setFuel(new Integer(s)); break; 
             case DISTANCE: airplaneList.getAirplane(index).setDistance(new Integer(s)); break;
             case SEATS_NUMBER: airplaneList.findMethod(index, "setSeatsNumber", new Integer(s));
         } 
         }
         catch(AircraftException e){
             
         } 
    }
    public AirplaneList getAirplaneList(){
        return this.airplaneList;
    }
    public void startDocument() {}    
    public void skippedEntity (String name){}
    public void processingInstruction (String target, String data){}
    public void ignorableWhitespace (char ch[], int start, int length){}
    public void endPrefixMapping (String prefix){}
    public void startPrefixMapping (String prefix, String uri){}
    public void endDocument(){}
    public void setDocumentLocator (Locator locator){}
} 

