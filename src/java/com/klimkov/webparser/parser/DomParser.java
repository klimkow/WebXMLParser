/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.klimkov.webparser.parser;
import com.klimkov.webparser.creator.InitAirplaneList;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import com.klimkov.webparser.unit.Airfreighter;
import com.klimkov.webparser.unit.Airliner;
import com.klimkov.webparser.unit.Airpost;
import java.io.IOException;
import org.xml.sax.SAXException;
import com.klimkov.webparser.unit.AircraftException;
import com.klimkov.webparser.unit.AirplaneList;
import org.apache.log4j.Logger;


/**
 *
 * @author Nigga
 */
public class DomParser extends ParserBuilder {  
    private static final Logger LOG = Logger.getLogger(ParserBuilder.class);
    
    @Override
    public AirplaneList parse(String filename){
        AirplaneList airplaneList = InitAirplaneList.init();
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            documentFactory.setValidating(false);
            documentFactory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(filename));
            
            
            NodeList nAirliner = document.getDocumentElement().getElementsByTagName(TagNameConst.AIRLINER);
            for(int tmp = 0; tmp < nAirliner.getLength(); tmp++)
            {
                NodeList nodeList = nAirliner.item(tmp).getChildNodes();
                
                airplaneList.addAirplane(new Airliner(nAirliner.item(tmp).getAttributes().item(0).getTextContent(),
                        new Double(getTagText(TagNameConst.CARRYING, nodeList)),
                        new Double(getTagText(TagNameConst.CAPACITY, nodeList)),
                        new Integer(getTagText(TagNameConst.DISTANCE, nodeList)),
                        new Integer(getTagText(TagNameConst.FUEL, nodeList)),
                        new Integer(getTagText(TagNameConst.SEATS_NUMBER, nodeList))
                ));
            }
            
            NodeList nAirfreighter = document.getDocumentElement().getElementsByTagName(TagNameConst.AIRFREIGHTER);
            for(int tmp = 0; tmp < nAirfreighter.getLength(); tmp++)
            {
                NodeList nodeList = nAirfreighter.item(tmp).getChildNodes();
                
                airplaneList.addAirplane(new Airfreighter(nAirfreighter.item(tmp).getAttributes().item(0).getTextContent(),
                        new Double(getTagText(TagNameConst.CARRYING, nodeList)),
                        new Double(getTagText(TagNameConst.CAPACITY, nodeList)),
                        new Integer(getTagText(TagNameConst.DISTANCE, nodeList)),
                        new Integer(getTagText(TagNameConst.FUEL, nodeList))
                ));               
            }
            
            NodeList nAirpost = document.getDocumentElement().getElementsByTagName(TagNameConst.AIRPOST);
            for(int tmp = 0; tmp < nAirpost.getLength(); tmp++)
            {
                NodeList nodeList = nAirpost.item(tmp).getChildNodes();
                
                airplaneList.addAirplane(new Airpost(nAirpost.item(tmp).getAttributes().item(0).getTextContent(),
                        new Double(getTagText(TagNameConst.CARRYING, nodeList)),
                        new Double(getTagText(TagNameConst.CAPACITY, nodeList)),
                        new Integer(getTagText(TagNameConst.DISTANCE, nodeList)),
                        new Integer(getTagText(TagNameConst.FUEL, nodeList)),
                        Airpost.PriorityLevel.valueOf(nAirpost.item(tmp).getAttributes().item(1).getTextContent().toString().toUpperCase())
                ));                
            } 
            
        }
        catch(AircraftException e){
            LOG.error(e);
        }
        catch (SAXException ex) {
            LOG.error(ex);
        } 
        catch (IOException ex) {
            LOG.error(ex);
        }
        catch (ParserConfigurationException ex) {
            LOG.error(ex);
        } 
        catch (ParserException ex) {
            LOG.error(ex);
        } 
        return airplaneList;        
    }
    private static String getTagText(String tagName, NodeList nodeList) throws ParserException{
        for(int i =0; i < nodeList.getLength(); i++){
                    Node node = nodeList.item(i);
                    if(node.getNodeType() == Node.ELEMENT_NODE){
                        Element element = (Element) node;
                        if(element.getTagName().contains(tagName)){
                            return element.getTextContent();
                        }
                    }
        }
        throw new ParserException("The tag with name " + tagName + " wasn't found");
                        
    }
}
