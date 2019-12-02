package JohnWork.main;

import java.io.StringWriter;
import java.util.concurrent.atomic.AtomicInteger;

import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

class JythonThreader extends Thread 
{ 
	org.python.util.PythonInterpreter python;
	String[] arguments;
	SpaceObj spaceObj;
	String returnObj;
	int objIndex;
	Double altitude;
	Double azimuth;
	
    public JythonThreader(String[] arguments, SpaceObj spaceObj, int k) {
    	this.arguments = arguments;
    	this.spaceObj = spaceObj;
    	this.objIndex = k;
	}
    
	public void run() 
    { 
        try
        { 
        	// Displaying the thread that is running 
        	//System.out.println ("Thread " + Thread.currentThread().getId() + " is running");  
        	org.python.util.PythonInterpreter python = new org.python.util.PythonInterpreter();
        	PythonInterpreter.initialize(System.getProperties(), System.getProperties(), arguments);
        	StringWriter out = new StringWriter();
			python.setOut(out);
			python.exec("from Article import solveLocation"); // This actually calls the Python script.
			PyObject func = python.get("solveLocation"); // This calls the specific function in the Python script.
			func.__call__(new PyString(arguments[1])); // This sends our argument to the Python Script.
			String outputStr = out.toString();
			//System.out.println(outputStr);
			// These 2 set the outputStr items of Alt and Azimuth to the SpaceObjs.
			//spaceObj.setAltitude(Double.parseDouble(outputStr.substring(outputStr.indexOf(',')+2,outputStr.indexOf(')'))));
			//spaceObj.setAzimuth(Double.parseDouble(outputStr.substring(outputStr.lastIndexOf(',')+2,outputStr.lastIndexOf(')'))));
			returnObj = outputStr.substring(outputStr.indexOf(',')+2,outputStr.indexOf(')')) + ":" + outputStr.substring(outputStr.lastIndexOf(',')+2,outputStr.lastIndexOf(')'));
			altitude = Double.parseDouble(returnObj.split("[:]")[0]);
			System.out.println(objIndex + " ALTITUDE AT " + altitude);
			azimuth = Double.parseDouble(returnObj.split("[:]")[1]);
			System.out.println(objIndex + " azimuth AT " + azimuth);
			System.out.println(objIndex + " Returning String info " + returnObj);
			return;
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
        	System.out.println(e);
            //System.out.println ("\ne\n"); 
        } 
        
    } 
} 
  
// Main Class 
public class JythonThreadRipper 
{ 
    public static String main(String[] arguments, SpaceObj spaceObj) 
    { 
    	JythonThreader object = new JythonThreader(arguments, spaceObj, 0); 
        object.start();
        
		return object.returnObj; 
    } 
} 

