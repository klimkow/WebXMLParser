/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.klimkov.webparser.creator;
import com.klimkov.webparser.parser.ParserBuilder;
import com.klimkov.webparser.parser.SaxParser;
import com.klimkov.webparser.parser.DomParser;
import com.klimkov.webparser.parser.StaxParser;
import com.klimkov.webparser.unit.AirplaneList;
/**
 *
 * @author Nigga
 */
public class InitBuilder {
    public static AirplaneList initSax(String filename){
        ParserBuilder parser = ParserBuilder.getInstance(new SaxParser());  
        return parser.parse(filename);        
    }
    public static AirplaneList initStax(String filename){
        ParserBuilder parser = ParserBuilder.getInstance(new StaxParser());  
        return parser.parse(filename);        
    }
    public static AirplaneList initDom(String filename){
        ParserBuilder parser = ParserBuilder.getInstance(new DomParser());  
        return parser.parse(filename);        
    }
}
