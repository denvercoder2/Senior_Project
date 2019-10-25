package JohnWork.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.w3c.dom.Document;

public class SkyMap_Formulae_J {
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy,MM,dd,HH,mm,ss");
	
	public static void main(String[] args) throws ParseException {
		
		Date nowDate = new Date();
		
		long startTime = System.nanoTime();
		
		
		
		ArrayList<SpaceObj> spaceObjList = null;

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
		
		/*
		for (int k = 0; k < spaceObjList.size(); k++) {
			spaceObjList.get(k).solveOwnLocation();
		}*/
		
		System.out.println(nowDate);
		System.out.println("day = " + nowDate.getDate());
		String[] arguments = {"Altest", spaceObjList.get(0).getRA(),
											spaceObjList.get(0).getDec(),
											"34.70160","-86.65970",
											Integer.toString(nowDate.getHours()),
											Integer.toString(nowDate.getMinutes()),
											Integer.toString(nowDate.getSeconds()),
											Integer.toString(nowDate.getYear()+1900),
											Integer.toString(nowDate.getMonth()+1),
											Integer.toString(nowDate.getDate())};
		
		PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		StringWriter out = new StringWriter();
		python.setOut(out);
		python.execfile("Article.py");
		String outputStr = out.toString();
		System.out.println(outputStr);
		
		
		System.out.println("\n" + spaceObjList.get(0).getProperName()
				+ "\nAltitude: " + spaceObjList.get(0).getAltitude() + ""
				+ "\nAzimuth: " + spaceObjList.get(0).getAzimuth() + "\n");
		
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Finished processing in " + elapsedTime / 1000000000.00 + " seconds.");
	}
}
