package JohnWork.main;

import java.io.File;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.w3c.dom.Document;

public class SkyMap_Formulae_J {
	
	public static void main(String[] args) throws ParseException {
		ArrayList<SpaceObj> spaceObjList = getSpace();
	}
	
	public static ArrayList getSpace() throws ParseException {
		Calendar c = Calendar.getInstance(); 

		//nowDate = nowDate2;
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
	        //System.out.println(spaceObjList.size() - 1); // -1 because we COUNT THAT 0
	        
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		String outputStr;
		String stringArgument;
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		
		boolean doOnce = false;
		for (int k = 0; k < spaceObjList.size(); k++) {
			stringArgument = spaceObjList.get(k).getRA() 			// RA
					+ ":" + spaceObjList.get(k).getDec() 			// DEC
					+ ":" + "34.7251" 								// Lat (UAH LAT)
					+ ":" +	"86.6398" 								// Lon (UAH LONG)
					+ ":" + c.get(Calendar.HOUR_OF_DAY)				// hour
					+ ":" + c.get(Calendar.MINUTE)					// min
					+ ":" + c.get(Calendar.SECOND)					// sec
					+ ":" + c.get(Calendar.YEAR)					// year
					+ ":" + (c.get(Calendar.MONTH)+1)				// month
					+ ":" + c.get(Calendar.DAY_OF_MONTH)			// day
					+ ":" + "FALSE";								// dst
			String[] arguments = {"solveLocation.py", stringArgument};
			if (doOnce == false) {
				PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
				doOnce = true;
			}
			StringWriter out = new StringWriter();
			python.setOut(out);
			python.exec("from Article import Btest");
			PyObject func = python.get("Btest");
			func.__call__(new PyString(arguments[1]));
			outputStr = out.toString();
			System.out.println(outputStr);
			spaceObjList.get(k).setAltitude(Double.parseDouble(outputStr.substring(outputStr.indexOf(',')+2,outputStr.indexOf(')'))));
			spaceObjList.get(k).setAzimuth(Double.parseDouble(outputStr.substring(outputStr.lastIndexOf(',')+2,outputStr.lastIndexOf(')'))));
		}
		
		System.out.println(spaceObjList.get(24).getAltitude());
		System.out.println(spaceObjList.get(24).getAzimuth());
		
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Finished processing in " + elapsedTime / 1000000000.00 + " seconds.");
		
		return spaceObjList;
	}
	
	public static ArrayList getSpace(String YEAR, String MONTH, String DAY, String HOUR, String MIN, String SEC) throws ParseException {
		Calendar c = Calendar.getInstance(); 

		//nowDate = nowDate2;
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
	        //System.out.println(spaceObjList.size() - 1); // -1 because we COUNT THAT 0
	        
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		String outputStr;
		String stringArgument;
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		
		boolean doOnce = false;
		for (int k = 0; k < spaceObjList.size(); k++) {
			stringArgument = spaceObjList.get(k).getRA() 			// RA
					+ ":" + spaceObjList.get(k).getDec() 			// DEC
					+ ":" + "34.7251" 								// Lat (UAH LAT)
					+ ":" +	"86.6398" 								// Lon (UAH LONG)
					+ ":" + HOUR									// hour
					+ ":" + MIN										// min
					+ ":" + SEC										// sec
					+ ":" + YEAR									// year
					+ ":" + MONTH									// month
					+ ":" + DAY										// day
					+ ":" + "FALSE";								// dst
			String[] arguments = {"solveLocation.py", stringArgument};
			if (doOnce == false) {
				PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
				doOnce = true;
			}
			StringWriter out = new StringWriter();
			python.setOut(out);
			python.exec("from Article import Btest");
			PyObject func = python.get("Btest");
			func.__call__(new PyString(arguments[1]));
			outputStr = out.toString();
			System.out.println(outputStr);
			spaceObjList.get(k).setAltitude(Double.parseDouble(outputStr.substring(outputStr.indexOf(',')+2,outputStr.indexOf(')'))));
			spaceObjList.get(k).setAzimuth(Double.parseDouble(outputStr.substring(outputStr.lastIndexOf(',')+2,outputStr.lastIndexOf(')'))));
		}
		
		System.out.println(spaceObjList.get(24).getAltitude());
		System.out.println(spaceObjList.get(24).getAzimuth());
		
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Finished processing in " + elapsedTime / 1000000000.00 + " seconds.");
		
		return spaceObjList;
	}
	
