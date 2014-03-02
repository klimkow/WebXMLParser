package com.klimkov.webparser.parser;
import com.klimkov.webparser.creator.InitAirplaneList;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import com.klimkov.webparser.unit.Airliner;
import com.klimkov.webparser.unit.Airfreighter;
import com.klimkov.webparser.unit.Airpost;        
import com.klimkov.webparser.unit.AirplaneList;
import static com.klimkov.webparser.parser.handler.SaxHandler.AirplaneProperties;
import com.klimkov.webparser.unit.AircraftException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.log4j.Logger;

/**
 *
 * @author Nigga
 */
public class StaxParser extends ParserBuilder {
    private static final Logger LOG = Logger.getLogger(ParserBuilder.class);

    @Override
    public AirplaneList parse(String filename){        
          
        AirplaneList airplaneList = InitAirplaneList.init();
        Integer index = 0;
        AirplaneProperties ap = null;
        try{
            
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(
                    new FileInputStream(filename));

            while(reader.hasNext()){
              int event = reader.next();
              
              switch(event){
                case XMLStreamConstants.START_ELEMENT: 
                    if(TagNameConst.AIRLINER.equals(reader.getLocalName())){
                        airplaneList.addAirplane(new Airliner());
                        airplaneList.getAirplane(index).setId(reader.getAttributeValue(0));
                    }
                    if(TagNameConst.AIRFREIGHTER.equals(reader.getLocalName())){
                        airplaneList.addAirplane(new Airfreighter());
                        airplaneList.getAirplane(index).setId(reader.getAttributeValue(0));
                    }
                    if(TagNameConst.AIRPOST.equals(reader.getLocalName())){
                        airplaneList.addAirplane(new Airpost());
                        airplaneList.getAirplane(index).setId(reader.getAttributeValue(0));
                        airplaneList.findMethod(index, "setPriority", Airpost.PriorityLevel.valueOf(reader.getAttributeValue(1).toUpperCase()));
                    }
                    if(TagNameConst.CAPACITY.equals(reader.getLocalName())){
                        ap = AirplaneProperties.CAPACITY;
                    }
                    if(TagNameConst.CARRYING.equals(reader.getLocalName())){
                        ap = AirplaneProperties.CARRYING;
                    }
                    if(TagNameConst.FUEL.equals(reader.getLocalName())){
                        ap = AirplaneProperties.FUEL;
                    }
                    if(TagNameConst.DISTANCE.equals(reader.getLocalName())){
                        ap = AirplaneProperties.DISTANCE;
                    }
                    if(TagNameConst.SEATS_NUMBER.equals(reader.getLocalName())){
                        ap = AirplaneProperties.SEATS_NUMBER;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                  if(ap == null || reader.getText().isEmpty()) break;   
                  switch (ap) { 
                    case CAPACITY: airplaneList.getAirplane(index).setCapacity(new Double(reader.getText().trim())); break; 
                    case CARRYING: airplaneList.getAirplane(index).setCarrying(new Double(reader.getText().trim())); break; 
                    case FUEL: airplaneList.getAirplane(index).setFuel(new Integer(reader.getText().trim())); break; 
                    case DISTANCE: airplaneList.getAirplane(index).setDistance(new Integer(reader.getText().trim())); break;
                    case SEATS_NUMBER: airplaneList.findMethod(index, "setSeatsNumber", new Integer(reader.getText().trim()));
                  }
                  break;

                case XMLStreamConstants.END_ELEMENT:
                    ap = null;
                    if(reader.getLocalName().equals(TagNameConst.AIRLINER) || reader.getLocalName().equals(TagNameConst.AIRPOST) || reader.getLocalName().equals(TagNameConst.AIRFREIGHTER)){
                            index++;                            
                    }
                break; 
              }
            }
        }
        catch (XMLStreamException ex) {
            LOG.error(ex);
        }
        catch(AircraftException e){
            LOG.error(e);
        } catch (FileNotFoundException ex) {
            LOG.error(ex);
        }
        return airplaneList;
    }
}
