// Placement for the java parsing program
package ScottWork;

import java.util.*;
import java.io.*;
import java.math.*;

public class Moon {
    // add attributes as needed
    String date;
    String status;
    
    // constructor
    public Moon(){
    }
    public static void main(String[] args) throws IOException{
    // int month = 4;
    // int day = 30;
    // int year = 1969;

    // ArrayList<String> moon = getPhase(month, day, year);
    // System.out.println(moon);
    Runtime rt = Runtime.getRuntime();
    String[] commands = {"python3.7", "ScottWork/MoonPhase.py"};
    Process proc = rt.exec(commands);
    
    BufferedReader stdInput = new BufferedReader(new 
         InputStreamReader(proc.getInputStream()));
    
    BufferedReader stdError = new BufferedReader(new 
         InputStreamReader(proc.getErrorStream()));
    
    // Read the output from the command
    String s = null;
    while ((s = stdInput.readLine()) != null) {
        System.out.println(s);
    }
    
    // Read any errors from the attempted command
    while ((s = stdError.readLine()) != null) {
        System.out.println(s);
        }
    }
}
