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

    /*
    Function: getPhase()
    Parameters: month, day, year
    Purpose: return an array of correctly
    formatted phase given the date and status
    */
    public static ArrayList<String> getPhase(int month, int day, int year){
        Moon moon = new Moon();
        ArrayList<String> returned_vals = new ArrayList<>();
        int[] ages = {
            18, 0, 11, 22, 3, 14, 25, 6, 17, 28,
             9, 20, 1, 12, 23, 4, 15, 26, 7
        };

        int[] offsets = {
            -1, 1, 0, 1, 2, 3, 4, 5, 7, 7, 9, 9
        };

        String[] descriptions = {
            "New (totally dark)",
            "Waxing Crescent (increasing to full)",
            "In its First Quarter (increasing to full)",
            "Waxing Gibbous (increasing to full)",
            "Full (full light)",
            "Waning Gibbous (decreasing from full)",
            "In its Last Quarter (decreasing from full)",
            "Waning Crescent (decreasing from full)"
        };

        String[] months = {
            " Jan", " Feb", " Mar", " Apr", " May", " Jun",
              " Jul", " Aug", " Sep", " Oct", " Nov", " Dec"
        };

        if (day == 31){
            day = 1;
        }

        int days_into_phase;
        int index; 
        int years_min = 1900;
        int years_max = 2100;

        if (year > 1900){
            year = year % 30;
        }
        days_into_phase = ((ages[(year + 1) % 19] +
                           ((day + offsets[month-1]) % 30) +
                           (year)));

        System.out.println(days_into_phase);
        
        index = (days_into_phase + 2) * (16/59);
        System.out.println(index);
        String status;
        if (index > 7){
            index = 7;
        }
        status = descriptions[index];
        returned_vals.add(status);
                        
    return returned_vals;
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
    System.out.println("Here is the standard output of the command:\n");
    String s = null;
    while ((s = stdInput.readLine()) != null) {
        System.out.println(s);
    }
    
    // Read any errors from the attempted command
    System.out.println("Here is the standard error of the command (if any):\n");
    while ((s = stdError.readLine()) != null) {
        System.out.println(s);
        }
    }
}