	public static ArrayList getSpace(String YEAR, String MONTH, String DAY, String HOUR, String MIN, String SEC, String LAT, String LONG) throws ParseException {
		Calendar c = Calendar.getInstance(); 

		//nowDate = nowDate2;
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
	        //System.out.println(spaceObjList.size() - 1); // -1 because we COUNT THAT 0
	        
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		String outputStr;
		String stringArgument;
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		
		boolean doOnce = false;
		for (int k = 0; k < spaceObjList.size(); k++) {
			stringArgument = spaceObjList.get(k).getRA() 			// RA
					+ ":" + spaceObjList.get(k).getDec() 			// DEC
					+ ":" + LAT										// Lat (UAH LAT)
					+ ":" +	LONG	 								// Lon (UAH LONG)
					+ ":" + HOUR									// hour
					+ ":" + MIN										// min
					+ ":" + SEC										// sec
					+ ":" + YEAR									// year
					+ ":" + MONTH									// month
					+ ":" + DAY										// day
					+ ":" + "FALSE";								// dst
			String[] arguments = {"solveLocation.py", stringArgument};
			if (doOnce == false) {
				PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
				doOnce = true;
			}
			StringWriter out = new StringWriter();
			python.setOut(out);
			python.exec("from Article import Btest");
			PyObject func = python.get("Btest");
			func.__call__(new PyString(arguments[1]));
			outputStr = out.toString();
			System.out.println(outputStr);
			spaceObjList.get(k).setAltitude(Double.parseDouble(outputStr.substring(outputStr.indexOf(',')+2,outputStr.indexOf(')'))));
			spaceObjList.get(k).setAzimuth(Double.parseDouble(outputStr.substring(outputStr.lastIndexOf(',')+2,outputStr.lastIndexOf(')'))));
		}
		
		System.out.println(spaceObjList.get(24).getAltitude());
		System.out.println(spaceObjList.get(24).getAzimuth());
		
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Finished processing in " + elapsedTime / 1000000000.00 + " seconds.");
		
		return spaceObjList;
	}
	
	public static ArrayList getSpace(String LAT, String LONG) throws ParseException {
		Calendar c = Calendar.getInstance(); 

		//nowDate = nowDate2;
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
	        //System.out.println(spaceObjList.size() - 1); // -1 because we COUNT THAT 0
	        
	        
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		String outputStr;
		String stringArgument;
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		
		boolean doOnce = false;
		for (int k = 0; k < spaceObjList.size(); k++) {
			stringArgument = spaceObjList.get(k).getRA() 			// RA
					+ ":" + spaceObjList.get(k).getDec() 			// DEC
					+ ":" + LAT										// Lat (UAH LAT)
					+ ":" +	LONG	 								// Lon (UAH LONG)
					+ ":" + c.get(Calendar.HOUR_OF_DAY)				// hour
					+ ":" + c.get(Calendar.MINUTE)					// min
					+ ":" + c.get(Calendar.SECOND)					// sec
					+ ":" + c.get(Calendar.YEAR)					// year
					+ ":" + (c.get(Calendar.MONTH)+1)				// month
					+ ":" + c.get(Calendar.DAY_OF_MONTH)			// day
					+ ":" + "FALSE";								// dst
			String[] arguments = {"solveLocation.py", stringArgument};
			if (doOnce == false) {
				PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
				doOnce = true;
			}
			StringWriter out = new StringWriter();
			python.setOut(out);
			python.exec("from Article import Btest");
			PyObject func = python.get("Btest");
			func.__call__(new PyString(arguments[1]));
			outputStr = out.toString();
			System.out.println(outputStr);
			spaceObjList.get(k).setAltitude(Double.parseDouble(outputStr.substring(outputStr.indexOf(',')+2,outputStr.indexOf(')'))));
			spaceObjList.get(k).setAzimuth(Double.parseDouble(outputStr.substring(outputStr.lastIndexOf(',')+2,outputStr.lastIndexOf(')'))));
		}
		
		System.out.println(spaceObjList.get(24).getAltitude());
		System.out.println(spaceObjList.get(24).getAzimuth());
		
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Finished processing in " + elapsedTime / 1000000000.00 + " seconds.");
		
		return spaceObjList;
	}
}

