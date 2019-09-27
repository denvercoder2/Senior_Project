// Placement for the java parsing program
// Imports
import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ParseCreate{


    public static void main(String[] args){
        try{
            File xml_file = new File ("stars.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xml_file);
                    
            doc.getDocumentElement().normalize();
        
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            
            // the total size of xml (reduction already in place)
            
            int edge = 31858;
            for (int j = 0; j < edge; j++){
                NodeList nList = doc.getElementsByTagName("row-" + String.valueOf(j));
                           
            // System.out.println("----------------------------");
        
            for (int counter = 0; counter < nList.getLength(); counter++) {
        
                Node nNode = nList.item(counter);
                        
                // System.out.println("\nCurrent Index in rows :" + nNode.getNodeName());
                String[] templates;
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    
                    // probably want to return these as index of some string array
                    // maybe have a function to convert each to correct type
                    Element eElement = (Element) nNode;
                    // type conversions
                    String StarID = eElement.getElementsByTagName("StarID").item(0).getTextContent();
                    String Hip = eElement.getElementsByTagName("Hip").item(0).getTextContent();
                    String HD = eElement.getElementsByTagName("HD").item(0).getTextContent();
                    String HR = eElement.getElementsByTagName("HR").item(0).getTextContent();
                    String Gliese = eElement.getElementsByTagName("Gliese").item(0).getTextContent();
                    String BayerFlamsteed = eElement.getElementsByTagName("BayerFlamsteed").item(0).getTextContent();
                    String ProperName = eElement.getElementsByTagName("ProperName").item(0).getTextContent();
                    String RA = eElement.getElementsByTagName("RA").item(0).getTextContent();
                    String Dec = eElement.getElementsByTagName("Dec").item(0).getTextContent();
                    String Distance = eElement.getElementsByTagName("Distance").item(0).getTextContent();
                    String Magnitude = eElement.getElementsByTagName("Mag").item(0).getTextContent();
                    String AbsMag = eElement.getElementsByTagName("AbsMag").item(0).getTextContent();
                    String Spectrum = eElement.getElementsByTagName("Spectrum").item(0).getTextContent();
                    String ColorIndex = eElement.getElementsByTagName("ColorIndex").item(0).getTextContent();

                    // Printing them out to check
                    if(Double.valueOf(Magnitude) < 6.0 && Double.valueOf(Magnitude) > 0.0){
                        System.out.println("===========================");
                        System.out.println("StarID: " + StarID);
                        System.out.println("Hip: " + Hip);
                        System.out.println("HD: " + HD);
                        System.out.println("Gliese: " + Gliese);
                        System.out.println("BayerFlamsteed: " + BayerFlamsteed);
                        System.out.println("ProperName: " + ProperName);
                        System.out.println("RA: " + RA);
                        System.out.println("Dec: " + Dec);
                        System.out.println("Distance: " + Distance);
                        System.out.println("Magnitude: " + Magnitude);
                        System.out.println("AbsMag: " + AbsMag);
                        System.out.println("Spectrum: " + Spectrum);
                        System.out.println("ColorIndex: " + ColorIndex);
                        System.out.println("===========================");
                        }
                    }
                }
            }
        
                } catch (Exception e) {
                e.printStackTrace();
                }
        }
    }

