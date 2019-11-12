package JohnWork.main;

import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ScottWork.main.Constellations;

public class SpaceObjListBuilder {
	// This is our list for spaceObject and our XPath factory.
	static ArrayList<SpaceObj> spaceObjList = new ArrayList<>();
	static XPath xPath = XPathFactory.newInstance().newXPath();
	
	public static ArrayList<SpaceObj> stars (String filename, Document doc, int desiredMagnitude) throws XPathExpressionException{
		// This is an XPath that finds elements based on a regex for 'row-' and spits out the count of them
		XPathExpression exp = xPath.compile("//*[contains(local-name(), 'row-')]");
		NodeList nList = (NodeList)exp.evaluate(doc,XPathConstants.NODESET);
		System.out.println(nList.getLength()); // This prints out how many rows there are of this nodeset.
		// This value will be our upper end.
		boolean printOutput = true;
		double d_RA = 0, d_Dec = 0;
		
		// Strings to hold out childNode's name and tempChildValue's name
		String tempChild = "", tempChildValue = "";
		// for (int i = 0; i < nList.getLength(); i++)
		for (int i = 0; i < nList.getLength(); i++) {
			//System.out.println("===============NEW SPACE OBJ CREATED===============");
			SpaceObj tempSpaceObj = new SpaceObj();
			tempSpaceObj.setType("STAR");
			Element e1 = (Element) nList.item(i);
			
			// Prints out our node name which has the children for our object
			//System.out.println(e1.getNodeName());
			
			// This grabs all the children of the above node and tests them for their name and value
			NodeList children = e1.getChildNodes();
			
			
			for (int k = 0; k < children.getLength(); k++) {
				Node child = children.item(k);
				

				// Now that we have our child, let's be sure it isn't a text node (or aka, let's make sure it HAS children)
				if (child.getNodeType() != Node.TEXT_NODE) {
					tempChild = child.getNodeName();
					//System.out.println("\t child  - " + tempChild);
					
					// Now that we know it HAS children, let's look at its children, and be sure they hold values we want to take (and are not null)
					if (child.getFirstChild() != null && child.getFirstChild().getNodeType() == Node.TEXT_NODE) {
						tempChildValue = child.getFirstChild().getNodeValue();
						//System.out.println("\t\t child value - " + tempChildValue);
					}
				}
				
				// Using a switch statement to add the values for the object based on nodeName
				switch(child.getNodeName()) {
				case "StarID":
					tempSpaceObj.setStarID(tempChildValue);
					break;
				case "Hip":
					tempSpaceObj.setHip(tempChildValue);
					break;
				case "HD":
					tempSpaceObj.setHD(tempChildValue);
					break;
				case "HR":
					tempSpaceObj.setHR(tempChildValue);
					break;
				case "Gliese":
					tempSpaceObj.setGliese(tempChildValue);
					break;
				case "BayerFlamsteed":
					tempSpaceObj.setBayerFlamsteed(tempChildValue);
					break;
				case "ProperName":
					tempSpaceObj.setProperName(tempChildValue);
					break;
				case "RA":
					tempSpaceObj.setRA(tempChildValue);
					d_RA = Double.parseDouble(tempSpaceObj.getRA()); 
					break;
				case "Dec":
					tempSpaceObj.setDec(tempChildValue);
					d_Dec = Double.parseDouble(tempSpaceObj.getDec()); 
					break;
				case "Distance":
					tempSpaceObj.setDistance(tempChildValue);
					break;
				case "Mag":
					tempSpaceObj.setMagnitude(tempChildValue);
					break;
				case "AbsMag":
					tempSpaceObj.setAbsMag(tempChildValue);
					break;
				case "Spectrum":
					tempSpaceObj.setSpectrum(tempChildValue);
					break;
				case "ColorIndex":
					tempSpaceObj.setColorIndex(tempChildValue);
					//System.out.println("===============NEW SPACE OBJ DONE===============");
					break;
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			// Finally, add the objects to our list
			spaceObjList.add(tempSpaceObj);
		
		}
		
		// return the fully created list.
		return spaceObjList;
	}
	
	public static ArrayList<SpaceObj> combineLists (ArrayList<SpaceObj> SpaceObjList, ArrayList<Constellations> importedItems) {
		System.out.println("ImportedItems size = " + importedItems.size());
		
		ArrayList<SpaceObj> newSpaceObjList = new ArrayList<>();
		for (int i = 0; i < importedItems.size(); i++) {
			SpaceObj tempObj = new SpaceObj(); 
			tempObj.setConstName(importedItems.get(i).getName());
			tempObj.setEnglish(importedItems.get(i).getEnglish());
			tempObj.setRA(importedItems.get(i).getRA());
			tempObj.setDec(importedItems.get(i).getDec());
			newSpaceObjList.add(tempObj);
		}
		SpaceObjList.addAll(newSpaceObjList);
		
		
		return SpaceObjList;
	}
}


