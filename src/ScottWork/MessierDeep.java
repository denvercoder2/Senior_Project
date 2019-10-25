
/*
Messier Deep Objects parse and functions
*/
package ScottWork;
import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MessierDeep{

        public static class MDSObjects{

        private String    ObjectNum;
        private String    Name;
        private String    Type;
        private String    Constellation;
        private String    RAHour;
        private String    RAMinute;
        private String    DecSign;
        private String    DecDeg;
        private String    DecMinute;
        private String    Magnitude;
        private String    Info;
        private String    Distance;
    }
    /*
    Function: MDSO
    Parameters: Filename, edge condition
    Purpose:
    To return all attributes of the messier deep space objects, specifically the
    ones we need to use
    */
    public static ArrayList<String> MDSO (String filename, int edge_condition){
        MDSObjects obj = new MDSObjects();
        ArrayList<String> SpaceObjects = new ArrayList<>();
        
        try{
            File xml_file = new File (filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml_file);
            doc.getDocumentElement().normalize();

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
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
        
                    // probably want to return these as index of some string array
                    // maybe have a function to convert each to correct type
                    Element eElement = (Element) nNode;
                    
                    // type conversions
                    String objNum              = eElement.getElementsByTagName("ObjectNum").item(0).getTextContent();
                    String name                = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    String type                = eElement.getElementsByTagName("Type").item(0).getTextContent();
                    String constellation       = eElement.getElementsByTagName("Constellation").item(0).getTextContent();
                    String rahour              = eElement.getElementsByTagName("RAHour").item(0).getTextContent();
                    String raminute            = eElement.getElementsByTagName("RAMinute").item(0).getTextContent();
                    String decSign             = eElement.getElementsByTagName("DecSign").item(0).getTextContent();
                    String decDeg              = eElement.getElementsByTagName("DecDeg").item(0).getTextContent();
                    String decMin              = eElement.getElementsByTagName("DecMinute").item(0).getTextContent();
                    String mag                 = eElement.getElementsByTagName("Magnitude").item(0).getTextContent();
                    String info                = eElement.getElementsByTagName("Info").item(0).getTextContent();
                    String distance            = eElement.getElementsByTagName("Distance").item(0).getTextContent();

                    // assigning to attributes of star class
                    obj.ObjectNum           = objNum;
                    obj.Name                = name;
                    obj.Type                = type;
                    obj.Constellation       = constellation;
                    obj.RAHour              = rahour;
                    obj.RAMinute            = raminute;
                    obj.DecSign             = decSign;
                    obj.DecDeg              = decDeg;
                    obj.DecMinute           = decMin;
                    obj.Magnitude           = mag;
                    obj.Info                = info;
                    obj.Distance            = distance;
                    
                    
                    
                    if(Double.valueOf(mag) < 6.0 && Double.valueOf(mag) > 0.0){
                        
                        SpaceObjects.add(objNum);
                        SpaceObjects.add(name);
                        SpaceObjects.add(type);
                        SpaceObjects.add(constellation);
                        SpaceObjects.add(rahour);
                        SpaceObjects.add(raminute);
                        SpaceObjects.add(decSign);
                        SpaceObjects.add(decDeg);
                        SpaceObjects.add(decMin);
                        SpaceObjects.add(mag);
                        SpaceObjects.add(info);
                        SpaceObjects.add(distance);
                        
                    }
                }
            }    
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("");
        }
        return SpaceObjects;
        }
    public static void main(String[] args) throws InterruptedException{
        long startTime = System.nanoTime();
        // number of messier deep catalog
        int upper_limit = 111;
        for (int k = 0; k < upper_limit; k++){
            ArrayList<String> SpaceObj = MDSO("MessierDeep.xml", k);
            if(!SpaceObj.isEmpty()){
                System.out.println("[0]: Object Number, [1]: Name, [2]: Type, [3]: Constellation, [4]: RAHour, [5]: RAMinute, [6]DecSign, [7]: DecDeg, [8]: DecMinute, [9]: Magnitude, [10]: Info, [11]: Distance");
                System.out.println("===============================================================================================================================================================================");
                System.out.println(SpaceObj);
                System.out.println("===============================================================================================================================================================================");
            }
            else{
                
                continue;
            }
        }
        long endTime = System.nanoTime();

        long timePassed = endTime - startTime;

        double seconds = timePassed / 1000000000F;
                                
        System.out.println("\nExecution time in seconds: " + seconds);
    }
}
