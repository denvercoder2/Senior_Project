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
	// Reading the comments for the first getSpace function will also describe the others.
	// The other functions sole purposes are to use arguments passed in rather than automatic ones.
	
	// Main for internal testing.
	public static void main(String[] args) throws ParseException {
		ArrayList<SpaceObj> spaceObjList = getSpace();
	}
	
	// This function returns a SpaceObject ArrayList in the case no arguments are sent.
	public static ArrayList getSpace() throws ParseException {
		// Initiates a calendar for us to go by for our own date.
		Calendar c = Calendar.getInstance(); 

		//nowDate = nowDate2;
		// Starts timer for debug use to try to speed up runtime
		long startTime = System.nanoTime();

		// Creates a SpaceObj arraylist for the GUI
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
		
		// This creates SpaceObjs for the planets to be sent into the Jython script later for figuring their Alt and Azimuth
		String[] planets = {"sol","mercury","venus","mars","jupiter","saturn","uranus","neptune"};
		for (int i = 1; i < planets.length; i++) {
			SpaceObj tempObj = new SpaceObj();
			tempObj.setType("PLAN");
			tempObj.setProperName(planets[i]);
			spaceObjList.add(tempObj);
		}	
		
		// This creates the SpaceObjs for the constellation locations that will be sent to the Jython script later for figuring their Alt and Azimuth
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
		// This creates the SpaceObjs for the Messier object locations that will be sent to the Jython script later for figuring their Alt and Azimuth
		for (int i = 0; i < 111; i++) {
			ArrayList<String> MessierDeepObj = MessierDeep.MDSO("MessierDeep.xml", i);

			if(!MessierDeepObj.isEmpty()){
				SpaceObj tempObj = new SpaceObj();
				tempObj.setType("MESR");
				tempObj.setProperName(MessierDeepObj.get(1));
				tempObj.setConstName(MessierDeepObj.get(3));
				tempObj.setMagnitude(MessierDeepObj.get(9));
				double tempRAHour = Double.parseDouble(MessierDeepObj.get(4));
				double tempRAMin = Double.parseDouble(MessierDeepObj.get(5));
				double tempDecHour = Double.parseDouble(MessierDeepObj.get(7));
				double tempDecMin = Double.parseDouble(MessierDeepObj.get(8));
				if (MessierDeepObj.get(6).equals("-"))
					tempDecHour *= -1;

				tempObj.setRA(Double.toString(Convert2RA.ConvertRA(tempRAHour,tempRAMin)));
				tempObj.setDec(Double.toString(Convert2RA.ConvertDec(tempDecHour,tempDecMin)));

				spaceObjList.add(tempObj);
			}
		}*/
		
		System.out.println("with messier = " + spaceObjList.size());

		
		// This begins our Jython implementation.
		// This string strictly holds the output coming from the Python script.
		String outputStr;
		// This string is used to send the argument to the Python script.
		String stringArgument;
		// This is the interpreter required to start a communication to python.
		org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
		
		// I use this boolean during the for loop to only open connection to the solving location once.
		// Doing it multiple times caused noticable speed increases in execution time. 
		boolean doOnce = false;
		
		// This for loop sends the current SpaceObj ArrayList through the Python script to get our Altitude and Azimuth for all the objects in the ArrayList.
		for (int k = 0; k < spaceObjList.size(); k++) {
			//System.out.println(spaceObjList.get(k).getRA());
			//System.out.println(spaceObjList.get(k).getDec());
			// This special argument is used to determine use on figuring a Star or Planet's Altitude and Azimuth when sent to the Python script.
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
			// This is done because the planets don't naturally hold a RA and Dec.
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
			String[] arguments = {"solveLocation.py", stringArgument}; // This prepares the arguments to be sent. Jython allows up to 4 arguments apparently, and only in the form of a string array.
			// This is only done once, as doing it multiple times causes noticable delays.
			if (doOnce == false) {
				PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
				doOnce = true;
			}
			StringWriter out = new StringWriter();
			python.setOut(out);
			python.exec("from Article import solveLocation"); // This actually calls the Python script.
			PyObject func = python.get("solveLocation"); // This calls the specific function in the Python script.
			func.__call__(new PyString(arguments[1])); // This sends our argument to the Python Script.
			outputStr = out.toString();
			System.out.println(outputStr);
			// These 2 set the outputStr items of Alt and Azimuth to the SpaceObjs.
			spaceObjList.get(k).setAltitude(Double.parseDouble(outputStr.substring(outputStr.indexOf(',')+2,outputStr.indexOf(')'))));
			spaceObjList.get(k).setAzimuth(Double.parseDouble(outputStr.substring(outputStr.lastIndexOf(',')+2,outputStr.lastIndexOf(')'))));
		}
		
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Finished processing in " + elapsedTime / 1000000000.00 + " seconds.");
		
		return spaceObjList;
	}
	
	// This function returns a SpaceObject ArrayList in the case all arguments are sent except Latitude and Longitude.
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
		for (int i = 1; i < 111; i++) {
			ArrayList<String> MessierDeepObj = MessierDeep.MDSO("MessierDeep.xml", i);

			if(!MessierDeepObj.isEmpty()){

				SpaceObj tempObj = new SpaceObj();
				tempObj.setType("MESR");
				tempObj.setProperName(MessierDeepObj.get(1));
				tempObj.setConstName(MessierDeepObj.get(3));
				tempObj.setMagnitude(MessierDeepObj.get(9));
				double tempRAHour = Double.parseDouble(MessierDeepObj.get(4));
				double tempRAMin = Double.parseDouble(MessierDeepObj.get(5));
				double tempDecHour = Double.parseDouble(MessierDeepObj.get(7));
				double tempDecMin = Double.parseDouble(MessierDeepObj.get(8));
				if (MessierDeepObj.get(6).equals("-"))
					tempDecHour *= -1;

				tempObj.setRA(Double.toString(Convert2RA.ConvertRA(tempRAHour,tempRAMin)));
				tempObj.setDec(Double.toString(Convert2RA.ConvertDec(tempDecHour,tempDecMin)));

				spaceObjList.add(tempObj);
			}
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

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Finished processing in " + elapsedTime / 1000000000.00 + " seconds.");
		
		return spaceObjList;
	}
	
	// This function returns a SpaceObject ArrayList in the case all arguments are sent.
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
		for (int i = 1; i < 111; i++) {
			ArrayList<String> MessierDeepObj = MessierDeep.MDSO("MessierDeep.xml", i);

			if(!MessierDeepObj.isEmpty()){

				SpaceObj tempObj = new SpaceObj();
				tempObj.setType("MESR");
				tempObj.setProperName(MessierDeepObj.get(1));
				tempObj.setConstName(MessierDeepObj.get(3));
				tempObj.setMagnitude(MessierDeepObj.get(9));
				double tempRAHour = Double.parseDouble(MessierDeepObj.get(4));
				double tempRAMin = Double.parseDouble(MessierDeepObj.get(5));
				double tempDecHour = Double.parseDouble(MessierDeepObj.get(7));
				double tempDecMin = Double.parseDouble(MessierDeepObj.get(8));
				if (MessierDeepObj.get(6).equals("-"))
					tempDecHour *= -1;

				tempObj.setRA(Double.toString(Convert2RA.ConvertRA(tempRAHour,tempRAMin)));
				tempObj.setDec(Double.toString(Convert2RA.ConvertDec(tempDecHour,tempDecMin)));

				spaceObjList.add(tempObj);
			}
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

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Finished processing in " + elapsedTime / 1000000000.00 + " seconds.");
		
		return spaceObjList;
	}
	
	// This function returns a SpaceObject ArrayList in the case when only the Latitude and Longitude arguments are sent.
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
		for (int i = 1; i < 111; i++) {
			ArrayList<String> MessierDeepObj = MessierDeep.MDSO("MessierDeep.xml", i);

			if(!MessierDeepObj.isEmpty()){

				SpaceObj tempObj = new SpaceObj();
				tempObj.setType("MESR");
				tempObj.setProperName(MessierDeepObj.get(1));
				tempObj.setConstName(MessierDeepObj.get(3));
				tempObj.setMagnitude(MessierDeepObj.get(9));
				double tempRAHour = Double.parseDouble(MessierDeepObj.get(4));
				double tempRAMin = Double.parseDouble(MessierDeepObj.get(5));
				double tempDecHour = Double.parseDouble(MessierDeepObj.get(7));
				double tempDecMin = Double.parseDouble(MessierDeepObj.get(8));
				if (MessierDeepObj.get(6).equals("-"))
					tempDecHour *= -1;

				tempObj.setRA(Double.toString(Convert2RA.ConvertRA(tempRAHour,tempRAMin)));
				tempObj.setDec(Double.toString(Convert2RA.ConvertDec(tempDecHour,tempDecMin)));

				spaceObjList.add(tempObj);
			}
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

		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		System.out.println("Finished processing in " + elapsedTime / 1000000000.00 + " seconds.");
		
		return spaceObjList;
	}
}

