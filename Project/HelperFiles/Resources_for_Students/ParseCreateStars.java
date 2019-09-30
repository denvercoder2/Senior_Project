// Placement for the java parsing program
// Imports
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


public class ParseCreateStars{
    
    public static class Star{
        private String starId;
        private String Hip;
        private String HD;
        private String HR;
        private String Gliese;
        private String BayerFlamsteed;
        private String ProperName;
        private String RA;
        private String Dec;
        private String Distance;
        private String Magnitude;
        private String AbsMag;
        private String Spectrum;
        private String ColorIndex;

        // starID constructor
        public void setStarID(String starID){
            this.starId = starID;
        }

        public String getStarID(){
            return this.starId;
        }
        // Hip constructor
        public void setHip(String Hip){
            this.Hip = Hip;
        }
        public String getHip(){
            return this.Hip;
        }
        // HD constructor
        public void setHD(String HD){
            this.HD = HD;
        }
        public String getHD(){
            return this.HD;
        }
        // HR constructor
        public void setHR(String HR){
            this.HR = HR;
        }
        public String getHR(){
            return this.HR;
        }
        // Gleise constructor
        public void setGliese(String Gliese){
            this.Gliese = Gliese;
        }
        public String getGliese(){
            return this.Gliese;
        }
        // BayerFlamsteed constructor
        public void setBayerFlamsteed(String BayerFlamsteed){
            this.BayerFlamsteed = BayerFlamsteed;
        }
        public String getBayerFlamsteed(){
            return this.BayerFlamsteed;
        }
        // Proper name constructor
        public void setProperName(String ProperName){
            this.ProperName = ProperName;
        }
        public String getProperName(){
            return this.ProperName;
        }
        // RA constructor
        public void setRA(String RA){
            this.RA = RA;
        }
        public String getRA(){
            return this.RA;
        }
        // Dec constructor
        public void setDec(String Dec){
            this.Dec = Dec;
        }
        public String getDec(){
            return this.Dec;
        }
        // Distance constructor
        public void setDistance(String Distance){
            this.Distance = Distance;
        }
        public String getDistance(){
            return this.Distance;
        }
        // Magnitude constructor
        public void setMagnitude(String Magnitude){
            this.Magnitude = Magnitude;
        }
        public String getMagnitude(){
            return this.Magnitude;
        }
        // AbsMag constructor
        public void setAbsMag(String AbsMag){
            this.AbsMag = AbsMag;
        }
        public String getAbsMag(){
            return this.AbsMag;
        }
        // Spectrum constructor
        public void setSpectrum(String Spectrum){
            this.Spectrum = Spectrum;
        }
        public String getSpectrum(){
            return this.Spectrum;
        }
        // ColorIndex constructor
        public void setColorIndex(String ColorIndex){
            this.ColorIndex = ColorIndex;
        }
        public String getColorIndex(){
            return this.ColorIndex;
        }
    }

    // function to parse and grab the stars
    public static ArrayList<String> stars (String filename, int edge_condition){
        Star star = new Star();
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
                    String StarID         = eElement.getElementsByTagName("StarID").item(0).getTextContent();
                    String Hip            = eElement.getElementsByTagName("Hip").item(0).getTextContent();
                    String HD             = eElement.getElementsByTagName("HD").item(0).getTextContent();
                    String HR             = eElement.getElementsByTagName("HR").item(0).getTextContent();
                    String Gliese         = eElement.getElementsByTagName("Gliese").item(0).getTextContent();
                    String BayerFlamsteed = eElement.getElementsByTagName("BayerFlamsteed").item(0).getTextContent();
                    String ProperName     = eElement.getElementsByTagName("ProperName").item(0).getTextContent();
                    String RA             = eElement.getElementsByTagName("RA").item(0).getTextContent();
                    String Dec            = eElement.getElementsByTagName("Dec").item(0).getTextContent();
                    String Distance       = eElement.getElementsByTagName("Distance").item(0).getTextContent();
                    String Magnitude      = eElement.getElementsByTagName("Mag").item(0).getTextContent();
                    String AbsMag         = eElement.getElementsByTagName("AbsMag").item(0).getTextContent();
                    String Spectrum       = eElement.getElementsByTagName("Spectrum").item(0).getTextContent();
                    String ColorIndex     = eElement.getElementsByTagName("ColorIndex").item(0).getTextContent();
                    
                    // assigning to attributes of star class
                    star.starId = StarID;
                    star.Hip = Hip;
                    star.HD = HD;
                    star.HR = HR;
                    star.Gliese = Gliese;
                    star.BayerFlamsteed = BayerFlamsteed;
                    star.ProperName = ProperName;
                    star.RA = RA;
                    star.Dec = Dec;
                    star.Distance = Distance;
                    star.Magnitude = Magnitude;
                    star.AbsMag = AbsMag;
                    star.Spectrum = Spectrum;
                    star.ColorIndex = ColorIndex;

                    // Adding them to the template array
                    // we only want attributes of the stars under 6 magnitude
                    if(Double.valueOf(Magnitude) < 6.0 && Double.valueOf(Magnitude) > 0.0){
                        System.out.println(nNode.getNodeName());
                        templates.add(StarID);
                        templates.add(Hip);
                        templates.add(HD);
                        templates.add(HR);
                        templates.add(Gliese);
                        templates.add(BayerFlamsteed);
                        templates.add(ProperName);
                        templates.add(RA);
                        templates.add(Dec);
                        templates.add(Distance);
                        templates.add(Magnitude);
                        templates.add(AbsMag);
                        templates.add(Spectrum);
                        templates.add(ColorIndex);
                    }
                }
            }    
                catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("\tError Handled, no problems");
                }
                return templates;
        }


/*
Special note:
Items in list are mapped to following index:

        starId = stars[0]
        Hip = stars[1]
        HD = stars[2]
        HR = stars[3]
        Gliese = stars[4]
        BayerFlamsteed = stars[5]
        ProperName = stars[6]
        RA = stars[6]
        Dec = stars[7]
        Distance = stars[8]
        Magnitude = stars[9]
        AbsMag = stars[10]
        Spectrum = stars[11]
        ColorIndex = stars[12]

*/
    public static void main(String[] args){
        // Using this for loop to an outer limit,
        // we can loop through all rows and pull
        // out the info for the row (defined above)
        // if it fits criteria. We can do pretty much whatever
        // with the data
        int upper_limit = 31858;
        for (int k = 0; k < upper_limit; k++){
            ArrayList<String> star = stars("stars.xml", k);
            if(!star.isEmpty()){
                System.out.println("====================================================================================================================================");
                System.out.println(star);
                System.out.println("====================================================================================================================================");
            }
            else{
                continue;
            }
        }
    }
}
