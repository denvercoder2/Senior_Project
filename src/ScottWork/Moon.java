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
    Function: fixReturn
    Parameters: inverse (double)
    Purpose: To fix a calculation error
    that only occurs sometimes.
    */
    public static double fixReturn(double inverse){
        double new_return = Math.abs(inverse);

        return new_return;
    }
    /*
    Function: returnDec
    Parameters: number (double)
    Purpose: return the decimal value of a double
    */
    public static double returnDec(double number){
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(number));
        int intValue = bigDecimal.intValue();
        double dec_return = bigDecimal.subtract(
            new BigDecimal(intValue)).doubleValue();

        return dec_return;
    }

    /*
    Function: getPhase()
    Parameters: month, day, year (all int)
    Purpose: return an array of correctly
    formatted phase given the date and status
    */
    public static String getPhase(int month, int day, int year){
        MoonPhase moon = new MoonPhase();
        String[] descriptions = {
            "New ( -> Waxing Cresent)",
            "Waxing Crescent ( -> First Quarter)",
            "First Quarter ( -> Waxing Gibbous)",
            "Waxing Gibbous ( -> Full)",
            "Full ( -> Waning Gibbous)",
            "Waning Gibbous ( -> Third Quarter)",
            "Third Quarter( ->  Waning Cresent)",
            "Waning Crescent ( -> New)",
            "New (End of Cycle)"
        };
            double JD = 0.0;
        if (month == 1 || month == 2){
            year = year - 1;
            month = month + 12;
        }
            // source: https://www.subsystems.us/uploads/9/8/9/4/98948044/moonphase.pdf

            // This is essentially a barebones JulianDay method
            int A = year/100;
            int B = A/4;
            int C = (2 - A + B);
            int E = (int)(365.25 * (year + 4716));
            int F = (int)(30.6001 * (month + 1));
            JD =  C + day + E + F - 1524.5;
            System.out.printf("Julian Day: %f", JD);
            
            
            double days_since_new = JD - 2451549.5;
            double new_moons = days_since_new / 29.53;
            double dec_new = returnDec(new_moons);
            double days_into_cycle = (dec_new * 29.53);
            String phase = null;
            boolean visable = true;
            
            System.out.printf("\nFor Year: %d, Month: %d, Day: %d", year, month, day);
            System.out.printf("\n=======================================\n");



            if(days_into_cycle < 0){
                days_into_cycle = fixReturn(days_into_cycle);
            }

            if(days_into_cycle >= 0.00 && days_into_cycle <= 3.68){
                phase = descriptions[0]; // new
            }
            else if(days_into_cycle >= 3.69 && days_into_cycle <= 7.37){
                phase = descriptions[1]; // waning crescent
            }
            else if(days_into_cycle >= 7.38 && days_into_cycle <= 11.05){
                phase = descriptions[2]; // third quarter
            }
            else if(days_into_cycle >= 11.06 && days_into_cycle <= 14.65){
                phase = descriptions[3]; // waning gibbious
            }
            else if(days_into_cycle >= 14.65 && days_into_cycle <= 18.42){
                phase = descriptions[4]; // Full
            }
            else if(days_into_cycle >= 18.43 && days_into_cycle <= 22.11){
                phase = descriptions[5]; // Waxing Gibbious
            }
            else if(days_into_cycle >= 22.12 && days_into_cycle <= 25.80){
                phase = descriptions[6]; // First Quarter
            }
            else if(days_into_cycle >= 25.81 && days_into_cycle <= 29.48){
                phase = descriptions[7]; // Waxing Crescent
            }
            else if(days_into_cycle >= 29.48 && days_into_cycle <= 29.53){
                phase = descriptions[8]; // New (End cycle)
            }
            else{
                System.out.printf("There's an error in floating point calculations somewhere\n");
            }

            System.out.printf("Days into cycle: %f", days_into_cycle,  "\n");

            moon.phase = phase;

            return phase;
    }
        
        public static void main(String[] args) {
            int month = 10;
            int day = 13;
            int year = 2019;
            // object phase is passed
            String test = getPhase(month, day, year);
            System.out.printf("\nPhase: %s", test);
 

    }
}   
