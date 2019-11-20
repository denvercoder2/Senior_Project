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

import ScottWork.main.Convert2RA;
import ScottWork.main.Constellations;
import ScottWork.main.MessierDeep;

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
			System.out.println("COULD NOT OPEN FILE");
			e.printStackTrace();
		}		
		

		String[] planets = {"sol","mercury","venus","mars","jupiter","saturn","uranus","neptune"};
		for (int i = 1; i < planets.length; i++) {
			SpaceObj tempObj = new SpaceObj();
			tempObj.setType("PLAN");
			tempObj.setProperName(planets[i]);
			spaceObjList.add(tempObj);
		}	
		System.out.println(spaceObjList.size());
		for (int i = 1; i < 31; i++) {
			Constellations Cobj = Constellations.CreateConstArr("Constellations.xml", i);
			SpaceObj tempObj = new SpaceObj();
			tempObj.setType("CONST");
			tempObj.setConstName(Cobj.getName());
			tempObj.setAbbrev(Cobj.getAbbrev());
			tempObj.setEnglish(Cobj.getEnglish());
			tempObj.setRA(Cobj.getRA());
			tempObj.setDec(Cobj.getDec());
			spaceObjList.add(tempObj);
		}		
		/*
		for (int i = 0; i < 111; i++) {
			ArrayList<String> MessierDeepObj = MessierDeep.MDSO("MessierDeep.xml", i);
			SpaceObj tempObj = new SpaceObj();
			tempObj.setType("MESR");
			tempObj.setProperName(MessierDeepObj.get(1));
			tempObj.setConstName(MessierDeepObj.get(3));
			tempObj.setMagnitude(MessierDeepObj.get(9));
			double tempRAHour = Double.parseDouble(MessierDeepObj.get(4));
			double tempRAMin = Double.parseDouble(MessierDeepObj.get(5));
			tempObj.setRA(Double.toString(Convert2RA.Convert(tempRAHour,tempRAMin)));
		}*/
		
		
		String outputStr;
		String stringArgument;
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		
		boolean doOnce = false;
		for (int k = 0; k < spaceObjList.size(); k++) {
			//System.out.println(spaceObjList.get(k).getRA());
			//System.out.println(spaceObjList.get(k).getDec());
			String argument11 = "";
			if (spaceObjList.get(k).getProperName() == "mercury")
				argument11 = "mercury";
			else if (spaceObjList.get(k).getProperName() == "venus")
				argument11 = "venus";
			else if (spaceObjList.get(k).getProperName() == "mars")
				argument11 = "mars";
			else if (spaceObjList.get(k).getProperName() == "jupiter")
				argument11 = "jupiter";
			else if (spaceObjList.get(k).getProperName() == "saturn")
				argument11 = "saturn";
			else if (spaceObjList.get(k).getProperName() == "uranus")
				argument11 = "uranus";
			else if (spaceObjList.get(k).getProperName() == "neptune")
				argument11 = "neptune";
			else if (spaceObjList.get(k).getProperName() == "sol")
				argument11 = "sol";
			String RAtoSend = spaceObjList.get(k).getRA();
			String DecToSend = spaceObjList.get(k).getDec();
			if (spaceObjList.get(k).getRA() == null) {
				RAtoSend = "0";
				DecToSend = "0"; 
				}
			stringArgument = RAtoSend 								// 0.RA
					+ ":" + DecToSend					 			// 1.DEC
					+ ":" + "34.7251" 								// 2.Lat (UAH LAT)
					+ ":" +	"86.6398" 								// 3.Lon (UAH LONG)
					+ ":" + c.get(Calendar.HOUR_OF_DAY)				// 4.hour
					+ ":" + c.get(Calendar.MINUTE)					// 5.min
					+ ":" + c.get(Calendar.SECOND)					// 6.sec
					+ ":" + c.get(Calendar.YEAR)					// 7.year
					+ ":" + (c.get(Calendar.MONTH)+1)				// 8.month
					+ ":" + c.get(Calendar.DAY_OF_MONTH)			// 9.day
					+ ":" + "FALSE"									// 10.dst
					+ ":" + argument11;								// 11.obj
			String[] arguments = {"solveLocation.py", stringArgument};
			if (doOnce == false) {
				PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
				doOnce = true;
			}
			StringWriter out = new StringWriter();
			python.setOut(out);
			python.exec("from Article import solveLocation");
			PyObject func = python.get("solveLocation");
			func.__call__(new PyString(arguments[1]));
			outputStr = out.toString();
			System.out.println(outputStr);

			spaceObjList.get(k).setAltitude(Double.parseDouble(outputStr.substring(outputStr.indexOf(',')+2,outputStr.indexOf(')'))));
			spaceObjList.get(k).setAzimuth(Double.parseDouble(outputStr.substring(outputStr.lastIndexOf(',')+2,outputStr.lastIndexOf(')'))));
		}
		
		System.out.println(spaceObjList.get(24).getProperName());
		System.out.println(spaceObjList.get(24).getAltitude());
		System.out.println(spaceObjList.get(24).getAzimuth());
		
		for (int i = 0; i < spaceObjList.size(); i++) {
			if (spaceObjList.get(i).getProperName() == "uranus") {
				System.out.println(spaceObjList.get(i).getProperName());
				System.out.println(spaceObjList.get(i).getAltitude());
				System.out.println(spaceObjList.get(i).getAzimuth());
				break;
			}
		}
		
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
		
		
		System.out.println(spaceObjList.size());
		for (int i = 1; i < 31; i++) {
			
			Constellations Cobj = Constellations.CreateConstArr("Constellations.xml", i);
			SpaceObj tempObj = new SpaceObj();
			tempObj.setType("CONST");
			tempObj.setConstName(Cobj.getName());
			tempObj.setAbbrev(Cobj.getAbbrev());
			tempObj.setEnglish(Cobj.getEnglish());
			tempObj.setRA(Cobj.getRA());
			tempObj.setDec(Cobj.getDec());
			spaceObjList.add(tempObj);
		}	
		
		
		String outputStr;
		String stringArgument;
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		
		boolean doOnce = false;
		for (int k = 0; k < spaceObjList.size(); k++) {
			//System.out.println(spaceObjList.get(k).getRA());
			//System.out.println(spaceObjList.get(k).getDec());
			String argument11 = "";
			if (spaceObjList.get(k).getProperName() == "mercury")
				argument11 = "mercury";
			else if (spaceObjList.get(k).getProperName() == "venus")
				argument11 = "venus";
			else if (spaceObjList.get(k).getProperName() == "mars")
				argument11 = "mars";
			else if (spaceObjList.get(k).getProperName() == "jupiter")
				argument11 = "jupiter";
			else if (spaceObjList.get(k).getProperName() == "saturn")
				argument11 = "saturn";
			else if (spaceObjList.get(k).getProperName() == "uranus")
				argument11 = "uranus";
			else if (spaceObjList.get(k).getProperName() == "neptune")
				argument11 = "neptune";
			else if (spaceObjList.get(k).getProperName() == "sol")
				argument11 = "sol";
			String RAtoSend = spaceObjList.get(k).getRA();
			String DecToSend = spaceObjList.get(k).getDec();
			if (spaceObjList.get(k).getRA() == null) {
				RAtoSend = "0";
				DecToSend = "0"; 
				}
			stringArgument = RAtoSend 								// 0.RA
					+ ":" + DecToSend					 			// 1.DEC
					+ ":" + "34.7251" 								// 2.Lat (UAH LAT)
					+ ":" +	"86.6398" 								// 3.Lon (UAH LONG)
					+ ":" + HOUR									// 4.hour
					+ ":" + MIN										// 5.min
					+ ":" + SEC										// 6.sec
					+ ":" + YEAR									// 7.year
					+ ":" + MONTH									// 8.month
					+ ":" + DAY										// 9.day
					+ ":" + "FALSE"									// 10.dst
					+ ":" + "";										// 11.obj
			String[] arguments = {"solveLocation.py", stringArgument};
			if (doOnce == false) {
				PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
				doOnce = true;
			}
			StringWriter out = new StringWriter();
			python.setOut(out);
			python.exec("from Article import solveLocation");
			PyObject func = python.get("solveLocation");
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
		
		
		System.out.println(spaceObjList.size());
		for (int i = 1; i < 31; i++) {
			Constellations Cobj = Constellations.CreateConstArr("Constellations.xml", i);
			SpaceObj tempObj = new SpaceObj();
			tempObj.setType("CONST");
			tempObj.setConstName(Cobj.getName());
			tempObj.setAbbrev(Cobj.getAbbrev());
			tempObj.setEnglish(Cobj.getEnglish());
			tempObj.setRA(Cobj.getRA());
			tempObj.setDec(Cobj.getDec());
			spaceObjList.add(tempObj);
		}	
		
		
		String outputStr;
		String stringArgument;
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		
		boolean doOnce = false;
		for (int k = 0; k < spaceObjList.size(); k++) {
			//System.out.println(spaceObjList.get(k).getRA());
			//System.out.println(spaceObjList.get(k).getDec());
			String argument11 = "";
			if (spaceObjList.get(k).getProperName() == "mercury")
				argument11 = "mercury";
			else if (spaceObjList.get(k).getProperName() == "venus")
				argument11 = "venus";
			else if (spaceObjList.get(k).getProperName() == "mars")
				argument11 = "mars";
			else if (spaceObjList.get(k).getProperName() == "jupiter")
				argument11 = "jupiter";
			else if (spaceObjList.get(k).getProperName() == "saturn")
				argument11 = "saturn";
			else if (spaceObjList.get(k).getProperName() == "uranus")
				argument11 = "uranus";
			else if (spaceObjList.get(k).getProperName() == "neptune")
				argument11 = "neptune";
			else if (spaceObjList.get(k).getProperName() == "sol")
				argument11 = "sol";
			String RAtoSend = spaceObjList.get(k).getRA();
			String DecToSend = spaceObjList.get(k).getDec();
			if (spaceObjList.get(k).getRA() == null) {
				RAtoSend = "0";
				DecToSend = "0"; 
				}
			stringArgument = RAtoSend 								// 0.RA
					+ ":" + DecToSend					 			// 1.DEC
					+ ":" + LAT										// 2.Lat (UAH LAT)
					+ ":" +	LONG	 								// 3.Lon (UAH LONG)
					+ ":" + HOUR									// 4.hour
					+ ":" + MIN										// 5.min
					+ ":" + SEC										// 6.sec
					+ ":" + YEAR									// 7.year
					+ ":" + MONTH									// 8.month
					+ ":" + DAY										// 9.day
					+ ":" + "FALSE"									// 10.dst
					+ ":" + "";										// 11.obj
			String[] arguments = {"solveLocation.py", stringArgument};
			if (doOnce == false) {
				PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
				doOnce = true;
			}
			StringWriter out = new StringWriter();
			python.setOut(out);
			python.exec("from Article import solveLocation");
			PyObject func = python.get("solveLocation");
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
		
		
		System.out.println(spaceObjList.size());
		for (int i = 1; i < 31; i++) {
			Constellations Cobj = Constellations.CreateConstArr("Constellations.xml", i);
			SpaceObj tempObj = new SpaceObj();
			tempObj.setType("CONST");
			tempObj.setConstName(Cobj.getName());
			tempObj.setAbbrev(Cobj.getAbbrev());
			tempObj.setEnglish(Cobj.getEnglish());
			tempObj.setRA(Cobj.getRA());
			tempObj.setDec(Cobj.getDec());
			spaceObjList.add(tempObj);
		}	
		
		
		String outputStr;
		String stringArgument;
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		
		boolean doOnce = false;
		for (int k = 0; k < spaceObjList.size(); k++) {
			//System.out.println(spaceObjList.get(k).getRA());
			//System.out.println(spaceObjList.get(k).getDec());
			String argument11 = "";
			if (spaceObjList.get(k).getProperName() == "mercury")
				argument11 = "mercury";
			else if (spaceObjList.get(k).getProperName() == "venus")
				argument11 = "venus";
			else if (spaceObjList.get(k).getProperName() == "mars")
				argument11 = "mars";
			else if (spaceObjList.get(k).getProperName() == "jupiter")
				argument11 = "jupiter";
			else if (spaceObjList.get(k).getProperName() == "saturn")
				argument11 = "saturn";
			else if (spaceObjList.get(k).getProperName() == "uranus")
				argument11 = "uranus";
			else if (spaceObjList.get(k).getProperName() == "neptune")
				argument11 = "neptune";
			else if (spaceObjList.get(k).getProperName() == "sol")
				argument11 = "sol";
			String RAtoSend = spaceObjList.get(k).getRA();
			String DecToSend = spaceObjList.get(k).getDec();
			if (spaceObjList.get(k).getRA() == null) {
				RAtoSend = "0";
				DecToSend = "0"; 
				}
			stringArgument = RAtoSend 								// 0.RA
					+ ":" + DecToSend					 			// 1.DEC
					+ ":" + LAT										// 2.Lat (UAH LAT)
					+ ":" +	LONG	 								// 3.Lon (UAH LONG)
					+ ":" + c.get(Calendar.HOUR_OF_DAY)				// 4.hour
					+ ":" + c.get(Calendar.MINUTE)					// 5.min
					+ ":" + c.get(Calendar.SECOND)					// 6.sec
					+ ":" + c.get(Calendar.YEAR)					// 7.year
					+ ":" + (c.get(Calendar.MONTH)+1)				// 8.month
					+ ":" + c.get(Calendar.DAY_OF_MONTH)			// 9.day
					+ ":" + "FALSE"									// 10.dst
					+ ":" + "";										// 11.obj
			String[] arguments = {"solveLocation.py", stringArgument};
			if (doOnce == false) {
				PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
				doOnce = true;
			}
			StringWriter out = new StringWriter();
			python.setOut(out);
			python.exec("from Article import solveLocation");
			PyObject func = python.get("solveLocation");
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

