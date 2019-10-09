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

<<<<<<< HEAD
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
=======
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
        if (year >= 1900){
        days_into_phase = ((ages[(year + 1) % 19] +
                            ((day + offsets[month-1]) % 30) +
                            (year) % 30));
                            index = (int)(days_into_phase + 2) * 16/59;
                            String status;
                            if (index > 7){
                                index = 7;
                            }
                            status = descriptions[index];
                            returned_vals.add(status);
                        }

    return returned_vals;
    }

    

    public static void main(String[] args) {
    int day = 1;
    int month = 1;
    int year = 1900;

    ArrayList<String> moon = getPhase(month, day, year);
    System.out.println(moon);
>>>>>>> parent of 0ed404a... MoonPhase calculations correct
    }

