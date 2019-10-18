package JohnWork.main;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class SkyMap_Formulae_J {
	//static SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
	
	public static void main(String[] args) throws ParseException {
		long startTime = System.nanoTime();
		ArrayList<SpaceObj> spaceObjList = null;
		Date nowDate = new Date();
    	String choiceDate = dateFormat.format(nowDate);
		/*double Mod2Pi_Angle;
		Mod2Pi_Angle = Mod2Pi.Mod2Pi(365);
		
		System.out.println(Mod2Pi_Angle);
		
		
		double JD; String choiceDate = "2008-01-05";
		Date checkAgainstDate = newDateFormat.parse(choiceDate);
		JD = GetJulianDay.GetJulianDay(checkAgainstDate, 20, 14);
		
		System.out.println(JD);
		*/
		//double trueAnomaly = TrueAnomaly.TrueAnomaly(meanAnom, eccentricity)
		String filename = "stars.xml";
		File xml_file = new File (filename);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
	        doc = dBuilder.parse(xml_file);
	        doc.getDocumentElement().normalize();
	        
	        spaceObjList = SpaceObjListBuilder.stars(filename, doc, 6);
	        System.out.println(spaceObjList.size() - 1); // -1 because we COUNT THAT 0
	        
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		for (int k = 0; k < spaceObjList.size(); k++) {
			spaceObjList.get(k).solveOwnLocation();
		}
		
		System.out.println("\n" + spaceObjList.get(0).getProperName()
				+ "\nAltitude: " + spaceObjList.get(0).getAltitude() + ""
				+ "\nAzimuth: " + spaceObjList.get(0).getAzimuth() + "\n");
		
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Finished processing in " + elapsedTime / 1000000000.00 + " seconds.");
	}
}
