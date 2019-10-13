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

public class SpaceObjListBuilder {
	static ArrayList<SpaceObj> spaceObjList = new ArrayList<>();
	static XPath xPath = XPathFactory.newInstance().newXPath();
	
	public static ArrayList<SpaceObj> stars (String filename, Document doc, int desiredMagnitude) throws XPathExpressionException{
		/*
		XPathExpression xpathExp = xPath.compile("//text()[normalize-space(.) = '']");
		NodeList emptyTextNodes = (NodeList) xpathExp.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < emptyTextNodes.getLength(); i++) {
			  Node emptyTextNode = emptyTextNodes.item(i);
			emptyTextNode.getParentNode().removeChild(emptyTextNode);
		}*/
		// This is an XPath that finds elements based on a regex for 'row-' and spits out the count of them
		XPathExpression exp = xPath.compile("//*[contains(local-name(), 'row-')]");
		NodeList nList = (NodeList)exp.evaluate(doc,XPathConstants.NODESET);
		System.out.println(nList.getLength()); // This prints out how many rows there are of this nodeset.
		// This value will be our upper end.
		
		for (int i = 0; i < nList.getLength(); i++) {
			System.out.println("===============NEW SPACE OBJ CREATED===============");
			SpaceObj tempSpaceObj = new SpaceObj();
//			exp = xPath.compile("//element/*[@id='row-" + i + "']");
			Element e1 = (Element) nList.item(i);
			
			System.out.println(e1.getNodeName());
			//if (e1.getNodeType() == Node.ELEMENT_NODE)
			//	System.out.println("\t" + e1.getFirstChild().getNodeValue());
			
			NodeList children = e1.getChildNodes();
			String tempChild, tempChildValue;
			for (int k = 0; k < children.getLength(); k++) {
				Node child = children.item(k);
				if (child.getNodeType() != Node.TEXT_NODE) {
					tempChild = child.getNodeName();
					System.out.println("\t child  - " + tempChild);
					if (child.getFirstChild() != null && child.getFirstChild().getNodeType() == Node.TEXT_NODE) {
						tempChildValue = child.getFirstChild().getNodeValue();
						//System.out.println("\t\t child value - " + tempChildValue);
					}
				}
				switch(child.getNodeName()) {
				case "StarID":
					tempSpaceObj.setStarID(child.getNodeValue());
					break;
				case "Hip":
					tempSpaceObj.setHip(child.getNodeValue());
					break;
				case "HD":
					tempSpaceObj.setHD(child.getNodeValue());
					break;
				case "HR":
					tempSpaceObj.setHR(child.getNodeValue());
					break;
				case "Gliese":
					tempSpaceObj.setGliese(child.getNodeValue());
					break;
				case "BayerFlamesteed":
					tempSpaceObj.setBayerFlamsteed(child.getNodeValue());
					break;
				case "ProperName":
					tempSpaceObj.setProperName(child.getNodeValue());
					break;
				case "RA":
					tempSpaceObj.setRA(child.getNodeValue());
					break;
				case "Dec":
					tempSpaceObj.setDec(child.getNodeValue());
					break;
				case "Distance":
					tempSpaceObj.setDistance(child.getNodeValue());
					break;
				case "Mag":
					tempSpaceObj.setMagnitude(child.getNodeValue());
					break;
				case "AbsMag":
					tempSpaceObj.setAbsMag(child.getNodeValue());
					break;
				case "Spectrum":
					tempSpaceObj.setSpectrum(child.getNodeValue());
					break;
				case "ColorIndex":
					tempSpaceObj.setColorIndex(child.getNodeValue());
					System.out.println("===============NEW SPACE OBJ DONE===============");
					break;
				}
			}
		
			spaceObjList.add(tempSpaceObj);
		
		}
		return spaceObjList;
	}
}
