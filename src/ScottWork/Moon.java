// Placement for the java parsing program
// package ScottWork;

import java.util.*;
import java.io.*;
import java.math.*;

public class Moon {
    // add attributes as needed
    
    // constructor
    public static class MoonPhase{
        private String phase;
    }
    /*
    Function: getPhase()
    Parameters: month, day, year
    Purpose: return an array of correctly
    formatted phase given the date and status
    */
    public static String getPhase(int month, int day, int year){
        MoonPhase moon = new MoonPhase();
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
        int index = 0; 
        String status = null;

            if(year > 1899){
                days_into_phase = (ages[(year) % 19]) +
                ((day + offsets[month-1]) % 30) + (year % 30);
                
                index = (days_into_phase + 2) * 16/59;
                if (index > 7){
                    index = 7;
                }
                status = descriptions[index];
                moon.phase = status;

                System.out.printf("Days into phase: %d", days_into_phase);
                
            }    
        return status;
        }
    

    public static void main(String[] args) {
    int month = 1;
    int day = 1;
    int year = 1900; 

    String moon = getPhase(month, day, year);
    System.out.printf("\n %s",moon);
    }
}   
