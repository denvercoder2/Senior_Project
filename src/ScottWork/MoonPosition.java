/*
 Calculating the moon's position
*/
import java.util.*;
import java.io.*;
import java.math.*;

public class MoonPosition{


    public static class MoonPos{
        private double lat;
        public double long;
    }

    /*
    Function: JDCalc
    Parameters: int day, int month, int year
    Return Type: double
    Purpose: Return a julian day, given a date
    */
    public static double JDCalc(int day, int month, int year){
        double JD = 0.0;
        if (month == 1 || month == 2){
            year = year - 1;
            month = month + 12;
        }
            int A = year/100;
            int B = A/4;
            int C = (2 - A + B);
            int E = (int)(365.25 * (year + 4716));
            int F = (int)(30.6001 * (month + 1));
            JD =  C + day + E + F - 1524.5;

            return JD
    }

    /*
    Function: getMoonPos
    Parameters: int day, int month, int year
    Return Type: ArrayList<double>
    Purpose: Return the lat and long of the moon at any point in time
    */
    public static ArrayList<double> getMoonPos(int y, int m, int d){

    }


    public static void main(String[] args){

    }
}