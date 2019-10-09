// Placement for the java parsing program
package ScottWork;

import java.util.*;
import java.io.*;
import java.math.*;

public class Moon {
    // add attributes as needed
    String status;
    
    // constructor
    public static class MoonPhase{
        private String status;    
    }

    public static String getPhase() throws IOException{
        MoonPhase moon = new MoonPhase();
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
            moon.status = s;
        }
            return s;
    }

    public static void main(String[] args) throws IOException{
        String phase = getPhase();
        System.out.println(phase);
        }
    }

