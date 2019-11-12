package ScottWork.main;
// Finding constellations
// Scott Holley

import java.io.*;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Constellations {

    private String ConstName;
    private String Abbrev;
    private String English;
    private String RA;
    private String Dec;
    
    
    public String getName(){
        return this.ConstName;
    }
    public void setName(String ConstName){
        this.ConstName = ConstName;
    }

    public String getAbbrev(){
        return this.Abbrev;
    }
    public void setAbbrev(String Abbrev){
        this.Abbrev = Abbrev;
    }
    
    public String getEnglish(){
        return this.English;
    }
    public void setEnglish(String English){
        this.English = English;
    }

    public void setRA(String RA){
        this.RA = RA;
    }
    public String getRA(){
        return this.RA;
    }
    public void setDec(String Dec){
        this.Dec = Dec;
    }
    public String getDec(){
        return this.Dec;
    }
    
    /*
     * Function: CreateConstArr Parameters: Filename, edge condition Purpose: To
     * return all attributes of the messier deep space objects, specifically the
     * ones we need to use
     */
    public static Constellations CreateConstArr(String filename, int edge_condition) throws NullPointerException {
    	Constellations Cobj = new Constellations();
        ArrayList<Constellations> ConstellationObj = new ArrayList<>();

        try {
            File xml_file = new File(filename);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml_file);
            doc.getDocumentElement().normalize();

            NodeList nList = null;
            for (int j = 0; j < edge_condition; j++) {
                // this is to iterate through all rows since they change at every child node
                // value
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
                Cobj.ConstName = eElement.getElementsByTagName("ConstName").item(0).getTextContent();
                Cobj.Abbrev = eElement.getElementsByTagName("Abbriv").item(0).getTextContent();
                Cobj.English = eElement.getElementsByTagName("English").item(0).getTextContent();
                Cobj.RA = eElement.getElementsByTagName("RA").item(0).getTextContent();
                Cobj.Dec = eElement.getElementsByTagName("Dec").item(0).getTextContent();

            }
        } catch (Exception e) {
            // e.printStackTrace();
            //System.out.println("File " + filename + " not found!");
        }
        return Cobj;
        
    }
/*
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.nanoTime();
        // number of messier deep catalog
        int upper_limit = 229;
        
        for (int k = 0; k < upper_limit; k++) {
            ArrayList<Constellations> Constellation_holder = CreateConstArr("Constellations.xml", k);
            if (!Constellation_holder.isEmpty()) {
                System.out.println("[0]: Constellation Name, [1]: Abbreviation, [2]: English Name, [3]: RA, [4]: Dec");
                System.out.println(
                        "===============================================================================================================================================================================");
                System.out.println(Constellation_holder);
                System.out.println(
                        "===============================================================================================================================================================================");
            } else {

                continue;
            }
        }
        long endTime = System.nanoTime();

        long timePassed = endTime - startTime;

        double seconds = timePassed / 1000000000F;

        System.out.println("\nExecution time in seconds: " + seconds);
    }*/
}
