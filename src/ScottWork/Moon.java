// Placement for the java parsing program
// Scott Holley
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

    // getters and setters
    public void setMoonPhase(String starID){
        this.phase = Phase;
    }
    public String getMoonPhase(){
        return this.phase;
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
            "New",
            "Waxing Crescent",
            "First Moon",
            "Waxing Gibbous",
            "Full",
            "Waning Gibbous",
            "Last Quarter",
            "Waning Crescent",
            "New"
        };
            double JD = 0.0;
        if (month == 1 || month == 2){
            year = year - 1;
            month = month + 12;
        }
            // source: http://home.hiwaay.net/~krcool/Astro/moon/moonphase/

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


            /*
            This structure is weird, but I found that it works with all inputs
            Occasionally, and I don't know why, the days into cycle return negative,
            but the correct value that they should be is the absolute value of what it
            returns. So I put a function in to fix this problem. Now works all the time
             
            */
            // if(days_into_cycle < 0){
            //     days_into_cycle = fixReturn(days_into_cycle);
            // }
            if(days_into_cycle >= 0.00 && days_into_cycle <= 1.00){
                phase = descriptions[0]; // new
            }
            else if(days_into_cycle >= 1.01 && days_into_cycle <= 7.40){
                phase = descriptions[1]; // Waxing Crescent
            }
            else if(days_into_cycle >= 7.41 && days_into_cycle <= 8.00){
                phase = descriptions[2]; // Full
            }
            else if(days_into_cycle >= 8.00 && days_into_cycle <= 14.70){
                phase = descriptions[3]; // Waxing Gibbious
            }
            else if(days_into_cycle >= 14.80 && days_into_cycle <= 15.74){
                phase = descriptions[4]; // Full
            }
            else if(days_into_cycle >= 15.75 && days_into_cycle <= 22.00){
                phase = descriptions[5]; // Waning Gibbious
            }
            else if(days_into_cycle >= 22.10 && days_into_cycle <= 22.99){
                phase = descriptions[6]; // Last Quarter
            }
            else if(days_into_cycle >= 23.00 && days_into_cycle <= 29.49){
                phase = descriptions[7]; // Waxing Crescent
            }
            else if(days_into_cycle >= 29.50 && days_into_cycle <= 29.53){
                phase = descriptions[8]; // New (End cycle)
            }
            else{
                System.out.printf("There's an error in floating point calculations somewhere\n");
            }

            System.out.printf("Days into cycle: %.2f", days_into_cycle,  "\n");

            moon.phase = phase;

            return phase;
    }
        
        public static void main(String[] args) {
            int month = 10;
            int day = 15;
            int year = 2019;
            // object phase is passed
            String test = getPhase(month, day, year);
            System.out.printf("\nPhase: %s", test);

    }
}   
