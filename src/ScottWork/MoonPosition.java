/*
 Calculating the moon's position
*/
import java.util.*;
import java.io.*;
import java.math.*;

public class MoonPosition{


    public static class MoonPos{
        private double lat;
        public double longitude;
    }

    /*
    Function: JDCalc
    Parameters: int day, int month, int year
    Return Type: double
    Purpose: Return a julian day, given a date
    */
    public static double JDCalc(int day, int month, int year){
        double JD = 0.0;
        if (month < 2){
            year = year - 1;
            month = month + 12;
        }
            int A = year/100;
            int B = A/4;
            int C = (2 - A + B);
            int E = (int)(365.25 * (year + 4716));
            int F = (int)(30.6001 * (month + 1));
            JD =  C + day + E + F - 1524.5;

            return JD;
    }

    public static double rev(double x)
    {
        return  x - Math.floor(x/360.0)*360.0;
    }
    /*
    Function: getMoonPos
    Parameters: int day, int month, int year
    Return Type: ArrayList<double>
    Purpose: Return the lat and long of the moon at any point in time
    */
    public static void getMoonPos(int d, int m, int y){

        double JD = JDCalc(d, m, y);
        // http://www.geoastro.de/elevaz/basics/meeus.htm
        // double JC =  (JD-2451545.0) / 36525;
        // double eps = eps = 23.0 + 26.0/60.0 + 21.448/3600.0 - (46.8150*JC + 0.00059*JC*JC - 0.001813*JC*JC*JC)/3600;
        double D = JD -  2451545.0;
        System.out.printf("D: %.5f", D);
        double N = 125.1228 - 0.0529538083  * D;                // (Long asc. node)
        double NormN = rev(N);
        System.out.printf("\nrevN: %.5f", NormN);
        double i =   5.1454;                                    // (Inclination)
        double w = 318.0634 + 0.1643573223  * D;                // (Arg. of perigee)
        double NormW = rev(w);
        System.out.printf("\nrevw: %.5f", NormW);
        double a =  60.2666;                                     // (Mean distance)
        double e = 0.054900;                                     //(Eccentricity)
        double M = 115.3654 + 13.0649929509 * D;                // (Mean anomaly)
        double NormM = M + (129 * 360);                         // Normalized M
        System.out.printf("\nrevM: %.5f", NormM);


        double E0 = NormM + (180/Math.PI) * e * Math.sin(NormM) * (1 + e * Math.cos(NormM));
        double E1 = E0 - (E0 - (180/Math.PI) * e * Math.sin(E0) - M) / (1 - e * Math.cos(E0));

        double x_coord = a * (Math.cos(E0) - e);
        double y_coord = a * (Math.sqrt(1 - e*e) * Math.sin(E0));
        System.out.printf("\nX: %.5f", x_coord);
        System.out.printf("\nY: %.5f", y_coord);

        // true anomaly
        double r = Math.sqrt(x_coord*x_coord + y_coord*y_coord);
        double v = Math.atan2(y_coord, x_coord);    // 259.8605
        System.out.printf("\nR: %.5f", r);
        System.out.printf("\nV: %.5f", v);  // wrong value?

        double xeclip = r * (Math.cos(N) * Math.cos(v+w) - Math.sin(N) * Math.sin(v+w) * Math.cos(i));
        double yeclip = r * (Math.sin(N) * Math.cos(v+w) + Math.cos(N) * Math.sin(v+w) * Math.cos(i));
        double zeclip = r * Math.sin(v+w) * Math.sin(i);
        System.out.printf("\nXE: %.5f", xeclip);
        System.out.printf("\nYE: %.5f", yeclip);
        System.out.printf("\nZE: %.5f", zeclip);
    }

    public static void getMoonPosNew(int d, int m, int y){

    }

    // http://www2.arnes.si/~gljsentvid10/tutorial_.html#7

    public static void main(String[] args){
            int d = 19;
            int m = 4;
            int y = 1990;
            getMoonPos(d, m, y);
    }
}