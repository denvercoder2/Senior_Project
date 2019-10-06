// template for planet parsing
import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseCreatePlanet{

    public static class Planet{
        private String PlanetName;
        private String Lscal;
        private String Lprop;
        private String Ascal;
        private String Aconst;
        private String Escal;
        private String Eprop;
        private String Iscal;
        private String Iprop;
        private String Wscal;
        private String Wprop;
        private String Oscal;
        private String Oprop;
    }

    public static ArrayList<String> planets (String filename, int edge_condition){
        Planet planet = new Planet();
        ArrayList<String> templates = new ArrayList<>();
        try{
            File xml_file = new File (filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml_file);
            doc.getDocumentElement().normalize();
            
            // the total size of xml (reduction already in place)
            
            NodeList nList = null;
            for (int j = 0; j < edge_condition; j++){
                // this is to iterate through all rows since they change at every child node value
                nList = doc.getElementsByTagName("row-" + String.valueOf(j));
            }
                // its adding every occurance to one list
                // I want to fix it where every star is its own array
                Node nNode = null;
            for (int counter = 0; counter < nList.getLength(); counter++) {
                nNode = nList.item(counter);
            }
                // System.out.println("\nCurrent Index in rows :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        
                    // probably want to return these as index of some string array
                    // maybe have a function to convert each to correct type
                    Element eElement = (Element) nNode;
                    
                    // type conversions

                    String PlanetName   = eElement.getElementsByTagName("PlanetName").item(0).getTextContent();
                    String LSCAL        = eElement.getElementsByTagName("Lscal").item(0).getTextContent();
                    String LPROP        = eElement.getElementsByTagName("Lprop").item(0).getTextContent();
                    String ASCAL        = eElement.getElementsByTagName("Ascal").item(0).getTextContent();
                    String ACONST       = eElement.getElementsByTagName("Aconst").item(0).getTextContent();
                    String ESCAL        = eElement.getElementsByTagName("Escal").item(0).getTextContent();
                    String EPROP        = eElement.getElementsByTagName("Eprop").item(0).getTextContent();
                    String ISCAL        = eElement.getElementsByTagName("Iscal").item(0).getTextContent();
                    String IPROP        = eElement.getElementsByTagName("Iprop").item(0).getTextContent();
                    String WSCAL        = eElement.getElementsByTagName("Wscal").item(0).getTextContent();
                    String WPROP        = eElement.getElementsByTagName("Wprop").item(0).getTextContent();
                    String OSCAL        = eElement.getElementsByTagName("Oscal").item(0).getTextContent();
                    String OPROP        = eElement.getElementsByTagName("Oprop").item(0).getTextContent();

                    // assigning to attributes of star class
                    planet.PlanetName = PlanetName;
                    planet.Lscal      = LSCAL;
                    planet.Lprop      = LPROP;
                    planet.Ascal      = ASCAL;
                    planet.Aconst     = ACONST;
                    planet.Escal      = ESCAL;
                    planet.Eprop      = EPROP;
                    planet.Iscal      = ISCAL;
                    planet.Iprop      = IPROP;
                    planet.Wscal      = WSCAL;
                    planet.Wprop      = WPROP;
                    planet.Oscal      = OSCAL;
                    planet.Oprop      = OPROP;
                    
                    templates.add(PlanetName);
                    templates.add(LSCAL);
                    templates.add(LPROP);
                    templates.add(ASCAL);
                    templates.add(ACONST);
                    templates.add(ESCAL);
                    templates.add(EPROP);
                    templates.add(ISCAL);
                    templates.add(IPROP);
                    templates.add(WSCAL);
                    templates.add(WPROP);
                    templates.add(OSCAL);
                    templates.add(OPROP);
                }
            }    
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("\tError Handled, no problems");
        }
        return templates;
        }

    
    
    public static void main(String[] args){
        int upper_limit = 10;
        for (int k = 0; k < upper_limit; k++){
            ArrayList<String> planet = planets("planets.xml", k);
            if(!planet.isEmpty()){
                System.out.println("==================================================================================================================================================");
                System.out.println("[0]Planet Name, [1]LSCAL, [2]LPROP, [3]ASCAL, [4]ACONST, [5]ESCAL, [6]EPROP, [7]ISCAL,  [8]IPROP, [9]WSCAL, [10]WPROP, [11]OSCAL, [12]OPROP]");
                System.out.println(planet);
                System.out.println("==================================================================================================================================================");
            }
            else{
                continue;
            }
        }
    }
}
