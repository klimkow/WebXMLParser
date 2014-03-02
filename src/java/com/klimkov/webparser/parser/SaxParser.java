/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.klimkov.webparser.parser;

import com.klimkov.webparser.parser.handler.SaxHandler;
import com.klimkov.webparser.unit.AirplaneList;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Nigga
 */
public class SaxParser extends ParserBuilder {
    private static final Logger LOG = Logger.getLogger(ParserBuilder.class);
    public SaxParser(){
        
    }    
    @Override
    public AirplaneList parse(String filename){
        AirplaneList airplaneList = null;
        try {
            XMLReader reader =  XMLReaderFactory.createXMLReader();
            SaxHandler sh = new SaxHandler();
            reader.setContentHandler(sh);
            reader.parse(filename);
            airplaneList = sh.getAirplaneList();
        } 
        catch (SAXException ex) {
            LOG.error(ex);
        } catch (IOException ex) {
            LOG.error(ex);
        } 
        return airplaneList;
        
    }
    
}
